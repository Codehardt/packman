import java.awt.Color;
import java.awt.Graphics;

public class Kreuz extends Kachel {

	public Kreuz() {
		
		darfDrauf = false;
		
	}

	public void zeichnen(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect(x+5,y,10,20);
        g.fillRect(x,y+5,5,10);
        g.fillRect(x+15,y+5,5,10);

	}
	
}
