package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Ban_DAO;
import dao.ChiTietDonDatBan_DAO;
import dao.ChiTietDonGoiMon_DAO;
import dao.DonDatBan_DAO;
import dao.HoaDon_DAO;
import dao.Mon_DAO;
import entities.Ban;
import entities.ChiTietDonDatBan;
import entities.DonDatBan;
import entities.HoaDon;
import entities.Mon;

import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;

public class TrangChu_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private ConnectDB con;
	private ArrayList<Ban> dsBan;
	private ArrayList<DonDatBan> dsDDB;
	private JLabel lblTongDoanhThu;
	private JLabel lblDonDaThucHien;
	private JLabel lblTongTienKet;
	private JLabel lblTongLuotKhach;
	private JLabel lblTongMonAnBanRa;
	private JLabel lblTrong;
	private JLabel lblDangPhucVu;
	private JLabel lblDangGiu;
	private JLabel lblLuotDungBan;
	private JLabel lblDaNhan;
	private JLabel lblDaHuy;
	private JLabel lblChoNhan;
	private JPanel panel_trangchu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChu_GUI frame = new TrangChu_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TrangChu_GUI() {
		con = new ConnectDB();
		con.getInstance().connect();
		dsBan = Ban_DAO.getAllBan(LocalDate.now());
		dsDDB = DonDatBan_DAO.getDonDatBanTheoNgay(LocalDate.now());
		System.out.println(ChiTietDonDatBan_DAO.demChiTietDonDatBanTrongNgay());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(1537, 864);
		this.setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.foreground"));
		panel_1.setBounds(0, 0, 1556, 136);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("New label");
		logo.setIcon(new ImageIcon("src\\images\\icon.png"));
		logo.setBounds(66, 22, 247, 89);
		panel_1.add(logo);
		
		JLabel avt = new JLabel("");
		ImageIcon originalIcon = new ImageIcon("src\\images\\avt.png");
		Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH); 
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        avt.setIcon(scaledIcon);
		avt.setBounds(1365, 22, 100, 104);
		panel_1.add(avt);
		
		JLabel name = new JLabel("Lê Vinh A");
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		name.setFont(new Font("Arial", Font.BOLD, 22));
		name.setForeground(new Color(255, 255, 255));
		name.setBounds(1230, 43, 125, 41);
		panel_1.add(name);
		
		JLabel lblNewLabel = new JLabel("Nhân viên quèn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(1220, 82, 135, 13);
		panel_1.add(lblNewLabel);
		
		JLabel lb_ngay = new JLabel("");
		lb_ngay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_ngay.setForeground(new Color(255, 255, 255));
		lb_ngay.setBackground(new Color(0, 0, 0));
		lb_ngay.setBounds(475, 57, 263, 54);
		panel_1.add(lb_ngay);
		
		JLabel lb_thoiGian = new JLabel("");
		lb_thoiGian.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_thoiGian.setForeground(new Color(255, 255, 255));
		lb_thoiGian.setBounds(767, 57, 269, 54);
		panel_1.add(lb_thoiGian);
		
		JLabel lblGio = new JLabel();
		lblGio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGio.setBounds(21, 39, 229, 25);
		
	
		// Định dạng ngày và giờ
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Cập nhật ngày và giờ mỗi giây
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Lấy ngày và giờ hiện tại
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();

                // Cập nhật JLabel
                lb_ngay.setText("Ngày: " + currentDate.format(dateFormatter));
                lb_thoiGian.setText("Thời gian: " + currentTime.format(timeFormatter));
            }
        }, 0, 1000); // Cập nhật mỗi giây (1000ms)
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 0));
		panel.setBounds(-27, 112, 311, 748);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JMenuItem mi_TrangChu = new JMenuItem("              TRANG CHỦ");
		mi_TrangChu.setBackground(new Color(255, 153, 0));
		mi_TrangChu.setSelected(true);
		mi_TrangChu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_TrangChu.setHorizontalAlignment(SwingConstants.LEFT);
		mi_TrangChu.setForeground(new Color(255, 255, 255));
		mi_TrangChu.setBounds(20, 48, 291, 61);
		panel.add(mi_TrangChu);
		
		JButton btnNewButton = new JButton("ĐĂNG XUẤT");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setBounds(89, 641, 164, 42);
		panel.add(btnNewButton);
		
		JMenuItem mi_DatBan = new JMenuItem("              ĐẶT BÀN");
		mi_DatBan.setSelected(true);
		mi_DatBan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_DatBan.setForeground(Color.WHITE);
		mi_DatBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_DatBan.setBackground(new Color(255, 153, 0));
		mi_DatBan.setBounds(20, 109, 291, 61);
		panel.add(mi_DatBan);
		
		JMenuItem mi_NhanBan = new JMenuItem("              NHẬN BÀN");
		mi_NhanBan.setSelected(true);
		mi_NhanBan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_NhanBan.setForeground(Color.WHITE);
		mi_NhanBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_NhanBan.setBackground(new Color(255, 153, 0));
		mi_NhanBan.setBounds(20, 171, 291, 61);
		panel.add(mi_NhanBan);
		
		JMenuItem mi_GoiMon = new JMenuItem("              GỌI MÓN");
		mi_GoiMon.setSelected(true);
		mi_GoiMon.setHorizontalAlignment(SwingConstants.LEFT);
		mi_GoiMon.setForeground(Color.WHITE);
		mi_GoiMon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_GoiMon.setBackground(new Color(255, 153, 0));
		mi_GoiMon.setBounds(20, 233, 291, 61);
		panel.add(mi_GoiMon);
		
		JMenuItem mi_ThanhToan = new JMenuItem("              THANH TOÁN");
		mi_ThanhToan.setSelected(true);
		mi_ThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_ThanhToan.setForeground(Color.WHITE);
		mi_ThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_ThanhToan.setBackground(new Color(255, 153, 0));
		mi_ThanhToan.setBounds(20, 293, 291, 61);
		panel.add(mi_ThanhToan);
		
		JMenuItem mi_LichSu = new JMenuItem("              LỊCH SỬ");
		mi_LichSu.setSelected(true);
		mi_LichSu.setHorizontalAlignment(SwingConstants.LEFT);
		mi_LichSu.setForeground(Color.WHITE);
		mi_LichSu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_LichSu.setBackground(new Color(255, 153, 0));
		mi_LichSu.setBounds(20, 353, 291, 61);
		panel.add(mi_LichSu);
		
		JMenuItem mi_ThongKe = new JMenuItem("              THỐNG KÊ");
		mi_ThongKe.setSelected(true);
		mi_ThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		mi_ThongKe.setForeground(Color.WHITE);
		mi_ThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_ThongKe.setBackground(new Color(255, 153, 0));
		mi_ThongKe.setBounds(20, 478, 291, 61);
		panel.add(mi_ThongKe);
		
		JMenuItem mi_QuanLy = new JMenuItem("              QUẢN LÝ");
		mi_QuanLy.setSelected(true);
		mi_QuanLy.setHorizontalAlignment(SwingConstants.LEFT);
		mi_QuanLy.setForeground(Color.WHITE);
		mi_QuanLy.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_QuanLy.setBackground(new Color(255, 153, 0));
		mi_QuanLy.setBounds(20, 417, 291, 61);
		panel.add(mi_QuanLy);
		
		JMenuItem mi_ThongKe_1 = new JMenuItem("              TRỢ GIÚP");
		mi_ThongKe_1.setSelected(true);
		mi_ThongKe_1.setHorizontalAlignment(SwingConstants.LEFT);
		mi_ThongKe_1.setForeground(Color.WHITE);
		mi_ThongKe_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_ThongKe_1.setBackground(new Color(255, 153, 0));
		mi_ThongKe_1.setBounds(20, 541, 291, 61);
		panel.add(mi_ThongKe_1);
		
		panel_trangchu = new JPanel();
		panel_trangchu.setBackground(new Color(255, 255, 255));
		panel_trangchu.setBounds(285, 133, 1254, 704);
		contentPane.add(panel_trangchu);
		panel_trangchu.setLayout(null);
		
		JPanel pThongTinTrongNgay = new JPanel();
		pThongTinTrongNgay.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pThongTinTrongNgay.setBackground(new Color(255, 255, 255));
		pThongTinTrongNgay.setBounds(21, 22, 1209, 253);
		panel_trangchu.add(pThongTinTrongNgay);
		pThongTinTrongNgay.setLayout(null);
		
		JPanel pTongDoanhThu = new JPanel();
		pTongDoanhThu.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		pTongDoanhThu.setBackground(new Color(255, 153, 51));
		pTongDoanhThu.setBounds(77, 59, 288, 112);
		pThongTinTrongNgay.add(pTongDoanhThu);
		pTongDoanhThu.setLayout(null);
		
		lblTongDoanhThu = new JLabel("19,999,999");
		lblTongDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTongDoanhThu.setForeground(new Color(255, 255, 255));
		lblTongDoanhThu.setBounds(0, 10, 288, 56);
		pTongDoanhThu.add(lblTongDoanhThu);
		
		JLabel lblNewLabel_5 = new JLabel("Tổng doanh thu");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(0, 65, 288, 37);
		pTongDoanhThu.add(lblNewLabel_5);
		
		JPanel pLuotDungBan = new JPanel();
		pLuotDungBan.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		pLuotDungBan.setBackground(new Color(102, 102, 102));
		pLuotDungBan.setBounds(841, 59, 288, 112);
		pThongTinTrongNgay.add(pLuotDungBan);
		pLuotDungBan.setLayout(null);
		
		lblLuotDungBan = new JLabel("154");
		lblLuotDungBan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLuotDungBan.setForeground(Color.WHITE);
		lblLuotDungBan.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLuotDungBan.setBounds(0, 10, 288, 56);
		pLuotDungBan.add(lblLuotDungBan);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Lượt dùng bàn");
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1.setForeground(Color.WHITE);
		lblNewLabel_5_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_5_1_1.setBounds(0, 65, 288, 37);
		pLuotDungBan.add(lblNewLabel_5_1_1);
		
		JPanel pDonDaThucHien = new JPanel();
		pDonDaThucHien.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		pDonDaThucHien.setBackground(new Color(255, 204, 51));
		pDonDaThucHien.setBounds(460, 59, 288, 112);
		pThongTinTrongNgay.add(pDonDaThucHien);
		pDonDaThucHien.setLayout(null);
		
		lblDonDaThucHien = new JLabel("99");
		lblDonDaThucHien.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonDaThucHien.setForeground(Color.WHITE);
		lblDonDaThucHien.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDonDaThucHien.setBounds(0, 10, 288, 56);
		pDonDaThucHien.add(lblDonDaThucHien);
		
		JLabel lblNewLabel_5_1 = new JLabel("Đơn đã thực hiện");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_5_1.setBounds(0, 65, 288, 37);
		pDonDaThucHien.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin trong ngày");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 10, 233, 28);
		pThongTinTrongNgay.add(lblNewLabel_1);
		
		lblTongTienKet = new JLabel("Tổng tiền trong két:");
		lblTongTienKet.setFont(new Font("Arial", Font.BOLD, 14));
		lblTongTienKet.setBounds(77, 196, 288, 28);
		pThongTinTrongNgay.add(lblTongTienKet);
		
		lblTongLuotKhach = new JLabel("Tổng lượt khách:");
		lblTongLuotKhach.setFont(new Font("Arial", Font.BOLD, 14));
		lblTongLuotKhach.setBounds(460, 196, 288, 28);
		pThongTinTrongNgay.add(lblTongLuotKhach);
		
		lblTongMonAnBanRa = new JLabel("Tổng món ăn bán ra:");
		lblTongMonAnBanRa.setFont(new Font("Arial", Font.BOLD, 14));
		lblTongMonAnBanRa.setBounds(841, 196, 288, 28);
		pThongTinTrongNgay.add(lblTongMonAnBanRa);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(21, 305, 575, 373);
		panel_trangchu.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel_6.setBackground(new Color(255, 204, 102));
		panel_6.setBounds(51, 62, 212, 237);
		panel_3.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("ĐƠN ĐẶT BÀN");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(42, 10, 134, 24);
		panel_6.add(lblNewLabel_2);
		
		lblChoNhan = new JLabel("Chờ nhận:");
		lblChoNhan.setFont(new Font("Arial", Font.BOLD, 14));
		lblChoNhan.setBounds(22, 65, 164, 24);
		panel_6.add(lblChoNhan);
		
		lblDaNhan = new JLabel("Đã nhận:");
		lblDaNhan.setFont(new Font("Arial", Font.BOLD, 14));
		lblDaNhan.setBounds(22, 99, 154, 24);
		panel_6.add(lblDaNhan);
		
		lblDaHuy = new JLabel("Đã hủy:");
		lblDaHuy.setFont(new Font("Arial", Font.BOLD, 14));
		lblDaHuy.setBounds(22, 133, 154, 24);
		panel_6.add(lblDaHuy);
		
		JLabel lblNewLabel_1_1 = new JLabel("Trạng thái đơn và bàn");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 10, 233, 28);
		panel_3.add(lblNewLabel_1_1);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel_6_1.setBackground(new Color(255, 204, 102));
		panel_6_1.setBounds(313, 62, 212, 237);
		panel_3.add(panel_6_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("BÀN SỬ DỤNG");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(42, 10, 134, 24);
		panel_6_1.add(lblNewLabel_2_1);
		
		lblTrong = new JLabel("Trống:");
		lblTrong.setFont(new Font("Arial", Font.BOLD, 14));
		lblTrong.setBounds(22, 65, 163, 24);
		panel_6_1.add(lblTrong);
		
		lblDangGiu = new JLabel("Đang giữ:");
		lblDangGiu.setFont(new Font("Arial", Font.BOLD, 14));
		lblDangGiu.setBounds(22, 99, 121, 24);
		panel_6_1.add(lblDangGiu);
		
		lblDangPhucVu = new JLabel("Đang phục vụ:");
		lblDangPhucVu.setFont(new Font("Arial", Font.BOLD, 14));
		lblDangPhucVu.setBounds(22, 133, 121, 24);
		panel_6_1.add(lblDangPhucVu);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(618, 305, 612, 373);
		panel_trangchu.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Thống kê món phổ biến");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 10, 233, 28);
		panel_4.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 592, 232);
		panel_4.add(scrollPane);
		
		String[] colnamesDGM = {
				"STT", "Tên món", "Loại món", "Đơn giá", "Lượt dùng"
		};
		tableModel = new DefaultTableModel(colnamesDGM, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		LocalDate ngayHienTai = LocalDate.now();
		updateLblTongDoanhThu(ngayHienTai);
		updateLblDonDaThucHien();
		updateLblTongTienTrongKet();
		updateLblTongLuotKhach();
		updateLblTongMonBanRa();
		updateLblBan();
		updateLblLuotDatBan();
		updateLblDoDatBan();
		updateTableMon(Mon_DAO.layDanhSachMonVaLuotDung(ngayHienTai));
	}
	private void updateLblTongDoanhThu(LocalDate ngayHienTai) {
	    
	    double tongDoanhThu = HoaDon_DAO.tinhTongDoanhThuTheoNgay(ngayHienTai);
	    
	    // Định dạng số tiền theo kiểu VNĐ
	    DecimalFormat df = new DecimalFormat("#,###");
	    String tongDoanhThuFormatted = df.format(tongDoanhThu);
	    lblTongDoanhThu.setText(tongDoanhThuFormatted);
	}
	private void updateLblDonDaThucHien() {
	    int count = ChiTietDonDatBan_DAO.demChiTietDonDatBanTrongNgay();
	    lblDonDaThucHien.setText(count+"");
	}
	private void updateLblTongTienTrongKet() {
	    double total = HoaDon_DAO.tinhTongDoanhThuTienMatTrongNgay();
	    DecimalFormat df = new DecimalFormat("#,###");
	    lblTongTienKet.setText("Tổng tiền trong két: "+ df.format(total));
	}
	private void updateLblTongLuotKhach() {
	    int count = DonDatBan_DAO.demSoKhachTrongNgayHienTai();
	    lblTongLuotKhach.setText("Tổng lượt khách: " + count);
	}
	private void updateLblTongMonBanRa() {
	    int count = ChiTietDonGoiMon_DAO.layTongSoLuongMonBanRa();
	    lblTongMonAnBanRa.setText("Tổng món ăn bán ra: " + count);
	}
	private void updateLblLuotDatBan() {
	    int count = DonDatBan_DAO.getSLDDBHomNay();
	    lblLuotDungBan.setText(count+"");
	}
	private void updateLblBan() {
		int countBanTrong = 0;
		int countBanDangPhucVu = 0;
		int countBanDangCho = 0;
		for (Ban ban: dsBan) {
			if (ban.getTinhTrang() == 1) countBanTrong++;
			else if (ban.getTinhTrang() == 2) countBanDangPhucVu++;
			else if (ban.getTinhTrang() == 3) countBanDangCho++;
		}
		lblTrong.setText("Trống: "+countBanTrong);
		lblDangGiu.setText("Đang giữ: "+countBanDangCho);
		lblDangPhucVu.setText("Đang phục vụ: "+ countBanDangPhucVu);
	}
	private void updateLblDoDatBan() {
		int countChoNhan = 0;
		int countDaNhan = 0;
		int countDaHuy = 0;
		for (DonDatBan ddb: dsDDB) {
			if (ddb.getTrangThai() == 1) countChoNhan++;
			else if (ddb.getTrangThai() == 0) countDaNhan++;
			else if (ddb.getTrangThai() == 2) countDaHuy++;
		}
		lblChoNhan.setText("Chờ nhận: "+countChoNhan);
		lblDaNhan.setText("Đã nhân: "+countDaNhan);
		lblDaHuy.setText("Đã hủy: "+ countDaHuy);
	}
	public void updateTableMon(Map<Mon, Integer> mapMon) {
	    // Tạo danh sách từ map để sắp xếp
	    List<Map.Entry<Mon, Integer>> danhSach = new ArrayList<>(mapMon.entrySet());
	    System.out.println(danhSach.size());
	    danhSach.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

	    // Xóa dữ liệu cũ
	    tableModel.setRowCount(0);

	    // Thêm dữ liệu mới
	    int stt =0;
	    for (Map.Entry<Mon, Integer> entry : danhSach) {
	    	stt++;
	        Mon mon = entry.getKey();
	        int luotDung = entry.getValue();
	        tableModel.addRow(new Object[]{
	        	stt,
	            mon.getTenMon(),
	            mon.getLoaiMon(),
	            mon.getDonGia(),
	            luotDung
	        });
	        if (stt == 10) break; // Chỉ lấy 5 món phổ biến nhất
	    }
	}
    public JPanel getPanel() {
    	return this.panel_trangchu;
    }

}
