package org.console_hub;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class JsoupIMDBClient implements IMDBClient {
    @Override
    public Document fetchDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
