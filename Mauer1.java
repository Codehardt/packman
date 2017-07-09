import java.awt.Color;
import java.awt.Graphics;

public class Mauer1 extends Kachel {

	public Mauer1() {
		
		darfDrauf = false;
		
	}

	public void zeichnen(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect(x,y+5,20,10);
        
	}

}
