package goblins;
import java.util.*;

public abstract class Card {
	public String supertype;
	
	public abstract void play(ArrayList<Card> c);
	
	public Card(String supertype){
		this.supertype = supertype;
	}
	
	public String getSuper(){
		return supertype;
	}
	
}
