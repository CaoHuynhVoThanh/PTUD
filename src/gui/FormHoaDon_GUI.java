//package gui;
//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.util.ArrayList;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import dao.ChiTietDonGoiMon_DAO;
//import entities.Ban;
//import entities.Mon;
//
//public class FormHoaDon_GUI extends JDialog {
//
//	private static final long serialVersionUID = 1L;
//	private final JPanel contentPanel = new JPanel();
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			FormHoaDon_GUI dialog = new FormHoaDon_GUI();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Create the dialog.
//	 */
//	public FormHoaDon_GUI() {
//		setBounds(100, 100, 450, 300);
//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setLayout(new FlowLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		{
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
//	}
//	public static String createHoaDon(ArrayList<Ban> dsb, ArrayList<Mon> dsm) {
//	    StringBuilder sb = new StringBuilder();
//
//	    sb.append("********** RECEIPT **********\n\n");
//
//	    sb.append("Bàn: ");
//	    for (Ban ban : dsb) {
//	        sb.append(ban.getMaBan()).append(" ");
//	    }
//	    sb.append("\n------------------------------\n");
//
//	    double tongTien = 0;
//
//	    for (Mon mon : dsm) {
//	        String ten = ChiTietDonGoiMon_DAO.getS + " x " + mon.getTenMon();
//	        String gia = String.format("$%.2f", mon.getThanhTien());
//
//	        // Canh tên bên trái, giá bên phải (giả sử 30 ký tự mỗi dòng)
//	        int space = 30 - ten.length() - gia.length();
//	        space = Math.max(1, space);
//
//	        sb.append(ten)
//	          .append(" ".repeat(space))
//	          .append(gia)
//	          .append("\n");
//
//	        tongTien += mon.getThanhTien();
//	    }
//
//	    sb.append("------------------------------\n");
//	    sb.append(String.format("%-20s %10s\n", "TỔNG CỘNG:", String.format("$%.2f", tongTien)));
//	    sb.append("THANH TOÁN BẰNG TIỀN MẶT\n");
//	    sb.append("XIN CẢM ƠN QUÝ KHÁCH!\n");
//
//	    return sb.toString();
//	}
//
//
//}
