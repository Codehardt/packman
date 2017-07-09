import java.awt.Color;


public class Mathe {

	public static int abstand(int x1, int x2, int y1, int y2) {
		
		return (int)wurzel(quadrat(x1-x2) + quadrat(y1-y2));
		
	}
	
	public static int wurzel(int x) {
		
		return (int)Math.pow(x, 0.5);
		
	}
	
	public static int quadrat(int x) {
		
		return (int)Math.pow(x, 2);
		
	}
	
	public static int zufallszahl(int min, int max) {
		
		return (int)(Math.random() * (max - min + 1)) + min;
		
	}
	
	public static boolean wahrscheinlichkeit(int x) {
		
		return zufallszahl(0,99) < x;
		
	}
	
	public static Color zufallsfarbe(Color[] farben) {
		
		return farben[zufallszahl(0,farben.length - 1)];
		
	}
	
}
