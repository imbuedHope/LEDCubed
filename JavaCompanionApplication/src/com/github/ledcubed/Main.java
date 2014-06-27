package com.github.ledcubed;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.github.ledcubed.gui.GUIFrame;
import com.github.ledcubed.gui.GUIMenu;

/**
 * Launch class
 * @author imbuedHope
 * 
 */
public class Main {
	//THESE ARE DEFAULT VALUES TO MAKE CHANGES CHANGE THE `led.conf` FILE
	public static int CubeSideX = 8;
	public static int CubeSideY = 8;
	public static int CubeSideZ = 8;

	public static int FrameGenerationRate = 500;
	
	public static void main(String[] args) {
		
		//TODO: add override commands from the commandline
		
		//Override Default Values with values from `led.conf` file
		try {
			InputStream is = Main.class.getResourceAsStream("led.conf");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			String in = null;
			while((in = reader.readLine()) != null) {
				if(in.contains("#")) {
					in = in.substring(0, in.indexOf("#"));
				}
				
				if(!in.isEmpty()) {					
					if(in.contains("=")) {
						in.replaceAll("\\s", "");
						String[] inp = in.split("=");
						try {
						
							switch(inp[0]) {
							case "CubeSideX":
								Main.CubeSideX = Integer.parseInt(inp[1]);
							case "CubeSideY":
								Main.CubeSideY = Integer.parseInt(inp[1]);
							case "CubeSideZ":
								Main.CubeSideZ = Integer.parseInt(inp[1]);
							case "FrameGenerationRate":
								Main.FrameGenerationRate = Integer.parseInt(inp[1]);
						}
						
						} catch(Exception e) {
							e.printStackTrace(System.err);
						}
					}
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		//Set to OS based L&F
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		//Set GUI to launch later and quit
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationByPlatform(true);
				frame.setTitle("LED Cube Manager");
				
				GUIFrame panel = new GUIFrame(CubeSideX, CubeSideY, CubeSideZ, FrameGenerationRate);
				
				frame.add(panel);
				frame.setJMenuBar(new GUIMenu(panel.stream));
				
				frame.setResizable(false);
				frame.setVisible(true);
				frame.pack();
			}
		});
	}
}
