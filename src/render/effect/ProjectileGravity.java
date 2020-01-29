package render.effect;

import object.ObjectHandler;
import render.ParticleHandler;
import main.Run;

public class ProjectileGravity extends Projectile{
	public ProjectileGravity(ObjectHandler handler, int life, float x, float y,
			float vX, float vY, float aX, float aY, int type, int color,
			int damage) {
		super(handler, life, x, y, vX, vY, aX, -ParticleHandler.g/Run.ticksPerSecond, type, color, damage);
		// TODO Auto-generated constructor stub
	}
	

}
