package object.types;

import object.ObjectHandler;
import render.Renderer;
import ui.HumanUI;

public class House extends Human{
	private HumanUI ui;
	private int spawnDelay;
	public House(double x, double y, ObjectHandler handler, int handlerIndex, HumanUI ui) {
		super(x, y, handler, handlerIndex);
		this.ui=ui;
		id=9;
		health=40;
		name="House";
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void walk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void idle() {
		// TODO Auto-generated method stub
		
	}
	
	@Override 
	public void tick(){
		super.tick();
		spawnDelay--;
		if(spawnDelay<0){
			ui.createIdle((int)(Math.random()*Renderer.backgroundWidth));
			spawnDelay=600;
		}
	}

}
