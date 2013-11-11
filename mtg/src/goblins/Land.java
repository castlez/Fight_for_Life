package goblins;

import java.util.ArrayList;

public abstract class Land extends Card {
	public String color;
	public Boolean tapped;
	
	@Override
	public void play(ArrayList<Card> c) {
		c.add(this);
	}
	
	public Land(String color){
		super("land");
		this.color = color;
		this.tapped = false;
	}
	
	public abstract void upkeep();
	public abstract void tap();

}
