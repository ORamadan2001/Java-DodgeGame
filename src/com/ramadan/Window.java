package com.ramadan;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 1801082051932126532L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		Dimension dims = new Dimension(width, height);
		frame.setPreferredSize(dims);
		frame.setMinimumSize(dims);
		frame.setMaximumSize(dims);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	
}
