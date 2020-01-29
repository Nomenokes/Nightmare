package object.types;

import object.ObjectHandler;
import render.Renderer;
import ui.HumanUI;

public class Mage extends HumanAttacking{
	private int attackDelay;
	public Mage(double x, double y, ObjectHandler handler, int handlerIndex) {
		super(x, y, handler, handlerIndex);

		id=7;
		name="Mage";
		health=10;
		targetTypes=new int[]{0,3};
	}

	@Override
	protected void walk() {
		// TODO Auto-generated method stub
		state=2;
		if(flipped)x+=.1;
		else x-=.1;
	}

	@Override
	protected void idle() {
		// TODO Auto-generated method stub
		state=0;
	}
	
	@Override
	public void logic(){
		super.logic();
		if((!HumanUI.rallying[id] && !followingX)||!currentlyCustomMoving){
			if(target!=null){
				attackDelay--;
				if(attackDelay<0){
					attackDelay=(int) (80+Math.random()*20);
					state=1;
					if(flipped)handler.beam(x+3, y, target.x, target.y, 0.1, target.type, Renderer.YELLOW, 1, 10);
					else handler.beam(x-3, y, target.x, target.y, 0.1, target.type, Renderer.YELLOW, 1, 10);
				}else if(attackDelay<40)state=0;
			}
		}
	}
	
	@Override
	public void die(){
		super.die();
		handler.run.menu.game.human.pop--;
	}

}
