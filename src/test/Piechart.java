/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author DELL
 */

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
public class Piechart {
    private PieDataset createDataset( int rental, int food, int transport, int others ) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Rental", rental);
        dataset.setValue("Food&Drink", food);
        dataset.setValue("Transportation", transport);
        dataset.setValue("Others", others);
        return dataset;
    }

    private JFreeChart createPieChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Money Report",
                dataset, true, true, true);
        return chart;
    }

    public Piechart( int rental, int food, int transport, int others ) {
        JFreeChart pieChart = createPieChart(createDataset(rental, food, transport, others));
        ChartPanel chartPanel = new ChartPanel(pieChart);
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setSize(400, 280);
        frame.setLocation(700,255);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
