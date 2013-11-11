package goblins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			super.power = 12;
			super.toughness = 12;
		}
		else{
			super.power = 6;
			super.toughness = 6;
		}
		if(sick){
			super.sick = false;
		}
	}

	@Override
	public int attack(){
		tapped = true;
		return power;
	}

	@Override
	public void play(ArrayList<Card> dest) {
		dest.add(this);
		System.out.println("Did you take damage this turn?(y or n) >");
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String ans;
		try {
			ans = read.readLine();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
			ans = "a";
			e.printStackTrace();
		}
		
		while(true){
			if(ans.toLowerCase().equals("y")){
				this.thirsty = true;
				break;
			}
			else if(ans.toLowerCase().equals("n")){
				this.thirsty = false;
				break;
			}
			else{
				System.out.println("Please answer 'y' or 'n', did you take damage this turn?");
				try {
					ans = read.readLine();
				} catch (IOException e) {
					System.out.println("ERROR: " + e.getMessage());
					e.printStackTrace();
				}
				continue;
			}
		}
	}

	@Override
	public String toString() {
		return "Furyborn Hellkite";
	}

}
