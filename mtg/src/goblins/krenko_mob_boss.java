package goblins;

import java.util.ArrayList;

public class krenko_mob_boss extends Creature {

	int power,toughness,cmc,red; //red = amount of red mana needed to play
	String supertype,type;
	Boolean tapped; 
	
	public krenko_mob_boss() {
		super(3, 3, 4, "creature", "goblin");
		red=2;
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
		return "Krenko, Mob Boss";
	}

}
