import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.BorderLayout;
import java.util.ArrayList;


public class Main {
	public static void main(String[] args){
		//create frame
		JFrame frame = new JFrame(". : : Siczones avoid : : .");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Exit program when click close button
		frame.setSize(700, 400);									//Set frame size
		frame.setLocation(300, 100);								//Set frame location on monitor
		frame.getContentPane().setLayout(new BorderLayout());
		
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Image img = tk.getImage("logo.png");						
		frame.setIconImage(img);									//set icon from directory
		
		ControlPanel cp = new ControlPanel();
		frame.getContentPane().add(cp, BorderLayout.CENTER);		//add background		
		
		SpaceShip ss = new SpaceShip(180, 60, 20, 20);				//add spaceship (distance_letfRight, distance_topDown, size_horizentol,  size_vertical)
		cp.shapes.add(ss);											//add SpaceShip to arrayList
		cp.updateGameUI();											//show spaceShip on monitor

		
		frame.setVisible(true);
	}
}
