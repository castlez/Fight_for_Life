package ai;
import java.io.*;
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
		try{
		deck = new Deck("goblins.txt");
		}
		catch(NullPointerException e){
			System.out.println("ERROR: couldn't find specified deck!");
		}
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String answer = "s"; //answer taken from scanner
		ArrayList<goblins.Card> myCreatures = field.creatures; //alias of array of creatures
		
		//if i have creatures to block
		if(!field.creatures.isEmpty()){
			//iterate through my creatures and assign blockers
			Creature blocker;
			for(int i = 0 ; i < myCreatures.size() ; i++){
				System.out.println(myCreatures.toString());
				blocker = (Creature) myCreatures.get(i); //cast index i into a creature
				//see if the creature survives
				System.out.printf("My %s blocks creature #%d (Prioritizing highest power),\n", blocker.toString(), i+1 );
				System.out.printf("he is a %d/%d, does he survive? (y/n) > ",  blocker.power, blocker.toughness);
				
				gotthrough--; //either way the amount of creatures getting through is reduced TODO: factor for trample
				try {
					answer = br.readLine();
				} catch (IOException e) {
					System.out.println("ERROR: " + e.getMessage());
					e.printStackTrace();
				}
				
				//echo results
				if(answer.toLowerCase().equals("y")){
					System.out.println("\nW00t!");
				}
				//if the creature didn't survive, send it to graveyard
				else if(answer.toLowerCase().equals("n")){
					System.out.printf("\nOh no! you destroyed my %s!\n", blocker.toString());
					myCreatures.remove(blocker);
					blocker.play(field.grave);
				}
			}
			
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
			
			return false;
		}
		
	}
	
	public int attack(){
		ArrayList<Creature> attackers = new ArrayList<>();; //array of attackers
		Creature c;
		int totalDamage=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String answer = "s"; //answer taken from scanner
		
		//check available attackers TODO: add strategy to what attacks
		for(int i = 0; i<field.creatures.size();i++){
			c = (Creature) field.creatures.get(i);
			if(!c.tapped && !c.sick && !c.toString().equals("Krenko, Mob Boss")){
				attackers.add(c);
			}
		}
		System.out.printf("I will be attacking with:\n");
		for(int i = 0 ; i < attackers.size() ; i++){
			c = attackers.get(i);
			if(i != 0){
				System.out.printf(", %s (%d/%d)", c.toString(), c.power, c.toughness);
			}
			else{
				System.out.printf("%s (%d/%d)", c.toString(), c.power, c.toughness);
			}
		}
		//TODO: add option of reacting to declared attackers
		
		for(int i = 0 ; i < attackers.size() ; i++){
			c = attackers.get(i);
			
			//TODO: hard-coding! MUST FIX
			if(c.toString().equals("Dragon Hatchling")){
				int amnt = field.availableMana();
				dragon_hatchling d = (dragon_hatchling) c;
				d.pump(amnt);
			}
			
			System.out.printf("%s is attacking as a %d/%d, do you block? (y or n) > ", c.toString(), c.power, c.toughness );
			try {
				answer = br.readLine();
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());
				e.printStackTrace();
			}
			if(answer.equals("y")){
				System.out.printf("\nDoes my %s die? (y or n) > ", c.toString());
				try {
					answer = br.readLine();
				} catch (IOException e) {
					System.out.println("ERROR: " + e.getMessage());
					e.printStackTrace();
				}
				if(answer.equals("y")){
					field.creatures.remove(c);
					attackers.remove(c);
					c.play(field.grave);
				}
			}
			else{
				totalDamage+=c.power;
			}
			
		}
		return totalDamage;
	}
	
	//shows whole field
	public void showField(){
		System.out.println("My creatures\n" + field.creatures.toString());
		System.out.println("My lands\n" + field.mana.toString());
		System.out.println("My graveyard\n" + field.grave.toString());
	}
	
	//takes a turn
	public void takeTurn(){
		
		ArrayList<Card> myCreatures = field.creatures;
		
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
					Land l;
					for(int j = 0 ; j < c.cmc ; j++){ //tap the proper amount of lands
						l = (Land) field.mana.get(j);
						l.tap();
					}
					System.out.printf("I tap %d land(s) to play %s!\n", c.cmc, c.toString());
					c.play(myCreatures); //play the creature
					hand.remove(i);
				}
			}
		}
		
		//combat
		Boolean available = false; //true if at least one creature is available to attack
		for(int i = 0 ; i < myCreatures.size() ; i++){
			c = (Creature) myCreatures.get(i);
			if(!c.tapped && !c.sick && !c.toString().equals("Krenko, Mob Boss")){
				available = true;
				break;
			}
		}
		if(available){
			int damage = attack();
			System.out.printf("After the dust settles I deal %d damage to you!\n", damage);
		}
		
		//TODO: main phase 2
		
		//TODO: end step
		ArrayList<Creature> buff = new ArrayList<>();
		for(int i = 0; i <myCreatures.size() ; i++){
			c = (Creature) myCreatures.get(i);
			
			if(c.toString().equals("Krenko, Mob Boss")){
				krenko_mob_boss k = (krenko_mob_boss) myCreatures.get(i);
				k.effect(myCreatures);
			}
			buff.add(c);
		}
		for(int i = 0 ; i < buff.size() ; i++){
			buff.get(i).end();
			
		}
		field.creatures.clear();
		field.creatures.addAll(buff);
		
		System.out.println("...and I end my turn.");
	}
	
	//CHEAT play target card straight from deck
	public void playFromDeck(String toPlay){
		Object toAdd;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String answer = "s"; //answer taken from scanner
		
		System.out.printf("Searching for card |%s|", toPlay);
		//tries to add a card of type 'toPlay'
		try {
			toAdd = Class.forName("goblins." + toPlay).newInstance();
			Card c = (Card) toAdd;
			int i = deck.deck.toString().toLowerCase().indexOf(c.toString().toLowerCase());
			if(i>=0){
				System.out.printf("found card: %s\n", c.toString());
				
				//ask where to play the card
				System.out.print("Does this spell need to know about lands (l), creatures(c) or graveyard(g)? > ");
				try {
					answer = br.readLine();
				} catch (IOException e) {
					System.out.println("ERROR: " + e.getMessage());
					e.printStackTrace();
				}
				switch(answer){
					case "l":
						Land l = (Land) c;
						l.play(field.mana);
						break;
					case "c":
						Creature t = (Creature) c;
						t.play(field.creatures);
						break;
					case "g":
						c.play(field.grave);
						break;
					default:
						System.out.println("No such location.");
			
				}
			}
			else{
				System.out.println("Card not found.");
			}
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
			System.out.println("Card not found.");
		}
		
	}
	
	public void addToHand(String toAdd){
		Object buff;
		
		System.out.printf("Searching for card |%s|", toAdd);
		//tries to add a card of type 'toPlay'
		try {
			buff = Class.forName("goblins." + toAdd).newInstance();
			Card c = (Card) buff;
			int i = deck.deck.toString().toLowerCase().indexOf(c.toString().toLowerCase());
			if(i>=0){
				System.out.printf("Added to hand: %s\n", c.toString());
				hand.add(c);
		
			}	
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
			System.out.println("Card not found.");
		}
	}
}
