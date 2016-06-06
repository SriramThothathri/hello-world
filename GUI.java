package project;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private static ArrayList<String> deadVerbs;
	static String essayraw;
	JTextField txtdata;
	JButton calbtn1 = new JButton("Check Essay for Spelling");
	JButton calbtn = new JButton("Check Essay for Dead Verbs");
	JTextField myOutput = new JTextField(16);
	static HashSet<String> dictionary = new HashSet<String>();
	static File file = new File("Z:/AP COMP SCI/Unit 6/src/project/AllWords.txt");

	public static boolean spellCheck(String xasd) {
		boolean x1 = false;
		String x = xasd;
		x = x.toLowerCase();
		if (dictionary.contains(x))
			x1 = true;
		return x1;
	}

	/*
	 * public static String checkEssay() { String x = ""; String[] split =
	 * essayraw.split(" "); for (int i = 0; i < split.length; i++) { for (int j
	 * = 0; j < dictionary.size(); j++) { if
	 * (split[i].substring(split[i].length() - 1).equals(",")) { if
	 * (split[i].substring(0, split[i].length() -
	 * 1).equalsIgnoreCase(dictionary.get(j))) { if
	 * (!(GUI.spellCheck(split[i]))) { split[i] = "||" + split[i].substring(0,
	 * split[i].length() - 1) + "||" + ","; }
	 * 
	 * } } if (split[i].substring(split[i].length() - 1).equals(".")) { if
	 * (split[i].substring(0, split[i].length() -
	 * 1).equalsIgnoreCase(dictionary.get(j))) { if
	 * (!(GUI.spellCheck(split[i]))) { split[i] = "||" + split[i].substring(0,
	 * split[i].length() - 1) + "||" + "."; }
	 * 
	 * } } else if (split[i].equalsIgnoreCase(dictionary.get(j))) { if
	 * (!(GUI.spellCheck(split[i]))) { split[i] = "||" + split[i] + "||"; } } }
	 * }
	 * 
	 * for (int i = 0; i < split.length; i++) { x += split[i] + " "; } return x;
	 * }
	 */

	public static String search(String key) {
		int count = 0;
		String x = "Spelling Errors Found: ";
		String[] split = essayraw.split(" ");
		for (int i = 0; i < split.length; i++) {
			if (split[i].substring(split[i].length() - 1).equals(",")) {
				if(split[i].substring(0, split[i].length() - 1).equals(key)){
					split[i] = "|" + split[i].substring(0, split[i].length() - 1) + "||" + ",";
				}
			} else if (split[i].substring(split[i].length() - 1).equals(".")) {
				if((((split[i].substring(split[i].length() - 1)).equals((key))))){
					split[i] = "||" + split[i].substring(0, split[i].length() - 1) + "||" + ".";
				}
			}
		}
		for (int i = 0; i < split.length; i++) {
			x += split[i] + " ";
		}
		return x;
	}

	public static String checkEssay() {
		String x =  "Spelling Errors Found: ";
		String[] split = essayraw.split(" ");
		for (int i = 0; i < split.length; i++) {
			if (split[i].substring(split[i].length() - 1).equals(",")) {
				if (!GUI.spellCheck(split[i])){
					split[i] = "|" + split[i].substring(0, split[i].length() - 1) + "||" + ",";
				}
			} else if (split[i].substring(split[i].length() - 1).equals(".")) {
				if (!GUI.spellCheck(split[i])) {
					split[i] = "||" + split[i].substring(0, split[i].length() - 1) + "||" + ".";
				}
			} else if (!GUI.spellCheck(split[i])){
				split[i] = "||" + split[i] + "||";
			}
		}
		for (int i = 0; i < split.length; i++) {
			x += split[i] + " ";
		}
		return x;
	}

	@SuppressWarnings("static-access")
	public GUI() {
		JPanel myPanel = new JPanel();
		add(myPanel);
		myPanel.setLayout(new GridLayout(3, 2));
		myPanel.add(calbtn);
		myPanel.add(calbtn1);
		calbtn.addActionListener(this);
		calbtn1.addActionListener(this);
		txtdata = new JTextField();
		myPanel.add(txtdata);
		this.deadVerbs = new ArrayList<String>(Arrays.asList("very", "really", "lot", "lots", "well", "so", "good",
				"bad", "nice", "cute", "great", "pretty", "small", "big", "fine", "fun", "thing", "something",
				"anything", "things", "everything", "nothing", "stuff", "nobody", "anybody", "everybody", "somebody",
				"someone", "anyone", "everyone", "I", "me", "my", "mine", "our", "ours", "we", "we'd", "us", "you",
				"yours", "you're", "your", "become", "get", "prove", "display", "exhibit", "seem", "show", "possess",
				"portray", "happen", "contain", "is", "was", "are", "were", "be", "being", "been", "do", "does", "did",
				"didn't"));
		myPanel.add(myOutput);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == calbtn) {
			String data = txtdata.getText();
			essayraw = data;
			myOutput.setText(GUI.deadVerbs());
		}
		if (e.getSource() == calbtn1) {
			String data = txtdata.getText();
			essayraw = data;
			myOutput.setText(GUI.checkEssay());
		}
	}

	public static String deadVerbs() {
		String x = " Dead Verbs Identified: ";
		String[] split = essayraw.split(" ");
		for (int i = 0; i < split.length; i++) {
			for (int j = 0; j < deadVerbs.size(); j++) {
				if (split[i].substring(split[i].length() - 1).equals(",")) {
					if (split[i].substring(0, split[i].length() - 1).equalsIgnoreCase(deadVerbs.get(j))) {
						split[i] = "|" + split[i].substring(0, split[i].length() - 1) + "|" + ",";
					}
				}
				if (split[i].substring(split[i].length() - 1).equals(".")) {
					if (split[i].substring(0, split[i].length() - 1).equalsIgnoreCase(deadVerbs.get(j))) {
						split[i] = "|" + split[i].substring(0, split[i].length() - 1) + "|" + ".";
					}
				} else if (split[i].equalsIgnoreCase(deadVerbs.get(j))) {
					split[i] = "|" + split[i] + "|";
				}
			}
		}

		for (int i = 0; i < split.length; i++) {
			x += split[i] + " ";
		}
		return x;
	}

	public static void main(String args[]) throws IOException {
		GUI g = new GUI();
		g.setLocation(10, 10);
		g.setSize(300, 300);
		g.setVisible(true);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				// process the line.
				dictionary.add(line);
			}
		}
	}
}
