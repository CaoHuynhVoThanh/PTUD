package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import connectDB.ConnectDB;
import dao.ChiTietDonGoiMon_DAO;
import dao.DonGoiMon_DAO;
import dao.Mon_DAO;
import entities.ChiTietDonGoiMon;
import entities.DonGoiMon;
import entities.Mon;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;

public class GoiMon_GUI extends JFrame implements ActionListener, TableModelListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableDGM;
	private JTextField txtTimKiem;
	private DefaultTableModel tableModelDGM;
	ConnectDB con;
	private JLabel lblTongTien;
	private JScrollPane scrollPane_Mon;
	private ArrayList<Mon> dsMon;
	private ArrayList<Mon> dsMonHienThi;
	private JComboBox comboLoaiMon;
	private String ghiChu = "";
	private JComboBox comboBan;
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
		con = new ConnectDB();
		con.getInstance().connect();
		dsMon = Mon_DAO.getAllMon();
		dsMonHienThi = new ArrayList<>(dsMon);
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
		txtTimKiem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        timKiemMon();
		    }
		});
        txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setBounds(24, 29, 304, 45);
		pGoiMon.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		comboBan = new JComboBox();
		comboBan.setModel(new DefaultComboBoxModel(new String[] {"Mã bàn", "A1-01", "A1-02"}));
		comboBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBan.setBounds(508, 35, 111, 33);
		pGoiMon.add(comboBan);
		
		comboLoaiMon = new JComboBox();
		comboLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboLoaiMon.setModel(new DefaultComboBoxModel(new String[] {"Loại món", "Mì", "Cơm", "Sushi", "Sashimi"}));
		comboLoaiMon.setBounds(361, 35, 124, 33);
		pGoiMon.add(comboLoaiMon);
		loadComboLoaiMon();
		
		JButton btnTimKiem = new JButton();
		btnTimKiem.setBackground(new Color(255, 153, 0));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemMon();
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
		
		scrollPane_Mon = new JScrollPane();
		tabbedPane.addTab("Món ăn", null, scrollPane_Mon, null);
		scrollPane_Mon.getVerticalScrollBar().setUnitIncrement(20);  // Tốc độ cuộn khi nhấn mũi tên
		scrollPane_Mon.getVerticalScrollBar().setBlockIncrement(64); // Tốc độ cuộn khi nhấn Page Up/Dow
		
		JPanel pMonAn = new JPanel();
		pMonAn.setBackground(Color.WHITE);
		pMonAn.setLayout(null);
		pMonAn.setPreferredSize(new Dimension(600, 3000));
		scrollPane_Mon.setViewportView(pMonAn);
		capNhatHienThiMonAn();
//		int columns = 3; // Số cột trên mỗi hàng
//		int spacingX = 212; // Khoảng cách ngang
//		int spacingY = 180; // Khoảng cách dọc
//		int i=0;
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
		tableModelDGM = new DefaultTableModel(colnamesDGM, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 3; // Chỉ cho chỉnh sửa số lượng và nút xóa
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 1 -> Integer.class;
                    case 3 -> JButton.class;
                    default -> String.class;
                };
            }
		};
		tableDGM = new JTable(tableModelDGM);
		scrollPane_DGM.setViewportView(tableDGM);
		
		tableDGM.setRowHeight(30);
        tableDGM.getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor());
		 // Định dạng cột thành tiền
        tableDGM.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value instanceof Number) {
                    label.setText(String.format("%,d", ((Number) value).intValue()));
                    label.setHorizontalAlignment(JLabel.RIGHT);
                }
                return label;
            }
        });
		
		JPanel pTongTien = new JPanel();
		pTongTien.setBackground(new Color(255, 255, 255));
		pTongTien.setBounds(733, 486, 480, 177);
		pGoiMon.add(pTongTien);
		pTongTien.setLayout(null);
		
		JLabel lblMaBan = new JLabel("Mã bàn:");
		lblMaBan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaBan.setBounds(45, 18, 344, 28);
		pTongTien.add(lblMaBan);
		
		lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTongTien.setBounds(45, 56, 391, 28);
		pTongTien.add(lblTongTien);
		
		JButton btnGhiChu = new JButton("Ghi chú");
		btnGhiChu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGhiChu.setForeground(new Color(255, 255, 255));
		btnGhiChu.setBackground(new Color(153, 153, 153));
		btnGhiChu.setBounds(45, 115, 110, 40);
		pTongTien.add(btnGhiChu);
		btnGhiChu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        hienThiHopThoaiGhiChu();
		    }
		});
		
		JButton btnHuyBo = new JButton("Hủy bỏ");
		btnHuyBo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHuyBo.setForeground(new Color(255, 255, 255));
		btnHuyBo.setBackground(new Color(0, 0, 0));
		btnHuyBo.setBounds(186, 115, 110, 40);
		pTongTien.add(btnHuyBo);
		btnHuyBo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        huyBoTatCaMon();
		    }
		});
		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXacNhan.setForeground(new Color(255, 255, 255));
		btnXacNhan.setBackground(new Color(255, 153, 0));
		btnXacNhan.setBounds(326, 115, 110, 40);
		pTongTien.add(btnXacNhan);
		btnXacNhan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        xacNhanDonGoiMon();
		    }
		});
		comboLoaiMon.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        timKiemMon();
		    }
		});
		updateTongTien();
