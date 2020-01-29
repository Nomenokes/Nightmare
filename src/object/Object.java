package object;

import java.awt.image.BufferedImage;

import render.Renderer;

public abstract class Object {
	
	public static int[][][] sprites;public int state=0;public boolean flipped;
	public static int[] height,width;
	public static final int num=11;
	
	public int id;//0=walker, 1=wall...
	public double x,y;
	public int animationDelay=0;
	protected ObjectHandler handler;public int handlerIndex;
	public int health;
	public boolean inversed;
	public int type;//0=nightmare,1=human,2=zombie,3=flying
	public String name;
	
	public Object(double x, double y, ObjectHandler handler, int handlerIndex, int type){
		this.y=y;this.x=x;
		this.handler=handler;
		
		this.handlerIndex=handlerIndex;
		this.type=type;
	}
	public void tick(){
		if(health<=0 || x<-50 || x>Renderer.backgroundWidth+50){
			die();
		}
	}
	public void die(){
		handler.remove(handlerIndex);
	}
	public static void init(){
		height=new int[num];
		width=new int[num];
		sprites=new int[num][][];
		BufferedImage[][] sprite=new BufferedImage[][]{
				new BufferedImage[]{
						Renderer.readFile("/nightmares/walker/walker.png"),
				/*0*/	Renderer.readFile("/nightmares/walker/walker1.png"),
						Renderer.readFile("/nightmares/walker/walker2.png"),
						Renderer.readFile("/nightmares/walker/walker3.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/nightmares/wall/wall.png"),
						Renderer.readFile("/nightmares/wall/wall2.png"),
						Renderer.readFile("/nightmares/wall/wall3.png"),
						Renderer.readFile("/nightmares/wall/wall4.png"),
						Renderer.readFile("/nightmares/wall/wall5.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/misc/arrow.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/nightmares/wyrm/wyrm.png"),
						Renderer.readFile("/nightmares/wyrm/wyrm1.png"),
						Renderer.readFile("/nightmares/wyrm/wyrm2.png"),
						Renderer.readFile("/nightmares/wyrm/wyrm3.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/human/hero/idle/heroidle1a.png"),
						Renderer.readFile("/human/hero/idle/heroidle1b.png"),
						Renderer.readFile("/human/hero/idle/heroidle2a.png"),
						Renderer.readFile("/human/hero/idle/heroidle2b.png"),
						Renderer.readFile("/human/hero/attack/heroattack1a.png"),
						Renderer.readFile("/human/hero/attack/heroattack1b.png"),
						Renderer.readFile("/human/hero/attack/heroattackfinal.png"),
						Renderer.readFile("/human/hero/run/herorun1a.png"),
						Renderer.readFile("/human/hero/run/herorun1b.png"),
						Renderer.readFile("/human/hero/run/herorun1c.png"),
						Renderer.readFile("/human/hero/run/herorun1d.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/human/hero/idle/heroidle1_sword.png"),
						Renderer.readFile("/human/hero/idle/heroidle1_sword.png"),
						Renderer.readFile("/human/hero/idle/heroidle2a_sword.png"),
						Renderer.readFile("/human/hero/idle/heroidle2b_sword.png"),
						Renderer.readFile("/human/hero/attack/heroattack1a_sword.png"),
				/*5*/	Renderer.readFile("/human/hero/attack/heroattack1b_sword.png"),
						Renderer.readFile("/human/hero/attack/heroattackfinal_sword.png"),
						Renderer.readFile("/human/hero/run/herorun_sword.png"),
						Renderer.readFile("/human/hero/run/herorun_sword.png"),
						Renderer.readFile("/human/hero/run/herorun_sword.png"),
						Renderer.readFile("/human/hero/run/herorun_sword.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/nightmares/non-animated/barrager.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/human/mage/mage-idle.png"),
						Renderer.readFile("/human/mage/Mage-left-attack.png"),
						Renderer.readFile("/human/mage/mage-walk-left.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/human/idle/idleidle.png"),
						Renderer.readFile("/human/idle/idlerun1.png"),
						Renderer.readFile("/human/idle/idlerun2.png"),
						Renderer.readFile("/human/idle/idlerun3.png")
				},
				new BufferedImage[]{
						Renderer.readFile("/human/building/house/House.png")
				},
				new BufferedImage[]{
				/*10*/	Renderer.readFile("/human/building/wall/wall.png")
				}
		};
		
		for(int i=0;i<sprite.length;i++){
			sprites[i]=new int[sprite[i].length][];
			for(int b=0;b<sprite[i].length;b++){
				sprites[i][b]=sprite[i][b].getRGB(0, 0, sprite[i][b].getWidth(), sprite[i][b].getHeight(), null, 0, sprite[i][b].getWidth());   
			}
			height[i]=sprite[i][0].getHeight();
			width[i]=sprite[i][0].getWidth();
		}
		
	}
	public void damage(int damage){
		health-=damage;
	}
}
