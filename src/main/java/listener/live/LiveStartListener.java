package listener.live;

import app.Config;
import app.Main;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import gui.MainFrame;
import helper.RobotHelper;
import items.PoeTradeResultModel;
import lombok.Builder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;


public class LiveStartListener implements ActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(LiveStartListener.class);
    private static final String QUERY = "[id^=item-container-]";

    private MainFrame frame;

    public LiveStartListener(MainFrame frame) {
        this.frame = frame;
    }

    private static final String URL = "https://poe.trade/search/%s";

    private ScheduledFuture thread;

    protected String convertStreamToString(InputStream is) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
        } catch (IOException e) {
            LOG.error("BaseConnector::convertStreamToString", e);
        }
        return result.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (frame.getLivePanel().getUpdateButton().getText().equalsIgnoreCase("Stop")) {
            thread.cancel(false);
            frame.getLivePanel().getUpdateButton().setText("Start");
            frame.getLivePanel().getCodeLookupInput().setEnabled(true);
            return;
        }

        frame.getLivePanel().getUpdateButton().setText("Stop");
        frame.getLivePanel().getCodeLookupInput().setEnabled(false);

        thread = Main.scheduleThreadTimer(() -> {
            String codeLookup = "";
            try {
                codeLookup = frame.getLivePanel().getCodeLookupInput().getDocument().getText(0, frame.getLivePanel().getCodeLookupInput().getDocument().getLength());
            } catch (BadLocationException e1) {
                LOG.error("MaxPayTxtBoxListener::insertUpdate", e1);
            }
            LOG.info("codeLookup = " + codeLookup);
            String urlWithCode = String.format(URL, codeLookup);

            HttpGet request = new HttpGet(urlWithCode);
            request.addHeader("User-Agent", "Mozilla/5.0");
            request.addHeader("Accept", "Mozilla/5.0");

            String result = "";
            try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
                LOG.debug("Sending 'GET' request to URL : " + urlWithCode);
                HttpResponse response = client.execute(request);
                LOG.debug("Response Code: " + response.getStatusLine().getStatusCode());
                result = convertStreamToString(response.getEntity().getContent());
            } catch (IOException ioe) {
                LOG.error("LiveStartListener::actionPerformed to " + urlWithCode + ", Returned IOException: " + ioe.getMessage());
            }

            List<Element> forSaleItems = new ArrayList<>();
            Document doc = Jsoup.parse(result);
            Elements items = doc.select(QUERY);

            for (Element item : items) {
                forSaleItems.add(item);
            }

            List<PoeTradeResultModel> convertedSaleItems = new LinkedList<>();
            for (int i = 0; i < forSaleItems.size(); i++) {
                convertedSaleItems.add(new PoeTradeResultModel(forSaleItems.get(i)));
            }

            frame.getLivePanel().setResult(convertedSaleItems);

            //TODO Check the (I already messaged that person in the last 5 minutes list)
            //TODO Filter duplicate items (Not here because here is the main 'item' search page not currency.
            //TODO add ones sent to new list..

            String[] columnNames = {"IGN", "Item Name", "Price"};
            DefaultTableModel newDataModel = new DefaultTableModel(columnNames, 0);

            if (convertedSaleItems != null && !convertedSaleItems.isEmpty()) {
                for (int i = 0; i < convertedSaleItems.size(); i++) {
                    LOG.info(convertedSaleItems.get(i).generateItemText());
                    Object[] row = {convertedSaleItems.get(i).getIgn(), convertedSaleItems.get(i).getName(), convertedSaleItems.get(i).getPriceAmount() + " " + convertedSaleItems.get(i).getPaymentCurrency()};
                    newDataModel.addRow(row);
                }
            } else {
                newDataModel.addRow(new Object[]{"", "", ""});
            }

            frame.getLivePanel().getJdt().setModel(newDataModel);
            frame.getLivePanel().getTradeables().setText("Tradeables: " + convertedSaleItems.size());

            if (convertedSaleItems.size() > 0) {
                String generatedTradeText = convertedSaleItems.get(0).generateTradeText();
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(generatedTradeText), null);

                frame.getLivePanel().getBtnNextTrade().setEnabled(true);

                Voice voice;
                VoiceManager vm = VoiceManager.getInstance();
                voice = vm.getVoice(Config.get().getVOICENAME());
                voice.allocate();

                try {
                    voice.speak(convertedSaleItems.get(0).generateVoiceText());
                } catch (Exception ec) {
                    LOG.error("Error speaking:: ", e);
                }
            } else {
                frame.getLivePanel().getBtnNextTrade().setEnabled(false);
            }

        }, 0, 10000);

    }
}
