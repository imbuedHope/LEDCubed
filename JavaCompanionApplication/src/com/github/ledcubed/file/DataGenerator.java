package com.github.ledcubed.file;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.github.ledcubed.Main;
import com.github.ledcubed.animation.Sequence;

public class DataGenerator {
	
	public static final String FILE_END = ".txt";
	
	Sequence data;
	String location;
	
	public DataGenerator(Sequence dat, String directory) {
		data = dat;
		location = directory;
	}
	
	public void generate() {
		
		int fileNum = 0;
		
		for(int t = data.getMinTime(); t < data.getMaxTime(); t++, fileNum++) {
			
			try {
				PrintWriter writer = new PrintWriter(new File(location+fileNum+FILE_END));
				
				for(int x = 0; x < Main.CubeSideX; x++) {
					for(int y = 0; y < Main.CubeSideY; y++) {
						for(int z = 0; z < Main.CubeSideZ; z++) {
							Color c = data.evaluate(x, y, z, t);
							writer.println(x+", "+y+", "+", "+z+", "+c.getRed()+", "+c.getGreen()+", "+c.getBlue()+";");
						}
					}
				}
				
				writer.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace(System.err);
			}
		}
		
	}
	
}
