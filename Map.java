/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: Map for Kirby Adventures game
 */
import java.awt.*;
import java.io.File;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class Map {
	private int map[][]= new int[12][12];
	private ImageIcon water, land;
	private int upDown;
	private int count, shift;//check to move map
	private boolean isStarlessUp, isStarlessDown;//new rows have no stars



	public Map() {

		//reset();
		try {
			Scanner scFile = new Scanner(new File("map1.txt"));
			for (int i =0; i< 11; i++) {
				String str=scFile.nextLine();
				for (int j=0; j<12; j++) {
					int block=Integer.parseInt(str.substring(j*2, j*2+1)); //skip over the commas
					map[i][j]=block;
				}
			}
			scFile.close();
		}


		catch(Exception e){
			System.out.println("Error: "+e);
		}
		upDown=0;
		shift=1; //map is shifted one up 
	}
	public void reset() {
		try {
			Scanner scFile = new Scanner(new File("map1.txt"));
			for (int i =0; i< 11; i++) {
				String str=scFile.nextLine();
				for (int j=0; j<12; j++) {
					int block=Integer.parseInt(str.substring(j*2, j*2+1)); //skip over the commas
					map[i][j]=block;
				}
			}
			scFile.close();
		}


		catch(Exception e){
			System.out.println("Error: "+e);
		}
		upDown=0;
		shift=1; //map is shifted one up 
	}

	//getters 
	public int getUpDown() {
		return upDown;
	}
	public int getShift() {
		return shift;
	}

	public boolean getStarsUp() { //need to fill stars on top row

		return isStarlessUp;
	}
	public boolean getStarsDown() { //need fill stars bottom row

		return isStarlessDown;
	}
	public String getRow(int row) { // convert row to string for when comparing to tomato row
		String strRow = "";
		for(int i = 0; i < map[0].length; i++) {
			strRow += map[row][i] + " ";
		}

		return strRow;
	}
	public int getRows() {
		return map.length;
	}
	public Rectangle getRect(int x, int y){
		return new Rectangle(x*50,y*50+upDown, 50, 50);   

	} 


	//map movement
	public void setMove(int i) {
		upDown+= i;
		count+=i;
		if (count ==50) { //user moving up by 50
			shiftDown();
			count=0;
			shift+= 1; 
			isStarlessUp=true;  // starless variables keep track of whether or not we need to add new stars

		}
		else if (count == -50) {  //user moving down by 50 
			shiftUp();
			count=0;
			shift-=1;
			isStarlessDown=true;

		}
		else {
			isStarlessDown=false;
			isStarlessUp=false;
		}
	}

	public void shiftUp() {
		int temp[]= map[0]; //top row to the bottom and shift rest up
		for (int i = 0; i< map.length-1; i++) { //row w/ index 1 into 0
			map[i]= map[i+1];
		}
		map[10]=temp;

	}
	public void shiftDown() {//map moving down = player is moving up
		int temp[]= map[10]; //bottom row to the top, shift rest down

		for (int i =map.length-2; i>=0; i--) { //row w/ index 9 -->10
			map[i+1]= map[i]; 
		}
		map[0]=temp;
	}


	public boolean isLand(int x, int y) {
		if (map[x][y] == 1) {
			return true;
		}
		return false;
	}

	public boolean isTomatoRow(String strRow) { //special row for tomato spawning
		if (strRow.equals("0 0 0 0 0 0 0 0 0 0 0 1 ")) { // checks if the string ver of the row is equal to the tomato row
			return true;
		}
		return false;
	}



	public void drawMap(Graphics g){

		water= new ImageIcon("water.gif");
		land= new ImageIcon("land.png");

		for (int i =0; i< map.length;i++) { //fill map from up to down, left to right
			for (int j =0; j<map[0].length; j++) {
				if (map[i][j]==0) {
					g.drawImage(water.getImage(),j*50,(i - shift)*50+upDown,50,50, null); 
				}
				else {
					g.drawImage(land.getImage(),j*50,(i - shift)*50+upDown,50,50, null); 

				}
			}
		}

	}	   
}