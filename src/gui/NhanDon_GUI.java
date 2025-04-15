package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.NhanDon_DAO;
import entities.DonDatBanView;

//import dao.Ban_DAO;
//import entities.Ban;

public class NhanDon_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDateChooser JDC_ngaychon = new JDateChooser();
	private JTextField textField_nhapTenMon;
	private JLabel lblTitle;
	private JTextField txtSoDienThoai;
	private JTextField txtNgayNhan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanDon_GUI frame = new NhanDon_GUI();
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
	public NhanDon_GUI() {
		ArrayList<DonDatBanView> donList = NhanDon_DAO.getAllDon();

		JDC_ngaychon.setDate(new Date());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(1537, 864);
		this.setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setContentPane(contentPane);
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
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();

                lb_ngay.setText("Ngày: " + currentDate.format(dateFormatter));
                lb_thoiGian.setText("Thời gian: " + currentTime.format(timeFormatter));
            }
        }, 0, 1000);
		
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
//		aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		
		JPanel panel_DSDon = new JPanel();
		panel_DSDon.setLayout(null);
		panel_DSDon.setBounds(320, 160, 1100, 600); 
//		panel_DSDon.setBounds(0, 0, 1100, 600); 
//		panel_DSDon.setBackground(Color.red);
		contentPane.add(panel_DSDon);
		
		panel_DSDon.add(lblTitle = new JLabel("Danh sách đơn chờ nhận"));
		lblTitle.setBounds(100, 0, 300, 30);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JPanel panel_TimKiem = new JPanel();
		panel_TimKiem.setBounds(0, 50, 1100, 30);
		panel_DSDon.add(panel_TimKiem);
		panel_TimKiem.setLayout(null); 

		
		txtSoDienThoai = new JTextField("   Nhập số điện thoại người đặt");
		txtSoDienThoai.setBounds(0, 0, 200, 30);
		panel_TimKiem.add(txtSoDienThoai);
		txtSoDienThoai.setForeground(Color.GRAY); // Đặt màu chữ xám để giống placeholder
		txtSoDienThoai.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtSoDienThoai.getText().equals("   Nhập số điện thoại người đặt")) {
		        	txtSoDienThoai.setText("");
		        	txtSoDienThoai.setForeground(Color.BLACK); // Đổi màu chữ về đen khi nhập
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtSoDienThoai.getText().trim().isEmpty()) {
		        	txtSoDienThoai.setText("   Nhập số điện thoại người đặt");
		        	txtSoDienThoai.setForeground(Color.GRAY); // Đặt lại màu chữ xám
		        }
		    }
		});
		
		txtNgayNhan = new JTextField("   Ngày nhận");
		txtNgayNhan.setBounds(230, 0, 200, 30);
		panel_TimKiem.add(txtNgayNhan);
		txtNgayNhan.setForeground(Color.GRAY); // Đặt màu chữ xám để giống placeholder
		txtNgayNhan.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtNgayNhan.getText().equals("   Ngày nhận")) {
		        	txtNgayNhan.setText("");
		        	txtNgayNhan.setForeground(Color.BLACK); // Đổi màu chữ về đen khi nhập
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtNgayNhan.getText().trim().isEmpty()) {
		        	txtNgayNhan.setText("   Ngày nhận");
		        	txtNgayNhan.setForeground(Color.GRAY); // Đặt lại màu chữ xám
		        }
		    }
		});
//		txtNgayNhan.setColumns(10);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(0, 0, 0));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTim.setBounds(530, 0, 80, 30);
		panel_TimKiem.add(btnTim);

		
		JPanel panelDanhSach = new JPanel();
        panelDanhSach.setLayout(new BoxLayout(panelDanhSach, BoxLayout.Y_AXIS));
        panelDanhSach.setBackground(Color.WHITE);

        for (int i = 0; i < donList.size(); i++) {
        	LocalDateTime thoiGian = donList.get(i).getDonDatBan().getThoiGianNhan();
        	String thoiGianNhan = String.format("%02dh%02d", 
        	    thoiGian.getHour(), 
        	    thoiGian.getMinute()
        	);
        	JPanel panelDon = taoPanelDon(
        	        donList.get(i).getDonDatBan().getMaDDB(),
        	        donList.get(i).getSoBan(),
        	        -404,
        	        thoiGianNhan,
        	        donList.get(i).getSoDienThoai()
        	    );
        	panelDon.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelDanhSach.add(panelDon);
        }
