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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.Ban_DAO;
import dao.ChiTietDonDatBan_DAO;
import dao.ChiTietDonGoiMon_DAO;
import dao.DonGoiMon_DAO;
import dao.Mon_DAO;
import entities.Ban;
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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;

public class GoiMon_GUI extends JFrame{

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
	private ArrayList<Ban> dsBan;
	private JComboBox comboLoaiMon;
	private String ghiChu = "";
	private JComboBox comboBan;
	private JLabel lblMaBan;
	private ArrayList<Mon> dsDoUong;
	private JScrollPane scrollPane_DoUong;
	private JDateChooser dateChooser = new JDateChooser();
	private JTextArea txtGhiChu = new JTextArea();
	private JPanel pGoiMon;
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
		dsMon = Mon_DAO.getAllMonAn();
		dsDoUong = Mon_DAO.getAllDoUong();
		dsMonHienThi = new ArrayList<>(dsMon);
		Date today = new Date();
        dateChooser.setDate(today);
        txtGhiChu.setText(""); 
		dsBan = Ban_DAO.getAllBan(LocalDate.now());
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

		pGoiMon = new JPanel();
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
		        timKiemMonTheoTen();
		    }
		});
        txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setBounds(24, 29, 230, 45);
		pGoiMon.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		comboBan = new JComboBox();
		comboBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBan.setBounds(463, 35, 100, 33);
		pGoiMon.add(comboBan);
		loadComboBan(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		comboBan.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String maBan = comboBan.getSelectedItem().toString();
		        if (!maBan.equals("Chọn bàn")) {
		            updateMaBan();
		            loadDonGoiMonTheoBan(maBan);
		        } else {
		        	tableModelDGM.setRowCount(0);
		        }
		    }
		});
		comboLoaiMon = new JComboBox();
		comboLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboLoaiMon.setModel(new DefaultComboBoxModel(new String[] {"Loại món", "Mì", "Cơm", "Sushi", "Sashimi"}));
		comboLoaiMon.setBounds(353, 35, 100, 33);
		pGoiMon.add(comboLoaiMon);
		loadComboLoaiMon();
		
		JButton btnTimKiem = new JButton();
		btnTimKiem.setBackground(new Color(255, 153, 0));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemMonTheoTen();
			}
		});
		btnTimKiem.setBounds(264, 29, 50, 45);
		ImageIcon iconTim = new ImageIcon("src\\images\\App\\iconSearch.png");
		Image imgTim = iconTim.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		btnTimKiem.setIcon(new ImageIcon(imgTim));
		btnTimKiem.setFocusPainted(false);
		pGoiMon.add(btnTimKiem);
		
		
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(573, 35, 127, 33);
        dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateChooser.setMinSelectableDate(today);
        pGoiMon.add(dateChooser);
        dateChooser.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            	Date selectedDate = dateChooser.getDate();
            	if (selectedDate!= null) {
            		loadComboBan(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            	}
            	
            }
        });
        
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
		capNhatHienThi(scrollPane_Mon, dsMonHienThi);
		
		// 1. Tạo scrollPane cho đồ uống
		scrollPane_DoUong = new JScrollPane();
		scrollPane_DoUong.setName("ScrollPane Đồ Uống"); // Đặt tên để debug
		scrollPane_DoUong.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane_DoUong.getVerticalScrollBar().setBlockIncrement(64);

		// 2. Tạo panel chứa đồ uống
		JPanel pDoUong = new JPanel();
		pDoUong.setName("Panel Đồ Uống"); // Đặt tên để debug
		pDoUong.setBackground(Color.WHITE);
		pDoUong.setLayout(null);
		pDoUong.setPreferredSize(new Dimension(600, calculatePanelHeight(dsDoUong.size())));
		scrollPane_DoUong.setViewportView(pDoUong);

		// 3. Thêm vào tabbedPane SAU KHI đã thiết lập xong
		tabbedPane.addTab("Đồ uống", null, scrollPane_DoUong, null);

		// 4. Cập nhật hiển thị ngay lập tức
		capNhatHienThi(scrollPane_DoUong, dsDoUong);
		
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
		
		lblMaBan = new JLabel("Mã bàn:");
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
		        timKiemMonTheoLoaiMon();
		    }
		});
		updateTongTien();
