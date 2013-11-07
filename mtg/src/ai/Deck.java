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
	public Deck(String deckType){
		in = getFileInputStream(deckType);
		scan = new Scanner(in);
		
		deck = new ArrayList<Object>();
		
		String next;
		Integer count;
		
		while(scan.hasNext()){
			next = scan.next();
			System.out.println(next);
			count = 1;
			if(next.startsWith("/")){
				continue;
			}
			else if (next.matches("\\d+")){
				count = Integer.parseInt(next);
			}	
			else{
				for(int i = 0; i<count;i++){
					addCard(next);
				}
			}
		}
	}	
	
	public void addCard(String card){
		@SuppressWarnings("rawtypes")
		Class toAdd;
		try {
			toAdd = Class.forName("goblins." + card); //make generic when it works
			deck.add(toAdd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String toString(){
		return deck.toString();
		
	}
}
