import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
	int playerID;
	int velocity;
	Ball ball;
	
	Paddle(int x, int y, int width, int height, int pID, Ball b) {
		super(x, y, width, height);
		playerID = pID;
		ball = b;
	}
	public void move() {
		if (playerID == 3) {//naive AI
			if (ball.x > 650 && ball.getXV() > 0)
				velocity = ball.y > y + 9 ? 10 : ball.y + 9 < y ? -10 : 0;
		} else if (playerID == 4) {//endless AI
			if (ball.x > 400 && ball.getXV() > 0)
				velocity = ball.y > y + 9 ? 10 : ball.y + 9 < y ? -10 : 0;
		}
		y += velocity;
	}
	public void keyPressed(KeyEvent e) {
		if (playerID == 1) {//player 1
			if (e.getKeyCode() == KeyEvent.VK_W) {
				velocity = -10;
				move();
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				velocity = 10;
				move();
			}
		} else if (playerID == 2) {//player 2
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				velocity = -10;
				move();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				velocity = 10;
				move();
			}
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
