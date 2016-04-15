import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

import java.util.Iterator;
import java.util.ArrayList;

public class Controls implements KeyListener{
	private ControlPanel cp;
	private SpaceShip ss;	
	private Timer timer;
	private double gameLevel = 0.3;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	

	public Controls(ControlPanel cp, SpaceShip ss){
		this.cp = cp;
		this.ss = ss;		
		cp.shapes.add(ss);										//add SpaceShip

		System.out.println("@ Controls Active");				//test class active

		timer = new Timer(40, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				process();										//start operation
			}
		});
		timer.setRepeats(true);									//set time repeats
	}
	
	public void start(){
		timer.start();
	}

	private void process(){
		if(Math.random() < gameLevel){
			generateEnemy();
		}

		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();										//Let enemy slide down
			if(!e.isAlive()){									//check thread running and remove when not running
				e_iter.remove();
				cp.shapes.remove(e);
			}
		}
		cp.updateGameUI();
	}
		
	void generateEnemy(){										//Create random posision enemy
		Enemy e = new Enemy((int)(Math.random()*
			(cp.getWidthScreen() - (ss.getWidth()/2)))
			,ss.getHeight(), cp.getHieghtScreen());				//Enemy (int x, int y, int heightScreen)
		cp.shapes.add(e);
		enemies.add(e);
	}

	void controlVehicle(KeyEvent e) {							//move controls input by keypad
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			ss.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			ss.move(1);
			break;
		case KeyEvent.VK_D:
			gameLevel += 0.1;
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