//        panelDanhSach.setBackground(Color.blue);

       
        JScrollPane scrollPane = new JScrollPane(panelDanhSach);
        scrollPane.setBounds(0, 110, 600, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel_DSDon.add(scrollPane);
        
//        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        JPanel panelRight = new JPanel();
        panelRight.setBounds(700, 110, 430, 500);
		panelRight.setBackground(Color.LIGHT_GRAY);
		Box box2 = Box.createVerticalBox();
		Box br1,br2,br3,br4,br5,br6,br7,br8,br9;
		Box boxHuyDon_GiaHan,boxNhanBan;
		JButton btnHuyDon,btnGiaHan,btnNhanBan;
		JLabel lblTenKH,lblSoDT,lblSoBan,lblSoMon,lblThoiGianDat,lblThoiGianNhan,lblTienCoc,lblNhanVien;
		
		
		box2.add(br1 = Box.createHorizontalBox());
		box2.add(Box.createVerticalStrut(10));
		br1.add(new JLabel("Thông tin đặt bàn") {{
		    setFont(new Font("SansSerif", Font.BOLD, 18));
		}});
		box2.add(Box.createVerticalStrut(40));
		
		box2.add(br2 = Box.createHorizontalBox());
		br2.setPreferredSize(new Dimension(300, 30));
		br2.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br2.add(new JLabel("Tên khách hàng:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br2.add(Box.createHorizontalGlue());
		br2.add(lblTenKH = new JLabel("AAA"));
		
		box2.add(br3 = Box.createHorizontalBox());
		br3.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br3.add(new JLabel("Số điện thoại:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br3.add(Box.createHorizontalGlue());
		br3.add(lblSoDT = new JLabel("123"));
		
		box2.add(br4 = Box.createHorizontalBox());
		br4.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br4.add(new JLabel("Số bàn:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br4.add(Box.createHorizontalGlue());
		br4.add(lblSoBan = new JLabel("-1"));
		
		box2.add(br5 = Box.createHorizontalBox());
		br5.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br5.add(new JLabel("Số món:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br5.add(Box.createHorizontalGlue());
		br5.add(lblSoMon = new JLabel("-1"));
		
		box2.add(br6 = Box.createHorizontalBox());
		br6.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br6.add(new JLabel("Thời gian đặt:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br6.add(Box.createHorizontalGlue());
		br6.add(lblThoiGianDat = new JLabel("11h"));
		
		box2.add(br7 = Box.createHorizontalBox());
		br7.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br7.add(new JLabel("Thời gian nhận:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br7.add(Box.createHorizontalGlue());
		br7.add(lblThoiGianNhan = new JLabel("11h30"));
		
		box2.add(br8 = Box.createHorizontalBox());
		br8.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br8.add(new JLabel("Tiền cọc:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br8.add(Box.createHorizontalGlue());
		br8.add(lblTienCoc = new JLabel("200,000"));
		
		box2.add(br9 = Box.createHorizontalBox());
		br9.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br9.add(new JLabel("Nhân viên thực hiện:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br9.add(Box.createHorizontalGlue());
		br9.add(lblNhanVien = new JLabel("AAA"));
		box2.add(Box.createVerticalStrut(50));
		
		box2.add(boxHuyDon_GiaHan = Box.createHorizontalBox());
		box2.add(Box.createVerticalStrut(10));
		boxHuyDon_GiaHan.add(btnHuyDon = new JButton("HỦY ĐƠN") {{
		    setBackground(Color.BLACK);                     
		    setForeground(Color.WHITE);                    
		    setFont(new Font("SansSerif", Font.BOLD, 17));  
		    setFocusPainted(false);                  
		    setPreferredSize(new Dimension(130, 60));       
		}});
		boxHuyDon_GiaHan.add(Box.createHorizontalGlue());
		boxHuyDon_GiaHan.add(btnGiaHan = new JButton("GIA HẠN") {{
		    setBackground(Color.GRAY);                     
		    setForeground(Color.WHITE);                    
		    setFont(new Font("SansSerif", Font.BOLD, 17));  
		    setFocusPainted(false);                  
		    setPreferredSize(new Dimension(130, 60));       
		}});
		
		box2.add(boxNhanBan = Box.createHorizontalBox());
		box2.add(Box.createVerticalStrut(10));
		boxNhanBan.add(btnNhanBan = new JButton("NHẬN BÀN") {{
		    setBackground(Color.ORANGE);                     
		    setForeground(Color.WHITE);                    
		    setFont(new Font("SansSerif", Font.BOLD, 17));  
		    setFocusPainted(false);                  
		    setPreferredSize(new Dimension(300, 35));
		    setMaximumSize(new Dimension(300, 35)); 
		}});
		panelRight.add(box2, BorderLayout.EAST);
		panel_DSDon.add(panelRight);
		
	}
	
	private JPanel taoPanelDon(String maDon, int soBan, int soMon, String gioDen, String soDT) {
        JPanel panelDon = new JPanel(null);
        panelDon.setPreferredSize(new Dimension(1100, 110));
        panelDon.setMaximumSize(new Dimension(1100, 110));
        panelDon.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelDon.setBackground(Color.WHITE);

        // Icon bàn
        ImageIcon iconBan = new ImageIcon("src/images/App/tabbleddb.png");
        Image img = iconBan.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(img));
        lblIcon.setBounds(15, 10, 90, 90); // nhớ cập nhật kích thước
        panelDon.add(lblIcon);

        // Thông tin đơn
        Font fontLon = new Font("Arial", Font.BOLD, 16);
        Font fontThuong = new Font("Arial", Font.PLAIN, 15);

        JLabel lblMaDon = new JLabel("Mã đơn: " + maDon);
        lblMaDon.setFont(fontLon);
        lblMaDon.setBounds(130, 10, 300, 25);
        panelDon.add(lblMaDon);

        JLabel lblSoBan = new JLabel("Số bàn: " + soBan);
        lblSoBan.setFont(fontThuong);
        lblSoBan.setBounds(130, 40, 150, 25);
        panelDon.add(lblSoBan);

        JLabel lblSoMon = new JLabel("Số món: " + soMon);
        lblSoMon.setFont(fontThuong);
        lblSoMon.setBounds(130, 65, 150, 25);
        panelDon.add(lblSoMon);

        JLabel lblGioDen = new JLabel("Giờ đến: " + gioDen);
        lblGioDen.setFont(fontThuong);
        lblGioDen.setBounds(290, 40, 200, 25);
        panelDon.add(lblGioDen);

        JLabel lblSDT = new JLabel("Điện thoại: " + soDT);
        lblSDT.setFont(fontThuong);
        lblSDT.setBounds(290, 65, 200, 25);
        panelDon.add(lblSDT);
//        panelDon.setBackground(Color.red);
   
        

        return panelDon;
    }

}
