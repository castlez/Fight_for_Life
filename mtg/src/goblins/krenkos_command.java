package goblins;

import java.util.ArrayList;

public class krenkos_command extends Spell {

	public krenkos_command(){
		super(2, "sorcery", false);
	}
	
	public String toString(){
		return "Krenko's Command";
	}
	
	public void  play(ArrayList<Card> dest){
		goblin_token t1, t2;
		t1 = new goblin_token();
		t2 = new goblin_token();
		
		dest.add(t1);
		dest.add(t2);
		
	}
}
