import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Scoreboard {
	private int p1 = 0, p2 = 0;
	
	public void render(Graphics g) {
		Font numbers = null;
		try {
			InputStream i = getClass().getResourceAsStream("/simplenumbers.ttf");
			numbers = Font.createFont(Font.TRUETYPE_FONT, i);
		} catch (FontFormatException | IOException e) {	}
		g.setFont(numbers.deriveFont(40f));
		
		g.setColor(Color.darkGray);
		g.drawString((p1 < 10 ? "0" : "") + p1, 25, 50);
		g.drawString((p2 < 10 ? "0" : "") + p2, 735 - (p2 < 10 ? 25 : (25 * (int) Math.log10(p2))), 50);
	}
	public void incP1() {
		p1++;
	}
	public void incP2() {
		p2++;
	}
}
