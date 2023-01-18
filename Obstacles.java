/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: Obstacles of Kirby game
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Obstacles{
	ImageIcon img;
	int x;
	int y;

	public Obstacles(int x, int y) {
		this.x = x;
		this.y = y;
		img = new ImageIcon("ice.png");
	}


	//setters and getters
	public void setY(int y) { //for moving obstacles with the map
		this.y +=y;
	}

	public void setX(int x) { //tomato
		this.x +=x;
	}
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}

	public void moveOutOfWay() {
		setY(75);
	}

	public void moveObst(int speed) { //move down anf right

		if((int)(Math.random()*2) == 1) { //move left
			x += 1; 
			y += speed;
		}
		else {
			x -= 1;
			y += speed; //move right
		}

	}

	//collisions, moving, and removing
	public Rectangle getRect() {
		return new Rectangle(x, y, 38, 38);
	}

	public boolean isOutOfScreen() {
		if(y>450 || y<=-100)
			return true;
		return false;
	}

	public void checkSpot(boolean isFlyUp) { //realigns obstacles according to map
		if (y%50!=0) {
			if (isFlyUp) {	
				y+=5;
			}
			else
				y-=5;
		}
	}

	//check type
	public boolean isMelon() {
		return false;
	}

	public boolean isTomato() {
		return false;
	}

	public boolean isIceCube() {
		return true;
	}

	public void myDraw(Graphics g) {
		g.drawImage(img.getImage(), x, y, 40, 40, null);
	}



}