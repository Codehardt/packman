import java.awt.Color;
import java.awt.Graphics;


public class Geist extends Kachel {
	
	public Color color;
	
	public void zeichnen(Graphics g) {
		
		g.setColor(color);
		g.fillOval(x,y,20,20);
		g.fillOval(x,y+15,5,5);
		g.fillOval(x+5,y+15,5,5);
		g.fillOval(x+10,y+15,5,5);
		g.fillOval(x+15,y+15,5,5);
		
		g.setColor(Color.WHITE);
		g.fillOval(x+4, y+4, 5, 8);
		g.fillOval(x+11,y+4, 5, 8);
		
		g.setColor(Color.GREEN);
		g.fillOval(x+5, y+6, 3, 3);
		g.fillOval(x+12, y+6, 3, 3);
		
	}

}
