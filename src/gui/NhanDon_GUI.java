package gui;

import javax.swing.JDialog;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import java.util.*;

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
import java.sql.Timestamp;
import java.time.LocalDateTime;


import com.toedter.calendar.JDateChooser;

import dao.NhanDon_DAO;
//import entities.DonDatBanViewa;

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
	private JPanel panel_DSDon;

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
		List<Map<String, Object>> donList = NhanDon_DAO.getAllDon();

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
		
		panel_DSDon = new JPanel();
		panel_DSDon.setLayout(null);
		panel_DSDon.setBounds(320, 160, 1200, 600); 
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
		
		JDC_ngaychon = new JDateChooser();
        
        JDC_ngaychon.setBounds(230, 0, 200, 30);
        JDC_ngaychon.setDate(new Date());
        JDC_ngaychon.setMinSelectableDate(new java.util.Date());
        panel_TimKiem.add(JDC_ngaychon);
        

		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(0, 0, 0));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTim.setBounds(520, 0, 80, 30);
		panel_TimKiem.add(btnTim);
		


        
//        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        JPanel panelRight = new JPanel();
        panelRight.setBounds(650, 110, 500, 500);
		panelRight.setBackground(Color.LIGHT_GRAY);
		Box box2 = Box.createVerticalBox();
		Box br1,br2,br3,br4,br5,br6,br7,br8,br9;
		Box boxHuyDon_GiaHan,boxNhanBan;
		JButton btnHuyDon,btnGiaHan,btnNhanBan;
		JLabel lblTenKH,lblSoDT,lblSoBan,lblSoMon,lblThoiGianDat,lblThoiGianNhan,lblTienCoc,lblNhanVien;
		
		
		
		box2.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		box2.add(br1 = Box.createHorizontalBox());
		br1.add(new JLabel("Thông tin đặt bàn") {{
		    setFont(new Font("SansSerif", Font.BOLD, 22));
		}});
		box2.add(Box.createVerticalStrut(30));

		// Dữ liệu
		Font fontLabel = new Font("SansSerif", Font.BOLD, 16);
		Font fontValue = new Font("SansSerif", Font.PLAIN, 16);

		String[][] thongTin = {
		    {"Tên khách hàng:", "ABC"},
		    {"Số điện thoại:", "123"},
		    {"Số bàn:", "-1"},
		    {"Số món:", "-1"},
		    {"Thời gian đặt:", "25h"},
		    {"Thời gian nhận:", "25h"},
		    {"Tiền cọc:", "-1"},
		    {"Nhân viên thực hiện:", "AAA"}
		};

		JLabel[] lblValues = new JLabel[thongTin.length];

		for (int i = 0; i < thongTin.length; i++) {
		    Box row = Box.createHorizontalBox();
		    row.setMaximumSize(new Dimension(400, 30));
		    box2.add(row);
		    box2.add(Box.createVerticalStrut(12));

		    JLabel label = new JLabel(thongTin[i][0]);
		    label.setFont(fontLabel);
		    lblValues[i] = new JLabel(thongTin[i][1]);
		    lblValues[i].setFont(fontValue);

		    row.add(label);
		    row.add(Box.createHorizontalGlue());
		    row.add(lblValues[i]);

		    // Lưu lại để set về sau nếu cần
		    switch (i) {
		        case 0 -> lblTenKH = lblValues[i];
		        case 1 -> lblSoDT = lblValues[i];
		        case 2 -> lblSoBan = lblValues[i];
		        case 3 -> lblSoMon = lblValues[i];
		        case 4 -> lblThoiGianDat = lblValues[i];
		        case 5 -> lblThoiGianNhan = lblValues[i];
		        case 6 -> lblTienCoc = lblValues[i];
		        case 7 -> lblNhanVien = lblValues[i];
		    }
		}

		// Nút
		box2.add(Box.createVerticalStrut(30));
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

