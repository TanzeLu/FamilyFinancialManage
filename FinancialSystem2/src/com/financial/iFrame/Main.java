package com.financial.iFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;

import com.financial.dao.function;

import com.financial.model.*;


public class Main extends JFrame{
	private JLabel pic;
	private JButton add;
	private JButton modify;
	private JButton delete;
	private JLabel income;
	private JLabel outcome;
	private JLabel statistic;
	private JLabel budget;
	private JLabel exit;
	private JTable table_outcome;
	private JTable table_income;
	private JScrollPane panel_outcome;
	private JScrollPane panel_income;
	private JPanel panel_statistic;
	private JPanel panel_budget;
	private JPanel panel_center;
	private String panel_name="outcome";
	
	private float[] leftcolumn=Color.RGBtoHSB(63,71,81,null);
	private Color color_left=Color.getHSBColor(leftcolumn[0], leftcolumn[1], leftcolumn[2]);
	private float[] leftfont=Color.RGBtoHSB(219,219,219,null);
	private Color color_leftfont=Color.getHSBColor(leftfont[0], leftfont[1], leftfont[2]);
	private float[] northback=Color.RGBtoHSB(197,35,75,null);
	private Color color_north=Color.getHSBColor(northback[0], northback[1], northback[2]);
	private float[] change_label=Color.RGBtoHSB(235,35,75,null);
	private Color color_change=Color.getHSBColor(change_label[0], change_label[1], change_label[2]);
	
	
//������ݴ洢
	private static function dao=new function();//�������ӷ���������
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model2 = new DefaultTableModel();
	//SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
	String[] recordtitle= {"���","����","����","���","��ע"};//�����

