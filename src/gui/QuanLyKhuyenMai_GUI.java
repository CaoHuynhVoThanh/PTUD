package gui;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

import dao.QuanLyKhuyenMai_DAO;
import dao.QuanLyMon_DAO;
import entities.KhuyenMai;
import entities.Mon;
import java.util.HashSet;
import java.util.Set;

//import dao.Ban_DAO;
//import entities.Ban;

public class QuanLyKhuyenMai_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDateChooser JDC_ngaychon = new JDateChooser();
	private JTextField textField_nhapTenMon;
	private JTextField textField_ngayKetThuc;
	private JDateChooser JDC_NgayBatDau;
	private JDateChooser JDC_NgayKetThuc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyKhuyenMai_GUI frame = new QuanLyKhuyenMai_GUI();
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
	public QuanLyKhuyenMai_GUI() {
		ArrayList<KhuyenMai> dsKM = QuanLyKhuyenMai_DAO.getAllKhuyenMai();
	
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
		
		
		JPanel panel_dropdown = new JPanel();
		panel_dropdown.setBounds(320, 160, 900, 40);
		contentPane.add(panel_dropdown);
		panel_dropdown.setLayout(null);
		
		JComboBox<String> comboBox_loai = new JComboBox<>();
		comboBox_loai.setBounds(0, 0, 150, 35); 
		comboBox_loai.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_loai.addItem("Tất cả");
		comboBox_loai.addItem("Bạc");
		comboBox_loai.addItem("Vàng");
		comboBox_loai.addItem("Kim cương");
		
		
//		Set<String> loaiSet = new HashSet<>();
//		for (Mon mon : dsMon) {
//		    loaiSet.add(mon.getLoaiMon());
//		}
//		
//		for (String loai : loaiSet) {
//		    comboBox_loai.addItem(loai);
//		}
//		
		panel_dropdown.add(comboBox_loai);
		
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
		lblNgayKetThuc.setBounds(300, 0, 130, 35);
		lblNgayKetThuc.setFont(new Font("Arial", Font.BOLD, 20));
		JDC_ngaychon = new JDateChooser();
        JDC_ngaychon.setBounds(450, 0, 200, 35);
        JDC_ngaychon.setDate(new Date());
        JDC_ngaychon.setMinSelectableDate(new java.util.Date());
        JDC_ngaychon.setFont(new Font("Arial", Font.BOLD, 18));
		JButton btn_search = new JButton("Tìm");
		btn_search.setBounds(670, 0, 70, 35);
		btn_search.setBackground(Color.black);
		btn_search.setForeground(Color.white);
		btn_search.setFont(new Font("Arial", Font.BOLD, 18));
		panel_dropdown.add(lblNgayKetThuc);
		panel_dropdown.add(JDC_ngaychon);
		panel_dropdown.add(btn_search);
		

		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(320, 220, 1100, 450);
		panel_table.setLayout(new BorderLayout()); 
		contentPane.add(panel_table);
		
		String[] columnNames = {"Mã khuyến mãi","Tên khuyến mãi" ,"Thành viên", "Thời gian bắt đầu","Thời gian kết thúc", "Loại khuyến mãi", "Tỷ lệ(%)","Xóa", "Chỉnh sửa"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(130);
        table.getColumnModel().getColumn(5).setPreferredWidth(130);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(30);
        table.getColumnModel().getColumn(8).setPreferredWidth(30);

		JScrollPane scrollPane = new JScrollPane(table);
		panel_table.add(scrollPane, BorderLayout.SOUTH);
		table.setRowHeight(60);
			
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (KhuyenMai km : dsKM) {
		    model.addRow(new Object[] {
		    	km.getMa(),
		    	km.getTen(),
		    	km.getThanhVien(),
		    	km.getThoiGianBatDau(),
		    	km.getThoiGianKetThuc(),
		    	km.getLoai(),
		    	km.getPhanTram(),
		        "Xóa", "Sửa"
		    });
		}
		for (int i = 0; i < table.getColumnCount(); i++) {			
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); 
		}

		
		
		
		
		JButton btn_KhuyenMai = new JButton("Thêm");
		btn_KhuyenMai.setBounds(1300, 700, 100, 40); 
		btn_KhuyenMai.setFont(new Font("Arial", Font.BOLD, 20)); 
		btn_KhuyenMai.setForeground(Color.BLACK);
		btn_KhuyenMai.setBackground(new Color(255, 153, 0));
		contentPane.add(btn_KhuyenMai);
		
//		aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		
		btn_search.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Date selectedDate = JDC_ngaychon.getDate();
		        if (selectedDate != null) {
		            // Chuyển sang LocalDateTime
		            Instant instant = selectedDate.toInstant();
		            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		            LocalDateTime ngayChon = zdt.toLocalDateTime();
		            System.err.println(ngayChon);
		            
		            model.setRowCount(0);

		            ArrayList<KhuyenMai> dsKM = QuanLyKhuyenMai_DAO.timKhuyenMaiKetThucTruoc(ngayChon);
		            JButton btnXoa = new JButton();
		            
		            btnXoa.setIcon(new ImageIcon("src/images/App/iconDelete.png")); // Đường dẫn đến file icon
		            btnXoa.setToolTipText("Xóa"); // Tooltip khi di chuột
		            btnXoa.setText("Xóa");
		            btnXoa.setIcon(new ImageIcon("icon/xoa.png"));


		            for (KhuyenMai km : dsKM) {
		            	model.addRow(new Object[] {
	        		    	km.getMa(),
	        		    	km.getTen(),
	        		    	km.getThanhVien(),
	        		    	km.getThoiGianBatDau(),
	        		    	km.getThoiGianKetThuc(),
	        		    	km.getLoai(),
	        		    	km.getPhanTram(),
	        		    	btnXoa, "Sửa"
	        		    });
		            }
		            System.err.println("ok");
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày!");
		        }
		    }
		});
		

		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        int row = table.rowAtPoint(e.getPoint()); 
		        int column = table.columnAtPoint(e.getPoint());
		        if (column == 7) {
		            if (row >= 0 && row < table.getRowCount()) {
		                String maKM = (String) model.getValueAt(row, 0);
		                String tenKM = (String) model.getValueAt(row, 1);

		                int confirm = JOptionPane.showConfirmDialog(
		                        null, 
		                        "Bạn có chắc chắn muốn xóa mã khuyến mãi: " + maKM + " - " + tenKM + "?", 
		                        "Xác nhận xóa", 
		                        JOptionPane.YES_NO_OPTION, 
		                        JOptionPane.WARNING_MESSAGE);
		                
		                if (confirm == JOptionPane.YES_OPTION) {	                	
		                    System.err.println(QuanLyKhuyenMai_DAO.deleteKhuyenMai(maKM));
		                    model.removeRow(row);
		                    JOptionPane.showMessageDialog(null, "Đã xóa: " + maKM);

		                    
		                }
		            }
		        }
		        if (column == 8) {
		            if (row >= 0 && row < table.getRowCount()) {
		                // Lấy các giá trị từ bảng và ép kiểu chính xác
		                String maKM = model.getValueAt(row, 0).toString();
		                String tenKM = model.getValueAt(row, 1).toString();
		                String loaiTV = model.getValueAt(row, 2).toString(); 
		                Double soTien = (Double) model.getValueAt(row, 6);    

		                // Kiểm tra kiểu dữ liệu của ngày bắt đầu và ngày kết thúc
		                Object ngayBDObject = model.getValueAt(row, 3);
		                Object ngayKTObject = model.getValueAt(row, 4);

		                Date ngayBD = null;
		                Date ngayKT = null;

		                // Nếu ngày bắt đầu và kết thúc là LocalDateTime, chuyển đổi chúng thành Date
		                if (ngayBDObject instanceof LocalDateTime) {
		                    ngayBD = Date.from(((LocalDateTime) ngayBDObject).atZone(ZoneId.systemDefault()).toInstant());
		                } else if (ngayBDObject instanceof Date) {
		                    ngayBD = (Date) ngayBDObject;
		                }

		                if (ngayKTObject instanceof LocalDateTime) {
		                    ngayKT = Date.from(((LocalDateTime) ngayKTObject).atZone(ZoneId.systemDefault()).toInstant());
		                } else if (ngayKTObject instanceof Date) {
		                    ngayKT = (Date) ngayKTObject;
		                }

		                // Gọi hàm chỉnh sửa khuyến mãi với các tham số đã chuẩn bị
		                hienThiFormChinhSua("Chỉnh sửa khuyến mãi", maKM, tenKM, loaiTV, ngayBD, ngayKT, soTien,model,"Chỉnh sửa",comboBox_loai.getSelectedItem().toString());
		            }
		        }

		    }
		});

		

		btn_KhuyenMai.addActionListener(new ActionListener() {
//			private File selectedFile = null;
//			private JDateChooser JDC_NgayBatDau;
//			private JDateChooser JDC_NgayKetThuc;

		    @Override
		    public void actionPerformed(ActionEvent e) {	        
		    	hienThiFormChinhSua("Thêm khuyến mãi", "", "", "", null, null, 0.0, model,"Thêm",comboBox_loai.getSelectedItem().toString());

		    }
		});
	}
	
	
    public void hienThiFormChinhSua(String title, String maKM, String tenKM, String loaiTV, Date ngayBD, Date ngayKT, Double soTien, DefaultTableModel model, String currentAction, String cb) {
    	JFrame frameKhuyenMai = new JFrame(title);
        frameKhuyenMai.setSize(630, 500);
        frameKhuyenMai.setLayout(null);
        frameKhuyenMai.setLocationRelativeTo(null);

        Font fontTo = new Font("Arial", Font.BOLD, 20);

        JLabel lblKhuyenMai = new JLabel("Tên khuyến mãi:");
        lblKhuyenMai.setBounds(50, 30, 200, 30);
        lblKhuyenMai.setFont(fontTo);
        JTextField txtTenKM = new JTextField();
        txtTenKM.setBounds(250, 30, 280, 30);
        txtTenKM.setText(tenKM);
        txtTenKM.setFont(fontTo);
        
//        JLabel lblHinhThuc = new JLabel("Hình thức khuyến mãi:");
//        lblHinhThuc.setBounds(50, 80, 200, 30);
//        lblHinhThuc.setFont(fontTo);
//        JRadioButton radPhanTram = new JRadioButton("Theo % hóa đơn");
//        radPhanTram.setBounds(250, 80, 150, 30);
//        radPhanTram.setFont(fontTo);
//        radPhanTram.setBackground(Color.WHITE);
//        radPhanTram.setSelected(true);
//        JRadioButton radSoTien = new JRadioButton("Theo số tiền");
//        radSoTien.setBounds(410, 80, 120, 30);
//        radSoTien.setFont(fontTo);
//        radSoTien.setBackground(Color.WHITE);
//        ButtonGroup groupHinhThuc = new ButtonGroup();
//        groupHinhThuc.add(radPhanTram);
//        groupHinhThuc.add(radSoTien);
        
        JLabel lblSoTien = new JLabel("Tỷ lệ khuyến mãi(%):");
        lblSoTien.setBounds(50, 90, 200, 30);
        lblSoTien.setFont(fontTo);
        JTextField txtSoTien = new JTextField();
        txtSoTien.setBounds(250, 90, 280, 30);
        txtSoTien.setText(String.valueOf(soTien));
        txtSoTien.setFont(fontTo);

        JLabel lblThoiGianBatDau = new JLabel("Thời gian bắt đầu:");
        lblThoiGianBatDau.setBounds(50, 150, 200, 30);
        lblThoiGianBatDau.setFont(fontTo);
        JDC_NgayBatDau = new JDateChooser();
        JDC_NgayBatDau.setBounds(250, 150, 280, 30);
        JDC_NgayBatDau.setDate(new Date());
        JDC_NgayBatDau.setMinSelectableDate(new java.util.Date());
        JDC_NgayBatDau.setDate(ngayBD);
        JDC_NgayBatDau.setFont(fontTo);
        
        
        JLabel lblThoiGianKetThuc = new JLabel("Thời gian kết thúc:");
        lblThoiGianKetThuc.setBounds(50, 210, 200, 30);
        lblThoiGianKetThuc.setFont(fontTo);
        JDC_NgayKetThuc = new JDateChooser();
        JDC_NgayKetThuc.setBounds(250, 210, 280, 30);
        JDC_NgayKetThuc.setDate(new Date());
        JDC_NgayKetThuc.setMinSelectableDate(new java.util.Date());
        JDC_NgayKetThuc.setDate(ngayKT);
        JDC_NgayKetThuc.setFont(fontTo);
        
        
        JLabel lblLoaiThanhVien = new JLabel("Loại Thành viên:");
        lblLoaiThanhVien.setBounds(50, 270, 200, 30);
        lblLoaiThanhVien.setFont(fontTo);
        JComboBox<String> cbLoaiThanhVien = new JComboBox<>();
        cbLoaiThanhVien.addItem("Bạc");
        cbLoaiThanhVien.addItem("Vàng");
        cbLoaiThanhVien.addItem("Kim cương");
        cbLoaiThanhVien.setBounds(250, 270, 280, 30);
        cbLoaiThanhVien.setSelectedItem(loaiTV);
        cbLoaiThanhVien.setFont(fontTo);



        JButton btnChinhSua_Them = new JButton(currentAction);
        btnChinhSua_Them.setFont(fontTo);
        btnChinhSua_Them.setBounds(300, 400, 140, 30);
        btnChinhSua_Them.setForeground(Color.BLACK);
        btnChinhSua_Them.setBackground(new Color(255, 153, 0));
        btnChinhSua_Them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String tenKM = txtTenKM.getText();
                String soTienText = txtSoTien.getText();
                double soTien = 0;
                
                try {
                    soTien = Double.parseDouble(soTienText); // Chuyển đổi từ String sang double
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ!");
                    hienThiFormChinhSua("Chỉnh sửa khuyến mãi", maKM, tenKM, loaiTV, ngayBD, ngayKT, soTien,model,"Chỉnh sửa",cb);
                    return;
                }
                String loaiTV = (String) cbLoaiThanhVien.getSelectedItem();
               
                LocalDateTime ngayBatDauLocal = JDC_NgayBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime ngayKetThucLocal = JDC_NgayKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                // Chuyển LocalDateTime thành java.util.Date
                Date ngayBatDau = Date.from(ngayBatDauLocal.atZone(ZoneId.systemDefault()).toInstant());
                Date ngayKetThuc = Date.from(ngayKetThucLocal.atZone(ZoneId.systemDefault()).toInstant());

                if (ngayBatDau == null || ngayKetThuc == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu và ngày kết thúc!");
                    return;
                }
                
                KhuyenMai khuyenMai = new KhuyenMai(maKM, tenKM, soTien, ngayBatDauLocal, ngayKetThucLocal, cb, "Khuyến mãi theo % hóa đơn");

                boolean result = false;
                // Cập nhật vào CSDL
                if (currentAction.equals("Thêm")) {
                	String maKM = QuanLyKhuyenMai_DAO.sinhMaKhuyenMai();
                	khuyenMai.setMa(maKM);
                	if(xuLyLoGic(ngayBatDau, ngayKetThuc)) {
                		result = QuanLyKhuyenMai_DAO.themKhuyenMai(khuyenMai);                		
                	}else {
                		JOptionPane.showMessageDialog(null,currentAction+ " thất bại!");
                		hienThiFormChinhSua("Thêm khuyến mãi", "", "", "", null, null, 0.0, model,"Thêm",cb);
                	}
                } else if (currentAction.equals("Chỉnh sửa")) {
                	if(xuLyLoGic(ngayBatDau, ngayKetThuc)) {
                		result = QuanLyKhuyenMai_DAO.updateKhuyenMai(khuyenMai);
                	}else {   
                		JOptionPane.showMessageDialog(null,currentAction+ " thất bại!");
                		hienThiFormChinhSua("Chỉnh sửa khuyến mãi", maKM, tenKM, loaiTV, ngayBD, ngayKT, soTien,model,"Chỉnh sửa",cb);
                	}
                }
                
                if (result) {
                    
                    model.setRowCount(0);
                    ArrayList<KhuyenMai> dsKM = QuanLyKhuyenMai_DAO.getAllKhuyenMai(); 
                    for (KhuyenMai km2 : dsKM) {
		            	model.addRow(new Object[] {
	        		    	km2.getMa(),
	        		    	km2.getTen(),
	        		    	km2.getThanhVien(),
	        		    	km2.getThoiGianBatDau(),
	        		    	km2.getThoiGianKetThuc(),
	        		    	km2.getLoai(),
	        		    	km2.getPhanTram(),
	        		        "Xóa", "Sửa"
	        		    });
		            }
                    JOptionPane.showMessageDialog(null,currentAction+ " thành công!");
                }
            }
        });
        
        JButton btnHuy = new JButton("Hủy");
        btnHuy.setFont(fontTo);
        btnHuy.setBounds(450, 400, 80, 30);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setBackground(new Color(255, 0, 0));
        btnHuy.addActionListener(ev -> {
            frameKhuyenMai.dispose(); 
        });
        btnChinhSua_Them.addActionListener(ev -> {
            frameKhuyenMai.dispose(); 
        });


        
        
        frameKhuyenMai.add(lblKhuyenMai);
        frameKhuyenMai.add(txtTenKM);
        frameKhuyenMai.add(lblSoTien);
        frameKhuyenMai.add(txtSoTien);
        frameKhuyenMai.add(lblThoiGianBatDau);
        frameKhuyenMai.add(JDC_NgayBatDau);
        frameKhuyenMai.add(lblThoiGianKetThuc);
        frameKhuyenMai.add(JDC_NgayKetThuc);
        frameKhuyenMai.add(lblLoaiThanhVien);
        frameKhuyenMai.add(cbLoaiThanhVien);
        frameKhuyenMai.add(btnChinhSua_Them);
        frameKhuyenMai.add(btnHuy);
//        frameKhuyenMai.add(lblHinhThuc);
//        frameKhuyenMai.add(radPhanTram);
//        frameKhuyenMai.add(radSoTien);

        frameKhuyenMai.setVisible(true);
    }
    
    public boolean xuLyLoGic(Date ngayBatDau, Date ngayKetThuc) {
        if (ngayBatDau == null || ngayKetThuc == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn cả ngày bắt đầu và ngày kết thúc!");
            return false;
        }

        // Chuyển java.util.Date sang java.time.LocalDate (bỏ phần giờ phút giây)
        LocalDate ngayBD = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayKT = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayHienTai = LocalDate.now();

        if (ngayBD.isBefore(ngayHienTai)) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải là hôm nay hoặc sau hôm nay!");
            return false;
        }

        if (ngayKT.isBefore(ngayBD)) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau hoặc bằng ngày bắt đầu!");
            return false;
        }

        return true;
    }


}
