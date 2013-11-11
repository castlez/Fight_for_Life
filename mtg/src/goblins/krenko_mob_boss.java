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
	public int attack(){
		tapped = true;
		return power;
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
		super.power = 3;
		super.toughness = 3;
		if(super.sick){
			super.sick = false;
		}
	}
	
	public void effect(ArrayList<Card> dest){
		if(!sick){
			int numGob = 0; //number of goblins on the battlefield (including krenko)
			Creature c;
			for(int i = 0 ; i < dest.size() ; i++){
				c = (Creature) (dest.get(i));
				if(c.type.equals("goblin")){
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
}
