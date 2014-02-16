package com.colors.ui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.colors.*;

public class SliderListener implements ChangeListener {

	public SliderListener() {}
	
	public void stateChanged(ChangeEvent e) {
		
		if (e.getSource() == HueDialog.LB_SLIDE) {
			if (!HueDialog.LB_SLIDE.getValueIsAdjusting()) {
				Angle.lbound = ((float) HueDialog.LB_SLIDE.getValue())/360.0f;
				Driver.FRAME_1.repaint();
			
			}
			
			HueDialog.FIELD_1.setText(Integer.toString(HueDialog.LB_SLIDE.getValue()));
		
		}
		
		else if (e.getSource() == HueDialog.UB_SLIDE) {
			if (!HueDialog.UB_SLIDE.getValueIsAdjusting()) {
				Angle.ubound = (360.0f - (float) HueDialog.UB_SLIDE.getValue())/360.0f;
				Driver.FRAME_1.repaint();
			
			}
			
			HueDialog.FIELD_2.setText(Integer.toString(360 - HueDialog.UB_SLIDE.getValue()));
		
		}
	}
}
