package com.colors;

import java.awt.Color;
import com.colors.parser.*;

public class Angle {	
	public static float lbound = 0.0f;
	public static float ubound = 1.0f;
	public static int woffset = 0;
	public static int hoffset = 0;
	public static String equation = "x^2+y^2";
	
	public static Parser parser = null;
	static {
		try {parser = new Parser(equation);} catch (ParserException e) {}
	}

	public Angle() {}

	public static int angleColor(int x, int y, double scale, int width, int height) {
		double dydt = parser.evaluate(((x + woffset) - ((width)/2))*scale, (((height)/2) - (y - hoffset))*scale);
		float hue = (((ubound) - lbound)*((float) (2*Math.atan(dydt)))) + lbound;
		return Color.HSBtoRGB(hue, 1.0f, 1.0f);
	}

	public static int getRainbowComp(int total, int index) {
		float hue = 1.0f*(((float) index)/((float) total));
		return Color.HSBtoRGB(hue, 1.0f, 1.0f);
	}	
}