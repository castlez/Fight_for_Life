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
	
	//checks if given string is a number
	public Boolean isNum(String str){
		try{
			@SuppressWarnings("unused")
			Integer i = Integer.parseInt(str);
		}
		catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	
	//vars
	InputStream in;  //input stream
	public ArrayList<Object> deck;  //deck array list
	Scanner scan; 
	
	//constructor
	public Deck(String deckType){
		in = getFileInputStream(deckType);
		scan = new Scanner(in);
		
		deck = new ArrayList<Object>();
		
		String next;
		Integer count=1;
		
		while(scan.hasNext()){
			next = scan.next();
			
			//ignore comments
			if(next.startsWith("/")){
				continue;
			}
			//if it is a number save that number to add the next card
			else if (isNum(next)){
				count = Integer.parseInt(next);
				continue;
			}	
			//add a number of 'next' cards equal to 'count'
			else{
				for(int i = 0; i<count;i++){
					try {
						addCard(next);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}	
	
	
	//adds a single card of class type passed to deck
	public void addCard(String card) throws InstantiationException, IllegalAccessException{
		Object toAdd;
		//tries to add a card of type 'card'
		try {
			toAdd = Class.forName("goblins." + card).newInstance();
			deck.add(toAdd);
		} 
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	//toString override
	public String toString(){
		StringBuilder sb = new StringBuilder(); //string builder to format deck
		String adder;  //string of class to be added
		
		//add new line, then the card's toString
		for(int i = 0 ; i < deck.size() ; i++){
			sb.append("\n");
			adder = (deck.get(i).toString());
			sb.append(adder);
		}
		String s = sb.toString();
		return s;
	}
}
