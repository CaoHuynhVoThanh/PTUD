package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class QuanLyNhanSu_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTable tableNV;
	private DefaultTableModel tableModelNV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNhanSu_GUI frame = new QuanLyNhanSu_GUI();
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
	public QuanLyNhanSu_GUI() {
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
		JPanel pQuanLyNhanSu = new JPanel();
		pQuanLyNhanSu.setBounds(286, 138, 1237, 689);
		contentPane.add(pQuanLyNhanSu);
		pQuanLyNhanSu.setLayout(null);
		
		JPanel pTimNV = new JPanel();
		pTimNV.setBackground(new Color(255, 153, 51));
		pTimNV.setBounds(31, 27, 1175, 90);
		pQuanLyNhanSu.add(pTimNV);
		pTimNV.setLayout(null);
		
		txtMaNV = new JTextField("Nhập mã nhân viên");
		txtMaNV.setForeground(Color.GRAY); // Đặt màu chữ xám để giống placeholder

		txtMaNV.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtMaNV.getText().equals("Nhập mã nhân viên")) {
		            txtMaNV.setText("");
		            txtMaNV.setForeground(Color.BLACK); // Đổi màu chữ về đen khi nhập
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtMaNV.getText().trim().isEmpty()) {
		            txtMaNV.setText("Nhập mã nhân viên");
		            txtMaNV.setForeground(Color.GRAY); // Đặt lại màu chữ xám
		        }
		    }
		});
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNV.setBounds(46, 29, 287, 36);
		pTimNV.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		txtTenNV = new JTextField("Nhập tên nhân viên");
		txtTenNV.setForeground(Color.GRAY); // Đặt màu chữ xám để giống placeholder

		txtTenNV.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtTenNV.getText().equals("Nhập tên nhân viên")) {
		            txtTenNV.setText("");
		            txtTenNV.setForeground(Color.BLACK); 
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtTenNV.getText().trim().isEmpty()) {
		            txtTenNV.setText("Nhập tên nhân viên");
		            txtTenNV.setForeground(Color.GRAY);
		        }
		    }
		});

		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(374, 29, 287, 36);
		pTimNV.add(txtTenNV);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(0, 0, 0));
		btnTim.setBounds(746, 26, 93, 39);
		pTimNV.add(btnTim);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(new Color(51, 204, 51));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(886, 26, 100, 39);
		pTimNV.add(btnThem);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(255, 0, 102));
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCapNhat.setBounds(1025, 26, 100, 39);
		pTimNV.add(btnCapNhat);
		
		JPanel pThongTinNV = new JPanel();
		pThongTinNV.setBackground(new Color(255, 255, 255));
		pThongTinNV.setBounds(31, 154, 1175, 514);
		pQuanLyNhanSu.add(pThongTinNV);
		pThongTinNV.setLayout(null);
		
		JLabel lblHoTen = new JLabel("Họ và tên");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHoTen.setBounds(57, 20, 90, 29);
		pThongTinNV.add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setBounds(162, 20, 179, 29);
		pThongTinNV.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(432, 20, 90, 29);
		pThongTinNV.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(498, 22, 179, 29);
		pThongTinNV.add(txtEmail);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiaChi.setBounds(747, 20, 90, 29);
		pThongTinNV.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(826, 20, 264, 29);
		pThongTinNV.add(txtDiaChi);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNgaySinh.setBounds(57, 78, 90, 29);
		pThongTinNV.add(lblNgaySinh);
		
		JLabel lblSDT = new JLabel("SDT");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSDT.setBounds(257, 78, 43, 29);
		pThongTinNV.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(324, 80, 144, 29);
		pThongTinNV.add(txtSDT);
		txtSDT.setColumns(10);
		
		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTrangThai.setBounds(517, 78, 90, 29);
		pThongTinNV.add(lblTrangThai);
		
		JComboBox comboTrangThai = new JComboBox();
		comboTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đang làm", "Nghỉ làm"}));
		comboTrangThai.setBounds(617, 78, 102, 29);
		pThongTinNV.add(comboTrangThai);
		
		JLabel lblChuVu = new JLabel("Chức vụ");
		lblChuVu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChuVu.setBounds(801, 78, 90, 29);
		pThongTinNV.add(lblChuVu);
		
		JRadioButton rdbtnNVQuanLy = new JRadioButton("Nhân viên quản lý");
		rdbtnNVQuanLy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNVQuanLy.setBackground(new Color(255, 255, 255));
		rdbtnNVQuanLy.setBounds(897, 100, 193, 21);
		pThongTinNV.add(rdbtnNVQuanLy);
		
		JRadioButton rdbtnNVPhucVu = new JRadioButton("Nhân viên phục vụ");
		rdbtnNVPhucVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNVPhucVu.setBackground(new Color(255, 255, 255));
		rdbtnNVPhucVu.setBounds(897, 78, 193, 21);
		pThongTinNV.add(rdbtnNVPhucVu);
		
		rdbtnNVPhucVu.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNVQuanLy);
		group.add(rdbtnNVPhucVu);
		
		JLabel lblThongTinNV = new JLabel("Thông tin nhân viên");
		lblThongTinNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblThongTinNV.setBounds(464, 122, 213, 47);
		pThongTinNV.add(lblThongTinNV);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 179, 1175, 335);
		pThongTinNV.add(scrollPane);
		
		String[] colnamesNV = {
				"STT", "Mã nhân viên", "Họ và tên", "Ngày sinh", "Số điện thoại", "Chức vụ", "Trạng thái", "Email", "Địa chỉ"
		};
		tableModelNV = new DefaultTableModel(colnamesNV, 0);
		tableNV = new JTable(tableModelNV);
		scrollPane.setViewportView(tableNV);
		
	}
}
