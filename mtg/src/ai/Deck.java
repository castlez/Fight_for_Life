package ai;
import java.io.*;
import java.util.*;

public class Deck {
	//grabs the file given and provides an input stream
	private static InputStream getFileInputStream(String fileName)
	{
		InputStream	is;
		
		try {
			is = new FileInputStream(new File(fileName));
		}
		catch (FileNotFoundException e) {			
			is = null;
			System.err.println(e.getMessage());
		}
		return is;
	}
	
	//vars
	InputStream in;  //input stream
	
	ArrayList<Object> deck;
	Scanner scan;  //TODO: not working 
	
	//constructor
	public Deck(String deck){
		in = getFileInputStream(deck);
		scan = new Scanner(in);
		String next;
		while(scan.hasNext()){
			next = scan.next();
			System.out.println(next);
			if(next.startsWith("/")){
				System.out.println("found a comment");
				continue;
			}
		}
	}	
}
