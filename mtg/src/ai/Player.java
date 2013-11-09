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
	
	//destroys named permanent
	public void kill(String victem){
		Boolean found = false; //turned to true if the permanent is found
		String dead = "a";
		
		//if user types all, destroy all permanents
		if(victem.toLowerCase().equals("all")){
			System.out.println("CHEATER");
			field.creatures.clear();
			field.mana.clear();
			field.grave.clear();
		}
		
		//else search for permanent named
		else{
			//search creatures
			for(int i = 0 ; i < field.creatures.size() ; i++){
				if(field.creatures.get(i).toString().toLowerCase().equals(victem)){
					field.creatures.get(i).play(field.grave);
					dead = field.creatures.get(i).toString();
					field.creatures.remove(i);
					found = true;
					break;
				}
			}
			//search lands
			for(int i = 0 ; i < field.mana.size() ; i++){
				if(field.mana.get(i).toString().toLowerCase().equals(victem)){
					field.mana.get(i).play(field.grave);
					dead = field.mana.get(i).toString();
					field.mana.remove(i);
					found = true;
					break;
				}
			}
			if(found){
				System.out.printf("You destroyed my %s\n", dead);
			}
			else{
				System.out.println("I don't have that, jokes on you!");
			}
			
		}
	}

	public Boolean block(int attackers){
		int gotthrough = attackers; //starts out equal to the m=number of attackers
		Scanner scan = new Scanner(System.in); //scanner
		String ans = "s"; //answer taken from scanner
		ArrayList<goblins.Card> myCreatures = field.creatures; //alias of array of creatures
		
		//if i have creatures to block
		if(!field.creatures.isEmpty()){
			//iterate through my creatures and assign blockers
			Creature blocker;
			for(int i = 0 ; i < myCreatures.size() ; i++){
				blocker = (Creature) myCreatures.get(i); //cast index i into a creature
				//see if the creature survives
				System.out.printf("My %s blocks creature #%d, he is a %d/%d, does he survive? (y/n) > ", 
						blocker.toString(), i+1, blocker.power, blocker.toughness );
				gotthrough--; //either way the amount of creatures getting through is reduced TODO: factor for trample
				ans = scan.nextLine();// check the result of survival
				//echo results
				if(ans.toLowerCase().equals("y")){
					System.out.println("\nW00t!");
				}
				//if the creature didn't survive, send it to graveyard
				else if(ans.toLowerCase().equals("n")){
					System.out.printf("\nOh no! you destroyed my %s!\n", blocker.toString());
					myCreatures.remove(blocker);
					blocker.play(field.grave);
				}
			}
			
			scan.close();
			System.out.printf("I blocked a total of %d of your creatures (prioritizing ones with the most power\n).", attackers-gotthrough);
			//return if and only if no attackers got through
			if(gotthrough == 0){
				return true;
			}
			else{
				return false;
			}
			
		}
		else{
			System.out.println("I have no creatures so I can't block!");
			scan.close();
			return false;
		}
		
	}
	
	//shows whole field
	public void showField(){
		System.out.println("My creatures\n" + field.creatures.toString());
		System.out.println("My lands\n" + field.mana.toString());
		System.out.println("My graveyard\n" + field.grave.toString());
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
					System.out.println("I play a " + m.toString() + ".");
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
					System.out.printf("I tap %d land(s) to play %s!\n", c.cmc, c.toString());
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
