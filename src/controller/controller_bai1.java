package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.JDBCUtil;
import view.view_bai1;

public class controller_bai1 extends JFrame implements ActionListener, MouseListener {
	private view_bai1 bai1;
	private String gtdelete = "";
	private int ktt = 0;

	public controller_bai1(view_bai1 bai1) {
		this.bai1 = bai1;
	}

	public void fill() {
		bai1.model.setRowCount(0);
		try {
			Connection cns = JDBCUtil.getConnection();
			String sql = "select * from books";
			Statement st = cns.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("title");
				float price = rs.getFloat("price");
				Object[] dtnew = { id, title, price };
				bai1.model.addRow(dtnew);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		if (event.equals(bai1.btnsearch.getText())) {
			if (bai1.txttitle.getText().trim().equals("")) {
				fill();
			} else {
				try {
					Connection cns = JDBCUtil.getConnection();
					String sql = "select * from books where title = ?";
					PreparedStatement st = cns.prepareStatement(sql);
					st.setString(1, bai1.txttitle.getText());
					ResultSet rs = st.executeQuery();
					while (rs.next()) {
						int id = rs.getInt("ID");
						String title = rs.getString("title");
						float price = rs.getFloat("price");
						Object[] dtnew = { id, title, price };
						bai1.model.setRowCount(0);
						bai1.model.addRow(dtnew);
						ktt++;
						JOptionPane.showMessageDialog(null, "1 row !");
					}
					if (ktt == 0) {
						JOptionPane.showMessageDialog(null, "The book is not available!");
					}
				} catch (SQLException exx) {
					exx.printStackTrace();
				}
			}
		}
		if (event.equals(bai1.btndelete.getText())) {
			if(bai1.txttitle.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Chua chon books !");
				return;
			}
			int lcc = JOptionPane.showConfirmDialog(null, "Delete", "Ban co muon xoa khong !",
					JOptionPane.YES_NO_OPTION);
			if (lcc == JOptionPane.YES_OPTION) {
				try {
					Connection cns = JDBCUtil.getConnection();
					String sql = "delete from books where title = ?";
					PreparedStatement st = cns.prepareStatement(sql);
					st.setString(1, String.valueOf(bai1.tb.getValueAt(bai1.tb.getSelectedRow(), 1)));
					int kq = st.executeUpdate();
					if (kq > 0) {
						JOptionPane.showMessageDialog(this, "OK!");
						fill();
					} else {
						JOptionPane.showMessageDialog(this, "Errr!");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gtdelete = String.valueOf(bai1.tb.getValueAt(bai1.tb.getSelectedRow(), 1));
		bai1.txttitle.setText(gtdelete);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
