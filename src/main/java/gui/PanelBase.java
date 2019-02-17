package gui;

import com.stefank.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class PanelBase extends JPanel {

    private static final Logger LOG = LoggerFactory.getLogger(PanelBase.class);

    protected static final String[] MAP_TIERS = {"Tier 1", "Tier 2", "Tier 3", "Tier 4", "Tier 5", "Tier 6", "Tier 7", "Tier 8",
            "Tier 9", "Tier 10", "Tier 11", "Tier 12", "Tier 13", "Tier 14", "Tier 15", "Tier 16"};


    private JLabel tradeables;

    private JLabel panelTitle;
    private JButton btnMinimize;
    private JButton btnExit;
    private JButton updateButton;

    private JLabel createdBy;
    private JLabel version;


    public PanelBase() {
        init();
    }

    private void init() {
        Image cancelIcon = null;
        Image minimizeIcon = null;
        try {
            cancelIcon = ImageIO.read(Main.class.getResourceAsStream("cancel.png"));
            minimizeIcon = ImageIO.read(Main.class.getResourceAsStream("minimize.png"));
        } catch (IOException e) {
            LOG.error("PanelBase::init loading resource images", e);
        }

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
        btnMinimize.setIcon(new ImageIcon(minimizeIcon));
        btnMinimize.setFocusPainted(false);

        btnExit = new JButton();
        btnExit.setForeground(new Color(0, 0, 0));
        btnExit.setBackground(new Color(204, 0, 0));
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setIcon(new ImageIcon(cancelIcon));
        btnExit.setFocusPainted(false);
        btnExit.setBounds(342, -1, 19, 24);

        updateButton = new JButton();
        updateButton.setText("Update");
        updateButton.setEnabled(false);
        updateButton.setBounds(139, 186, 152, 43);
        updateButton.setLocation(172, 196);

        // LABELS
        version = new JLabel("beta 1.3");
        version.setForeground(new Color(255, 235, 205));
        version.setFont(new Font("Tahoma", Font.PLAIN, 8));
        version.setBackground(new Color(0, 128, 0));
        version.setBounds(294, 286, 45, 14);

        createdBy = new JLabel("Created by ezkk2");
        createdBy.setForeground(new Color(255, 235, 205));
        createdBy.setFont(new Font("Tahoma", Font.PLAIN, 8));
        createdBy.setBackground(new Color(0, 128, 0));
        createdBy.setBounds(210, 286, 86, 14);

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

    public JButton getBtnMinimize() {
        return btnMinimize;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JLabel getTradeables() {
        return tradeables;
    }
}
