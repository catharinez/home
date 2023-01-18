/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: Kirby sprites for Kirby Adventures game
 */

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sprite {
	// images for the sprite
	private static BufferedImage spriteImg, basicRightImg, basicLeftImg;
	private static BufferedImage flyRight[];
	private static BufferedImage flyLeft[];
	private static int cnt=0;

	public static void loadImages(){
		// read file with the image for the sprite
		try{
			spriteImg = ImageIO.read(new File("kirbySprite.png")); 
		}catch(Exception e){
			System.out.print("Error: " + e);
		}  
		// get basic images
		basicRightImg=spriteImg.getSubimage(9,161,21,21); 
		basicLeftImg=spriteImg.getSubimage(83,161,21,21);

		// images for when the user flies right
		flyRight= new BufferedImage[3];

		flyRight[1]= spriteImg.getSubimage(9,161,21,21);
		flyRight[2]= spriteImg.getSubimage(33,161,21,21);
		flyRight[0]= spriteImg.getSubimage(57,161,21,21);		

		// images for when the user flies right
		flyLeft= new BufferedImage[3];

		flyLeft[1]= spriteImg.getSubimage(83,161,21,21);
		flyLeft[2]= spriteImg.getSubimage(109,161,21,21);
		flyLeft[0]= spriteImg.getSubimage(132,161,21,21);	
	}

	// methods that return images to draw as the sprite
	public static BufferedImage getNextRight(){
		cnt=(cnt+1)%flyRight.length;
		return flyRight[cnt];
	}

	public static BufferedImage getNextLeft(){
		cnt=(cnt+1)%flyLeft.length;
		return flyLeft[cnt];
	}

	// basic image depends on which direction the user was previously flying
	public static BufferedImage getBasicRightImg(){
		return basicRightImg;
	}
	public static BufferedImage getBasicLeftImg(){
		return basicLeftImg;
	}
}
