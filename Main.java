import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame(". : : Siczones avoid : : .");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Exit program when click close button
		frame.setSize(700, 400);									//Set frame size
		frame.setLocation(300, 100);								//Set frame location on monitor
		frame.getContentPane().setLayout(new BorderLayout());
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		//set icon
		Image img = kit.getImage("logo.png");
		frame.setIconImage(img); 
		//add background
		Background bg = new Background();
		frame.getContentPane().add(bg, BorderLayout.CENTER);		

		frame.setVisible(true);
	}
}
