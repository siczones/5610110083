import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class BufferedImageLoader {
	
	private BufferedImage image;
	public	BufferedImage loadImage(String path) throws IOException{
		InputStream stream = getClass().getResourceAsStream(path);
		image = ImageIO.read(stream);
		return image;
	} 

}