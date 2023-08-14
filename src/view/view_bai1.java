package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.controller_bai1;

public class view_bai1 extends JFrame{
	public JFrame jFrame;
	public JLabel lbltitle;
	public JButton btnsearch,btnexit,btndelete;
	public JTextField txttitle;
	public JTable tb;
	public JPanel p1;
	public DefaultTableModel model;
	
	public view_bai1() {
		GUI();
	}
	public void GUI() {
		jFrame = new JFrame();
		jFrame.setTitle("BAI1");
		jFrame.setSize(600, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(null);
		p1 = new JPanel();
		p1.setBorder(new TitledBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED),"Filter:",TitledBorder.LEFT,TitledBorder.TOP, new Font("Serif", Font.BOLD, 22),Color.RED));
		p1.setLayout(null);
		lbltitle = new JLabel("Title: ");
		lbltitle.setFont(new Font("Serif", Font.PLAIN, 20));
		lbltitle.setBounds(10, 40, 50, 30);
		p1.add(lbltitle);
		p1.setBounds(30, 20, 330, 100);
		btnsearch = new JButton("Search");
		btnsearch.setFont(new Font("Serif", Font.BOLD, 20));
		btnsearch.setBounds(370, 60, 100, 35);
		
		btnexit = new JButton("Exit");
		btnexit.setFont(new Font("Serif", Font.BOLD, 20));
		btnexit.setBounds(480, 60, 70, 35);
		
		txttitle = new JTextField();
		txttitle.setPreferredSize(new Dimension(230,35));
		txttitle.setFont(new Font("Serif", Font.BOLD, 20));
		txttitle.setBounds(70, 40, 230, 35);
		p1.add(txttitle);
		
		String[] colunm = { "Id", "Title", "Price" };
		Object[][] data = { {} };
		model = new DefaultTableModel(data, colunm);
		tb = new JTable(model);
		tb.setFont(new Font("Serif", Font.TRUETYPE_FONT, 17));
		JTableHeader header = tb.getTableHeader();
		Font font = new Font("Serif", Font.BOLD, 20);
		header.setFont(font);
		header.setForeground(Color.RED);
		tb.setPreferredScrollableViewportSize(new Dimension(600, 170));
		JScrollPane scrollPane = new JScrollPane(tb);
		scrollPane.setBounds(32, 140, 520, 165);	
		btndelete = new JButton("Delete");
		btndelete.setFont(new Font("Serif", Font.BOLD, 20));
		btndelete.setBounds(450, 315, 100, 35);
		
		ActionListener ac = new controller_bai1(this);
		btnsearch.addActionListener(ac);
		btnexit.addActionListener(ac);
		btndelete.addActionListener(ac);
		MouseListener mc = new controller_bai1(this);
		tb.addMouseListener(mc);
		jFrame.add(p1);
		jFrame.add(btnsearch);
		jFrame.add(btnexit);
		jFrame.add(scrollPane);
		jFrame.add(btndelete);
		jFrame.setVisible(true);
	}
}
