package com.colors;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class SpectrumPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int k, r;
	int specwidth = 480;
	int specheight = 40;
	
	public SpectrumPanel() {
	}
	
public void paintComponent(Graphics g) { 
	Graphics2D g2d = (Graphics2D)g;
	
    BufferedImage spectrum = g2d.getDeviceConfiguration().createCompatibleImage(specwidth, specheight, Transparency.OPAQUE);
    
    for (k = 0; k < specwidth; k++) {

    	for (r = 0; r < specheight; r++) {
    		spectrum.setRGB(k,r, Angle.getRainbowComp(specwidth, k));
    	}
    }	   
    g.drawImage(spectrum, 7,0, this);
  }
}
