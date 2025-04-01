package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GoiMon_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableDGM;
	private JTextField txtTimKiem;
	private DefaultTableModel tableModelDGM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoiMon_GUI frame = new GoiMon_GUI();
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
	public GoiMon_GUI() {
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
		JPanel pGoiMon = new JPanel();
		pGoiMon.setBounds(286, 138, 1237, 689);
		contentPane.add(pGoiMon);
		pGoiMon.setLayout(null);
		
		txtTimKiem = new JTextField("Nhập tên món");
		txtTimKiem.setForeground(Color.GRAY); // Đặt màu chữ xám để giống placeholder

		txtTimKiem.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtTimKiem.getText().equals("Nhập tên món")) {
		            txtTimKiem.setText("");
		            txtTimKiem.setForeground(Color.BLACK); // Đổi màu chữ về đen khi nhập
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtTimKiem.getText().trim().isEmpty()) {
		            txtTimKiem.setText("Nhập tên món");
		            txtTimKiem.setForeground(Color.GRAY); // Đặt lại màu chữ xám
		        }
		    }
		});
        txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setBounds(24, 29, 304, 45);
		pGoiMon.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JComboBox comboBan = new JComboBox();
		comboBan.setModel(new DefaultComboBoxModel(new String[] {"Mã bàn", "A1-01", "A1-02"}));
		comboBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBan.setBounds(508, 35, 111, 33);
		pGoiMon.add(comboBan);
		
		JComboBox comboLoaiMon = new JComboBox();
		comboLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboLoaiMon.setModel(new DefaultComboBoxModel(new String[] {"Loại món", "Mì", "Cơm", "Sushi", "Sashimi"}));
		comboLoaiMon.setBounds(361, 35, 124, 33);
		pGoiMon.add(comboLoaiMon);
		
		JButton btnTimKiem = new JButton();
		btnTimKiem.setBackground(new Color(255, 153, 0));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setBounds(650, 29, 50, 45);
		ImageIcon iconTim = new ImageIcon("D:\\ProjectPTUD\\PTUD\\src\\images\\iconSearch.png");
		Image imgTim = iconTim.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		btnTimKiem.setIcon(new ImageIcon(imgTim));
		btnTimKiem.setFocusPainted(false);
		pGoiMon.add(btnTimKiem);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(24, 94, 676, 574);
		pGoiMon.add(tabbedPane);
		
		JScrollPane scrollPane_Mon = new JScrollPane();
		tabbedPane.addTab("Món ăn", null, scrollPane_Mon, null);
		String[] tenMon = {"Mì Soba", "Bánh Xếp Gyoza", "Kimpira", "Cơm Taco"};
		String[] giaMon = {"200,000 VND", "100,000 VND", "150,000 VND", "110,000 VND"};
		String[] hinhAnh = {"src/images/mi_soba.png", "src/images/banh_xep_gyoza.png", "src/images/kimpira.png", "src/images/com_taco.png"};

		JPanel pMonAn = new JPanel();
		pMonAn.setBackground(Color.WHITE);
		pMonAn.setLayout(null);
		pMonAn.setPreferredSize(new Dimension(600, 800));
		scrollPane_Mon.setViewportView(pMonAn);

		int columns = 3; // Số cột trên mỗi hàng
		int spacingX = 212; // Khoảng cách ngang
		int spacingY = 180; // Khoảng cách dọc

		for (int i = 0; i < tenMon.length; i++) {
		    int row = i / columns;
		    int col = i % columns;
		    
		    JPanel pMon = new JPanel();
		    pMon.setLayout(null);
		    pMon.setBackground(Color.WHITE);
		    pMon.setBounds(25 + col * spacingX, 22 + row * spacingY, 180, 151);
		    pMonAn.add(pMon);

		    JLabel lblImgMon = new JLabel("");
		    lblImgMon.setBounds(38, 0, 100, 88);
		    lblImgMon.setIcon(new ImageIcon(hinhAnh[i]));
		    pMon.add(lblImgMon);

		    JPanel pThongTinMon = new JPanel();
		    pThongTinMon.setLayout(null);
		    pThongTinMon.setBounds(0, 72, 180, 78);
		    pMon.add(pThongTinMon);

		    JLabel lblGiaMon = new JLabel(giaMon[i]);
		    lblGiaMon.setHorizontalAlignment(SwingConstants.CENTER);
		    lblGiaMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblGiaMon.setBounds(10, 22, 160, 18);
		    pThongTinMon.add(lblGiaMon);

		    JLabel lblTenMon = new JLabel(tenMon[i]);
		    lblTenMon.setHorizontalAlignment(SwingConstants.CENTER);
		    lblTenMon.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblTenMon.setBounds(10, 45, 160, 24);
		    pThongTinMon.add(lblTenMon);

		    JButton btnThemMon = new JButton("+");
		    btnThemMon.setFont(new Font("Tahoma", Font.PLAIN, 10));
		    btnThemMon.setBackground(new Color(169, 169, 169));
		    btnThemMon.setBounds(140, 0, 40, 40);
		    pMon.add(btnThemMon);
		}

		JScrollPane scrollPane_DoUong = new JScrollPane();
		tabbedPane.addTab("Đồ uống", null, scrollPane_DoUong, null);
		
		JScrollPane scrollPane_HayDung = new JScrollPane();
		tabbedPane.addTab("Hay dùng", null, scrollPane_HayDung, null);
		
		JScrollPane scrollPane_DGM = new JScrollPane();
		scrollPane_DGM.setBounds(733, 29, 480, 430);
		pGoiMon.add(scrollPane_DGM);
		
		String[] colnamesDGM = {
				"Tên món ăn", "SL", "Thành tiền", "Hủy"
		};
		tableModelDGM = new DefaultTableModel(colnamesDGM, 0);
		tableDGM = new JTable(tableModelDGM);
		scrollPane_DGM.setViewportView(tableDGM);
		
		JPanel pTongTien = new JPanel();
		pTongTien.setBackground(new Color(255, 255, 255));
		pTongTien.setBounds(733, 486, 480, 177);
		pGoiMon.add(pTongTien);
		pTongTien.setLayout(null);
		
		JLabel lblMaBan = new JLabel("Mã bàn:");
		lblMaBan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaBan.setBounds(45, 18, 344, 28);
		pTongTien.add(lblMaBan);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTongTien.setBounds(45, 56, 391, 28);
		pTongTien.add(lblTongTien);
		
		JButton btnGhiChu = new JButton("Ghi chú");
		btnGhiChu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGhiChu.setForeground(new Color(255, 255, 255));
		btnGhiChu.setBackground(new Color(153, 153, 153));
		btnGhiChu.setBounds(45, 115, 110, 40);
		pTongTien.add(btnGhiChu);
		
		JButton btnHuyBo = new JButton("Hủy bỏ");
		btnHuyBo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHuyBo.setForeground(new Color(255, 255, 255));
		btnHuyBo.setBackground(new Color(0, 0, 0));
		btnHuyBo.setBounds(186, 115, 110, 40);
		pTongTien.add(btnHuyBo);
		
		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXacNhan.setForeground(new Color(255, 255, 255));
		btnXacNhan.setBackground(new Color(255, 153, 0));
		btnXacNhan.setBounds(326, 115, 110, 40);
		pTongTien.add(btnXacNhan);
		
	}
}
