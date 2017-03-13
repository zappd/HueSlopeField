package com.zappd.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.zappd.*;

public class PopupListener implements ActionListener {
	
	public PopupListener() {
		/***/
	}

	public void actionPerformed(ActionEvent event) {
		
		   if (event.getActionCommand() == "Hue Adjust") {
			   Driver.FRAME_2.setVisible(true);
		   }
		   
		   else if (event.getActionCommand() == "Exit") {
			   System.exit(0);
		   }
		   
		   else if (event.getActionCommand() == "Reset") {
			   ColorPanel.scale = 1.0/80.0;
			   Angle.woffset = 0;
			   Angle.hoffset = 0;
			   Driver.FRAME_1.repaint();
		   }
		   
		   else if (event.getActionCommand() == "Set Equation") {
			   Driver.FRAME_3.setVisible(true);
		   }

		 }
}
