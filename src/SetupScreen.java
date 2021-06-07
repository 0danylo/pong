import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupScreen extends JPanel implements ActionListener {
	static int player2ID;
	private Window window;
	private final JButton humanButton, easyAIButton, hardAIButton;
	SetupScreen(Window w) {
		window = w;
		
		setLayout(new GridLayout(8, 1));
		JLabel title = new JLabel("PONG", SwingConstants.CENTER);
		JLabel header = new JLabel("Play against...", SwingConstants.CENTER);
		humanButton = new JButton("Human");
		easyAIButton = new JButton("Easy Computer");
		hardAIButton = new JButton("Harder Computer");
		JLabel humanInfo = new JLabel("<html><center>Controls are W and S for player 1 (left side) and</center><center>Up and Down arrow keys for player 2 (right side)</center></html>", SwingConstants.CENTER);
		JLabel easyAIInfo = new JLabel("<html><center>A rather easy AI to score against.</center><center>Ball slowly speeds up as it hits the paddles.</center></html>", SwingConstants.CENTER);
		JLabel hardAIInfo = new JLabel("<html><center>A more difficult AI. Ball speeds up</center><center>more quickly with more random directions.</center></html>", SwingConstants.CENTER);
		
		setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		title.setFont(new Font("Consolas", Font.BOLD, 40));
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, -18, 0));
		header.setFont(new Font("", Font.PLAIN, 15));
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, -15, 0));
		humanInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		easyAIInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		humanButton.addActionListener(this);
		easyAIButton.addActionListener(this);
		hardAIButton.addActionListener(this);
		
		add(title);
		add(header);
		add(humanButton);
		add(humanInfo);
		add(easyAIButton);
		add(easyAIInfo);
		add(hardAIButton);
		add(hardAIInfo);
	}
	public void startGame(int p2ID) {
		player2ID = p2ID;
		
		window.dispose();
		window = new Window(true);
		new GameScreen(window, p2ID);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(humanButton))
			startGame(2);
		else if (e.getSource().equals(easyAIButton))
			startGame(3);
		else if (e.getSource().equals(hardAIButton))
			startGame(4);
	}
}
