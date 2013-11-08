package ai;
import java.util.*;

public class Player {
	
	Deck deck;
	Field field;
	ArrayList<Object> hand;
	int life;
	
	//constructor
	public Player(){
		deck = new Deck("goblins.txt");
		field = new Field();
		hand = new ArrayList<Object>();
		life = 20;
		
		deck.shuffle();
		fillHand();
		
	}
	
	//shows contents of deck
	public String showDeck(){
		return deck.toString();
	}
	
	public void shuffle(){
		deck.shuffle();
	}
	
	void fillHand(){
		for(int i = 0;hand.size()!=7;i++){
			hand.add(deck.deck.get(i));
			deck.deck.remove(i);
		}
	}
	
	public void showHand(){
		System.out.println(hand.toString());
	}
	
	public void takeTurn(){
		
		//upkeep
		//TODO: finish upkeep 
		
		
		hand.add(deck.deck.get(0));
		deck.deck.remove(0);
	}
}
