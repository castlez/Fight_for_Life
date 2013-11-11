package goblins;

import java.util.*;

public abstract class Creature extends Spell {
	public int power,toughness;
	public String type;
	public Boolean tapped, sick; //sick = summoning sickness
	
	public Creature(int power, int toughness, int cmc, String supertype, String type){
		super(cmc, supertype, false);
		this.power = power;
		this.toughness = toughness;
		this.tapped = false;
		this.sick = true;
		this.type = type;
	}
	
	
	public abstract void upkeep();
	public abstract int attack();
	public abstract void play(ArrayList<Card> dest);
	public abstract void end();
	public abstract String toString();
	
}
