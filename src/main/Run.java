package main;

import input.InputHandler;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Object;
import object.ObjectHandler;
import render.ParticleHandler;
import render.Renderer;
import ui.Menu;

public class Run {
	
	public String name="Nightmare";
	
	public static final int ticksPerSecond=60;
	public boolean running;
	public boolean paused;
	
	private int frameHeight,frameWidth;
	public JFrame frame;
	public JPanel game;
	public JPanel text;
	public Canvas canvas;
	public int canvasHeight,canvasWidth;
	
	public Renderer renderer;
	public ObjectHandler objects;
	public ParticleHandler particles;
	public InputHandler input;
	public Menu menu;
	
	public static void main(String[] args){
		new Run();
	}
	public Run(){
		System.out.println("Starting.");
		start();
		loop();
		System.out.println("Closing.");
	}
	public void start(){
		
		frameHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
		frameWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
		canvasHeight=(int) (frameWidth*(19d/20)/4);
		canvasWidth=(int) (frameWidth*(19d/20));
		
		frame=new JFrame(name);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(new Dimension((int)frameWidth/2,(int)frameHeight/2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game=new JPanel();
		text=new JPanel();
		text.setLayout(new BorderLayout());
		
		game.setSize(new Dimension(canvasWidth,canvasHeight));
		text.setPreferredSize(new Dimension(frameWidth,(frameHeight-canvasHeight)/3));
		text.add(new JLabel("Nightmare"),BorderLayout.NORTH);
		
		canvas=new Canvas();
		canvas.setSize(game.getSize());
		game.add(canvas);
		
		frame.add(game,BorderLayout.CENTER);
		frame.add(text,BorderLayout.NORTH);
		
		Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
		
		
		renderer=new Renderer(this);
		objects=new ObjectHandler(this);//must make renderer first
		particles=new ParticleHandler(this);//must make renderer first
		input=new InputHandler(this);
		menu=new Menu(this);
		
		menu.setPreferredSize(new Dimension(canvasWidth,(int) ((frameHeight-canvasHeight)*(1d/2))));
		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		frame.add(menu,BorderLayout.SOUTH);
		menu.init();
		
		ObjectHandler.init();
		
	//	objects.addHero(100);
	//	objects.addWalker(-10);
	//	objects.addStreaker(0, 20);
	//	menu.game.human.createIdle(100);
	//	menu.game.human.pop=1;
	//	menu.game.human.idle=9;
		menu.game.human.res=1000000;
		
		//objects.addPlayer()
		
		running=true;
		paused=false;
	}
	@SuppressWarnings("unused")
	public void loop(){
		long tickShould=0;
		long tickLast=0;
		long tickCount=0;
		
		long before=0;
		long lag=0;
		
		while(running){
			tickShould=System.nanoTime()/(1000000000/ticksPerSecond);
			if(tickShould>=tickLast+1){
				menu.update();
				if(!paused){
					tickLast=tickShould;
					before=System.nanoTime();
					
					tick(tickCount);
					tickCount++;
					
					lag=System.nanoTime()-before;
				}
				renderer.render();
			}
		}
	}
	public void tick(long count){
		objects.tick();
		particles.tick();
		
	}
}
