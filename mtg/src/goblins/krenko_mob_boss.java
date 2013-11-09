package goblins;

import java.util.ArrayList;

public class krenko_mob_boss extends Creature {

	int power,toughness,cmc,red; //red = amount of red mana needed to play
	String supertype,type;
	Boolean tapped; 
	
	public krenko_mob_boss() {
		super(3, 3, 4, "creature", "goblin");
		red=2;
	}
	
	@Override
	public void upkeep() {
		tapped = false;

	}

	@Override
	public void attack() {
		tapped = true;

	}

	@Override
	public void play(ArrayList<Card> dest) {
		dest.add(this);

	}

	@Override
	public String toString() {
		return "Krenko, Mob Boss";
	}
	
	public void end(){
		power = 3;
		toughness = 3;
	}
	
	public void effect(ArrayList<Creature> dest){
		int numGob = 0; //number of goblins on the battlefield (including krenko)
		for(int i = 0 ; i < dest.size() ; i++){
			if(dest.get(i).type == "goblin"){
				numGob++;
			}
		}
		
		tapped = true;
		goblin_token token;
		for(int i = 0 ; i < numGob ; i++){
			token = new goblin_token();
			dest.add(token);
		}
	}

}
