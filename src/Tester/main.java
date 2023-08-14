package Tester;

import javax.swing.UIManager;

import view.view_bai1;
import view.view_bai2;

public class main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			view_bai1 view_bai1 = new view_bai1();
			view_bai2 view_bai2 = new view_bai2();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
