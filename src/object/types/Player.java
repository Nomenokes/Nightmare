package object.types;

import object.Object;
import object.ObjectHandler;

public class Player extends Object{

	public Player(double x, double y, ObjectHandler handler, int handlerIndex,
			int type) {
		super(x, y, handler, handlerIndex, type);
		
		name="Player";
		health=1;
		id=11;
		type=1;
	}

}
