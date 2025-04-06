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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JComboBox;

public class DatBan_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		
		JPanel panel_trangchu = new JPanel();
		panel_trangchu.setBackground(new Color(255, 255, 255));
		panel_trangchu.setBounds(285, 133, 1254, 704);
		contentPane.add(panel_trangchu);
		panel_trangchu.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		scrollPane.setBounds(23, 189, 796, 484);
		panel_trangchu.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(620, 999));
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban2.png"));
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		panel_4.add(btnNewButton_1);
		btnNewButton_1.setPreferredSize(new Dimension(180, 180));
		
		JButton btnNewButton_1_4 = new JButton("");
		btnNewButton_1_4.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_4.setOpaque(false);
		btnNewButton_1_4.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban2.png"));
		btnNewButton_1_4.setContentAreaFilled(false);
		btnNewButton_1_4.setBorderPainted(false);
		panel_4.add(btnNewButton_1_4);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_1.setOpaque(false);
		btnNewButton_1_1.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban2.png"));
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		panel_4.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_2.setOpaque(false);
		btnNewButton_1_2.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban2.png"));
		btnNewButton_1_2.setContentAreaFilled(false);
		btnNewButton_1_2.setBorderPainted(false);
		panel_4.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("");
		btnNewButton_1_3.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_3.setOpaque(false);
		btnNewButton_1_3.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban2.png"));
		btnNewButton_1_3.setContentAreaFilled(false);
		btnNewButton_1_3.setBorderPainted(false);
		panel_4.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_8 = new JButton("");
		btnNewButton_1_8.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_8.setOpaque(false);
		btnNewButton_1_8.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban2.png"));
		btnNewButton_1_8.setContentAreaFilled(false);
		btnNewButton_1_8.setBorderPainted(false);
		panel_4.add(btnNewButton_1_8);
		
		JButton btnNewButton_1_5 = new JButton("");
		btnNewButton_1_5.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_5.setOpaque(false);
		btnNewButton_1_5.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban2.png"));
		btnNewButton_1_5.setContentAreaFilled(false);
		btnNewButton_1_5.setBorderPainted(false);
		panel_4.add(btnNewButton_1_5);
		
		JButton btnNewButton_1_6 = new JButton("");
		btnNewButton_1_6.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_6.setOpaque(false);
		btnNewButton_1_6.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban2.png"));
		btnNewButton_1_6.setContentAreaFilled(false);
		btnNewButton_1_6.setBorderPainted(false);
		panel_4.add(btnNewButton_1_6);
		
		JButton btnNewButton_1_7 = new JButton("");
		btnNewButton_1_7.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_7.setOpaque(false);
		btnNewButton_1_7.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban4green.png"));
		btnNewButton_1_7.setContentAreaFilled(false);
		btnNewButton_1_7.setBorderPainted(false);
		panel_4.add(btnNewButton_1_7);
		
		JButton btnNewButton_1_7_1 = new JButton("");
		btnNewButton_1_7_1.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_7_1.setOpaque(false);
		btnNewButton_1_7_1.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban4green.png"));
		btnNewButton_1_7_1.setContentAreaFilled(false);
		btnNewButton_1_7_1.setBorderPainted(false);
		panel_4.add(btnNewButton_1_7_1);
		
		JButton btnNewButton_1_7_2 = new JButton("");
		btnNewButton_1_7_2.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_7_2.setOpaque(false);
		btnNewButton_1_7_2.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban4green.png"));
		btnNewButton_1_7_2.setContentAreaFilled(false);
		btnNewButton_1_7_2.setBorderPainted(false);
		panel_4.add(btnNewButton_1_7_2);
		
		JButton btnNewButton_1_7_3 = new JButton("");
		btnNewButton_1_7_3.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_7_3.setOpaque(false);
		btnNewButton_1_7_3.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban4green.png"));
		btnNewButton_1_7_3.setContentAreaFilled(false);
		btnNewButton_1_7_3.setBorderPainted(false);
		panel_4.add(btnNewButton_1_7_3);
		
		JButton btnNewButton_1_7_4 = new JButton("");
		btnNewButton_1_7_4.setPreferredSize(new Dimension(180, 180));
		btnNewButton_1_7_4.setOpaque(false);
		btnNewButton_1_7_4.setIcon(new ImageIcon("D:\\demoGit\\PTUD\\src\\images\\ban4green.png"));
		btnNewButton_1_7_4.setContentAreaFilled(false);
		btnNewButton_1_7_4.setBorderPainted(false);
		panel_4.add(btnNewButton_1_7_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 24, 1193, 144);
		panel_trangchu.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(139, 10, 203, 31);
		panel_2.add(textField);
		textField.setColumns(10);
		
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
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(422, 10, 203, 31);
		panel_2.add(textField_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ngày:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(367, 10, 61, 31);
		panel_2.add(lblNewLabel_1_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(398, 87, 172, 34);
		panel_2.add(comboBox);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Tầng:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(398, 61, 61, 23);
		panel_2.add(lblNewLabel_1_2_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(595, 87, 172, 34);
		panel_2.add(comboBox_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Khu vực:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(595, 61, 61, 23);
		panel_2.add(lblNewLabel_1_2_1_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(798, 87, 172, 34);
		panel_2.add(comboBox_2);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Loại bàn:");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_2.setBounds(798, 61, 61, 23);
		panel_2.add(lblNewLabel_1_2_1_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(1000, 90, 172, 34);
		panel_2.add(comboBox_3);
		
		JLabel lblNewLabel_1_2_1_3 = new JLabel("Tình trạng:");
		lblNewLabel_1_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_3.setBounds(1000, 64, 82, 23);
		panel_2.add(lblNewLabel_1_2_1_3);
		
		JButton btnNewButton_3 = new JButton("Về hiện tại");
		btnNewButton_3.setBackground(new Color(0, 0, 0));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(719, 15, 106, 26);
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("TÌM");
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBackground(new Color(255, 153, 0));
		btnNewButton_4.setBounds(868, 10, 150, 34);
		panel_2.add(btnNewButton_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(843, 189, 368, 484);
		panel_trangchu.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("ĐẶT BÀN");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBackground(new Color(255, 153, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(33, 410, 309, 38);
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("CHUYỂN BÀN");
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2_1.setForeground(new Color(255, 255, 255));
		btnNewButton_2_1.setBackground(new Color(0, 0, 0));
		btnNewButton_2_1.setBounds(33, 364, 139, 38);
		panel_3.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("HỦY ĐẶT BÀN");
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2_2.setBounds(197, 362, 145, 38);
		panel_3.add(btnNewButton_2_2);
		
		JLabel lblNewLabel_2 = new JLabel("Mã bàn:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(33, 71, 119, 27);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Loại bàn:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(33, 105, 119, 27);
		panel_3.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Vị trí:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(33, 142, 119, 27);
		panel_3.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Mã bàn:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_3.setBounds(33, 179, 119, 27);
		panel_3.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Khu vực:");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_4.setBounds(33, 216, 119, 27);
		panel_3.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Trạng thái:");
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_5.setBounds(33, 253, 119, 27);
		panel_3.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_3 = new JLabel("THÔNG TIN BÀN");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(33, 10, 306, 30);
		panel_3.add(lblNewLabel_3);
	}
}
