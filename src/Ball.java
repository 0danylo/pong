import java.awt.*;

public class Ball extends Rectangle {
	double xVelocity, yVelocity;
	int player2ID = GameScreen.player2ID;
	Ball(int x, int y) {
		super(x, y, 20, 20);
		xVelocity = Math.random() > 0.5 ? 5 : -5;
		yVelocity = Math.random() > 0.5 ? 5 : -5;
	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	public void negateXV () {
		xVelocity *= -1;
		if (player2ID == 4) {
			xVelocity += xVelocity > 0 ? Math.random() : -Math.random();
			yVelocity += yVelocity > 0 ? Math.random() : -Math.random();
		}
	}
	public void negateYV () {
		yVelocity *= -1;
	}
	public double getXV () {
		return xVelocity;
	}
	public void render (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
