package ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Calculator;
import main.Run;
import render.Renderer;

public class NightmareUI extends JPanel{
	private Run run;
	private GameUI gameUI;
	public int cursorx;
	public int moving;
	
	public int pop;
	
	private JLabel popLabel;
	private JLabel s1,s2,s3,s4,s5,s6,s7,s8,s9,s0;
	private JLabel d1,d2,d3,d4,d5,d6,d7,d8,d9,d0;
	private int currentDescription;
	
	public NightmareUI(Run run, GameUI game){
		this.run=run;
		gameUI=game;
	}
	public void update(){
		run.particles.add(Calculator.posToAbs(cursorx,Renderer.backgroundHeight-2), Renderer.RED, 1, 2);
		//fix this later with an actual sprite
		popLabel.setText("Nightmares: "+pop+"/"+gameUI.human.pop);
		if(moving==1)cursorx--;
		else if(moving==2)cursorx++;
	}
	public void init(){
		//this.setLayout(new GridLayout(0,1,0,1));
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		popLabel=new JLabel();
		
		//when changing pop values: here, "if", method called by if, and death logic of the object
		s1=new JLabel("1:Spawn Infector--cost:1");
		s2=new JLabel("2:Spawn Zombie--cost:1");
		s3=new JLabel("3:Spawn Crawler--cost:2");
		s4=new JLabel("4:Spawn Tower--cost:4");
		s5=new JLabel("5:Spawn Walker--cost:6");
		s6=new JLabel("6:Spawn Streaker--cost:2");
		s7=new JLabel("7:Spawn Zombie Gunship--cost:5");
		s8=new JLabel("8:Spawn Rot--cost:4");
		s9=new JLabel("9:Spawn Wyrm--cost:10");
		s0=new JLabel("0:Spawn Missile");
		
		d1=new JLabel("The Infector is a cheap, weak unit that can infect humans and towers.");
		d2=new JLabel("Hey, that guy looks familiar... except for those tentacle things growing out of his face.");
		d3=new JLabel("Crawlers quickly race towards their target to overwhelm defenses.");
		d4=new JLabel("Kittens, people, lasers, walls -- the tower don't care. It just keeps going.");
		d5=new JLabel("A slow, powerful unit that shoots beams of death from its singular eye.");
		d6=new JLabel("The basic air unit, enough Streakers can whittle down any defense.");
		d7=new JLabel("Flimsy but powerful, the Gunship can infect crowds of defenders at once.");
		d8=new JLabel("This will take more than your average weedkiller to stop.");
		d9=new JLabel("Wyrms shoot out of the ground, distrupting defenses.");
		d0=new JLabel("");
		
		
		add(new JLabel("---NIGHTMARE---"));
		add(popLabel);
		add(new JLabel());
		
		add(s1);
		add(s2);
		add(s3);
		add(s4);
		add(s5);
		add(new JLabel());
		add(s6);
		add(s7);
		add(new JLabel());
		add(s8);
		add(s9);
		add(s0);
		
		add(new JLabel());
		add(d0);
		currentDescription=0;
		
		
		revalidate();
	}
	public void changeDescription(int num){
		if(currentDescription==1)remove(d1);
		if(currentDescription==2)remove(d2);
		if(currentDescription==3)remove(d3);
		if(currentDescription==4)remove(d4);
		if(currentDescription==5)remove(d5);
		if(currentDescription==6)remove(d6);
		if(currentDescription==7)remove(d7);
		if(currentDescription==8)remove(d8);
		if(currentDescription==9)remove(d9);
		if(currentDescription==0)remove(d0);
		
		if(num==1)add(d1);
		if(num==2)add(d2);
		if(num==3)add(d3);
		if(num==4)add(d4);
		if(num==5)add(d5);
		if(num==6)add(d6);
		if(num==7)add(d7);
		if(num==8)add(d8);
		if(num==9)add(d9);
		if(num==0)add(d0);
		
		currentDescription=num;
		repaint();
	}
	public void spawn(int num){
		if(num==1);
		else if(num==5 && pop+6<=gameUI.human.pop)walker();
		else if(num==4 && pop+4<=gameUI.human.pop)wall();
		else if(num==9 && pop+10<=gameUI.human.pop)wyrm();
		else if(num==6 && pop+2<=gameUI.human.pop)streaker();
	}
	
	private void walker(){
		if(cursorx<Renderer.backgroundWidth/2)run.objects.addWalker(-10);
		else run.objects.addWalker(Renderer.backgroundWidth+10).flipped=true;
		pop+=6;
	}
	private void wall(){
		if(cursorx<Renderer.backgroundWidth/2)run.objects.addWall(-10);
		else run.objects.addWall(Renderer.backgroundWidth+10).flipped=true;
		pop+=4;
	}
	private void wyrm(){
		boolean flipped=false;
		if(Math.random()<0.5)flipped=true;
		run.objects.addWyrm(cursorx).flipped=flipped;
		pop+=10;
	}
	private void streaker(){
		run.objects.addStreaker(cursorx, -2);
		pop+=2;
	}
}
