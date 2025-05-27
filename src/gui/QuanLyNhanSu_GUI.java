package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import entities.NhanVien;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuanLyNhanSu_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTim;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTable tableNV;
	private DefaultTableModel tableModelNV;
	private JComboBox comboTrangThai;
	private JRadioButton rdbtnNVPhucVu;
	private JRadioButton rdbtnNVQuanLy;
	private JDateChooser dateChooser = new JDateChooser();
	ConnectDB con;
	private JButton btnTim;
	private JPanel pQuanLyNhanSu;
	
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
		logo.setIcon(new ImageIcon(getClass().getResource("/images/App/logo.png")));
		logo.setBounds(66, 22, 247, 89);
		panel_1.add(logo);
		
		JLabel avt = new JLabel("");
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/App/avt.png"));
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
		pQuanLyNhanSu = new JPanel();
		pQuanLyNhanSu.setBounds(286, 138, 1237, 689);
		contentPane.add(pQuanLyNhanSu);
		pQuanLyNhanSu.setLayout(null);
		
		JPanel pTimNV = new JPanel();
		pTimNV.setBackground(new Color(255, 153, 51));
		pTimNV.setBounds(28, 27, 796, 90);
		pQuanLyNhanSu.add(pTimNV);
		pTimNV.setLayout(null);
		
		txtTim = new JTextField("Nhập mã hoặc tên nhân viên");
		txtTim.setForeground(Color.GRAY); // Đặt màu chữ xám để giống placeholder

		txtTim.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtTim.getText().equals("Nhập mã hoặc tên nhân viên")) {
		            txtTim.setText("");
		            txtTim.setForeground(Color.BLACK); // Đổi màu chữ về đen khi nhập
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtTim.getText().trim().isEmpty()) {
		            txtTim.setText("Nhập mã hoặc tên nhân viên");
		            txtTim.setForeground(Color.GRAY); // Đặt lại màu chữ xám
		        }
		    }
		});
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTim.setBounds(46, 29, 370, 36);
		pTimNV.add(txtTim);
		txtTim.setColumns(10);
		
		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(0, 0, 0));
		btnTim.setBounds(501, 28, 110, 39);
		pTimNV.add(btnTim);
		
		JButton btnKhoiPhuc = new JButton("Khôi phục");
		btnKhoiPhuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnKhoiPhuc.setForeground(Color.WHITE);
		btnKhoiPhuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKhoiPhuc.setBackground(Color.BLACK);
		btnKhoiPhuc.setBounds(644, 28, 110, 39);
		pTimNV.add(btnKhoiPhuc);
		
		JPanel pThongTinNV = new JPanel();
		pThongTinNV.setBackground(new Color(255, 255, 255));
		pThongTinNV.setBounds(28, 154, 1190, 514);
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
		
		Date today = new Date();
        dateChooser.setDate(today);
		dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(162, 78, 144, 29);
        dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dateChooser.setMaxSelectableDate(today);
        pThongTinNV.add(dateChooser);
		
		JLabel lblSDT = new JLabel("SDT");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSDT.setBounds(342, 78, 43, 29);
		pThongTinNV.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(395, 81, 144, 29);
		pThongTinNV.add(txtSDT);
		txtSDT.setColumns(10);
		
		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTrangThai.setBounds(587, 78, 90, 29);
		pThongTinNV.add(lblTrangThai);
		
		comboTrangThai = new JComboBox();
		comboTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đang làm", "Nghỉ làm"}));
		comboTrangThai.setBounds(696, 79, 102, 29);
		pThongTinNV.add(comboTrangThai);
		
		JLabel lblChuVu = new JLabel("Chức vụ");
		lblChuVu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChuVu.setBounds(841, 78, 90, 29);
		pThongTinNV.add(lblChuVu);
		
		rdbtnNVQuanLy = new JRadioButton("Nhân viên quản lý");
		rdbtnNVQuanLy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNVQuanLy.setBackground(new Color(255, 255, 255));
		rdbtnNVQuanLy.setBounds(937, 107, 153, 21);
		pThongTinNV.add(rdbtnNVQuanLy);
		
		rdbtnNVPhucVu = new JRadioButton("Nhân viên quầy");
		rdbtnNVPhucVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNVPhucVu.setBackground(new Color(255, 255, 255));
		rdbtnNVPhucVu.setBounds(937, 83, 153, 21);
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
		scrollPane.setBounds(0, 179, 1190, 335);
		pThongTinNV.add(scrollPane);
		
		String[] colnamesNV = {
				"Mã nhân viên", "Họ và tên", "Ngày sinh", "Số điện thoại", "Chức vụ", "Trạng thái", "Email", "Địa chỉ"
		};
		tableModelNV = new DefaultTableModel(colnamesNV, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa ô nào cả
            }
		};
		tableNV = new JTable(tableModelNV);
		scrollPane.setViewportView(tableNV);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(1129, 0, 61, 21);
		pThongTinNV.add(btnXoa);
		tableNV.getTableHeader().setReorderingAllowed(false);
		tableNV.getTableHeader().setResizingAllowed(false);
		
		btnXoa.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Clear all text fields
		        txtHoTen.setText("");
		        txtEmail.setText("");
		        txtDiaChi.setText("");
		        txtSDT.setText("");
		        txtTim.setText("Nhập mã hoặc tên nhân viên");
		        txtTim.setForeground(Color.GRAY); // Reset placeholder style
		        comboTrangThai.setSelectedIndex(0);
		        rdbtnNVPhucVu.setSelected(true);
		        dateChooser.setDate(new Date());
		    }
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 153, 51));
		panel_2.setBounds(897, 27, 322, 90);
		pQuanLyNhanSu.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBackground(new Color(51, 204, 51));
		btnThem.setBounds(32, 28, 110, 39);
		panel_2.add(btnThem);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCapNhat.setBackground(new Color(255, 0, 102));
		btnCapNhat.setBounds(183, 28, 110, 39);
		panel_2.add(btnCapNhat);
		
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatNhanVien();
			}
		});
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themNhanVien();
			}
		});
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimNhanVien();
			}
		});
		btnKhoiPhuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataNhanVien();
			}
		});
		loadDataNhanVien();
		addTableSelectionListener();
	}
	private void loadDataNhanVien() {
	    try {
	        // Xóa dữ liệu cũ trong bảng
	        tableModelNV.setRowCount(0);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        // Lấy danh sách nhân viên từ DAO
	        ArrayList<NhanVien> dsNhanVien = NhanVien_DAO.getAllNhanVien();
	        
	        // Thêm dữ liệu vào bảng
	        for (NhanVien nv : dsNhanVien) {
	            Object[] rowData = {
	                nv.getMaNV(),
	                nv.getTenNV(),
	                nv.getNgaySinh().format(formatter),
	                nv.getSdt(),
	                nv.getChucVu(),
	                nv.isTrangThai() ? "Đang làm" : "Nghỉ làm",
	                nv.getEmail(),
	                nv.getDiaChi()
	            };
	            tableModelNV.addRow(rowData);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private void TimNhanVien() {
		String keyword = txtTim.getText().trim();
	    if (keyword.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hoặc tên nhân viên để tìm kiếm!");
	        return;
	    }

	    // Xóa dữ liệu cũ trong bảng
	    tableModelNV.setRowCount(0);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    // Tìm theo mã trước
	    NhanVien nv = NhanVien_DAO.timNhanVienTheoMa(keyword);
	    if (nv != null) {
	        String[] rowData = new String[]{
	            nv.getMaNV(),
	            nv.getTenNV(),
	            nv.getNgaySinh().format(formatter),
	            nv.getSdt(),
	            nv.getChucVu(),
	            nv.isTrangThai() ? "Đang làm" : "Đã nghỉ",
	            nv.getEmail(),
	            nv.getDiaChi()
	        };
	        tableModelNV.addRow(rowData);
	    } else {
	        // Nếu không tìm thấy theo mã, tìm theo tên
	        ArrayList<NhanVien> ds = NhanVien_DAO.timNhanVienTheoTen(keyword);
	        if (ds.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên!");
	        } else {
	            for (NhanVien nvTen : ds) {
	                String[] rowData = new String[]{
	                    nvTen.getMaNV(),
	                    nvTen.getTenNV(),
	                    nvTen.getNgaySinh().format(formatter),
	                    nvTen.getSdt(),
	                    nvTen.getChucVu(),
	                    nvTen.isTrangThai() ? "Đang làm" : "Đã nghỉ",
	                    nvTen.getEmail(),
	                    nvTen.getDiaChi()
	                };
	                tableModelNV.addRow(rowData);
	            }
	        }
	    }
	}
	private void themNhanVien() {
		if (!validateInput()) {
	        return;
	    }
	    try {
	        String tenNV = txtHoTen.getText().trim();
	        String email = txtEmail.getText().trim();
	        String diaChi = txtDiaChi.getText().trim();
	        String sdt = txtSDT.getText().trim();

	        Date selectedDate = dateChooser.getDate();
	        if (selectedDate == null) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        LocalDate ngaySinh = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	        // Kiểm tra các trường bắt buộc
	        if (tenNV.isEmpty() || email.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        boolean trangThai = comboTrangThai.getSelectedItem().toString().equals("Đang làm");
	        String chucVu = rdbtnNVPhucVu.isSelected() ? "Nhân viên quầy" : "Nhân viên quản lý";

	        // Tạo mã nhân viên dạng YYXXXXXX
	        int year = LocalDate.now().getYear();
	        String yearSuffix = String.format("%04d", year);
	        int stt = NhanVien_DAO.getAllNhanVien().size() + 1;
	        String soThuTu = String.format("%06d", stt);
	        String maNV ="NV" + yearSuffix + soThuTu;

	        NhanVien nv = new NhanVien(
	            maNV,
	            tenNV,
	            email,
	            sdt,
	            diaChi,
	            chucVu,
	            ngaySinh,
	            trangThai
	        );

	        boolean tkThemThanhCong = TaiKhoan_DAO.themTaiKhoan(maNV, "123456");

	        if (!tkThemThanhCong) {
	            JOptionPane.showMessageDialog(this, "Tạo tài khoản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        boolean nvThemThanhCong = NhanVien_DAO.themNhanVien(nv);

	        if (nvThemThanhCong) {
	            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
	            loadDataNhanVien();
	        } else {
	            JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Lỗi khi thêm nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void capNhatNhanVien() {
		if (!validateInput()) {
	        return; // Stop if validation fails
	    }
	    try {
	        // Kiểm tra có hàng nào được chọn trong bảng không
	        int selectedRow = tableNV.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(
	                this, 
	                "Vui lòng chọn nhân viên cần cập nhật", 
	                "Thông báo", 
	                JOptionPane.WARNING_MESSAGE
	            );
	            return;
	        }
	        
	        // Lấy mã nhân viên từ hàng được chọn
	        String maNV = tableNV.getValueAt(selectedRow, 0).toString();
	        
	        // Lấy thông tin từ các trường nhập liệu
	        String tenNV = txtHoTen.getText().trim();
	        String email = txtEmail.getText().trim();
	        String diaChi = txtDiaChi.getText().trim();
	        String sdt = txtSDT.getText().trim();
	        
	        // Kiểm tra các trường bắt buộc
	        if (tenNV.isEmpty() || email.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()) {
	            JOptionPane.showMessageDialog(
	                this, 
	                "Vui lòng điền đầy đủ thông tin bắt buộc", 
	                "Lỗi", 
	                JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }
	        
	        // Lấy ngày sinh từ JDateChooser
	        Date selectedDate = dateChooser.getDate();
	        if (selectedDate == null) {
	            JOptionPane.showMessageDialog(
	                this, 
	                "Vui lòng chọn ngày sinh", 
	                "Lỗi", 
	                JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }
	        LocalDate ngaySinh = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        
	        // Lấy thông tin từ combobox và radio button
	        boolean trangThai = comboTrangThai.getSelectedItem().toString().equals("Đang làm");
	        String chucVu = rdbtnNVPhucVu.isSelected() ? "Nhân viên quầy" : "Nhân viên quản lý";
	        
	        // Tạo đối tượng nhân viên
	        NhanVien nv = new NhanVien(
	            maNV,
	            tenNV, 
	            email, 
	            sdt, 
	            diaChi, 
	            chucVu, 
	            ngaySinh, 
	            trangThai
	        );
	        
	        // Cập nhật vào database
	        if (NhanVien_DAO.suaNhanVien(nv)) {
	            JOptionPane.showMessageDialog(
	                this, 
	                "Cập nhật nhân viên thành công!", 
	                "Thành công", 
	                JOptionPane.INFORMATION_MESSAGE
	            );
	            loadDataNhanVien(); 
	            for (int i = 0; i < tableModelNV.getRowCount(); i++) {
	                if (tableModelNV.getValueAt(i, 0).toString().equals(maNV)) {
	                    tableNV.setRowSelectionInterval(i, i); // chọn lại dòng
	                    break;
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(
	                this, 
	                "Cập nhật nhân viên thất bại!", 
	                "Lỗi", 
	                JOptionPane.ERROR_MESSAGE
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(
	            this, 
	            "Lỗi khi cập nhật nhân viên: " + e.getMessage(), 
	            "Lỗi", 
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}
	private void addTableSelectionListener() {
	    tableNV.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting() && tableNV.getSelectedRow() != -1) {
	            int selectedRow = tableNV.getSelectedRow();
	            // Hiển thị thông tin nhân viên được chọn lên các trường nhập liệu
	            txtHoTen.setText(tableNV.getValueAt(selectedRow, 1).toString());
	            txtEmail.setText(tableNV.getValueAt(selectedRow, 6).toString());
	            txtDiaChi.setText(tableNV.getValueAt(selectedRow, 7).toString());
	            txtSDT.setText(tableNV.getValueAt(selectedRow, 3).toString());
	            
	            // Cập nhật combobox trạng thái
	            String trangThai = tableNV.getValueAt(selectedRow, 5).toString();
	            comboTrangThai.setSelectedItem(trangThai);
	            
	            try {
	                String ngaySinhStr = tableNV.getValueAt(selectedRow, 2).toString();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                LocalDate ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
	                Date date = Date.from(ngaySinh.atStartOfDay(ZoneId.systemDefault()).toInstant());
	                dateChooser.setDate(date);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                dateChooser.setDate(null);
	            }
	            
	            // Cập nhật radio button chức vụ
	            String chucVu = tableNV.getValueAt(selectedRow, 4).toString();
	            if (chucVu.equals("Nhân viên quản lý")) {
	                rdbtnNVQuanLy.setSelected(true);
	            } else {
	                rdbtnNVPhucVu.setSelected(true);
	            }
	        }
	    });
	}
	private void clearFields() {
	    txtHoTen.setText("");
	    txtEmail.setText("");
	    txtDiaChi.setText("");
	    txtSDT.setText("");
	    comboTrangThai.setSelectedIndex(0);
	    rdbtnNVPhucVu.setSelected(true);
	}
	private boolean validateInput() {
	    // Validate Họ tên (txtHoTen) - Chỉ cho phép chữ cái tiếng Việt và khoảng trắng
	    String hoTen = txtHoTen.getText().trim();
	    if (!hoTen.matches("^^(?:[A-ZÀ-Ỹ][a-zà-ỹ']+)(?: [A-ZÀ-Ỹ][a-zà-ỹ']+)*$")) {
	        JOptionPane.showMessageDialog(this, "Họ tên phải bắt đầu bằng chữ hoa theo sau chữ thường!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Validate Email (txtEmail) - Định dạng email
	    String email = txtEmail.getText().trim();
	    if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
	        JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Validate Ngày sinh (dateChooser) - Phải đủ 18 tuổi
	    Date selectedDate = dateChooser.getDate();
	    if (selectedDate == null) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    LocalDate ngaySinh = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate now = LocalDate.now();
	    if (now.minusYears(18).isBefore(ngaySinh)) {
	        JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Validate Số điện thoại (txtSDT) - Chỉ chứa 10 ký tự số
	    String sdt = txtSDT.getText().trim();
	    if (!sdt.matches("^\\d{10}$")) {
	        JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm 10 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    return true;
	}

	public JPanel getPanel() {
        return pQuanLyNhanSu;
    }
}
