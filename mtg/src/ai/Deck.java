package ai;
import java.io.*;

public class Deck {
	private static InputStream getFileInputStream(String fileName)
	{
		InputStream	inputStream;
		
		try {
			inputStream = new FileInputStream(new File(fileName));
			}
		catch (FileNotFoundException e) {			// no file with this name exists
			System.err.println(e.getMessage());
			inputStream = null;
			}
		return inputStream;
	}
	InputStream in;
	Deck(String deck){
		in = getFileInputStream(deck);
	}
	
}
