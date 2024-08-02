package org.console_hub;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String tvShowId = "tt0944947";
        IMDBClient imdbClient = new JsoupIMDBClient();
        IMDBScraper imdbScraper = new IMDBScraper(imdbClient);
        imdbScraper.scrape(tvShowId);

        ChartDisplay chartDisplay = new ChartDisplay(imdbScraper.getSeasonAverages(), tvShowId);
        chartDisplay.save();
    }
}