import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

import java.util.Iterator;
import java.util.ArrayList;

public class Controls implements KeyListener, Score{
	private ControlPanel cp;
	private SpaceShip ss;	
	private Timer timer;
	private double gameLevel;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private long score;

	public Controls(ControlPanel cp, SpaceShip ss){
		this.cp = cp;
		this.ss = ss;		
		cp.shapes.add(ss);										//add SpaceShip
		gameLevel = 0.2;
		score = 0;

		System.out.println("@ Controls Active");				//test class active

		timer = new Timer(40, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				process();										//start operation
			}
		});
		timer.setRepeats(true);									//set time repeats
	}
	
	public void start(){										//start game
		timer.start();
	}

	public void stop(){											//stop game
		timer.stop();
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
				score+=5;										//Score up 5 point per time
				//System.out.println(score);
			}

			cp.updateGameUI(this);
			Rectangle2D.Double ssRect = ss.getRectangle();
			Rectangle2D.Double eRect;
			for(Enemy enemy : enemies){
				eRect = enemy.getRectangle();
				if(eRect.intersects(ssRect)){					//game stop when enemy intersect with spaceship
					stop();
					return;										//exist from process current method
				}
			}
		}
		
	}
		
	void generateEnemy(){										//Create random posision enemy
		Enemy e = new Enemy((int)(Math.random()*
			(cp.getWidthScreen() - (ss.getWidth()/2)))
			,ss.getHeight(), cp.getHieghtScreen());				//Enemy (int x, int y, int heightScreen)
		cp.shapes.add(e);
		enemies.add(e);
	}

	void controlVehicle(KeyEvent e) {							//define move controls from input (by keypad)
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			ss.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			ss.move(1);
			break;
		case KeyEvent.VK_UP:
			ss.move(-2);
			break;
		case KeyEvent.VK_DOWN:
			ss.move(2);
			break;

		case KeyEvent.VK_D:
			gameLevel += 0.1;
			break;
		}
	}
	
	public long getScore(){										//get current score
		return score;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);										//check Vehicle condition when key pressed
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
