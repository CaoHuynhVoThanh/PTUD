package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DonDatBan_DAO;
import dao.HoaDon_DAO;
import entities.NhanVien;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ThanhToanChiTiet_GUI extends JDialog implements ActionListener{
	private NhanVien currenUser = new NhanVien("25000001", "Lê Vinh Quang", "quankle@gmail.com", "0987654321", "Gò Vấp", "Nhân Viên Quầy", null, true);
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tf_km;
	private JTextField tf_tv;
	private JLabel lb_tgdb;
	private JLabel lb_tgtt;
	private JLabel lb_tamtinh;
	private JLabel lb_tennv;
	private JLabel lb_tongtt;
	private JButton btn_tienmat;
	private JButton btn_nganhang;
	private JButton btn_chuyenkhoan;
	private JButton btn_huy;
	private JLabel lb_vat;
	private JLabel lb_km;
	private JLabel lb_tv;
	private JButton btn_xemtruoc;
	private ArrayList<String> dsddb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ThanhToanChiTiet_GUI dialog = new ThanhToanChiTiet_GUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ThanhToanChiTiet_GUI() {
		setBounds(550, 100, 450, 553);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN THANH TOÁN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(64, 10, 288, 37);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Thời gian đặt bàn:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(23, 65, 143, 23);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Thời gian thanh toán:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(23, 98, 143, 23);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Tạm tính:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(23, 131, 143, 23);
		contentPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("VAT (10%):");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(23, 164, 143, 23);
		contentPanel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Mã khuyến mãi:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1.setBounds(23, 197, 143, 23);
		contentPanel.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Thẻ thành viên:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1_1.setBounds(23, 249, 143, 23);
		contentPanel.add(lblNewLabel_1_1_1_1_1_1);
		
		tf_km = new JTextField();
		tf_km.setBounds(131, 197, 96, 23);
		contentPanel.add(tf_km);
		tf_km.setColumns(10);
		
		JButton btnNewButton = new JButton("O");
		btnNewButton.setBounds(237, 195, 54, 25);
		contentPanel.add(btnNewButton);
		
		tf_tv = new JTextField();
		tf_tv.setColumns(10);
		tf_tv.setBounds(131, 249, 96, 23);
		contentPanel.add(tf_tv);
		
		JButton btnNewButton_1 = new JButton("O");
		btnNewButton_1.setBounds(237, 247, 54, 25);
		contentPanel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(23, 226, 45, 13);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setBounds(131, 226, 45, 13);
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("New label");
		lblNewLabel_2_1_1.setBounds(131, 280, 45, 13);
		contentPanel.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("New label");
		lblNewLabel_2_2.setBounds(23, 280, 45, 13);
		contentPanel.add(lblNewLabel_2_2);
		
		lb_tgdb = new JLabel("0000000");
		lb_tgdb.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tgdb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_tgdb.setBounds(176, 65, 237, 23);
		contentPanel.add(lb_tgdb);
		
		lb_tgtt = new JLabel("0000000");
		lb_tgtt.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tgtt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_tgtt.setBounds(176, 98, 237, 23);
		contentPanel.add(lb_tgtt);
		
		lb_tamtinh = new JLabel("0000000");
		lb_tamtinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tamtinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_tamtinh.setBounds(270, 131, 143, 23);
		contentPanel.add(lb_tamtinh);
		
		lb_vat = new JLabel("0000000");
		lb_vat.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_vat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_vat.setBounds(270, 164, 143, 23);
		contentPanel.add(lb_vat);
		
		lb_km = new JLabel("0000000");
		lb_km.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_km.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_km.setBounds(270, 197, 143, 23);
		contentPanel.add(lb_km);
		
		lb_tv = new JLabel("0000000");
		lb_tv.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tv.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_tv.setBounds(270, 249, 143, 23);
		contentPanel.add(lb_tv);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Nhân viên quầy:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(23, 303, 143, 23);
		contentPanel.add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_2 = new JLabel("Tổng thành tiền:");
		lblNewLabel_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1_1_2.setBounds(23, 336, 143, 23);
		contentPanel.add(lblNewLabel_1_1_1_1_1_1_2);
		
		lb_tennv = new JLabel("0000000");
		lb_tennv.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tennv.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_tennv.setBounds(270, 303, 143, 23);
		contentPanel.add(lb_tennv);
		
		lb_tongtt = new JLabel("0000000");
		lb_tongtt.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tongtt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_tongtt.setBounds(270, 336, 143, 23);
		contentPanel.add(lb_tongtt);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Phương thức thanh toán:");
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(23, 369, 168, 23);
		contentPanel.add(lblNewLabel_1_1_1_1_1_1_1_1);
		
		btn_tienmat = new JButton("Tiền mặt");
		btn_tienmat.addActionListener(this);
		btn_tienmat.setForeground(new Color(255, 255, 255));
		btn_tienmat.setBackground(new Color(0, 255, 64));
		btn_tienmat.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_tienmat.setBounds(33, 402, 117, 37);
		contentPanel.add(btn_tienmat);
		
		btn_nganhang = new JButton("Thẻ");
		btn_nganhang.addActionListener(this);
		btn_nganhang.setForeground(new Color(255, 255, 255));
		btn_nganhang.setBackground(new Color(0, 0, 255));
		btn_nganhang.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_nganhang.setBounds(285, 402, 117, 37);
		contentPanel.add(btn_nganhang);
		
		btn_chuyenkhoan = new JButton("Chuyển khoản");
		btn_chuyenkhoan.addActionListener(this);
		btn_chuyenkhoan.setForeground(new Color(255, 255, 255));
		btn_chuyenkhoan.setBackground(new Color(255, 0, 128));
		btn_chuyenkhoan.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_chuyenkhoan.setBounds(160, 402, 117, 37);
		contentPanel.add(btn_chuyenkhoan);
		
		btn_huy = new JButton("Hủy thanh toán");
		btn_huy.addActionListener(this);
		btn_huy.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_huy.setForeground(new Color(255, 255, 255));
		btn_huy.setBackground(new Color(0, 0, 0));
		btn_huy.setBounds(133, 457, 158, 37);
		contentPanel.add(btn_huy);
		
		btn_xemtruoc = new JButton("Xem trước");
		btn_xemtruoc.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btn_xemtruoc.setOpaque(false);
		btn_xemtruoc.setContentAreaFilled(false);
		btn_xemtruoc.setBorderPainted(false);
		btn_xemtruoc.setBounds(301, 466, 101, 21);
		contentPanel.add(btn_xemtruoc);
	}
	public void setTT(LocalDateTime tgd, String tamtinh) {
		lb_tgdb.setText(tgd.toString());
		lb_tamtinh.setText(tamtinh);
		lb_tgtt.setText(LocalDateTime.now().withNano(0).toString());
		double tam = Double.parseDouble(tamtinh);
		lb_vat.setText((tam*0.1)+"");
		lb_tennv.setText(currenUser.getTenNV());
		lb_km.setText("0.0");
		lb_tv.setText("0.0");
		double tong = Double.parseDouble(tamtinh)+Double.parseDouble(lb_vat.getText())-Double.parseDouble(lb_km.getText())-Double.parseDouble(lb_tv.getText());
		lb_tongtt.setText(tong+"");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		
		System.out.println(cmd);
		if (cmd.equals("Hủy thanh toán")) {
			this.dispose();
		}
		if (cmd.equals("Tiền mặt")) {
			String tienString = JOptionPane.showInputDialog("Nhập số tiền khách đưa");
			if (("".equals(tienString))) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán?")==JOptionPane.YES_OPTION) {
					LocalDate now = LocalDate.now(); 
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
			        String formattedDate = now.format(formatter);
					String ma = formattedDate+currenUser.getMaNV()+HoaDon_DAO.demHoaDonTrongNgay()+"";
					String makm = "";
					if (tf_km.getText().equals("")) makm=null;
					String matv = "";
					if (tf_tv.getText().equals("")) matv=null;
					double tongtien = Double.parseDouble(lb_tongtt.getText());
					HoaDon_DAO.insertHoaDon(ma, currenUser.getMaNV(), LocalDateTime.now(), makm, tongtien, "Tiền mặt", matv, tongtien/10000);
					for (String s: dsddb) {
						DonDatBan_DAO.capNhatMaHDChoDonDatBan(s, ma);
					}
					JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
					ThanhToan_GUI.hiddenButtonThanhToan.doClick();
					this.dispose();
				}
			}
		}
		if (cmd.equals("Chuyển khoản")) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán?")==JOptionPane.YES_OPTION) {
				LocalDate now = LocalDate.now(); 
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		        String formattedDate = now.format(formatter);
				String ma = formattedDate+currenUser.getMaNV()+HoaDon_DAO.demHoaDonTrongNgay()+"";
				String makm = "";
				if (tf_km.getText().equals("")) makm=null;
				String matv = "";
				if (tf_tv.getText().equals("")) matv=null;
				double tongtien = Double.parseDouble(lb_tongtt.getText());
				HoaDon_DAO.insertHoaDon(ma, currenUser.getMaNV(), LocalDateTime.now(), makm, tongtien, "Chuyển khoản", matv, tongtien/10000);
				for (String s: dsddb) {
					DonDatBan_DAO.capNhatMaHDChoDonDatBan(s, ma);
				}
				JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
				ThanhToan_GUI.hiddenButtonThanhToan.doClick();
				this.dispose();
			}
		}
		if (cmd.equals("Thẻ")) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán?")==JOptionPane.YES_OPTION) {
				LocalDate now = LocalDate.now(); 
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		        String formattedDate = now.format(formatter);
				String ma = formattedDate+currenUser.getMaNV()+HoaDon_DAO.demHoaDonTrongNgay()+"";
				String makm = "";
				if (tf_km.getText().equals("")) makm=null;
				String matv = "";
				if (tf_tv.getText().equals("")) matv=null;
				double tongtien = Double.parseDouble(lb_tongtt.getText());
				HoaDon_DAO.insertHoaDon(ma, currenUser.getMaNV(), LocalDateTime.now(), makm, tongtien, "Thẻ", matv, tongtien/10000);
				for (String s: dsddb) {
					DonDatBan_DAO.capNhatMaHDChoDonDatBan(s, ma);
				}
				JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
				ThanhToan_GUI.hiddenButtonThanhToan.doClick();
				this.dispose();
			}
		}
		if (cmd.equals("Xem trước")) {
			
		}
	}

	public void setDsDDB(ArrayList<String> maDonChon) {
		// TODO Auto-generated method stub
		this.dsddb = maDonChon;
	}
}
