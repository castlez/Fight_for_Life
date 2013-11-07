package main;
import ai.*;

public class Game {
	public static void main(String [] args){
		Deck op = new Deck("goblins.txt");
		System.out.println(op.deck.size() + op.toString());
	}
}
