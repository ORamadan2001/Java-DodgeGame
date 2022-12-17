package com.ramadan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	static final int WIDTH = 1280, HEIGHT = 720;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("Java Dodge Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Java Dodge Game ");
		title.setFont(new Font("Tahoma", Font.BOLD, 55));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(326, 76, 595, 76);
		contentPane.add(title);
		
		JLabel byLabel = new JLabel("by Omar Ramadan");
		byLabel.setBounds(759, 143, 106, 14);
		contentPane.add(byLabel);
		
		JButton startButton = new JButton("Start the Game.");
		startButton.setFont(new Font("Tahoma", Font.BOLD, 28));
		startButton.setBounds(356, 345, 495, 111);
		startButton.addActionListener(e -> startGame());
		contentPane.add(startButton);
			
	}
	
	private void startGame() {
		new Game();
	}
}
