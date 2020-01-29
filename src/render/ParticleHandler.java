package render;

import java.util.ArrayList;

import main.Calculator;
import main.Run;
import render.effect.ParticleEffect;
import render.effect.Projectile;

public class ParticleHandler {
	public static final float g=0.11f;
	public Run run;
	private int[] particles;
	private int[] types;//-1 is falling,1 is timed,2 is stacked timed
	private float[] velocity;
	private float[] pos;
	private int[] lifetimes;
	
	public ArrayList<ParticleEffect> effects;
	
	public ParticleHandler(Run run){
		this.run=run;
		
		particles=new int[Renderer.backgroundWidth*Renderer.backgroundHeight];
		types=new int[particles.length];
		velocity=new float[particles.length];
		pos=new float[particles.length];
		lifetimes=new int[particles.length];
		effects=new ArrayList<ParticleEffect>();
		
	}
	
	public void tick(){
		for(int i=particles.length-Renderer.backgroundWidth;i<particles.length;i++){
			add(i,Renderer.BLACK,2,Integer.MAX_VALUE);
		}
		for(int i=0;i<effects.size();i++){
			ParticleEffect effect=effects.get(i);
			
			if(effect.lifetime<=0)effects.remove(i);
			else effect.tick();
		}
		
		int init=Renderer.backgroundWidth;
		float gravityAccel=g/Run.ticksPerSecond;
		for(int i=types.length-init-1;i>=0;i--){
			if(types[i]==-1){
				if(types[i+init]==2){
					types[i]=2;
				}else{
					velocity[i]+=gravityAccel;
					pos[i]+=velocity[i];
					if(pos[i]>1){
						pos[i]=0;
						particles[i+init]=particles[i];
						particles[i]=0;
						velocity[i+init]=velocity[i];
						velocity[i]=0;
						types[i+init]=-1;
						types[i]=0;
						lifetimes[i+init]=lifetimes[i];
						lifetimes[i]=0;
					}
				}
			}
			else if(types[i]==1){
				lifetimes[i]--;
				if(lifetimes[i]<=0){
					particles[i]=0;
					types[i]=0;
				}
			}
			else if(types[i]==2){
				lifetimes[i]--;
				
				if(lifetimes[i]<=0){
		/*/		lifetimes[i]=lifetimes[killStacked(i,init)];  /*/
					particles[i]=0;
					types[i]=0; /**/
				}
				if(particles[i+init]==0){
					particles[i+init]=particles[i];
					lifetimes[i+init]=lifetimes[i];
					types[i+init]=2;
					
					particles[i]=0;
					types[i]=0;
				}
			}
		}
	}
	/*private int killStacked(int index,int width){
		if(particles[index-width]==0){
			particles[index]=0;
			types[index]=0;
			return index;
		}
		else
			return killStacked(index-width,width);
	}*/
	public int[] render(){
		int[] returning=particles.clone();
		
		for(ParticleEffect p:effects){
			//for(int i:p.positions){
				//returning[i]=p.color;
			//}
			returning[Calculator.posToAbs((int)p.x, (int)p.y)]=p.color;
		}
		
		return returning;
	}
	
	public void add(int spot,int color,int type,int lifetime){
		particles[spot]=color;
		types[spot]=type;
		lifetimes[spot]=lifetime;
	}
	public void addProjectile(float fromx, float fromy, float tox, float toy, float inaccuracy, float v, float a, int type, int color, int damage){
	//	float fromx=(float) Calculator.absToPosX(to);
	//	float fromy=(float) Calculator.absToPosY(to);
	//	float tox=(float) Calculator.absToPosX(to);
	//	float toy=(float) Calculator.absToPosY(to);
		
		float mag=(float) Math.sqrt((tox-fromx)*(tox-fromx)+(toy-fromy)*(toy-fromy));
		float incrementx=((tox-fromx)/mag)*v;float oldx=incrementx;
		float incrementy=((toy-fromy)/mag)*v;
		incrementx+=Calculator.inaccurate(incrementy, inaccuracy);
		incrementy+=Calculator.inaccurate(oldx,inaccuracy);
		float ax=(tox-fromx)/mag*a;oldx=ax;
		float ay=(toy-fromy)/mag*a;
		ax+=Calculator.inaccurate(ay, inaccuracy);
		ay+=Calculator.inaccurate(oldx,inaccuracy);
		
		effects.add(new Projectile(run.objects,(int) ((mag/v)*2),fromx,fromy, incrementx,incrementy,ax,ay,type,color,damage));
	}
	public void testProjectile(){
		effects.add(new Projectile(run.objects,1000,100,50,0,0,0.1f,0,1,Renderer.RED,1));
	}
	
}
