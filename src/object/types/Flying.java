package object.types;

import main.Calculator;
import object.ObjectHandler;
import render.Renderer;

public abstract class Flying extends Nightmare{
	public static final int BUFFER=5;
	public static final double MAX_V=.2;
	public static int yPositionMax=50;
	
	public double ax,ay;
	public double tox,toy;
	public double vx,vy;
	
	public static void init(){
		//xPositions=new int[]{(int) (Renderer.backgroundWidth*(1f/4)),(int) (Renderer.backgroundWidth*(2f/4)),(int) (Renderer.backgroundWidth*(3f/4))};
		//yPositionMax=40;
	}
	
	public Flying(double x, double y, ObjectHandler handler, int handlerPos) {
		super(x, y, handler, handlerPos);
		type=3;
		changeTarget();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(){
		super.tick();
		makeParticles();
		move();
	}
	
	protected void makeParticles(){
		for(int h=0;h<height[id];h++){
			for(int w=0;w<width[id];w++){
				int color=sprites[id][state][h*width[id]+w];
				if(color==Renderer.BLACK || color==Renderer.RED){
					if(!flipped) handler.run.particles.add(Calculator.posToAbs((int)(x+w-width[id]/2),(int)( y+h-height[id]/2)),Renderer.BLACK,1,/**/(int)(Math.random()*240)+60/*/180/**/);
					else handler.run.particles.add(Calculator.posToAbs((int)(x-w+width[id]/2),(int)( y+h-height[id]/2)),Renderer.BLACK,1,/**/(int)(Math.random()*240)+60/*/180/**/);
				}
				
			}
		}
	}
	protected void move(){
		ax=(tox-x)/100000;
		ay=(toy-y)/100000;
		
		vx+=ax;vy+=ay;
		if(vx>MAX_V)vx=MAX_V;if(vx<-MAX_V)vx=-MAX_V;
		if(vy>MAX_V)vy=MAX_V;if(vy<-MAX_V)vy=-MAX_V;
		
		x+=vx;y+=vy;
		if(x<tox+BUFFER && x>tox-BUFFER && y<toy+BUFFER && y>toy-BUFFER)changeTarget();
	}
	protected void changeTarget(){
	
		tox=Math.random()*(Renderer.backgroundWidth-40)+20;
		toy=Math.random()*yPositionMax+10;
		
	}
	
}
