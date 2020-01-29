package object.types;

import object.ObjectHandler;

public class HumanWall extends Human{

	public HumanWall(double x, double y, ObjectHandler handler, int handlerIndex) {
		super(x, y, handler, handlerIndex);
		health=20;
		id=10;
		name="Fortification";
	}

	@Override
	protected void walk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void idle() {
		// TODO Auto-generated method stub
		
	}
	
	/*@Override
	public void tick(){
		super.tick();
		System.out.println(Object.sprites[id][0][0]);
	}*/

}
