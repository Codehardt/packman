import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MyFileReader {
	
	public static File[] getFiles(String folder) {
		
		File f = new File(folder);
		return f.listFiles();
		
	}

	public static String readFile(String file) {
		
		String inhalt = "";
		String zeile;
		BufferedReader br;
		FileReader fr;
  		try {
  			
			fr = new FileReader(file);
	        br = new BufferedReader(fr);
	    	while ((zeile = br.readLine()) != null)
	    		inhalt += zeile;
	    	br.close();
	        
  		} 
  		catch(IOException e) {
  			
  			System.out.println("Fehler beim FileReader: " + e.getMessage());
  			
  		}
        return inhalt;
		
	}
	
	public static int zeilen(String file) {
		
		int zeilen;
		BufferedReader br;
		FileReader fr;
  		try {
  			
  			zeilen 	= 0;
			fr 		= new FileReader(file);
	        br 		= new BufferedReader(fr);
	    	while (br.readLine() != null)
	    		zeilen++;
	    	br.close();
	        
  		} 
  		catch(IOException e) {
  			
  			zeilen = 0;
  			System.out.println("Fehler beim FileReader: " + e.getMessage());
  			
  		}
        return zeilen;
		
	}
	
	public static int spalten(String file) {
		
		String line;
		BufferedReader br;
		FileReader fr;
  		try {
  			
			fr 		= new FileReader(file);
	        br 		= new BufferedReader(fr);
	    	line 	= br.readLine();
	    	br.close();
	        
  		} 
  		catch(IOException e) {
  			
  			line = "";
  			System.out.println("Fehler beim FileReader: " + e.getMessage());
  			
  		}
        return line.length();
		
	}
	
}
