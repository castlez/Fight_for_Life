package ai;
import java.util.*;
import goblins.*;

public class Player {
	
	Deck deck;
	Field field;
	ArrayList<Card> hand;
	int life;
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
					field.mana.add(m);
					hand.remove(i);
				}
			}
		}
		
		//play a creature if you haven't and can
		for(int i = 0 ; i < hand.size() ; i++){
			if(hand.get(i).supertype=="creature"){  //if you have a creature
				c = (Creature) hand.get(i); //make that card look like a Creature
				if(c.cmc <= field.availableMana()){  //if you have enough mana
					c.play(field.creatures);
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
	}
}
