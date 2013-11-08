package goblins;
import java.util.*;

public class beetleback_chief {
	
	int cmc,red,power,toughness; //integer values of card
	Boolean tapped;
	goblin_token t1,t2;
	String type,supertype;
	
	public beetleback_chief(){
		cmc=4;
		red = 2;
		power=2;
		toughness=2;
		tapped = false;
		type = "goblin";
		supertype = "creature";
		t1 = new goblin_token();
		t2 = new goblin_token();
	}
	
	
	public String toString(){
		return "BeetleBack Chief";
	}
	
	public void play(ArrayList<Object> c){
		c.add(this);
		c.add(t1);
		c.add(t2);
	}
	
	public void attack(){
		tapped = true;
		
	}
}
