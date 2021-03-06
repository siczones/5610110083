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
	private int speedUp;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private long score;
	private long maxScore;
	private boolean flag;										//variable for keep game running status
	private boolean mute;
	private AudioLoader audio;

	public Controls(ControlPanel cp, SpaceShip ss){
		this.cp = cp;
		this.ss = ss;
		speedUp = 5;
		cp.shapes.add(ss);										//add SpaceShip
		gameLevel = 0.2;
		score = 0;
		maxScore = 0;
		flag = false;
		mute = false;
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

	public void reset(){
		flag = false;											//is not generate enemy
		score = 0;
		speedUp = 5;
		gameLevel = 0.2;
		System.out.print("+");
	}
		
	private void process(){
		if((Math.random() < gameLevel) && (!flag)){
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

				if((score % 100 == 0) && (score != 0)){
					speedUp += 2;								//speed up when score up == 100
					e.setSpeed(speedUp);
					//System.out.println(speedUp);
					//System.out.println(e.getSpeed());
					if((score % 1000 == 0) && (score != 0))		//level up ever 1000 point
						gameLevel += 0.1;
				}

				//System.out.println(score);
			}

			cp.updateGameUI(this);
			Rectangle2D.Double ssRect = ss.getRectangle();
			Rectangle2D.Double eRect;
			for(Enemy enemy : enemies){
				eRect = enemy.getRectangle();
				if(eRect.intersects(ssRect)){					//game stop when enemy intersect with spaceship
					flag = true;
					if(score > maxScore){
						maxScore = score;
					}
					if(!mute)
						new AudioLoader("audios/die.wav");			//play now audio when die
				}
				else if(flag){
					reset();
				}
					//return;									//exist from process current method
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
			if(!mute)												//play audio when not mute
				new AudioLoader("audios/ding.wav");					//play now audio when spaceship as Vehicle
			break;
		case KeyEvent.VK_RIGHT:
			ss.move(1);
			if(!mute)												//play audio when not mute
				new AudioLoader("audios/ding.wav");					//play now audio when spaceship as Vehicle
			break;
		case KeyEvent.VK_UP:
			ss.move(-2);
			if(!mute)												//play audio when not mute
				new AudioLoader("audios/ding.wav");					//play now audio when spaceship as Vehicle
			break;
		case KeyEvent.VK_DOWN:
			ss.move(2);
			if(!mute)												//play audio when not mute
				new AudioLoader("audios/ding.wav");					//play now audio when spaceship as Vehicle
			break;		
		case KeyEvent.VK_END :
			System.exit(0);										//press key End to exit 
			break;
		case KeyEvent.VK_M:
			mute = !mute;
			break;
		}
	}
	
	public long getScore(){										//get current score
		return score;
	}

	public long getMaxScore(){									//get max score
		return maxScore;
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
