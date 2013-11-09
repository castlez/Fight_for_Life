package ai;
import java.util.*;
import goblins.*;

public class Player {
	
	Deck deck;
	Field field;
	ArrayList<Card> hand;
	public int life;
	Boolean playedLand;
	Creature c; //Creature place holder for searching hand, field etc
	
	//constructor
	public Player(){
		deck = new Deck("goblins.txt");
		field = new Field();
		hand = new ArrayList<>();
		life = 20;
		playedLand = false;
		
		deck.shuffle();
		fillHand();
		
	}
	
	//shows contents of deck
	public String showDeck(){
		return deck.toString();
	}
	
	//shuffles deck
	public void shuffle(){
		deck.shuffle();
	}
	
	//fills hand to 7 cards
	void fillHand(){
		while(hand.size()!=7){
			draw();
		}
	}
	
	//draws a single card
	public void draw(){
		hand.add(deck.deck.get(0));
		deck.deck.remove(0);
	}
	
	//reveals hand
	public void showHand(){
		System.out.println(hand.toString());
	}
	
	public void showField(){
		System.out.println("My creatures\n" + field.creatures.toString());
		System.out.println("My lands\n" + field.mana.toString());
		System.out.println("My graveyard (ignore the tombstone)\n" + field.grave.toString());
	}
	
	//takes a turn
	public void takeTurn(){
		
		/*upkeep*/
		field.upkeep(); 
		
		/*draw step*/
		draw();
		
		/*main phase*/
		//play a land if you haven't and have one
		if(!playedLand){
			for(int i = 0 ; i < hand.size() ; i++){
				if(hand.get(i).toString()=="Mountain"){
					mountain m = (mountain) hand.get(i);
					System.out.println("I play a mountain.");
					field.mana.add(m);
					hand.remove(i);
					break;
				}
			}
		}
		
		//play a creature if you haven't and can
		for(int i = 0 ; i < hand.size() ; i++){
			if(hand.get(i).supertype=="creature"){  //if you have a creature
				c = (Creature) hand.get(i); //make that card look like a Creature
				if(c.cmc <= field.availableMana()){  //if you have enough mana
					for(int j = 0 ; j < c.cmc ; j++){ //tap the proper amount of lands
						field.mana.get(j).tap();
					}
					System.out.printf("I tap %d lands to play %s!\n", c.cmc, c.toString());
					c.play(field.creatures); //play the creature
					hand.remove(i);
				}
			}
		}
		
		//TODO: combat
		
		//TODO: main phase 2
		
		//TODO: end step
		for(int i = 0; i <field.creatures.size() ; i++){
			c = (Creature) field.creatures.get(i);
			c.end();
		}
		System.out.println("...and I end my turn.");
	}
}
