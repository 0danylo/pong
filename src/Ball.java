import java.awt.*;

public class Ball extends Rectangle {
	int xDirection, yDirection;
	Ball(int x, int y, int diameter) {
		super(x, y, diameter, diameter);
		int randXDir = Math.random() > 0.5 ? 5 : -5;
		setXDir(randXDir);
		
		int randYDir = Math.random() > 0.5 ? 5 : -5;
		setYDir(randYDir);
	}
	public void move() {
		x += xDirection;
		y += yDirection;
	}
	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	public void setXDir(int xDir) {
		xDirection = xDir;
	}
	public void setYDir(int yDir) {
		yDirection = yDir;
	}
	public int getXDir() {
		return xDirection;
	}
	public int getYDir() {
		return yDirection;
	}
}
