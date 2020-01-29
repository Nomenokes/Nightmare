package ui;

import javax.swing.JPanel;

import main.Run;

public class Menu extends JPanel{
	private Run run;
	public GameUI game;
	public Menu(Run run){
		this.run=run;
		game=new GameUI(run);
	}
	public void update(){
		game.update();
	}
	public void init(){
		game.init();
		game.setPreferredSize(this.getPreferredSize());
		
		add(game);
		revalidate();
	}
}
