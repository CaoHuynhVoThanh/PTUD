package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class ThongKe_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableTKMon;
	private DefaultTableModel tableModelMon;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKe_GUI frame = new ThongKe_GUI();
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
	public ThongKe_GUI() {
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
		
		JMenuItem mi_TrangChu = new JMenuItem("      TRANG CHỦ");
		mi_TrangChu.setBackground(new Color(255, 153, 0));
		mi_TrangChu.setSelected(true);
		mi_TrangChu.setFont(new Font("Segoe UI", Font.BOLD, 28));
		mi_TrangChu.setHorizontalAlignment(SwingConstants.LEFT);
		mi_TrangChu.setForeground(new Color(255, 255, 255));
		mi_TrangChu.setBounds(20, 77, 291, 61);
		panel.add(mi_TrangChu);
		
		JButton btnNewButton = new JButton("ĐĂNG XUẤT");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setBounds(89, 641, 164, 42);
		panel.add(btnNewButton);
		
		JMenuItem mi_DatBan = new JMenuItem("      ĐẶT BÀN");
		mi_DatBan.setSelected(true);
		mi_DatBan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_DatBan.setForeground(Color.WHITE);
		mi_DatBan.setFont(new Font("Segoe UI", Font.BOLD, 28));
		mi_DatBan.setBackground(new Color(255, 153, 0));
		mi_DatBan.setBounds(20, 138, 291, 61);
		panel.add(mi_DatBan);
		
		JMenuItem mi_NhanBan = new JMenuItem("      NHẬN BÀN");
		mi_NhanBan.setSelected(true);
		mi_NhanBan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_NhanBan.setForeground(Color.WHITE);
		mi_NhanBan.setFont(new Font("Segoe UI", Font.BOLD, 28));
		mi_NhanBan.setBackground(new Color(255, 153, 0));
		mi_NhanBan.setBounds(20, 200, 291, 61);
		panel.add(mi_NhanBan);
		
		JMenuItem mi_GoiMon = new JMenuItem("      GỌI MÓN");
		mi_GoiMon.setSelected(true);
		mi_GoiMon.setHorizontalAlignment(SwingConstants.LEFT);
		mi_GoiMon.setForeground(Color.WHITE);
		mi_GoiMon.setFont(new Font("Segoe UI", Font.BOLD, 28));
		mi_GoiMon.setBackground(new Color(255, 153, 0));
		mi_GoiMon.setBounds(20, 262, 291, 61);
		panel.add(mi_GoiMon);
		
		JMenuItem mi_ThanhToan = new JMenuItem("      THANH TOÁN");
		mi_ThanhToan.setSelected(true);
		mi_ThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_ThanhToan.setForeground(Color.WHITE);
		mi_ThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 28));
		mi_ThanhToan.setBackground(new Color(255, 153, 0));
		mi_ThanhToan.setBounds(20, 322, 291, 61);
		panel.add(mi_ThanhToan);
		
		JMenuItem mi_LichSu = new JMenuItem("      LỊCH SỬ");
		mi_LichSu.setSelected(true);
		mi_LichSu.setHorizontalAlignment(SwingConstants.LEFT);
		mi_LichSu.setForeground(Color.WHITE);
		mi_LichSu.setFont(new Font("Segoe UI", Font.BOLD, 28));
		mi_LichSu.setBackground(new Color(255, 153, 0));
		mi_LichSu.setBounds(20, 382, 291, 61);
		panel.add(mi_LichSu);
		
		JMenuItem mi_ThongKe = new JMenuItem("      THỐNG KÊ");
		mi_ThongKe.setSelected(true);
		mi_ThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		mi_ThongKe.setForeground(Color.WHITE);
		mi_ThongKe.setFont(new Font("Segoe UI", Font.BOLD, 28));
		mi_ThongKe.setBackground(new Color(255, 153, 0));
		mi_ThongKe.setBounds(20, 498, 291, 61);
		panel.add(mi_ThongKe);
		
		JMenuItem mi_QuanLy = new JMenuItem("      QUẢN LÝ");
		mi_QuanLy.setSelected(true);
		mi_QuanLy.setHorizontalAlignment(SwingConstants.LEFT);
		mi_QuanLy.setForeground(Color.WHITE);
		mi_QuanLy.setFont(new Font("Segoe UI", Font.BOLD, 28));
		mi_QuanLy.setBackground(new Color(255, 153, 0));
		mi_QuanLy.setBounds(20, 439, 291, 61);
		panel.add(mi_QuanLy);
		JPanel pThongKe = new JPanel();
		pThongKe.setBounds(286, 138, 1237, 689);
		contentPane.add(pThongKe);
		pThongKe.setLayout(null);
		
		JPanel pTKNgay = new JPanel();
		pTKNgay.setBackground(new Color(255, 255, 255));
		pTKNgay.setBounds(22, 10, 426, 229);
		pThongKe.add(pTKNgay);
		pTKNgay.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Thống kê theo ngày");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(20, 5, 210, 28);
		pTKNgay.add(lblNewLabel_4);
		
		JPanel pTKTuan = new JPanel();
		pTKTuan.setBackground(new Color(255, 255, 255));
		pTKTuan.setBounds(22, 263, 780, 200);
		pThongKe.add(pTKTuan);
		pTKTuan.setLayout(null);
		
		JLabel lblNewLabel_4_1 = new JLabel("Thống kê theo tuần");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(20, 5, 210, 28);
		pTKTuan.add(lblNewLabel_4_1);
		
		JPanel pTKNam = new JPanel();
		pTKNam.setBackground(new Color(255, 255, 255));
		pTKNam.setBounds(22, 479, 780, 200);
		pThongKe.add(pTKNam);
		pTKNam.setLayout(null);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Thống kê theo năm");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_1_1.setBounds(20, 5, 210, 28);
		pTKNam.add(lblNewLabel_4_1_1);
		
		JPanel pChonNgay = new JPanel();
		pChonNgay.setBackground(new Color(255, 153, 51));
		pChonNgay.setBounds(492, 10, 735, 81);
		pThongKe.add(pChonNgay);
		pChonNgay.setLayout(null);
		
		JLabel lblChonNgay = new JLabel("Chọn ngày thống kê");
		lblChonNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChonNgay.setForeground(new Color(255, 255, 255));
		lblChonNgay.setBounds(54, 23, 209, 30);
		pChonNgay.add(lblChonNgay);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(306, 29, 215, 26);
		pChonNgay.add(lblNewLabel_1);
		
		JButton btnChonNgay = new JButton("Chọn");
		btnChonNgay.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnChonNgay.setForeground(new Color(255, 255, 255));
		btnChonNgay.setBackground(new Color(0, 0, 0));
		btnChonNgay.setBounds(588, 20, 109, 43);
		pChonNgay.add(btnChonNgay);
		
		JPanel pTongHoaDon = new JPanel();
		pTongHoaDon.setBackground(new Color(255, 255, 255));
		pTongHoaDon.setBounds(492, 118, 220, 121);
		pThongKe.add(pTongHoaDon);
		pTongHoaDon.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tổng số hóa đơn");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 0, 220, 39);
		pTongHoaDon.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ngày");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(22, 37, 84, 22);
		pTongHoaDon.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tháng");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(22, 60, 84, 22);
		pTongHoaDon.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Năm");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_1.setBounds(22, 82, 84, 22);
		pTongHoaDon.add(lblNewLabel_3_1_1);
		
		JPanel pSoDDB = new JPanel();
		pSoDDB.setBackground(new Color(255, 255, 255));
		pSoDDB.setBounds(749, 118, 220, 121);
		pThongKe.add(pSoDDB);
		pSoDDB.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Số đơn đặt bàn");
		lblNewLabel_2_1.setBounds(48, 10, 122, 20);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		pSoDDB.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Ngày");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(23, 40, 84, 22);
		pSoDDB.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Tháng");
		lblNewLabel_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_2.setBounds(23, 63, 84, 22);
		pSoDDB.add(lblNewLabel_3_1_2);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Năm");
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_1_1.setBounds(23, 85, 84, 22);
		pSoDDB.add(lblNewLabel_3_1_1_1);
		
		JPanel pSoTienCoc = new JPanel();
		pSoTienCoc.setBackground(new Color(255, 255, 255));
		pSoTienCoc.setBounds(1007, 118, 220, 121);
		pThongKe.add(pSoTienCoc);
		pSoTienCoc.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tổng số tiền cọc");
		lblNewLabel_2_2.setBounds(43, 10, 141, 20);
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		pSoTienCoc.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Ngày");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1.setBounds(34, 40, 84, 22);
		pSoTienCoc.add(lblNewLabel_3_2_1);
		
		JLabel lblNewLabel_3_1_2_1 = new JLabel("Tháng");
		lblNewLabel_3_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_2_1.setBounds(34, 63, 84, 22);
		pSoTienCoc.add(lblNewLabel_3_1_2_1);
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Năm");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_1_1_1.setBounds(34, 85, 84, 22);
		pSoTienCoc.add(lblNewLabel_3_1_1_1_1);
		
		JPanel pTKMon = new JPanel();
		pTKMon.setBackground(new Color(255, 255, 255));
		pTKMon.setBounds(812, 263, 415, 416);
		pThongKe.add(pTKMon);
		pTKMon.setLayout(null);
		
		String[] colnamesMon = {
				"STT", "Tên món", "Số lượng"
		};
		tableModelMon = new DefaultTableModel(colnamesMon, 0);
		tableTKMon = new JTable(tableModelMon);
		tableTKMon.setBounds(0, 221, 415, 195);
		scrollPane = new JScrollPane(tableTKMon);
		scrollPane.setBounds(0, 221, 415, 195);
		pTKMon.add(scrollPane);
		
		JLabel lblTKMon = new JLabel("Thống kê theo món ăn");
		lblTKMon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTKMon.setBounds(20, 5, 210, 28);
		pTKMon.add(lblTKMon);
		
	}
}