//      aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		
		JPanel panelDanhSach = new JPanel();
        panelDanhSach.setLayout(new BoxLayout(panelDanhSach, BoxLayout.Y_AXIS));
        panelDanhSach.setBackground(Color.WHITE);

        for (int i = 0; i < donList.size(); i++) {
        	Map<String, Object> don = donList.get(i);
        	
        	String maDDB = (String) don.get("maDDB");
            int soBan = (int) don.get("soBan");
            int soMon = (int) don.get("soMon");
            double tienCoc = (double) don.get("tienCoc");
            String tenKH = (String) don.get("tenKH");
            String soDT = (String) don.get("soDienThoai");
            Timestamp tgNhan = (Timestamp) don.get("thoiGianNhan");
            String maNV = (String) don.get("maNV");

            LocalDateTime thoiGian = tgNhan.toLocalDateTime();
            String thoiGianNhan = String.format("%02dh%02d", thoiGian.getHour(), thoiGian.getMinute());

            JPanel panelDon = taoPanelDon(maDDB, soBan, soMon, tienCoc, tenKH, thoiGianNhan, soDT);

            panelDon.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelDon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	JLabel[] lblThongTin = new JLabel[8];
                	panelRight.removeAll();
                	Box box2 = taoBoxThongTinDatBan(don,lblThongTin,donList,panelRight,panelDanhSach);

                	panelRight.add(box2);
                	panelRight.revalidate();
                	panelRight.repaint();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    panelDon.setBackground(new Color(230, 240, 255));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    panelDon.setBackground(Color.WHITE);
                }
            });

            panelDanhSach.add(panelDon);
        }
