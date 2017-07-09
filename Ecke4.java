import java.awt.Color;
import java.awt.Graphics;

public class Ecke4 extends Kachel {

	public Ecke4() {
		
		darfDrauf = false;
		
	}

	public void zeichnen(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect(x+5,y,10,15);
        g.fillRect(x+15,y+5,5,10);

	}

}
