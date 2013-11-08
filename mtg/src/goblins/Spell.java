package goblins;

public abstract class Spell {
	int cmc;
	String supertype;
	Boolean instant;
	
	public Spell(int cmc, String supertype, Boolean instant){
		this.cmc=cmc;
		this.supertype = supertype;
		this.instant = instant;
	}
	
	public abstract String toString();
}
