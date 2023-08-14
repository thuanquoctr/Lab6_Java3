package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.controller_bai2;

public class view_bai2 extends JFrame {
	public JFrame frame;
	public DefaultTableModel model;
	public JTable tb;
	public JTableHeader header;
	public JLabel lblid,txtid,lblname, lbladdress, lblparentname, lblcontactno, lblstandard, lblfees;
	public JTextField txtname, txtparentname, txtcontactno;
	public JTextArea txtaddress;
	public JComboBox cbxstandard, cbxfees;
	public JButton btnnew, btninsert, btnedit, btnupdate, btnnext, btnprevious, btndelete, btnexit;
	

	public view_bai2() {
		GUI();
	}

	public void GUI() {
		frame = new JFrame();
		frame.setTitle("BAI2");
		frame.setSize(960, 530);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(null);

		String[] colunm = { "Name", "Standard" };
		String[][] data = { {}, {}, {}, {} };
		model = new DefaultTableModel(data, colunm);
		tb = new JTable(model);
		tb.setFont(new Font("Serif", Font.TRUETYPE_FONT, 17));
		header = tb.getTableHeader();
		header.setFont(new Font("Serif", Font.BOLD, 20));
		header.setForeground(Color.RED);
		JScrollPane jScrollPane = new JScrollPane(tb);
		jScrollPane.setBounds(10, 10, 400, 440);
		frame.add(jScrollPane);

		Font font = new Font("Serif", Font.BOLD, 20);
		
		lblid = new JLabel("ID :");
		lblid.setFont(font);
		lblid.setForeground(Color.BLUE);
		lblid.setBounds(820, 20, 100, 30);
		frame.add(lblid);
		
		txtid = new JLabel();
		txtid.setFont(font);
		txtid.setForeground(Color.RED);
		txtid.setBounds(870, 20, 100, 30);
		frame.add(txtid);
		
		lblname = new JLabel("Name :");
		lblname.setFont(font);
		lblname.setForeground(Color.BLUE);
		lblname.setBounds(510, 20, 100, 30);
		frame.add(lblname);

		lbladdress = new JLabel("Address :");
		lbladdress.setFont(font);
		lbladdress.setForeground(Color.BLUE);
		lbladdress.setBounds(485, 70, 100, 30);
		frame.add(lbladdress);

		lblparentname = new JLabel("ParentName :");
		lblparentname.setFont(font);
		lblparentname.setForeground(Color.BLUE);
		lblparentname.setBounds(450, 180, 120, 30);
		frame.add(lblparentname);

		lblcontactno = new JLabel("ContactNo :");
		lblcontactno.setFont(font);
		lblcontactno.setForeground(Color.BLUE);
		lblcontactno.setBounds(465, 230, 120, 30);
		frame.add(lblcontactno);

		lblstandard = new JLabel("Standard :");
		lblstandard.setFont(font);
		lblstandard.setForeground(Color.BLUE);
		lblstandard.setBounds(475, 280, 120, 30);
		frame.add(lblstandard);

		lblfees = new JLabel("Fees :");
		lblfees.setFont(font);
		lblfees.setForeground(Color.BLUE);
		lblfees.setBounds(515, 330, 120, 30);
		frame.add(lblfees);

		txtname = new JTextField();
		txtname.setPreferredSize(new Dimension(220, 35));
		txtname.setFont(font);
		txtname.setBounds(580, 20, 220, 35);
		frame.add(txtname);

		txtaddress = new JTextArea();
		txtaddress.setPreferredSize(new Dimension(280, 90));
		txtaddress.setFont(font);
		txtaddress.setBounds(580, 75, 280, 90);
		frame.add(txtaddress);

		txtparentname = new JTextField();
		txtparentname.setPreferredSize(new Dimension(280, 35));
		txtparentname.setFont(font);
		txtparentname.setBounds(580, 180, 280, 35);
		frame.add(txtparentname);

		txtcontactno = new JTextField();
		txtcontactno.setPreferredSize(new Dimension(220, 35));
		txtcontactno.setFont(font);
		txtcontactno.setBounds(580, 230, 220, 35);
		frame.add(txtcontactno);

		Object[] datacbx1 = { "Java1", "Java2", "Java3", "Java4", "Java5", "Java6" };
		cbxstandard = new JComboBox(datacbx1);
		cbxstandard.setPreferredSize(new Dimension(220, 35));
		cbxstandard.setFont(font);
		cbxstandard.setBounds(580, 280, 220, 35);
		frame.add(cbxstandard);

		Object[] datacbx2 = { "1000000", "2000000", "3000000", "4000000", "5000000", "6000000" };
		cbxfees = new JComboBox(datacbx2);
		cbxfees.setPreferredSize(new Dimension(280, 35));
		cbxfees.setFont(font);
		cbxfees.setBounds(580, 330, 280, 35);
		frame.add(cbxfees);

		btnnew = new JButton("New");
		btnnew.setFont(font);
		btnnew.setBounds(450, 390, 100, 35);
		frame.add(btnnew);

		btninsert = new JButton("Insert");
		btninsert.setFont(font);
		btninsert.setBounds(570, 390, 100, 35);
		frame.add(btninsert);

		btnedit = new JButton("Edit");
		btnedit.setFont(font);
		btnedit.setBounds(690, 390, 100, 35);
		frame.add(btnedit);

		btnupdate = new JButton("Update");
		btnupdate.setEnabled(false);
		btnupdate.setFont(font);
		btnupdate.setBounds(810, 390, 100, 35);
		frame.add(btnupdate);

		btnnext = new JButton("Next");
		btnnext.setFont(font);
		btnnext.setBounds(450, 440, 100, 35);
		frame.add(btnnext);

		btnprevious = new JButton("Previous");
		btnprevious.setFont(font);
		btnprevious.setBounds(570, 440, 100, 35);
		frame.add(btnprevious);

		btndelete = new JButton("Delete");
		btndelete.setFont(font);
		btndelete.setBounds(690, 440, 100, 35);
		frame.add(btndelete);

		btnexit = new JButton("Exit");
		btnexit.setFont(font);
		btnexit.setBounds(810, 440, 100, 35);
		frame.add(btnexit);

		ActionListener acc = new controller_bai2(this);
		btnnew.addActionListener(acc);
		btninsert.addActionListener(acc);
		btnedit.addActionListener(acc);
		btnupdate.addActionListener(acc);
		btnnext.addActionListener(acc);
		btnprevious.addActionListener(acc);
		btndelete.addActionListener(acc);
		btnexit.addActionListener(acc);
		 
		MouseListener mc = new controller_bai2(this);
		tb.addMouseListener(mc);
		frame.setVisible(true);
	}

}
