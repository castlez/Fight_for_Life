package goblins;
import java.util.*;

public class beetleback_chief extends Creature {
	
	int red; //integer values of card
	goblin_token t1,t2;
	
	public beetleback_chief(){
		super(2, 2, 4, "creature", "goblin");
		red = 2;
		t1 = new goblin_token();
		t2 = new goblin_token();
	}
	
	
	public String toString(){
		return "BeetleBack Chief";
	}
	

	public void play(ArrayList<Card> c){
		c.add(this);
		c.add(t1);
		c.add(t2);
	}
	
	public int attack(){
		tapped = true;
		return power;
	}
	
	public void upkeep(){
		tapped = false;
	}
	
	public void end(){
		super.power = 2;
		super.toughness = 2;
		if(super.sick){
			super.sick = false;
		}
	}
}
