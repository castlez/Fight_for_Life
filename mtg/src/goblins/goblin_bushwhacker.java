package goblins;

import java.util.ArrayList;

public class goblin_bushwhacker extends Creature {

	int power,toughness,cmc,red; //red = amount of red mana needed to play
	String supertype,type;
	Boolean tapped; 
	
	public goblin_bushwhacker() {
		super(1, 1, 1, "creature", "goblin");
		red=1;
	}

	@Override
	public void upkeep() {
		tapped = false;

	}

	@Override
	public void attack() {
		tapped = true;

	}

	@Override
	public void play(ArrayList<Object> dest) {
		dest.add(this);

	}

	@Override
	public String toString() {
		return "Goblin Bushwhacker";
	}

}
