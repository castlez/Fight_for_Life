package goblins;

public class goblin_token {
	int power,toughness; 
	String supertype,type;
	Boolean tapped; 
	
	public goblin_token(){
		power=1;
		toughness=1;
		supertype = "creature";
		type = "goblin";
		tapped = false;
	}
	

	public void upkeep() {
		tapped = false;

	}


	public void attack() {
		tapped = true;

	}
	
	public String toString(){
		return "Goblin Token";
	}
}
