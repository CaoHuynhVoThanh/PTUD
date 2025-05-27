package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DonDatBan_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.KhuyenMai_DAO;
import dao.ThanhVien_DAO;
import entities.KhachHang;
import entities.KhuyenMai;
import entities.NhanVien;
import entities.ThanhVien;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class ThanhToanChiTiet_GUI extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
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
	private JLabel lb_dtl;
	private JButton btn_xemtruoc;
	private ArrayList<String> dsddb;
	private JLabel lb_tentv;
	private JLabel lb_rank;
	private JButton btn_tv;
	public static List<Map<String, ?>> dataList = new ArrayList<>();
	public static Map<String, Object> params = new HashMap<>();
	private ArrayList<KhuyenMai> dskm = null;
	private ThanhVien thanhvien = null;
	private JTextField textField;
	private JLabel lb_kmTienGiam;
	private JTextField tf_km = new JTextField();
	private JButton btn_km;
	private KhuyenMai kmad = null;
	private JLabel lb_phanTramGiam;
	private JLabel lb_tenkm;

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
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Thẻ thành viên:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1_1.setBounds(23, 199, 143, 23);
		contentPanel.add(lblNewLabel_1_1_1_1_1_1);
		
		tf_tv = new JTextField();
		tf_tv.setColumns(10);
		tf_tv.setBounds(131, 199, 96, 23);
		contentPanel.add(tf_tv);
		
		btn_tv = new JButton("O");
		btn_tv.setBounds(237, 197, 54, 25);
		contentPanel.add(btn_tv);
		btn_tv.addActionListener(this);
		btn_tv.setActionCommand("Tìm TV");
		
		lb_rank = new JLabel("");
		lb_rank.setBounds(210, 232, 72, 13);
		contentPanel.add(lb_rank);
		
		lb_tentv = new JLabel("");
		lb_tentv.setBounds(23, 230, 152, 13);
		contentPanel.add(lb_tentv);
		
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
		
		lb_dtl = new JLabel("0000000");
		lb_dtl.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_dtl.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_dtl.setBounds(301, 199, 112, 23);
		contentPanel.add(lb_dtl);
		
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
		
		lb_kmTienGiam = new JLabel("");
		lb_kmTienGiam.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_kmTienGiam.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_kmTienGiam.setBounds(301, 251, 112, 23);
		contentPanel.add(lb_kmTienGiam);
		
		btn_km = new JButton("O");
		btn_km.setBounds(237, 249, 54, 25);
		contentPanel.add(btn_km);
		btn_km.addActionListener(this);
		btn_km.setActionCommand("timkm");
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(131, 251, 96, 23);
		contentPanel.add(textField);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Mã khuyến mãi:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1.setBounds(23, 251, 143, 23);
		contentPanel.add(lblNewLabel_1_1_1_1_1);
		
		lb_tenkm = new JLabel("");
		lb_tenkm.setBounds(23, 280, 143, 13);
		contentPanel.add(lb_tenkm);
		
		JLabel lb_kmgiam = new JLabel("");
		lb_kmgiam.setBounds(131, 280, 45, 13);
		contentPanel.add(lb_kmgiam);
		
		lb_phanTramGiam = new JLabel("");
		lb_phanTramGiam.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_phanTramGiam.setBounds(187, 280, 101, 13);
		contentPanel.add(lb_phanTramGiam);
		btn_xemtruoc.addActionListener(this);
	}
	public void setTT(LocalDateTime tgd, String tamtinh) {
		lb_tgdb.setText(tgd.toString());
		lb_tamtinh.setText(tamtinh);
		lb_tgtt.setText(LocalDateTime.now().withNano(0).toString());
		double tam = Double.parseDouble(tamtinh);
		lb_vat.setText((tam*0.1)+"");
		lb_tennv.setText(Application.nhanvien.getTenNV());
		lb_kmTienGiam.setText("0.0");
		lb_dtl.setText("0.0");
		double tong = Double.parseDouble(tamtinh)+Double.parseDouble(lb_vat.getText())-Double.parseDouble(lb_kmTienGiam.getText())-Double.parseDouble(lb_dtl.getText());
		lb_tongtt.setText(tong+"");
	}
	
	public static String norText(String str) {
        if (str == null) return null;

        // Chuẩn hóa Unicode thành dạng decomposed (NFD)
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);

        // Loại bỏ các dấu thanh (dấu sắc, huyền, hỏi, ngã, nặng)
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String noDiacritics = pattern.matcher(normalized).replaceAll("");

        // Thay thế riêng ký tự Đ và đ
        noDiacritics = noDiacritics.replace("Đ", "D").replace("đ", "d");

        return noDiacritics;
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
//			
			double tongTien = Double.parseDouble(lb_tongtt.getText());
			double tien = 0.0;
			String tienString = null;
			do {
			    tienString = JOptionPane.showInputDialog(null, "Nhập số tiền khách đưa:");

			    // Người dùng bấm Cancel
			    if (tienString == null) {
			        return; // Thoát phương thức
			    }

			    tienString = tienString.trim();

			    if (tienString.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền!");
			        continue;
			    }

			    try {
			        tien = Double.parseDouble(tienString);

			        if (tien <= tongTien) {
			            JOptionPane.showMessageDialog(null, "Số tiền phải lớn hơn " + tongTien + "!");
			        } else {
			            break; // Nhập hợp lệ, thoát khỏi vòng lặp
			        }
			    } catch (NumberFormatException err) {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ!");
			    }

			} while (true);

			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán?")==JOptionPane.YES_OPTION) {
				LocalDate now = LocalDate.now(); 
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		        String num = String.format("%06d", HoaDon_DAO.demHoaDonTrongNgay());
		        String formattedDate = now.format(formatter);
				String ma ="HD"+ formattedDate+num+"";
				String makm = "";
				if (tf_km.getText().equals("")) makm=null;
				String matv;
				if (thanhvien==null) matv =null;
				else matv = thanhvien.getMaTV();
				double tongtien = Double.parseDouble(lb_tongtt.getText());
				HoaDon_DAO.insertHoaDon(ma, Application.nhanvien.getMaNV(), LocalDateTime.now(), makm, tongtien, "Tiền mặt", matv, tongtien/10000);
				for (String s: dsddb) {
					DonDatBan_DAO.capNhatMaHDChoDonDatBan(s, ma);
					DonDatBan_DAO.capNhatTrangThaiDonDatBan(s, 2);
				}
				Double tientra = tien-tongTien;
				JOptionPane.showMessageDialog(null, "Thanh toán thành công! Số tiền trả khách: "+tientra);
				String jrxmlFile = "/other/Blank_Letter_2.jrxml";
				JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(dataList);
				params.put("address", norText("Số 13, Nguyễn Văn Bảo, Phường 7, Gò Vấp"));
				params.put("cashier", norText(Application.nhanvien.getTenNV()));
	            params.put("invoiceID", norText(ma));
	            LocalDateTime d = LocalDateTime.now();
	            params.put("dateTime", d.toString());
	            try {
	            	InputStream jrxmlStream = getClass().getResourceAsStream(jrxmlFile);
	            	JasperReport report = JasperCompileManager.compileReport(jrxmlStream);
	                JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
	                JasperExportManager.exportReportToPdfFile(print, "invoice_output.pdf");
				} catch (Exception e2) {
					// TODO: handle exception
				}
				ThanhToan_GUI.hiddenButtonThanhToan.doClick();
				this.dispose();
			}
			
		}
		if (cmd.equals("Chuyển khoản")) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán?")==JOptionPane.YES_OPTION) {
				LocalDate now = LocalDate.now(); 
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		        String num = String.format("%06d", HoaDon_DAO.demHoaDonTrongNgay());
		        String formattedDate = now.format(formatter);
				String ma ="HD"+ formattedDate+num+"";
				String makm = "";
				if (tf_km.getText().equals("")) makm=null;
				String matv;
				if (thanhvien==null) matv =null;
				else matv = thanhvien.getMaTV();
				double tongtien = Double.parseDouble(lb_tongtt.getText());
				HoaDon_DAO.insertHoaDon(ma, Application.nhanvien.getMaNV(), LocalDateTime.now(), makm, tongtien, "Chuyển khoản", matv, tongtien/10000);
				for (String s: dsddb) {
					DonDatBan_DAO.capNhatMaHDChoDonDatBan(s, ma);
					DonDatBan_DAO.capNhatTrangThaiDonDatBan(s, 2);
				}
				JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
				String jrxmlFile = "/other/Blank_Letter_2.jrxml";
				JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(dataList);
				params.put("address", norText("Số 13, Nguyễn Văn Bảo, Phường 7, Gò Vấp"));
				params.put("cashier", norText(Application.nhanvien.getTenNV()));
	            params.put("invoiceID", norText(ma));
	            LocalDateTime d = LocalDateTime.now();
	            params.put("dateTime", d.toString());
	            try {
	            	InputStream jrxmlStream = getClass().getResourceAsStream(jrxmlFile);
	            	JasperReport report = JasperCompileManager.compileReport(jrxmlStream);
	                JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
	                JasperExportManager.exportReportToPdfFile(print, "invoice_output.pdf");
				} catch (Exception e2) {
					// TODO: handle exception
				}
				ThanhToan_GUI.hiddenButtonThanhToan.doClick();
				this.dispose();
			}
		}
		if (cmd.equals("Thẻ")) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán?")==JOptionPane.YES_OPTION) {
				LocalDate now = LocalDate.now(); 
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		        String num = String.format("%06d", HoaDon_DAO.demHoaDonTrongNgay());
		        String formattedDate = now.format(formatter);
				String ma ="HD"+ formattedDate+num+"";
				String makm = "";
				if (tf_km.getText().equals("")) makm=null;
				String matv;
				if (thanhvien==null) matv =null;
				else matv = thanhvien.getMaTV();
				double tongtien = Double.parseDouble(lb_tongtt.getText());
				HoaDon_DAO.insertHoaDon(ma, Application.nhanvien.getMaNV(), LocalDateTime.now(), makm, tongtien, "Thẻ", matv, tongtien/10000);
				for (String s: dsddb) {
					DonDatBan_DAO.capNhatMaHDChoDonDatBan(s, ma);
					DonDatBan_DAO.capNhatTrangThaiDonDatBan(s, 2);
				}
				JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
				String jrxmlFile = "/other/Blank_Letter_2.jrxml";
				JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(dataList);
				params.put("address", norText("Số 13, Nguyễn Văn Bảo, Phường 7, Gò Vấp"));
				params.put("cashier", norText(Application.nhanvien.getTenNV()));
	            params.put("invoiceID", norText(ma));
	            LocalDateTime d = LocalDateTime.now();
	            params.put("dateTime", d.toString());
	            try {
	            	InputStream jrxmlStream = getClass().getResourceAsStream(jrxmlFile);
	            	if (jrxmlStream == null) {
	            	    throw new RuntimeException("Không tìm thấy file JRXML! Đảm bảo file nằm trong thư mục resources và được export vào JAR.");
	            	}
	            	JasperReport report = JasperCompileManager.compileReport(jrxmlStream);
	                JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
	                JasperExportManager.exportReportToPdfFile(print, "invoice_output.pdf");
				} catch (Exception e2) {
				    e2.printStackTrace(); // THAY vì để trống
				    JOptionPane.showMessageDialog(this,
				        "Lỗi khi in báo cáo: " + e2.getMessage(),
				        "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				ThanhToan_GUI.hiddenButtonThanhToan.doClick();
				this.dispose();
			}
		}
		if (cmd.equals("Xem trước")) {
			String jrxmlFile = "/other/Blank_Letter_2.jrxml";
			JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(dataList);
			params.put("address", norText("Số 13, Nguyễn Văn Bảo, Phường 7, Gò Vấp"));
			params.put("cashier", norText(Application.nhanvien.getTenNV()));
			
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        String num = String.format("%06d", HoaDon_DAO.demHoaDonTrongNgay());
	        String formattedDate = now.format(formatter);
			String ma ="HD"+ formattedDate+num+"";
            params.put("invoiceID", norText(ma));
            LocalDateTime d = LocalDateTime.now();
            params.put("dateTime", d.toString());
            try {
            	InputStream jrxmlStream = getClass().getResourceAsStream(jrxmlFile);
            	if (jrxmlStream == null) {
            	    throw new RuntimeException("Không tìm thấy file: " + jrxmlFile);
            	}
            	for (Map.Entry<String, Object> entry : params.entrySet()) {
            	    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            	}


            	JasperReport report = JasperCompileManager.compileReport(jrxmlStream);
                JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
                JasperViewer.viewReport(print, false);
			} catch (Exception e2) {
				// TODO: handle exception
			}
            
		}
		if (cmd.equals("Tìm TV")) {
			String ma = tf_tv.getText().trim();
			thanhvien = ThanhVien_DAO.timThanhVienTheoMa(ma);
			if (thanhvien==null) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin thành viên");
				thanhvien = null;
				lb_dtl.setText("");
				lb_tentv.setText("");
				lb_rank.setText("");
				lb_tenkm.setText("");
				lb_kmTienGiam.setText("");
				lb_phanTramGiam.setText("");
			}
			else {
				Double tt = Double.parseDouble(lb_tamtinh.getText());
				Double vat = Double.parseDouble(lb_vat.getText());
				int dtl = (int)((tt+vat)/10000);
				lb_dtl.setText("+"+dtl+" điểm ");
				lb_tentv.setText(thanhvien.getTenTV());
				lb_rank.setText(thanhvien.getHangThe());
			}
		}
		if (cmd.equals("timkm")) {
			String matv = tf_tv.getText();
			String hang = lb_rank.getText();
			System.out.println(matv+" "+hang);
			kmad = KhuyenMai_DAO.getKhuyenMaiTotNhat(matv, hang);
			if (kmad==null) {
				JOptionPane.showMessageDialog(null, "Không có khuyến mãi phù hợp");
				lb_tenkm.setText("");
				lb_kmTienGiam.setText("");
				lb_phanTramGiam.setText("");
			}
			else {
				lb_tenkm.setText(kmad.getTen());
				DecimalFormat df = new DecimalFormat("#.##%");
				lb_phanTramGiam.setText("Giảm "+df.format(kmad.getPhanTram()));
				Double tt = Double.parseDouble(lb_tamtinh.getText());
				Double vat = Double.parseDouble(lb_vat.getText());
				Double giamKm = (tt+vat)*kmad.getPhanTram();
				lb_kmTienGiam.setText("-"+Math.round(giamKm*100)/100.0);
				Double total = tt+vat-giamKm;
				lb_tongtt.setText(""+Math.round(total*100)/100.0);
			}
		}
	}

	public void setDsDDB(ArrayList<String> maDonChon) {
		// TODO Auto-generated method stub
		this.dsddb = maDonChon;
	}
}
