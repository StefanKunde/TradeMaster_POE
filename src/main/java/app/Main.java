package app;

import connector.ConfigurationConnector;
import gui.MainFrame;
import items.PoeNinjaPrices;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.concurrent.*;

@Slf4j
public class Main {

    private static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

    @Getter
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    @Getter
    @Setter
    private static boolean minimised;

    public static void main(String... args) {
        // Loads all required configuration from API
        ConfigurationConnector.configure();
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        new MainFrame(new PoeNinjaPrices());
    }

    public static ScheduledFuture scheduleThreadTimer(Runnable timerTask, int initialDelay, int period) {
        return scheduledThreadPool.scheduleAtFixedRate(timerTask, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    public static void shutdown() {
        log.debug("Main::shutdown() activated, destroying scheduler pools then frames.");
        scheduledThreadPool.shutdownNow();
        executor.shutdownNow();

        while (!scheduledThreadPool.isTerminated() && !executor.isTerminated()) {
            log.debug("All threads not terminated yet.. waiting...");
        }
        log.debug("All threads now in terminated state.");
        for (Frame f : Frame.getFrames()) {
            f.dispose();
        }
        log.debug("All Frames now disposed, application process terminated.");
    }

}
