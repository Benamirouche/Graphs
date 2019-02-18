package mainPackage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * GraphDrawer est la classe responsable d'afficher le graphe de loi de distribution
 */
public class GraphDrawer extends JFrame {
	Map<Integer,List<Integer>> prefAttach;

	public GraphDrawer(Map<Integer,List<Integer>> prefAttach) {
		 super("Distribution des degrés");
		this.prefAttach=prefAttach;
		 
	        JPanel chartPanel = createChartPanel();
	        add(chartPanel, BorderLayout.CENTER);
	 
	        setSize(1000, 700);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	}

	 private JPanel createChartPanel() {
		 String chartTitle = "Distribution des degrés";
		    String xAxisLabel = "Le degré";
		    String yAxisLabel = "Le nombre de noeuds";
		 
		    XYDataset dataset = createDataset();
		 
		    boolean showLegend = false;
		    boolean createURL = false;
		    boolean createTooltip = false;
		     
		    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
		            xAxisLabel, yAxisLabel, dataset,
		            PlotOrientation.VERTICAL, showLegend, createTooltip, createURL);
		    XYPlot plot = chart.getXYPlot();
		    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setSeriesPaint(0, Color.RED);
		    renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		    plot.setRenderer(renderer);
		    plot.setOutlinePaint(Color.BLUE);
		    plot.setOutlineStroke(new BasicStroke(2.0f));
		    plot.setBackgroundPaint(Color.DARK_GRAY);
		    plot.setRangeGridlinesVisible(true);
		    plot.setRangeGridlinePaint(Color.BLACK);
		    plot.setDomainGridlinesVisible(true);
		    plot.setDomainGridlinePaint(Color.BLACK);
		    return new ChartPanel(chart);
	    }
	 
	    private XYDataset createDataset() {
	    	XYSeriesCollection dataset = new XYSeriesCollection();
	        XYSeries series1 = new XYSeries("power law degree");
	        for(Map.Entry<Integer,List<Integer>> entry:prefAttach.entrySet())
	        {
	        	series1.add((long)entry.getKey(),(long)entry.getValue().size());
	        }
	        dataset.addSeries(series1);
	        return dataset;
	    }
}
