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
		
		setLayout(new GridLayout(9, 1));
		JLabel title = new JLabel("PONG", SwingConstants.CENTER);
		JLabel slogan = new JLabel("Dont Miss the Ball", SwingConstants.CENTER);
		JLabel header = new JLabel("Play against...", SwingConstants.CENTER);
		humanButton = new JButton("Human");
		easyAIButton = new JButton("Easy Computer");
		hardAIButton = new JButton("Harder Computer");
		JLabel humanInfo = new JLabel("<html><center>Controls are W and S for Player 1 (left side) and</center><center>Up and Down arrow keys for Player 2 (right side)</center></html>", SwingConstants.CENTER);
		JLabel easyAIInfo = new JLabel("<html><center>A rather easy AI to score against. Ball slowly speeds</center><center>up as it hits the paddles. You are the left player.</center></html>", SwingConstants.CENTER);
		JLabel hardAIInfo = new JLabel("<html><center>A more difficult AI. Ball speeds up more quickly </center><center>with more random directions. You are the left player.</center></html>", SwingConstants.CENTER);
		
		setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
		title.setFont(new Font("Consolas", Font.BOLD, 60));
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, -30, 0));
		slogan.setFont(new Font("Consolas", Font.PLAIN, 20));
		slogan.setBorder(BorderFactory.createEmptyBorder(0, 0, -10, 0));
		header.setFont(new Font("", Font.ITALIC, 30));
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, -10, 0));
		humanInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		easyAIInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		hardAIInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		humanButton.addActionListener(this);
		humanButton.setFont(new Font("Consolas", Font.BOLD, 25));
		humanInfo.setFont(new Font("", Font.PLAIN, 20));
		easyAIButton.addActionListener(this);
		easyAIButton.setFont(new Font("Consolas", Font.BOLD, 25));
		easyAIInfo.setFont(new Font("", Font.PLAIN, 20));
		hardAIButton.addActionListener(this);
		hardAIButton.setFont(new Font("Consolas", Font.BOLD, 25));
		hardAIInfo.setFont(new Font("", Font.PLAIN, 20));
		
		add(title);
		add(slogan);
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
		new GameScreen(window, player2ID);
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
