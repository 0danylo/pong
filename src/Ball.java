import java.awt.*;

public class Ball extends Rectangle {
	int xDirection, yDirection;
	Ball(int x, int y, int diameter) {
		super(x, y, diameter, diameter);
		xDirection = Math.random() > 0.5 ? 5 : -5;
		yDirection = Math.random() > 0.5 ? 5 : -5;
	}
	public void negateXDir () {
		xDirection *= -1;
	}
	public void negateYDir () {
		yDirection *= -1;
	}
	public void move() {
		x += xDirection;
		y += yDirection;
	}
	public void render (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