	//��ʼ�����湹��
	Main(){
		super();
		Font font1=new Font("΢���ź�",Font.BOLD,16);
		setTitle("��ͥ�������ϵͳ");
		this.setSize(600, 400);
		this.setLocation(383, 184);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		//����������
		JPanel panel_north=new JPanel();
		panel_north.setPreferredSize(new Dimension(600,60));
		panel_north.setLayout(new GridLayout(1,4));
		panel_north.setBackground(color_north);
		
		ImageIcon image=new ImageIcon("img/top2.png");
		pic=new JLabel();
		pic.setIcon(image);
		pic.setPreferredSize(new Dimension(100,60));
		
		add=new JButton("���");
		add.setHorizontalAlignment(SwingConstants.CENTER);
		add.setFont(font1);
		add.setForeground(color_leftfont);
		add.setBackground(color_north);
		
		modify=new JButton("�޸�");
		modify.setHorizontalAlignment(SwingConstants.CENTER);
		modify.setFont(font1);
		modify.setForeground(color_leftfont);
		modify.setBackground(color_north);
		
	//	modify.setBackground(Color.getHSBColor(60, 100, 85));
		delete=new JButton("ɾ��");
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		delete.setFont(font1);
		delete.setForeground(color_leftfont);
		delete.setBackground(color_north);
		
	//	delete.setBackground(Color.getHSBColor(60, 100, 85));
		panel_north.add(pic);
		panel_north.add(add);
		panel_north.add(modify);
		panel_north.add(delete);
		panel_north.setBackground(color_north);
		
		//��Ӻ���
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int flag;
				if(panel_name.equals("outcome")){
					flag=0;
					Add_record frame=new Add_record(flag);
					frame.setVisible(true);
					
				}else if(panel_name.equals("income")){
					flag=1;
					Add_record frame=new Add_record(flag);
					frame.setVisible(true);
				}	
				else
					JOptionPane.showMessageDialog(null, "�޷���ӣ�");
			}
			
		});
		
		//�޸ĺ���
		modify.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(panel_name.equals("outcome")){
					if(table_outcome.getSelectedRow()>=0){
						//showResults(getOutcome(getResult(Login.getUser().getUser())));
						Map<String,Object> map=getOutcome(getResult(Login.getUser().getUser())).get(table_outcome.getSelectedRow());
						int id=Integer.parseInt(map.get("id").toString());
						map.put("keyid",id);
						Modify_record frame=new Modify_record(map);
						frame.setVisible(true);
					}else
						JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ��");
				}else if(panel_name.equals("income")){
					if(table_income.getSelectedRow()>=0){
						//showResults(getIncome(getResult(Login.getUser().getUser())));
						Map<String,Object> map=getIncome(getResult(Login.getUser().getUser())).get(table_income.getSelectedRow());
						int id=Integer.parseInt(map.get("id").toString());
						map.put("keyid",id);
						Modify_record frame=new Modify_record(map);
						frame.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ��");
					}
				}else
					JOptionPane.showMessageDialog(null, "��ѡ�������֧������ٽ����޸ģ�");
			}
			
		});
		
		//ɾ������
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(panel_name.equals("outcome")){
					if(table_outcome.getSelectedRow()>=0){
						Map<String,Object> map=getOutcome(getResult(Login.getUser().getUser())).get(table_outcome.getSelectedRow());
						int id=Integer.parseInt(map.get("id").toString());
						Map<String,String> params=new HashMap<String,String>();
						params.put("id",String.valueOf(id));
						int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��ô��", "��ʾ",JOptionPane.YES_NO_OPTION);
		                if(n==0)
		                	JOptionPane.showMessageDialog(null, dao.Delete_record(params));
					}else{
						JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����");
					}
				}else if(panel_name.equals("income")){
					if(table_income.getSelectedRow()>=0){
						Map<String,Object> map=getIncome(getResult(Login.getUser().getUser())).get(table_income.getSelectedRow());
						int id=Integer.parseInt(map.get("id").toString());
						Map<String,String> params=new HashMap<String,String>();
						params.put("id",String.valueOf(id));
						int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��ô��", "��ʾ",JOptionPane.YES_NO_OPTION);
		                if(n==0)
		                	JOptionPane.showMessageDialog(null, dao.Delete_record(params));
					}else{
						JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����");
					}
				}else
					JOptionPane.showMessageDialog(null, "��ѡ�������֧������ٽ���ɾ����");
			}
			
		});
		
		//ѡ��������
		JPanel panel_left=new JPanel();
		panel_left.setPreferredSize(new Dimension(148,300));
		panel_left.setLayout(new GridLayout(5,1));
		outcome=new JLabel(" ֧��");
		outcome.setHorizontalAlignment(SwingConstants.CENTER);
		outcome.setFont(font1);
		outcome.setForeground(color_leftfont);
		outcome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		outcome.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		income=new JLabel(" ����");
		income.setHorizontalAlignment(SwingConstants.CENTER);
		income.setFont(font1);
		income.setForeground(color_leftfont);
		income.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		income.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		statistic=new JLabel(" ��ϸ");
		statistic.setHorizontalAlignment(SwingConstants.CENTER);
		statistic.setFont(font1);
		statistic.setForeground(color_leftfont);
		statistic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		statistic.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		budget=new JLabel(" Ԥ��");
		budget.setHorizontalAlignment(SwingConstants.CENTER);
		budget.setFont(font1);
		budget.setForeground(color_leftfont);
		budget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		budget.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		exit=new JLabel(" �˳�");
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		exit.setFont(font1);
		exit.setForeground(color_leftfont);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		ImageIcon image_out=new ImageIcon("img/outcome.png");
		ImageIcon image_in=new ImageIcon("img/in.png");
		ImageIcon image_static=new ImageIcon("img/detail.png");
		ImageIcon image_budget=new ImageIcon("img/yusuan.png");
		ImageIcon image_exit=new ImageIcon("img/exit.png");
		outcome.setIcon(image_out);
		income.setIcon(image_in);
		statistic.setIcon(image_static);
		budget.setIcon(image_budget);
		exit.setIcon(image_exit);
		panel_left.add(outcome);
		panel_left.add(income);
		panel_left.add(statistic);
		panel_left.add(budget);
		panel_left.add(exit);
		panel_left.setBackground(color_left);
		
		//���������	
		JPanel panel_center=new JPanel();
		panel_center.setLayout(new CardLayout());
	    panel_income=new JScrollPane();
	    panel_outcome=new JScrollPane();
		panel_statistic=new JPanel();
		panel_budget=new JPanel();
	//	panel_center.setBackground(Color.getHSBColor(60, 100, 85));
		panel_center.add(panel_outcome, "outcome");
		panel_center.add(panel_income, "income");
		panel_center.add(panel_statistic, "statistic");
		panel_center.add(panel_budget, "budget");
		((CardLayout)panel_center.getLayout()).show(panel_center,"outcome");
		
		
		//��ϸͳ�Ʋ���
		//JPanel panel_1 = new JPanel();
		JSplitPane panel_split=new JSplitPane();
		JPanel jpanel_left=new JPanel();
		JPanel jpanel_right=new JPanel();
		panel_split.add(jpanel_left,JSplitPane.LEFT);
		panel_split.add(jpanel_right,JSplitPane.RIGHT);
		panel_split.setDividerSize(1);
		jpanel_left.setBorder(BorderFactory.createTitledBorder("֧����Ϣ"));
		jpanel_right.setBorder(BorderFactory.createTitledBorder("������Ϣ"));
	    jpanel_left.setPreferredSize(new Dimension(226,250));
	    jpanel_right.setPreferredSize(new Dimension(226,250));
		//	jpanel_left.add(getOutcomeChart(),BorderLayout.CENTER);
		JLabel label1_1=new JLabel("��֧��:");
		label1_1.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField outcome_statistic=new JTextField(10);
	/*	JLabel label1_2=new JLabel("֧���������");
		label1_2.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField outcometype_max=new JTextField(10);
		JLabel label1_3=new JLabel("֧����������");
		label1_3.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField outcometype_min=new JTextField(10);*/
		outcome_statistic.setEditable(false);
	//	outcometype_max.setEditable(false);
	//	outcometype_min.setEditable(false);
		JLabel label2_1=new JLabel("������");
		label2_1.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField income_statistic=new JTextField(10);
		income_statistic.setEditable(false);
		JPanel panel1_1=new JPanel();
		panel1_1.setLayout(new GridLayout(1,2));
		panel1_1.add(label1_1);
		panel1_1.add(outcome_statistic);
		JButton chart=new JButton("�鿴ͼ��");
	/*	panel1_1.add(label1_2);
		panel1_1.add(outcometype_max);
		panel1_1.add(label1_3);
		panel1_1.add(outcometype_min);*/
		jpanel_left.add(panel1_1);
		jpanel_left.add(chart,BorderLayout.SOUTH);
		JPanel panel2_1=new JPanel();
		panel2_1.add(label2_1);
		panel2_1.add(income_statistic);
		jpanel_right.add(panel2_1);
		
		JPanel panel1_2=new JPanel();
		JButton search=new JButton("��ѯ����");
		panel1_2.add(search);
		panel_statistic.add(panel_split,BorderLayout.CENTER);
		panel_statistic.add(panel1_2, BorderLayout.SOUTH);
		search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Search_record frame=new Search_record();
				frame.setVisible(true);
			}
			
		});
		chart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				ChartShow frame=new ChartShow();
				ChartFrame chartFrame=new ChartFrame("֧���������",ChartShow.Chart(getStatistic(Login.getUser().getUser()))); 
			        //chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			    chartFrame.pack(); //�Ժ��ʵĴ�Сչ��ͼ��
			    chartFrame.setLocation(383, 184);
			    chartFrame.setVisible(true);//ͼ���Ƿ�ɼ�
			}
		});

		
	
		//Ԥ��������
		JSplitPane panel_split2=new JSplitPane();
		JPanel jpanel_left2=new JPanel();
		JPanel jpanel_right2=new JPanel();
		panel_split2.add(jpanel_left2,JSplitPane.LEFT);
		panel_split2.add(jpanel_right2,JSplitPane.RIGHT);
		panel_split2.setDividerSize(0);
		jpanel_left2.setBorder(BorderFactory.createTitledBorder("����֧������"));
		jpanel_right2.setBorder(BorderFactory.createTitledBorder("Ԥ����Ϣ"));
	    jpanel_left2.setPreferredSize(new Dimension(226,250));
	    jpanel_right2.setPreferredSize(new Dimension(226,250));
		
		panel_budget.add(panel_split2,BorderLayout.CENTER);
		
		Map<String,Integer> map=getStatistic(Login.getUser().getUser());
		
		JLabel label11=new JLabel("��֧��");
		label11.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Ex_count=new JTextField(10);
		Ex_count.setEditable(false);
		//Ex_count.setText(map.get("ex").toString());
		JLabel label21=new JLabel("����֧��");
		label21.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Edu_count=new JTextField(10);
		Edu_count.setEditable(false);
		//Edu_count.setText(map.get("edu").toString());
		JLabel label31=new JLabel("����֧��");
		label31.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Live_count=new JTextField(10);
		Live_count.setEditable(false);
		//Live_count.setText(map.get("live").toString());
		JLabel label41=new JLabel("����֧��");
		label41.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField shop_count=new JTextField(10);
		shop_count.setEditable(false);
		//shop_count.setText(map.get("shop").toString());
		JLabel label51=new JLabel("����֧��");
		label51.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField travel_count=new JTextField(10);
		travel_count.setEditable(false);
		//travel_count.setText(map.get("travel").toString());
		jpanel_left2.setLayout(new GridLayout(5,2,20,30));
		jpanel_left2.add(label11);
		jpanel_left2.add(Ex_count);
		jpanel_left2.add(label21);
		jpanel_left2.add(Edu_count);
		jpanel_left2.add(label31);
		jpanel_left2.add(Live_count);
		jpanel_left2.add(label41);
		jpanel_left2.add(shop_count);
		jpanel_left2.add(label51);
		jpanel_left2.add(travel_count);
		
		
		JLabel label1=new JLabel("��Ԥ��");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Ex_budget=new JTextField(20);
		Ex_budget.setEditable(true);
		JLabel label2=new JLabel("����Ԥ��");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Edu_budget=new JTextField(20);
		Edu_budget.setEditable(true);
		JLabel label3=new JLabel("����Ԥ��");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField Live_budget=new JTextField(20);
		Live_budget.setEditable(true);
		JLabel label4=new JLabel("����Ԥ��");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField shop_budget=new JTextField(20);
		shop_budget.setEditable(true);
		JLabel label5=new JLabel("����Ԥ��");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField travel_budget=new JTextField(20);
		travel_budget.setEditable(true);
		JButton modify2=new JButton("�޸�");
		JButton exit2=new JButton("ȡ��");
		jpanel_right2.setLayout(new GridLayout(5,2,10,20));
		jpanel_right2.add(label1);
		jpanel_right2.add(Ex_budget);
		jpanel_right2.add(label2);
		jpanel_right2.add(Edu_budget);
		jpanel_right2.add(label3);
		jpanel_right2.add(Live_budget);
		jpanel_right2.add(label4);
		jpanel_right2.add(shop_budget);
		jpanel_right2.add(label5);
		jpanel_right2.add(travel_budget);
		JPanel panel_button=new JPanel();
		FlowLayout flowlayout=new FlowLayout();
		flowlayout.setAlignment(FlowLayout.RIGHT);
		panel_button.setLayout(flowlayout);
		panel_button.add(modify2);
		panel_button.add(exit2);
		panel_budget.add(panel_button, BorderLayout.SOUTH);
		modify2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Map<String,String> map=new HashMap<String,String>();
				map.put("Ex_budget", Ex_budget.getText());
				map.put("Edu_budget", Edu_budget.getText());
				map.put("Live_budget", Live_budget.getText());
				map.put("shop_budget", shop_budget.getText());
				map.put("travel_budget", travel_budget.getText());
				map.put("userid", Login.getUser().getUser());
				JOptionPane.showMessageDialog(null, dao.Modify_budget(map));
			}
			
		});
		
		//�����ܿ�������
		//Ĭ��֧������
		model.setColumnIdentifiers(recordtitle);
		showResults(getOutcome(getResult(Login.getUser().getUser())));
		table_outcome=new JTable();
		table_outcome.setModel(model);
		table_outcome.setFocusable(isFocusable());
		panel_outcome.setViewportView(table_outcome);
		//��������
		
		outcome.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// ���������
				add.setVisible(true);
				modify.setVisible(true);
				delete.setVisible(true);
				outcome.setForeground(color_change);
				income.setForeground(color_leftfont);
				statistic.setForeground(color_leftfont);
				budget.setForeground(color_leftfont);
				exit.setForeground(color_leftfont);
				model.setColumnIdentifiers(recordtitle);
				//getOutcome(getResult(Login.getUser().getUser()))
				showResults(getOutcome(getResult(Login.getUser().getUser())));
				table_outcome=new JTable();
				table_outcome.setFocusable(isFocusable());
				panel_outcome.setViewportView(table_outcome);
				table_outcome.setModel(model);
				((CardLayout)panel_center.getLayout()).show(panel_center,"outcome");
				panel_name="outcome";
			}
			public void mouseEntered(MouseEvent e) {
				// �����������
				
			}
			public void mouseExited(MouseEvent e) {
				// ��������뿪
				
			}
			public void mousePressed(MouseEvent e) {
				// ������갴��
				
			}
			public void mouseReleased(MouseEvent e) {
				// ��������ͷ�
				
			}
		});
		income.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// ���������
				add.setVisible(true);
				modify.setVisible(true);
				delete.setVisible(true);
				income.setForeground(color_change);
				outcome.setForeground(color_leftfont);
				statistic.setForeground(color_leftfont);
				budget.setForeground(color_leftfont);
				exit.setForeground(color_leftfont);
				model2.setColumnIdentifiers(recordtitle);
				showResults(getIncome(getResult(Login.getUser().getUser())));
				table_income=new JTable();
		        table_income.setModel(model2);
				table_income.setFocusable(isFocusable());
				panel_income.setViewportView(table_income);
				((CardLayout)panel_center.getLayout()).show(panel_center,"income");
				panel_name="income";
			}
			public void mouseEntered(MouseEvent e) {
				// �����������
				
			}
			public void mouseExited(MouseEvent e) {
				// ��������뿪
			}
			public void mousePressed(MouseEvent e) {
				// ������갴��
				
			}
			public void mouseReleased(MouseEvent e) {
				// ��������ͷ�
				
			}
	    
		});
		
		
		
		//��ϸͳ��
		statistic.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				add.setVisible(false);
				modify.setVisible(false);
				delete.setVisible(false);
				// TODO Auto-generated method stub
				statistic.setForeground(color_change);
				income.setForeground(color_leftfont);
				outcome.setForeground(color_leftfont);
				budget.setForeground(color_leftfont);
				exit.setForeground(color_leftfont);
				Map<String,String> params=new HashMap<String,String>();
				Date d = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				String date=String.valueOf(c.get(Calendar.YEAR))+"-"+String.valueOf(c.get(Calendar.MONTH)+1);
				params.put("userid", Login.getUser().getUser());
				params.put("date", date);
				Map<String,Object> map=dao.getStatistic(params);
				outcome_statistic.setText(map.get("outcome").toString());
				income_statistic.setText(map.get("income").toString());
				((CardLayout)panel_center.getLayout()).show(panel_center,"statistic");
				panel_name="statistic";
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//Ԥ��ͳ��
		budget.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				add.setVisible(false);
				modify.setVisible(false);
				delete.setVisible(false);
				budget.setForeground(color_change);
				income.setForeground(color_leftfont);
				statistic.setForeground(color_leftfont);
				outcome.setForeground(color_leftfont);
				exit.setForeground(color_leftfont);
				Map<String,String> params=new HashMap();
				params.put("userid",Login.getUser().getUser());
				Map<String,Object> result=dao.getBudget(params);
				if(result.isEmpty()){
					int n = JOptionPane.showConfirmDialog(null, "�㻹û�����Ԥ����Ϣ��ǰȥ��Ӱɣ�", "��ʾ",JOptionPane.YES_NO_OPTION);
					if(n==0){
						Add_budget frame=new Add_budget();
						frame.setVisible(true);
					}
				}else{
					Ex_budget.setText(result.get("Ex_budget").toString());
					Edu_budget.setText(result.get("Edu_budget").toString());
					Live_budget.setText(result.get("Live_budget").toString());
					shop_budget.setText(result.get("shop_budget").toString());
					travel_budget.setText(result.get("travel_budget").toString());
					((CardLayout)panel_center.getLayout()).show(panel_center,"budget");
					panel_name="budget";
				}
				Map<String,Integer> map=getStatistic(Login.getUser().getUser());
				Ex_count.setText(map.get("ex").toString());
				Edu_count.setText(map.get("edu").toString());
				Live_count.setText(map.get("live").toString());
				shop_count.setText(map.get("shop").toString());
				travel_count.setText(map.get("travel").toString());
				if(!isOver(result).equals("")){
					JOptionPane.showMessageDialog(null, isOver(result));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		exit.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// ���������
				exit.setForeground(color_change);
				income.setForeground(color_leftfont);
				statistic.setForeground(color_leftfont);
				outcome.setForeground(color_leftfont);
				budget.setForeground(color_leftfont);
				int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳�ô��", "��ʾ",JOptionPane.YES_NO_OPTION);
				if(n==0){
					setVisible(false);
					Login frame=new Login();
					frame.setVisible(true);
				}
					
			}
			public void mouseEntered(MouseEvent e) {
				// �����������
				
			}
			public void mouseExited(MouseEvent e) {
				// ��������뿪
			}
			public void mousePressed(MouseEvent e) {
				// ������갴��
				
			}
			public void mouseReleased(MouseEvent e) {
				// ��������ͷ�
				
			}
		});
		
		//���岼��
		this.add(panel_north,BorderLayout.NORTH);
		add(panel_left,BorderLayout.WEST);
		add(panel_center,BorderLayout.CENTER);
	}
	
	//list���չʾ�ڱ����
	public void showResults(List<Map<String,Object>> list){
		int id=1;
		while(model.getRowCount()>0)
			model.removeRow(0);
		while(model2.getRowCount()>0)
			model2.removeRow(0);
		for(int i=0;i<list.size();i++){
			String[] results = new String[5];
			results[0]=String.valueOf(id++);
			results[1]=list.get(i).get("date").toString();
			results[2]=list.get(i).get("type").toString();
			results[3]=list.get(i).get("num").toString();
			results[4]=list.get(i).get("about").toString();
			if(list.get(i).get("divide").equals(0))
				model.addRow(results);
			else
				model2.addRow(results);	
		}
		
	}
	
	//��ȡ���û����м�¼
	public static List<Map<String,Object>> getResult(String userid){
		Map<String,String> params=new HashMap<String,String>();
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		String date=String.valueOf(c.get(Calendar.YEAR))+"-"+String.valueOf(c.get(Calendar.MONTH)+1);
		params.put("userid", userid);
		params.put("date", date);
		List<Map<String,Object>> re=dao.getList(params);
		return re;
	}
	
	//��ȡ���û�֧����¼
	public static List<Map<String,Object>> getOutcome(List<Map<String,Object>> re){
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		System.out.println("�����б�"+result);
		for(Map m:re){
			if(m.get("divide").equals(0))
				result.add(m);
		}
		return result;	
	}
	
	
	//��ȡ���û������¼
	public List<Map<String,Object>> getIncome(List<Map<String,Object>> re){
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		for(Map m:re){
			if(m.get("divide").equals(1))
				result.add(m);
		}
		return result;	
	}
	
	//��װ�����û���֧������
	public Map<String,Integer> getStatistic(String userid){
		Map<String,Integer> result=new HashMap<String,Integer>();
		List<Map<String,Object>> list=getOutcome(getResult(userid));
		int edu_count=0;
		int live_count=0;
		int shop_count=0;
		int travel_count=0;
		for(Map m:list){
			int count=Integer.parseInt(m.get("num").toString());
			if(m.get("type").equals("����"))
				edu_count=edu_count+count;
			else if(m.get("type").equals("����"))
				live_count=live_count+count;
			else if(m.get("type").equals("����"))
				shop_count=shop_count+count;
			else if(m.get("type").equals("����"))
				travel_count=travel_count+count;
		}
		result.put("ex", edu_count+live_count+shop_count+travel_count);
		result.put("edu",edu_count);
		result.put("live",live_count);
		result.put("shop",shop_count);
		result.put("travel",travel_count);
		return result;
		
	}
	
	//�ж��Ƿ񳬳�Ԥ��
	public String isOver(Map<String,Object> map){
		String result="";
		Map<String,Integer> list=getStatistic(Login.getUser().getUser());
		if(!map.isEmpty()&&!list.isEmpty()){
			int edu_count=Integer.parseInt(map.get("Edu_budget").toString())-list.get("edu");
			int live_count=Integer.parseInt(map.get("Live_budget").toString())-list.get("live");
			int shop_count=Integer.parseInt(map.get("shop_budget").toString())-list.get("shop");
			int travel_count=Integer.parseInt(map.get("travel_budget").toString())-list.get("travel");
			if(edu_count<=0)
			    result="��������֧���Ѿ���֧��";
			else if(edu_count<100){
				result="��������֧��������֧��";
			}
			if(live_count<=0)
			    result+="��������֧���Ѿ���֧��";
			else if(live_count<100){
				result+="��������֧��������֧��";
			}
			
			if(shop_count<=0)
			    result+="��������֧���Ѿ���֧��";
			else if(shop_count<100){
				result+="��������֧��������֧��";
			}
			if(travel_count<=0)
			    result+="��������֧���Ѿ���֧��";
			else if(travel_count<100){
				result+="��������֧��������֧��";
			}
		}
		return result;
	}
	
	

}
