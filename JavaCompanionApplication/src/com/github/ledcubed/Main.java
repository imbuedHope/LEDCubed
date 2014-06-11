package com.github.ledcubed;

import javax.swing.JFrame;

import com.github.ledcubed.gui.GUIFrame;

/**
 * Launch class
 * @author imbuedHope
 * 
 */
public class Main {
	
	static int CubeSideX = 8;
	static int CubeSideY = 8;
	static int CubeSideZ = 8;

	static int FrameGenerationRate = 500;
	
	public static void main(String[] args) {
		//TODO: Load values from `.conf` file in place of hardcoded values
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationByPlatform(true);
				frame.setTitle("LED Cube Manager");

				frame.add(new GUIFrame(CubeSideX, CubeSideY, CubeSideZ, FrameGenerationRate));

				frame.setResizable(false);
				frame.setVisible(true);
				frame.pack();
			}
		});
	}
}
