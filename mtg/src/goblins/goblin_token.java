package goblins;
import java.util.*;

public class goblin_token extends Creature {

	public goblin_token(){
		super(1, 1, 0, "creature", "goblin");
	}
	

	public void upkeep() {
		tapped = false;

	}
	
	public void end(){
		super.power = 1;
		super.toughness = 1;
		if(super.sick){
			super.sick = false;
		}
	}

	public void play(ArrayList<Card> dest){} //you don't 'Play' tokens

	public int attack(){
		tapped = true;
		return power;
	}
	
	public String toString(){
		return "Goblin Token";
	}
}
