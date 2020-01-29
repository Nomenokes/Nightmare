package render.effect;

import main.Calculator;
import object.ObjectHandler;

public class Projectile extends ParticleEffect{
	protected float vX,vY,aX,aY;
	protected int damage,type;
	protected ObjectHandler handler;
	public Projectile(ObjectHandler handler,int life,float x, float y, float vX, float vY, float aX, float aY, int type, int color, int damage) {
		super(life,color, x,y);
		this.handler=handler;
		this.vX=vX;this.vY=vY;this.aX=aX;this.aY=aY;
		this.type=type;this.damage=damage;
	}
	@Override
	public void tick(){
		super.tick();
		
		vX+=aX;
		vY+=aY;
		x+=vX;
		y+=vY;
		
		int spot=Calculator.posToAbs((int)x, (int)y);
		
		if(handler.testDamage(spot, type, damage))lifetime=0;
		
		handler.run.particles.add(spot, color, 1, (int) (Math.random()*30+10));
		
	}

}
