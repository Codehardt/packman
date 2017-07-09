import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Spielfeld extends JPanel implements Runnable, KeyListener {
	
	private final static long 	serialVersionUID 	= 1L;
	private final static int 	SCHRITTWEITE 		= 10;
	private final static int 	TIMESLICE 			= SCHRITTWEITE * 7;
	private final static Futter	icon 				= new Futter(660,45);
	private Kachel[][] 			kachel;
	private Geist[] 			geist;
	private Futter[] 			futter;
	private Pac 				p;
	private long 				start;
	private boolean 			running, pause;
	private int 				gefressen;
	
	public Spielfeld() {

		Thread thread;
		thread = new Thread(this);
		starten();
		thread.start();
		
	}
	
	private void starten() {
		
		String 	filepath, aufbau;
		int 	zeilen, spalten;
		
		geist 		= new Geist[0];
		futter 		= new Futter[0];
		gefressen 	= 0;
		filepath	= "res/feld.txt";
		aufbau 		= MyFileReader.readFile(filepath);
		zeilen		= MyFileReader.zeilen(filepath);
		spalten		= MyFileReader.spalten(filepath);
		
		fillKachel(aufbau, zeilen, spalten);
		
		start 		= System.currentTimeMillis();
		
	}
	
  	private void fillKachel(String aufbau, int zeilen, int spalten) {
  		
  		char[] 	bauteile;
  		char 	bauteil;
  		int 	index, x, y;
  		
  		kachel 		= new Kachel[zeilen][spalten];
        bauteile 	= aufbau.toCharArray();
        index 		= 0;
        
        for (int i = 0; i < kachel.length; i++) {
        	
			y = 20 * i;
			
        	for (int j = 0; j < kachel[i].length; j++) {
        		
    			x 		= 20 * j;
    			bauteil = bauteile[index++];
    			
        		switch (bauteil) {
        		
        			case 'A': 	kachel[i][j] = new Mauer2(); 					break;
        			case 'B': 	kachel[i][j] = new Mauer1(); 					break;
        			case 'C': 	kachel[i][j] = new Ecke1(); 					break;
        			case 'D': 	kachel[i][j] = new Ecke2(); 					break;
        			case 'E': 	kachel[i][j] = new Ecke4(); 					break;
        			case 'F': 	kachel[i][j] = new Ecke3(); 					break;
        			case 'G': 	kachel[i][j] = new Doppelecke1(); 				break;
        			case 'H': 	kachel[i][j] = new Doppelecke2(); 				break;
        			case 'I': 	kachel[i][j] = new Doppelecke3(); 				break;
        			case 'J': 	kachel[i][j] = new Doppelecke4();	 			break;
        			case 'K': 	kachel[i][j] = new Kreuz(); 					break;
        			case 'L': 	kachel[i][j] = new Leer(); 						break;
        			case 'M': 	kachel[i][j] = new Leer(); createFutter(x,y); 	break;
        			case 'N': 	kachel[i][j] = new Leer(); p = new Pac(x,y);  	break;
        			case 'O': 	kachel[i][j] = new Leer(); createGeist(x,y); 	break;
        			default: 	kachel[i][j] = new Leer(); 						break;
        			
                }
        		
        		kachel[i][j].x = x;
        		kachel[i][j].y = y;
    			
        	}
        	
    	}
        
  	}
  	
  	private void createGeist(int x, int y) {
  		
  		Geist temp[];
  		
  		temp 						= geist;
  		geist 						= new Geist[temp.length + 1];
  		geist[temp.length] 			= new Geist();
  		geist[temp.length].color 	= Mathe.zufallsfarbe(new Color[]{Color.RED,Color.GREEN,Color.CYAN,Color.LIGHT_GRAY});
  		geist[temp.length].x 		= x;
  		geist[temp.length].y 		= y;
  		
  		for (int i = 0; i < temp.length; i++)
  			geist[i] = temp[i];
  		
  	}
  	
  	private void createFutter(int x, int y) {
  		
  		Futter temp[];
  		
  		temp 				= futter;
  		futter 				= new Futter[temp.length + 1];
  		futter[temp.length] = new Futter(x,y);
  		
  		for (int i = 0; i < temp.length; i++)
  			futter[i] = temp[i];
  			
  	}
  	
  	private void bewegePacman() {
  		
  		int altX, altY, neuX, neuY;
  		
  		altX = p.x;
  		altY = p.y;
  		neuX = altX - p.left 	+ p.right;
  		neuY = altY - p.up 		+ p.down;
  		
  		if (darfDrauf(neuX,neuY)) {
  			
  			p.x = neuX;
  			p.y = neuY;
  			
  		} 
  		
  		else { 
  			
  			if (p.left + p.right > 0) {
  				
  				if (darfDrauf(neuX, neuY + SCHRITTWEITE)) {
  					
  					p.x = neuX;
  					p.y = neuY + SCHRITTWEITE;
  					
  				}
  				
  				else if (darfDrauf(neuX, neuY - SCHRITTWEITE)) {
  					
  					p.x = neuX;
  					p.y = neuY - SCHRITTWEITE;
  					
  				}
  				
  			}
  			
  			else if (p.up + p.down > 0) {
  				
  				if (darfDrauf(neuX + SCHRITTWEITE, neuY)) {
  					
  					p.x = neuX + SCHRITTWEITE;
  					p.y = neuY;
  					
  				}
  				
  				else if (darfDrauf(neuX - SCHRITTWEITE, neuY)) {
  					
  					p.x = neuX - SCHRITTWEITE;
  					p.y = neuY;
  					
  				}
  				
  			}
  			
  		}
  		
  	}
  	
  	private void fressen() {
  		
  		for (Futter f : futter) {
  			
  			if (!f.gefressen) {
  				
	  			if (Mathe.abstand(p.x, f.x, p.y, f.y) < 20) {
	  				
	  				gefressen++;
	  				f.gefressen = true;
	  				
	  				if (gefressen == futter.length) {
	  					
	  	  				int choose = JOptionPane.showConfirmDialog(this, "Du hast in " + (System.currentTimeMillis() - start) + "ms ALLE Stücke gefressen! Möchtest Du erneut spielen?");
	  	  				
	  	  				if (choose == JOptionPane.YES_OPTION) 	starten();	
	  	  				else 									end();
	  	  					
	  				}
	  				
	  			}
	  			
  			}
  			
  		}
  		
  	}
  	
  	private void bewegeGeister() {
  		
  		for (Geist g : geist) {
  			
  			int 	altX, altY, neuX1, neuX2, neuX3, neuY1, neuY2, neuY3, abstandOben, abstandUnten, abstandRechts, abstandLinks, gewinner1, gewinner2, gewinner3;
  			boolean oben, unten, links, rechts;
  			
  			altX 			= g.x;
  			altY 			= g.y;
  			neuX1 			= altX - SCHRITTWEITE;
  			neuX2 			= altX;
  			neuX3 			= altX + SCHRITTWEITE;
  			neuY1 			= altY - SCHRITTWEITE;
  			neuY2 			= altY;
  			neuY3 			= altY + SCHRITTWEITE;
  			
  			oben			= darfDrauf(neuX2,neuY1);
  			unten			= darfDrauf(neuX2,neuY3);
  			links			= darfDrauf(neuX1,neuY2);
  			rechts			= darfDrauf(neuX3,neuY2);
  			
  			abstandOben 	= (oben) 	? Mathe.abstand(p.x+10, neuX2+10, p.y+10, neuY1+10) 
  							: 10000;
  			abstandUnten 	= (unten) 	? Mathe.abstand(p.x+10, neuX2+10, p.y+10, neuY3+10) 
  							: 10000;
  			abstandLinks 	= (links) 	? Mathe.abstand(p.x+10, neuX1+10, p.y+10, neuY2+10) 
  							: 10000;
  			abstandRechts 	= (rechts) 	? Mathe.abstand(p.x+10, neuX3+10, p.y+10, neuY2+10) 
  							: 10000;
  			
  			gewinner1 		= (abstandOben < abstandUnten) ? 1 
  							: (abstandOben > abstandUnten) ? 2 
  							: Mathe.zufallszahl(1, 2);
  			gewinner2		= (abstandRechts > abstandLinks) ? 3 
  							: (abstandRechts < abstandLinks) ? 4 
  							: Mathe.zufallszahl(3, 4);
  			
  			if 		(gewinner1 == 1 && gewinner2 == 3) 	gewinner3 	= (abstandOben < abstandLinks) ? 1
  																	: (abstandOben > abstandLinks) ? 3
  																	: Mathe.zufallszahl(1, 3);
  			
  			else if (gewinner1 == 1 && gewinner2 == 4) 	gewinner3 	= (abstandOben < abstandRechts) ? 1
  																	: (abstandOben > abstandRechts) ? 4
  																	: Mathe.zufallszahl(1, 4);
  			
  			else if (gewinner1 == 2 && gewinner2 == 3) 	gewinner3 	= (abstandUnten < abstandLinks) ? 2
  																	: (abstandUnten > abstandLinks) ? 3
  																	: Mathe.zufallszahl(2, 3);
  			
  			else if (gewinner1 == 2 && gewinner2 == 4) 	gewinner3 	= (abstandUnten < abstandRechts) ? 2
  																	: (abstandUnten > abstandRechts) ? 4
  																	: Mathe.zufallszahl(2, 4);
  			
  			else 										gewinner3 	= 0;
  			
  			switch (gewinner3) {
  			
  				case 0: g.x = neuX2; g.y = neuY2; break;
  				case 1: g.x = neuX2; g.y = neuY1; break;
  				case 2: g.x = neuX2; g.y = neuY3; break;
  				case 3: g.x = neuX1; g.y = neuY2; break;
  				case 4: g.x = neuX3; g.y = neuY2; break;
  				
  			}
  			
  			if (Mathe.abstand(p.x + 10, g.x + 10, p.y + 10, g.y + 10) < 20) {
  				
  				int choose = JOptionPane.showConfirmDialog(this, "Du hast in " + (System.currentTimeMillis() - start) + "ms " + gefressen + " Stücke gefressen! Möchtest Du erneut spielen?");
  				
  				if (choose == JOptionPane.YES_OPTION) 	starten();
  				else 									end();
  			
  			}
  			
  		}
  		
  	}
  	
  	private boolean darfDrauf(int x, int y) {
  		
  		int pruefX1, pruefX2, pruefY1, pruefY2;
  		int a1, b1, a2, b2;
  		Kachel kachel1, kachel2, kachel3, kachel4;
  		
  		pruefX1 = x;
  		pruefX2 = x + 20 - SCHRITTWEITE;
  		pruefY1 = y;
  		pruefY2 = y + 20 - SCHRITTWEITE;
  		
  		a1 = pruefX1 / 20;
  		a2 = pruefX2 / 20;
  		b1 = pruefY1 / 20;
  		b2 = pruefY2 / 20;
  		
  		kachel1 = kachel[b1][a1];
  		kachel2 = kachel[b2][a1];
  		kachel3 = kachel[b1][a2];
  		kachel4 = kachel[b2][a2];
  		
  		return kachel1.darfDrauf && kachel2.darfDrauf && kachel3.darfDrauf && kachel4.darfDrauf;
  		
  	}
  	
  	public void keyPressed(KeyEvent e) {
  		
  		int keyCode;
  		
  		keyCode = e.getKeyCode();
  		
  		switch(keyCode) {
  		
  			case KeyEvent.VK_UP: 		p.up = SCHRITTWEITE; p.down = 0; p.right = 0; p.left = 0; 	break;
  			case KeyEvent.VK_DOWN: 		p.up = 0; p.down = SCHRITTWEITE; p.right = 0; p.left = 0; 	break;
  			case KeyEvent.VK_RIGHT: 	p.up = 0; p.down = 0; p.right = SCHRITTWEITE; p.left = 0; 	break;
  			case KeyEvent.VK_LEFT:  	p.up = 0; p.down = 0; p.right = 0; p.left = SCHRITTWEITE; 	break;
  			case KeyEvent.VK_ESCAPE: 	escape(); 													break;
  			case KeyEvent.VK_P: 		pause = !pause; 											break;
  			
  		}
  		
  	}
  	
  	private void escape() {
  		
  		int choose;
  		
		pause 	= true;
		choose 	= JOptionPane.showConfirmDialog(this, "Möchtest Du das Spiel wirklich beenden?");
		
		if (choose == JOptionPane.YES_OPTION) end();
		
		pause = false;
  		
  	}
  	
	public void paint(Graphics g) {
		
		long zeit;
		
        g.fillRect(0,0,getWidth(),getHeight());
        for (Kachel[] kacheln : kachel) 
        	for (Kachel k : kacheln) 
        		k.zeichnen(g);
        for (Futter f : futter) 
        	f.zeichnen(g);
        for (int i = 0; i < geist.length; i++) 
        	geist[i].zeichnen(g);
        p.zeichnen(g);
        zeit = System.currentTimeMillis() - start;
        g.setColor(Color.white);
        g.drawString(zeit + "ms", 640, 40);
        g.drawString(String.valueOf(gefressen), 640, 60);
        icon.zeichnen(g);
        
        if (pause) {
        	
        	Font myFont = new Font("Arial", Font.ITALIC|Font.PLAIN, 35);
        	g.setFont(myFont);
        	g.drawString("PAUSE",400,400);
        	
        }
        
  	}
	
	public void run() {
		
		long start, stop;
		boolean geister;
		
		running = true;
		geister = true;
		
		while (running) {
			
			start = System.currentTimeMillis();
			
			if (!pause) {
				
				geister = !geister;
				bewegePacman();
				fressen();
				
				if (geister) bewegeGeister();
				
			}
			
	  		repaint();
			stop = System.currentTimeMillis();
			
			try {
				
				long sleeptime = TIMESLICE - stop + start;
				if (sleeptime > 0)
					Thread.sleep(sleeptime);
				
			}
			
			catch (InterruptedException e) {
				
				System.out.println("Fehler beim 'SLEEP': " + e.getMessage());
				
			}
			
		}
		
	}
	
	private void end() {
		
		running = false;
		System.exit(0);
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
}
