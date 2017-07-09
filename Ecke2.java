import java.awt.Color;
import java.awt.Graphics;

public class Ecke2 extends Kachel {

	public Ecke2() {
		
		darfDrauf = false;
		
	}

	public void zeichnen(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect(x+5,y+5,10,15);
        g.fillRect(x,y+5,5,10);

	}

}
