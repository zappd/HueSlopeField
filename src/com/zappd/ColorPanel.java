package com.zappd;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ColorPanel extends JPanel {
	public int i, j;
	public static Toolkit tk = Toolkit.getDefaultToolkit();  
	public static int width = ((int) tk.getScreenSize().getWidth());
	public static int height = ((int) tk.getScreenSize().getHeight());
	public static double scale = 1.0/80.0;

	public void paintComponent(Graphics g) { 

		Graphics2D g2d = (Graphics2D)g;

		BufferedImage bimage = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.OPAQUE);

		for (i = 0; i < width; i++) {

			for (j = 0; j < height; j++) {
				bimage.setRGB(i,j, Angle.angleColor(i, j, scale, width, height));
			}
		}	   
		g.drawImage(bimage, 0,0, this);
	}

}
