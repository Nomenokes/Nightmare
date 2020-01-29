package object.types;

import object.ObjectHandler;

public class HumanIdle extends Human{
	private int animateDelay,incomeDelay;
	public HumanIdle(double x, double y, ObjectHandler handler, int handlerIndex) {
		super(x, y, handler, handlerIndex);
		id=8;
		health=5;
		name="Idle population";
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void walk() {
		// TODO Auto-generated method stub
		animateDelay--;
		if(animateDelay<0){
			state++;
			if(state>3)state=1;
			if(flipped)x++;else x--;
			animateDelay=7;
		}
	}

	@Override
	protected void idle() {
		state=0;
		
	}
	
	@Override
	public void tick(){
		super.tick();
		incomeDelay--;
		if(incomeDelay<0){
			handler.run.menu.game.human.res++;
			incomeDelay=400;
		}
	}
	
	@Override
	public void die(){
		super.die();
		handler.run.menu.game.human.removeIdle(this);
		handler.run.menu.game.human.pop--;
	}

}
