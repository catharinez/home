/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: Scoreboard for Kirby Adventures game
*/

import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
public class StatsP extends JPanel implements ActionListener{
	private JPanel p;
	private ImageIcon scoreBoard, back;
	private JButton backBtn;
	private JLabel showScoreLives;
	private int score, lives;
	private ArrayList<Integer> myScores;
	private Font pixelFont;
	
	public StatsP() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		back= new ImageIcon("back.png");
		backBtn = new JButton(back);
		makeClearButton(backBtn);
		score=0;
		lives = 5;
		
		// import custom pixel font
		try {
		    pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PixelFont.ttf")).deriveFont(12f);
		    GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    g.registerFont(pixelFont);
		} 
		catch (IOException e) {
		    e.printStackTrace();
		} 
		catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		
		showScoreLives = new JLabel("Score: 0  \n Lives: 5   ");
		showScoreLives.setFont(pixelFont);
		add(showScoreLives);
		add(backBtn);
		myScores= new ArrayList<Integer>();
	}
	
	//setters and getters
	public void setScore(int x) {
		score = x;
		showScoreLives.setText("Score: "+score+"  \nLives: "+ lives +"  ");
		
	}
	
	public void setLives(int x) {
		lives = x;
		showScoreLives.setText("Score: "+score+"  \nLives: "+ lives+"   ");
	}
	
	public int getHighScore() {
		int max=0;
		for (int i =0; i< myScores.size(); i++) {
			if (max< myScores.get(i)) {
				max = myScores.get(i);
			}
		}
		return max;
	}
	public void resetScore() {
		myScores.add(score); //add to score array
		setScore(0);
	}
	
	public void paintComponent(Graphics g) {
		scoreBoard= new ImageIcon("scoreBoard.png");
		g.drawImage(scoreBoard.getImage(), 0, 0, 600, 150, null);
	
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backBtn) {
			MainP.cardsL.show(MainP.cont, "menu");
			//go to menu
		}
	}
	
	public void makeClearButton(JButton b) {
		//set appearance of button
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		//listener
		b.addActionListener(this);
	}

}