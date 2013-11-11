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
	public int attack(){
		tapped = true;
		return power;
	}

	@Override
	public void play(ArrayList<Card> dest) {
		dest.add(this);
	}

	@Override
	public String toString() {
		return "Dragon Hatchling";
	}
	
	//pumpping with red mana 'amt' times
	public void pump(int amt){
		power += amt;
	}
	public void end(){
		super.power = 0;
		super.toughness = 1;
		if(super.sick){
			super.sick = false;
		}
	}
}
