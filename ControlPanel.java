import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.io.IOException;
import javax.swing.JButton;

public class ControlPanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();
	ArrayList<Shapes> shapes = new ArrayList<Shapes>();
	BufferedImageLoader bg = new BufferedImageLoader();

	private BufferedImage background = null;									//variable for keep background
	private int widthScreen;
	private int hieghtScreen;
	
	public ControlPanel(int widthScreen, int hieghtScreen) {
		System.out.println("@ ControlPanel Active");							//test class active
	
		this.widthScreen = widthScreen;
		this.hieghtScreen = hieghtScreen;
		bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		//big.setBackground(Color.PINK);
		//add image background
		background = new BufferedImage(widthScreen, hieghtScreen, BufferedImage.TYPE_INT_ARGB);
		try{
			background = bg.loadImage("/images/background.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		Background();
	}

	public void Background(){		
		big.drawImage(background, 0, 0, widthScreen, hieghtScreen, null);
	}
	//show spaceShip & score on monitor
	public void updateGameUI(Score score){
		big.clearRect(0, 0, d.width, d.height);
		big.setColor(Color.yellow);												//set score color
		Background();
		//draw score is xxxx,xxxx form and set score posision(x,y);16 is size of 8 charector 
		big.drawString(String.format("%08d", score.getScore()), widthScreen/2 - 16 , 20);	
		for(Shapes s : shapes){
			s.draw(big);														
		}
		repaint();
	}

	public int getWidthScreen(){
		return widthScreen;
	}

	public int getHieghtScreen(){
		return hieghtScreen;
	}

	public void showRsBtn(){
		JButton RsBtn = new JButton("RESTART");
		RsBtn.setPreferredSize(new Dimension(100, 60));
		System.out.println("\nRestart button active");
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
}
