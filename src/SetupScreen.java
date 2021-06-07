import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupScreen extends JPanel implements ActionListener {
	JButton humanButton, easyAIButton, hardAIButton;
	JLabel title = new JLabel("Pong", SwingConstants.CENTER), header = new JLabel("Play against...", SwingConstants.CENTER), humanInfo, easyAIInfo, hardAIInfo;
	Window window;
	static int player2ID;
	SetupScreen(Window w) {
		window = w;
		
		setLayout(new GridLayout(8, 1));
		humanButton = new JButton("another Human");
		easyAIButton = new JButton("an easier Computer");
		hardAIButton = new JButton("a harder Computer");
		humanButton.addActionListener(this);
		easyAIButton.addActionListener(this);
		hardAIButton.addActionListener(this);
		
		humanInfo = new JLabel("Controls are W and S for player 1 (left side)\n and Up and Down arrow keys for player 2 (right side)", SwingConstants.CENTER);
		easyAIInfo = new JLabel("A rather easy AI to score against.\n Ball slowly speeds up as it hits the paddles.", SwingConstants.CENTER);
		hardAIInfo = new JLabel("A more difficult AI to score against.\n Ball speeds up more quickly.", SwingConstants.CENTER);
		
		setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		title.setFont(new Font("", Font.BOLD, 96));
		title.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
		header.setFont(new Font("", Font.PLAIN, 24));
		header.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		add(title);
		add(header);
		add(humanButton);
		add(humanInfo);
		add(easyAIButton);
		add(easyAIInfo);
		add(hardAIButton);
		add(hardAIInfo);
	}
	public void startGame (int p2ID) {
		player2ID = p2ID;
		
		window.dispose();
		window = new Window(true);
		new GameScreen(window, p2ID);
	}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getSource().equals(humanButton))
			startGame(2);
		else if (e.getSource().equals(easyAIButton))
			startGame(3);
		else if (e.getSource().equals(hardAIButton))
			startGame(4);
	}
}
