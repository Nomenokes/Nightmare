package main;

import render.Renderer;


public class Calculator {
	public static int posToAbs(int x,int y){
		if(x>=Renderer.backgroundWidth||y>=Renderer.backgroundHeight||x<0||y<0)return 0;
		return  y*Renderer.backgroundWidth+x;
	}
	public static double absToPosX(int abs){
		return abs%Renderer.backgroundWidth;
	}
	public static double absToPosY(int abs){
		return abs/Renderer.backgroundWidth;
	}
	public static int getFreeSpot(Object[] objects){
		for(int i=0;i<objects.length;i++){
			if(objects[i]==null){
				return i;
			}
		}
		return -1;
	}
	public static int getFreeSpot(int[] objects){
		for(int i=0;i<objects.length;i++){
			if(objects[i]==0){
				return i;
			}
		}
		/*for(int i=objects.length-1;i>=0;i--){
			if(objects[i]==0){
				return i;
			}
		}*/
		return -1;
	}
	public static float getDistanceSquared(double x1, double y1, double x2, double y2){
		float distancex=(float) (x1-x2);
		float distancey=(float) (y1-y2);
		return distancex*distancex+distancey*distancey;
	}
	public static double inaccurate(double init, double inaccuracy){
		return (Math.random()-0.5)*init*inaccuracy*2;
	}
	
}
