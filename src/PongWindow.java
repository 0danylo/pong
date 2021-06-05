import javax.swing.*;
import java.awt.*;

public class PongWindow extends JFrame {
	GameScreen screen;
	PongWindow () {
		screen = new GameScreen();
		setTitle("Pong");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(screen);
		setBackground(Color.BLACK);
		pack();
		setVisible(true);
	}
}
