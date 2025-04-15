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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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

import dao.Ban_DAO;
import dao.ChiTietDonDatBan_DAO;
import dao.ChiTietDonGoiMon_DAO;
import dao.DonDatBan_DAO;
import dao.DonGoiMon_DAO;
import dao.KhachHang_DAO;
import dao.Mon_DAO;
import entities.Ban;
import entities.ChiTietDonDatBan;
import entities.ChiTietDonGoiMon;
import entities.DonDatBan;
import entities.DonGoiMon;
import entities.KhachHang;
import entities.Mon;
import entities.NhanVien;
import entities.UniqueArrayList;

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
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class ThanhToan_GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_tenkh;
	private JTextField tf_soban;
	private JTextField tf_sdt;
	private JTextField tf_vt;
	private JTable scrollBan;
	private JTable tbmon;
	JScrollPane tb_ban;
	JLabel lb_tongdatban, lb_tonggoimon, lb_tamTinh;
	private JPanel panel_dsban;
	private ArrayList<DonDatBan> dsddb = new ArrayList<>();
	private ArrayList<KhachHang> dskh = new ArrayList<>();
	private ArrayList<String> danhSachBanDuocChon = new ArrayList<>();
	ArrayList<DonGoiMon> dgm;
	JScrollPane scrollmon;
	JButton btn_thanhtoan;
	JTextArea ta_ghichu;
	ArrayList<Mon> mon = new ArrayList<>();
	private UniqueArrayList listcb = new UniqueArrayList();
	private JScrollPane scrollPane;
	private JLabel lb_tongdatban_1;
	private JLabel lb_tonggoimon_1;
	public static JButton hiddenButtonThanhToan = new JButton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThanhToan_GUI frame = new ThanhToan_GUI();
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
	public ThanhToan_GUI() {
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
		logo.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\logo.png"));
		logo.setBounds(66, 22, 247, 89);
		panel_1.add(logo);
		
		JLabel avt = new JLabel("");
		ImageIcon originalIcon = new ImageIcon("D:\\\\demoGit\\\\PTUD\\\\src\\\\images\\\\App\\\\avt.png");
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "B\u1ED9 l\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(31, 20, 659, 142);
		panel_trangchu.add(panel_2);
		panel_2.setLayout(null);
		
		tf_tenkh = new JTextField();
		tf_tenkh.setBounds(38, 39, 204, 27);
		panel_2.add(tf_tenkh);
		tf_tenkh.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(38, 20, 131, 13);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã số của bàn:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(38, 76, 177, 13);
		panel_2.add(lblNewLabel_1_1);
		
		tf_soban = new JTextField();
		tf_soban.setColumns(10);
		tf_soban.setBounds(38, 95, 204, 27);
		panel_2.add(tf_soban);
		
		tf_sdt = new JTextField();
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(287, 39, 204, 27);
		panel_2.add(tf_sdt);
		
		JLabel lblNewLabel_1_2 = new JLabel("Số điện thoại");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(287, 20, 131, 13);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Vị trí");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(287, 76, 131, 13);
		panel_2.add(lblNewLabel_1_1_1);
		
		tf_vt = new JTextField();
		tf_vt.setColumns(10);
		tf_vt.setBounds(287, 95, 204, 27);
		panel_2.add(tf_vt);
		
		JButton btnNewButton_1 = new JButton("Tìm");
		btnNewButton_1.setBackground(new Color(255, 153, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(522, 36, 100, 33);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Khôi phục");
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1_1.setBounds(522, 89, 100, 33);
		panel_2.add(btnNewButton_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(31, 172, 659, 401);
		panel_trangchu.add(panel_3);
		panel_3.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 10, 639, 381);
		panel_3.add(scrollPane);
		
		panel_dsban = new JPanel();
		scrollPane.setViewportView(panel_dsban);
		panel_dsban.setPreferredSize(new Dimension(620, 0));
		panel_dsban.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
//		
//		
//		JPanel panel_6_2 = new JPanel();
//		panel_6_2.setLayout(null);
//		panel_6_2.setPreferredSize(new Dimension(620, 100));
//		panel_6_2.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_6_2.setBackground(Color.WHITE);
//		panel_dsban.add(panel_6_2);
//		
//		JLabel lblNewLabel_2_2 = new JLabel("");
//		lblNewLabel_2_2.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\tabbleddb.png"));
//		lblNewLabel_2_2.setBounds(30, 10, 110, 80);
//		panel_6_2.add(lblNewLabel_2_2);
//		
//		JLabel lblNewLabel_4_1_2 = new JLabel("Số điện thoại: 098765321");
//		lblNewLabel_4_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
//		lblNewLabel_4_1_2.setBounds(165, 62, 196, 20);
//		panel_6_2.add(lblNewLabel_4_1_2);
//		
//		JLabel lblNewLabel_4_3 = new JLabel("Tên khách: Lê Vinh Quang");
//		lblNewLabel_4_3.setFont(new Font("Arial", Font.PLAIN, 14));
//		lblNewLabel_4_3.setBounds(165, 44, 196, 20);
//		panel_6_2.add(lblNewLabel_4_3);
//		
//		JLabel lblNewLabel_3_2 = new JLabel("HD213411233");
//		lblNewLabel_3_2.setFont(new Font("Arial", Font.BOLD, 22));
//		lblNewLabel_3_2.setBounds(165, 20, 149, 20);
//		panel_6_2.add(lblNewLabel_3_2);
//		
//		JCheckBox chckbxNewCheckBox_2 = new JCheckBox();
//		chckbxNewCheckBox_2.setPreferredSize(new Dimension(200, 100));
//		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
//		chckbxNewCheckBox_2.setBackground(Color.WHITE);
//		chckbxNewCheckBox_2.setBounds(567, 30, 22, 34);
//		panel_6_2.add(chckbxNewCheckBox_2);
//		
//		JPanel panel_6_4 = new JPanel();
//		panel_6_4.setLayout(null);
//		panel_6_4.setPreferredSize(new Dimension(620, 100));
//		panel_6_4.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_6_4.setBackground(Color.WHITE);
//		panel_dsban.add(panel_6_4);
//		
//		JLabel lblNewLabel_2_4 = new JLabel("");
//		lblNewLabel_2_4.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\tabbleddb.png"));
//		lblNewLabel_2_4.setBounds(30, 10, 110, 80);
//		panel_6_4.add(lblNewLabel_2_4);
//		
//		JLabel lblNewLabel_4_1_4 = new JLabel("Số điện thoại: 098765321");
//		lblNewLabel_4_1_4.setFont(new Font("Arial", Font.PLAIN, 14));
//		lblNewLabel_4_1_4.setBounds(165, 62, 196, 20);
//		panel_6_4.add(lblNewLabel_4_1_4);
//		
//		JLabel lblNewLabel_4_5 = new JLabel("Tên khách: Lê Vinh Quang");
//		lblNewLabel_4_5.setFont(new Font("Arial", Font.PLAIN, 14));
//		lblNewLabel_4_5.setBounds(165, 44, 196, 20);
//		panel_6_4.add(lblNewLabel_4_5);
//		
//		JLabel lblNewLabel_3_4 = new JLabel("HD213411233");
//		lblNewLabel_3_4.setFont(new Font("Arial", Font.BOLD, 22));
//		lblNewLabel_3_4.setBounds(165, 20, 149, 20);
//		panel_6_4.add(lblNewLabel_3_4);
//		
//		JCheckBox chckbxNewCheckBox_4 = new JCheckBox();
//		chckbxNewCheckBox_4.setPreferredSize(new Dimension(200, 100));
//		chckbxNewCheckBox_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
//		chckbxNewCheckBox_4.setBackground(Color.WHITE);
//		chckbxNewCheckBox_4.setBounds(567, 30, 22, 34);
//		panel_6_4.add(chckbxNewCheckBox_4);
//		
//		JPanel panel_6_3 = new JPanel();
//		panel_6_3.setLayout(null);
//		panel_6_3.setPreferredSize(new Dimension(620, 100));
//		panel_6_3.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_6_3.setBackground(Color.WHITE);
//		panel_dsban.add(panel_6_3);
//		
//		JLabel lblNewLabel_2_3 = new JLabel("");
//		lblNewLabel_2_3.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\tabbleddb.png"));
//		lblNewLabel_2_3.setBounds(30, 10, 110, 80);
//		panel_6_3.add(lblNewLabel_2_3);
//		
//		JLabel lblNewLabel_4_1_3 = new JLabel("Số điện thoại: 098765321");
//		lblNewLabel_4_1_3.setFont(new Font("Arial", Font.PLAIN, 14));
//		lblNewLabel_4_1_3.setBounds(165, 62, 196, 20);
//		panel_6_3.add(lblNewLabel_4_1_3);
//		
//		JLabel lblNewLabel_4_4 = new JLabel("Tên khách: Lê Vinh Quang");
//		lblNewLabel_4_4.setFont(new Font("Arial", Font.PLAIN, 14));
//		lblNewLabel_4_4.setBounds(165, 44, 196, 20);
//		panel_6_3.add(lblNewLabel_4_4);
//		
//		JLabel lblNewLabel_3_3 = new JLabel("HD213411233");
//		lblNewLabel_3_3.setFont(new Font("Arial", Font.BOLD, 22));
//		lblNewLabel_3_3.setBounds(165, 20, 149, 20);
//		panel_6_3.add(lblNewLabel_3_3);
//		
//		JCheckBox chckbxNewCheckBox_3 = new JCheckBox();
//		chckbxNewCheckBox_3.setPreferredSize(new Dimension(200, 100));
//		chckbxNewCheckBox_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
//		chckbxNewCheckBox_3.setBackground(Color.WHITE);
//		chckbxNewCheckBox_3.setBounds(567, 30, 22, 34);
//		panel_6_3.add(chckbxNewCheckBox_3);
//		
//		JPanel panel_6 = new JPanel();
//		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_6.setBackground(new Color(255, 255, 255));
//		panel_6.setPreferredSize(new Dimension(620, 100));
//		panel_dsban.add(panel_6);
//		panel_6.setLayout(null);
//		
//		JLabel lblNewLabel_2 = new JLabel("");
//		lblNewLabel_2.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\tabbleddb.png"));
//		lblNewLabel_2.setBounds(30, 10, 110, 80);
//		panel_6.add(lblNewLabel_2);
//		
//		JLabel lblNewLabel_4_1 = new JLabel("Số điện thoại: 098765321");
//		lblNewLabel_4_1.setBounds(165, 62, 196, 20);
//		panel_6.add(lblNewLabel_4_1);
//		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 14));
//		
//		JLabel lblNewLabel_4 = new JLabel("Tên khách: Lê Vinh Quang");
//		lblNewLabel_4.setBounds(165, 44, 196, 20);
//		panel_6.add(lblNewLabel_4);
//		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
//		
//		JLabel lblNewLabel_3 = new JLabel("HD213411233");
//		lblNewLabel_3.setBounds(165, 20, 149, 20);
//		panel_6.add(lblNewLabel_3);
//		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 22));
//		
//		JCheckBox chckbxNewCheckBox = new JCheckBox();
//		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
//		chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
//		chckbxNewCheckBox.setBounds(567, 30, 22, 34);
//		chckbxNewCheckBox.setPreferredSize(new Dimension(200, 100));
//		panel_6.add(chckbxNewCheckBox);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(705, 22, 525, 656);
		panel_trangchu.add(panel_4);
		panel_4.setLayout(null);
		
		tb_ban = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		tb_ban.setBounds(21, 46, 483, 155);
		panel_4.add(tb_ban);
		
		scrollBan = new JTable();
		DefaultTableModel tableModel0 = new DefaultTableModel();
        tableModel0.addColumn("Mã bàn");
        tableModel0.addColumn("Loại bàn");
        tableModel0.addColumn("Vị trí");
        tableModel0.addColumn("Phụ thu");
        tableModel0.addColumn("");
		scrollBan.setModel(tableModel0);
		tb_ban.setViewportView(scrollBan);
		
		JLabel lblNewLabel_5 = new JLabel("Thông tin đặt bàn");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(167, 10, 190, 26);
		panel_4.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Thông tin gọi món");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_5_1.setBounds(167, 245, 190, 26);
		panel_4.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Tổng đặt bàn:");
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_5_1_1.setBounds(31, 211, 115, 26);
		panel_4.add(lblNewLabel_5_1_1);
		
		scrollmon = new JScrollPane();
		scrollmon.setBounds(21, 281, 483, 217);
		panel_4.add(scrollmon);
		
		tbmon = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Tên món");
        tableModel.addColumn("Loại");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Trả trước");
        tableModel.addColumn("Thành tiền");
		tbmon.setModel(tableModel);
		scrollmon.setViewportView(tbmon);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Tổng gọi món:");
		lblNewLabel_5_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_5_1_1_1.setBounds(21, 508, 115, 26);
		panel_4.add(lblNewLabel_5_1_1_1);
		
		btn_thanhtoan = new JButton("THANH TOÁN");
		btn_thanhtoan.setForeground(new Color(255, 255, 255));
		btn_thanhtoan.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_thanhtoan.setBackground(new Color(255, 153, 0));
		btn_thanhtoan.setBounds(319, 588, 185, 41);
		btn_thanhtoan.addActionListener(this);
		panel_4.add(btn_thanhtoan);
		
		JButton btnNewButton_2_1 = new JButton("GỘP");
		btnNewButton_2_1.setForeground(new Color(255, 255, 255));
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2_1.setBackground(new Color(0, 0, 0));
		btnNewButton_2_1.setBounds(21, 588, 115, 41);
		panel_4.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("Tạm tính:");
		lblNewLabel_5_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_5_1_2.setBounds(21, 544, 78, 26);
		panel_4.add(lblNewLabel_5_1_2);
		
		JButton btnNewButton_2_1_1 = new JButton("TÁCH");
		btnNewButton_2_1_1.setForeground(Color.WHITE);
		btnNewButton_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2_1_1.setBackground(Color.BLACK);
		btnNewButton_2_1_1.setBounds(156, 588, 115, 41);
		panel_4.add(btnNewButton_2_1_1);
		
		lb_tongdatban = new JLabel("0.0");
		lb_tongdatban.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tongdatban.setFont(new Font("Arial", Font.BOLD, 12));
		lb_tongdatban.setBounds(119, 211, 115, 26);
		panel_4.add(lb_tongdatban);
		
		lb_tonggoimon = new JLabel("0.0");
		lb_tonggoimon.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tonggoimon.setFont(new Font("Arial", Font.BOLD, 12));
		lb_tonggoimon.setBounds(119, 508, 115, 26);
		panel_4.add(lb_tonggoimon);
		
		lb_tamTinh = new JLabel("0.0");
		lb_tamTinh.setForeground(new Color(255, 0, 0));
		lb_tamTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lb_tamTinh.setFont(new Font("Arial", Font.BOLD, 16));
		lb_tamTinh.setBounds(156, 544, 78, 26);
		panel_4.add(lb_tamTinh);
		
		JLabel lblNewLabel_5_1_1_2 = new JLabel("Cọc trước bàn:");
		lblNewLabel_5_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_5_1_1_2.setBounds(301, 211, 115, 26);
		panel_4.add(lblNewLabel_5_1_1_2);
		
		lb_tongdatban_1 = new JLabel("0.0");
		lb_tongdatban_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tongdatban_1.setFont(new Font("Arial", Font.BOLD, 12));
		lb_tongdatban_1.setBounds(389, 211, 115, 26);
		panel_4.add(lb_tongdatban_1);
		
		lb_tonggoimon_1 = new JLabel("0.0");
		lb_tonggoimon_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tonggoimon_1.setFont(new Font("Arial", Font.BOLD, 12));
		lb_tonggoimon_1.setBounds(389, 508, 115, 26);
		panel_4.add(lb_tonggoimon_1);
		
		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("Cọc trước món:");
		lblNewLabel_5_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_5_1_1_1_1.setBounds(291, 508, 115, 26);
		panel_4.add(lblNewLabel_5_1_1_1_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new TitledBorder(null, "Ghi ch\u00FA \u0111\u1EB7t m\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBounds(32, 589, 658, 89);
		panel_trangchu.add(panel_3_1);
		
		ta_ghichu = new JTextArea();
		ta_ghichu.setEditable(false);
		ta_ghichu.setBounds(10, 20, 638, 59);
		panel_3_1.add(ta_ghichu);
		mon = Mon_DAO.getAllMon();
		dsddb = DonDatBan_DAO.getDonDatBanTheoNgayChuaTT(LocalDate.now());
		loadDsDDB(dsddb);
	}
	public void loadDsDDB(ArrayList<DonDatBan> ds) {	
		panel_dsban.removeAll();
		scrollPane.revalidate();
		scrollPane.repaint();
		int cnt=0;
		for (DonDatBan x: ds) {
			KhachHang kh = KhachHang_DAO.getKhachHangTheoMa(x.getMaKH());
			System.out.println(x.getMaKH());
			JPanel panel_6_1 = new JPanel();
			panel_6_1.setLayout(null);
			panel_6_1.setPreferredSize(new Dimension(620, 100));
			panel_6_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_6_1.setBackground(Color.WHITE);
			panel_dsban.add(panel_6_1);
			
			JLabel lblNewLabel_2_1 = new JLabel("");
			lblNewLabel_2_1.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\tabbleddb.png"));
			lblNewLabel_2_1.setBounds(30, 10, 110, 80);
			panel_6_1.add(lblNewLabel_2_1);
			
			JLabel lblNewLabel_4_1_1 = new JLabel("Số điện thoại: "+kh.getSoDienThoai());
			lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
			lblNewLabel_4_1_1.setBounds(165, 62, 196, 20);
			panel_6_1.add(lblNewLabel_4_1_1);
			
			JLabel lblNewLabel_4_2 = new JLabel("Tên khách: "+kh.getTenKH());
			lblNewLabel_4_2.setFont(new Font("Arial", Font.PLAIN, 14));
			lblNewLabel_4_2.setBounds(165, 44, 196, 20);
			panel_6_1.add(lblNewLabel_4_2);
			
			JLabel lblNewLabel_3_1 = new JLabel(" "+x.getMaDDB());
			lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 22));
			lblNewLabel_3_1.setBounds(160, 20, 190, 20);
			panel_6_1.add(lblNewLabel_3_1);
			
			JCheckBox chckbxNewCheckBox_1 = new JCheckBox();
			chckbxNewCheckBox_1.addActionListener(this);
			chckbxNewCheckBox_1.setActionCommand("cb"+x.getMaDDB());
			chckbxNewCheckBox_1.setPreferredSize(new Dimension(200, 0));
			chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
			chckbxNewCheckBox_1.setBackground(Color.WHITE);
			chckbxNewCheckBox_1.setBounds(567, 30, 22, 34);
			panel_6_1.add(chckbxNewCheckBox_1);
			hiddenButtonThanhToan.setVisible(false);
			hiddenButtonThanhToan.addActionListener(this);
			hiddenButtonThanhToan.setActionCommand("Update DDB");
			cnt+=1;
		}
		int y_size=100*cnt;
		panel_dsban.setPreferredSize(new Dimension(620, y_size));
	}
	public void updateBan(UniqueArrayList list) {
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		        // Cột cuối (index 4) là Boolean → hiển thị thành JCheckBox
		        return columnIndex == 4 ? Boolean.class : Object.class;
		    }

		    @Override
		    public boolean isCellEditable(int row, int columnIndex) {
		        // Chỉ cho phép chỉnh checkbox
		        return columnIndex == 4;
		    }
		};

        tableModel.addColumn("Mã bàn");
        tableModel.addColumn("Loại bàn");
        tableModel.addColumn("Vị trí");
        tableModel.addColumn("Phụ thu");
        tableModel.addColumn("");

//        tableModel.addColumn("Trang trí");
        danhSachBanDuocChon = new ArrayList<>();
		for (String s: list.getList()) {
			ArrayList<Ban> dsban = Ban_DAO.getBanInfoByMaDDB(s);
			for (Ban ban: dsban) {
				danhSachBanDuocChon.add(ban.getMaBan());
//				dgm = new ArrayList<>();
//				dgm.add(DonGoiMon_DAO.getDonGoiMonTheoMaDDBVaMaBan(s, ban.getMaBan()));
				System.out.println(ban.getLoaiBan());
		        tableModel.addRow(new Object[] {ban.getMaBan(), ban.getLoaiBan()+" người", "Lầu "+ban.getViTri(), ban.getPhuPhi(), true});
			}
		}
		scrollBan.setModel(tableModel);
		double tongPhuThu = 0.0;
		double tongCoc = 0.0;
		int rowCount = tableModel.getRowCount();
		System.out.println("dong:"+rowCount);
		for (int i = 0; i < rowCount; i++) {
		    Object value = tableModel.getValueAt(i, 3); // cột thứ 4: chỉ số 3
		    if (value != null) {
		        try {
		            // Trường hợp value là Double hoặc String
		            double phuThu = Double.parseDouble(value.toString());
		            tongPhuThu += phuThu;
		        } catch (NumberFormatException e) {
		            System.err.println("Lỗi định dạng số tại dòng " + i + ": " + value);
		        }
		    }
		}
		lb_tongdatban.setText(tongPhuThu+"");
		updateMon(danhSachBanDuocChon);
		tableModel.addTableModelListener(e -> {
//			System.out.println("wtf");
			danhSachBanDuocChon = new ArrayList<>();
			for (int i = 0; i < tableModel.getRowCount(); i++) {
			    Boolean isChecked = (Boolean) tableModel.getValueAt(i, 4); // Cột checkbox
			    if (Boolean.TRUE.equals(isChecked)) {
			        String maBan = (String) tableModel.getValueAt(i, 0); // Mã bàn
			        danhSachBanDuocChon.add(maBan);
			    }
			}
			updateMon(danhSachBanDuocChon);
		});
	}
	
	public void updateMon(ArrayList<String> ds) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Tên món");
        tableModel.addColumn("Loại");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Trả trước");
        tableModel.addColumn("Thành tiền");
		if (ds.size()==0) {
			tbmon.setModel(tableModel);
		}
		Double tonggiamon=0.0;
		String ghiChu = "";
		for (String ma: ds) {
	        DonGoiMon don = DonGoiMon_DAO.getDonGoiMonMoiNhatTrongNgay(ma);
	        if (don==null) continue;
	        ghiChu+=""+don.getGhiChu();
	        ArrayList<ChiTietDonGoiMon> ctdgm = ChiTietDonGoiMon_DAO.getChiTietDonGoiMonTheoMaDGM(don.getMaDGM());
	        for (ChiTietDonGoiMon x: ctdgm) {
	        	for (Mon y: mon) {
	        		if (x.getMaMon().equals(y.getMaMon())) {
	        			tableModel.addRow(new Object[] {y.getTenMon(), y.getLoaiMon(), y.getDonGia(), x.getSoLuong(), x.getSoLuongDaThanhToan(), x.getSoLuong()*y.getDonGia()});
	        		}
	        	}
	        }
	        tbmon.setModel(tableModel);
		}
		ta_ghichu.setText(ghiChu);
		int rowCount = tableModel.getRowCount();
		System.out.println("dong:"+rowCount);
		for (int i = 0; i < rowCount; i++) {
		    Object value = tableModel.getValueAt(i, 5); // cột thứ 4: chỉ số 3
		    if (value != null) {
		        try {
		            // Trường hợp value là Double hoặc String
		            double phuThu = Double.parseDouble(value.toString());
		            tonggiamon += phuThu;
		        } catch (NumberFormatException e) {
		            System.err.println("Lỗi định dạng số tại dòng " + i + ": " + value);
		        }
		    }
		}
		lb_tonggoimon.setText(tonggiamon+"");
		Double tamtinh = Double.parseDouble(lb_tongdatban.getText())+Double.parseDouble(lb_tonggoimon.getText());
		lb_tamTinh.setText(tamtinh+"");
	}
	
	public void clearAll() {
		DefaultTableModel emptyModel = new DefaultTableModel();
		scrollBan.setModel(emptyModel);
		tb_ban.revalidate();
		tb_ban.repaint();
		
//		scrollmon.removeAll();
		tbmon.setModel(emptyModel);
		scrollmon.revalidate();
		scrollmon.repaint();
		lb_tongdatban.setText("0.0");
		lb_tongdatban_1.setText("0.0");
		lb_tonggoimon.setText("0.0");
		lb_tonggoimon_1.setText("0.0");
		ta_ghichu.removeAll();
		lb_tamTinh.setText("0.0");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		String isDDB = cmd.substring(0, 2);
		if (isDDB.equals("cb")) {
			listcb.addOrRemove(cmd.substring(2));
			listcb.printList();
			Double tongCoc=0.0;
			for (DonDatBan x: dsddb) {
				if (x.getMaDDB().equals(cmd.substring(2))) tongCoc+=x.getTienCoc();
			}
			lb_tongdatban_1.setText(""+tongCoc);
			updateBan(listcb);
		}
		if (cmd.equals("THANH TOÁN") && listcb.getList().size()!=0) {
			ThanhToanChiTiet_GUI dialog = new ThanhToanChiTiet_GUI();
			ArrayList<LocalDateTime> time = new ArrayList<>();
//			ArrayList<String> maDonChon = new ArrayList<>();
			updateBan(listcb);
			for (String s: listcb.getList()) {	
				for (DonDatBan ddb: dsddb) {
					if (ddb.getMaDDB().equals(s)) time.add(ddb.getThoiGianDat());
				}
			}
			LocalDateTime thoiGianDatBan = Collections.min(time);
			System.out.println(thoiGianDatBan);
			dialog.setTT(thoiGianDatBan, lb_tamTinh.getText());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setDsDDB(listcb.getList());
			dialog.setVisible(true);
		}
		if (cmd.equals("Update DDB")) {
			clearAll();
			dsddb = DonDatBan_DAO.getDonDatBanTheoNgayChuaTT(LocalDate.now());
			loadDsDDB(dsddb);
		}
	}
}
