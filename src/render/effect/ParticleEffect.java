package render.effect;


public class ParticleEffect {
	public float x,y;
	//public int ix,iy;
	public int color;
	public int lifetime;
	public ParticleEffect(int life,int color, float x, float y){
		this.x=x;this.y=y;this.color=color;
		lifetime=life;
	}
	public void tick(){
		lifetime--;
		//System.out.println(color);
	}
	
}
