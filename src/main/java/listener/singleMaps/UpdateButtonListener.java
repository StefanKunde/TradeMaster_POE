package listener.singleMaps;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import app.Main;
import org.jsoup.nodes.Element;

import gui.MainFrame;
import handler.PoeTradeHandler;
import items.Map;
import items.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used for Single Maps
 */
public class UpdateButtonListener implements ActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateButtonListener.class);

    private MainFrame frame;

    public UpdateButtonListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getSingleMapsPanel().disableUpdateButtonAndSetPendingText();
        frame.getSingleMapsPanel().getTradeables().setText("Loading...");

        Main.getExecutor().execute(() -> {
            List<Element> buyableMaps = PoeTradeHandler.fetchBuyableMapsAsHtml(frame.getSearchBuilder().generateSearchData());

            frame.setMaps(new ArrayList<>());
            for (int i = 0; i < buyableMaps.size(); i++) {
                frame.getMaps().add(new Map(buyableMaps.get(i)));
            }

            Maps myMaps = new Maps(frame.getMaps());
            myMaps.initializeMaps();

            LOG.debug("Map Size: " + myMaps.getMaps().size());
            myMaps.filterByCurrency(frame.getCurrency());

            frame.getSingleMapsPanel().getTradeables().setText("Tradeables count: " + myMaps.getMaps().size());
            frame.getSingleMapsPanel().getTradeables().setEnabled(true);
            frame.setTradeableMaps(myMaps.getMaps());

            if (frame.getTradeableMaps().size() > 0) {
                frame.getSingleMapsPanel().getBtn_nextTrade().setEnabled(true);
            }

            frame.getSingleMapsPanel().enableAndResetUpdateButtonText();
            Toolkit.getDefaultToolkit().beep();

        });
    }

}
