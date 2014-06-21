package com.github.ledcubed.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.github.ledcubed.comm.CommManager;

public class GUIFrame extends JPanel implements ActionListener {
	static final Dimension TEXT_AREA = new Dimension(500, 300);
	static final Dimension BUTTONS = new Dimension(40, 40);
	
	private PrintStream stream;
	private JTextArea console;
	
	private JButton play;
	private JButton nextAnim;
	private JButton prevAnim;
	private JButton nextAfter;
	private JButton prevAfter;
	
	private CommManager serial;
	
	public GUIFrame(int cubeSideX, int cubeSideY, int cubeSideZ, int frameGenerationRate) {
		//setPreferredSize(new Dimension(XLEN, YLEN));

		console = new JTextArea();
		console.setLineWrap(true);
		console.setEditable(false);

		
		stream = new PrintStream(new OutputStream(){
			@Override
			public void write(int arg) throws IOException {
				String c = ""+(char) arg;
				console.append(c);
			}
		});
		
		JScrollPane pane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(TEXT_AREA);
		
		setLayout(new BorderLayout());
		add(pane, BorderLayout.NORTH);
		//add(console, BorderLayout.NORTH);
		
		play = new JButton();
		nextAnim = new JButton();
		prevAnim = new JButton();
		nextAfter = new JButton();
		prevAfter = new JButton();
		
		play.setToolTipText("Play");
		nextAnim.setToolTipText("Next Animation");
		prevAnim.setToolTipText("Previous Animation");
		nextAfter.setToolTipText("Move to Next After Animation");
		prevAfter.setToolTipText("Move to Previous After Animation");
		
		play.setActionCommand("play");
		nextAnim.setActionCommand("nextAnim");
		prevAnim.setActionCommand("prevAnim");
		nextAfter.setActionCommand("nextAfter");
		prevAfter.setActionCommand("prevAfter");
		
		play.setPreferredSize(BUTTONS);
		nextAnim.setPreferredSize(BUTTONS);
		prevAnim.setPreferredSize(BUTTONS);
		nextAfter.setPreferredSize(BUTTONS);
		prevAfter.setPreferredSize(BUTTONS);
		
		play.addActionListener(this);
		nextAnim.addActionListener(this);
		prevAnim.addActionListener(this);
		nextAfter.addActionListener(this);
		prevAfter.addActionListener(this);
		
		JPanel holder = new JPanel();
		holder.setLayout(new FlowLayout());
		holder.add(prevAnim);
		holder.add(prevAfter);
		holder.add(play);
		holder.add(nextAfter);
		holder.add(nextAnim);
		
		add(holder, BorderLayout.SOUTH);
		
		serial = new CommManager(stream);
	}

	private static final long serialVersionUID = -2167912679009145686L;

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "play":
			console.append(">Ctrl\t: Requesting display to play\n");
			serial.play();
			break;
		case "nextAnim":
			console.append(">Ctrl\t: Requesting display to skip forward\n");
			serial.nextAnim();
			break;
		case "prevAnim":
			console.append(">Ctrl\t: Requesting diplay to skip backward\n");
			serial.prevAnim();
			break;
		case "nextAfter":
			console.append(">Ctrl\t: Requesting display to skip forward later\n");
			serial.nextAfter();
			break;
		case "prevAfter":
			console.append(">Ctrl\t: Requesting diplay to skip backward later\n");
			serial.prevAfter();
			break;
		}
	}

}
