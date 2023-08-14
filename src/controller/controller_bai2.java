package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.JDBCUtil;
import view.view_bai2;

public class controller_bai2 extends JFrame implements ActionListener,MouseListener {
	private view_bai2 vb2;
	private int indexx;

	public controller_bai2(view_bai2 vb2) {
		this.vb2 = vb2;
		reset();
		fill();
	}

	public boolean checknull() {
		if (vb2.txtname.getText().trim().equals("")) {
			return false;
		}
		if (vb2.txtaddress.getText().trim().equals("")) {
			return false;
		}
		if (vb2.txtparentname.getText().trim().equals("")) {
			return false;
		}
		if (vb2.txtcontactno.getText().trim().equals("")) {
			return false;
		}
		int cbx1 = Integer.valueOf(vb2.cbxstandard.getSelectedIndex());
		if (cbx1 == -1) {
			return false;
		}
		int cbx2 = Integer.valueOf(vb2.cbxfees.getSelectedIndex());
		if (cbx2 == -1) {
			return false;
		}
		return true;
	}

	public void reset() {
		vb2.txtid.setText("");
		vb2.txtname.setText("");
		vb2.txtaddress.setText("");
		vb2.txtparentname.setText("");
		vb2.txtcontactno.setText("");
		vb2.cbxstandard.setSelectedIndex(-1);
		vb2.cbxfees.setSelectedIndex(-1);
	}

