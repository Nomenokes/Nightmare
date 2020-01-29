package object.types;

import main.Calculator;
import object.Object;
import object.ObjectHandler;
import render.Renderer;
import ui.HumanUI;

public abstract class Human extends Object{
	public static final int BUFFER=5;
	public boolean followingX;
	public int followX;
	public boolean dying=false;
	public boolean currentlyCustomMoving;
	
	public Human(double x, double y, ObjectHandler handler, int handlerIndex) {
		super(x, y, handler, handlerIndex, 1);
	}
	
	@Override
	public void tick(){
		super.tick();
		
		//if(!followingX)followX=(int) x;
		
		if(followingX){
			if (x<followX-BUFFER || x>followX+BUFFER){
				if(flipped&&followX<x)flipped=false;
				else if(!flipped&&followX>x)flipped=true;
				currentlyCustomMoving=true;
				walk();
			}else{
				currentlyCustomMoving=false;
				idle();
				
			}
		}
		else if(HumanUI.rallying[id]){
			if(x<HumanUI.rally[id]-BUFFER || x>HumanUI.rally[id]+BUFFER){
				if(flipped&&HumanUI.rally[id]<x)flipped=false;
				else if(!flipped&&HumanUI.rally[id]>x)flipped=true;
				currentlyCustomMoving=true;
				walk();
			}else{
				currentlyCustomMoving=false;
				idle();
			}
		}else currentlyCustomMoving=false;
	}
	
	protected abstract void walk();
	protected abstract void idle();
	
	@Override
	public void die(){
		//dying=true;
		//animationDelay--;
		//if(animationDelay<0){
		//	state++;
		//}
		super.die();
		for(int h=0;h<height[id];h++){
			for(int w=0;w<width[id];w++){
				int color=sprites[id][state][h*width[id]+w];
				if(color==Renderer.BROWN){
					if(!flipped) handler.run.particles.add(Calculator.posToAbs((int)(x+w-width[id]/2),(int)( y+h-height[id]/2)),Renderer.BROWN,-1,/**/(int)(Math.random()*Nightmare.fallenMaxLifetime)+600/*/180/**/);
					else handler.run.particles.add(Calculator.posToAbs((int)(x-w+width[id]/2),(int)( y+h-height[id]/2)),Renderer.BROWN,-1,/**/(int)(Math.random()*Nightmare.fallenMaxLifetime)+600/*/180/**/);
				}
			}
		}
	}
}
