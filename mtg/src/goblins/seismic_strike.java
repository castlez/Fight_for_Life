package goblins;
import java.util.*;

public class seismic_strike extends Spell {
	
	public seismic_strike(){
		super(3, "instant", true);
	}
	
	public String toString(){
		return "Seismic Strike";
	}
	
	public void play(ArrayList<Card> dest){
		int damage = 0;
		if(dest.contains("Mountain")){
			damage = dest.size();
		}
		
		System.out.printf("I cast Seismic Strike for %d damage (equal to the number of mountains I have)\n", damage);
	}
}
