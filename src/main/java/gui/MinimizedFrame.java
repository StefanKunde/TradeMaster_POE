package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import viewcontroller.MaximizeButtonListener;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MinimizedFrame extends JFrame implements IHideable {
	
	private JTextField txtTest;
	private boolean userWantsMinimize;
	private boolean isVisible;
	MainFrame mainFrame;
	
	
	public MinimizedFrame(MainFrame mainFrame)  {
		this.mainFrame = mainFrame;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnMaximize = new JButton("MAXIMIZE");
		
		
		MaximizeButtonListener maxBtnListener = new MaximizeButtonListener(this, mainFrame);
		btnMaximize.addActionListener(maxBtnListener);
		
		btnMaximize.setBounds(51, 0, 254, 46);
		getContentPane().add(btnMaximize);
		this.setTitle("Minimized Frame");
		this.setSize(364, 96);
		this.getContentPane().requestFocusInWindow();
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
	    FrameDragListener frameDragListener = new FrameDragListener(this);
	    this.addMouseListener(frameDragListener);
	    this.addMouseMotionListener(frameDragListener);
        this.setAlwaysOnTop(true);
		//frame.setMinimumSize(new Dimension(300, 300));
		SwingUtilities.updateComponentTreeUI(this);
		isVisible = false;
		
	}

	@Override
	public void setFrameVisible() {
		this.setVisible(true);
		isVisible = true;
	}

	@Override
	public boolean isUserWantsMinimize() {
		return userWantsMinimize;
	}
	
	public void setUserWantsMinimize(boolean userWantsMinimize) {
		this.userWantsMinimize = userWantsMinimize;
	}

	@Override
	public void setFrameInvisible() {
		this.setVisible(false);
		isVisible = false;
	}

	@Override
	public boolean isFrameVisible() {
		return isVisible;
	}
}
