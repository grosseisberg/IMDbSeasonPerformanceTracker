package org.console_hub;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class ChartGenerator {
    private final List<Double> seasonAverages;

    public ChartGenerator(List<Double> seasonAverages) {
        this.seasonAverages = seasonAverages;
    }

    public JFreeChart createChart() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createLineChart(
                "Season Ratings",
                "Season",
                "Rating",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Y ekseni ayarları
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 10.0);
        rangeAxis.setTickUnit(new NumberTickUnit(1.0));

        // Grafik stilini güzelleştirme
        chart.setBackgroundPaint(Color.white);
        plot.setBackgroundPaint(new Color(230, 230, 250));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setDefaultShapesVisible(true);

        // Nokta değerlerini gösterme
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0.00")));

        plot.setRenderer(renderer);

        return chart;
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < seasonAverages.size(); i++) {
            dataset.addValue(seasonAverages.get(i), "Rating", String.valueOf(i + 1));
        }
        return dataset;
    }
}