package goblins;

public class mountain {
	int count;
	int mana;
	
	mountain(){
		mana = 1;
		count = 1;
	}
	
	Boolean tap(int num){
		if(num<=mana){
			mana-=num;
			return true;
		}
		else{
			return false;
		}
	}
	
	void upkeep(){
		mana=count;
	}
}
