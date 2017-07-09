import java.awt.Color;
import java.awt.Graphics;


public class Futter extends Kachel {

	public boolean gefressen = false;
	
	public Futter(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void zeichnen(Graphics g) {
		
		if (!gefressen) {
			
			int[] xPoints = {x+7,x+13,x+13};
			int[] yPoints = {y+10,y+7,y+13};
			int nPoints = 3;
			g.setColor(Color.LIGHT_GRAY);
			g.fillPolygon(xPoints, yPoints, nPoints);
			
		}
		
	}

}
