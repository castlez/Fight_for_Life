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
			if(sc.hasNext()){
				ans = sc.nextLine();
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
		while(op.life>=20){
			//players turn
			do{
				System.out.print("Your turn!(type 'h' for list of commands) > ");
				if(sc.hasNext()){
					ans = sc.nextLine();
				}
				switch(ans){
				
				//deal damage to comp
				case "d":
					System.out.print("Uh oh... How much?(enter amount in number form) > ");
					while(true){
						if(sc.hasNext()){
							ans = sc.nextLine();
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
				
				//attack comp
				case "a":
					System.out.print("Bring it! How many attackers? (enter a number) >");
					if(sc.hasNext()){
						ans = sc.nextLine();
					}
					Boolean blocked = false;
					if(isNum(ans)){
						amnt = Integer.parseInt(ans);
						blocked = op.block(amnt);
					}
					else{
						System.out.println("You must enter a number of attackers!");
					}
					
					if(!blocked){
						System.out.print("How much damage did you do? (enter a number) > ");
						if(sc.hasNext()){
							ans = sc.nextLine();
						}
						if(isNum(ans)){
							amnt = Integer.parseInt(ans);
							op.life -= amnt;
							System.out.printf("You hit me for %d, bring my life total to %d!", amnt, op.life);
						}
					}
					break;
					
				case "k":
					System.out.print("Destroy what? (type the name) > ");
					if(sc.hasNext()){
						ans = sc.nextLine();
					}
					op.kill(ans);
					break;
					
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
							+ "k = destroy one of my permanents"
							+ "s = see my hand,\n "
							+ "f = see my field,\n"
							+ "q = quit game");
					break;
					
			    //quit game 
				case "q":
					break;
					
				case "e":
					break;
					
				default:
					Scanner s = new Scanner(System.in);
					String answer;
					while(true){
						System.out.println("TRAPPED");
						if(s.hasNext()){
							answer = s.nextLine();
						}
					}
					
					
				
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
