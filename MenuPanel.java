/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: Main menu panel for Kirby Adventures game
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
public class MenuPanel extends JPanel implements ActionListener{
	// variables
	private JButton game;
	private JButton instructions;
	private JButton highScoreB;
	private JButton exit;
	private ImageIcon back;
	private JLabel bg;
	private static ImageIcon startGame, highS, instruct, leave, inst;
	private Font pixelFont;
	private int highScore, timesPlayed;

	public MenuPanel() {

		this.setLayout(null);//using custom layout
		startGame = new ImageIcon("startGame.png");
		game = new JButton(startGame); // button for playing the game
		makeClearButton(game);
		instruct = new ImageIcon("instructions.png");
		instructions = new JButton(instruct); // button for instructions
		makeClearButton(instructions);

		highS= new ImageIcon("highScore.png");
		highScoreB = new JButton(highS); // button for high score
		makeClearButton(highScoreB);

		leave= new ImageIcon("exit.png");
		exit = new JButton(leave);  // exit button
		makeClearButton(exit);

		highScore = 0;
		timesPlayed = 0;

		game.setBounds(75, 200, 200, 30); //set bounds of each button
		instructions.setBounds(300, 200, 250, 30); 
		highScoreB.setBounds(75, 300, 200, 30); 
		exit.setBounds(325, 300, 200, 35); 

		//ADDED ACTION LISTENER IN METHOD
		back = new ImageIcon("background.gif");
		inst = new ImageIcon("inst.png");
		bg= new JLabel(back);
		bg.setBounds(0,0, 600, 600);
		add(bg);

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

		// set pixel font for JOptionPane
		UIManager.put("OptionPane.messageFont", pixelFont);
		UIManager.put("OptionPane.buttonFont", pixelFont);

	}
	public void paintComponent(Graphics g) {//no double buffering --> double buffering: java creates off screen image and replaces entire screen
		super.paintComponent(g);
		g.setFont(new Font("SansSerif", Font.ITALIC, 30));
		g.drawString("KIRBY ADVENTURES", 150, 150);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==game) {
			
			MainP.cardsL.show(MainP.cont, "game");//MainP is class --> call static variable container 
			MainP.playP.setFocusable(true);
			MainP.playP.requestFocus();
		}
		
		else if(e.getSource()==instructions) {
			//display instructions using a JOptionPane
			JOptionPane.showMessageDialog(null, "Instructions\n\nThe Kirby character can be moved using\nthe following keys: A, W, S, D.\nTo move up press W.\nTo move down press S.\nTo move left press A.\nTo move right press D.\n\nThe main objective in this game is to\ncollect stars while avoiding obstacles.\nYou are given 5 lives before Kirby dies.\nGetting hit by an obstacle will cause\nyou to lose a life.\nEach star collected adds 50 points to\nyour score.\nWatermelons will spawn around the map as\npower ups, they provide an extra life each.\nAnother obstacle to avoid are tomatoes.\nRunning into a tomato takes away a life.", "Instructions", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("spinningKirby.gif"));
		}
		else if(e.getSource()==highScoreB) {
			// display high score (no high score if user has not played yet)
			if(timesPlayed == 0)
				JOptionPane.showMessageDialog(null, "You have no high score :(", "High Score", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("spinningKirby.gif"));
			else
				JOptionPane.showMessageDialog(null, "High score: " + highScore, "High Score", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("spinningKirby.gif"));
		}
		else if(e.getSource()==exit) {
			JOptionPane.showMessageDialog(null, "Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("spinningKirby.gif"));
			System.exit(0); 
		}


	}

	public void makeClearButton(JButton b) {
		//set appearance
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		//add action listener
		b.addActionListener(this);
		this.add(b);//add button
	}

	public void setHighScore(int s) { //set high score for displaying in JOptionPane
		highScore = s;
	}

	public void setTimesPlayed(int i) {
		timesPlayed = i;
	}

}
