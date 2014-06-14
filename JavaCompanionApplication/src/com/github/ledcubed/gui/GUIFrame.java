package com.github.ledcubed.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

public class GUIFrame extends JPanel {
	static final int XLEN = 800;
	static final int YLEN = 450;
	
	public GUIFrame(int cubeSideX, int cubeSideY, int cubeSideZ, int frameGenerationRate) {
		this.setPreferredSize(new Dimension(XLEN, YLEN));
		//TODO: Desgin a GUI to control the system in a comfortable way
	}

	private static final long serialVersionUID = -2167912679009145686L;

}
