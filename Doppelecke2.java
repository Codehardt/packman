import java.awt.Color;
import java.awt.Graphics;

public class Doppelecke2 extends Kachel {

	public Doppelecke2() {
		
		darfDrauf = false;
		
	}
	
	public void zeichnen(Graphics g) {

		g.setColor(Color.BLUE);
        g.fillRect(x+5,y,10,20);
        g.fillRect(x+15,y+5,5,10);
        
	}
	
}
