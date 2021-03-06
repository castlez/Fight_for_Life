package ai;
import java.io.*;
import java.util.*;
import goblins.*;

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
	public ArrayList<Card> deck;  //deck array list
	Scanner scan; 
	
	//constructor
	public Deck(String deckType){
		in = getFileInputStream(deckType);
		scan = new Scanner(in);
		
		deck = new ArrayList<>();
		
		String next;
		Integer count=1;
		
		while(scan.hasNext()){
			next = scan.next();
			
			//ignore comments
			if(next.startsWith("/")){
				count = 0;
				continue;
			}
			//if it is a number save that number to add the next card
			else if (isNum(next.substring(0, 1))){
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
			Card c = (Card) toAdd;
			deck.add(c);
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
		sb.append("\n");
		String s = sb.toString();
		return s;
	}


	public void shuffle(){
		Collections.shuffle(deck);
	}

}

	
