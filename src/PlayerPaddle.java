import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerPaddle extends Rectangle {
	private final int playerID, baseSpeed = 10;
	private int velocity;
	Ball ball;
	
	PlayerPaddle (int x, int y, int pID, Ball b) {
		super(x, y, 20, 100);
		playerID = pID;
		ball = b;
	}
	public void move() {
		if (playerID == 3) {//easy AI
			if (ball.x > 600 && ball.getXV() > 0)
				velocity = ball.y + 10 > y + 59 ? baseSpeed : ball.y + 19 < y + 50 ? -baseSpeed : 0;
		} else if (playerID == 4) {//hard AI
			velocity = ball.y + 10 > y + 59 ? baseSpeed : ball.y + 19 < y + 50 ? -baseSpeed : 0;
		}
		y += velocity;
	}
	public void keyPressed(KeyEvent e) {
		if (playerID == 1) {//human player 1
			if (e.getKeyCode() == KeyEvent.VK_W)
				velocity = -baseSpeed;
			else if (e.getKeyCode() == KeyEvent.VK_S)
				velocity = baseSpeed;
		} else if (playerID == 2) {//human player 2
			if (e.getKeyCode() == KeyEvent.VK_UP)
				velocity = -baseSpeed;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				velocity = baseSpeed;
		}
	}
	public void keyReleased(KeyEvent e) {
		if (playerID == 1) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)
				velocity = 0;
		} else if (playerID == 2) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
				velocity = 0;
		}
	}
	public void render (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
