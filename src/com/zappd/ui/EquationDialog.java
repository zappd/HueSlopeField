package com.zappd.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zappd.Angle;
import com.zappd.Driver;
import com.zappd.parser.Parser;
import com.zappd.parser.ParserException;

public class EquationDialog extends JFrame {

	static JTextField FIELD;

	public EquationDialog() {

		setLayout(null);

		setTitle("Set Equation");

		FIELD = new JTextField(20);
		FIELD.setBounds(50,15,240,20);
		FIELD.setText("x^2+y^2");

		JLabel dydx = new JLabel("dy/dx = ");
		dydx.setBounds(5,14,50,20);

		JButton draw = new JButton("Draw");
		draw.setBounds(300,11,64,30);
		draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Angle.equation = FIELD.getText();
					Angle.parser = new Parser(Angle.equation);
				} catch (ParserException p) {
					FIELD.setText(p.getMessage());
				}

				Driver.FRAME_1.invalidate();
				Driver.FRAME_1.validate();
				Driver.FRAME_1.repaint();

			}
		});

		add(FIELD);
		add(dydx);
		add(draw);
	}
}
