package main;
import ai.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CLI {

	public static Boolean isNum(String str){
		try{
			@SuppressWarnings("unused")
			Integer i = Integer.parseInt(str);
		}
		catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	/*LIST OF CHEATS
	 * deck = shows the whole deck
	 * play = forces the ai to reach into its deck and play named card*/
	
	
	public static void main(String[] args){
		Player op = new Player(); //opponent ai
		Scanner sc = new Scanner(System.in);
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String ans = "h";
		int amnt;
		
		System.out.println("Welcome to Fight_for_Life!");
		
		do{
			System.out.print("Whose going first?(c = computer, m = me) > ");
			try {
				ans = read.readLine();
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());
				e.printStackTrace();
			}
			System.out.printf("Your choice was '%s'\n", ans);
			
			if(ans.equals("c")){
				op.takeTurn();
				break;
			}
			else if (ans.equals("m")){
				System.out.println("OK!");
				break;
			}
		}while(!ans.equals("c") || !ans.equals("m"));
		
		
		//game loop begin
		while(op.life>0){
			//players turn
			do{
				
				System.out.print("Your turn!(type 'h' for list of commands) > ");
				read = new BufferedReader(new InputStreamReader(System.in));
				
				//read from user
				try {
					ans = read.readLine();
				} catch (IOException e) {
					System.out.println("ERROR: " + e.getMessage());
					e.printStackTrace();
				}
				switch(ans){
				
				//deal damage to comp
				case "d":
					System.out.print("Uh oh... How much?(enter amount in number form) > ");
					while(true){
						try {
							ans = read.readLine();
						} catch (IOException e) {
							System.out.println("ERROR: " + e.getMessage());
							e.printStackTrace();
						}
						if(isNum(ans)){
							amnt = Integer.parseInt(ans);
							break;
						}
						else{
							System.out.print("Please enter a number! > ");
							continue;
						}
					}
					op.life-=amnt;
					System.out.printf("OW! I'm down to %d life!\n", op.life);
					break;
				
				//attack computer
				case "a":
					System.out.print("Bring it! How many attackers? (enter a number) >");
					try {
						ans = read.readLine();
					} catch (IOException e) {
						System.out.println("ERROR: " + e.getMessage());
						e.printStackTrace();
					}
					Boolean blocked = false;
					if(isNum(ans)){
						amnt = Integer.parseInt(ans);
						blocked = op.block(amnt);
						if(!blocked){
							System.out.print("How much damage did you do? (enter a number) > ");
							try {
								ans = read.readLine();
							} catch (IOException e) {
								System.out.println("ERROR: " + e.getMessage());
								e.printStackTrace();
							}
							
							if(isNum(ans)){
								amnt = Integer.parseInt(ans);
								op.life -= amnt;
								System.out.printf("\nYou hit me for %d, bring my life total to %d!\n", amnt, op.life);
								break;
							}
						}
						else{
							System.out.println("\nHaha! I blocked all your creatures!");
							break;
						}
						
					}
					
					else{
						System.out.println("\nYou must enter a number of attackers!");
						break;
					}
					
					
				//destroy target permanent	
				case "k":
					System.out.print("Destroy what? (type the name) > ");
					try {
						ans = read.readLine();
					} catch (IOException e) {
						System.out.println("ERROR: " + e.getMessage());
						e.printStackTrace();
					}
					op.kill(ans);
					break;
					
				//look at comp's hand
				case "s":
					System.out.println("CHEATER!!");
					op.showHand();
					break;
				
				//CHEAT deck
				case "deck":
					System.out.println("CHEATER\n" + op.showDeck());
					break;
				//check out comp's field
				case "f":
					op.showField();
					break;
				
				//help
				case "h":
					System.out.println(" e = end turn,\n "
							+ "d = deal damage to me,\n "
							+ "a = attack me,\n "
							+ "k = destroy one of my permanents,\n"
							+ " s = see my hand,\n "
							+ "f = see my field,\n"
							+ " l = check life total,\n"
							+ " q = quit game");
					break;
					
			    //quit game 
				case "q":
					break;
				
				//ends the turn	
				case "e":
					break;
					
				//CHEAT play
				case "play":
					System.out.print("CHEATER\nwhat to play from deck? > ");
					try {
						ans = read.readLine();
					} catch (IOException e) {
						System.out.println("ERROR: " + e.getMessage());
						e.printStackTrace();
					}
					op.playFromDeck(ans);
					break;
					
				//default 	
				default:
					System.out.println("Invalid Command.");
					break;
				}
				
				//breaks loop
				if(ans.equals("q")){
					break;
				}
				
			}while(!ans.equals("e"));
			
			//ends game loop
			if(ans.equals("q")){
				break;
			}
			
			//computers turn
			System.out.println("Ok! My turn!");
			op.takeTurn();
			
		} //game loop end
		
		//salutations
		System.out.println("See you next time, thanks for playing!");
		
		
		sc.close();
		return;
	}//end main
} 
