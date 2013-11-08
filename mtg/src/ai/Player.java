package ai;

public class Player {
	
	Deck d;
	Field f;
	Hand h;
	
	public Player(){
		d = new Deck("goblins.txt");
		f = new Field();
		h = new Hand();
	}
	
}
