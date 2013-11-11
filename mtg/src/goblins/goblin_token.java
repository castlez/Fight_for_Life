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
		power = 1;
		toughness = 1;
		if(sick){
			sick = false;
		}
	}

	public void play(ArrayList<Card> dest){} //you don't 'Play' tokens

	public void attack() {
		tapped = true;

	}
	
	public String toString(){
		return "Goblin Token";
	}
}
