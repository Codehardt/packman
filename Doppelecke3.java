import java.awt.Color;
import java.awt.Graphics;

public class Doppelecke3 extends Kachel {

	public Doppelecke3() {
		
		darfDrauf = false;
		
	}

	public void zeichnen(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect(x,y+5,20,10);
        g.fillRect(x+5,y,10,5);

  	}

}
