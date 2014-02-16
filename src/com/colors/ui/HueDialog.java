package com.colors.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.colors.*;

public class HueDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JSlider LB_SLIDE;
	static JSlider UB_SLIDE;
	static JTextField FIELD_1;
	static JTextField FIELD_2;

  public HueDialog() {
	setLayout(null);
	
	setTitle("Hue Adjust");
	
	SpectrumPanel q = new SpectrumPanel();
	q.setBounds(0,20,500,34);
	
	getContentPane().add(q);
	
	
	
	LB_SLIDE = new JSlider();
	UB_SLIDE = new JSlider();
	
	LB_SLIDE.setValue(0);
	UB_SLIDE.setValue(0);
	LB_SLIDE.setInverted(false);
	UB_SLIDE.setInverted(true);
	LB_SLIDE.setMinimum(0);
	LB_SLIDE.setMaximum(360);
	UB_SLIDE.setMinimum(0);
	UB_SLIDE.setMaximum(360);
	LB_SLIDE.setBounds(0,5,495,15);
	UB_SLIDE.setBounds(0,54,495,20);
	
	getContentPane().add(LB_SLIDE);
	getContentPane().add(UB_SLIDE);
	
	FIELD_1 = new JTextField(3);
	FIELD_2 = new JTextField(3);
	FIELD_1.setBounds(115,77,40,16);
	FIELD_2.setBounds(115,102,40,16);
	FIELD_1.setText("0");
	FIELD_2.setText("360");
	
	final JLabel L_LAB = new JLabel("Hue Lower Bound: ");
	final JLabel U_LAB = new JLabel("Hue Upper Bound: ");

	L_LAB.setBounds(5,75,150,20);
	U_LAB.setBounds(5,100,150,20);

	add(L_LAB);
	add(U_LAB);
	add(FIELD_1);
	add(FIELD_2);
	
	LB_SLIDE.addChangeListener(new SliderListener());
	
	UB_SLIDE.addChangeListener(new SliderListener());
	

	
	JButton DRAW = new JButton("Draw");
	DRAW.setBounds(200,82,64,30);
	DRAW.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			
			int lb = Integer.parseInt(FIELD_1.getText());
			int ub = Integer.parseInt(FIELD_2.getText());
			
			Angle.lbound = (float) lb/(360.0f);
			
			Angle.ubound = (float) ub/(360.0f);
			
			LB_SLIDE.setValue(lb);
			UB_SLIDE.setValue(360 - ub);
			
			repaint();
			Driver.FRAME_1.validate();
			Driver.FRAME_1.repaint();
		}
	});
	
	add(DRAW);
	
  }
}