//         Set renderer & editor cho cột hủy dùng nút
        tableDGM.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        tableDGM.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), tableDGM));
        
	     tableDGM.getTableHeader().setReorderingAllowed(false);
	     tableDGM.getTableHeader().setResizingAllowed(false);
     // Điều chỉnh kích thước cột
        tableDGM.getColumnModel().getColumn(0).setPreferredWidth(250); // Cột Tên món ăn
        tableDGM.getColumnModel().getColumn(1).setPreferredWidth(50);  // Cột SL
        tableDGM.getColumnModel().getColumn(2).setPreferredWidth(150); // Cột Thành tiền
        tableDGM.getColumnModel().getColumn(3).setPreferredWidth(50);  // Cột Hủy

        // Tăng kích thước chữ cho toàn bộ bảng
        tableDGM.setFont(new Font("Arial", Font.PLAIN, 18));
        tableDGM.setRowHeight(30);

        // Tăng kích thước chữ cho header
        JTableHeader header = tableDGM.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
		
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
	private void timKiemMonTheoTen() {
	    String tuKhoa = txtTimKiem.getText().trim().toLowerCase();
	    
	    if (tuKhoa.isEmpty() || tuKhoa.equals("nhập tên món")) {
	        dsMonHienThi = new ArrayList<>(dsMon);
	    } else {
	        dsMonHienThi = new ArrayList<>();
	        for (Mon mon : dsMon) {
	            if (mon.getTenMon().toLowerCase().contains(tuKhoa)) {
	                dsMonHienThi.add(mon);
	            }
	        }
	    }
    
	    capNhatHienThi(scrollPane_Mon, dsMonHienThi);
	}
	private void timKiemMonTheoLoaiMon() {
		String loaiMonChon = comboLoaiMon.getSelectedItem().toString();
		 if (loaiMonChon.equals("Tất cả")) {
			 dsMonHienThi = new ArrayList<>(dsMon);
		 } else {
			 dsMonHienThi = new ArrayList<>();
			 for (Mon mon : dsMon) {
				 if (mon.getLoaiMon().equals(loaiMonChon)) {
					 dsMonHienThi.add(mon);
				 }
			 }
		 }
		 capNhatHienThi(scrollPane_Mon, dsMonHienThi);
	}
	private int calculatePanelHeight(int itemCount) {
	    int columns = 3;
	    int spacingY = 180;
	    int rows = (int) Math.ceil((double) itemCount / columns);
	    return 22 + rows * spacingY;
	}
	private void capNhatHienThi(JScrollPane scrollPane, ArrayList<Mon> danhSachMon) {
	    // Kiểm tra dữ liệu đầu vào
	    if (scrollPane == null || danhSachMon == null) {
	        System.err.println("Lỗi: ScrollPane hoặc danh sách món null");
	        return;
	    }

	    // Lấy panel chứa từ scrollPane
	    JPanel panel = (JPanel) scrollPane.getViewport().getView();
	    if (panel == null) {
	        panel = new JPanel();
	        panel.setBackground(Color.WHITE);
	        panel.setLayout(null);
	        scrollPane.setViewportView(panel);
	    }

	    // Xóa nội dung cũ
	    panel.removeAll();
	    
	    // Thiết lập các thông số hiển thị (giữ nguyên như code cũ)
	    final int COLUMNS = 3;
	    final int ITEM_WIDTH = 180;
	    final int ITEM_HEIGHT = 151;
	    final int HORIZONTAL_SPACING = 212;
	    final int VERTICAL_SPACING = 180;
	    final int START_X = 25;
	    final int START_Y = 22;
	    
	    int x = START_X;
	    int y = START_Y;
	    int itemCount = 0;

	    // Hiển thị từng món (giữ nguyên giao diện pMon như code cũ)
	    for (Mon mon : danhSachMon) {
	        // Tạo panel cho mỗi món (giống với pMon cũ)
	        JPanel pMon = new JPanel();
	        pMon.setLayout(null);
	        pMon.setBackground(Color.WHITE);
	        pMon.setBounds(x, y, ITEM_WIDTH, ITEM_HEIGHT);
	        panel.add(pMon);

	        // Hiển thị hình ảnh (giống code cũ)
	        JLabel lblImgMon = new JLabel("");
	        lblImgMon.setBounds(38, 0, 100, 88);
	        try {
	            ImageIcon originalIcon = new ImageIcon("src/images/imageMon/" + mon.getHinhAnh());
	            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 88, Image.SCALE_SMOOTH);
	            lblImgMon.setIcon(new ImageIcon(scaledImage));
	        } catch (Exception e) {
	            // Fallback nếu không load được ảnh
	            lblImgMon.setIcon(new ImageIcon("src/images/imageMon/default.png"));
	        }
	        pMon.add(lblImgMon);
	        
	        // Panel thông tin món (giống code cũ)
	        JPanel pThongTinMon = new JPanel();
	        pThongTinMon.setLayout(null);
	        pThongTinMon.setBounds(0, 72, ITEM_WIDTH, 78);
	        pMon.add(pThongTinMon);
	        
	        // Hiển thị giá (giống code cũ)
	        DecimalFormat df = new DecimalFormat("#,##0");
	        JLabel lblGiaMon = new JLabel(df.format(mon.getDonGia()) + " VND");
	        lblGiaMon.setHorizontalAlignment(SwingConstants.CENTER);
	        lblGiaMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblGiaMon.setBounds(10, 22, ITEM_WIDTH - 20, 18);
	        pThongTinMon.add(lblGiaMon);

	        // Hiển thị tên món (giống code cũ)
	        JLabel lblTenMon = new JLabel("<html><div style='text-align: center;'>" + mon.getTenMon() + "</div></html>");
	        lblTenMon.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTenMon.setFont(new Font("Arial", Font.BOLD, 14));
	        lblTenMon.setBounds(10, 31, ITEM_WIDTH - 20, 48);
	        pThongTinMon.add(lblTenMon);
	     
	        ImageIcon originalIcon = new ImageIcon("src/images/App/iconAdd.png");

	     // Resize icon nhỏ hơn so với button (ví dụ: 24x24 pixel)
	        Image scaledImage = originalIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	        ImageIcon scaledIcon = new ImageIcon(scaledImage);

	        JButton btnThemMon = new JButton();
	        btnThemMon.setIcon(scaledIcon); // Gán icon đã resize
	        btnThemMon.setText(""); // Xóa text nếu chỉ muốn hiển thị icon
	        btnThemMon.setToolTipText("Thêm món"); // Tooltip nếu cần
	        btnThemMon.setBackground(Color.WHITE);
	        btnThemMon.setOpaque(true);
	        btnThemMon.setBorderPainted(false); // Ẩn viền nút
	        btnThemMon.setBounds(140, 0, 40, 40);

	        btnThemMon.addActionListener(e -> themMonVaoBang(mon));
	        pMon.add(btnThemMon);

	        itemCount++;
	        
	        // Tính toán vị trí món tiếp theo
	        if (itemCount % COLUMNS == 0) {
	            x = START_X;
	            y += VERTICAL_SPACING;
	        } else {
	            x += HORIZONTAL_SPACING;
	        }
	    }

	    // Tính toán kích thước panel chứa
	    int rows = (int) Math.ceil((double) danhSachMon.size() / COLUMNS);
	    int panelHeight = START_Y + (rows * VERTICAL_SPACING);
	    panel.setPreferredSize(new Dimension(600, panelHeight));

	    // Cập nhật giao diện
	    panel.revalidate();
	    panel.repaint();
	    scrollPane.revalidate();
	    scrollPane.repaint();

	    System.out.println("Đã cập nhật hiển thị: " + danhSachMon.size() + " món");
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
	private void loadComboBan(LocalDate ngayChon) {
		dsBan = Ban_DAO.getAllBan(ngayChon);
	    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
	    model.addElement("Chọn bàn");
	    ArrayList<String> dsBanPhucVu = new ArrayList<>();
	    for (Ban ban: dsBan) {
	        if (ban.getTinhTrang() == 2 || ban.getTinhTrang() == 3) {
	        	dsBanPhucVu.add(ban.getMaBan());
	        	model.addElement(ban.getMaBan());
	        }
	    }
	    
	    comboBan.setModel(model);
	}
	private void huyBoTatCaMon() {
		LocalDate ngayChon = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    String maBan = comboBan.getSelectedItem().toString();
	    String maDGM = ChiTietDonDatBan_DAO.getMaDGMTheoNgayVaBan(ngayChon, maBan);
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
	        capNhatChiTietGoiMon(maDGM);
	    }
	}
	private void hienThiHopThoaiGhiChu() {
	    // Tạo JTextArea để nhập ghi chú
	    txtGhiChu = new JTextArea(8, 25);
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
	    if (comboBan.getSelectedItem().toString().equalsIgnoreCase("Chọn bàn")) {
	        JOptionPane.showMessageDialog(this, "Bàn chưa được chọn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    LocalDate ngayChon = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    String maBan = comboBan.getSelectedItem().toString();
	    String maDGM = ChiTietDonDatBan_DAO.getMaDGMTheoNgayVaBan(ngayChon, maBan);
	    System.out.println(maDGM);
	    if (maDGM == null) {
	        if (tableModelDGM.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một món!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        String ngayThangNam = ngayChon.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	        int soThuTu = DonGoiMon_DAO.demSoDonTrongNgay(ngayChon) + 1;
	        String maDGMMoi = String.format("GM%s%05d", ngayThangNam, soThuTu);
	        LocalDateTime thoiGian = LocalDateTime.now();
	        
	        // 1. First create the DonGoiMon
	        DonGoiMon donGoiMon = new DonGoiMon(maDGMMoi, thoiGian, ghiChu);
	        try {
	            DonGoiMon_DAO.themDonGoiMon(donGoiMon);
	            
	            // 2. Then update ChiTietDonDatBan with the new maDGM
	            ChiTietDonDatBan_DAO.capNhatMaDGMTheoNgayVaBan(ngayChon, maBan, maDGMMoi);
	            
	            // 3. Now add the order details
	            for (int i = 0; i < tableModelDGM.getRowCount(); i++) {
	                String tenMon = tableModelDGM.getValueAt(i, 0).toString();
	                int soLuong = (int) tableModelDGM.getValueAt(i, 1);
	                
	                Mon mon = Mon_DAO.getMonTheoTen(tenMon);
	                String maMon = mon.getMaMon();
	                
	                ChiTietDonGoiMon chiTiet = new ChiTietDonGoiMon(maMon, maDGMMoi, soLuong, 0);
	                ChiTietDonGoiMon_DAO.themChiTietDonGoiMon(chiTiet);
	            }
	            
	            JOptionPane.showMessageDialog(this, "Tạo đơn gọi món thành công!\nMã đơn: " + maDGMMoi, 
	                "Thành công", JOptionPane.INFORMATION_MESSAGE);
	            
	            tableModelDGM.setRowCount(0);
	            updateTongTien();
	            ghiChu = txtGhiChu.getText().trim();;
	            
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Lỗi khi tạo đơn: " + ex.getMessage(), 
	                "Lỗi", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
	    } else {
	        try {	            
	            capNhatChiTietGoiMon(maDGM);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật đơn: " + ex.getMessage(), 
	                "Lỗi", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
	    }

	    comboBan.setEnabled(true);
	    comboBan.setSelectedItem("Chọn bàn");
	}
	private void capNhatChiTietGoiMon(String maDGM) {
		System.out.println("Có cập nhật đơn gọi món" + maDGM);
		ChiTietDonGoiMon_DAO.xoaChiTietTheoMaDGM(maDGM);
        // 3. Thêm lại các chi tiết mới từ tableModelDGM
        for (int i = 0; i < tableModelDGM.getRowCount(); i++) {
            String tenMon = tableModelDGM.getValueAt(i, 0).toString();
            int soLuong = (int) tableModelDGM.getValueAt(i, 1);

            Mon mon = Mon_DAO.getMonTheoTen(tenMon);
            String maMon = mon.getMaMon();

            ChiTietDonGoiMon chiTiet = new ChiTietDonGoiMon(maMon, maDGM, soLuong, 0);
            ChiTietDonGoiMon_DAO.themChiTietDonGoiMon(chiTiet);
        }

        JOptionPane.showMessageDialog(this, "Cập nhật đơn gọi món thành công!", 
            "Thành công", JOptionPane.INFORMATION_MESSAGE);

        tableModelDGM.setRowCount(0);
        updateTongTien();
        ghiChu = txtGhiChu.getText().trim();
	}
 	private void updateMaBan() {
		String maBan = comboBan.getSelectedItem().toString();
		String txtMBan = "Mã bàn:";
		if (!maBan.equalsIgnoreCase("Chọn bàn")) {
			txtMBan = "Mã bàn: " + maBan;
		}
		lblMaBan.setText(txtMBan);
	}
	public void setSelectedBan(String maBan) {    
	    comboBan.setSelectedItem(maBan);;
	    comboBan.setEnabled(false);
	    comboBan.setBackground(new Color(240, 240, 240));
	    // Cập nhật label hiển thị
	    lblMaBan.setText("Mã bàn: " + maBan);
	}
	private void loadDonGoiMonTheoBan(String maBan) {
		tableModelDGM.setRowCount(0);
		LocalDate ngayChon = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String maDGM = ChiTietDonDatBan_DAO.getMaDGMTheoNgayVaBan(ngayChon, maBan);
		if (maDGM == null) {
			System.out.println("DGM = null");
			return;
		}
	    try {
	        // Xóa dữ liệu cũ trong bảng
	        tableModelDGM.setRowCount(0);
	        
	        // Lấy danh sách đơn gọi món của bàn này
	        ArrayList<DonGoiMon> dsDonGoiMon = DonGoiMon_DAO.getDonGoiMonTheoBan(maBan);
	        
	        if (dsDonGoiMon.isEmpty()) {
	            return;
	        }
	        
	        // Duyệt qua các đơn gọi món và thêm vào bảng
	        for (DonGoiMon don : dsDonGoiMon) {
	            // Lấy chi tiết đơn gọi món
	            ArrayList<ChiTietDonGoiMon> dsChiTiet = ChiTietDonGoiMon_DAO.getChiTietDonGoiMonTheoMaDGM(don.getMaDGM());
	            
	            for (ChiTietDonGoiMon ct : dsChiTiet) {
	                // Lấy thông tin món
	                Mon mon = Mon_DAO.getMonTheoMa(ct.getMaMon());
	                
	                if (mon != null) {
	                    // Thêm vào bảng
	                    Object[] row = {
	                        mon.getTenMon(),
	                        ct.getSoLuong(),
	                        mon.getDonGia() * ct.getSoLuong(),
	                        "Hủy"
	                    };
	                    tableModelDGM.addRow(row);
	                }
	            }
	            
	            // Cập nhật ghi chú nếu có
	            if (don.getGhiChu() != null && !don.getGhiChu().isEmpty()) {
	                ghiChu = don.getGhiChu();
	            }
	        }
	        
	        // Cập nhật tổng tiền
	        updateTongTien();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Lỗi khi tải đơn gọi món: " + e.getMessage(), 
	            "Lỗi", JOptionPane.ERROR_MESSAGE);
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
    	private final ImageIcon scaledIcon;
        public ButtonRenderer() {
        	ImageIcon originalIcon = new ImageIcon("src/images/App/iconDelete.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(scaledImage);

            setIcon(scaledIcon);
            setBackground(Color.WHITE);
            setOpaque(true);
            setBorderPainted(false);
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
        private final JButton button = new JButton();
        private JTable table;
        private int rowToDelete = -1;
        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button.addActionListener(e -> fireEditingStopped());
            ImageIcon originalIcon = new ImageIcon("src/images/App/iconDelete.png");

         // Resize icon nhỏ hơn so với button (ví dụ: 24x24 pixel)
            Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            button.setIcon(scaledIcon); // Gán icon đã resize
	        button.setBackground(Color.WHITE);
	        button.setOpaque(true);
	        button.setBorderPainted(false); // Ẩn viền nút
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            rowToDelete = row;
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
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
    public JPanel getPanel() {
    	return this.pGoiMon;
    }
}