	public void fill() {
		vb2.model.setRowCount(0);
		try {
			Connection cns = JDBCUtil.getConnection();
			String sql = "select Namee,Standard from students";
			PreparedStatement st = cns.prepareStatement(sql);
			ResultSet kq = st.executeQuery();
			while (kq.next()) {
				String name = kq.getString("Namee");
				String address = kq.getString("Standard");
				String[] datanew = { name, address };
				vb2.model.addRow(datanew);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		if (event.equals("New")) {
			reset();
		}
		if (event.equals("Insert")) {
			if (checknull()) {
				try {
					Connection cns = JDBCUtil.getConnection();
					String sql = "insert into students (Namee,Address,ParentName,Phonee,Standard,Fees) values (?,?,?,?,?,?)";
					PreparedStatement st = cns.prepareStatement(sql);
					st.setString(1, vb2.txtname.getText());
					st.setString(2, vb2.txtaddress.getText());
					st.setString(3, vb2.txtparentname.getText());
					st.setString(4, vb2.txtcontactno.getText());
					st.setString(5, String.valueOf(vb2.cbxstandard.getSelectedItem()));
					st.setString(6, String.valueOf(vb2.cbxfees.getSelectedItem()));
					int kq = st.executeUpdate();
					if (kq > 0) {
						JOptionPane.showMessageDialog(this, "Insert thanh cong !");
						fill();
						reset();
					} else {
						JOptionPane.showMessageDialog(this, "Insert khong thanh cong !");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui long dien day du thong tin !");
				return;
			}
		}
		if (event.equals("Edit")) {
			if(!checknull()) {
				JOptionPane.showMessageDialog(this, "Vui long chon dong muon Edit !");
				return;
			}
			vb2.btnupdate.setEnabled(true);
		}
		if (event.equals("Update")) {
			try {
				Connection cns = JDBCUtil.getConnection();
				String sql = "update students set Namee = ?,Address = ?,ParentName = ? ,Phonee = ? ,Standard = ? ,Fees = ? where Regidd = ?";
				PreparedStatement st = cns.prepareStatement(sql);
				st.setString(1, vb2.txtname.getText());
				st.setString(2, vb2.txtaddress.getText());
				st.setString(3, vb2.txtparentname.getText());
				st.setString(4, vb2.txtcontactno.getText());
				st.setString(5, String.valueOf(vb2.cbxstandard.getSelectedItem()));
				st.setString(6, String.valueOf(vb2.cbxfees.getSelectedItem()));
				st.setString(7, vb2.txtid.getText());
				int kq = st.executeUpdate();
				if (kq > 0) {
					JOptionPane.showMessageDialog(this, "Update thanh cong !");
					fill();
					reset();
					vb2.btnupdate.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(this, "Update khong thanh cong !");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (event.equals("Next")) {
			try {
				Connection cns = JDBCUtil.getConnection();
				String sql = "select count(*) from students";
				PreparedStatement st = cns.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int length = Integer.parseInt(rs.getString("count(*)"));
					indexx = vb2.tb.getSelectedRow() + 1;
					if (indexx > length - 1) {
						indexx = 0;
					}
					vb2.tb.setRowSelectionInterval(indexx, indexx);
					Connection cnss = JDBCUtil.getConnection();
					String sqll = "select * from students where Namee = ?";
					PreparedStatement stt = cnss.prepareStatement(sqll);
					stt.setString(1, String.valueOf(vb2.tb.getValueAt(indexx, 0)));
					ResultSet rss = stt.executeQuery();
					while (rss.next()) {
						vb2.txtid.setText(rss.getString("Regidd"));
						vb2.txtname.setText(rss.getString("Namee"));
						vb2.txtaddress.setText(rss.getString("Address"));
						vb2.txtparentname.setText(rss.getString("ParentName"));
						vb2.txtcontactno.setText(rss.getString("Phonee"));
						vb2.cbxstandard.setSelectedItem(rss.getString("Standard"));
						vb2.cbxfees.setSelectedItem(rss.getString("Fees"));
					}
				}
			} catch (SQLException e2) {

			}
		}
		if (event.equals("Previous")) {
			try {
				Connection cns = JDBCUtil.getConnection();
				String sql = "select count(*) from students";
				PreparedStatement st = cns.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int length = Integer.parseInt(rs.getString("count(*)"));
					indexx = vb2.tb.getSelectedRow() - 1;
					if (indexx < 0) {
						indexx = length - 1;
					}
					vb2.tb.setRowSelectionInterval(indexx, indexx);
					Connection cnss = JDBCUtil.getConnection();
					String sqll = "select * from students where Namee = ?";
					PreparedStatement stt = cnss.prepareStatement(sqll);
					stt.setString(1, String.valueOf(vb2.tb.getValueAt(indexx, 0)));
					ResultSet rss = stt.executeQuery();
					while (rss.next()) {
						vb2.txtid.setText(rss.getString("Regidd"));
						vb2.txtname.setText(rss.getString("Namee"));
						vb2.txtaddress.setText(rss.getString("Address"));
						vb2.txtparentname.setText(rss.getString("ParentName"));
						vb2.txtcontactno.setText(rss.getString("Phonee"));
						vb2.cbxstandard.setSelectedItem(rss.getString("Standard"));
						vb2.cbxfees.setSelectedItem(rss.getString("Fees"));
					}

				}
			} catch (SQLException e2) {

			}
		}
		if (event.equals("Delete")) {
			if(!checknull()) {
				JOptionPane.showMessageDialog(this, "Vui long chon doi tuong muon Delete !");
				return;
			}
			try {
				Connection cns = JDBCUtil.getConnection();
				String sql = "delete from students where Regidd = ?";
				PreparedStatement st = cns.prepareStatement(sql);
				st.setString(1, vb2.txtid.getText());
				int kq = st.executeUpdate();
				if (kq > 0) {
					JOptionPane.showMessageDialog(this, "Delete thanh cong !");
					fill();
					reset();
					vb2.btnupdate.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(this, "Delete khong thanh cong !");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (event.equals("Exit")) {
			int x = JOptionPane.showConfirmDialog(this, "Ban co muon thoat khong ?", "Thoat",
					JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				System.exit(0);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			Connection cnss = JDBCUtil.getConnection();
			String sqll = "select * from students where Namee = ?";
			PreparedStatement stt = cnss.prepareStatement(sqll);
			indexx = vb2.tb.getSelectedRow();
			stt.setString(1, String.valueOf(vb2.tb.getValueAt(indexx, 0)));
			ResultSet rss = stt.executeQuery();
			while (rss.next()) {
				vb2.txtid.setText(rss.getString("Regidd"));
				vb2.txtname.setText(rss.getString("Namee"));
				vb2.txtaddress.setText(rss.getString("Address"));
				vb2.txtparentname.setText(rss.getString("ParentName"));
				vb2.txtcontactno.setText(rss.getString("Phonee"));
				vb2.cbxstandard.setSelectedItem(rss.getString("Standard"));
				vb2.cbxfees.setSelectedItem(rss.getString("Fees"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
