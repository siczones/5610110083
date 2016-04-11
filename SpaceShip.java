import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Shapes{
	int step = 10;												//step move spaceship
	int widthScreen;
	
	public SpaceShip(int x, int y, int width, int height, int widthScreen) {
		super(x, y, width, height);								//send argument SpaceShip to Shapes
		this.widthScreen = widthScreen;
		//test
		System.out.println("@ SpaceShip Active");
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);								//fill color into SpaceShip
		g.fillRect(x, y, width, height);						//draws a solid rectangle (int distance_letfRight, int distance_topDown, int size_horizentol, int size_vertical)
	}
	
	//move direction control
	public void move(int direction){
		x += (step * direction);								//determine move step 
		//check out of border
		if(x <= 0)
			x = 0;
		if(x >= widthScreen - (width + width/2))
			x = widthScreen - (width + width/2);
	}

}
