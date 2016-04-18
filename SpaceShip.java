// Defintion SpaceShip charector 
// SpaceShip.java

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class SpaceShip extends Shapes{
	private int step = 10;												//step move spaceship
	private int widthScreen;
	private int heightScreen;
	public static final int BOUND = 60;
	
	public SpaceShip(int x, int y, int width, int height, int widthScreen, int heightScreen) {
		super(x, y, width, height);								//send argument SpaceShip to Shapes
		this.widthScreen = widthScreen;
		this.heightScreen = heightScreen;
		System.out.println("@ SpaceShip Active");				//test class active
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);								//fill color into SpaceShip
		g.fillRect(x, y, width, height);						//draws a solid rectangle (int distance_letfRight, int distance_topDown, int size_horizentol, int size_vertical)
	}
	
	//check move direction control from parameter
	public void move(int direction){
		if(direction == 1 || direction == -1){
			x += (step * direction);							
			leftRight();
		}
		else if(direction == 2 || direction == -2){
			y += (step * (direction/2));
			upDown();
		}		
	}

	//check over bound left and right key
	public void leftRight(){
			if(x <= 0)
				x = 0;
			if(x >= widthScreen - BOUND)			
				x = widthScreen - BOUND;
		}

	//check over bound up and down key
	public void upDown(){
			if(y <= 0)
				y = 0;
			if(y >= heightScreen - BOUND)			
				y = heightScreen - BOUND;
		}

}
