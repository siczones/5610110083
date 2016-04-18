import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.BorderLayout;

public class Main {
	public static final int WIDTH_SCREEN = 700;
	public static final int HEIGTH_SCREEN = 400;
	public static void main(String[] args){
		//create frame
		JFrame frame = new JFrame(". : : Siczones avoid : : .");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				//Exit program when click close button
		frame.setSize(WIDTH_SCREEN, HEIGTH_SCREEN);							//Set frame size width and height
		frame.setLocation(300, 100);										//Set frame location on monitor
		frame.getContentPane().setLayout(new BorderLayout());
		
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Image img = tk.getImage("logo.png");						
		frame.setIconImage(img);											//set icon from directory
		
		ControlPanel cp = new ControlPanel(WIDTH_SCREEN, HEIGTH_SCREEN);
		frame.getContentPane().add(cp, BorderLayout.CENTER);				//add ControlPanel		

		//System.out.println(d.width + ":" + d.height);
		SpaceShip ss = new SpaceShip(330, 340, 40, 20, WIDTH_SCREEN, HEIGTH_SCREEN);//add spaceship posision (x, y, width_size, height_size)
		Controls c = new Controls(cp, ss);
		frame.addKeyListener(c);
		c.start();
		frame.setVisible(true);
	}
}
