package object.types;
import main.Calculator;
import object.Object;
import object.ObjectHandler;
import render.Renderer;
public class HeroSword extends Object{
	private Hero hero;
	public HeroSword(Hero hero, ObjectHandler handler, int handlerIndex) {
		super(0, 0, handler, handlerIndex, -1);
		id=5;
		this.hero=hero;
		health=1;
	}
	
	@Override
	public void tick(){
		super.tick();
		
		x=hero.x;
		y=hero.y-8;
		
		if(hero.health<=0 || hero.dying)die();
		
		state=hero.state;
		flipped=hero.flipped;
	}
	
	@Override 
	public void die(){
		super.die();
		for(int h=0;h<height[id];h++){
			for(int w=0;w<width[id];w++){
				int color=sprites[id][state][h*width[id]+w];
				if(color!=0 && color!=Renderer.WHITE){
					if(!flipped) handler.run.particles.add(Calculator.posToAbs((int)(x+w-width[id]/2),(int)( y+h-height[id]/2)),Renderer.BLACK,-1,/**/(int)(Math.random()*Nightmare.fallenMaxLifetime)+600/*/180/**/);
					else handler.run.particles.add(Calculator.posToAbs((int)(x-w+width[id]/2),(int)( y+h-height[id]/2)),Renderer.BLACK,-1,/**/(int)(Math.random()*Nightmare.fallenMaxLifetime)+600/*/180/**/);
				}
			}
		}
	}

}
