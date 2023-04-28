/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viz;

/**
 *
 * @author ellen
 */
import bridges.base.LineChart;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class BridgesLineChart {
    public static void linechart(double[] X, 
            ArrayList<ArrayList<Double>> Ys, String[] Y_legends, 
            int bridges_task_id, String bridges_user_id,
            String bridges_api_key,String title) {
        Bridges bridges = new Bridges(bridges_task_id, bridges_user_id, bridges_api_key);

        LineChart plot = new LineChart();
        plot.setTitle(title);

        for (int j = 0; j < Y_legends.length; j++){
            double y[] = new double[X.length];
            for (int i = 0; i < X.length; i++)
                y[i] = Ys.get(i).get(j);
            plot.setDataSeries(Y_legends[j], X, y);
            if (j == 0)
                bridges.setDataStructure(plot);
            
            try{
                bridges.visualize();
            
                if (j == Y_legends.length - 1){
                    plot.toggleLogarithmicX(true);
                    bridges.visualize();

                    plot.toggleMouseTrack(true);
                    plot.toggleLogarithmicY(true);

                    bridges.visualize(); 
                }            
            } catch (Exception ex){
                ex.printStackTrace();
                return;
            }
        }
    }
}
