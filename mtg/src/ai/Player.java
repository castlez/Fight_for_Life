package ai;

public class Player {
	
	Deck deck;
	Field field;
	Hand hand;
	int life;
	
	//constructor
	public Player(){
		deck = new Deck("goblins.txt");
		field = new Field();
		hand = new Hand();
		life = 20;
	}
	
	//shows contents of deck
	public String showDeck(){
		return deck.toString();
	}
	
	public void shuffle(){
		deck.shuffle();
	}
	
}
