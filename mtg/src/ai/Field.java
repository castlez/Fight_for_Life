package ai;

import java.util.*;
import goblins.*;

public class Field {
	
	ArrayList<goblins.Card> creatures;
	ArrayList<goblins.Card> mana;
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
		
		Land l;
		for(int i = 0;i<mana.size();i++){
			l = (Land) mana.get(i);
			l.upkeep();
		}
	}
	
	public int availableMana(){
		int amnt = 0;
		Land l;
		for(int i = 0 ; i < mana.size() ; i++){
			l = (Land) mana.get(i);
			if(!l.tapped){
				amnt++;
			}
		}
		return amnt;
	}
}
