package com.yourcompany;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		System.out.println(e.paramString());
		System.out.println(e);
	}
	
}
