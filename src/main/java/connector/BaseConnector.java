package connector;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public abstract class BaseConnector {

    public static final Gson GSON = new Gson();
    protected static final String USER_AGENT = "Mozilla/5.0";

    protected String convertStreamToString(InputStream is) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
        } catch (IOException e) {
            getLogger().error("BaseConnector::convertStreamToString", e);
        }
        return result.toString();
    }

    abstract Logger getLogger();
}
