package object.types;

import object.ObjectHandler;
import ui.HumanUI;

public class Soldier extends HumanAttacking{

	public Soldier(double x, double y, ObjectHandler handler, int handlerIndex) {
		super(x, y, handler, handlerIndex);

		//id=0;
		////name="Soldier";
		//health=10;
		//targetTypes=new int[]{2};
	}

	@Override
	protected void walk() {
		// TODO Auto-generated method stub
		delay--;
		if(delay<=0){
			state++;
			if(state>=1)state=0;
			delay=30;
			if(!flipped)x--;else x++;
		}
	}

	@Override
	protected void idle() {
		// TODO Auto-generated method stub
		state=0;
	}
	
	protected void attack(){
		
	}
	
	@Override
	public void logic(){
		super.logic();
		if(!HumanUI.rallying[id] && !followingX){
			if(ObjectHandler.dX(this, target)>100)walk();
			else  attack();
		}
	}

}
