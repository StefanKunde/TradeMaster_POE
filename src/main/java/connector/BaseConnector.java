package connector;

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

    protected static final String USER_AGENT = "Mozilla/5.0";

    protected String convertHttpEntityContentToString(HttpEntity httpEntity) {
        StringBuilder result = new StringBuilder();

//        HttpEntity entity = new GzipDecompressingEntity(httpEntity);
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(httpEntity.getContent()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            getLogger().error("convertEntityContentToString", e);
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                getLogger().error("convertEntityContentToString attempting to consume httpEntity", e);
            }
        }
        return result.toString();
    }

    protected String convertStreamToString(InputStream is) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
        } catch (IOException e) {
            getLogger().error("PoeNinaFetcher::convertStreamToString", e);
        }
        return result.toString();
    }

    abstract Logger getLogger();
}
