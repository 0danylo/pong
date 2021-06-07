import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerPaddle extends Rectangle {
	private final int playerID, baseSpeed = 10;
	private int velocity;
	Ball ball;
	
	PlayerPaddle(int x, int y, int pID, Ball b) {
		super(x, y, 20, 100);
		playerID = pID;
		ball = b;
	}
	public void move() {
		double yDiff;
		int newV = 0;
		if (playerID > 2) {
			yDiff = ball.getY() - getY();
			newV = yDiff > 49 ? baseSpeed : yDiff < 31 ? -baseSpeed : 0;
		}
		if (playerID == 3) {
			if (ball.getX() > 600 && ball.getXComp() > 0)
				velocity = newV;
		} else if (playerID == 4) {
			if (ball.getXComp() > 0)
				velocity = newV;
		}
		y += velocity;
	}
	public void setY(int newY) {
		y = newY;
	}
	public void keyP(KeyEvent e) {
		if (playerID == 1) {
			if (e.getKeyCode() == KeyEvent.VK_W)
				velocity = -baseSpeed;
			else if (e.getKeyCode() == KeyEvent.VK_S)
				velocity = baseSpeed;
		} else if (playerID == 2) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				velocity = -baseSpeed;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				velocity = baseSpeed;
		}
	}
	public void keyR(KeyEvent e) {
		if (playerID == 1) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)
				velocity = 0;
		} else if (playerID == 2) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
				velocity = 0;
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
