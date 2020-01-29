package object;

import input.InputHandler;
import main.Calculator;
import main.Run;
import object.types.Flying;
import object.types.Hero;
import object.types.HeroSword;
import object.types.House;
import object.types.Human;
import object.types.HumanCursor;
import object.types.HumanIdle;
import object.types.HumanWall;
import object.types.Mage;
import object.types.Streaker;
import object.types.Walker;
import object.types.Wall;
import object.types.Wyrm;
import render.Renderer;

public class ObjectHandler {
	public Run run;
	private Object[] objects=new Object[9999];
	public int[] colors;
	public static final int MAX_SIZE=2048;
	public static final int MAX_BEAM_LENGTH=600;
	/**must make renderer first*/
	public ObjectHandler(Run run){
		this.run=run;
		colors=new int[Renderer.backgroundWidth*Renderer.backgroundHeight];
	}
	public void tick(){
		/*if(InputHandler.moving==1){
			moveAll(1);
		}
		else if(InputHandler.moving==2){
			moveAll(-1);
		}*/
		
		for(int i=0;i<objects.length;i++){
			if(objects[i]!=null)
				objects[i].tick();
		}
		if(run.menu.game.human.pop<=0){
			for(int i=0;i<objects.length;i++){
				if(objects[i]!=null)
					objects[i].die();
			}
			run.menu.game.human.idle=0;
			run.menu.game.human.pop=0;
			run.menu.game.human.res=100;
			run.menu.game.nightmare.pop=0;
		}
	}
	public int[] render(){
		for(int i=0;i<colors.length;i++){
			colors[i]=0;
		}
		for(int i=0;i<objects.length;i++){
			if(objects[i]!=null){
				if(objects[i].type!=1 || !((Human)objects[i]).dying){
					for(int h=0;h<Object.height[objects[i].id];h++){
						for(int w=0;w<Object.width[objects[i].id];w++){
							if(objects[i]!=null){
								int pos=0;
								if(!objects[i].flipped)pos=Calculator.posToAbs((int)(objects[i].x+w-Object.width[objects[i].id]/2), (int)(objects[i].y+h-Object.height[objects[i].id]/2));
								else pos=Calculator.posToAbs((int)(objects[i].x-w+Object.width[objects[i].id]/2), (int)(objects[i].y+h-Object.height[objects[i].id]/2));
								int color=Object.sprites[objects[i].id][objects[i].state][h*Object.width[objects[i].id]+w];
								if(pos(pos,color))
									colors[pos]=color;
							}
						}
					}
				}
				else{
					
				}
			}
		}
		return colors;
	}
	
	
	public Walker addWalker(double x){
		int spot=Calculator.getFreeSpot(objects);
		return (Walker) addToObjects(new Walker(x,Renderer.backgroundHeight-1-Object.height[0]/2,this,spot),spot);
	}
	public Wall addWall(double x){
		int spot=Calculator.getFreeSpot(objects);
		return (Wall) addToObjects(new Wall(x,Renderer.backgroundHeight-1-Object.height[1]/2,this,spot),spot);
	}
	public Wyrm addWyrm(double x){
		int spot=Calculator.getFreeSpot(objects);
		return (Wyrm) addToObjects(new Wyrm(x,Renderer.backgroundHeight-1-Object.height[3]/2,this,spot),spot);
	}
	public Hero addHero(double x){
		int spot=Calculator.getFreeSpot(objects);
		return (Hero) addToObjects(new Hero(x,Renderer.backgroundHeight-1-Object.height[4]/2,this,spot),spot);
	}
	public Streaker addStreaker(double x, double y){
		int spot=Calculator.getFreeSpot(objects);
		return (Streaker) addToObjects(new Streaker(x,y,this,spot),spot);
	}
	public Mage addMage(double x){
		int spot=Calculator.getFreeSpot(objects);
		return (Mage) addToObjects(new Mage(x,Renderer.backgroundHeight-1-Object.height[8]/2,this,spot),spot);
	}
	public HumanIdle addHumanIdle(double x){
		int spot=Calculator.getFreeSpot(objects);
		return (HumanIdle) addToObjects(new HumanIdle(x,Renderer.backgroundHeight-1-Object.height[8]/2,this,spot),spot);
	}
	public House addHouse(double x){
		int spot=Calculator.getFreeSpot(objects);
		return (House) addToObjects(new House(x,Renderer.backgroundHeight-1-Object.height[9]/2,this,spot,run.menu.game.human),spot);
	}
	public HumanWall addHumanWall(double x){
		int spot=Calculator.getFreeSpot(objects);
		return (HumanWall) addToObjects(new HumanWall(x,Renderer.backgroundHeight-1-Object.height[10]/2,this,spot),spot);
	}
	public HumanCursor addArrow(){
		int spot=Calculator.getFreeSpot(objects);
		HumanCursor add=new HumanCursor(Renderer.backgroundWidth/2,this,spot);
		addToObjects(add,spot);
		return add;
	}
	public void addSword(Hero hero){
		int spot=Calculator.getFreeSpot(objects);
		addToObjects(new HeroSword(hero,this,spot),spot);
	}
	
	
	private Object addToObjects(Object adding, int spot){
		objects[spot]=adding;
		return adding;
	}
	public void removeObject(int num){
		objects[num]=null;
	}
	public Object get(int index){
		if(index>=0&&index<objects.length)return objects[index];
		return null;
	}
	
