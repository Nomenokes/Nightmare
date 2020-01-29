package ui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.Run;

public class GameUI extends JPanel{
	public static final String TAB="        ";
	
	private Run run;
	public HumanUI human;
	public NightmareUI nightmare;
	public GameUI(Run run){
		this.run=run;
		human=new HumanUI(run,this);
		nightmare=new NightmareUI(run,this);
		
		//human.setPreferredSize(new Dimension(getPreferredSize().width/2,getPreferredSize().height));
		//nightmare.setPreferredSize(new Dimension(getPreferredSize().width/2,getPreferredSize().height));
		human.setMinimumSize(new Dimension(getPreferredSize().width/2,getPreferredSize().height));
		nightmare.setMinimumSize(new Dimension(getPreferredSize().width/2,getPreferredSize().height));
	}
	public void update(){
		human.update();
		nightmare.update();
	}
	public void init(){
		human.init();nightmare.init();
		
		this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		add(nightmare);
		add(Box.createHorizontalGlue());
		add(human);
		revalidate();
	}
}
