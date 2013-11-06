package ai;
import java.io.*;

public class Deck {
	InputStream in;
	Deck(String deck){
		in = getFileInputStream(deck);
	}
	
}
