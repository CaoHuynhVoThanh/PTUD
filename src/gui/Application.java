package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.UIManager;
import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;


import connectDB.ConnectDB;
import entities.NhanVien;
import test.LoadingPanel;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
public class Application extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JMenuItem mi_TrangChu;
	public static JMenuItem mi_DatBan;
	public static JMenuItem mi_NhanBan;
	public static JMenuItem mi_GoiMon;
	public static JMenuItem mi_ThanhToan;
	public static JMenuItem mi_LichSu;
	public static JMenuItem mi_ThongKe;
	public static JMenuItem mi_QuanLy;
	public static JMenuItem mi_TroGiup;
	public static NhanVien nhanvien = null;
	public static Application frame = new Application();
	public static DangNhap_GUI g = new DangNhap_GUI();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectDB con = new ConnectDB();
					con.connect();
					if (nhanvien==null) {
						g = new DangNhap_GUI();
						g.setVisible(true);
					}
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private JButton btn_dangxuat;

	/**
	 * Create the frame.
	 */
	public Application() {
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
		
		JLabel name = new JLabel("Lê Vinh Quang");
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		name.setFont(new Font("Arial", Font.BOLD, 22));
		name.setForeground(new Color(255, 255, 255));
		name.setBounds(1114, 43, 247, 41);
		panel_1.add(name);
		
		JLabel lblNewLabel = new JLabel("Nhân viên quầy");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(1220, 74, 135, 29);
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
		
		mi_TrangChu = new JMenuItem("              TRANG CHỦ");
		mi_TrangChu.addActionListener(this);
		mi_TrangChu.setBackground(new Color(255, 153, 0));
		mi_TrangChu.setSelected(true);
		mi_TrangChu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_TrangChu.setHorizontalAlignment(SwingConstants.LEFT);
		mi_TrangChu.setForeground(new Color(255, 255, 255));
		mi_TrangChu.setBounds(20, 48, 291, 61);
		panel.add(mi_TrangChu);
		
		btn_dangxuat = new JButton("ĐĂNG XUẤT");
		btn_dangxuat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_dangxuat.setForeground(new Color(255, 255, 255));
		btn_dangxuat.setBackground(new Color(0, 0, 0));
		btn_dangxuat.setBounds(89, 641, 164, 42);
		btn_dangxuat.addActionListener(this);
		panel.add(btn_dangxuat);
		
		mi_DatBan = new JMenuItem("              ĐẶT BÀN");
		mi_DatBan.addActionListener(this);
		mi_DatBan.setSelected(true);
		mi_DatBan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_DatBan.setForeground(Color.WHITE);
		mi_DatBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_DatBan.setBackground(new Color(255, 153, 0));
		mi_DatBan.setBounds(20, 109, 291, 61);
		panel.add(mi_DatBan);
		
		mi_NhanBan = new JMenuItem("              NHẬN BÀN");
		mi_NhanBan.addActionListener(this);
		mi_NhanBan.setSelected(true);
		mi_NhanBan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_NhanBan.setForeground(Color.WHITE);
		mi_NhanBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_NhanBan.setBackground(new Color(255, 153, 0));
		mi_NhanBan.setBounds(20, 171, 291, 61);
		panel.add(mi_NhanBan);
		
		mi_GoiMon = new JMenuItem("              GỌI MÓN");
		mi_GoiMon.addActionListener(this);
		mi_GoiMon.setSelected(true);
		mi_GoiMon.setHorizontalAlignment(SwingConstants.LEFT);
		mi_GoiMon.setForeground(Color.WHITE);
		mi_GoiMon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_GoiMon.setBackground(new Color(255, 153, 0));
		mi_GoiMon.setBounds(20, 233, 291, 61);
		panel.add(mi_GoiMon);
		
		mi_ThanhToan = new JMenuItem("              THANH TOÁN");
		mi_ThanhToan.addActionListener(this);
		mi_ThanhToan.setSelected(true);
		mi_ThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
		mi_ThanhToan.setForeground(Color.WHITE);
		mi_ThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_ThanhToan.setBackground(new Color(255, 153, 0));
		mi_ThanhToan.setBounds(20, 293, 291, 61);
		panel.add(mi_ThanhToan);
		
		mi_LichSu = new JMenuItem("              LỊCH SỬ");
		mi_LichSu.addActionListener(this);
		mi_LichSu.setSelected(true);
		mi_LichSu.setHorizontalAlignment(SwingConstants.LEFT);
		mi_LichSu.setForeground(Color.WHITE);
		mi_LichSu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_LichSu.setBackground(new Color(255, 153, 0));
		mi_LichSu.setBounds(20, 353, 291, 61);
		panel.add(mi_LichSu);
		
		mi_ThongKe = new JMenuItem("              THỐNG KÊ");
		mi_ThongKe.addActionListener(this);
		mi_ThongKe.setSelected(true);
		mi_ThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		mi_ThongKe.setForeground(Color.WHITE);
		mi_ThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_ThongKe.setBackground(new Color(255, 153, 0));
		mi_ThongKe.setBounds(20, 478, 291, 61);
		panel.add(mi_ThongKe);
		
		mi_QuanLy = new JMenuItem("              QUẢN LÝ");
		mi_QuanLy.addActionListener(this);
		mi_QuanLy.setSelected(true);
		mi_QuanLy.setHorizontalAlignment(SwingConstants.LEFT);
		mi_QuanLy.setForeground(Color.WHITE);
		mi_QuanLy.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_QuanLy.setBackground(new Color(255, 153, 0));
		mi_QuanLy.setBounds(20, 417, 291, 61);
		panel.add(mi_QuanLy);
		
		mi_TroGiup = new JMenuItem("              TRỢ GIÚP");
		mi_TroGiup.addActionListener(this);
		mi_TroGiup.setSelected(true);
		mi_TroGiup.setHorizontalAlignment(SwingConstants.LEFT);
		mi_TroGiup.setForeground(Color.WHITE);
		mi_TroGiup.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mi_TroGiup.setBackground(new Color(255, 153, 0));
		mi_TroGiup.setBounds(20, 541, 291, 61);
		panel.add(mi_TroGiup);
		
		JPanel panel_trangchu = new JPanel();
		panel_trangchu.setBackground(new Color(255, 255, 255));
		panel_trangchu.setBounds(285, 133, 1254, 704);
		contentPane.add(panel_trangchu);
		mi_TrangChu.doClick();
//		panel_trangchu.setLayout(null);
//		ArrayList<Integer> viTriKhongTrung = (ArrayList<Integer>) dsb.stream()
//			    .map(Ban::getViTri)              // Lấy vị trí
//			    .distinct()                      // Loại bỏ trùng lặp
//			    .sorted()                        // Sắp xếp tăng dần
//			    .collect(Collectors.toList());   // Thu về danh sách
//		for (Integer i: viTriKhongTrung) {
//			comb_tang.addItem(i.toString());
//		}
//		ArrayList<String> tenKhuVucKhongTrung = (ArrayList<String>) dsb.stream()
//			    .map(Ban::getTenKV)               // Lấy tên khu vực
//			    .filter(Objects::nonNull)         // Bỏ null nếu có
//			    .distinct()                       // Loại trùng lặp
//			    .sorted()                         // Sắp xếp tăng dần
//			    .collect(Collectors.toList());
//		for (String s: tenKhuVucKhongTrung) {
//			comb_kv.addItem(s);
//		}
//		ArrayList<Integer> loaiBanKhongTrung = (ArrayList<Integer>) dsb.stream()
//			    .map(Ban::getLoaiBan)           // Lấy giá trị loaiBan
//			    .distinct()                     // Loại trùng
//			    .sorted()                       // Sắp xếp tăng dần
//			    .collect(Collectors.toList());
//		for (Integer i: loaiBanKhongTrung) {
//			comb_loaiban.addItem(i.toString());
//		}
//		hiddenDateChange.setVisible(false);
//		hiddenDateChange.addActionListener(this);
//		loadAllBan();
//		displayBan(dsb);
//		hiddenButton.addActionListener(this);
	}
	public void loadPanel(JPanel gui) {
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand().trim();
		if (cmd.equalsIgnoreCase("trang chủ")) {
			int count = contentPane.getComponentCount();
			if (count > 0) {
			    contentPane.remove(count - 1);
			}
			LoadingPanel loading = new LoadingPanel();
			contentPane.add(loading);

			// Quan trọng: cập nhật lại giao diện sau khi thay đổi component
			contentPane.revalidate();
			contentPane.repaint();
			loading.setBounds(285, 133, 1254, 704);
			loading.loadAsync(() -> {
			    TrangChu_GUI gui = new TrangChu_GUI();
			    return gui.getPanel();
			}, 500);
		}
		if (cmd.equals("ĐĂNG XUẤT")){
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?")==JOptionPane.YES_OPTION) {
				frame.setVisible(false);
				g.clearAll();
				g.setVisible(true);
				nhanvien=null;
			}
		}
		if (cmd.equalsIgnoreCase("đặt bàn")) {
			int count = contentPane.getComponentCount();
			if (count > 0) {
			    contentPane.remove(count - 1);
			}
			LoadingPanel loading = new LoadingPanel();
			contentPane.add(loading);

			// Quan trọng: cập nhật lại giao diện sau khi thay đổi component
			contentPane.revalidate();
			contentPane.repaint();
			loading.setBounds(285, 133, 1254, 704);
			loading.loadAsync(() -> {
			    DatBan_GUI gui = new DatBan_GUI();
			    return gui.getPanel();
			}, 1000);
		}
		if (cmd.equalsIgnoreCase("nhận bàn")) {
			int count = contentPane.getComponentCount();
			if (count > 0) {
			    contentPane.remove(count - 1);
			}
			LoadingPanel loading = new LoadingPanel();
			contentPane.add(loading);

			// Quan trọng: cập nhật lại giao diện sau khi thay đổi component
			contentPane.revalidate();
			contentPane.repaint();
			loading.setBounds(285, 133, 1254, 704);
			loading.loadAsync(() -> {
			    NhanDon_GUI gui = new NhanDon_GUI();
			    return gui.getPanel();
			}, 1000);
		}
		if (cmd.equalsIgnoreCase("gọi món")) {
			int count = contentPane.getComponentCount();
			if (count > 0) {
			    contentPane.remove(count - 1);
			}
			LoadingPanel loading = new LoadingPanel();
			contentPane.add(loading);

			// Quan trọng: cập nhật lại giao diện sau khi thay đổi component
			contentPane.revalidate();
			contentPane.repaint();
			loading.setBounds(285, 133, 1254, 704);
			loading.loadAsync(() -> {
			    GoiMon_GUI gui = new GoiMon_GUI();
			    return gui.getPanel();
			}, 1000);
		}
		if (cmd.equalsIgnoreCase("thanh toán")) {
			int count = contentPane.getComponentCount();
			if (count > 0) {
			    contentPane.remove(count - 1);
			}
			LoadingPanel loading = new LoadingPanel();
			contentPane.add(loading);

			// Quan trọng: cập nhật lại giao diện sau khi thay đổi component
			contentPane.revalidate();
			contentPane.repaint();
			loading.setBounds(285, 133, 1254, 704);
			loading.loadAsync(() -> {
			    ThanhToan_GUI gui = new ThanhToan_GUI();
			    return gui.getPanel();
			}, 1000);
		}
		if (cmd.equalsIgnoreCase("lịch sử")) {
			int count = contentPane.getComponentCount();
			if (count > 0) {
			    contentPane.remove(count - 1);
			}
			LoadingPanel loading = new LoadingPanel();
			contentPane.add(loading);

			// Quan trọng: cập nhật lại giao diện sau khi thay đổi component
			contentPane.revalidate();
			contentPane.repaint();
			loading.setBounds(285, 133, 1254, 704);
			loading.loadAsync(() -> {
			    LichSu_GUI gui = new LichSu_GUI();
			    return gui.getPanel();
			}, 1000);
		}
		if (cmd.equalsIgnoreCase("thống kê")) {
			int count = contentPane.getComponentCount();
			if (count > 0) {
			    contentPane.remove(count - 1);
			}
			LoadingPanel loading = new LoadingPanel();
			contentPane.add(loading);

			// Quan trọng: cập nhật lại giao diện sau khi thay đổi component
			contentPane.revalidate();
			contentPane.repaint();
			loading.setBounds(285, 133, 1254, 704);
			loading.loadAsync(() -> {
			    ThongKe_GUI gui = new ThongKe_GUI();
			    return gui.getPanel();
			}, 1000);
		}
		if (cmd.equalsIgnoreCase("quản lý")) {
			int count = contentPane.getComponentCount();
			if (count > 0) {
			    contentPane.remove(count - 1);
			}
			LoadingPanel loading = new LoadingPanel();
			contentPane.add(loading);

			// Quan trọng: cập nhật lại giao diện sau khi thay đổi component
			contentPane.revalidate();
			contentPane.repaint();
			loading.setBounds(285, 133, 1254, 704);
			loading.loadAsync(() -> {
			    QuanLy_GUI gui = new QuanLy_GUI();
			    return gui.getPanel();
			}, 1000);
		}
		if (cmd.equalsIgnoreCase("trợ giúp")) {
		    try {
		        File htmlFile = new File("src/gui/help.html");
		        Desktop.getDesktop().browse(htmlFile.toURI());
		    } catch (IOException ex) {
		        System.err.println("Error opening help file: " + ex.getMessage());
		        JOptionPane.showMessageDialog(this, 
		            "Không thể mở trang trợ giúp", 
		            "Lỗi", 
		            JOptionPane.ERROR_MESSAGE);
		    }
		}
	}

}
