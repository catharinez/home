/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: PlayP for Kirby Adventures game
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class PlayP extends JPanel implements KeyListener, ActionListener{
	// variables
	private Timer myTimer;
	private JButton b;
	private User user;
	private Map map;
	private ArrayList<Obstacles> obstacles;
	private int num;
	private int counter;
	private StatsP myStats;
	private boolean isPlaying, isFlyUp;
	private int timesPlayed;
	

	public PlayP() { // constructor 
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
		setLayout(new BorderLayout()); // border layout
		myStats= new StatsP();
		add(myStats, BorderLayout.SOUTH);
		user = new User(0,300);

		Sprite.loadImages();
		myTimer = new Timer(120, this); 
		myTimer.start();
		setFocusable(true);
		map = new Map();

		obstacles = new ArrayList<Obstacles>(); //initial obstacles first on screen
		counter = 0; //count time
		resetObstacles();

		timesPlayed = 0;
		isPlaying=false;
	}

	// drawing the map, user, and obstacles
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		map.drawMap(g); 
		user.myDraw(g);

		for(int i = 0; i < obstacles.size();i++) {
			obstacles.get(i).myDraw(g);
		}
	}

	// resetting obstacles when user dies
	public void resetObstacles() {
		for(int i = 0; i < obstacles.size(); i=0) { // remove all obstacles before adding new ones
			obstacles.remove(i);
			System.out.println(obstacles.size());

		}

		for(int i = 0; i < 3; i++) { // spawn a few ice cubes at first so the user can't just run past the first row 
			obstacles.add(new Obstacles((int)(Math.random()*461)+50, 125));
		}

		for (int i =0; i<12; i++) {
			for (int j=1; j<map.getRows()-1; j++) { //solves overlap
				if ((int)(Math.random()*2+1)%2==0 &&!map.isLand(j,i) && !(j==7 && i==0) && j!=3) { //make stars spawn randomly but not where kirby spawns
					if ((int)(Math.random()*2+1)%2!=0){ 
						obstacles.add(new Star(i*50, (j-1)*50));
					}
					else {
						obstacles.add(new Melon(i*50, (j-1)*50));
					}
				}
			}
		}
		obstacles.add(new Tomato(0, 2*50)); // spawn tomato in the same row everytime the map resets

	}

	public boolean hitLand() { // check if user will collide with land

		for (int i =0; i<12; i++) {
			for (int j=0; j<map.getRows(); j++) { 
				if (map.isLand(j,i)) {
					if (user.getUserMoveRect().intersects(map.getRect(i,j-map.getShift()))) { // checks if user rect intersects land for collisions 
						return true;
					}

				}
			}
		}
		return false;
	}

	public void addStars(int row) { // add stars for when map is reset or when new rows are loaded
		for (int i =0; i<12; i++) {
			if ((int)(Math.random()*2+1)%2!=0 &&!map.isLand(row,i)) { 
				if ((int)(Math.random()*3+1)%3!=0){
					obstacles.add(new Star(i*50, (row-1)*50));
				}
				else {
					obstacles.add(new Melon(i*50, (row-1)*50));
				}
			}
		}
	}

	public void addTomato(int row) { //adding tomato whenever new rows get added to the map
		Obstacles enemy= new Tomato(0, (row-1)*50);
		obstacles.add(enemy);
	}

	public void actionPerformed(ActionEvent e) {
		//everytime the timer ticks
		if (e.getSource()==myTimer){

			//prevents user from moving into land
			if (hitLand()) {
				user.setStay();
			}
			map.setMove(-user.getVert()); // moves map as user moves up or down
			counter++;

			if(counter%15 == 0) {
				obstacles.add(new Obstacles((int)(Math.random()*561), -3));//x coordinate of obstacle to be dropped 
			}

			//collisions, obstacles, resetting 

			for(int i = 0; i < obstacles.size();i++) {
				Obstacles ob = obstacles.get(i); //pointer

				//collisions, moving and removing obstacles
				if(ob.isIceCube()) {
					if  (user.getVert()>=0) //make ice cube move faster if user if moving up, prevents ice cube from going up
						ob.moveObst(4);
					else
						ob.moveObst(10);
				}

				else { //not an ice cube
					ob.setY(-user.getVert()); //move stars with the map
					if (map.getUpDown()%50==0)
						ob.checkSpot(isFlyUp);
				}

				if (ob.isTomato()) { // make tomato move back and forth
					ob.moveObst(7);
				}


				if(ob.getRect().intersects(user.getRect())) { // check if user collides with obstacles
					if(isPlaying &&(ob.isIceCube() || ob.isTomato())) { 

						if(user.getLives() - 1<= 0) {//part of lose lives
							myStats.resetScore();
							map=new Map();
							resetObstacles();
							user=new User(0,300);
							timesPlayed++;
							myStats.setLives(user.getLives()); // updates lives on scoreboard
							counter=0;
							isPlaying = false;
							break;
						}
						user.loseLife();						
						myStats.setLives(user.getLives());
						obstacles.get(i).moveOutOfWay(); //adjusts y / x out of the players path, preventing the player from losing multiple lives at once

					}
					else {

						if (ob.isMelon()) {
							user.addLife();//add a life
							myStats.setLives(user.getLives());
						}
						
						else if (!ob.isTomato() && !ob.isIceCube()){//is star
							user.addScore(); //add score
						}
						
						if (!ob.isTomato() && !ob.isIceCube())
							obstacles.remove(i); //remove stars or watermelon

						myStats.setScore(user.getScore()); 
					}

				}
				if(ob.isOutOfScreen()) { //remove stars and ice out of screen
					obstacles.remove(i);
				}

			}

			if (map.getStarsUp()) { //if a new row is getting added at the top, add to the first row

				if (map.isTomatoRow(map.getRow(0))) {
					addTomato(0);
				}
				else 
					addStars(0); 
			}
			if (map.getStarsDown()) { // if a new row is getting added at the bottom, add to the last row
				if (map.isTomatoRow(map.getRow(10))) {
					addTomato(10);
				}
				else 
					addStars(10);
			}

			//move user and repaint
			user.move();
			repaint();



		}
	}


	public void keyPressed( KeyEvent e ){  // allows user/map to move when a key is pressed
		isPlaying = true; //player has started game
		if(KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {
			user.setLeft();
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {
			user.setDown();
			isFlyUp=false;

		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {
			user.setUp();
			isFlyUp=true;

		} 
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {
			user.setRight();
		}
	}

	public void keyReleased( KeyEvent e ) {   
		user.setStay(); // stops user from moving once the key is released
	}
	public void keyTyped( KeyEvent e )   {   }

	//send to main
	public int getHighScore (){
		return myStats.getHighScore(); // sends score for high score JOptionPane
	}

	public int getTimesPlayed() { //keeps track of how many times the user has played
		return timesPlayed;
	}
}