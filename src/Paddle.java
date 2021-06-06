import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
	int player2ID;
	int velocity;
	Ball ball;
	
	Paddle(int x, int y, int pID, Ball b) {
		super(x, y, 20, 100);
		player2ID = pID;
		ball = b;
	}
	public void move() {
		if (player2ID == 3) {//naive AI
			if (ball.x > 600 && ball.getXV() > 0)
				velocity = ball.y + 10 > y + 59 ? 10 : ball.y + 19 < y + 50 ? -10 : 0;
		} else if (player2ID == 4) {//difficult AI
			velocity = ball.y + 10 > y + 59 ? 10 : ball.y + 19 < y + 50 ? -10 : 0;
		}
		y += velocity;
	}
	public void keyPressed(KeyEvent e) {
		if (player2ID == 1) {//player 1
			if (e.getKeyCode() == KeyEvent.VK_W) {
				velocity = -10;
				move();
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				velocity = 10;
				move();
			}
		} else if (player2ID == 2) {//player 2
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
		if (player2ID == 1) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)
				velocity = 0;
		} else if (player2ID == 2) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
				velocity = 0;
		}
	}
	public void render (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
