package object.types;

import object.ObjectHandler;
import object.Object;
import render.Renderer;

public class Walker extends Nightmare{
	public int fireDelay;
	private static final int DAMAGE=1,LIFETIME=20;
	private static final double INACCURACY=0.15;
	public Walker(double x, double y,ObjectHandler handler, int handlerPos) {
		super(x,y,handler, handlerPos);
		this.id=0;
		name="Walker";
		health=20;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void tick(){
		super.tick();
		if(checkInFront()<0) walk();
		fireDelay--;
		if(fireDelay<0){fire(); fireDelay=(int) (110+Math.random()*10);}
		
	}
	
	public void walk(){
		animationDelay--;
		if(animationDelay<0){
			state++;
			if(!flipped)x++;
			else x--;
			if(state>=sprites[id].length){
				state=0;
			}
			animationDelay=(int) (20+Math.random()*20);
		}
	}
	
	public void fire(){
		Object o=handler.get(handler.findNearest(handlerIndex, 1));
		
		double randX=Math.random();
		double randY=Math.random();
		if(o!=null){
			if(!flipped){
				handler.beam(x+3, y-9, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				handler.beam(x+4, y-10, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				handler.beam(x+5, y-11, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
			}
			else{
				handler.beam(x-3, y-9, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				handler.beam(x-4, y-10, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				handler.beam(x-5, y-11, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
			}
		}
	}
	
	@Override
	public void die(){
		super.die();
		handler.run.menu.game.nightmare.pop-=6;
	}
	
}
