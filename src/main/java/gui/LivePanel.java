package gui;

import items.PoeTradeResultModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LivePanel extends PanelBase {

    private static final long serialVersionUID = 1L;

    private JLabel searchCodeLookupLabel;

    @Getter
    private JTextField codeLookupInput;

    private JPanel jdtPanel;

    @Getter
    private JTable jdt;

    @Getter
    private JButton btnNextTrade;

    @Getter @Setter
    private List<PoeTradeResultModel> result;

    @Override
    protected void initTab() {
        // LABELS
        searchCodeLookupLabel = new JLabel("Search Code");
        searchCodeLookupLabel.setForeground(Color.WHITE);
        searchCodeLookupLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        searchCodeLookupLabel.setBackground(Color.GRAY);
        searchCodeLookupLabel.setBounds(10, 34, 152, 14);

        // TEXTBOXES
        codeLookupInput = new JTextField();
        codeLookupInput.setColumns(10);
        codeLookupInput.setBounds(172, 34, 152, 20);
        codeLookupInput.setText("ohomanokokitun");

        btnNextTrade = new JButton();
        btnNextTrade.setText("Next Trade");
        btnNextTrade.setEnabled(false);
        btnNextTrade.setBounds(10, 250, 134, 50);

        add(searchCodeLookupLabel);
        add(codeLookupInput);
        add(btnNextTrade);

        String[] columnNames = { "IGN", "Item Name", "Price" };
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);

        //Creating JPanel and setting properties
        jdtPanel = new JPanel();
        jdtPanel.setLayout(new BorderLayout());
        jdtPanel.setBounds(10, 55, 350, 125);

        // Initializing the JTable
        jdt = new JTable(dtm);
        jdt.setBackground(Color.WHITE);
        jdt.getTableHeader().setReorderingAllowed(false);
        jdt.setBounds(10, 55, 350, 125);
        jdt.setEnabled(false);

        JScrollPane sp = new JScrollPane(jdt);
        jdtPanel.add(sp, BorderLayout.CENTER);

        // adding it to JScrollPane
        add(jdtPanel);

        getUpdateButton().setEnabled(true);
        getUpdateButton().setText("Start");
        getUpdateButton().setBounds(180, 190, 152, 43);
    }


    @Override
    public String getTabTitle() {
        return "Live";
    }


}
