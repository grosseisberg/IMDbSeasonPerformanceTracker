package org.console_hub;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChartDisplay {
    private final ChartGenerator chartGenerator;
    private final String tvShowId;

    public ChartDisplay(List<Double> seasonAverages, String tvShowId) {
        this.chartGenerator = new ChartGenerator(seasonAverages);
        this.tvShowId = tvShowId;
    }

    public void save() {
        JFreeChart chart = chartGenerator.createChart();
        String chartFilePath = "src/main/resources/Charts/" + tvShowId + "_chart.png";
        try {
            File chartFile = new File(chartFilePath);
            ChartUtils.saveChartAsPNG(chartFile, chart, 800, 600);
            System.out.println("Chart saved as " + chartFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}