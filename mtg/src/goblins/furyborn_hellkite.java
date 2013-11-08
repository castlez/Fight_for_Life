package goblins;

import java.util.ArrayList;

public class furyborn_hellkite extends Creature {

	int power,toughness,cmc,red; //red = amount of red mana needed to play
	String supertype,type;
	Boolean tapped;
	
	public furyborn_hellkite() {
		super(6, 6, 7, "creature", "dragon");
		red = 3;
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
		return "Furyborn Hellkite";
	}

}
