package listener.live;

import app.Config;
import app.Main;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import gui.LivePanel;
import gui.MainFrame;
import helper.RobotHelper;
import items.PoeTradeResultModel;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class NextButtonListener implements ActionListener {

    private MainFrame frame;

    public NextButtonListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LivePanel _this = frame.getLivePanel();
        List<PoeTradeResultModel> result = frame.getLivePanel().getResult();

        if (result != null && result.size() > 0) {
            result.remove(0);
            ((DefaultTableModel) _this.getJdt().getModel()).removeRow(0);

            _this.getTradeables().setText("Tradeables: " + result.size());
            frame.setForegroundWindow(MainFrame.POE_WINDOW_NAME);

            String generatedTradeText = result.get(0).generateTradeText();
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(generatedTradeText), null);

            sendVoiceMessage(result.get(0).generateVoiceText());

            if (Config.get().isUseAutomatedTrading()) {
                RobotHelper.sendClipboardTextToChat();
            }

            _this.getBtnNextTrade().setEnabled(true);
        } else {
            _this.getBtnNextTrade().setEnabled(false);
            _this.getTradeables().setText("Tradeables: 0");
        }

    }

    private void sendVoiceMessage(String textToSpeech) {
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(Config.get().getVOICENAME());
        voice.allocate();
        try {
            voice.speak(textToSpeech);
        } catch (Exception ec) {
        }
    }
}
