import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Score {
	private int p1 = 0, p2 = 0;
	
	public void render(Graphics g) {
		try {
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\simplenumbers.ttf")));
		} catch (FontFormatException | IOException e) {	}
		g.setFont(new Font("simplenumbers", Font.PLAIN, 40));
		
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
