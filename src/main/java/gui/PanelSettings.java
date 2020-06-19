package gui;

import app.Config;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class PanelSettings extends PanelBase {

    private static final long serialVersionUID = 1L;

    private JLabel leagueLabel;

    @Getter
    private JLabel pricesLoaded;

    @Getter
    private JComboBox<String> leagueSelection;

    @Override
    protected void initTab() {
        // LABELS
        leagueLabel = new JLabel("League?");
        leagueLabel.setForeground(Color.WHITE);
        leagueLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        leagueLabel.setBackground(Color.GRAY);
        leagueLabel.setBounds(10, 34, 152, 14);

        pricesLoaded = new JLabel();
        pricesLoaded.setForeground(Color.YELLOW);
        pricesLoaded.setFont(new Font("Tahoma", Font.BOLD, 15));
        pricesLoaded.setBackground(Color.GRAY);
        pricesLoaded.setBounds(10, 150, 300, 20);

        // COMBO-BOXES
        leagueSelection = new JComboBox(Config.get().getLeagues());
        leagueSelection.setBounds(10, 59, 152, 20);
        leagueSelection.setSelectedItem(Config.get().getLeagueSelection());

        add(leagueLabel);
        add(pricesLoaded);
        add(leagueSelection);
    }

    public void updatePricesLoaded(String data) {
        this.pricesLoaded.setText(data);
    }

    @Override
    protected boolean addOptionalTradeables() {
        return false;
    }

    @Override
    public String getTabTitle() {
        return "Settings";
    }
}
