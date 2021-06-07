import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	Window (boolean isGame) {
		setTitle("Pong");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(isGame ? new GameScreen(this, SetupScreen.player2ID) : new SetupScreen(this));
		setBackground(Color.BLACK);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main (String[] args) {
		new Window(false);
	}
}
