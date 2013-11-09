package goblins;

import java.util.ArrayList;

public abstract class Spell extends Card {
	public int cmc;
	public String supertype;
	public Boolean instant;
	
	public Spell(int cmc, String supertype, Boolean instant){
		super(supertype);
		this.cmc=cmc;
		this.instant = instant;
	}
	
	public abstract String toString();
	public abstract void play(ArrayList<Card> dest);
}
