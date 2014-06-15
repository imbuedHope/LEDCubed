package com.github.ledcubed.animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class EqnSequence extends Sequence {
	
	private ArrayList<String> R = new ArrayList<>();
	private ArrayList<String> G = new ArrayList<>();
	private ArrayList<String> B = new ArrayList<>();
	
	public EqnSequence(String[] str) {
		super();
		//TODO: Convert str which is read from a .eqn file into to a working eqn variable of sorts
		//There's a sample stored under the anim folder
		//The input will be in the format of "R = ???"
		
		//Presuming that the syntax is correct...
		//TODO: Syntax check / error toss
		
		for(int i = 0; i < str.length; i++) {
			
			str[i] = str[i].replaceAll("\\s","");
			str[i] = str[i].toUpperCase();
			
			if(str[i].startsWith("R=")) {
				splice(str[i].substring(2), R);
			} else if (str[i].startsWith("G=")) {
				splice(str[i].substring(2), G);
			} else if (str[i].startsWith("B=")) {
				splice(str[i].substring(2), B);
			}
			
		}
		
		//TODO: Remove print statement once no longer needed
		System.out.println(R + "\n" + G + "\n" + B);
		
	}
	
	private void splice(String dat, ArrayList<String> store) {
		
		String tmp = "";
		boolean complete = false;
		char d = 0;
		
		for(int i = 0; i < dat.length(); i++) {
			char c = dat.charAt(i);
			
			if(i+1 < dat.length())
				d = dat.charAt(i+1);
			tmp += c;
			
			complete = 	(c == '(' || c ==')' || c == '%' || c == '*' || c == '/' || c == '+' || c == '-') ||
						(d == '(' || d ==')' || d == '%' || d == '*' || d == '/' || d == '+' || d == '-') ||
						(i+1 == dat.length());
			
			if(complete) {
				store.add(tmp);
				complete = false;
				tmp = "";
			}
			
			d = 0;
		}
	}

	@Override
	public Color getNext() {
		return null;
	}
	
	//TODO: Change to private once done with testing
	@SuppressWarnings("unchecked")
	public Color evaluate(double x, double y, double z, double t) {
		double val[] = { x, y, z, t};
		String chars[] = { "X", "Y", "Z", "T"};
		
		ArrayList<String> R_ = (ArrayList<String>) R.clone();
		ArrayList<String> G_ = (ArrayList<String>) G.clone();
		ArrayList<String> B_ = (ArrayList<String>) B.clone();
		
		for(int i = 0; i < chars.length; i++) {
			int j = R_.indexOf(chars[i]);
			if(j > 0) {
				R_.remove(j);
				R_.add(j, ""+val[i]);
			}
		}
		
		for(int i = 0; i < chars.length; i++) {
			int j = G_.indexOf(chars[i]);
			if(j > 0) {
				G_.remove(j);
				G_.add(j, ""+val[i]);
			}
		}
		
		for(int i = 0; i < chars.length; i++) {
			int j = B_.indexOf(chars[i]);
			if(j > 0) {
				B_.remove(j);
				B_.add(j, ""+val[i]);
			}
		}
		
		//TODO: Remove print statement once no longer needed
		System.out.println(R_ + "\n" + G_ + "\n" + B_);
		
		double r = evaluate(R_);
		double g = evaluate(G_);
		double b = evaluate(B_);
		
		System.out.println(r + "\n" + g + "\n" + b);
		
		return null;
	}
	
	private double evaluate(List<String> list) {
		int ind = -1;
		
		if(list.contains("(")) {
			int indfinal = -1;
			int temp = 0;
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).equals("(") && ind < 0) {
					ind = i;
				} else if(list.get(i).equals("(")) {
					temp++;
				} else if(list.get(i).equals(")") && temp > 0) {
					temp--;
				} else if(list.get(i).equals(")")) {
					indfinal = i;
					break;
				}
			}
			
			double val = evaluate(list.subList(ind+1, indfinal));
			List<String> tmp;
			
			if(ind > 0) {
				if(indfinal < list.size()-1) {
					//...(...)...
					tmp = list.subList(0, ind);
					tmp.add(""+val);
					tmp.addAll(list.subList(indfinal+1, list.size()-1));
					return evaluate(tmp);
				} else {
					//...(...)
					tmp = list.subList(0, ind);
					tmp.add(""+val);
					return evaluate(tmp);
				}
			} else {
				if(indfinal < list.size()-1) {
					//(...)...
					tmp = list.subList(indfinal+1, list.size()-1);
					tmp.add(0, ""+val);
					return evaluate(tmp);
				} else {
					//(...)
					return val;
				}
			}
		}
		
		ind = list.indexOf("-");
		if(ind > 0) {
			return evaluate(list.subList(0, ind))-evaluate(list.subList(ind+1, list.size()));
		}
		
		ind = list.indexOf("+");
		if(ind > 0) {
			return evaluate(list.subList(0, ind))+evaluate(list.subList(ind+1, list.size()));
		}
		
		ind = list.indexOf("*");
		if(ind > 0) {
			return evaluate(list.subList(0, ind))*evaluate(list.subList(ind+1, list.size()));
		}
		
		ind = list.indexOf("/");
		if(ind > 0) {
			return evaluate(list.subList(0, ind))/evaluate(list.subList(ind+1, list.size()));
		}
		
		ind = list.indexOf("%");
		if(ind > 0) {
			return evaluate(list.subList(0, ind))%evaluate(list.subList(ind+1, list.size()));
		}

		return Double.parseDouble(list.get(0));
	}
	
	/**
	 * Test Run for class
	 * TODO: Remove from code when no longer needed
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello world!");
		String[] test = { 	"R = 4 * 4 + y + t + X",
							"G = 2 * (23 + 13)",
							"B = 23 * 123 +23",};
		
		new EqnSequence(test).evaluate(0, 1, 2, 3);
		
	}
}
