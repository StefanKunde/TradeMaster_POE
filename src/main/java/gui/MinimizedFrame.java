package gui;

import app.Main;
import com.sun.jna.Native;
import com.sun.jna.PointerType;
import listener.MaximizeButtonListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.User32;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MinimizedFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(MinimizedFrame.class);

    private User32 user32 = User32.INSTANCE;

    public MinimizedFrame(MainFrame mainFrame) {
        setTitle("MapTrado Mini");
        setupThreadAndShowFrame();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setForeground(Color.GRAY);
        getContentPane().setBackground(Color.DARK_GRAY);
        setResizable(false);
        setAutoRequestFocus(false);
        setForeground(Color.GRAY);
        setFont(new Font("Calibri", Font.PLAIN, 12));
        setBackground(Color.GRAY);

        JFrame.setDefaultLookAndFeelDecorated(true);

        JButton btnMaximize = new JButton("");
        btnMaximize.setLocation(14, 14);
        btnMaximize.setOpaque(false);
        btnMaximize.setForeground(Color.BLACK);
        btnMaximize.setContentAreaFilled(false);
        btnMaximize.setBorderPainted(false);
        btnMaximize.setIcon(new ImageIcon(Main.class.getResource("/images/maximize.png")));
        btnMaximize.setFocusPainted(false);
        btnMaximize.setSize(32, 32);

        MaximizeButtonListener maxBtnListener = new MaximizeButtonListener(this, mainFrame);
        btnMaximize.addActionListener(maxBtnListener);
        getContentPane().setLayout(null);
        getContentPane().add(btnMaximize);


        setSize(60, 60);
        setLocationRelativeTo(null);
        setUndecorated(true);
        getContentPane().requestFocusInWindow();
        FrameDragListener frameDragListener = new FrameDragListener(this);
        addMouseListener(frameDragListener);
        addMouseMotionListener(frameDragListener);
        setAlwaysOnTop(true);
        //frame.setMinimumSize(new Dimension(300, 300));
        setVisible(false);
    }

    private void setupThreadAndShowFrame() {
        Main.scheduleThreadTimer(() -> {
            byte[] windowText = new byte[512];
            PointerType hwnd = user32.GetForegroundWindow();
            User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);

            String activeWindowTitle = Native.toString(windowText);

            if (Native.toString(windowText).equals("Path of Exile")) {
                if (Main.isMinimised() && !isVisible()) {
                    setVisible(true);
                }
                return;
            }
            boolean isActiveWindowMain = activeWindowTitle.equals("MapTrado Main");
            boolean isActiveWindowMini = activeWindowTitle.equals("MapTrado Mini");

            if (!isActiveWindowMain && !isActiveWindowMini) {
                setVisible(false);
            } else {
                if (Main.isMinimised()) {
                    setVisible(true);
                }
            }

        });
    }
}
