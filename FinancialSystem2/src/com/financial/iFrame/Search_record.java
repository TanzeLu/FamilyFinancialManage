package com.financial.iFrame;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.financial.dao.function;
import com.financial.model.Calender;

public class Search_record extends JFrame{
	private String[] recordtitle= {"编号","日期","类型","金额","备注"};//表格项
	private static function dao=new function();
	private JComboBox year_choose;
	private JComboBox month_choose;
	private int year_current=0;
	private int month_current=0;
	@SuppressWarnings("deprecation")
	public Search_record(){
		super();
		this.setSize(600,400);
		this.setTitle("记录查询");
		this.setLayout(new BorderLayout());
		this.setLocation(383, 184);
		JPanel panel_north=new JPanel();
		JLabel label1=new JLabel("请选择查询日期");
		Integer[] year=new Integer[10];
		Integer[] month=new Integer[12];
		Date d=new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		year_current=c.get(Calendar.YEAR);
		month_current=c.get(Calendar.MONTH)+1;
		year_choose=new JComboBox();
		month_choose=new JComboBox();
		for(int i=0;i<10;i++){
			year[i]=year_current--;
			year_choose.addItem(year[i]);
		}
		year_current+=10;
		for(int j=0;j<12;j++){
			month[j]=(month_current++)%12;
			if(month[j]==0)
				month[j]=12;
			month_choose.addItem(month[j]);
		}
		panel_north.add(label1);
		panel_north.add(year_choose);
		panel_north.add(month_choose);
		add(panel_north,BorderLayout.NORTH);
		JScrollPane panel_center=new JScrollPane();
		//List<Map<String,Object>> list=getResult(Login.getUser().getUser(),getYear(),getMonth());
		//JTable table=new JTable(showResult(list),recordtitle);
		//panel_center.setViewportView(table);
		add(panel_center,BorderLayout.CENTER);
		JButton search=new JButton("查询");
		add(search,BorderLayout.SOUTH);
		search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Map<String,Object>> list=getResult(Login.getUser().getUser(),getYear(),getMonth());
				if(list.isEmpty())
					JOptionPane.showMessageDialog(null, "所选月份无记录");
				//else{
				JTable table=new JTable(showResult(list),recordtitle);
				panel_center.setViewportView(table);
				//}
			}
			
		});
	}
	public Object[][] showResult(List<Map<String,Object>> list){
		int id=1;
		String divide="";
		Object[][] results = new Object[list.size()][6];
		for(int i=0;i<list.size();i++){
			if(list.get(i).get("Divide").equals(0))
				divide="支出";
			else
				divide="收入";
			
			results[i][0]=String.valueOf(id++);
			results[i][1]=list.get(i).get("date").toString();
			results[i][2]=divide;
			results[i][3]=list.get(i).get("type").toString();
			results[i][4]=list.get(i).get("num").toString();
			results[i][5]=list.get(i).get("about").toString();
			
		}
		return results;
		
	}
	public static List<Map<String,Object>> getResult(String userid,int year,int month){
		Map<String,String> params=new HashMap<String,String>();
		String date=String.valueOf(year)+"-"+String.valueOf(month);
		params.put("userid", userid);
		params.put("date", date);
		System.out.println(date);
		List<Map<String,Object>> re=dao.getList(params);
		return re;
	}
	
	public int getYear(){
		System.out.println("日期"+year_current);
		return year_current-year_choose.getSelectedIndex();
	}
	public int getMonth(){
		
		    int month=(month_current+month_choose.getSelectedIndex())%12;
		    if(month==0)
		    	month=12;
		    return month;
	}
	
	
	



}
