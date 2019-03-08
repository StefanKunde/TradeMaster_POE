package gui;

import app.Main;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public abstract class PanelBase extends JPanel {

    private static final Logger LOG = LoggerFactory.getLogger(PanelBase.class);
    @Getter
    private JLabel tradeables;

    private JLabel panelTitle;

    @Getter
    private JButton btnMinimize;
    @Getter
    private JButton btnExit;
    @Getter
    private JButton updateButton;

    private JLabel createdBy;
    private JLabel maintainedBy;
    private JLabel version;

    public PanelBase() {
        init();
    }

    private void init() {
        setLayout(null);
        setPreferredSize(new Dimension(420, 315));
        setForeground(Color.GRAY);
        setBackground(new Color(51, 51, 51));

        panelTitle = new JLabel(getTabTitle());
        panelTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
        panelTitle.setForeground(new Color(255, 235, 205));
        panelTitle.setBackground(new Color(0, 128, 0));
        panelTitle.setBounds(70, -1, 242, 24);

        // BUTTONS
        btnMinimize = new JButton();
        btnMinimize.setSize(19, 24);
        btnMinimize.setLocation(322, -1);
        btnMinimize.setOpaque(false);
        btnMinimize.setForeground(Color.BLACK);
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setBorderPainted(false);
        btnMinimize.setBackground(new Color(204, 0, 0));
        btnMinimize.setOpaque(false);
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setBorderPainted(false);
        btnMinimize.setIcon(new ImageIcon(Main.class.getResource("/images/minimize.png")));
        btnMinimize.setFocusPainted(false);

        btnExit = new JButton();
        btnExit.setForeground(new Color(0, 0, 0));
        btnExit.setBackground(new Color(204, 0, 0));
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setIcon(new ImageIcon(Main.class.getResource("/images/cancel.png")));
        btnExit.setFocusPainted(false);
        btnExit.setBounds(342, -1, 19, 24);

        updateButton = new JButton();
        updateButton.setText("Send Request");
        updateButton.setEnabled(false);
        updateButton.setBounds(139, 186, 152, 43);
        updateButton.setLocation(172, 196);

        // LABELS
        version = new JLabel("v" + getClass().getPackage().getImplementationVersion());
        version.setForeground(new Color(255, 235, 205));
        version.setFont(new Font("Tahoma", Font.PLAIN, 8));
        version.setBackground(new Color(0, 128, 0));
        version.setBounds(294, 286, 45, 14);

        createdBy = new JLabel("Originally created by ezkk2");
        createdBy.setForeground(new Color(255, 235, 205));
        createdBy.setFont(new Font("Tahoma", Font.PLAIN, 8));
        createdBy.setBackground(new Color(0, 128, 0));
        createdBy.setBounds(180, 286, 110, 14);

        maintainedBy = new JLabel("Maintained by VeenarM");
        maintainedBy.setForeground(new Color(255, 235, 205));
        maintainedBy.setFont(new Font("Tahoma", Font.PLAIN, 8));
        maintainedBy.setBackground(new Color(0, 128, 0));
        maintainedBy.setBounds(180, 300, 110, 14);

        tradeables = new JLabel("Tradeables: ");
        tradeables.setForeground(Color.WHITE);
        tradeables.setEnabled(false);
        tradeables.setBounds(154, 250, 128, 14);
        tradeables.setLocation(172, 250);

        add(panelTitle);
        add(btnMinimize);
        add(btnExit);
        add(version);
        add(createdBy);
        add(updateButton);
        add(maintainedBy);

        if (addOptionalTradeables()) {
            add(tradeables);
        }

        // Force init
        initTab();
    }

    protected boolean addOptionalTradeables() {
        return true;
    }

    abstract void initTab();

    abstract String getTabTitle();

    public void enableAndResetUpdateButtonText() {
        updateButton.setText("Send Request");
        updateButton.setEnabled(true);
    }

    public void disableUpdateButtonAndSetPendingText() {
        updateButton.setText("Please wait...");
        updateButton.setEnabled(false);
    }
}
