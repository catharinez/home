/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: Star obstacles for Kirby Adventures game
*/

import java.awt.Graphics;

import javax.swing.ImageIcon;


public class Star extends Obstacles{
	
	public Star(int x, int y) {
		super(x, y);
		img= (new ImageIcon("purpleStar.gif"));
	}
	
	//checking type of obstacle
	public boolean isIceCube() {
		return false;
	}
	
	public void myDraw(Graphics g) {
		g.drawImage(img.getImage(), x+7, y+7, 30, 30, null);
	}

}