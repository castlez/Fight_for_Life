package goblins;

import java.util.ArrayList;

//this is a place holder Card that marks the graveyard
public class tombstone extends Card {

	@Override
	public void play(ArrayList<Card> c) {} //you dont make a tombstone
	
	public tombstone(){
		super("tombstone");
	}
	
	public String toString(){
		return "";
	}

}
