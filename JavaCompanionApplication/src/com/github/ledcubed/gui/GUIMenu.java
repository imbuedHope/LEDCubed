package com.github.ledcubed.gui;

import java.io.PrintStream;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GUIMenu extends JMenuBar {
	
	PrintStream stream;
	
	JMenu file;
	
	public GUIMenu(PrintStream stream) {
		this.stream = stream;
		
		file = new JMenu("File");
		
		add(file);
	}
}
