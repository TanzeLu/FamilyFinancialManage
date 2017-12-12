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

public class Modify_record extends JFrame{
	private JTextField record;
	private JComboBox type;
	private JTextField count;
	private JTextField about;
	private function dao;
	Modify_record(Map<String,Object> map){
		super();
		this.setSize(600,400);
		setTitle("��¼�޸�");
		this.setLocation(383, 184);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel panel_center=new JPanel();
		panel_center.setLayout(new GridLayout(4,2,20,40));
		JLabel label1=new JLabel("����");
		record=new JTextField();
		record.setEditable(false);
	//	record.setS
		JLabel label2=new JLabel("��������");
		type=new JComboBox();
	/*	String[] sort2={"����","����","����","����","����"};
		for(int j=0;j<sort2.length;j++){
			type.addItem(sort2[j]);
		}
		
	/*	record.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getItem().equals("֧��")){
					type.removeAllItems();
					String[] sort2={"����","����","����","����","����"};
					for(int j=0;j<sort2.length;j++){
						type.addItem(sort2[j]);
					}
				}else{
					type.removeAllItems();
					String[] sort3={"��������","����","���","����"};
					for(int j=0;j<sort3.length;j++){
						type.addItem(sort3[j]);
					}
				}
			}
		});*/
		
		
		JLabel label3=new JLabel("���");
		count=new JTextField(20);
		JLabel label4=new JLabel("��ע");
		about=new JTextField(20);
		JButton modify=new JButton("ȷ��");
		JButton exit=new JButton("ȡ��");
		modify.setPreferredSize(new Dimension(70,30));
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
		panel_bottom.add(modify);
		panel_bottom.add(exit);
		panel_bottom.setLayout(flowlayout);
		add(panel_bottom,BorderLayout.SOUTH);
		
		modify.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String count_string=count.getText();
				if(count_string.equals(""))
					JOptionPane.showMessageDialog(null, "����Ϊ��");
				else if(count_string.length()>=11){
					JOptionPane.showMessageDialog(null, "�����̫���ˣ�����������");
					count.setText("");
				}
					
				dao=new function();
				Map<String,String> map2=new HashMap<String,String>();
				    map2.put("keyid", map.get("keyid").toString());
				    map2.put("divide", map.get("divide").toString());
				    map2.put("num", count.getText());
				    map2.put("about",about.getText());
				    map2.put("type",gettype());
				    JOptionPane.showMessageDialog(null, dao.Modify_record(map2));
				    setVisible(false);
			}
			
		});
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳�ô��", "��ʾ",JOptionPane.YES_NO_OPTION);
                if(n==0)
                	setVisible(false);
			}
			
		});
		init(map);
	}
	
	public  void init(Map<String,Object> map){
		if(map.get("divide").equals(0))
			record.setText("֧��");
		else
			record.setText("����");
	//	type.setSelectedIndex(getTypeindex(map.get("type").toString()));
	    if(record.getText().equals("֧��")){
	    	String[] sort2={"����","����","����","����","����"};
			for(int j=0;j<sort2.length;j++){
				type.addItem(sort2[j]);
			}
	    }else{
	    	String[] sort3={"��������","����","���","����"};
			for(int j=0;j<sort3.length;j++){
				type.addItem(sort3[j]);
			}
	    }
		type.setSelectedItem(map.get("type"));
		count.setText(map.get("num").toString());
	    about.setText(map.get("about").toString());
	    
	}
/*	public int getSort(){
		int sort;
		if(record.getSelectedItem().equals("֧��"))
			sort=0;
		else
			sort=1;
		return sort;
	}*/
	public String gettype(){
		return type.getSelectedItem().toString();
	}
	public int getTypeindex(String type){
		if(type.equals("����"))
			return 0;
		else if(type.equals("����"))
			return 1;
		else if(type.equals("����"))
			return 2;
		else if(type.equals("����"))
			return 3;
		else
			return 4;
	}

}
