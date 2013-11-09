package goblins;

import java.util.*;


public class fling extends Spell {
	
	public fling(){
		super(2, "instant", true);
	}
	
	public String toString(){
		return "Fling";
	}
	
	public void play(ArrayList<Card> dest){
		Creature toFling =(Creature) dest.get(0);
		System.out.printf("I fling %s at you for %d damage!\n", toFling.toString(), toFling.power);//TODO: replace system.out
		dest.remove(0);
		
	}
}
