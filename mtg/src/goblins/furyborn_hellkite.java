package goblins;

import java.util.*;

public class furyborn_hellkite extends Creature {

	int power,toughness,cmc,red; //red = amount of red mana needed to play
	String supertype,type;
	Boolean tapped, thirsty; //thirsty = if blood thirst was triggered
	
	public furyborn_hellkite() {
		super(6, 6, 7, "creature", "dragon");
		red = 3;
	}

	@Override
	public void upkeep() {
		tapped = false;

	}
	
	public void end(){
		if(thirsty){
			power = 12;
			toughness = 12;
		}
		else{
			power = 6;
			toughness = 6;
		}
	}

	@Override
	public void attack() {
		tapped = true;
	}

	@Override
	public void play(ArrayList<Card> dest) {
		dest.add(this);
		System.out.println("Did you take damage this turn?(y or n) >");
		Scanner in = new Scanner(System.in);
		String ans = in.nextLine();
		
		while(ans.toLowerCase() != "y" || ans.toLowerCase() != "n"){
			if(ans.toLowerCase() == "y"){
				this.thirsty = true;
			}
			else if(ans.toLowerCase() == "n"){
				this.thirsty = false;
			}
			else{
				System.out.println("Please answer 'y' or 'n', did you take damage this turn?");
				ans = in.nextLine();
			}
		}
		in.close();
	}

	@Override
	public String toString() {
		return "Furyborn Hellkite";
	}

}
