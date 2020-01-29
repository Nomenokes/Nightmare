package object.types;
import object.Object;
import object.ObjectHandler;
public class HumanCursor extends Object{
	public static int ARROW_HEIGHT;
	public HumanCursor(double x, ObjectHandler handler, int handlerIndex) {
		super(x, ARROW_HEIGHT, handler, handlerIndex, -1);
		this.id=2;
		// TODO Auto-generated constructor stub
	}
	
}
