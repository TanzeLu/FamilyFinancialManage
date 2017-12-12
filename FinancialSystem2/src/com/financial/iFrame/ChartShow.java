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
		
        DefaultPieDataset dpd=new DefaultPieDataset(); //����һ��Ĭ�ϵı�ͼ
        dpd.setValue("����", Integer.parseInt(map.get("edu").toString()));  //��������
        dpd.setValue("����",Integer.parseInt(map.get("live").toString()));
        dpd.setValue("����", Integer.parseInt(map.get("shop").toString()));
        dpd.setValue("����",Integer.parseInt(map.get("travel").toString()));
        System.out.println(map);
        JFreeChart chart=ChartFactory.createPieChart("������֧���������",dpd,true,true,false);
        //���Բ�����API�ĵ�,��һ�������Ǳ��⣬�ڶ���������һ�����ݼ���������������ʾ�Ƿ���ʾLegend�����ĸ�������ʾ�Ƿ���ʾ��ʾ�������������ʾͼ���Ƿ����URL
        Font titleFont = new Font("����", Font.BOLD, 16);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(titleFont);// Ϊ��������������  
          
        Font plotFont = new Font("����", Font.PLAIN, 14);  
        PiePlot plot = (PiePlot) chart.getPlot();  
        plot.setLabelFont(plotFont); // Ϊ��ͼԪ������������  
          
        Font LegendFont = new Font("����", Font.PLAIN, 16);  
        LegendTitle legend = chart.getLegend(0);  
        legend.setItemFont(LegendFont);// Ϊͼ��˵���������� 
        return chart;
	} 
	
}