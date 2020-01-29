package object.types;

import object.ObjectHandler;

public class Wall extends Nightmare{

	public Wall(double x, double y, ObjectHandler handler, int handlerPos) {
		super(x, y, handler, handlerPos);
		this.id=1;
		name="Tower";
		health=50;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick(){
		super.tick();
		walk();
		
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
			animationDelay=(int) (15+Math.random()*5);
		}
	}
	
	@Override
	public void die(){
		super.die();
		handler.run.menu.game.nightmare.pop-=4;
	}

}
