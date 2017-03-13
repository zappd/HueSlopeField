package com.zappd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.zappd.ui.*;

public class FieldFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPopupMenu popup;

	public FieldFrame() {
		
		setTitle("Field");

		ColorPanel p = new ColorPanel();
		
		popup = new JPopupMenu("Options");
		
		JMenuItem hueAdjust = new JMenuItem("Hue Adjust");
		JMenuItem setEquation = new JMenuItem("Set Equation");
		JMenuItem setCenter = new JMenuItem("Center Here (Left Click)");
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem exit = new JMenuItem("Exit");
		
		hueAdjust.addActionListener(new PopupListener());
		setCenter.addActionListener(new PopupListener());
		setEquation.addActionListener(new PopupListener());
		reset.addActionListener(new PopupListener());
		exit.addActionListener(new PopupListener());
		
		popup.add(hueAdjust);
		popup.add(setEquation);
		popup.add(setCenter);
		popup.add(reset);
		popup.addSeparator();
		popup.add(exit);
		p.add(popup);
		
		getContentPane().add(p);
		
		addMouseListener(new MouseAdapter() {
		 public void mousePressed(MouseEvent e) {
			 if(e.getButton() == 3) {
				 
				 popup.show( e.getComponent(), e.getX(), e.getY());
				 popup.setVisible(true);
				 
			 }
			 
			 if (e.getButton() == 1) {
				 Angle.woffset = Angle.woffset + e.getX() - (ColorPanel.width/2);;
				 Angle.hoffset = Angle.hoffset + (ColorPanel.height/2) - e.getY();
				 repaint();
			 }
		 }
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				ColorPanel.scale = 1.0/((1.0/ColorPanel.scale) - (1.0/(ColorPanel.scale*10))*e.getScrollAmount()*e.getWheelRotation());
				
				repaint();
				
			}
			
		});
		
	}
	
}
