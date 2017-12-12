package com.financial.iFrame;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import com.financial.dao.function;
import com.financial.model.User;


public class Login extends JFrame{
	public static int WIDTH=500;
	public static int HEIGHT=300;
	private JButton login;
	private JButton register;
	private JTextField userid;
	private JPasswordField pswd;
	private static User user=new User();
	private float[] colorcolumn=Color.RGBtoHSB(63,71,81,null);
	private Color color_button=Color.getHSBColor(colorcolumn[0], colorcolumn[1], colorcolumn[2]);
/*	private static info.Userinfo user=new info.Userinfo();
	public static info.Userinfo getUser(){
		return user;
	}*/
	public static User getUser(){
		return user;
	}
	public static void setUser(String user){
		Login.user.setUser(user);
	}
	private function dao=new function();
	Login()
	{
		super();
		setTitle("家庭财务管理系统");
		setSize(WIDTH, HEIGHT);
		setLocation(433,234);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		JPanel panel2=new JPanel();
		 login=new JButton("登录");
		 register=new JButton("注册");
		login.setPreferredSize(new Dimension(60,30));
		login.setBackground(color_button);
		login.setForeground(Color.WHITE);
		register.setBackground(Color.getHSBColor(60, 100, 85));
		register.setPreferredSize(new Dimension(60,30));
		register.setBackground(color_button);
		register.setForeground(Color.WHITE);
		panel2.add(login);
		panel2.add(register);
		panel2.setPreferredSize(new Dimension(500,50));
		add(panel2,BorderLayout.SOUTH);
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(0,2,5,50));
		JLabel label1=new JLabel("用户名:");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label2=new JLabel("密码:");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBackground(Color.BLUE);
		label2.setBackground(Color.BLUE);
	    userid=new JTextField(20);
	    pswd=new JPasswordField(20);
		panel.add(label1);
		panel.add(userid);
		panel.add(label2);
		panel.add(pswd);
		ImageIcon image=new ImageIcon("img/top.png");
		JLabel pic=new JLabel();
		pic.setIcon(image);
		add(pic,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		setVisible(true);
		register.addActionListener(new RegisterAction());
	/*	login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String nametext=username.getText();
				String passwordtext=pswd.getText();
				String str=new String(passwordtext);
				boolean x=(nametext.equals("starsong"));
				boolean y=(str.equals("750720"));
				boolean z=(x&&y);
				if(z)
				{
					setVisible(false);
				}
				else if(!z)
				{
					username.setText("");
					pswd.setText("");
				}
			}
		});*/
		login.addActionListener(new LoginAction());
		
	}
	
	public static void main(String[] args)
	{
		Login login=new Login();
	}
	
	class RegisterAction implements ActionListener
	{
		public void actionPerformed(final ActionEvent e){
			Register reg=new Register();
			reg.setVisible(true);
			Login.this.setVisible(false);
		}
	}
	
	class LoginAction implements ActionListener
	{
		public void actionPerformed(final ActionEvent e){
			String idtext=userid.getText();
			String passwordtext=pswd.getText();
			String str=new String(passwordtext);
			user.setUser(idtext);
			Map<String,String> params=new HashMap<String,String>();
			params.put("username", idtext);
			params.put("password", passwordtext);
		//	boolean x=(nametext.equals("starsong"));
		//	boolean y=(str.equals("750720"));
		//	boolean z=(x&&y);
			if(idtext.equals(""))
			{
				JOptionPane.showMessageDialog(null, "用户名不能为空");
				userid.setFocusable(true);
				userid.requestFocus();
			}else if(passwordtext.equals("")){
				JOptionPane.showMessageDialog(null, "密码不能为空");
				pswd.setFocusable(true);
				pswd.requestFocus();
			}else if(idtext.length()>20){
				JOptionPane.showMessageDialog(null, "用户名不能超过20位！");
				userid.setText("");
				userid.setFocusable(true);
				userid.requestFocus();
			}else if(passwordtext.length()>20){
				JOptionPane.showMessageDialog(null, "密码不能超过20位！");
				pswd.setText("");
				pswd.setFocusable(true);
				pswd.requestFocus();
			}
			else{
				System.out.println(dao.login(params));
				JOptionPane.showMessageDialog(null,dao.login(params));
		    if(dao.login(params).equals("登录成功"))
			{
				Main main=new Main();
				main.setVisible(true);
				Login.this.setVisible(false);
			//	user=new Userinfo();
				//user.setUserid(idtext);
			}
		    if(dao.login(params).equals("密码错误")){
		    	pswd.setText("");
		    	pswd.setFocusable(true);
		    	pswd.requestFocus();
		    }
		    if(dao.login(params).equals("用户名不存在")){
		    	userid.setText("");
		    	userid.requestFocus();
		    }
			}
			
			
			
		}
	}

}
