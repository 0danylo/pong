import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
	int playerNum;
	int yDirection;
	
	Paddle(int x, int y, int width, int height, int pNum) {
		super(x, y, width, height);
		playerNum = pNum;
	}
	public void move() {
		y += yDirection;
	}
	public void keyPressed(KeyEvent e) {
		if (playerNum == 1) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDir(-10);
				move();
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDir(10);
				move();
			}
		} else if (playerNum == 2) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDir(-10);
				move();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDir(10);
				move();
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		if (playerNum == 1) {
			if (e.getKeyCode() == KeyEvent.VK_W)
				setYDir(0);
			else if (e.getKeyCode() == KeyEvent.VK_S)
				setYDir(0);
		} else if (playerNum == 2) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				setYDir(0);
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				setYDir(0);
		}
	}
	public void setYDir(int yDir) {
		yDirection = yDir;
	}
	public void render (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
