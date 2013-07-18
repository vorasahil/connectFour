package edu.nyu.pqs.ps4.App;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.nyu.pqs.ps4.ConnectFour;

public class GameMenu {
	public static void go(final ConnectFour game){
		final JFrame frame=new JFrame();
		JButton button1=new JButton("Single Player");
		JButton button2=new JButton("Two Player");
		JButton button3=new JButton("Quit");
		 
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ConnectFourApp.singlePlayerGame(game);
				//System.exit(0);
			}
			});
		 
		 button2.addActionListener(new ActionListener() {
			 @Override	
			 public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ConnectFourApp.twoPlayerGame(game);
					//System.exit(0);
			}
		 });
		
		 button3.addActionListener(new ActionListener() {
			 @Override	
			 public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
		 });
		 JPanel inputPanel=new JPanel();
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 inputPanel.setLayout(new BorderLayout());
		 inputPanel.add(button1,BorderLayout.NORTH);
		 inputPanel.add(button2,BorderLayout.CENTER);
		 inputPanel.add(button3,BorderLayout.SOUTH);
		 frame.getContentPane().add(inputPanel, BorderLayout.CENTER);
		 frame.pack();
		 frame.setVisible(true);
	}
	
	
}