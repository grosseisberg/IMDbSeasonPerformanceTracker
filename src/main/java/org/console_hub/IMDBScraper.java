package org.console_hub;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.List;

public class IMDBScraper {
    private final IMDBClient imdbClient;
    private final SeasonRatingCalculator ratingCalculator;

    public IMDBScraper(IMDBClient imdbClient) {
        this.imdbClient = imdbClient;
        this.ratingCalculator = new SeasonRatingCalculator(imdbClient);
    }

    public void scrape(String tvShowId) throws IOException {
        int maxSeason = fetchMaxSeason(tvShowId);
        ratingCalculator.calculateRatings(tvShowId, maxSeason);
    }

    private int fetchMaxSeason(String tvShowId) throws IOException {
        String url = "https://www.imdb.com/title/" + tvShowId + "/episodes/?season=1";
        Document document = imdbClient.fetchDocument(url);
        Elements links = document.select("a.ipc-tab");

        int maxSeason = 0;
        for (Element link : links) {
            String text = link.text().trim();
            try {
                int seasonNumber = Integer.parseInt(text);
                if (seasonNumber > maxSeason) {
                    maxSeason = seasonNumber;
                }
            } catch (NumberFormatException e) {
                //System.err.println("Failed to parse season number: " + text);
            }
        }
        return maxSeason;
    }

    public List<Double> getSeasonAverages() {
        return ratingCalculator.getSeasonAverages();
    }
}
