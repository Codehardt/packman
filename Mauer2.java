import java.awt.Color;
import java.awt.Graphics;

public class Mauer2 extends Kachel {

	public Mauer2() {
		
		darfDrauf = false;
		
	}
	
  	public void zeichnen(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect(x+5,y,10,20);
         
  	}
  
}
