package org.console_hub;

import org.jsoup.nodes.Document;
import java.io.IOException;

public interface IMDBClient {
    Document fetchDocument(String url) throws IOException;
}
