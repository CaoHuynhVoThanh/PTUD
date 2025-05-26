package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
public class QuanLy_GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_quanly;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_GUI frame = new QuanLy_GUI();
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
	public QuanLy_GUI() {
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
		panel_quanly = new JPanel();
		panel_quanly.setBackground(new Color(255, 255, 255));
		panel_quanly.setBounds(285, 133, 1254, 704);
		contentPane.add(panel_quanly);
		panel_quanly.setLayout(null); // Vẫn sử dụng null layout

		// Tạo menuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1254, 40); // Đặt vị trí và kích thước cụ thể
		panel_quanly.add(menuBar);

		// Tạo các menu item
		JMenuItem mntmQLBan = new JMenuItem("Quản lý bàn");
		menuBar.add(mntmQLBan);

		JMenuItem mntmQLMon = new JMenuItem("Quản lý món");
		menuBar.add(mntmQLMon);

		JMenuItem mntmQLNhanSu = new JMenuItem("Quản lý nhân sự");
		menuBar.add(mntmQLNhanSu);

		JMenuItem mntmQLThanhVien = new JMenuItem("Quản lý thành viên");
		menuBar.add(mntmQLThanhVien);

		JMenuItem mntmQLPhuPhi = new JMenuItem("Quản lý phụ phí & ưu đãi");
		menuBar.add(mntmQLPhuPhi);
		
		// Tạo panel chứa nội dung (nằm dưới menuBar)
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(0, 40, 1254, 664); // Đặt bên dưới menuBar
		contentPanel.setLayout(null);
		panel_quanly.add(contentPanel);
		
		// Thêm sự kiện cho menu Quản lý nhân sự
		mntmQLNhanSu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Reset màu tất cả các menu item
		        resetMenuItemsColor(menuBar);
		        
		        // Đặt màu nền cho item được chọn
		        mntmQLNhanSu.setBackground(new Color(51, 153, 255)); // Màu xanh dương
		        mntmQLNhanSu.setForeground(Color.WHITE);
		        contentPanel.removeAll();
		        
		        // Tạo và thêm panel quản lý nhân sự
		        QuanLyNhanSu_GUI quanLyNhanSu = new QuanLyNhanSu_GUI();
		        JPanel pNhanSu = quanLyNhanSu.getPanel();
		        pNhanSu.setBounds(0, 0, 1254, 664); // Đặt kích thước phù hợp
		        contentPanel.add(pNhanSu);
		        
		        contentPanel.revalidate();
		        contentPanel.repaint();
		    }
		});
		mntmQLBan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Reset màu tất cả các menu item
		        resetMenuItemsColor(menuBar);
		        
		        // Đặt màu nền cho item được chọn
		        mntmQLBan.setBackground(new Color(51, 153, 255)); // Màu xanh dương
		        mntmQLBan.setForeground(Color.WHITE);
		        contentPanel.removeAll();

		        // Tạo và thêm panel quản lý nhân sự
		        QuanLyBan_GUI quanLyBan_GUI = new QuanLyBan_GUI();
		        JPanel pBan = quanLyBan_GUI.getPanel();
		        pBan.setBounds(0, 0, 1254, 664); // Đặt kích thước phù hợp
		        contentPanel.add(pBan);
		        
		        contentPanel.revalidate();
		        contentPanel.repaint();
		    }
		});
		mntmQLMon.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Reset màu tất cả các menu item
		        resetMenuItemsColor(menuBar);
		        
		        // Đặt màu nền cho item được chọn
		        mntmQLMon.setBackground(new Color(51, 153, 255)); // Màu xanh dương
		        mntmQLMon.setForeground(Color.WHITE);
		        contentPanel.removeAll();
		        
		        QuanLyMon_GUI quanLyMon_GUI = new QuanLyMon_GUI();
		        JPanel pMon = quanLyMon_GUI.getPanel();
		        pMon.setBounds(0, 0, 1254, 664); // Đặt kích thước phù hợp
		        contentPanel.add(pMon);
		        
		        contentPanel.revalidate();
		        contentPanel.repaint();
		    }
		});
		mntmQLThanhVien.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Reset màu tất cả các menu item
		        resetMenuItemsColor(menuBar);
		        
		        // Đặt màu nền cho item được chọn
		        mntmQLThanhVien.setBackground(new Color(51, 153, 255)); // Màu xanh dương
		        mntmQLThanhVien.setForeground(Color.WHITE);
		        contentPanel.removeAll();
		        
		        // Tạo và thêm panel quản lý nhân sự
		        QuanLyThanhVien_GUI quanLyThanhVien_GUI = new QuanLyThanhVien_GUI();
		        JPanel pThanhVien = quanLyThanhVien_GUI.getPanel();
		        pThanhVien.setBounds(0, 0, 1254, 664); // Đặt kích thước phù hợp
		        contentPanel.add(pThanhVien);
		        
		        contentPanel.revalidate();
		        contentPanel.repaint();
		    }
		});
		mntmQLBan.doClick();
	}
	private void resetMenuItemsColor(JMenuBar menuBar) {
        for (Component comp : menuBar.getComponents()) {
            if (comp instanceof JMenuItem) {
                comp.setBackground(UIManager.getColor("Menu.background"));
                comp.setForeground(UIManager.getColor("Menu.foreground"));
            }
        }
    }
	public JPanel getPanel() {
		return this.panel_quanly;
	}
}
