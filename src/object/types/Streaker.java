package object.types;

import object.Object;
import object.ObjectHandler;
import render.Renderer;

public class Streaker extends Flying{
	public int fireDelay;
	
	public Streaker(double x, double y, ObjectHandler handler, int handlerPos) {
		super(x, y, handler, handlerPos);
		health=5;
		name="Streaker";
		id=6;
	}
	
	@Override 
	public void tick(){
		super.tick();
		fireDelay--;
		if(fireDelay<0){
			fire();
			fireDelay=(int) (Math.random()*60+120);
		}
	//	x+=.1;
	}
	
	public void fire(){
		Object target=handler.get(handler.findNearest(handlerIndex, 1));
		if(target!=null)handler.run.particles.addProjectile((float)x,(float)y,(float)target.x,(float)target.y, .3f, 0, .01f, 1, Renderer.RED, 1);
	}
	
	@Override
	public void die(){
		super.die();
		handler.run.menu.game.nightmare.pop-=2;
	}
	
	

}
