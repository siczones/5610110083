import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Shapes{

	int step = 10;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);								//send argument SpaceShip to Shapes
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);								//fill color into SpaceShip
		g.fillRect(x, y, width, height);						//draws a solid rectangle (int distance_letfRight, int distance_topDown, int size_horizentol, int size_vertical)
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}

}
