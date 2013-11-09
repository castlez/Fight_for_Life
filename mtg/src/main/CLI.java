package main;
import ai.*;

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
	
	public static void main(String[] args){
		Player op = new Player(); //opponent ai
		Scanner sc = new Scanner(System.in);
		String ans = "a";
		int amnt;
		
		System.out.println("Welcome to Fight_for_Life!");
		
		do{
			System.out.print("Whose going first?(c = computer, m = me) > ");
			ans = sc.nextLine();
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
		while(op.life>=20){
			//players turn
			do{
				System.out.print("Your turn!(type 'h' for list of commands) > ");
				ans = sc.nextLine();
				switch(ans){
				
				//deal damage to comp
				case "d":
					System.out.print("Uh oh... How much?(enter amount in number form) > ");
					while(true){
						ans = sc.nextLine();
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
				
				//attack comp
				case "a":
					
				//look at comp's hand
				case "s":
					System.out.println("CHEATER!!");
					op.showHand();
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
							+ "s = see my hand,\n "
							+ "f = see my field,\n"
							+ "q = quit game");
					break;
					
			    //quit game
				case "q":
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
	}
}
