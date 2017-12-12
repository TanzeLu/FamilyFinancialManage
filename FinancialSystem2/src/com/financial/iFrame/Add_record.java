package com.financial.iFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.financial.dao.function;

public class Add_record extends JFrame{
	private JComboBox record;
	private JComboBox type;
	private function dao;
	//添加窗体
	Add_record(int flag){
		super();
		this.setSize(600,400);
		setTitle("记录添加");
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocation(383, 184);
		JLabel label1=new JLabel("类型");
		record=new JComboBox();
		String[] sort={"支出","收入"};
		JLabel label2=new JLabel("具体类型");
		type=new JComboBox();
		//支出收入默认显示不同，两个下拉框判定
		if(flag==0){
			for(int j=0;j<sort.length;j++){
				record.addItem(sort[j]);
			}
			String[] sort2={"生活","教育","购物","旅游","其他"};
			for(int j=0;j<sort2.length;j++){
				type.addItem(sort2[j]);
			}

		}else{
			String[] sort3={"基本工资","奖金","外快","其他"};
			for(int j=0;j<sort3.length;j++){
				type.addItem(sort3[j]);
			}
			for(int j=1;j>=0;j--){
				record.addItem(sort[j]);
			}
		}
			

		//监听默认显示
		record.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getItem().equals("支出")){
					type.removeAllItems();
					String[] sort2={"生活","教育","购物","旅游","其他"};
					for(int j=0;j<sort2.length;j++){
						type.addItem(sort2[j]);
					}
				}else{
					type.removeAllItems();
					String[] sort3={"基本工资","奖金","外快","其他"};
					for(int j=0;j<sort3.length;j++){
						type.addItem(sort3[j]);
					}
				}
			}
		});
		
		
		
		JPanel panel_center=new JPanel();
		panel_center.setLayout(new GridLayout(4,2,20,30));
		JLabel label3=new JLabel("金额");
		JTextField count=new JTextField(20);
		JLabel label4=new JLabel("备注");
		JTextField about=new JTextField(20);
		JButton add=new JButton("添加");
		JButton exit=new JButton("取消");
		add.setPreferredSize(new Dimension(70,30));
		exit.setPreferredSize(new Dimension(70,30));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_center.add(label1);
		panel_center.add(record);
		panel_center.add(label2);
		panel_center.add(type);
		panel_center.add(label3);
		panel_center.add(count);
		panel_center.add(label4);
		panel_center.add(about);
		add(panel_center,BorderLayout.CENTER);
		JPanel panel_bottom=new JPanel();
		FlowLayout flowlayout=new FlowLayout();
		flowlayout.setAlignment(FlowLayout.RIGHT);
		panel_bottom.add(add);
		panel_bottom.add(exit);
		panel_bottom.setLayout(flowlayout);
		add(panel_bottom,BorderLayout.SOUTH);
		//添加函数
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String count_string=count.getText();
				if(count_string.equals("")){
					JOptionPane.showMessageDialog(null, "金额不能为空！");
					count.isFocusable();
				}else{
					if(count_string.length()>=11){
						JOptionPane.showMessageDialog(null, "金额太大啦！！！");
					    count.setText("");
					}else if(!isInteger(count.getText())){
						JOptionPane.showMessageDialog(null, "金额请输入整数");
					    count.setText("");
					}
						else if(about.getText().length()>20){
					
						JOptionPane.showMessageDialog(null, "备注太长了！！！");
					    about.setText("");
					}
					else{
						dao=new function();
						Map<String,String> params=new HashMap<String,String>();
						params.put("userid", Login.getUser().getUser());
						params.put("divide",String.valueOf(getSort()));
						params.put("type", gettype());
						params.put("num",count.getText());
						params.put("about",about.getText());
						JOptionPane.showMessageDialog(null, dao.Add_record(params));
						setVisible(false);
					}
					
				}
			}
			
		});
		
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "确定要退出么？", "提示",JOptionPane.YES_NO_OPTION);
                if(n==0)
                	setVisible(false);
			}
			
		});
	}
	
	//获得大的类型 支出；收入
	public int getSort(){
		int sort;
		if(record.getSelectedItem().equals("支出"))
			sort=0;
		else
			sort=1;
		return sort;
	}
	//获得具体类型
	public String gettype(){
		return type.getSelectedItem().toString();
	}
	
	//判断是否为整数
	public boolean isInteger(String num){
		return num.matches("[0-9]+");
	}

}
