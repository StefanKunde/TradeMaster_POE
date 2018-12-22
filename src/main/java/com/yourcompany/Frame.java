package com.yourcompany;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import com.sun.jna.Native;

import connector.SearchParameter;
import handler.TradeHandler;
import items.Map;
import items.Maps;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Frame {
	private User32 user32 = User32.INSTANCE;
	JFrame frame;
	JPanel panel;
	JLabel lblCurrency;
	boolean isVisible;
	String[] currencys = { "ANY", "chaos", "alchemy", "chisel", "vaal" };
	String[] tiers = {"Tier 1", "Tier 2", "Tier 3", "Tier 4", "Tier 5", "Tier 6", "Tier 7", "Tier 8", 
			"Tier 9", "Tier 10", "Tier 11", "Tier 12", "Tier 13", "Tier 14", "Tier 15"};
		

	JButton btn_update = new JButton();
	private JComboBox cmb_currency;
	private JComboBox cmb_tier;
	private JComboBox cmb_map;
	
	List<Map> maps;
	List<Map> tradeableMaps;
	//List<String> tier_1_maps = new ArrayList<String>();
	
	SearchParameter searchBuilder;
	String currency = "";
	String mapName = "";
	
	boolean selectedCurrency = false;
	boolean selectedTier = false;
	boolean selectedMap = false;
	
	
	
	public Frame() {
		maps = new ArrayList<Map>();
		tradeableMaps = new ArrayList<Map>();
		searchBuilder = new SearchParameter();
		frame = new JFrame("JFrame Example");
		frame.setForeground(Color.GRAY);
		frame.setFont(new Font("Calibri", Font.PLAIN, 12));
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		panel.setForeground(Color.GRAY);
		lblCurrency = new JLabel("Currency");
		lblCurrency.setForeground(new Color(255, 255, 255));
		lblCurrency.setBackground(Color.GRAY);
		lblCurrency.setBounds(10, 9, 71, 14);
		
		btn_update.setEnabled(false);
		btn_update.setBounds(155, 102, 129, 29);
		btn_update.setText("Update");
		panel.setLayout(null);
		panel.add(lblCurrency);
		panel.add(btn_update);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		frame.getContentPane().add(panel);
		
		cmb_currency = new JComboBox(currencys);
		cmb_currency.setBounds(91, 5, 243, 22);
		panel.add(cmb_currency);
		
		JButton btn_exit = new JButton("X");
		btn_exit.setForeground(new Color(0, 0, 0));
		btn_exit.setBackground(new Color(204, 0, 0));
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btn_exit.setBounds(349, 11, 39, 29);
		panel.add(btn_exit);
		
		cmb_tier = new JComboBox(tiers);
		cmb_tier.setBounds(91, 34, 243, 22);
		panel.add(cmb_tier);
		
		JLabel lblTier = new JLabel("Tier");
		lblTier.setForeground(Color.WHITE);
		lblTier.setBackground(Color.GRAY);
		lblTier.setBounds(10, 38, 71, 14);
		panel.add(lblTier);
		
		cmb_map = new JComboBox(new Object[]{});
		cmb_map.setEnabled(false);
		cmb_map.setBounds(91, 67, 243, 22);
		panel.add(cmb_map);
		
		JLabel lblMap = new JLabel("Map");
		lblMap.setForeground(Color.WHITE);
		lblMap.setBackground(Color.GRAY);
		lblMap.setBounds(10, 71, 71, 14);
		panel.add(lblMap);
		
		JLabel lbl_count = new JLabel("Tradeables: ");
		lbl_count.setEnabled(false);
		lbl_count.setBounds(155, 142, 129, 14);
		panel.add(lbl_count);
		
		JButton btn_nextTrade = new JButton();
		btn_nextTrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(tradeableMaps.size() > 0) {
						//lbl_trade.setText(this.tradeableMaps.get(0).generateTradeText());
						String myString = tradeableMaps.get(0).generateTradeText();
						StringSelection stringSelection = new StringSelection(myString);
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(stringSelection, null);
						tradeableMaps.remove(0);
						lbl_count.setText("Tradeables left: " + tradeableMaps.size());
						setForegroundWindow("Path of Exile");
					} else {
						lbl_count.setText("no tradeables left.");
					}
			}
		});
		btn_nextTrade.setEnabled(false);
		btn_nextTrade.setText("Next Trade");
		btn_nextTrade.setBounds(10, 192, 161, 54);
		panel.add(btn_nextTrade);
		frame.setSize(398, 257);
		frame.setLocationRelativeTo(null);
	    frame.setUndecorated(true);
	    FrameDragListener frameDragListener = new FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);
		frame.setAlwaysOnTop(true);
		//frame.setMinimumSize(new Dimension(300, 300));
		SwingUtilities.updateComponentTreeUI(frame);
		this.isVisible = false;
		
		
		
		// Listener
		cmb_currency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(((JComboBox) e.getSource()).getSelectedItem());
				String selectedCurrency = (String) ((JComboBox) e.getSource()).getSelectedItem();
				if(selectedCurrency.equals("ANY")) {
					selectedCurrency = "";
				}
				currency = selectedCurrency;
				searchBuilder.setCurrency(selectedCurrency);
			}
		});
		
		cmb_tier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
				
				this.loadMapsFromSelectedTier(selectedItem);
			}

			private void loadMapsFromSelectedTier(String selectedTier) {
				System.out.println("selectedTier " + selectedTier);
				if(selectedTier.startsWith("Tier ")) {
					String mapsAsJsonString = "";
			        //mapsAsJsonString = new String(Files.readAllBytes(Paths.get("/resources/maps.json")));
					String text = new Scanner(Main.class.getResourceAsStream("maps.json"), "UTF-8").useDelimiter("\\A").next();
					mapsAsJsonString = new String(text);
			        
			        JSONObject json = new JSONObject(mapsAsJsonString);
			        JSONArray maps = json.getJSONArray(selectedTier);
			        List<String> mapsAsList = new ArrayList<String>();
			        // convert json array into arraylist
			        for(int i = 0; i < maps.length(); i++) {
			        	mapsAsList.add(maps.get(i).toString());
			        }
			        Collections.sort(mapsAsList, String.CASE_INSENSITIVE_ORDER);
			        
			        
			        String[] mapsAsArray = new String[mapsAsList.size()];
			        cmb_map.removeAllItems();
			        
			        for(int i = 0; i < mapsAsList.size(); i++) {
			        	cmb_map.addItem( mapsAsList.get(i) );
			        }
			        
			        cmb_map.setEnabled(true);
			        btn_update.setEnabled(true);
				}
			}

		});
		
		cmb_map.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
				searchBuilder.setMapName(selectedItem);
			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TradeHandler trader = new TradeHandler(searchBuilder.generateSearchData());
				List<Element> buyableMaps = trader.fetchBuyableMapsAsHtml();
				maps = new ArrayList<Map>();
				
				for( int i = 0; i < buyableMaps.size(); i++) {
					maps.add( new Map (buyableMaps.get(i)));
				}
				
				Maps myMaps = new Maps(maps);
				myMaps.initializeMaps();
				
				System.out.println(myMaps.getMaps().size());
				
				System.out.println("selectedCurrency: " + selectedCurrency);
				//myMaps.filterByCurrency(currency);
				
				lbl_count.setText("Tradeables count: " + myMaps.getMaps().size());
				lbl_count.setEnabled(true);
				tradeableMaps = myMaps.getMaps();
				
				if(tradeableMaps.size() > 0) {
					btn_nextTrade.setEnabled( true);
				}
			}
		});
		
	}
	
	public void setVisible() {
		frame.setVisible(true);
		this.isVisible = true;
	}
	
	public void setInvisible() {
		frame.setVisible(false);
		this.isVisible = false;
	}
	
	public boolean isVisible() {
		return this.isVisible;
	}
	
	public void setForegroundWindow(final String titleName){
        user32.EnumWindows((hWnd, arg1) -> {
            byte[] windowText = new byte[512];
            user32.GetWindowTextA(hWnd, windowText, 512);
            String wText = Native.toString(windowText);

            if (wText.isEmpty()) {
                return true;
            }
            if (wText.equals(titleName)) {
                user32.SetForegroundWindow(hWnd);
                return false;
            }
            return true;
        }, null);
	}
	
	
}
