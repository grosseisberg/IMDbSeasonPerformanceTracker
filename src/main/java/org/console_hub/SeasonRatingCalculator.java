package org.console_hub;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SeasonRatingCalculator {
    private final IMDBClient imdbClient;
    private final List<Double> seasonAverages = new ArrayList<>();
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public SeasonRatingCalculator(IMDBClient imdbClient) {
        this.imdbClient = imdbClient;
    }

    public void calculateRatings(String tvShowId, int maxSeason) throws IOException {
        for (int season = 1; season <= maxSeason; season++) {
            String url = "https://www.imdb.com/title/" + tvShowId + "/episodes/?season=" + season;
            Document document = imdbClient.fetchDocument(url);
            Elements spans = document.select("span.ipc-rating-star--rating");

            double totalRating = 0;
            int episodeCount = spans.size();

            for (Element span : spans) {
                String ratingText = span.text().trim();
                try {
                    double rating = Double.parseDouble(ratingText);
                    totalRating += rating;
                } catch (NumberFormatException e) {
                    //System.err.println("Failed to parse rating: " + ratingText);
                }
            }

            double averageRating = (episodeCount > 0) ? (totalRating / episodeCount) : 0.0;
            seasonAverages.add(averageRating);
            System.out.println("Season " + season + " average rating: " + decimalFormat.format(averageRating));
        }
    }

    public List<Double> getSeasonAverages() {
        return seasonAverages;
    }
}
