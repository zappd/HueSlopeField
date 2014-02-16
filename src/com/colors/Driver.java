package com.colors;

import javax.swing.*;
import com.colors.ui.*;

public class Driver {
	public static FieldFrame 	 FRAME_1 = null;
	public static HueDialog 	 FRAME_2 = null;
	public static EquationDialog FRAME_3 = null;
	
	public static void main(String[] args) {
		FRAME_1 = new FieldFrame();		
		
		FRAME_1.pack();
		FRAME_1.setVisible(true);
		//FRAME_1.setExtendedState(Frame.MAXIMIZED_BOTH); 
		FRAME_1.setSize(ColorPanel.width + 6,ColorPanel.height + 28);
		FRAME_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME_1.setResizable(false);
		
		FRAME_2 = new HueDialog();
		
		FRAME_2.pack();
		FRAME_2.setSize(505,150);
		FRAME_2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		FRAME_2.setResizable(false);
		
		FRAME_3 = new EquationDialog();
		
		FRAME_3.pack();
		FRAME_3.setSize(400,80);
		FRAME_3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		FRAME_3.setResizable(false);
	}
}