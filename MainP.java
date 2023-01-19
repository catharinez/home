/*
Name: Mona Jiang and Catharine Zhou
Class: ICS3U7-1
Teacher: Ms. Strelkovska
Assignment: Final Project
Description: JFrame for Kirby Adventures game
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainP extends JFrame implements ActionListener{
	// variables
	static CardLayout cardsL; //can't be private because we will be calling using other classes
	static Container cont;
	static MenuPanel menuP;
	static PlayP playP;
	private Timer myTimer;

	public MainP(String str) {
		super(str);
		cont = getContentPane();
		cardsL=new CardLayout();
		cont.setLayout(cardsL);
		menuP= new MenuPanel();
		playP= new PlayP();
		
		//credit to simone
		playP.addKeyListener(playP);
		playP.setFocusable(true);

		// add panels to card layout
		cont.add("menu", menuP);
		cont.add("game", playP);


		this.setContentPane(cont);
		cardsL.show(cont, "menu");

		setResizable(false);//prevents user from being able to resize the screen

		myTimer = new Timer(120, this); 
		myTimer.start();
	}

	public static void main(String[] args) {
		MainP javaFrame = new MainP("Kirby Adventures");// my main frame 
		javaFrame.setSize(600, 600);
		javaFrame.setVisible(true);
		javaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == myTimer) { // sends new score from play p to the main menu 
			menuP.setHighScore(playP.getHighScore());
			menuP.setTimesPlayed(playP.getTimesPlayed());
		}
	}


}