//        panelDanhSach.setBackground(Color.blue);
        

       
        JScrollPane scrollPane = new JScrollPane(panelDanhSach);
        scrollPane.setBounds(0, 110, 600, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel_DSDon.add(scrollPane);
		
        
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String soDienThoai = txtSoDienThoai.getText().trim();

                List<Map<String, Object>> dsKetQua;

                //Nếu 1 -> lấy toàn bộ đơn
                if (soDienThoai.equals("1")) {
                    dsKetQua = NhanDon_DAO.getAllDon();
                    System.err.println(dsKetQua.size());
                } else {
                    dsKetQua = NhanDon_DAO.timDonTheoSoDienThoai(soDienThoai);
                    System.err.println(soDienThoai);
                }

                panelDanhSach.removeAll();

                if (dsKetQua == null || dsKetQua.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy đơn đặt bàn nào.");
                } else {
                	for (Map<String, Object> don : dsKetQua) {
                	    String maDDB = (String) don.get("maDDB");
                	    int soBan = (int) don.get("soBan");
                	    int soMon = (int) don.get("soMon");
                	    double tienCoc = (double) don.get("tienCoc");
                	    String tenKH = (String) don.get("tenKH");
                	    String soDT = (String) don.get("soDienThoai");

                	    Timestamp tgNhan = (Timestamp) don.get("thoiGianNhan");
                	    String thoiGianNhan = "";
                	    if (tgNhan != null) {
                	        LocalDateTime ldt = tgNhan.toLocalDateTime();
                	        thoiGianNhan = String.format("%02dh%02d", ldt.getHour(), ldt.getMinute());
                	    }

                	    JPanel panelDon = taoPanelDon(maDDB, soBan, soMon, tienCoc, tenKH, thoiGianNhan, soDT);
                	    panelDon.addMouseListener(new MouseAdapter() {
	                        @Override
	                        public void mouseClicked(MouseEvent e) {
	                            JLabel[] lblThongTin = new JLabel[8];
	                            panelRight.removeAll();
	                            Box box2 = taoBoxThongTinDatBan(don, lblThongTin, donList, panelRight,panelDanhSach);

	                            panelRight.add(box2);
	                            panelRight.revalidate();
	                            panelRight.repaint();
	                        }

	                        @Override
	                        public void mouseEntered(MouseEvent e) {
	                            panelDon.setBackground(new Color(230, 240, 255));
	                        }

	                        @Override
	                        public void mouseExited(MouseEvent e) {
	                            panelDon.setBackground(Color.WHITE);
	                        }
	                    });

	                    panelDanhSach.add(panelDon);

                	    panelDanhSach.add(panelDon);
                	}

                }

                panelDanhSach.revalidate();
                panelDanhSach.repaint();
            }
        });
        
        JDC_ngaychon.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date ngayChon = JDC_ngaychon.getDate();

                if (ngayChon != null) {
                	ArrayList<Map<String, Object>> dsKetQua = NhanDon_DAO.timDonTheoNgayNhan(ngayChon);

                    // Cập nhật giao diện
                    panelDanhSach.removeAll();
                    for (Map<String, Object> don : dsKetQua) {
                	    String maDDB = (String) don.get("maDDB");
                	    int soBan = (int) don.get("soBan");
                	    int soMon = (int) don.get("soMon");
                	    double tienCoc = (double) don.get("tienCoc");
                	    String tenKH = (String) don.get("tenKH");
                	    String soDT = (String) don.get("soDienThoai");

                	    Timestamp tgNhan = (Timestamp) don.get("thoiGianNhan");
                	    String thoiGianNhan = "";
                	    if (tgNhan != null) {
                	        LocalDateTime ldt = tgNhan.toLocalDateTime();
                	        thoiGianNhan = String.format("%02dh%02d", ldt.getHour(), ldt.getMinute());
                	    }

                	    JPanel panelDon = taoPanelDon(maDDB, soBan, soMon, tienCoc, tenKH, thoiGianNhan, soDT);
                	    panelDon.addMouseListener(new MouseAdapter() {
	                        @Override
	                        public void mouseClicked(MouseEvent e) {
	                            JLabel[] lblThongTin = new JLabel[8];
	                            panelRight.removeAll();
	                            Box box2 = taoBoxThongTinDatBan(don, lblThongTin, donList, panelRight,panelDanhSach);

	                            panelRight.add(box2);
	                            panelRight.revalidate();
	                            panelRight.repaint();
	                        }

	                        @Override
	                        public void mouseEntered(MouseEvent e) {
	                            panelDon.setBackground(new Color(230, 240, 255));
	                        }

	                        @Override
	                        public void mouseExited(MouseEvent e) {
	                            panelDon.setBackground(Color.WHITE);
	                        }
	                    });

	                    panelDanhSach.add(panelDon);
                	    panelDanhSach.add(panelDon);
                	}

                    panelDanhSach.revalidate();
                    panelDanhSach.repaint();
                }
            }
        });


	}
	
	private JPanel taoPanelDon(String maDon, int soBan, int soMon, double coc, String tenKH, String gioDen, String soDT) {
        JPanel panelDon = new JPanel(null);
        panelDon.setPreferredSize(new Dimension(1200, 120));
        panelDon.setMaximumSize(new Dimension(1200, 120));
        panelDon.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelDon.setBackground(Color.WHITE);

        // Icon bàn
        ImageIcon iconBan = new ImageIcon(getClass().getResource("/images/App/tabbleddb.png"));
        
        
        Image img = iconBan.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(img));
        lblIcon.setBounds(15, 15, 90, 90); 
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
        lblSoBan.setBounds(130, 40, 100, 25);
        panelDon.add(lblSoBan);
        
        JLabel lblSoMon = new JLabel("Số món: " + soMon);
        lblSoMon.setFont(fontThuong);
        lblSoMon.setBounds(290, 40, 100, 25);
        panelDon.add(lblSoMon);

        JLabel lblCoc = new JLabel("Cọc: " + coc);
        lblCoc.setFont(fontThuong);
        lblCoc.setBounds(430, 40, 150, 25);
        panelDon.add(lblCoc);
        
        JLabel lblSDT = new JLabel("Điện thoại: " + soDT);
        lblSDT.setFont(fontThuong);
        lblSDT.setBounds(130, 65, 150, 25);
        panelDon.add(lblSDT);
        
        JLabel lblGioDen = new JLabel("Giờ đến: " + gioDen);
        lblGioDen.setFont(fontThuong);
        lblGioDen.setBounds(290, 65, 200, 25);
        panelDon.add(lblGioDen);
        

        JLabel lblTenKH = new JLabel("Tên Khách hàng: " + tenKH);
        lblTenKH.setFont(fontThuong);
        lblTenKH.setBounds(130, 90, 300, 25);
        panelDon.add(lblTenKH);
