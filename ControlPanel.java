import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	private BufferedImage bi;	
	Graphics2D big;

	Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();

	ArrayList<Shapes> shapes = new ArrayList<Shapes>();
	
	public ControlPanel() {
		bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.PINK);
	}

	public void updateGameUI(){
		big.clearRect(0, 0, d.width, d.height);
		
		for(Shapes s : shapes){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
