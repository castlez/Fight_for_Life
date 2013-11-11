package ai;

import java.util.*;
import goblins.*;

public class Field {
	
	ArrayList<goblins.Card> creatures;
	ArrayList<goblins.Land> mana;
	ArrayList<goblins.Card> grave;

	public Field(){
		creatures = new ArrayList<>();
		mana =  new ArrayList<>();
		grave = new ArrayList<>();
		tombstone tomb = new tombstone();
		grave.add(0, tomb);
	}
	
	public void upkeep(){
		Creature c;
		for(int i = 0;i<creatures.size();i++){
			c = (Creature) creatures.get(i);
			c.upkeep();
		}
		
		for(int i = 0;i<mana.size();i++){
			mana.get(i).upkeep();
		}
	}
	
	public int availableMana(){
		int amnt = 0;
		for(int i = 0 ; i < mana.size() ; i++){
			if(!mana.get(i).tapped){
				amnt++;
			}
		}
		return amnt;
	}
}
