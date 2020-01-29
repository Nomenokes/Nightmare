package object.types;

import object.Object;
import object.ObjectHandler;
import render.Renderer;

public class Wyrm extends Nightmare{
	public int fireDelay;
	private static final int DAMAGE=2,LIFETIME=10;
	private static final double INACCURACY=0.3;

	public Wyrm(double x, double y, ObjectHandler handler, int handlerPos) {
		super(x, y, handler, handlerPos);
		id=3;
		name="Wyrm";
		health=40;
	}
	
	@Override
	public void tick(){
		super.tick();
		cycleTextures();
		fireDelay--;
		if(fireDelay<0){fire(); fireDelay=(int) (50+Math.random()*10);}
	}
	
	private void cycleTextures(){
		animationDelay--;
		if(animationDelay<0){
			state++;
			if(state>=sprites[id].length){
				state=0;
			}
			animationDelay=(int) (30+Math.random()*20);
		}
	}
	
	public void fire(){
		Object o=handler.get(handler.findNearest(handlerIndex, 1));
		
		double randX=Math.random();
		double randY=Math.random();
		double random=Math.random();
		if(o!=null){
			if(!flipped){
				if(random<0.25)handler.beam(x, y+21, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				else if(random<0.5)handler.beam(x, y+9, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				else if(random<0.75)handler.beam(x+1, y-5, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				else handler.beam(x-1, y-5, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
			}
			else{
				if(random<0.25)handler.beam(x, y+21, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				else if(random<0.5)handler.beam(x, y+9, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				else if(random<0.75)handler.beam(x-1, y-5, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
				else handler.beam(x+1, y-5, o.x, o.y,randX,randY, INACCURACY, 1, Renderer.RED, DAMAGE,LIFETIME);
			}
		}
	}
	
	@Override
	public void die(){
		super.die();
		handler.run.menu.game.nightmare.pop-=10;
		
	}

}
