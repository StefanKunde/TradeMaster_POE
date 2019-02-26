package gui;

import config.Config;

import javax.swing.*;
import java.awt.*;

public class PanelSettings extends PanelBase {

    private static final long serialVersionUID = 1L;

    private JLabel leagueLabel;
    private JComboBox<String> leagueSelection;

    @Override
    protected void initTab() {
        // LABELS
        leagueLabel = new JLabel("League?");
        leagueLabel.setForeground(Color.WHITE);
        leagueLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        leagueLabel.setBackground(Color.GRAY);
        leagueLabel.setBounds(10, 34, 152, 14);

        // COMBO-BOXES
        leagueSelection = new JComboBox(Config.AVAILABLE_LEAGUES);
        leagueSelection.setBounds(10, 59, 152, 20);
        leagueSelection.setSelectedItem(Config.leagueSelection);

        add(leagueLabel);
        add(leagueSelection);
    }

    @Override
    protected boolean addOptionalTradeables() {
        return false;
    }

    @Override
    public String getTabTitle() {
        return "Trademaster - Settings";
    }

    public JComboBox<String> getLeagueSelection() {
        return leagueSelection;
    }
}
