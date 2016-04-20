// Defintion enemy charector 
// Enemy.java

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Enemy extends Shapes{
	private int Y_TO_FADE ;
	private int Y_TO_DIE ;
	private int step = 5;										//speed of enemy variable
	private boolean alive = true;

	BufferedImageLoader ePath;										//variable keep path image
	private BufferedImage eImage = null;							//variable buffer image
	
	public Enemy(int x, int y, int heightScreen){
		super(x, y, 20, 30);
		Y_TO_FADE = heightScreen - 150;
		Y_TO_DIE = heightScreen - 50;
		System.out.print(".");										//test class active

		//load enemy image
		ePath = new BufferedImageLoader();
		eImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try{
			eImage = ePath.loadImage("/images/enemy.png");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g){
		if(y < Y_TO_FADE)							
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		//g.setColor(Color.RED);
		//g.fillRect(x, y, width, height);
		g.drawImage(eImage, x, y-height/2, width , height, null);		//add enemy image
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}

	public void setSpeed(int step){
		this.step = step;												//set speed
	}

	public int getSpeed(){
		return step;													//get speed
	}
}