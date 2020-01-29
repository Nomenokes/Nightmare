package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Calculator;
import main.Run;
import object.Object;
import object.types.Hero;
import object.types.House;
import object.types.Human;
import object.types.HumanIdle;
import object.types.HumanWall;
import object.types.Mage;
import render.Renderer;

public class HumanUI extends JPanel{
	private Run run;
	private GameUI gameUI;
	private Object selected;
	//private Object cursor;
	public int cursorx;
	
	
	private JLabel title;
	private JLabel popL;
	private JLabel idleL;
	private JLabel resL;
	
	private JLabel selectedHeader;
	private JLabel name;
	private JLabel health;
	
	private JButton rallyOnOff,followOff;
	
	public static int[] rally=new int[234];
	public static boolean[] rallying=new boolean[234];
	
	
	private JButton[] build;
	private ArrayList<HumanIdle> idlePop=new ArrayList<HumanIdle>();
	
	
	public int pop,res,idle;
	
	public HumanUI(Run run, GameUI game){
		this.run=run;
		gameUI=game;
		//cursor=run.objects.addArrow();
	}
	public void select(Object select){
		selected=select;
	}
	public void select(int x){
		cursorx=x;
	}
	public void update(){
		run.particles.add(Calculator.posToAbs(cursorx,01), Renderer.YELLOW, 1, 2);
		
		if(selected!=null){
			cursorx=(int) selected.x;
			name.setText(GameUI.TAB+selected.name);
			health.setText(GameUI.TAB+"Health: " + Integer.toString(selected.health));
			
			if(selected.type==1){
				Human human=(Human)selected;
				if(human.followingX){
					followOff.setEnabled(true);
				}
			}
		}
		else{name.setText("");health.setText("");}
		resL.setText(GameUI.TAB+"Resources: "+Integer.toString(res));
		popL.setText(GameUI.TAB+"Population: "+Integer.toString(pop));
		idleL.setText(GameUI.TAB+"Idle pop: "+Integer.toString(idle));
		
		//pop++;
		//idle++;
		//res++;
		
		//repaint();
		//revalidate();
	}
	public void move(int x){
		if(selected!=null && selected.type==1){
			Human human=(Human)selected;
			human.followX=x;
			human.followingX=true;
		}
	}
	public void createIdle(int x){
		idlePop.add(run.objects.addHumanIdle(x));
		idle++;
		pop++;
	}
	public void removeIdle(){
		int i=(int) (Math.random()*idlePop.size());
		run.objects.remove(idlePop.get(i).handlerIndex);
		idlePop.remove(i);
		idle--;
	}
	public void removeIdle(HumanIdle remove){
		run.objects.remove(remove.handlerIndex);
		idlePop.remove(remove);
		idle--;
	}
	public void init(){
		//this.setLayout(new GridLayout(0,1,0,1));
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		title=new JLabel();
		popL=new JLabel();
		resL=new JLabel();
		idleL=new JLabel();
		name=new JLabel();
		health=new JLabel();
		selectedHeader=new JLabel();
		
		title.setText("---HUMAN---");
		name.setText("");
		health.setText("");
		selectedHeader.setText("Selected: ");
		
		JButton b1=new JButton("house: 0,50");
		JButton b2=new JButton("mage: 1,30");
		JButton b3=new JButton("soldier: 1,20");
		JButton b4=new JButton("wall: 0,20");
		JButton b5=new JButton("hero: 1,100");
		
		rallyOnOff=new JButton("Rally here");
		followOff=new JButton("Stop custom movement");
		b1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(res>=50){
					House w=run.objects.addHouse(cursorx);
					if(Math.random()<0.5)w.flipped=true;
					res-=50;
				}
			}
		});
		b2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(res>=30&&idle>=1){
					Mage w=run.objects.addMage(cursorx);
					if(Math.random()<0.5)w.flipped=true;
					res-=30;removeIdle();
				}
			}
		});
		b3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(res>=20&&idle>=1){
				//	Walker w=run.objects.addMage(cursorx);
				//	if(Math.random()<0.5)w.flipped=true;
				//	res-=20;removeIdle();
				}
			}
		});
		b4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(res>=20){
					HumanWall w=run.objects.addHumanWall(cursorx);
					if(Math.random()<0.5)w.flipped=true;
					res-=20;
				}
			}
		});
		b5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(res>=100&&idle>=1){
					Hero w=run.objects.addHero(cursorx);
					if(Math.random()<0.5)w.flipped=true;
					res-=100;removeIdle();
				}
			}
		});
		
		rallyOnOff.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected==null || selected.type!=1)return;
				//Human human=(Human)selected;
				if(rallying[selected.id]){
					rallying[selected.id]=false;
					
					rallyOnOff.setText("Rally here");
					//repaint();
					//revalidate();
				}
				else {
					rallying[selected.id]=true;
					rally[selected.id]=(int) selected.x;
					rallyOnOff.setText("Stop rallying");
				}
			}
		});
		followOff.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected==null || selected.type!=1)return;
				Human human=(Human)selected;
				if(human.followingX){
					human.followingX=false;
					followOff.setEnabled(false);
				}
			}
		});
		
		build=new JButton[]{b1,b2,b3,b4,b5};
		
		
		add(title);
		add(popL);
		add(idleL);
		add(resL);
		
		add(selectedHeader);
		add(name);
		add(health);
		add(rallyOnOff);
		add(followOff);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		
		
		
		revalidate();
	}
}