	private boolean pos(int pos,int color){
		if(color==Renderer.WHITE)return false;//never render white
		if(colors[pos]==Renderer.BLACK)return true;// black is min priority
		
		// will add more with more color priorities
		
		if(colors[pos]==0)return true;
		return true;
	}
	public void remove(int pos){
		objects[pos]=null;
	}
	public static void init(){
		
		Object.init();
		Flying.init();
	}
	
	
	
	
	
	
	
	
	public int findNearest(int index, int[] types){
		if(index<0||index>=objects.length||objects[index]==null)return -1;
		int winnerIndex=-1;
		float winnerDistance=Float.MAX_VALUE;
		for(int i=0;i<objects.length;i++){
			for(int j=0;j<types.length;j++){
				if(i!=index && objects[i]!=null && objects[i].type==types[j] && objects[i].health>0){
					float distance=Calculator.getDistanceSquared(objects[i].x,objects[i].y,objects[index].x,objects[index].y);
					if(distance<winnerDistance){
						winnerIndex=i;
						winnerDistance=distance;
					}
				}
			}
		}
		return winnerIndex;
	}
	public int findNearest(int index, int type){
		if(index<0||index>=objects.length||objects[index]==null)return -1;
		int winnerIndex=-1;
		float winnerDistance=Float.MAX_VALUE;
		for(int i=0;i<objects.length;i++){
			if(i!=index && objects[i]!=null && objects[i].type==type && objects[i].health>0){
				float distance=Calculator.getDistanceSquared(objects[i].x,objects[i].y,objects[index].x,objects[index].y);
				if(distance<winnerDistance){
					winnerIndex=i;
					winnerDistance=distance;
				}
			}
		}
		return winnerIndex;
	}
	public int testPos(int pos, int type){
		double x=Calculator.absToPosX(pos);
		double y=Calculator.absToPosY(pos);
		for(int i=0;i<objects.length;i++){
			if(objects[i]!=null && (objects[i].type==type || type==-1) && objects[i].health>0 && Calculator.getDistanceSquared(x,y,objects[i].x,objects[i].y)<=MAX_SIZE){
				if(
						x>objects[i].x-Object.width[objects[i].id]/2 && 
						x<objects[i].x+Object.width[objects[i].id]/2 &&
						y>objects[i].y-Object.height[objects[i].id]/2 && 
						y<objects[i].y+Object.height[objects[i].id]/2
				) return i;
			}
		}
		return -1;
		
	}
	public int testPos(int pos, int[] types){
		double x=Calculator.absToPosX(pos);
		double y=Calculator.absToPosY(pos);
		for(int i=0;i<objects.length;i++){
			for(int type:types){
				if(objects[i]!=null && (objects[i].type==type || type==-1) && objects[i].health>0 && Calculator.getDistanceSquared(x,y,objects[i].x,objects[i].y)<=MAX_SIZE){
					if(
							x>objects[i].x-Object.width[objects[i].id]/2 && 
							x<objects[i].x+Object.width[objects[i].id]/2 &&
							y>objects[i].y-Object.height[objects[i].id]/2 && 
							y<objects[i].y+Object.height[objects[i].id]/2
					) return i;
				}
			}
		}
		return -1;
	}
	public int testHit(int pos, int type){
		double x=Calculator.absToPosX(pos);
		double y=Calculator.absToPosY(pos);
		for(int i=0;i<objects.length;i++){
			if(objects[i]!=null && (objects[i].type==type || type==-1) && objects[i].health>0 && Calculator.getDistanceSquared(x,y,objects[i].x,objects[i].y)<=MAX_SIZE){
				if(
						x>objects[i].x-Object.width[objects[i].id]/4 && 
						x<objects[i].x+Object.width[objects[i].id]/4 &&
						y>objects[i].y-Object.height[objects[i].id]/3 && 
						y<objects[i].y+Object.height[objects[i].id]/3
				) return i;
			}
		}
		return -1;
	}
	public boolean testDamage(int pos, int type, int damage){
		int i=testPos(pos,type);
		if(i!=-1){
			objects[i].health-=damage;
			return true;
		}
		return false;
	}
	public boolean testDamage(int pos, int[] types, int damage){
		int i=testPos(pos,types);
		if(i!=-1){
			objects[i].health-=damage;
			return true;
		}
		return false;
	}
	
