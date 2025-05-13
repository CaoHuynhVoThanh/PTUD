package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.Mon_DAO;
import entities.HoaDon;
import entities.Mon;
import dao.DonDatBan_DAO;

public class ThongKe_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableTKMon;
	private DefaultTableModel tableModelMon;
	private JScrollPane scrollPane;
	private JPanel pThongKe;
	private ConnectDB con;
	private JDateChooser dateChooser = new JDateChooser();
	private ArrayList<HoaDon> dsHDNgay = HoaDon_DAO.layHoaDonTheoNgay(LocalDate.now());
	private ArrayList<HoaDon> dsHDTuan = HoaDon_DAO.layHoaDonTheoTuan(LocalDate.now());
	private ArrayList<HoaDon> dsHDNam = HoaDon_DAO.layHoaDonTheoNam(LocalDate.now());
	private Map<Mon, Integer> mapMon = Mon_DAO.layDanhSachMonVaLuotDung(LocalDate.now());
	private JButton btnChonNgay;
	private JPanel pTKNgay;
	private JPanel pTKTuan;
	private JPanel pTKNam;
	private JPanel pTKMon;
	private JButton btnChiTietNgay;
	private JButton btnChiTietTuan;
	private JButton btnChiTietNam;
	private JLabel lblNgayHD;
	private JLabel lblThangHD;
	private JLabel lblNamHD;
	private JLabel lblNgayDDB;
	private JLabel lblThangDDB;
	private JLabel lblNamDDB;
	private JLabel lblNamTienCoc;
	private JLabel lblThangTienCoc;
	private JLabel lblNgayTienCoc;

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
		System.out.println(dsHDNam.size());
		con = new ConnectDB();
		con.getInstance().connect();
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
		pThongKe = new JPanel();
		pThongKe.setBounds(286, 138, 1237, 689);
		contentPane.add(pThongKe);
		pThongKe.setLayout(null);
		
		pTKNgay = new JPanel();
		pTKNgay.setBackground(new Color(255, 255, 255));
		pTKNgay.setBounds(22, 10, 426, 229);
		pThongKe.add(pTKNgay);
		pTKNgay.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Thống kê theo ngày");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(20, 5, 210, 28);
		pTKNgay.add(lblNewLabel_4);
		
		pTKTuan = new JPanel();
		pTKTuan.setBackground(new Color(255, 255, 255));
		pTKTuan.setBounds(22, 263, 780, 200);
		pThongKe.add(pTKTuan);
		pTKTuan.setLayout(null);
		
		JLabel lblNewLabel_4_1 = new JLabel("Thống kê theo tuần");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(20, 5, 210, 28);
		pTKTuan.add(lblNewLabel_4_1);
		
		pTKNam = new JPanel();
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
		
		Date today = new Date();
        dateChooser.setDate(today);
		dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(306, 20, 215, 40);
        dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dateChooser.setMaxSelectableDate(today);
        pChonNgay.add(dateChooser);
        
		btnChonNgay = new JButton("Chọn");
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
		
		lblNgayHD = new JLabel("Ngày");
		lblNgayHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayHD.setBounds(22, 37, 169, 22);
		pTongHoaDon.add(lblNgayHD);
		
		lblThangHD = new JLabel("Tháng");
		lblThangHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThangHD.setBounds(22, 60, 169, 22);
		pTongHoaDon.add(lblThangHD);
		
		lblNamHD = new JLabel("Năm");
		lblNamHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNamHD.setBounds(22, 82, 169, 22);
		pTongHoaDon.add(lblNamHD);
		
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
		
		lblNgayDDB = new JLabel("Ngày");
		lblNgayDDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayDDB.setBounds(23, 40, 168, 22);
		pSoDDB.add(lblNgayDDB);
		
		lblThangDDB = new JLabel("Tháng");
		lblThangDDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThangDDB.setBounds(23, 63, 168, 22);
		pSoDDB.add(lblThangDDB);
		
		lblNamDDB = new JLabel("Năm");
		lblNamDDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNamDDB.setBounds(23, 85, 168, 22);
		pSoDDB.add(lblNamDDB);
		
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
		
		lblNgayTienCoc = new JLabel("Ngày");
		lblNgayTienCoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayTienCoc.setBounds(34, 40, 164, 22);
		pSoTienCoc.add(lblNgayTienCoc);
		
		lblThangTienCoc = new JLabel("Tháng");
		lblThangTienCoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThangTienCoc.setBounds(34, 63, 164, 22);
		pSoTienCoc.add(lblThangTienCoc);
		
		lblNamTienCoc = new JLabel("Năm");
		lblNamTienCoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNamTienCoc.setBounds(34, 85, 164, 22);
		pSoTienCoc.add(lblNamTienCoc);
		
		pTKMon = new JPanel();
		pTKMon.setBackground(new Color(255, 255, 255));
		pTKMon.setBounds(812, 263, 415, 416);
		pThongKe.add(pTKMon);
		pTKMon.setLayout(null);
		
		String[] colnamesMon = {
				"STT", "Tên món", "Lượt dùng"
		};
		tableModelMon = new DefaultTableModel(colnamesMon, 0);
		tableTKMon = new JTable(tableModelMon);
		tableTKMon.setBounds(0, 221, 415, 195);
		scrollPane = new JScrollPane(tableTKMon);
		scrollPane.setBounds(0, 239, 415, 177);
		pTKMon.add(scrollPane);
		
		JLabel lblTKMon = new JLabel("Thống kê theo món ăn");
		lblTKMon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTKMon.setBounds(20, 5, 210, 28);
		pTKMon.add(lblTKMon);
		pTKNgay.add(createDailyShiftPieChart());
	    
		
		btnChiTietNgay = new JButton("Chi tiết");
		btnChiTietNgay.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChiTietNgay.setBackground(new Color(0, 206, 209));
		btnChiTietNgay.setForeground(Color.WHITE);
		btnChiTietNgay.setBounds(338, 5, 85, 27);
		pTKNgay.add(btnChiTietNgay);;
		
		btnChiTietTuan = new JButton("Chi tiết");
		btnChiTietTuan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChiTietTuan.setBackground(new Color(0, 206, 209));
		btnChiTietTuan.setForeground(Color.WHITE);
		btnChiTietTuan.setBounds(685, 5, 85, 27);
		pTKTuan.add(btnChiTietTuan);

		// Add Daily Shift Pie Chart to pTKNgay
		ChartPanel dailyChartPanel = (ChartPanel) createDailyShiftPieChart();
		dailyChartPanel.setBounds(0, 36, 426, 193); // Set bounds explicitly
		pTKNgay.add(dailyChartPanel);

		// Add Weekly Bar Chart to pTKTuan
		ChartPanel weeklyChartPanel = (ChartPanel) createWeeklyBarChart();
		weeklyChartPanel.setBounds(0, 32, 780, 172); // Set bounds explicitly
		pTKTuan.add(weeklyChartPanel);

		// Add Yearly Bar Chart to pTKNam
		ChartPanel yearlyChartPanel = (ChartPanel) createYearlyBarChart();
		yearlyChartPanel.setBounds(0, 32, 780, 168); // Set bounds explicitly
		pTKNam.add(yearlyChartPanel);

		// Add Dish Pie Chart to pTKMon
		ChartPanel dishChartPanel = (ChartPanel) createDishPieChart();
		dishChartPanel.setBounds(0, 36, 426, 199); // Set bounds explicitly
		pTKMon.add(dishChartPanel);
		
		btnChiTietNam = new JButton("Chi tiết");
		btnChiTietNam.setForeground(Color.WHITE);
		btnChiTietNam.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChiTietNam.setBackground(new Color(0, 206, 209));
		btnChiTietNam.setBounds(685, 6, 85, 27);
		pTKNam.add(btnChiTietNam);
		
		JButton btnChiTietMon = new JButton("Chi tiết");
		btnChiTietMon.setBackground(new Color(0, 206, 209));
		btnChiTietMon.setForeground(Color.WHITE);
		btnChiTietMon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChiTietMon.setBounds(330, 5, 85, 27);
		pTKMon.add(btnChiTietMon);
		updateTableMon(mapMon);
		
		btnChonNgay.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy ngày được chọn từ dateChooser và chuyển sang LocalDate
		        Date selectedDate = dateChooser.getDate();
		        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		        // Cập nhật dữ liệu theo ngày mới
		        dsHDNgay = HoaDon_DAO.layHoaDonTheoNgay(localDate);
		        dsHDTuan = HoaDon_DAO.layHoaDonTheoTuan(localDate);
		        dsHDNam = HoaDon_DAO.layHoaDonTheoNam(localDate);
		        mapMon = Mon_DAO.layDanhSachMonVaLuotDung(localDate);

		        // Cập nhật dữ liệu cho các biểu đồ
		        updateDailyShiftPieChart();
		        updateWeeklyBarChart();
		        updateYearlyBarChart();
		        updateDishPieChart();

		        // Cập nhật bảng thống kê món
		        updateTableMon(mapMon);
		        capNhatLblHD();
		        capNhatLblDDB();
		        capNhatLblTienCoc();
		    }
		});
		
		btnChiTietNgay.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      
		        double totalRevenue = 0;
		        double morningRevenue = 0;
		        double afternoonRevenue = 0;
		        for (HoaDon hd : dsHDNgay) {
		            double revenue = HoaDon_DAO.tinhTongTien(hd.getMaHD());
		            totalRevenue += revenue;
		            int hour = hd.getThoiGianThanhToan().getHour();
		            if (hour >= 10 && hour < 14) {
		                morningRevenue += revenue;
		            } else if (hour >= 14 && hour < 22) {
		                afternoonRevenue += revenue;
		            }
		        }

		        JDialog dialog = new JDialog();
		        dialog.setTitle("Chi tiết doanh thu ngày");
		        dialog.setSize(400, 300);
		        dialog.setLocationRelativeTo(null);
		        dialog.setModal(true);
		        dialog.getContentPane().setLayout(null);

		        // Hiển thị thông tin
		        JLabel lblTotal = new JLabel("Tổng doanh thu: " + String.format("%.2f", totalRevenue) + " VND");
		        lblTotal.setBounds(20, 20, 350, 30);
		        dialog.getContentPane().add(lblTotal);

		        JLabel lblMorning = new JLabel("Doanh thu ca sáng: " + String.format("%.2f", morningRevenue) + " VND");
		        lblMorning.setBounds(20, 70, 350, 30);
		        dialog.getContentPane().add(lblMorning);

		        JLabel lblAfternoon = new JLabel("Doanh thu ca chiều: " + String.format("%.2f", afternoonRevenue) + " VND");
		        lblAfternoon.setBounds(20, 120, 350, 30);
		        dialog.getContentPane().add(lblAfternoon);

		        // Hiển thị dialog
		        dialog.setVisible(true);
		    }
		});
		btnChiTietTuan.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Date selectedDate = dateChooser.getDate();
		        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        // Tính doanh thu tuần này
		        double totalRevenue = 0;
		        double maxRevenue = Double.MIN_VALUE;
		        double minRevenue = Double.MAX_VALUE;
		        String maxDay = "";
		        String minDay = "";
		        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"STT", "Thứ trong ngày", "Doanh thu"}, 0);

		        double[] dailyRevenue = new double[7];
		        String[] daysOfWeek = {"Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật"};

		        for (HoaDon hd : dsHDTuan) {
		            int dayIndex = hd.getThoiGianThanhToan().getDayOfWeek().getValue() - 1;
		            double revenue = HoaDon_DAO.tinhTongTien(hd.getMaHD());
		            dailyRevenue[dayIndex] += revenue;
		            totalRevenue += revenue;
		        }

		        // Tìm ngày có doanh thu cao nhất và thấp nhất
		        for (int i = 0; i < dailyRevenue.length; i++) {
		            if (dailyRevenue[i] > maxRevenue) {
		                maxRevenue = dailyRevenue[i];
		                maxDay = daysOfWeek[i];
		            }
		            if (dailyRevenue[i] < minRevenue) {
		                minRevenue = dailyRevenue[i];
		                minDay = daysOfWeek[i];
		            }
		        }

		        // Tạo danh sách dữ liệu cho bảng và sắp xếp giảm dần theo doanh thu
		        List<Object[]> rows = new ArrayList<>();
		        for (int i = 0; i < dailyRevenue.length; i++) {
		            rows.add(new Object[]{i + 1, daysOfWeek[i], dailyRevenue[i]});
		        }

		        rows.sort((a, b) -> Double.compare((double) b[2], (double) a[2]));

		        int stt = 1;
		        for (Object[] row : rows) {
		            tableModel.addRow(new Object[]{
		                stt++,
		                row[1],
		                String.format("%.2f", row[2]) + " VND"
		            });
		        }

		        // So sánh với tuần trước
		        ArrayList<HoaDon> dsHDLastWeek = HoaDon_DAO.layHoaDonTheoTuan(localDate.minusWeeks(1));
		        double lastWeekRevenue = 0;
		        for (HoaDon hd : dsHDLastWeek) {
		            lastWeekRevenue += HoaDon_DAO.tinhTongTien(hd.getMaHD());
		        }
		        System.out.println(lastWeekRevenue);
		        double percentageChange = lastWeekRevenue == 0 ? 0 : ((totalRevenue - lastWeekRevenue) / lastWeekRevenue) * 100;
		        String comparisonText = percentageChange > 0 
		            ? "Tăng " + String.format("%.2f", percentageChange) + " %" 
		            : (percentageChange < 0 
		                ? "Giảm " + String.format("%.2f", Math.abs(percentageChange)) + " %" 
		                : "Không thay đổi");

		        // Tạo dialog
		        JDialog dialog = new JDialog();
		        dialog.setTitle("Chi tiết doanh thu tuần");
		        dialog.setSize(600, 400);
		        dialog.setLocationRelativeTo(null);
		        dialog.setModal(true);
		        dialog.getContentPane().setLayout(null);

		        // Hiển thị thống kê
		        JLabel lblTotal = new JLabel("Tổng doanh thu: " + String.format("%.2f", totalRevenue) + " VND");
		        lblTotal.setBounds(20, 20, 550, 30);
		        dialog.getContentPane().add(lblTotal);

		        JLabel lblMax = new JLabel("Ngày có doanh thu cao nhất: " + maxDay + " (" + String.format("%.2f", maxRevenue) + " VND)");
		        lblMax.setBounds(20, 60, 550, 30);
		        dialog.getContentPane().add(lblMax);

		        JLabel lblMin = new JLabel("Ngày có doanh thu thấp nhất: " + minDay + " (" + String.format("%.2f", minRevenue) + " VND)");
		        lblMin.setBounds(20, 100, 550, 30);
		        dialog.getContentPane().add(lblMin);

		        JLabel lblComparison = new JLabel("So với tuần trước: " + comparisonText);
		        lblComparison.setBounds(20, 140, 550, 30);
		        dialog.getContentPane().add(lblComparison);

		        // Bảng doanh thu từng ngày
		        JTable table = new JTable(tableModel);
		        JScrollPane scrollPane = new JScrollPane(table);
		        scrollPane.setBounds(20, 180, 550, 150);
		        dialog.getContentPane().add(scrollPane);

		        // Hiển thị dialog
		        dialog.setVisible(true);
		    }
		});

		btnChiTietNam.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Tính doanh thu năm hiện tại
		        double totalRevenue = 0;
		        double maxRevenue = Double.MIN_VALUE;
		        double minRevenue = Double.MAX_VALUE;
		        String maxMonth = "";
		        String minMonth = "";
		        double[] monthlyRevenue = new double[12];
		        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

		        for (HoaDon hd : dsHDNam) {
		            int monthIndex = hd.getThoiGianThanhToan().getMonthValue() - 1;
		            double revenue = HoaDon_DAO.tinhTongTien(hd.getMaHD());
		            monthlyRevenue[monthIndex] += revenue;
		            totalRevenue += revenue;
		        }

		        // Tìm tháng có doanh thu cao nhất và thấp nhất
		        for (int i = 0; i < monthlyRevenue.length; i++) {
		            if (monthlyRevenue[i] > maxRevenue) {
		                maxRevenue = monthlyRevenue[i];
		                maxMonth = months[i];
		            }
		            if (monthlyRevenue[i] < minRevenue) {
		                minRevenue = monthlyRevenue[i];
		                minMonth = months[i];
		            }
		        }

		        // So sánh với năm trước
		        ArrayList<HoaDon> dsHDLastYear = HoaDon_DAO.layHoaDonTheoNam(LocalDate.now().minusYears(1));
		        double lastYearRevenue = 0;
		        for (HoaDon hd : dsHDLastYear) {
		            lastYearRevenue += HoaDon_DAO.tinhTongTien(hd.getMaHD());
		        }
		        double percentageChange = lastYearRevenue == 0 ? 0 : ((totalRevenue - lastYearRevenue) / lastYearRevenue) * 100;
		        String comparisonText = percentageChange > 0
		            ? "Tăng " + String.format("%.2f", percentageChange) + " %"
		            : (percentageChange < 0
		                ? "Giảm " + String.format("%.2f", Math.abs(percentageChange)) + " %"
		                : "Không thay đổi");

		        // Tạo danh sách dữ liệu cho bảng và sắp xếp giảm dần theo doanh thu
		        List<Object[]> rows = new ArrayList<>();
		        for (int i = 0; i < monthlyRevenue.length; i++) {
		            rows.add(new Object[]{i + 1, months[i], monthlyRevenue[i]});
		        }
		        rows.sort((a, b) -> Double.compare((double) b[2], (double) a[2]));

		        // Tạo model cho bảng
		        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"STT", "Tên tháng", "Doanh thu"}, 0);
		        int stt = 1;
		        for (Object[] row : rows) {
		            tableModel.addRow(new Object[]{stt++, row[1], String.format("%.2f", row[2]) + " VND"});
		        }

		        // Tạo dialog hiển thị thông tin
		        JDialog dialog = new JDialog();
		        dialog.setTitle("Chi tiết doanh thu năm");
		        dialog.setSize(600, 500);
		        dialog.setLocationRelativeTo(null);
		        dialog.setModal(true);
		        dialog.getContentPane().setLayout(null);

		        // Hiển thị thông tin
		        JLabel lblTotal = new JLabel("Tổng doanh thu: " + String.format("%.2f", totalRevenue) + " VND");
		        lblTotal.setBounds(20, 20, 550, 30);
		        dialog.getContentPane().add(lblTotal);

		        JLabel lblMax = new JLabel("Tháng có doanh thu cao nhất: " + maxMonth + " (" + String.format("%.2f", maxRevenue) + " VND)");
		        lblMax.setBounds(20, 60, 550, 30);
		        dialog.getContentPane().add(lblMax);

		        JLabel lblMin = new JLabel("Tháng có doanh thu thấp nhất: " + minMonth + " (" + String.format("%.2f", minRevenue) + " VND)");
		        lblMin.setBounds(20, 100, 550, 30);
		        dialog.getContentPane().add(lblMin);

		        JLabel lblComparison = new JLabel("So với năm trước: " + comparisonText);
		        lblComparison.setBounds(20, 140, 550, 30);
		        dialog.getContentPane().add(lblComparison);

		        // Bảng doanh thu từng tháng
		        JTable table = new JTable(tableModel);
		        JScrollPane scrollPane = new JScrollPane(table);
		        scrollPane.setBounds(20, 180, 550, 250);
		        dialog.getContentPane().add(scrollPane);

		        // Hiển thị dialog
		        dialog.setVisible(true);
		    }
		});
		btnChiTietMon.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Calculate total usage count
		        int totalUsage = mapMon.values().stream().mapToInt(Integer::intValue).sum();

		        // Prepare data for the table and sort in descending order by usage count
		        List<Object[]> rows = new ArrayList<>();
		        mapMon.forEach((mon, luotDung) -> rows.add(new Object[]{mon.getTenMon(), luotDung}));
		        rows.sort((a, b) -> Integer.compare((int) b[1], (int) a[1])); // Sort descending by usage count

		        // Create table model
		        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"STT", "Tên món", "Lượt dùng"}, 0);
		        int stt = 1;
		        for (Object[] row : rows) {
		            tableModel.addRow(new Object[]{stt++, row[0], row[1]});
		        }

		        // Create dialog
		        JDialog dialog = new JDialog();
		        dialog.setTitle("Chi tiết thống kê món ăn");
		        dialog.setSize(600, 400);
		        dialog.setLocationRelativeTo(null);
		        dialog.setModal(true);
		        dialog.getContentPane().setLayout(null);

		        // Display total usage count
		        JLabel lblTotal = new JLabel("Tổng lượt dùng: " + totalUsage);
		        lblTotal.setBounds(20, 20, 550, 30);
		        dialog.getContentPane().add(lblTotal);

		        // Display table
		        JTable table = new JTable(tableModel);
		        JScrollPane scrollPane = new JScrollPane(table);
		        scrollPane.setBounds(20, 60, 550, 280);
		        dialog.getContentPane().add(scrollPane);

		        // Show dialog
		        dialog.setVisible(true);
		    }
		});
		capNhatLblHD();
		capNhatLblDDB();
		capNhatLblTienCoc();
	}
	private void updateDailyShiftPieChart() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    int morningShiftCount = 0;
    int afternoonShiftCount = 0;

    for (HoaDon hd : dsHDNgay) {
        int hour = hd.getThoiGianThanhToan().getHour();
        if (hour >= 10 && hour < 14) {
            morningShiftCount++;
        } else if (hour >= 14 && hour < 22) {
            afternoonShiftCount++;
        }
    }

    dataset.setValue("Morning Shift (10h-14h)", morningShiftCount);
    dataset.setValue("Afternoon Shift (14h-22h)", afternoonShiftCount);

    JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);
    ChartPanel newChartPanel = new ChartPanel(pieChart);

    for (Component component : pTKNgay.getComponents()) {
        if (component instanceof ChartPanel) {
            pTKNgay.remove(component);
            break;
        }
    }

    // Add the new chart panel
    newChartPanel.setBounds(0, 36, 426, 193); // Set bounds explicitly
    pTKNgay.add(newChartPanel);
    pTKNgay.revalidate();
    pTKNgay.repaint();
}

	private void updateWeeklyBarChart() {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    double[] weeklyRevenue = new double[7];

	    for (HoaDon hd : dsHDTuan) {
	        int dayIndex = hd.getThoiGianThanhToan().getDayOfWeek().getValue() - 1;
	        weeklyRevenue[dayIndex] += HoaDon_DAO.tinhTongTien(hd.getMaHD());
	    }

	    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	    for (int i = 0; i < days.length; i++) {
	        dataset.addValue(weeklyRevenue[i], "Revenue", days[i]);
	    }

	    JFreeChart barChart = ChartFactory.createBarChart(null, null, "Revenue", dataset, PlotOrientation.VERTICAL, false, true, false);
	    ChartPanel newChartPanel = new ChartPanel(barChart);

	    // Remove only the existing ChartPanel
	    for (Component component : pTKTuan.getComponents()) {
	        if (component instanceof ChartPanel) {
	            pTKTuan.remove(component);
	            break;
	        }
	    }

	    // Add the new chart panel
	    newChartPanel.setBounds(0, 32, 780, 172); // Set bounds explicitly
	    pTKTuan.add(newChartPanel);
	    pTKTuan.revalidate();
	    pTKTuan.repaint();
	}

	private void updateYearlyBarChart() {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    double[] monthlyRevenue = new double[12];

	    for (HoaDon hd : dsHDNam) {
	        int monthIndex = hd.getThoiGianThanhToan().getMonthValue() - 1;
	        monthlyRevenue[monthIndex] += HoaDon_DAO.tinhTongTien(hd.getMaHD());
	        System.out.println(HoaDon_DAO.tinhTongTien(hd.getMaHD()));
	    }

	    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	    for (int i = 0; i < months.length; i++) {
	        dataset.addValue(monthlyRevenue[i], "Revenue", months[i]);
	    }

	    JFreeChart barChart = ChartFactory.createBarChart(null, null, "Revenue", dataset, PlotOrientation.VERTICAL, false, true, false);
	    ChartPanel newChartPanel = new ChartPanel(barChart);

	    // Remove only the existing ChartPanel
	    for (Component component : pTKNam.getComponents()) {
	        if (component instanceof ChartPanel) {
	            pTKNam.remove(component);
	            break;
	        }
	    }

	    // Add the new chart panel
	    newChartPanel.setBounds(0, 32, 780, 168); // Set bounds explicitly
	    pTKNam.add(newChartPanel);
	    pTKNam.revalidate();
	    pTKNam.repaint();
	}

	private void updateDishPieChart() {
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    mapMon.entrySet().stream()
	        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
	        .limit(5)
	        .forEach(entry -> dataset.setValue(entry.getKey().getTenMon(), entry.getValue()));

	    JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);
	    ChartPanel newChartPanel = new ChartPanel(pieChart);

	    // Remove only the existing ChartPanel
	    for (Component component : pTKMon.getComponents()) {
	        if (component instanceof ChartPanel) {
	            pTKMon.remove(component);
	            break;
	        }
	    }

	    // Add the new chart panel
	    newChartPanel.setBounds(0, 36, 426, 199); // Set bounds explicitly
	    pTKMon.add(newChartPanel);
	    pTKMon.revalidate();
	    pTKMon.repaint();
	}

	
	public JPanel createDishPieChart() {
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    // Sort the map by values in descending order and take the top 5 entries
	    mapMon.entrySet().stream()
	        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by value descending
	        .limit(5) // Take the top 5
	        .forEach(entry -> dataset.setValue(entry.getKey().getTenMon(), entry.getValue()));

	    // Create the pie chart
	    JFreeChart pieChart = ChartFactory.createPieChart(
	            null,
	            dataset,
	            true,
	            true,
	            false
	    );

	    // Wrap the chart in a panel
	    ChartPanel chartPanel = new ChartPanel(pieChart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(426, 229)); // Set preferred size
	    return chartPanel;
	}

	public JPanel createDailyShiftPieChart() {
	    // Initialize counters for morning and afternoon shifts
	    int morningShiftCount = 0;
	    int afternoonShiftCount = 0;

	    // Iterate through the list of invoices for the day
	    for (HoaDon hd : dsHDNgay) {
	        int hour = hd.getThoiGianThanhToan().getHour();
	        if (hour >= 10 && hour < 14) {
	            morningShiftCount++;
	        } else if (hour >= 14 && hour < 22) {
	            afternoonShiftCount++;
	        }
	    }

	    // Create the dataset for the pie chart
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    dataset.setValue("Morning Shift (10h-14h)", morningShiftCount);
	    dataset.setValue("Afternoon Shift (14h-22h)", afternoonShiftCount);

	    // Create the pie chart
	    JFreeChart pieChart = ChartFactory.createPieChart(
	            null,
	            dataset,
	            true,
	            true,
	            false
	    );

	    // Wrap the chart in a panel
	    ChartPanel chartPanel = new ChartPanel(pieChart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(426, 229)); // Set preferred size
	    return chartPanel;
	}

	public JPanel createWeeklyBarChart() {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    double[] weeklyRevenue = new double[7]; // Array to store revenue for each day of the week

	    // Calculate revenue for each day of the week
	    for (HoaDon hd : dsHDTuan) {
	        int dayIndex = hd.getThoiGianThanhToan().getDayOfWeek().getValue() - 1; // Monday = 0, Sunday = 6
	        weeklyRevenue[dayIndex] += HoaDon_DAO.tinhTongTien(hd.getMaHD());
	    }

	    // Add data to the dataset
	    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	    for (int i = 0; i < days.length; i++) {
	        dataset.addValue(weeklyRevenue[i], "Revenue", days[i]);
	    }

	    // Create the bar chart
	    JFreeChart barChart = ChartFactory.createBarChart(
	            null,
	            null,
	            "Revenue",
	            dataset,
	            PlotOrientation.VERTICAL,
	            false,
	            true,
	            false
	    );

	    ChartPanel chartPanel = new ChartPanel(barChart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(780, 200)); // Set preferred size
	    return chartPanel;
	}

	public JPanel createYearlyBarChart() {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    double[] monthlyRevenue = new double[12]; // Array to store revenue for each month

	    // Calculate revenue for each month
	    for (HoaDon hd : dsHDNam) {
	        int monthIndex = hd.getThoiGianThanhToan().getMonthValue() - 1; // January = 0, December = 11
	        monthlyRevenue[monthIndex] += HoaDon_DAO.tinhTongTien(hd.getMaHD());
	    }

	    // Add data to the dataset
	    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	    for (int i = 0; i < months.length; i++) {
	        dataset.addValue(monthlyRevenue[i], "Revenue", months[i]);
	    }

	    // Create the bar chart
	    JFreeChart barChart = ChartFactory.createBarChart(
	            null,
	            null,
	            "Revenue",
	            dataset,
	            PlotOrientation.VERTICAL,
	            false,
	            true,
	            false
	    );

	    ChartPanel chartPanel = new ChartPanel(barChart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(780, 200)); // Set preferred size
	    return chartPanel;
	}
	public void updateTableMon(Map<Mon, Integer> mapMon) {
	    // Create a list from the map to sort
	    List<Map.Entry<Mon, Integer>> danhSach = new ArrayList<>(mapMon.entrySet());
	    danhSach.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue())); // Sort by usage count descending

	    // Clear old data
	    tableModelMon.setRowCount(0);

	    // Add new data
	    int stt = 1; // Serial number
	    for (Map.Entry<Mon, Integer> entry : danhSach) {
	        Mon mon = entry.getKey();
	        int luotDung = entry.getValue();
	        tableModelMon.addRow(new Object[]{
	            stt++,           
	            mon.getTenMon(), // Dish name
	            luotDung         // Usage count
	        });
	    }
	}
	private void capNhatLblHD() {
	    lblNgayHD.setText("Ngày: " + dsHDNgay.size() + " đơn");
	    lblThangHD.setText("Tháng: " + dsHDTuan.size() + " đơn");
	    lblNamHD.setText("Năm: " + dsHDNam.size() + " đơn");
	}
	private void capNhatLblDDB() {
		Date selectedDate = dateChooser.getDate();
        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    lblNgayDDB.setText("Ngày: " + DonDatBan_DAO.getSoDonDatBanTheoNgay(localDate) + " đơn");
	    lblThangDDB.setText("Tháng: " + DonDatBan_DAO.getSoDonDatBanTheoThang(localDate) + " đơn");
	    lblNamDDB.setText("Năm: " + DonDatBan_DAO.getSoDonDatBanTheoNam(localDate) + " đơn");
	}
	private void capNhatLblTienCoc() {
		Date selectedDate = dateChooser.getDate();
        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    lblNgayTienCoc.setText("Ngày: " + DonDatBan_DAO.tinhTongTienCocTheoNgay(localDate) + " VND");
	    lblThangTienCoc.setText("Tháng: " + DonDatBan_DAO.tinhTongTienCocTheoThang(localDate) + " VND");
	    lblNamTienCoc.setText("Năm: " + DonDatBan_DAO.tinhTongTienCocTheoNam(localDate) + " VND");
	}
    public JPanel getPanel() {
    	return this.pThongKe;
    }
}
