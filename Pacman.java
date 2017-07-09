import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Pacman extends JFrame {
  
	private final static long serialVersionUID = 1L;
	private Spielfeld spielfeld;

	public Pacman(String title) {
		
		super(title);
		setUndecorated(true);
		setAlwaysOnTop(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(800,580);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    spielfeld = new Spielfeld();
	    spielfeld.setFocusable(true);
	    spielfeld.addKeyListener(spielfeld);
	    add(spielfeld);
	    JOptionPane.showMessageDialog(this, "Steuerung: 'Pfeiltasten', Pause: 'p', Beenden: 'ESC'");
	    setVisible(true);
	    
	}
	
	public static void main(String[] args) {
		
		new Pacman("Pacman");
		
	}
	
}
