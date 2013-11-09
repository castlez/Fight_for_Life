package goblins;
import java.util.*;

public class goblin_grenade extends Spell {

		public goblin_grenade(){
			super(1, "sorcery", false);
		}
		
		public String toString(){
			return "Goblin Grenade";
		}
		
		public void play(ArrayList<Card> dest){
			Creature c;
			for(int i = 0 ; i < dest.size() ; i++){
				c = (Creature) dest.get(i);
				if(c.type=="goblin" && c.toString()!="Krenko, Mob Boss"){ //if found and its not krenko, cast spell
					dest.remove(i);
					System.out.printf("I sacrifice %s to cast Goblin Grenade at you for 5 damage!\n", c.toString()); //TODO: remove Sys out
					break;
				}
				else{
					continue;
				}
			}
		}
		
}
