import javax.swing.*;
import java.awt.*;

public class Program {
	public static int WINDOW_WIDTH = 768;
	public static int WINDOW_HEIGHT = 432;
	private static JFrame window;
	
	Program() {
		window = new JFrame("Pong");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel panel = new SetupScreen();
		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		window.setResizable(false);
		window.setContentPane(panel);
		window.pack();
		window.setVisible(true);
	}
	public static void startGame(PlayerType[] config) {
		GameScreen screen = new GameScreen(config);
		window.setContentPane(screen);
		window.addKeyListener(new GameScreen.KeyInputHandler(screen));
	}
	public static void renderScreen(Screen screen) {
		Graphics graphics = window.getGraphics();
		screen.render(graphics);
	}
	public static void switchToSetup(Screen screen) {
		window.setContentPane(screen);
		window.revalidate();
	}
	
	public static void main (String[] args) {
		new Program();
	}
}
