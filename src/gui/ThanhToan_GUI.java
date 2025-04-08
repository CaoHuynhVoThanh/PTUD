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
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

public class ThanhToan_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTable table_1;

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
		
		textField = new JTextField();
		textField.setBounds(38, 39, 204, 27);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(38, 20, 131, 13);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số bàn có trong đơn");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(38, 76, 177, 13);
		panel_2.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(38, 95, 204, 27);
		panel_2.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(287, 39, 204, 27);
		panel_2.add(textField_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Số điện thoại");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(287, 20, 131, 13);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Vị trí");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(287, 76, 131, 13);
		panel_2.add(lblNewLabel_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(287, 95, 204, 27);
		panel_2.add(textField_3);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 10, 639, 381);
		panel_3.add(scrollPane);
		
		JPanel panel_5 = new JPanel();
		scrollPane.setViewportView(panel_5);
		panel_5.setPreferredSize(new Dimension(620, 999));
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setPreferredSize(new Dimension(620, 100));
		panel_6_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_1.setBackground(Color.WHITE);
		panel_5.add(panel_6_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\tabbleddb.png"));
		lblNewLabel_2_1.setBounds(30, 10, 110, 80);
		panel_6_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Số điện thoại: 098765321");
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_1_1.setBounds(165, 62, 196, 20);
		panel_6_1.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Tên khách: Lê Vinh Quang");
		lblNewLabel_4_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_2.setBounds(165, 44, 196, 20);
		panel_6_1.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("HD213411233");
		lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_3_1.setBounds(165, 20, 149, 20);
		panel_6_1.add(lblNewLabel_3_1);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox();
		chckbxNewCheckBox_1.setPreferredSize(new Dimension(200, 0));
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxNewCheckBox_1.setBackground(Color.WHITE);
		chckbxNewCheckBox_1.setBounds(567, 30, 22, 34);
		panel_6_1.add(chckbxNewCheckBox_1);
		
		JPanel panel_6_2 = new JPanel();
		panel_6_2.setLayout(null);
		panel_6_2.setPreferredSize(new Dimension(620, 100));
		panel_6_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_2.setBackground(Color.WHITE);
		panel_5.add(panel_6_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\tabbleddb.png"));
		lblNewLabel_2_2.setBounds(30, 10, 110, 80);
		panel_6_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_4_1_2 = new JLabel("Số điện thoại: 098765321");
		lblNewLabel_4_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_1_2.setBounds(165, 62, 196, 20);
		panel_6_2.add(lblNewLabel_4_1_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("Tên khách: Lê Vinh Quang");
		lblNewLabel_4_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_3.setBounds(165, 44, 196, 20);
		panel_6_2.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_3_2 = new JLabel("HD213411233");
		lblNewLabel_3_2.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_3_2.setBounds(165, 20, 149, 20);
		panel_6_2.add(lblNewLabel_3_2);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox();
		chckbxNewCheckBox_2.setPreferredSize(new Dimension(200, 100));
		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxNewCheckBox_2.setBackground(Color.WHITE);
		chckbxNewCheckBox_2.setBounds(567, 30, 22, 34);
		panel_6_2.add(chckbxNewCheckBox_2);
		
		JPanel panel_6_4 = new JPanel();
		panel_6_4.setLayout(null);
		panel_6_4.setPreferredSize(new Dimension(620, 100));
		panel_6_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_4.setBackground(Color.WHITE);
		panel_5.add(panel_6_4);
		
		JLabel lblNewLabel_2_4 = new JLabel("");
		lblNewLabel_2_4.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\tabbleddb.png"));
		lblNewLabel_2_4.setBounds(30, 10, 110, 80);
		panel_6_4.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_4_1_4 = new JLabel("Số điện thoại: 098765321");
		lblNewLabel_4_1_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_1_4.setBounds(165, 62, 196, 20);
		panel_6_4.add(lblNewLabel_4_1_4);
		
		JLabel lblNewLabel_4_5 = new JLabel("Tên khách: Lê Vinh Quang");
		lblNewLabel_4_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_5.setBounds(165, 44, 196, 20);
		panel_6_4.add(lblNewLabel_4_5);
		
		JLabel lblNewLabel_3_4 = new JLabel("HD213411233");
		lblNewLabel_3_4.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_3_4.setBounds(165, 20, 149, 20);
		panel_6_4.add(lblNewLabel_3_4);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox();
		chckbxNewCheckBox_4.setPreferredSize(new Dimension(200, 100));
		chckbxNewCheckBox_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxNewCheckBox_4.setBackground(Color.WHITE);
		chckbxNewCheckBox_4.setBounds(567, 30, 22, 34);
		panel_6_4.add(chckbxNewCheckBox_4);
		
		JPanel panel_6_3 = new JPanel();
		panel_6_3.setLayout(null);
		panel_6_3.setPreferredSize(new Dimension(620, 100));
		panel_6_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6_3.setBackground(Color.WHITE);
		panel_5.add(panel_6_3);
		
		JLabel lblNewLabel_2_3 = new JLabel("");
		lblNewLabel_2_3.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\App\\tabbleddb.png"));
		lblNewLabel_2_3.setBounds(30, 10, 110, 80);
		panel_6_3.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_4_1_3 = new JLabel("Số điện thoại: 098765321");
		lblNewLabel_4_1_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_1_3.setBounds(165, 62, 196, 20);
		panel_6_3.add(lblNewLabel_4_1_3);
		
		JLabel lblNewLabel_4_4 = new JLabel("Tên khách: Lê Vinh Quang");
		lblNewLabel_4_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4_4.setBounds(165, 44, 196, 20);
		panel_6_3.add(lblNewLabel_4_4);
		
		JLabel lblNewLabel_3_3 = new JLabel("HD213411233");
		lblNewLabel_3_3.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_3_3.setBounds(165, 20, 149, 20);
		panel_6_3.add(lblNewLabel_3_3);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox();
		chckbxNewCheckBox_3.setPreferredSize(new Dimension(200, 100));
		chckbxNewCheckBox_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxNewCheckBox_3.setBackground(Color.WHITE);
		chckbxNewCheckBox_3.setBounds(567, 30, 22, 34);
		panel_6_3.add(chckbxNewCheckBox_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setPreferredSize(new Dimension(620, 100));
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\tabbleddb.png"));
		lblNewLabel_2.setBounds(30, 10, 110, 80);
		panel_6.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4_1 = new JLabel("Số điện thoại: 098765321");
		lblNewLabel_4_1.setBounds(165, 62, 196, 20);
		panel_6.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblNewLabel_4 = new JLabel("Tên khách: Lê Vinh Quang");
		lblNewLabel_4.setBounds(165, 44, 196, 20);
		panel_6.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("HD213411233");
		lblNewLabel_3.setBounds(165, 20, 149, 20);
		panel_6.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 22));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox();
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox.setBounds(567, 30, 22, 34);
		chckbxNewCheckBox.setPreferredSize(new Dimension(200, 100));
		panel_6.add(chckbxNewCheckBox);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(705, 22, 525, 656);
		panel_trangchu.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane_1.setBounds(21, 46, 483, 155);
		panel_4.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 b\u00E0n", "Lo\u1EA1i", "V\u1ECB tr\u00ED", "\u0110\u00E3 c\u1ECDc", "Ph\u1EE5 thu", "Trang tr\u00ED", ""
			}
		));
		scrollPane_1.setViewportView(table);
		
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
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(21, 281, 483, 217);
		panel_4.add(scrollPane_1_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"T\u00EAn m\u00F3n", "Lo\u1EA1i", "\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
			}
		));
		scrollPane_1_1.setViewportView(table_1);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Tổng gọi món:");
		lblNewLabel_5_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_5_1_1_1.setBounds(21, 508, 115, 26);
		panel_4.add(lblNewLabel_5_1_1_1);
		
		JButton btnNewButton_2 = new JButton("THANH TOÁN");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_2.setBackground(new Color(255, 153, 0));
		btnNewButton_2.setBounds(319, 588, 185, 41);
		panel_4.add(btnNewButton_2);
		
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
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new TitledBorder(null, "Ghi ch\u00FA \u0111\u1EB7t m\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBounds(32, 589, 658, 89);
		panel_trangchu.add(panel_3_1);
	}
}
