// Defintion enemy charector 
// Enemy.java

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Shapes{
	private int Y_TO_FADE ;
	private int Y_TO_DIE ;
	
	private int step = 10;
	private boolean alive = true;
	
	public Enemy(int x, int y, int heightScreen){
		super(x, y, 20, 10);
		Y_TO_FADE = heightScreen - 150;
		Y_TO_DIE = heightScreen - 50;
		System.out.print(".");						//test class active
	}

	@Override
	public void draw(Graphics2D g){
		if(y < Y_TO_FADE)							
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		
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
}