//        panelDon.setBackground(Color.red);
   
        

        return panelDon;
    }
	
	public Box taoBoxThongTinDatBan(Map<String, Object> don , JLabel[] lblThongTin,List<Map<String, Object>> donList, JPanel panelRight, JPanel panelDanhSach) {
	    Box box2 = Box.createVerticalBox();
	    box2.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

	    Font fontLabel = new Font("SansSerif", Font.BOLD, 16);
	    Font fontValue = new Font("SansSerif", Font.PLAIN, 16);

	    // Tiêu đề
	    Box br1 = Box.createHorizontalBox();
	    JLabel lblTieuDe = new JLabel("Thông tin đặt bàn");
	    lblTieuDe.setFont(new Font("SansSerif", Font.BOLD, 22));
	    br1.add(lblTieuDe);
	    box2.add(br1);
	    box2.add(Box.createVerticalStrut(30));

	    // Label và Value
	    String[] labels = {
	        "Tên khách hàng:", "Số điện thoại:", "Số bàn:", "Số món:",
	        "Thời gian đặt:", "Thời gian nhận:", "Tiền cọc:", "Nhân viên thực hiện:"
	    };

	    String tenKhachHang = (String) don.get("tenKH");
	    String soDienThoai = (String) don.get("soDienThoai");
	    int soBan = (Integer) don.get("soBan");
	    int soMon = (Integer) don.get("soMon");

	    Timestamp thoiGianDat = (Timestamp) don.get("thoiGianDat");
	    Timestamp thoiGianNhan = (Timestamp) don.get("thoiGianNhan");

	    Double tienCoc = (Double) don.get("tienCoc");
	    String maNV = (String) don.get("maNV");
	    String[] values = {
	    		tenKhachHang,
	    	    soDienThoai,
	    	    String.valueOf(soBan),
	    	    String.valueOf(soMon),
	    	    thoiGianDat != null ? thoiGianDat.toString() : "",
	    	    thoiGianNhan != null ? thoiGianNhan.toString() : "",
	    	    tienCoc != null ? String.valueOf(tienCoc) : "0",
	    	    maNV
	    };

	    for (int i = 0; i < labels.length; i++) {
	        Box row = Box.createHorizontalBox();
	        row.setMaximumSize(new Dimension(400, 30));
	        box2.add(row);
	        box2.add(Box.createVerticalStrut(12));

	        JLabel lbl = new JLabel(labels[i]);
	        lbl.setFont(fontLabel);

	        lblThongTin[i] = new JLabel(values[i]);
	        lblThongTin[i].setFont(fontValue);

	        row.add(lbl);
	        row.add(Box.createHorizontalGlue());
	        row.add(lblThongTin[i]);
	    }

	    // Nút Hủy đơn và Gia hạn
	    box2.add(Box.createVerticalStrut(30));
	    Box boxHuyDon_GiaHan = Box.createHorizontalBox();

	    JButton btnHuyDon = new JButton("HỦY ĐƠN");
	    btnHuyDon.setBackground(Color.BLACK);
	    btnHuyDon.setForeground(Color.WHITE);
	    btnHuyDon.setFont(new Font("SansSerif", Font.BOLD, 17));
	    btnHuyDon.setFocusPainted(false);
	    btnHuyDon.setPreferredSize(new Dimension(130, 60));
	    btnHuyDon.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int confirm = JOptionPane.showConfirmDialog(null, 
	                "Bạn có chắc muốn hủy đơn đặt bàn này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	            if (confirm == JOptionPane.YES_OPTION) {
	                NhanDon_DAO dao = new NhanDon_DAO();
	                boolean ketQua = dao.huyDonVaCapNhatTatCaBan((String) don.get("maDDB"));

	                
	                if (ketQua) {
	                    JOptionPane.showMessageDialog(null, "Hủy đơn thành công!");

	                    // Cập nhật lại danh sách đơn đặt bàn
	                    panelDanhSach.removeAll();
	                    List<Map<String, Object>> donListMoi = NhanDon_DAO.getAllDon();
	                    for (Map<String, Object> donMoi : donListMoi) {
	                    	LocalDateTime thoiGian = null;
		                    Object tgNhanObj = don.get("thoiGianNhan");
		                    if (tgNhanObj instanceof LocalDateTime) {
		                        thoiGian = (LocalDateTime) tgNhanObj;
		                    } else if (tgNhanObj instanceof Timestamp) {
		                        thoiGian = ((Timestamp) tgNhanObj).toLocalDateTime();
		                    }
		                    String thoiGianNhan = "";
		                    if (thoiGian != null) {
		                        thoiGianNhan = String.format("%02dh%02d", thoiGian.getHour(), thoiGian.getMinute());
		                    }

		                    JPanel panelDonMoi = taoPanelDon(
		                        (String) donMoi.get("maDDB"),
		                        (int) donMoi.get("soBan"),
		                        (int) donMoi.get("soMon"),
		                        (double) donMoi.get("tienCoc"),
		                        (String) donMoi.get("tenKH"),
		                        thoiGianNhan,
		                        (String) donMoi.get("soDienThoai")
		                    );

//	                	    panelDanhSach.add(panelDon);
	                        panelDonMoi.setAlignmentX(Component.CENTER_ALIGNMENT);
	                        panelDonMoi.addMouseListener(new MouseAdapter() {
		                        @Override
		                        public void mouseClicked(MouseEvent e) {
		                            JLabel[] lblThongTin = new JLabel[8];
		                            panelRight.removeAll();
		                            Box box2 = taoBoxThongTinDatBan(donMoi, lblThongTin, donList, panelRight,panelDanhSach);

		                            panelRight.add(box2);
		                            panelRight.revalidate();
		                            panelRight.repaint();
		                        }

		                        @Override
		                        public void mouseEntered(MouseEvent e) {
		                        	panelDonMoi.setBackground(new Color(230, 240, 255));
		                        }

		                        @Override
		                        public void mouseExited(MouseEvent e) {
		                        	panelDonMoi.setBackground(Color.WHITE);
		                        }
		                    });
	                        panelDanhSach.add(panelDonMoi);
	                    }
	                    panelDanhSach.revalidate();
	                    panelDanhSach.repaint();

	                    // Xóa thông tin chi tiết bên phải
	                    panelRight.removeAll();
	                    panelRight.revalidate();
	                    panelRight.repaint();

	                } else {
	                    JOptionPane.showMessageDialog(null, "Hủy đơn thất bại, vui lòng thử lại!");
	                }
	            }
	        }
	    });




	    JButton btnGiaHan = new JButton("GIA HẠN");
	    btnGiaHan.setBackground(Color.GRAY);
	    btnGiaHan.setForeground(Color.WHITE);
	    btnGiaHan.setFont(new Font("SansSerif", Font.BOLD, 17));
	    btnGiaHan.setFocusPainted(false);
	    btnGiaHan.setPreferredSize(new Dimension(130, 60));
	    btnGiaHan.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            JDialog dialog = new JDialog((Frame) null, "Gia hạn thời gian", true);
	            dialog.setUndecorated(true); // Bỏ viền nếu muốn giống popup
	            dialog.setLayout(new BorderLayout());

	            JPanel panel = new JPanel();
	            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	            panel.setBackground(Color.WHITE);

	            // Lấy thời gian hiện tại của đơn
	            Timestamp oldTime = (Timestamp) don.get("thoiGianNhan");
	            LocalDateTime oldDateTime = oldTime.toLocalDateTime();
	            int gioCu = oldDateTime.getHour();
	            int phutCu = oldDateTime.getMinute();

	            JLabel lblTitle = new JLabel("Gia hạn thời gian nhận bàn");
	            lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
	            lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

	            JLabel lblCurrent = new JLabel("Hiện tại: " + String.format("%02dh%02d", gioCu, phutCu));
	            lblCurrent.setFont(new Font("SansSerif", Font.PLAIN, 13));
	            lblCurrent.setAlignmentX(Component.CENTER_ALIGNMENT);

	            JComboBox<Integer> comboGio = new JComboBox<>();
	            for (int i = gioCu; i < 22; i++) comboGio.addItem(i);
	            comboGio.setSelectedItem(gioCu);

	            JComboBox<Integer> comboPhut = new JComboBox<>();
	            for (int i = 0; i < 60; i += 5) comboPhut.addItem(i);
	            comboPhut.setSelectedItem(phutCu);

	            JPanel timePanel = new JPanel();
	            timePanel.setBackground(Color.WHITE);
	            timePanel.add(new JLabel("Giờ:"));
	            timePanel.add(comboGio);
	            timePanel.add(new JLabel("Phút:"));
	            timePanel.add(comboPhut);

	            JButton btnXacNhan = new JButton("Xác nhận");
	            btnXacNhan.setAlignmentX(Component.CENTER_ALIGNMENT);
	            btnXacNhan.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    int newHour = (int) comboGio.getSelectedItem();
	                    int newMinute = (int) comboPhut.getSelectedItem();

	                    LocalDate ngay = oldDateTime.toLocalDate();
	                    LocalTime gioMoi = LocalTime.of(newHour, newMinute);
	                    LocalDateTime thoiGianMoi = LocalDateTime.of(ngay, gioMoi);

	                    NhanDon_DAO dao = new NhanDon_DAO();
	                    boolean result = dao.giaHanThoiGianNhan((String) don.get("maDDB"), thoiGianMoi);

	                    if (result) {
	                        JOptionPane.showMessageDialog(null, "Gia hạn thành công!");
	                     // Cập nhật lại danh sách đơn đặt bàn
		                    panelDanhSach.removeAll();
		                    List<Map<String, Object>> donListMoi = NhanDon_DAO.getAllDon();
		                    
		                    for (Map<String, Object> donMoi : donListMoi) {
		                    	LocalDateTime thoiGian = null;
			                    Object tgNhanObj = donMoi.get("thoiGianNhan");
			                    if (tgNhanObj instanceof LocalDateTime) {
			                        thoiGian = (LocalDateTime) tgNhanObj;
			                    } else if (tgNhanObj instanceof Timestamp) {
			                        thoiGian = ((Timestamp) tgNhanObj).toLocalDateTime();
			                    }
			                    String thoiGianNhan = "";
			                    if (thoiGian != null) {
			                        thoiGianNhan = String.format("%02dh%02d", thoiGian.getHour(), thoiGian.getMinute());
			                    }

			                    JPanel panelDonMoi = taoPanelDon(
			                        (String) donMoi.get("maDDB"),
			                        (int) donMoi.get("soBan"),
			                        (int) donMoi.get("soMon"),
			                        (double) donMoi.get("tienCoc"),
			                        (String) donMoi.get("tenKH"),
			                        thoiGianNhan,
			                        (String) donMoi.get("soDienThoai")
			                    );
//			                    System.err.println(thoiGianNhan);

//		                	    panelDanhSach.add(panelDon);
		                        panelDonMoi.setAlignmentX(Component.CENTER_ALIGNMENT);
		                        panelDonMoi.addMouseListener(new MouseAdapter() {
			                        @Override
			                        public void mouseClicked(MouseEvent e) {
			                            JLabel[] lblThongTin = new JLabel[8];
			                            panelRight.removeAll();
			                            Box box2 = taoBoxThongTinDatBan(donMoi, lblThongTin, donList, panelRight,panelDanhSach);
			                            System.err.println("Lan 2"+donMoi.get("thoiGianNhan"));
			                            panelRight.add(box2);
			                            panelRight.revalidate();
			                            panelRight.repaint();
			                        }

			                        @Override
			                        public void mouseEntered(MouseEvent e) {
			                        	panelDonMoi.setBackground(new Color(230, 240, 255));
			                        }

			                        @Override
			                        public void mouseExited(MouseEvent e) {
			                        	panelDonMoi.setBackground(Color.WHITE);
			                        }
			                    });
		                        panelDanhSach.add(panelDonMoi);
		                    }
		                    panelDanhSach.revalidate();
		                    panelDanhSach.repaint();

		                    // Xóa thông tin chi tiết bên phải
//		                    panelRight.removeAll();
//		                    panelRight.revalidate();
//		                    panelRight.repaint();
	                        dialog.dispose();
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Gia hạn thất bại.");
	                    }
	                }
	            });

	            panel.add(lblTitle);
	            panel.add(Box.createVerticalStrut(5));
	            panel.add(lblCurrent);
	            panel.add(Box.createVerticalStrut(10));
	            panel.add(timePanel);
	            panel.add(Box.createVerticalStrut(10));
	            panel.add(btnXacNhan);

	            dialog.add(panel, BorderLayout.CENTER);
	            dialog.pack();

	            // Hiển thị dialog ở ngay phía trên nút
	            Point location = btnGiaHan.getLocationOnScreen();
	            dialog.setLocation(location.x, location.y - dialog.getHeight());
	            dialog.setVisible(true);
	        }
	    });


	    boxHuyDon_GiaHan.add(btnHuyDon);
	    boxHuyDon_GiaHan.add(Box.createHorizontalGlue());
	    boxHuyDon_GiaHan.add(btnGiaHan);
	    box2.add(boxHuyDon_GiaHan);
	    box2.add(Box.createVerticalStrut(10));

	    
	    Box boxNhanBan = Box.createHorizontalBox();
	    JButton btnNhanBan = new JButton("NHẬN BÀN");
	    btnNhanBan.setBackground(Color.ORANGE);
	    btnNhanBan.setForeground(Color.WHITE);
	    btnNhanBan.setFont(new Font("SansSerif", Font.BOLD, 17));
	    btnNhanBan.setFocusPainted(false);
	    btnNhanBan.setPreferredSize(new Dimension(300, 35));
	    btnNhanBan.setMaximumSize(new Dimension(300, 35));
	    boxNhanBan.add(btnNhanBan);
	    box2.add(boxNhanBan);
	    box2.add(Box.createVerticalStrut(10));
	    btnNhanBan.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	String maDDB_NB = (String) don.get("maDDB");
	            if (maDDB_NB == null || maDDB_NB.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn trước khi nhận.");
	                return;
	            }
	            NhanDon_DAO nhanDon = new NhanDon_DAO();
	            boolean nhanBan = nhanDon.nhanBan(maDDB_NB);
	            if (nhanBan) {
	                panelDanhSach.removeAll();
	                
	                List<Map<String, Object>> donList = NhanDon_DAO.getAllDon();

	                for (Map<String, Object> don : donList) {
	                    LocalDateTime thoiGian = null;
	                    Object tgNhanObj = don.get("thoiGianNhan");
	                    if (tgNhanObj instanceof LocalDateTime) {
	                        thoiGian = (LocalDateTime) tgNhanObj;
	                    } else if (tgNhanObj instanceof Timestamp) {
	                        thoiGian = ((Timestamp) tgNhanObj).toLocalDateTime();
	                    }
	                    String thoiGianNhan = "";
	                    if (thoiGian != null) {
	                        thoiGianNhan = String.format("%02dh%02d", thoiGian.getHour(), thoiGian.getMinute());
	                    }

	                    JPanel panelDon = taoPanelDon(
	                        (String) don.get("maDDB"),
	                        (int) don.get("soBan"),
	                        (int) don.get("soMon"),
	                        (double) don.get("tienCoc"),
	                        (String) don.get("tenKH"),
	                        thoiGianNhan,
	                        (String) don.get("soDienThoai")
	                    );

	                    panelDon.setAlignmentX(Component.CENTER_ALIGNMENT);

	                    panelDon.addMouseListener(new MouseAdapter() {
	                        @Override
	                        public void mouseClicked(MouseEvent e) {
	                            JLabel[] lblThongTin = new JLabel[8];
	                            panelRight.removeAll();
	                            Box box2 = taoBoxThongTinDatBan(don, lblThongTin, donList, panelRight,panelDanhSach);

	                            panelRight.add(box2);
	                            panelRight.revalidate();
	                            panelRight.repaint();
	                        }

	                        @Override
	                        public void mouseEntered(MouseEvent e) {
	                            panelDon.setBackground(new Color(230, 240, 255));
	                        }

	                        @Override
	                        public void mouseExited(MouseEvent e) {
	                            panelDon.setBackground(Color.WHITE);
	                        }
	                    });

	                    panelDanhSach.add(panelDon);
	                }

	                panelDanhSach.revalidate();
	                panelDanhSach.repaint();
	                panelRight.removeAll();
                    panelRight.revalidate();
                    panelRight.repaint();

	                JOptionPane.showMessageDialog(null, "Nhận bàn thành công!");
	            } else {
	                JOptionPane.showMessageDialog(null, "Nhận bàn thất bại hoặc bàn chưa được đặt.");
	            }
	        }
	    });


	    return box2;
	}



    public JPanel getPanel() {
    	return this.panel_DSDon;
    }

}
