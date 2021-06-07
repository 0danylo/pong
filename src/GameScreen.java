import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements KeyListener, Runnable {
	public static int player2ID;
	private Window window;
	private final Score score;
	private Ball ball;
	private PlayerPaddle p1paddle, p2paddle;
	
	GameScreen(Window w, int p2ID) {
		player2ID = p2ID;
		window = w;
		
		setPreferredSize(new Dimension(800, 500));
		setFocusable(true);
		addKeyListener(this);
		score = new Score();
		ball = new Ball(390, 240);
		p1paddle = new PlayerPaddle(0, 200, 1, ball);
		p2paddle = new PlayerPaddle(780, 200, p2ID, ball);
		window.setLocationRelativeTo(null);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void checkForCollisions() {
		if (ball.getY() <= 0 || ball.getY() >= 480)
			ball.negateYComp();
		Rectangle in1 = ball.intersection(p1paddle), in2 = ball.intersection(p2paddle);
		if ((!in1.isEmpty() && in1.getWidth() > in1.getHeight()) || (!in2.isEmpty() && in2.getWidth() > in2.getHeight())) {
			ball.negateYComp();
			ball.setXComp(ball.getXComp() + ball.getXComp() > 0 ? 1000 : -1000);
		}	else if (!in1.isEmpty() || !in2.isEmpty())
			ball.negateXComp();
		
		if (p1paddle.getY() <= 0)
			p1paddle.setY(0);
		if (p1paddle.getY() >= 400)
			p1paddle.setY(400);
		if (p2paddle.getY() <= 0)
			p2paddle.setY(0);
		if (p2paddle.getY() >= 400)
			p2paddle.setY(400);
		
		if (ball.getX() <= 0) {
			score.incP2();
			ballToMiddleLine();
			resetPaddles();
		} else if (ball.getX() >= 780) {
			score.incP1();
			ballToMiddleLine();
			resetPaddles();
		}
	}
	public void ballToMiddleLine() {
		ball = new Ball(390, (int) (Math.random() * 481));
	}
	public void resetPaddles() {
		p1paddle = new PlayerPaddle((int) p1paddle.getX(), (int) p1paddle.getY(), 1, ball);
		p2paddle = new PlayerPaddle((int) p2paddle.getX(), (int) p2paddle.getY(), player2ID, ball);
	}
	public void moveAll() {
		ball.move();
		p1paddle.move();
		p2paddle.move();
	}
	public void renderAll(Graphics g) {
		score.render(g);
		ball.render(g);
		p1paddle.render(g);
		p2paddle.render(g);
	}
	public void paint(Graphics g) {
		Image img = createImage(getWidth(), getHeight());
		Graphics imgG = img.getGraphics();
		imgG.setColor(Color.darkGray);
		imgG.setFont(new Font("Consolas", Font.ITALIC, 20));
		imgG.drawString("\"Esc\" to go back to the menu", 420, 480);
		
		Graphics2D g2d = (Graphics2D) imgG.create();
		g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{16}, 30));
		g2d.drawLine(400, 0, 400, 500);
		
		renderAll(imgG);
		g.drawImage(img, 0, 0, this);
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			window.dispose();
			window = new Window(false);
		}
		p1paddle.keyP(e);
		p2paddle.keyP(e);
	}
	public void keyReleased(KeyEvent e) {
		p1paddle.keyR(e);
		p2paddle.keyR(e);
	}
	public void keyTyped(KeyEvent e) { }
	public void run() {
		double previousT = System.currentTimeMillis(), currentT, diff = 0;
		int millisPerFrame = 20;
		do {
			currentT = System.currentTimeMillis();
			diff += (currentT - previousT) / millisPerFrame;
			previousT = currentT;
			if (diff >= 1) {
				moveAll();
				checkForCollisions();
				repaint();
				diff--;
			}
		} while (true);
	}
}
