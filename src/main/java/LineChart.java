import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
/*
Для отрисовки графиков использовал стороннюю библиотеку JFreeChart, в этом
классе все оформлено по примеру из интернета. Ниже есть пара моих комментариев, но,
думаю, и без них тут все довольно понятно.
 */
public class LineChart extends JFrame {

    public LineChart() {

        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);

        add(chartPanel);

        pack();
        setTitle("Графики");
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {

        // первая серия точек, Hash реализация
        var series1 = new XYSeries("HashMap");
        series1.add(0,0);
        for(int i = 0; i < Logic.elemsCountHash.size(); i++) {
            series1.add(Logic.timeValuesHash.get(i), Logic.elemsCountHash.get(i));
        }

        // вторая серия точек, Tree реализация
        var series2 = new XYSeries("TreeMap");
        series2.add(0,0);
        for(int i = 0; i < Logic.elemsCountTree.size(); i++) {
            series2.add(Logic.timeValuesTree.get(i), Logic.elemsCountTree.get(i));
        }

        var dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Графики",
                "Время",
                "Кол-во элементов",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Графики",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }

    public static void main() {

        EventQueue.invokeLater(() -> {

            var ex = new LineChart();
            ex.setVisible(true);
        });
    }
}
