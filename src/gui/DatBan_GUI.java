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
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.Ban_DAO;
import dao.KhachHang_DAO;
import dao.KhuVuc_DAO;
import entities.Ban;
import entities.KhachHang;
import entities.KhuVuc;

import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class DatBan_GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_tenkh;
	private JPanel panel_dsBan;
	private JLabel lb_ma, lb_loai, lb_khuvuc, lb_vitri, lb_trangthai;
	private JButton btn_chuyen, btn_huy, btn_datban, btn_themmon;
	private JLabel lb_tenkh, lb_sdtkh;
	JComboBox<String> combMode;
	JDateChooser JDC_ngaychon = new JDateChooser();
    
	JComboBox<String> comb_tinhtrang, comb_kv, comb_tang, comb_loaiban;
	ArrayList<Ban> dsb = new ArrayList<>();
	JButton hiddenDateChange = new JButton("Date changed");
	ArrayList<KhuVuc> dskv = KhuVuc_DAO.getAllKhuVuc();
	public static JButton hiddenButton = new JButton("Xác nhận đặt bàn thành công");
	private ArrayList<JCheckBox> lcb = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectDB con = new ConnectDB();
					con.connect();
					DatBan_GUI frame = new DatBan_GUI();
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
	public DatBan_GUI() {
		JDC_ngaychon.setDate(new Date());
		ArrayList<Ban> dsb = Ban_DAO.getAllBan(JDC_ngaychon.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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
		logo.setIcon(new ImageIcon("src\\images\\App\\logo.png"));
		logo.setBounds(66, 22, 247, 89);
		panel_1.add(logo);
		
		JLabel avt = new JLabel("");
		ImageIcon originalIcon = new ImageIcon("src\\images\\App\\avt.png");
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
		
		JPanel panel_trangchu = new JPanel();
		panel_trangchu.setBackground(new Color(255, 255, 255));
		panel_trangchu.setBounds(285, 133, 1254, 704);
		contentPane.add(panel_trangchu);
		panel_trangchu.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		scrollPane.setBounds(23, 189, 796, 484);
		panel_trangchu.add(scrollPane);
		
		panel_dsBan = new JPanel();
		panel_dsBan.setPreferredSize(new Dimension(620, 999));
		scrollPane.setViewportView(panel_dsBan);
		panel_dsBan.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 24, 1193, 144);
		panel_trangchu.add(panel_2);
		panel_2.setLayout(null);
		
		tf_tenkh = new JTextField();
		tf_tenkh.setBounds(139, 10, 203, 31);
		panel_2.add(tf_tenkh);
		tf_tenkh.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 10, 150, 31);
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(51, 255, 51));
		panel_5.setBounds(26, 64, 20, 20);
		panel_2.add(panel_5);
		
		JLabel lblNewLabel_1_1 = new JLabel("Trống");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(56, 64, 150, 20);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Đang phục vụ");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(56, 101, 150, 20);
		panel_2.add(lblNewLabel_1_1_1);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBackground(new Color(255, 204, 51));
		panel_5_1.setBounds(26, 101, 20, 20);
		panel_2.add(panel_5_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Bàn đặt trước");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(246, 64, 150, 20);
		panel_2.add(lblNewLabel_1_1_2);
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setBackground(new Color(0, 51, 204));
		panel_5_2.setBounds(216, 64, 20, 20);
		panel_2.add(panel_5_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Ngừng phục vụ");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_3.setBounds(246, 101, 150, 20);
		panel_2.add(lblNewLabel_1_1_3);
		
		JPanel panel_5_3 = new JPanel();
		panel_5_3.setBackground(new Color(102, 102, 102));
		panel_5_3.setBounds(216, 101, 20, 20);
		panel_2.add(panel_5_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ngày:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(595, 10, 61, 31);
		panel_2.add(lblNewLabel_1_2);
		
        JDC_ngaychon = new JDateChooser();
        
        JDC_ngaychon.setBounds(644, 10, 224, 31);
        JDC_ngaychon.setDate(new Date());
        panel_2.add(JDC_ngaychon);
		
		comb_tang = new JComboBox<String>();
		comb_tang.addItem("Toàn bộ");
		ArrayList<Integer> viTriKhongTrung = (ArrayList<Integer>) dsb.stream()
			    .map(Ban::getViTri)              // Lấy vị trí
			    .distinct()                      // Loại bỏ trùng lặp
			    .sorted()                        // Sắp xếp tăng dần
			    .collect(Collectors.toList());   // Thu về danh sách
		for (Integer i: viTriKhongTrung) {
			comb_tang.addItem(i.toString());
		}
		comb_tang.setBounds(398, 87, 172, 34);
		panel_2.add(comb_tang);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Tầng:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(398, 61, 61, 23);
		panel_2.add(lblNewLabel_1_2_1);
		
		comb_kv = new JComboBox<String>();
		ArrayList<String> tenKhuVucKhongTrung = (ArrayList<String>) dsb.stream()
			    .map(Ban::getTenKV)               // Lấy tên khu vực
			    .filter(Objects::nonNull)         // Bỏ null nếu có
			    .distinct()                       // Loại trùng lặp
			    .sorted()                         // Sắp xếp tăng dần
			    .collect(Collectors.toList());    // Thu về danh sách
		comb_kv.addItem("Toàn bộ");
		for (String s: tenKhuVucKhongTrung) {
			comb_kv.addItem(s);
		}
		comb_kv.setBounds(595, 87, 172, 34);
		panel_2.add(comb_kv);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Khu vực:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(595, 61, 61, 23);
		panel_2.add(lblNewLabel_1_2_1_1);
		
		comb_loaiban = new JComboBox<String>();
		ArrayList<Integer> loaiBanKhongTrung = (ArrayList<Integer>) dsb.stream()
			    .map(Ban::getLoaiBan)           // Lấy giá trị loaiBan
			    .distinct()                     // Loại trùng
			    .sorted()                       // Sắp xếp tăng dần
			    .collect(Collectors.toList()); // Thu về danh sách
		comb_loaiban.addItem("Toàn bộ");
		for (Integer i: loaiBanKhongTrung) {
			comb_loaiban.addItem(i.toString());
		}
		comb_loaiban.setBounds(798, 87, 172, 34);
		panel_2.add(comb_loaiban);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Loại bàn:");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_2.setBounds(798, 61, 61, 23);
		panel_2.add(lblNewLabel_1_2_1_2);
		
		comb_tinhtrang = new JComboBox<String>();
		comb_tinhtrang.addItem("Toàn bộ");
		comb_tinhtrang.addItem("Trống");
		comb_tinhtrang.addItem("Đang sử dụng");
		comb_tinhtrang.addItem("Đặt trước");
		comb_tinhtrang.addItem("Ngừng phục vụ");
		comb_tinhtrang.setBounds(1000, 90, 172, 34);
		panel_2.add(comb_tinhtrang);
		
		JLabel lblNewLabel_1_2_1_3 = new JLabel("Tình trạng:");
		lblNewLabel_1_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_3.setBounds(1000, 64, 82, 23);
		panel_2.add(lblNewLabel_1_2_1_3);
		
		JButton btn_dateReset = new JButton("Về hiện tại");
		btn_dateReset.setBackground(new Color(0, 0, 0));
		btn_dateReset.setForeground(new Color(255, 255, 255));
		btn_dateReset.setBounds(890, 11, 106, 29);
		btn_dateReset.addActionListener(this);
		panel_2.add(btn_dateReset);
		
		JButton btn_tim = new JButton("TÌM");
		btn_tim.setForeground(new Color(255, 255, 255));
		btn_tim.setBackground(new Color(255, 153, 0));
		btn_tim.setBounds(363, 10, 91, 31);
		btn_tim.addActionListener(this);
		panel_2.add(btn_tim);
		
		JButton btn_tim_1 = new JButton("KHÔI PHỤC");
		btn_tim_1.setForeground(Color.WHITE);
		btn_tim_1.setBackground(new Color(0, 0, 0));
		btn_tim_1.setBounds(466, 10, 104, 31);
		panel_2.add(btn_tim_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(843, 189, 368, 484);
		panel_trangchu.add(panel_3);
		panel_3.setLayout(null);
		
		btn_datban = new JButton("ĐẶT BÀN");
		btn_datban.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_datban.setBackground(new Color(255, 153, 0));
		btn_datban.setForeground(new Color(255, 255, 255));
		btn_datban.setBounds(197, 410, 145, 38);
		panel_3.add(btn_datban);
		
		btn_chuyen = new JButton("CHUYỂN BÀN");
		btn_chuyen.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_chuyen.setForeground(new Color(255, 255, 255));
		btn_chuyen.setBackground(new Color(0, 0, 0));
		btn_chuyen.setBounds(27, 364, 145, 38);
		panel_3.add(btn_chuyen);
		
		btn_huy = new JButton("HỦY ĐẶT BÀN");
		btn_huy.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_huy.setForeground(new Color(255, 255, 255));
		btn_huy.setBackground(new Color(255, 0, 0));
		btn_huy.setBounds(197, 362, 145, 38);
		panel_3.add(btn_huy);
		
		btn_themmon = new JButton("THÊM MÓN");
		btn_themmon.setForeground(Color.WHITE);
		btn_themmon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_themmon.setBackground(new Color(255, 153, 0));
		btn_themmon.setBounds(27, 410, 145, 38);
		btn_themmon.addActionListener(e -> {
			String maBan = lb_ma.getText();
		    // Tạo và hiển thị form GoiMon_GUI
		    GoiMon_GUI goiMonGUI = new GoiMon_GUI();
		    
		    // Hoặc thiết lập tất cả các bàn nếu GoiMon_GUI hỗ trợ
		    goiMonGUI.setSelectedBan(maBan);
		    goiMonGUI.setVisible(true);
		    this.dispose();
		});
		panel_3.add(btn_themmon);
		
		JLabel lblNewLabel_2 = new JLabel("Mã bàn:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(33, 50, 70, 27);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Loại bàn:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(33, 84, 119, 27);
		panel_3.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Vị trí:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(33, 121, 119, 27);
		panel_3.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_4 = new JLabel("Khu vực:");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_4.setBounds(33, 158, 119, 27);
		panel_3.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Trạng thái:");
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_5.setBounds(33, 195, 119, 27);
		panel_3.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_3 = new JLabel("THÔNG TIN BÀN");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(33, 10, 306, 30);
		panel_3.add(lblNewLabel_3);
		
		lb_ma = new JLabel("");
		lb_ma.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_ma.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_ma.setBounds(176, 50, 166, 27);
		panel_3.add(lb_ma);
		
		lb_loai = new JLabel("");
		lb_loai.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_loai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_loai.setBounds(197, 84, 145, 27);
		panel_3.add(lb_loai);
		
		lb_vitri = new JLabel("");
		lb_vitri.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_vitri.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_vitri.setBounds(197, 121, 145, 27);
		panel_3.add(lb_vitri);
		
		lb_khuvuc = new JLabel("");
		lb_khuvuc.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_khuvuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_khuvuc.setBounds(197, 158, 145, 27);
		panel_3.add(lb_khuvuc);
		
		lb_trangthai = new JLabel("");
		lb_trangthai.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_trangthai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_trangthai.setBounds(197, 195, 145, 27);
		panel_3.add(lb_trangthai);
		btn_chuyen.setEnabled(false);
		btn_chuyen.addActionListener(this);
		btn_huy.setEnabled(false);
		btn_huy.addActionListener(this);
		btn_datban.setEnabled(false);
		btn_datban.addActionListener(this);
		btn_themmon.setEnabled(false);
		
		combMode = new JComboBox<String>();
		combMode.addItem("Đơn");
		combMode.addItem("Nhiều");
		combMode.setBounds(197, 304, 145, 38);
		combMode.addActionListener(this);
		panel_3.add(combMode);
		
		JLabel lblNewLabel_4 = new JLabel("Chế độ chọn:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(27, 311, 120, 20);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2_5_1 = new JLabel("Thông tin khách đặt:");
		lblNewLabel_2_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_5_1.setBounds(33, 232, 267, 27);
		panel_3.add(lblNewLabel_2_5_1);
		
		lb_tenkh = new JLabel("");
		lb_tenkh.setHorizontalAlignment(SwingConstants.LEFT);
		lb_tenkh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_tenkh.setBounds(33, 269, 166, 27);
		panel_3.add(lb_tenkh);
		
		lb_sdtkh = new JLabel("");
		lb_sdtkh.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_sdtkh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lb_sdtkh.setBounds(209, 269, 133, 27);
		panel_3.add(lb_sdtkh);
		btn_themmon.addActionListener(this);
		hiddenDateChange.setVisible(false);
		hiddenDateChange.addActionListener(this);
		loadBan();
//		for (Ban x: dsb) {
//			System.out.println(x.getMaBan());
//			System.out.println(x.getTinhTrang());
//		}
		comb_kv.addActionListener(this);
		comb_loaiban.addActionListener(this);
		comb_tang.addActionListener(this);
		comb_tinhtrang.addActionListener(this);
		JDC_ngaychon.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = JDC_ngaychon.getDate();
                if (selectedDate != null) {
                	hiddenDateChange.doClick();
                }
            }

//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				// TODO Auto-generated method stub
//				
//			}
        });
		hiddenButton.addActionListener(this);
	}
	
	private static int getPriority(int trangThai) {
	    switch (trangThai) {
	        case 1: return 0;
	        case 2: return 1;
	        case 3: return 2;
	        case 0: return 3;
	        default: return 4; // nếu có giá trị khác
	    }
	}
	
	public void loadBan() {
		panel_dsBan.removeAll(); // Xóa tất cả các component trong panel
		panel_dsBan.revalidate(); // Cập nhật lại layout
		panel_dsBan.repaint(); // Vẽ lại giao diện
		dsb = Ban_DAO.getAllBan(JDC_ngaychon.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//		System.out.println(JDC_ngaychon.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		dsb.sort(
			    Comparator.comparingInt((Ban b) -> getPriority(b.getTinhTrang()))
			              .thenComparing(Ban::getLoaiBan)
			);
		System.out.println(dsb.size());
		dskv = KhuVuc_DAO.getAllKhuVuc();
		for (Ban x: dsb) {
			String kv = x.getMaBan()+" "+ x.getTenKV();
			JButton BanMoi = new JButton(kv);
			BanMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
			BanMoi.setIcon(new ImageIcon("src\\images\\Ban\\"+x.getHinh()+".png"));
			BanMoi.setHorizontalTextPosition(SwingConstants.CENTER);
			BanMoi.setVerticalTextPosition(SwingConstants.BOTTOM);
			BanMoi.setOpaque(false);
			BanMoi.setContentAreaFilled(false);
			BanMoi.setBorderPainted(false);
			BanMoi.setPreferredSize(new Dimension(180, 180));
			BanMoi.addActionListener(this);
			panel_dsBan.add(BanMoi);
		}
		int y_size = (int) Math.ceil(dsb.size()/4);
		panel_dsBan.setPreferredSize(new Dimension(620, 180*y_size));
	}
	public void loadBanWithCb() {
		panel_dsBan.removeAll(); // Xóa tất cả các component trong panel
		panel_dsBan.revalidate(); // Cập nhật lại layout
		panel_dsBan.repaint(); // Vẽ lại giao diện
		dsb = Ban_DAO.getAllBan(JDC_ngaychon.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		dsb.sort(
			    Comparator.comparingInt((Ban b) -> getPriority(b.getTinhTrang()))
			              .thenComparing(Ban::getLoaiBan)
			);

		dskv = KhuVuc_DAO.getAllKhuVuc();
		int cnt=0;
		for (Ban x: dsb) {
			if (x.getTinhTrang()==1) {
				cnt+=1;
				String kv = x.getMaBan()+" "+ x.getTenKV();
//				for (KhuVuc k: dskv) {
//					if (k.getMaKV().equals(x.getMaKV())) {
//						kv = x.getMaBan()+" "+ k.getTenKV();
//						break;
//					}
//				}
				JButton BanMoi = new JButton(kv);
				BanMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				BanMoi.setIcon(new ImageIcon("src\\images\\Ban\\"+x.getHinh()+".png"));
				BanMoi.setHorizontalTextPosition(SwingConstants.CENTER);
				BanMoi.setVerticalTextPosition(SwingConstants.BOTTOM);
				BanMoi.setOpaque(false);
				BanMoi.setContentAreaFilled(false);
				BanMoi.setBorderPainted(false);
				BanMoi.setPreferredSize(new Dimension(180, 180));
				BanMoi.addActionListener(this);
				JCheckBox cb = new JCheckBox();
				cb.addActionListener(this);
				cb.setActionCommand(kv);
				if (x.getTinhTrang()==0) cb.setEnabled(false);
				BanMoi.add(cb);
				lcb.add(cb);
				panel_dsBan.add(BanMoi);
			}
		}
		int y_size = (int) Math.ceil(cnt/4);
		panel_dsBan.setPreferredSize(new Dimension(620, 180*y_size));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		String maban = cmd.substring(0, 4);
		Boolean isBan = false;
		Ban banchon = null;
		for (Ban k: dsb) {
			if (k.getMaBan().equals(maban)) {
				isBan=true;
				banchon = k;
				break;
			}
		}
		if (isBan && ((String) combMode.getSelectedItem()).equals("Đơn")) {
			lb_ma.setText(banchon.getMaBan());
			lb_vitri.setText("Lầu "+banchon.getViTri());
			lb_loai.setText(banchon.getLoaiBan()+" người");
			lb_khuvuc.setText(banchon.getTenKV());
			String tt = "";
			switch (banchon.getTinhTrang()) {
			case 0: {
				tt = "Ngưng phục vụ";
				btn_chuyen.setEnabled(false);
				btn_huy.setEnabled(false);
				btn_datban.setEnabled(false);
				btn_themmon.setEnabled(false);
				lb_tenkh.setText("");
				lb_sdtkh.setText("");
				break;
			}
			case 2:{
				tt = "Đang phục vụ";
				btn_chuyen.setEnabled(true);
				btn_huy.setEnabled(false);
				btn_datban.setEnabled(false);
				btn_themmon.setEnabled(true);
				KhachHang kh = KhachHang_DAO.getKhachHangMoiNhatCuaBan(banchon.getMaBan());
				lb_tenkh.setText(kh.getTenKH());
				lb_sdtkh.setText(kh.getSoDienThoai());
				break;
			}
			case 3:{
				tt = "Đặt trước";
				btn_chuyen.setEnabled(true);
				btn_huy.setEnabled(true);
				btn_datban.setEnabled(false);
				btn_themmon.setEnabled(true);
				KhachHang kh = KhachHang_DAO.getKhachHangMoiNhatCuaBan(banchon.getMaBan());
				lb_sdtkh.setText(kh.getSoDienThoai());
				lb_tenkh.setText(kh.getTenKH());
				break;
			}
			default:
				tt = "Trống";
				btn_chuyen.setEnabled(false);
				btn_huy.setEnabled(false);
				btn_datban.setEnabled(true);
				btn_themmon.setEnabled(false);
				lb_tenkh.setText("");
				lb_sdtkh.setText("");
			}
			lb_trangthai.setText(tt);
		}
		else if (isBan && !((String) combMode.getSelectedItem()).equals("Đơn")) {
			btn_chuyen.setEnabled(false);
			btn_huy.setEnabled(false);
			btn_datban.setEnabled(true);
			btn_themmon.setEnabled(false);
		}
		if (cmd.equals("ĐẶT BÀN")) {
			try {
				DatBanChiTiet_GUI dialog = new DatBanChiTiet_GUI();
				Date selectedDate = JDC_ngaychon.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = sdf.format(selectedDate);
                ArrayList<Ban> dsBanChon = new ArrayList<>(); 
                if (combMode.getSelectedIndex()==0 && !lb_ma.getText().equals("")) { 	
                	dsBanChon.add(getBanTheoMa(lb_ma.getText()));             		               	
                }
                if (combMode.getSelectedIndex()==1) {
                	for (JCheckBox x: lcb) {
                		if (x.isSelected()==true) {
//                			System.out.println(x.getActionCommand());
                			dsBanChon.add(getBanTheoMa(x.getActionCommand().substring(0, 4)));
                		}
                	}
                }
				dialog.setDate(dateString);
				dialog.setDsBan(dsBanChon);

				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
		if (cmd.equals("XÁC NHẬN")) {
			System.out.println("Hihi");
		}
		if (cmd.equals("comboBoxChanged")) {
			if (((String) combMode.getSelectedItem()).equals("Đơn")) loadBan();
			else loadBanWithCb();
		}
		if (cmd.equals("Xác nhận đặt bàn thành công")) {
			combMode.setSelectedIndex(0);
			loadBan();
		}
		if (cmd.equals("Date changed")) {
			if (combMode.getSelectedIndex()==0) loadBan();
			else loadBanWithCb();
		}

		
	}
	public Ban getBanTheoMa(String ma) {
		for (Ban x: dsb) {
			if (x.getMaBan().equals(ma)) return x;
		}
		return null;
	}
}
