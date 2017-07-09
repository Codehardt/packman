import java.awt.Color;
import java.awt.Graphics;

public class Pac extends Kachel {
	
	public int left, right, up, down;
	public int mundZustand;
	public boolean auf;
	
	public Pac(int x, int y) {
		
		this.x = x;
		this.y = y;
		auf = true;
		mundZustand = 0;
		
	}
	
	public void zeichnen(Graphics g) {
		
		int xPoints[];
		int yPoints[];
		int nPoints;
		int augeX;
		int augeY;
		
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, 20, 20);
		
		g.setColor(Color.BLACK);
		
		xPoints = new int[3];
		yPoints = new int[3];
		nPoints = xPoints.length;
		
		xPoints[0] 	= (left > 0) 	? x + 10 
					: (right > 0) 	? x + 10 
					: (up > 0) 		? x + 10 
					: (down > 0) 	? x + 10 
					: x + 10;
		
		xPoints[1] 	= (left > 0) 	? x
					: (right > 0) 	? x + 20 
					: (up > 0) 		? x + 10 + mundZustand
					: (down > 0) 	? x + 10 - mundZustand
					: x + 20;
	
		xPoints[2] 	= (left > 0) 	? x 
					: (right > 0) 	? x + 20 
					: (up > 0) 		? x + 10 - mundZustand 
					: (down > 0) 	? x + 10 + mundZustand
					: x + 20;
		
		yPoints[0] 	= (left > 0) 	? y + 10 
					: (right > 0) 	? y + 10 
					: (up > 0) 		? y + 10 
					: (down > 0) 	? y + 10 
					: y + 10;
		
		yPoints[1] 	= (left > 0) 	? y + 10 - mundZustand 
					: (right > 0) 	? y + 10 + mundZustand
					: (up > 0) 		? y 
					: (down > 0) 	? y + 20
					: y + 10 + mundZustand;
		
		yPoints[2] 	= (left > 0) 	? y + 10 + mundZustand
					: (right > 0) 	? y + 10 - mundZustand
					: (up > 0) 		? y
					: (down > 0) 	? y + 20 
					: y + 10 - mundZustand;
		
		augeX	 	= (left > 0) 	? x + 11 
					: (right > 0) 	? x + 4 
					: (up > 0) 		? x + 3 
					: (down > 0) 	? x + 3 
					: x + 4;
		
		augeY	 	= (left > 0) 	? y + 3 
					: (right > 0) 	? y + 3 
					: (up > 0) 		? y + 11 
					: (down > 0) 	? y + 4
					: y + 3;
		
		g.fillPolygon(xPoints, yPoints, nPoints);
		g.fillOval(augeX, augeY, 5, 5);
		
		mundZustand = (auf) ? mundZustand + 2 : mundZustand - 2;
		
		if (mundZustand >= 10 || mundZustand <= 0) auf = !auf;
		
	}

}
