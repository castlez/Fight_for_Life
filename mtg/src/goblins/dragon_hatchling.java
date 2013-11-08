package goblins;

import java.util.ArrayList;

public class dragon_hatchling extends Creature {
	int power,toughness,cmc,red;
	String supertype,type;
	Boolean tapped;
	
	
	public dragon_hatchling() {
		super(0, 1, 2, "creature", "dragon");
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
		return "Dragon Hatchling";
	}
	
	//pumpping with red mana 'amt' times
	public void pump(int amt){
		power = amt;
	}
	public void end(){
		power = 0;
		toughness = 1;
	}
}
