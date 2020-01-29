package object.types;

import main.Calculator;
import object.ObjectHandler;
import render.Renderer;
import ui.HumanUI;

public class Hero extends HumanAttacking{
	
	
	
	public Hero(double x, double y, ObjectHandler handler, int handlerIndex) {
		super(x, y, handler, handlerIndex);
		
		id=4;
		name="Hero";
		
		state=6;
		handler.addSword(this);//TODO fix weird bug
		handler.addSword(this);
		
		health=150;
		targetTypes=new int[]{0,2};
	}
	
	@Override
	public void tick(){
		super.tick();
		
		
	}
	@Override
	protected void walk(){
		delay--;
		if(delay<=0){
			state++;
			if(state>10||state<7)state=7;
			//if(state==7)if(!flipped)x--;else x++;
			//else if(state==8)if(!flipped)x--;else x++;
			//else if(state==9)if(!flipped)x--;else x++;
			//else if(state==10)if(!flipped)x--;else x++;
			if(!flipped)x--;else x++;
			delay=7;
		}
	}
	protected void attack(){
		delay--;
		if(delay<=0){
			state++;
			if(state<4||(state>0&&state<4))state=4;
			else if(state>6)state=0;
			
			if(state==0)delay=80;
			else if(state==4)delay=100;
			else if(state==5){delay=10;/*if(!flipped)x--;else x++;*/}
			else{
				delay=50;
				if(!flipped){
					//x--;  
					int i=9;
					int pos=Calculator.posToAbs((int)x, (int)y);
					
					while(i<27){
						handler.testDamage(pos-i, targetTypes, 3);
						i++;
					}
				}else{
					//x++;
					int i=9;
					int pos=Calculator.posToAbs((int)x, (int)y);
					while(i<27){
						handler.testDamage(pos+i, targetTypes, 3);
						i++;
					}
				}
			}
		}
	}
	@Override
	protected void idle(){
		delay--;
		if(delay<=0){
			state=(int) (Math.random()*4);
			
			delay=(int) (Math.random()*240)+120;
		}
		
	}
	@Override
	public void logic(){
		super.logic();
		if(!HumanUI.rallying[id] && !followingX){
			if(target!=null){
				//if(flipped&&target.x<x)flipped=false;
				//else if(!flipped&&target.x>x)flipped=true;
				if(ObjectHandler.dX(this, target)>23)walk();
				else  attack();
			}else idle();
			
		}else if(!currentlyCustomMoving){
			if(target!=null){
				//if(flipped&&target.x<x)flipped=false;
				//else if(!flipped&&target.x>x)flipped=true;
				if(ObjectHandler.dX(this, target)<=23)attack();
			}
		}
		else{
			
		}
	}
	
	@Override
	public void die(){
		super.die();
		handler.run.menu.game.human.pop--;
	}

}
