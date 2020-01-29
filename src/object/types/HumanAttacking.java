package object.types;

import object.Object;
import object.ObjectHandler;
import ui.HumanUI;

public abstract class HumanAttacking extends Human{
	public int retargetDelay=0;
	protected Object target;
	public int delay;
	public int[] targetTypes;
	
	public HumanAttacking(double x, double y, ObjectHandler handler,
			int handlerIndex) {
		super(x, y, handler, handlerIndex);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick(){
		super.tick();
		
		logic();
	}
	
	public void logic(){
		retargetDelay--;
		if(retargetDelay<0||(target==null && retargetDelay<40)){
			target=handler.get(handler.findNearest(handlerIndex, targetTypes));
			retargetDelay=60;
		}
		if(!HumanUI.rallying[id] && !followingX){
			if(target!=null){
				if(flipped&&target.x<x)flipped=false;
				else if(!flipped&&target.x>x)flipped=true;
			}
		}
		else if(!currentlyCustomMoving){
			if(target!=null){
				if(flipped&&target.x<x)flipped=false;
				else if(!flipped&&target.x>x)flipped=true;
			}
		}
	}

}