//         Set renderer & editor cho cột hủy dùng nút
        tableDGM.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        tableDGM.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), tableDGM));
		
//		
	}
	private void themMonVaoBang(Mon mon) {
		String tenSanPhamMoi = mon.getTenMon();
        boolean daTonTai = false;
        for (int i = 0; i < tableModelDGM.getRowCount(); i++) {
            String tenSanPham = tableModelDGM.getValueAt(i, 0).toString();
            if (tenSanPham.equalsIgnoreCase(tenSanPhamMoi)) {
                // Tăng số lượng
                int soLuong = (int) tableModelDGM.getValueAt(i, 1);
                soLuong++;
                tableModelDGM.setValueAt(soLuong, i, 1);
                tableModelDGM.setValueAt((soLuong *mon.getDonGia()), i, 2);
                daTonTai = true;
                break;
            }
        }
        if (!daTonTai) {
        	Object[] dataRow = {mon.getTenMon(), 1 , mon.getDonGia(), "Hủy" };
            tableModelDGM.addRow(dataRow);
        }
		updateTongTien();
	}
	private void updateTongTien() {
        int total = 0;
        for (int i = 0; i < tableModelDGM.getRowCount(); i++) {
            Object value = tableModelDGM.getValueAt(i, 2);
            if (value instanceof Number) {
                total += ((Number) value).intValue();
            }
        }
        lblTongTien.setText("Tổng tiền: " + String.format("%,d VND", total));
    }
	private void timKiemMon() {
	    String tuKhoa = txtTimKiem.getText().trim().toLowerCase();
	    String loaiMonChon = comboLoaiMon.getSelectedItem().toString();
	    
	    // Nếu ô tìm kiếm trống hoặc là placeholder và chọn "Tất cả" thì hiển thị tất cả
	    if ((tuKhoa.isEmpty() || tuKhoa.equals("nhập tên món")) && loaiMonChon.equals("Tất cả")) {
	        dsMonHienThi = new ArrayList<>(dsMon);
	    } else {
	        // Lọc danh sách món theo từ khóa và loại món
	        dsMonHienThi = new ArrayList<>();
	        for (Mon mon : dsMon) {
	            boolean khopTen = mon.getTenMon().toLowerCase().contains(tuKhoa);
	            boolean khopLoai = loaiMonChon.equals("Tất cả") || mon.getLoaiMon().equals(loaiMonChon);
	            if (khopTen && khopLoai) {
	                dsMonHienThi.add(mon);
	            } else if ((tuKhoa.isEmpty() || tuKhoa.equals("nhập tên món")) && khopLoai) {
	            	dsMonHienThi.add(mon);
	            }
	        }
	    }
    
	    capNhatHienThiMonAn();
	}

	private void capNhatHienThiMonAn() {
	    // Xóa các component cũ
	    JPanel pMonAn = (JPanel) scrollPane_Mon.getViewport().getView();
	    pMonAn.removeAll();
	    pMonAn.revalidate();
	    pMonAn.repaint();
	    
	    // Thêm các món mới theo danh sách đã lọc
	    int columns = 3;
	    int spacingX = 212;
	    int spacingY = 180;
	    int i = 0;
	    
	    for (Mon mon : dsMonHienThi) {
	        int row = i / columns;
	        int col = i % columns;
	        
	        JPanel pMon = new JPanel();
	        pMon.setLayout(null);
	        pMon.setBackground(Color.WHITE);
	        pMon.setBounds(25 + col * spacingX, 22 + row * spacingY, 180, 151);
	        pMonAn.add(pMon);

	        JLabel lblImgMon = new JLabel("");
	        lblImgMon.setBounds(38, 0, 100, 88);
	        lblImgMon.setIcon(new ImageIcon("src/images/imageMon/"+mon.getHinhAnh()));
	        pMon.add(lblImgMon);

	        JPanel pThongTinMon = new JPanel();
	        pThongTinMon.setLayout(null);
	        pThongTinMon.setBounds(0, 72, 180, 78);
	        pMon.add(pThongTinMon);
	        
	        JLabel lblGiaMon = new JLabel(mon.getDonGia()+ " VND");
	        lblGiaMon.setHorizontalAlignment(SwingConstants.CENTER);
	        lblGiaMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblGiaMon.setBounds(10, 22, 160, 18);
	        pThongTinMon.add(lblGiaMon);

	        JLabel lblTenMon = new JLabel("<html><div style='text-align: center;'>" + mon.getTenMon() + "</div></html>");
	        lblTenMon.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTenMon.setFont(new Font("Arial", Font.BOLD, 14));
	        lblTenMon.setBounds(10, 31, 160, 48);
	        pThongTinMon.add(lblTenMon);
	     
	        JButton btnThemMon = new JButton("+");
	        btnThemMon.setFont(new Font("Tahoma", Font.PLAIN, 10));
	        btnThemMon.setBackground(new Color(169, 169, 169));
	        btnThemMon.setBounds(140, 0, 40, 40);
	        pMon.add(btnThemMon);
	        
	        btnThemMon.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                themMonVaoBang(mon);
	            }
	        });
	        
	        i++;
	        
	    }
	    
	    // Cập nhật lại kích thước panel chứa món
	    int rows = (int) Math.ceil((double) dsMonHienThi.size() / columns);
	    pMonAn.setPreferredSize(new Dimension(600, 22 + rows * spacingY));
	}
	private void loadComboLoaiMon() {
	    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
	    model.addElement("Tất cả"); // Thêm option "Tất cả"
	    
	    // Lấy danh sách các loại món duy nhất từ dsMon
	    ArrayList<String> dsLoaiMon = new ArrayList<>();
	    for (Mon mon : dsMon) {
	        if (!dsLoaiMon.contains(mon.getLoaiMon())) {
	            dsLoaiMon.add(mon.getLoaiMon());
	            model.addElement(mon.getLoaiMon());
	        }
	    }
	    
	    comboLoaiMon.setModel(model);
	}
	private void huyBoTatCaMon() {
	    // Hiển thị hộp thoại xác nhận
	    int confirm = JOptionPane.showConfirmDialog(
	        this, 
	        "Bạn có chắc chắn muốn hủy tất cả món đã chọn?", 
	        "Xác nhận hủy", 
	        JOptionPane.YES_NO_OPTION
	    );
	    
	    if (confirm == JOptionPane.YES_OPTION) {
	        // Xóa tất cả các dòng trong bảng
	        tableModelDGM.setRowCount(0);
	        // Cập nhật lại tổng tiền
	        updateTongTien();
	    }
	}
	private void hienThiHopThoaiGhiChu() {
	    // Tạo JTextArea để nhập ghi chú
	    JTextArea txtGhiChu = new JTextArea(8, 25);
	    txtGhiChu.setText(ghiChu); // Hiển thị ghi chú cũ nếu có
	    txtGhiChu.setLineWrap(true);
	    txtGhiChu.setWrapStyleWord(true);
	    
	    // Tạo scroll pane cho text area
	    JScrollPane scrollPane = new JScrollPane(txtGhiChu);
	    
	    // Hiển thị hộp thoại
	    int result = JOptionPane.showConfirmDialog(
	        this,
	        scrollPane,
	        "Nhập ghi chú",
	        JOptionPane.OK_CANCEL_OPTION,
	        JOptionPane.PLAIN_MESSAGE
	    );
	    
	    // Xử lý khi nhấn OK
	    if (result == JOptionPane.OK_OPTION) {
	        ghiChu = txtGhiChu.getText().trim();
	        JOptionPane.showMessageDialog(this, "Đã lưu ghi chú thành công!");
	    }
	}
	private void xacNhanDonGoiMon() {
	    // Kiểm tra nếu không có món nào được chọn
	    if (tableModelDGM.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một món!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    LocalDate today = LocalDate.now();
	    String ngayThangNam = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

	    // Đếm số đơn đã có trong ngày
	    int soThuTu = DonGoiMon_DAO.demSoDonTrongNgay(today) + 1; // tăng thêm 1 cho đơn mới
	    
	    // Tạo mã đơn gọi món dạng GMxxxxxxxx-yyyyy
	    String maDGM = String.format("GM%s-%05d", ngayThangNam, soThuTu);
	    System.out.println(maDGM);
	    // Lấy thông tin từ giao diện
	    String maBan = comboBan.getSelectedItem().toString();
	    LocalDateTime thoiGian = LocalDateTime.now();
	    
	    // Tạo đơn gọi món
	    DonGoiMon donGoiMon = new DonGoiMon(maDGM, thoiGian, ghiChu);
	    
	    try {
	        // Lưu đơn gọi món vào CSDL
	        DonGoiMon_DAO.themDonGoiMon(donGoiMon);
	        
	        // Tạo các chi tiết đơn
	        for (int i = 0; i < tableModelDGM.getRowCount(); i++) {
	            String tenMon = tableModelDGM.getValueAt(i, 0).toString();
	            int soLuong = (int) tableModelDGM.getValueAt(i, 1);
	            
	            // Lấy mã món từ tên món (giả sử có phương thức này)
	            Mon mon = Mon_DAO.getMonTheoTen(tenMon);
	            String maMon = mon.getMaMon();
	            
	            // Tạo chi tiết đơn
	            ChiTietDonGoiMon chiTiet = new ChiTietDonGoiMon(maMon, maDGM, soLuong, 0);
	            System.out.println(maDGM);
	            ChiTietDonGoiMon_DAO.themChiTietDonGoiMon(chiTiet);
	        }
	        
	        // Thông báo thành công
	        JOptionPane.showMessageDialog(this, "Tạo đơn gọi món thành công!\nMã đơn: " + maDGM, "Thành công", JOptionPane.INFORMATION_MESSAGE);
	        
	        // Reset giao diện sau khi tạo đơn
	        tableModelDGM.setRowCount(0);
	        updateTongTien();
	        ghiChu = "";
	        
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Lỗi khi tạo đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}
	class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
	    private final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
	    private int editingRow;
	    private double donGia; // Thêm biến lưu đơn giá

	    public SpinnerEditor() {
	        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spinner.getEditor();
	        JTextField textField = editor.getTextField();
	        textField.setEditable(true);

	        spinner.addChangeListener(e -> {
	            if (tableDGM.isEditing()) {
	                int quantity = (Integer) spinner.getValue();
	                tableModelDGM.setValueAt(quantity * donGia, editingRow, 2);
	                updateTongTien();
	            }
	        });

	        textField.addActionListener(e -> stopCellEditing());
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        editingRow = row;
	        // Lấy đơn giá từ dữ liệu món ăn
	        String tenMon = tableModelDGM.getValueAt(row, 0).toString();
	        Mon mon = Mon_DAO.getMonTheoTen(tenMon); // Giả sử có phương thức này
	        if (mon != null) {
	            donGia = mon.getDonGia();
	        }
	        spinner.setValue(value);
	        return spinner;
	    }

	    // Các phương thức khác giữ nguyên
	    @Override
	    public Object getCellEditorValue() {
	        return spinner.getValue();
	    }

	    @Override
	    public boolean stopCellEditing() {
	        try {
	            spinner.commitEdit();
	            return super.stopCellEditing();
	        } catch (java.text.ParseException e) {
	            JOptionPane.showMessageDialog(tableDGM, "Giá trị không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    }

	    @Override
	    public boolean isCellEditable(EventObject e) {
	        if (e instanceof MouseEvent) {
	            return ((MouseEvent) e).getClickCount() >= 2;
	        }
	        return true;
	    }
	}

    // Renderer cho nút trong bảng
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Xóa");
            setForeground(Color.RED);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return this;
        }
    }

    // Editor cho nút trong bảng (xử lý hành động xóa)
    static class ButtonEditor extends DefaultCellEditor {
        private final JButton button = new JButton("Xóa");
        private JTable table;
        private int rowToDelete = -1;
        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button.setForeground(Color.RED);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            rowToDelete = row;
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
            // Trì hoãn xóa dòng đến khi cell editor hoàn tất
            SwingUtilities.invokeLater(() -> {
                if (rowToDelete >= 0 && rowToDelete < table.getRowCount()) {
                    ((DefaultTableModel) table.getModel()).removeRow(rowToDelete);
                    GoiMon_GUI parent = (GoiMon_GUI) SwingUtilities.getWindowAncestor(table);
                    parent.updateTongTien();
                }
            });
            return null;
        }
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}
}
