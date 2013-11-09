package goblins;
import java.util.*;

public class mountain extends Land {
	
	public mountain(){
		super("red");
	}
	
	public void tap(){
		tapped = true;
	}
	
	public void play(ArrayList<Card> dest){
		dest.add(this);
	}
	
	public void upkeep(){
		tapped = false;
	}
	
	public String toString(){
		return "Mountain";
	}
}
