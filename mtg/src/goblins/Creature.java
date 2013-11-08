package goblins;

import java.util.*;

public abstract class Creature extends Spell {
	public int power,toughness;
	public String type;
	Boolean tapped;
	
	public Creature(int power, int toughness, int cmc, String supertype, String type){
		super(cmc, supertype, false);
		this.power = power;
		this.toughness = toughness;
		this.tapped = false;
		this.type = type;
	}
	
	
	public abstract void upkeep();
	public abstract void attack();
	public abstract void play(ArrayList<Object> dest);
	public abstract String toString();
	
}
