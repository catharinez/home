/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: Melon (life) for Kirby Adventures game
 */
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Melon extends Star{
	public Melon(int x, int y) {
		super(x, y);
		img= (new ImageIcon("melon.gif"));
	}

	//check type
	public boolean isMelon() {
		return true;
	}
	public boolean isTomato() {
		return false;
	}
	
}
