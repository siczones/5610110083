import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

public class Controls implements KeyListener{
	private ControlPanel cp;
	private SpaceShip ss;	
	private Timer timer;
	private double difficulty = 0.3;
	
	public Controls(ControlPanel cp, SpaceShip ss) {
		this.cp = cp;
		this.ss = ss;		
		cp.shapes.add(ss);										//add SpaceShip

		//test 
		System.out.println("@ Controls Active");

		timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
	}
	
	public void start(){
		timer.start();
	}

	private void process(){
		cp.updateGameUI();
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {				//move controls input by keypad
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			ss.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			ss.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
