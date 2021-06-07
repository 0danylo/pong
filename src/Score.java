import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Score {
	int p1 = 0, p2 = 0;
	
	public void render (Graphics g) {
		try {
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\simplenumbers.ttf")));
		} catch (FontFormatException | IOException e) {	}
		g.setFont(new Font("simplenumbers", Font.PLAIN, 40));
		g.setColor(Color.darkGray);
		g.drawString((p1 < 10 ? "0" : "") + p1, 25, 50);
		g.drawString((p2 < 10 ? "0" : "") + p2, 720, 50);
		//FIX 3 DIGIT ISSUE
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{16}, 30));
		g2d.drawLine(400, 0, 400, 500);
	}
	public void incP1 () {
		p1++;
	}
	public void incP2 () {
		p2++;
	}
}