	public void beam(double posx, double posy, double tox, double toy, double inaccuracy, int type, int color, int damage,int lifetime){
		double distance=Math.sqrt((toy-posy)*(toy-posy)+(tox-posx)*(tox-posx));
		
		double incrementDistance=0;
		double incrementx=(tox-posx)/distance;double oldx=incrementx;
		double incrementy=(toy-posy)/distance;
		incrementx+=Calculator.inaccurate(incrementy, inaccuracy);
		incrementy+=Calculator.inaccurate(oldx,inaccuracy);
		double currentx=posx-incrementx;
		double currenty=posy-incrementy;
		int test=0;
		int object=-1;
		
		while(incrementDistance<MAX_BEAM_LENGTH){
			
			test=Calculator.posToAbs((int)currentx, (int)currenty);
			run.particles.add(test, color, 1, (int) (Math.random()*lifetime+lifetime));
			
			object=testHit(test,type);
			if(object!=-1){
				objects[object].health-=damage;
				return;
			}
			
			incrementDistance++;
			currentx+=incrementx;
			currenty+=incrementy;
		}
	}
	
	public void beam(double posx, double posy, double tox, double toy, double randX,double randY,double inaccurate, int type, int color, int damage, int lifetime){
		double distance=Math.sqrt((toy-posy)*(toy-posy)+(tox-posx)*(tox-posx));
		
		double incrementDistance=0;
		double incrementx=(tox-posx)/distance;double oldx=incrementx;
		double incrementy=(toy-posy)/distance;
		incrementx+=(randX-0.5)*incrementy*inaccurate*2;
		incrementy+=(randY-0.5)*oldx*inaccurate*2;
		double currentx=posx-incrementx;
		double currenty=posy-incrementy;
		int test=0;
		int object=-1;
		
		while(incrementDistance<MAX_BEAM_LENGTH){
			
			test=Calculator.posToAbs((int)currentx, (int)currenty);
			run.particles.add(test, color, 1, (int) (Math.random()*lifetime+lifetime));
			
			object=testHit(test,type);
			if(object!=-1){
				objects[object].health-=damage;
				return;
			}
			
			incrementDistance++;
			currentx+=incrementx;
			currenty+=incrementy;
		}
	}
	
	public static double dX(Object o1,Object o2){
		return Math.abs(o2.x-o1.x);
	}
	
	public void moveAll(float amount){
		for(Object o:objects){
			if(o!=null){
				if(!o.name.equals("player"))o.x+=amount;
			}
		}
	}
}
