import javax.swing.*;
import java.awt.*;

public class Pong extends JFrame {
	Pong() {
		GameScreen screen = new GameScreen(4);
		setTitle("Pong");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(screen);
		setBackground(Color.BLACK);
		pack();
		setVisible(true);
	}
	public static void main (String[] args) {
		Pong game = new Pong();
	}
}
