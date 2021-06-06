import java.awt.*;

public class Ball extends Rectangle {
	int xVelocity, yVelocity;
	Ball(int x, int y, int diameter) {
		super(x, y, diameter, diameter);
		xVelocity = Math.random() > 0.5 ? 5 : -5;
		yVelocity = Math.random() > 0.5 ? 5 : -5;
	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	public void negateXV () {
		xVelocity *= -1;
	}
	public void negateYV () {
		yVelocity *= -1;
	}
	public int getXV () {
		return xVelocity;
	}
	public void render (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
