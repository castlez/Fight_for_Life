package main;
import ai.*;

public class Game {
	public static void main(String [] args){
		Player op = new Player();
		
		//display deck, shuffle, then display again
		System.out.println(op.showDeck());
		op.shuffle();
		System.out.println(op.showDeck());
		
		
		
	}
}
