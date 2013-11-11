package goblins;

import java.util.ArrayList;

public class goblin_arsonist extends Creature {

	int power,toughness,cmc,red; //red = amount of red mana needed to play
	String supertype,type;
	Boolean tapped; 
	
	public goblin_arsonist() {
		super(1, 1, 1, "creature", "goblin");
		red=1;
	}

	@Override
	public void upkeep() {
		tapped = false;

	}

	@Override
	public int attack(){
		tapped = true;
		return power;
	}

	@Override
	public void play(ArrayList<Card> dest) {
		dest.add(this);
		if(dest.get(0).supertype=="tombstone"){  //tombstone is added to graveyard in field constructor
			System.out.println("Goblin Arsonist has died, dealing 1 damage to you!"); //TODO: replace system.out
		}
	}

	@Override
	public String toString() {
		return "Goblin Arsonist";
	}
	
	public void end(){
		power = 1;
		toughness = 1;
		if(sick){
			sick = false;
		}
	}

}
