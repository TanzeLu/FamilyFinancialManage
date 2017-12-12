package com.financial.iFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import com.financial.dao.function;



public class Register extends JFrame{
	private JTextField id;
	private JTextField name;
	private JPasswordField pswd;
	private JPasswordField repswd;
	private JTextField userphon;
	private function dao=new function();
	//ע����洰��
	public Register()
	{
		super();
		this.setTitle("ע��");
		setSize(500,300);
		setVisible(true);
		setLocation(433,234);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JLabel label1=new JLabel("�û���");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		id=new JTextField(20);
		JLabel label4=new JLabel("����");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		name=new JTextField(20);
		JLabel label2=new JLabel("����");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		pswd=new JPasswordField(20);
		JLabel label3=new JLabel("ȷ������");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		repswd=new JPasswordField(20);
		JLabel label5=new JLabel("��ϵ�绰");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		userphon=new JTextField(20);
		JButton register=new JButton("ע��");
		panel1.setLayout(new GridLayout(6,2,5,20));
		panel1.add(label1);
		panel1.add(id);
		panel1.add(label4);
		panel1.add(name);
		panel1.add(label2);
		panel1.add(pswd);
		panel1.add(label3);
		panel1.add(repswd);
		panel1.add(label5);
		panel1.add(userphon);
		panel2.add(register);
		add(panel1,BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
		register.addActionListener(new RegisterAction());	
	}
	
	//ע�ᴥ������
	class RegisterAction implements ActionListener{
		public void actionPerformed(final ActionEvent e){
			String userid=id.getText();
			String username=name.getText();
			String pass=pswd.getText();
			String repass=repswd.getText();
			String phon=userphon.getText();
			String msg="";
			Map<String,String> params=new HashMap<String,String>();
			params.put("username", userid);
			params.put("password", pass);
			params.put("tel", phon);
			if(userid==null||username==null||pass==null||phon==null)
			{
				JOptionPane.showMessageDialog(null, "����Ϊ��");
			}else if(userid.length()>20){
				JOptionPane.showMessageDialog(null, "�û������ܳ���20λ��");
			    id.setText("");
			    id.requestFocus();
			}else if(!pass.equals(repass)){
				JOptionPane.showMessageDialog(null,"ǰ�������������벻һ��");	
			    repswd.setText("");
			    repswd.requestFocus();
			}else if(phon.length()>13){
				JOptionPane.showMessageDialog(null,"��ϵ��ʽ���ܳ���13λ��");
				userphon.setText("");
				userphon.requestFocus();
			}else if(!phon.matches("[0-9]+")){
				JOptionPane.showMessageDialog(null,"��ϵ��ʽ��Ҫ����������ַ�");	
				userphon.setText("");
				userphon.requestFocus();
			}else{
				msg=dao.register(params);
				JOptionPane.showMessageDialog(null, msg);
			}
			
			if(msg.equals("�û����Ѵ��ڣ�"))
			{
				id.setText("");
				id.setFocusable(true);
				id.requestFocus();
			}
			if(msg.equals("ע��ɹ�")){
				Login.setUser(userid);
				Main frame=new Main();
				frame.setVisible(true);
				Register.this.setVisible(false);
			}
			
		}

}

}

