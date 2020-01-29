package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import main.Calculator;
import main.Run;
import object.Object;
import render.Renderer;

public class InputHandler implements KeyListener, MouseListener{
	private Run run;
	
	public static int moving;
	
	
	public InputHandler(Run run){
		this.run=run;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==KeyEvent.VK_LEFT)run.menu.game.nightmare.moving=1;
		else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT)run.menu.game.nightmare.moving=2;
		
		if(arg0.getKeyCode()==KeyEvent.VK_LEFT)moving=1;
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT)moving=2;
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE)run.paused=!run.paused;
		
		
		
		else if(arg0.getKeyCode()==KeyEvent.VK_1)run.menu.game.nightmare.spawn(1);
		else if(arg0.getKeyCode()==KeyEvent.VK_2)run.menu.game.nightmare.spawn(2);
		else if(arg0.getKeyCode()==KeyEvent.VK_3)run.menu.game.nightmare.spawn(3);
		else if(arg0.getKeyCode()==KeyEvent.VK_4)run.menu.game.nightmare.spawn(4);
		else if(arg0.getKeyCode()==KeyEvent.VK_5)run.menu.game.nightmare.spawn(5);
		else if(arg0.getKeyCode()==KeyEvent.VK_6)run.menu.game.nightmare.spawn(6);
		else if(arg0.getKeyCode()==KeyEvent.VK_7)run.menu.game.nightmare.spawn(7);
		else if(arg0.getKeyCode()==KeyEvent.VK_8)run.menu.game.nightmare.spawn(8);
		else if(arg0.getKeyCode()==KeyEvent.VK_9)run.menu.game.nightmare.spawn(9);
		else if(arg0.getKeyCode()==KeyEvent.VK_0)run.menu.game.nightmare.spawn(0);
		
		else if(arg0.getKeyCode()==KeyEvent.VK_Q)run.menu.game.nightmare.changeDescription(1);
		else if(arg0.getKeyCode()==KeyEvent.VK_W)run.menu.game.nightmare.changeDescription(2);
		else if(arg0.getKeyCode()==KeyEvent.VK_E)run.menu.game.nightmare.changeDescription(3);
		else if(arg0.getKeyCode()==KeyEvent.VK_R)run.menu.game.nightmare.changeDescription(4);
		else if(arg0.getKeyCode()==KeyEvent.VK_T)run.menu.game.nightmare.changeDescription(5);
		else if(arg0.getKeyCode()==KeyEvent.VK_Y)run.menu.game.nightmare.changeDescription(6);
		else if(arg0.getKeyCode()==KeyEvent.VK_U)run.menu.game.nightmare.changeDescription(7);
		else if(arg0.getKeyCode()==KeyEvent.VK_I)run.menu.game.nightmare.changeDescription(8);
		else if(arg0.getKeyCode()==KeyEvent.VK_O)run.menu.game.nightmare.changeDescription(9);
		else if(arg0.getKeyCode()==KeyEvent.VK_P)run.menu.game.nightmare.changeDescription(0);
		
		else if(arg0.getKeyCode()==KeyEvent.VK_LEFT || arg0.getKeyCode()==KeyEvent.VK_RIGHT)run.menu.game.nightmare.moving=0;
		
		
		 if(arg0.getKeyCode()==KeyEvent.VK_LEFT || arg0.getKeyCode()==KeyEvent.VK_RIGHT)moving=0;
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(SwingUtilities.isLeftMouseButton(arg0))select(arg0);
		else if(SwingUtilities.isRightMouseButton(arg0))moveHuman(arg0);
		
	}
	private void select(MouseEvent mouse){
		int x=(int)screenToGamePosX(mouse.getX(),Renderer.backgroundWidth);
		int index=run.objects.testPos(
				Calculator.posToAbs(
						x,
						(int)screenToGamePosY(mouse.getY(),Renderer.backgroundHeight)
				),
				new int[]{0,1,2,3});
		if(index>=0){
			Object selected=run.objects.get(index);
			run.menu.game.human.select(selected);
		}else{
			run.menu.game.human.select(null);
			run.menu.game.human.select(x);
		}
	}
	private void moveHuman(MouseEvent mouse){
		int x=(int)screenToGamePosX(mouse.getX(),Renderer.backgroundWidth);
		run.menu.game.human.move(x);
	}
	
	public double screenToGamePosX(int screenPos, int backgroundWidth){
		double ratio=(double)screenPos/run.canvasWidth;
		return backgroundWidth*ratio;
	}
	public double screenToGamePosY(int screenPos, int backgroundHeight){
		double ratio=(double)screenPos/run.canvasHeight;
		return backgroundHeight*ratio;
	}
	
}
