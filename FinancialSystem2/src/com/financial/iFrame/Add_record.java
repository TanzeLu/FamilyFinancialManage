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
	//��Ӵ���
	Add_record(int flag){
		super();
		this.setSize(600,400);
		setTitle("��¼���");
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocation(383, 184);
		JLabel label1=new JLabel("����");
		record=new JComboBox();
		String[] sort={"֧��","����"};
		JLabel label2=new JLabel("��������");
		type=new JComboBox();
		//֧������Ĭ����ʾ��ͬ�������������ж�
		if(flag==0){
			for(int j=0;j<sort.length;j++){
				record.addItem(sort[j]);
			}
			String[] sort2={"����","����","����","����","����"};
			for(int j=0;j<sort2.length;j++){
				type.addItem(sort2[j]);
			}

		}else{
			String[] sort3={"��������","����","���","����"};
			for(int j=0;j<sort3.length;j++){
				type.addItem(sort3[j]);
			}
			for(int j=1;j>=0;j--){
				record.addItem(sort[j]);
			}
		}
			

		//����Ĭ����ʾ
		record.addItemListener(new ItemListener(){

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
		});
		
		
		
		JPanel panel_center=new JPanel();
		panel_center.setLayout(new GridLayout(4,2,20,30));
		JLabel label3=new JLabel("���");
		JTextField count=new JTextField(20);
		JLabel label4=new JLabel("��ע");
		JTextField about=new JTextField(20);
		JButton add=new JButton("���");
		JButton exit=new JButton("ȡ��");
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
		//��Ӻ���
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String count_string=count.getText();
				if(count_string.equals("")){
					JOptionPane.showMessageDialog(null, "����Ϊ�գ�");
					count.isFocusable();
				}else{
					if(count_string.length()>=11){
						JOptionPane.showMessageDialog(null, "���̫����������");
					    count.setText("");
					}else if(!isInteger(count.getText())){
						JOptionPane.showMessageDialog(null, "�������������");
					    count.setText("");
					}
						else if(about.getText().length()>20){
					
						JOptionPane.showMessageDialog(null, "��ע̫���ˣ�����");
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
				int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳�ô��", "��ʾ",JOptionPane.YES_NO_OPTION);
                if(n==0)
                	setVisible(false);
			}
			
		});
	}
	
	//��ô������ ֧��������
	public int getSort(){
		int sort;
		if(record.getSelectedItem().equals("֧��"))
			sort=0;
		else
			sort=1;
		return sort;
	}
	//��þ�������
	public String gettype(){
		return type.getSelectedItem().toString();
	}
	
	//�ж��Ƿ�Ϊ����
	public boolean isInteger(String num){
		return num.matches("[0-9]+");
	}

}
