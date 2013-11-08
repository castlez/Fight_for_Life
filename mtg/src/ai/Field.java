package ai;

import java.util.*;

public class Field {
	
	ArrayList<goblins.Creature> creatures;
	ArrayList<goblins.mountain> mana;
	ArrayList<Object> grave;

	public Field(){
		creatures = new ArrayList<>();
		mana =  new ArrayList<>();
		grave = new ArrayList<Object>();
	}
	
	public void upkeep(){
		for(int i = 0;i<creatures.size();i++){
		}
		
		//TODO: upkeep creatures and mana
	}
}
