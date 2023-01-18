/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: Tomato (enemy) for Kirby Adventures game
*/
import java.awt.Graphics;
import javax.swing.ImageIcon;


public class Tomato extends Star{
	private boolean isMoveRight; 
	
	public Tomato(int x, int y) {
		super(x, y);
		img= (new ImageIcon("tomato.gif"));
		isMoveRight=false;
	}
	
	//check type
	public boolean isMelon() {
		return false;
	}
	public boolean isTomato() {
		return true;
	}
	
	public void moveOutOfWay() {
		if (x<378 && isMoveRight)
		    setX(70);
		else
			setX(-70);
	 }
	
	public void moveObst(int speed) { 
		if (x>=448 || x<0)
			isMoveRight=!isMoveRight; //swap direction if moving off platform
		
		if (isMoveRight)
			x += speed;
		else
			x -= speed;
	}
	public void myDraw(Graphics g) {
		g.drawImage(img.getImage(), x+7, y+7, 40, 40, null);
	}
}

