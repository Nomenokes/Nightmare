package render;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Run;

public class Renderer {
	public static final int BLACK=-16777216;
	public static final int WHITE=16777215;
	public static final int RED=-65536;
	public static final int BROWN=-10471149;
	public static final int YELLOW=-256;
	
	public Run run;
	private BufferedImage background;
	public static int backgroundWidth;
	public static int backgroundHeight;
	
	
	public Renderer(Run run){
		this.run=run;
		if(run.canvas.getBufferStrategy()==null){
			run.canvas.createBufferStrategy(3);
		}
		setBackground("/background/background.png");
	}
	public void render(){
		Image image=getRenderImage();
		Graphics g=run.canvas.getBufferStrategy().getDrawGraphics();
		g.drawImage(image, 0, 0, run.canvas.getWidth(),run.canvas.getHeight(),null);
		g.dispose();
		run.canvas.getBufferStrategy().show();
	}
	private Image getRenderImage(){
		BufferedImage image= copyImage(background);
		int[] pixels=image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		
		int[] particles=run.particles.render();
		int[] objects=run.objects.render();
		
		for(int i=1;i<particles.length;i++){
			if(particles[i]!=0)
				pixels[i]=particles[i];
		}
		for(int i=1;i<objects.length;i++){
			if(objects[i]!=0 && pixels[i] != RED)//red max priority
				pixels[i]=objects[i];
			
		}
		
		image.setRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		return image;
	}
	public static BufferedImage copyImage(BufferedImage source){
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	public void setBackground(String name){
		background=readFile(name);
		backgroundWidth=background.getWidth();
		backgroundHeight=background.getHeight();
	}
	public static BufferedImage readFile(String name){
		try {
			return ImageIO.read(Renderer.class.getResourceAsStream(name));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not find texture by that name");
		}
		return null;
	}
	
}
