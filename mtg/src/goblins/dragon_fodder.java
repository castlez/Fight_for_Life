package goblins;

import java.util.ArrayList;

public class dragon_fodder extends Spell {
	
	public dragon_fodder(){
		super(2, "sorcery", false);
	}
	
	public String toString(){
		return "Dragon Fodder";
	}
	
	public void  play(ArrayList<Card> dest){
		goblin_token t1, t2;
		t1 = new goblin_token();
		t2 = new goblin_token();
		
		dest.add(t1);
		dest.add(t2);
		
	}
}
