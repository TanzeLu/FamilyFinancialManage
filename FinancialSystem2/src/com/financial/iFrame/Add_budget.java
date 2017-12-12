package com.financial.iFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;

import com.financial.dao.function;

public class Add_budget extends JFrame{
	private function dao=new function();
	public Add_budget(){
		super();
		setSize(600,400);
		setLocation(383, 184);
		JLabel label1=new JLabel("总预算");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Ex_budget=new JTextField(20);
		Ex_budget.setEditable(true);
		JLabel label2=new JLabel("教育预算");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Edu_budget=new JTextField(20);
		Edu_budget.setEditable(true);
		JLabel label3=new JLabel("生活预算");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Live_budget=new JTextField(20);
		Live_budget.setEditable(true);
		JLabel label4=new JLabel("购物预算");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField shop_budget=new JTextField(20);
		shop_budget.setEditable(true);
		JLabel label5=new JLabel("旅游预算");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField travel_budget=new JTextField(20);
		travel_budget.setEditable(true);
		JButton modify2=new JButton("添加");
		JButton exit2=new JButton("取消");
		setLayout(new GridLayout(6,2,20,30));
		add(label1);
		add(Ex_budget);
		add(label2);
		add(Edu_budget);
		add(label3);
		add(Live_budget);
		add(label4);
		add(shop_budget);
		add(label5);
		add(travel_budget);
		add(modify2);
		add(exit2);
		modify2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Ex_budget.getText().equals("")||Edu_budget.getText().equals("")||Live_budget.getText().equals("")||shop_budget.getText().equals("")||travel_budget.getText().equals("")){
					JOptionPane.showMessageDialog(null, "预算不能为空");
				}else {
				Map<String,String> map=new HashMap<String,String>();
				map.put("Ex_budget", Ex_budget.getText());
				map.put("Edu_budget", Edu_budget.getText());
				map.put("Live_budget", Live_budget.getText());
				map.put("shop_budget", shop_budget.getText());
				map.put("travel_budget", travel_budget.getText());
				map.put("userid", Login.getUser().getUser());
				JOptionPane.showMessageDialog(null, dao.Modify_budget(map));
			    setVisible(false);
				}
			}
			
		});
	}

}
