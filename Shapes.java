import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public abstract class Shapes {
	int x;
	int y;
	int width;
	int height;
	
	public Shapes(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	abstract public void draw(Graphics2D g);
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);		//get shape property
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}
}
