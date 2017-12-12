package com.financial.iFrame;

import java.awt.Font;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class ChartShow
{
	public static JFreeChart Chart(Map<String,Integer> map){
		
        DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
        dpd.setValue("教育", Integer.parseInt(map.get("edu").toString()));  //输入数据
        dpd.setValue("生活",Integer.parseInt(map.get("live").toString()));
        dpd.setValue("购物", Integer.parseInt(map.get("shop").toString()));
        dpd.setValue("旅游",Integer.parseInt(map.get("travel").toString()));
        System.out.println(map);
        JFreeChart chart=ChartFactory.createPieChart("各部分支出情况分析",dpd,true,true,false);
        //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
        Font titleFont = new Font("黑体", Font.BOLD, 16);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(titleFont);// 为标题设置上字体  
          
        Font plotFont = new Font("宋体", Font.PLAIN, 14);  
        PiePlot plot = (PiePlot) chart.getPlot();  
        plot.setLabelFont(plotFont); // 为饼图元素设置上字体  
          
        Font LegendFont = new Font("楷体", Font.PLAIN, 16);  
        LegendTitle legend = chart.getLegend(0);  
        legend.setItemFont(LegendFont);// 为图例说明设置字体 
        return chart;
	} 
	
}