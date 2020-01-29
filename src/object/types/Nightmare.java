package object.types;

import main.Calculator;
import main.Run;
import object.Object;
import object.ObjectHandler;
import render.Renderer;

public class Nightmare extends Object{
	public static final int fallenMaxLifetime=1440;
	
	public Nightmare(double x, double y, ObjectHandler handler, int handlerPos) {
		super(x,y,handler, handlerPos,  0);
	}
	@Override
	public void die(){
		super.die();
		for(int h=0;h<height[id];h++){
			for(int w=0;w<width[id];w++){
				int color=sprites[id][state][h*width[id]+w];
				if(color==Renderer.BLACK){
					if(!flipped) handler.run.particles.add(Calculator.posToAbs((int)(x+w-width[id]/2),(int)( y+h-height[id]/2)),color,-1,/**/(int)(Math.random()*fallenMaxLifetime)+600/*/180/**/);
					else handler.run.particles.add(Calculator.posToAbs((int)(x-w+width[id]/2),(int)( y+h-height[id]/2)),color,-1,/**/(int)(Math.random()*fallenMaxLifetime)+600/*/180/**/);
				}
				//else if(color==Renderer.RED)
				//	handler.run.particles.add(Calculator.posToAbs(x+w-width[id]/2, y+h-height[id]/2, backWidth), color, 1,new Random().nextInt(fallenMaxLifetime)+360);
			}
		}
	}
	public static void init(){
		
	}
	public int checkInFront(){
		if(flipped){
			return handler.testPos(Calculator.posToAbs((int)x+width[id]/2, (int)y-height[id]/2), 1);
		}
		else{
			return handler.testPos(Calculator.posToAbs((int)x-width[id]/2, (int)y-height[id]/2), 1);
		}
	}

}
