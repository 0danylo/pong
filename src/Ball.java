import java.awt.*;

public class Ball extends Rectangle {
	double xVelocity, yVelocity;
	int player2ID = GameScreen.player2ID, baseSpeed = 5;
	double easyMultiplier = 0.5, hardMultiplier = 1;
	Ball(int x, int y) {
		super(x, y, 20, 20);
		xVelocity = Math.random() > 0.5 ? baseSpeed : -baseSpeed;
		yVelocity = Math.random() > 0.5 ? baseSpeed : -baseSpeed;
	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	public void negateXV () {
		xVelocity *= -1;
		if (player2ID == 3) {
			xVelocity += xVelocity > 0 ? Math.random() * easyMultiplier : Math.random() * -easyMultiplier;
			yVelocity += yVelocity > 0 ? Math.random() * easyMultiplier : Math.random() * -easyMultiplier;
		} else if (player2ID == 4) {
			xVelocity += xVelocity > 0 ? Math.random() * hardMultiplier : Math.random() * -hardMultiplier;
			yVelocity += yVelocity > 0 ? Math.random() * hardMultiplier : Math.random() * -hardMultiplier;
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
