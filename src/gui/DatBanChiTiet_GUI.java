package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import connectDB.ConnectDB;
import dao.ChiTietDonDatBan_DAO;
import dao.ChiTietDonGoiMon_DAO;
import dao.DonDatBan_DAO;
import dao.DonGoiMon_DAO;
import dao.KhachHang_DAO;
import dao.Mon_DAO;
import entities.Ban;
import entities.ChiTietDonGoiMon;
import entities.DonGoiMon;
import entities.Mon;
import entities.NhanVien;
import gui.GoiMon_GUI.SpinnerEditor;

import javax.swing.JRadioButton;

public class DatBanChiTiet_GUI extends JDialog implements ActionListener{
	private NhanVien currenUser = new NhanVien("25000001", "Lê Vinh Quang", "quankle@gmail.com", "0987654321", "Gò Vấp", "Nhân Viên Quầy", null, true);
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tf_sdt;
	private JTextField tf_ngayNhan;
	private JTextField tf_tenkh;
	private JTable table;
	JSpinner tf_sokhach;
	JComboBox<String> combPhut;
	JComboBox<String> combGio;
	public static JButton okButton;
	JLabel lb_coc;
	ArrayList<Ban> dsbd;
	ConnectDB con;
	private JLabel lblTngSBn;
	private JLabel lblTngSGh;
	private JLabel lb_tongban;
	private JLabel lb_soghe;
	private ArrayList<Mon> dsMon = Mon_DAO.getAllMon();
	private JTable tblSelected;
	private JTable tblAllItems;
	private JLabel lblTotal;
	private JComboBox cboCategory;
	private ArrayList<String> dsMaBan;
	private JLabel lblMaBan = new JLabel("Mã bàn: ");;
	private JTextArea txtGhiChu;
	private String ghiChu = "";
	private DefaultTableModel modelSelected;
	private double tongTienCoc = 0.0;
	public static double tongMonDatTruoc=0.0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnectDB con = new ConnectDB();
			con.connect();
			DatBanChiTiet_GUI dialog = new DatBanChiTiet_GUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatBanChiTiet_GUI() {
		con = new ConnectDB();
		con.getInstance().connect();
		setBounds(400, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Số điện thoại:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(421, 125, 116, 27);
		contentPanel.add(lblNewLabel);
		
		tf_sdt = new JTextField();
		tf_sdt.setBounds(559, 127, 180, 27);
		contentPanel.add(tf_sdt);
		tf_sdt.setColumns(10);
		
		tf_ngayNhan = new JTextField();
	    tf_ngayNhan.setEditable(false);
		tf_ngayNhan.setColumns(10);
		tf_ngayNhan.setBounds(169, 75, 213, 27);
		contentPanel.add(tf_ngayNhan);
		
		JLabel lblNgyNhn = new JLabel("Ngày nhận:");
		lblNgyNhn.setForeground(Color.WHITE);
		lblNgyNhn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyNhn.setBounds(41, 73, 158, 27);
		contentPanel.add(lblNgyNhn);
		
		JLabel lblThiGianNhn = new JLabel("Thời gian nhận:");
		lblThiGianNhn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThiGianNhn.setForeground(Color.WHITE);
		lblThiGianNhn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThiGianNhn.setBounds(413, 73, 124, 27);
		contentPanel.add(lblThiGianNhn);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setForeground(Color.WHITE);
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnKhchHng.setBounds(40, 127, 132, 27);
		contentPanel.add(lblTnKhchHng);
		
		tf_tenkh = new JTextField();
		tf_tenkh.setColumns(10);
		tf_tenkh.setBounds(170, 127, 212, 27);
		contentPanel.add(tf_tenkh);
		
		JLabel lblSKhch = new JLabel("Số khách:");
		lblSKhch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSKhch.setForeground(Color.WHITE);
		lblSKhch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSKhch.setBounds(41, 181, 77, 27);
		contentPanel.add(lblSKhch);
		
		tf_sokhach = new JSpinner();
		tf_sokhach.setBounds(169, 183, 213, 27);
		contentPanel.add(tf_sokhach);
		
		JLabel lblNewLabel_1 = new JLabel("THÔNG TIN ĐẶT BÀN");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(201, 10, 414, 32);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("CHI TIẾT CÁC BÀN");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(201, 238, 414, 32);
		contentPanel.add(lblNewLabel_1_1);
		
		lblTngSBn = new JLabel("Tổng số bàn:");
		lblTngSBn.setForeground(Color.WHITE);
		lblTngSBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngSBn.setBounds(510, 280, 116, 27);
		contentPanel.add(lblTngSBn);
		
		lblTngSGh = new JLabel("Tổng số ghế:");
		lblTngSGh.setForeground(Color.WHITE);
		lblTngSGh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngSGh.setBounds(510, 312, 116, 27);
		contentPanel.add(lblTngSGh);
		
		JButton btnDatMon = new JButton("Đặt món");
		btnDatMon.setBackground(new Color(0, 0, 0));
		btnDatMon.setForeground(new Color(255, 255, 255));
		btnDatMon.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDatMon.setBounds(510, 353, 229, 37);
		contentPanel.add(btnDatMon);
		JLabel lblSMnGi = new JLabel("Số món gọi trước:");
		lblSMnGi.setForeground(Color.WHITE);
		lblSMnGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSMnGi.setBounds(510, 400, 142, 27);
		contentPanel.add(lblSMnGi);
		
		JLabel lblCnThanhTon = new JLabel("Cần cọc trước:");
		lblCnThanhTon.setForeground(Color.WHITE);
		lblCnThanhTon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCnThanhTon.setBounds(510, 437, 204, 27);
		contentPanel.add(lblCnThanhTon);
		
		lb_coc = new JLabel("0");
		lb_coc.setBackground(new Color(255, 0, 0));
		lb_coc.setForeground(new Color(255, 0, 0));
		lb_coc.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_coc.setBounds(510, 481, 204, 27);
		contentPanel.add(lb_coc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 279, 433, 229);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Mã bàn", "Loại bàn", "Phụ phí", "Tiền cọc"
			}
		));
		scrollPane.setViewportView(table);
		
		combGio = new JComboBox<>();
        for (int i = 10; i <= 21; i++) {
        	combGio.addItem(String.format("%02d", i)); 
        }

		combGio.setBounds(559, 78, 45, 21);
		contentPanel.add(combGio);
		
		JLabel lblGi = new JLabel("Giờ");
		lblGi.setHorizontalAlignment(SwingConstants.LEFT);
		lblGi.setForeground(Color.WHITE);
		lblGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGi.setBounds(610, 73, 29, 27);
		contentPanel.add(lblGi);
		
		JLabel lblPht = new JLabel("Phút");
		lblPht.setHorizontalAlignment(SwingConstants.LEFT);
		lblPht.setForeground(Color.WHITE);
		lblPht.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPht.setBounds(700, 73, 39, 27);
		contentPanel.add(lblPht);
		
        combPhut = new JComboBox<>();
        combPhut.addItem("00");
        combPhut.addItem("30");

		combPhut.setBounds(649, 78, 45, 21);
		contentPanel.add(combPhut);
		
		lb_tongban = new JLabel("0");
		lb_tongban.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tongban.setForeground(Color.WHITE);
		lb_tongban.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_tongban.setBounds(623, 280, 116, 27);
		contentPanel.add(lb_tongban);
		
		lb_soghe = new JLabel("0");
		lb_soghe.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_soghe.setForeground(Color.WHITE);
		lb_soghe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_soghe.setBounds(623, 312, 116, 27);
		contentPanel.add(lb_soghe);
		
		JLabel lb_tongban_1 = new JLabel("0");
		lb_tongban_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tongban_1.setForeground(Color.WHITE);
		lb_tongban_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_tongban_1.setBounds(635, 400, 116, 27);
		contentPanel.add(lb_tongban_1);
		
		ButtonGroup group = new ButtonGroup();
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("XÁC NHẬN");
		okButton.addActionListener((ActionListener) this);
		okButton.setBackground(new Color(255, 153, 0));
		okButton.setForeground(new Color(255, 255, 255));
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("HỦY");
		cancelButton.setBackground(new Color(0, 0, 0));
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(this);
		
		btnDatMon.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        showDatMonDialog();
		    }
		});

		
	}
	public void setDate(String s) {
		tf_ngayNhan.setText(s);
	}
	
	public ArrayList<String> getDsMaBan() {
	    return this.dsMaBan;
	}
	public boolean createDDB() {
		int sl = DonDatBan_DAO.getSLDDBHomNay()+1;
		String formattedNumber = String.format("%05d", sl);
		LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String ma = formattedDate+formattedNumber;
		
		int slkh = KhachHang_DAO.getSLKHHomNay()+1;
		formattedNumber = String.format("%04d", slkh);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		formattedDate = today.format(formatter);
		String makh = formattedDate+formattedNumber;

		String hour = (String) combGio.getSelectedItem();   
		String minute = (String) combPhut.getSelectedItem();
		String ngayDat = tf_ngayNhan.getText();
		
		String input = ngayDat+" "+hour+":"+minute;
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		int soKhach = (int) tf_sokhach.getValue();
		String tienCocText = lb_coc.getText();
		double tiencoc = 0.0;
		// Cách 1: Loại bỏ dấu phẩy trực tiếp (đơn giản, nếu bạn chắc chắn định dạng)
		try {
		    String cleanedTienCocText = tienCocText.replace(",", ""); // Xóa bỏ tất cả dấu phẩy
		    tiencoc = Double.parseDouble(cleanedTienCocText);
		    System.out.println("Tiền cọc (cách 1): " + tiencoc);
		} catch (NumberFormatException e) {
		    System.err.println("Lỗi chuyển đổi tiền cọc" + e.getMessage());
		}
		LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
		String hoten = tf_tenkh.getText();
		String sdt = tf_sdt.getText();
		System.out.println(dateTime);
		createKhachHang(makh, hoten, sdt);

		DonDatBan_DAO.insertDonDatBan(ma, null, Application.nhanvien.getMaNV(), makh, dateTime, dateTime, soKhach, tiencoc, 1);
		for (Ban x: dsbd) {
			createChiTietDDB(ma, x.getMaBan(), null);
		}
		return true;
	}
	public boolean createKhachHang(String makh, String tenKH, String sdt) {
		KhachHang_DAO.insertKhachHang(makh, tenKH, sdt, LocalDate.now());
		return true;
	}
	
	public boolean createChiTietDDB(String madon, String maban, String madgm) {
		DonDatBan_DAO.insertChiTietDonDatBan(madon, maban, madgm);
		return true;
	}
	
	private void showDatMonDialog() {
	    // Create and configure the dialog
	    JDialog dialog = new JDialog(this, "Đặt món", true);
	    dialog.setSize(1200, 700);
	    dialog.setLocationRelativeTo(null);
	    dialog.setLayout(null);

	    // Left panel with all items
	    JPanel leftPanel = new JPanel(null);
	    leftPanel.setBounds(0, 0, 600, 700);
	    leftPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

	    // Search components
	    JTextField txtSearch = new JTextField();
	    txtSearch.setBounds(20, 20, 250, 30);
	    txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    leftPanel.add(txtSearch);

	    JButton btnSearch = new JButton("Tìm kiếm");
	    btnSearch.setBounds(280, 20, 100, 30);
	    btnSearch.setBackground(new Color(255, 153, 0));
	    btnSearch.setForeground(Color.WHITE);
	    leftPanel.add(btnSearch);

	    // ComboBox for food categories
	    cboCategory = new JComboBox<>();
	    cboCategory.setBounds(430, 20, 150, 30);
	    loadComboLoaiMon();
	    leftPanel.add(cboCategory);

	    String[] colNames = {"Tên món", "Đơn giá", "Loại món", "Thêm"};
	    DefaultTableModel modelAllItems = new DefaultTableModel(colNames, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return column == 3;
	        }
	    };

	    tblAllItems = new JTable(modelAllItems);
	    JScrollPane scrollAllItems = new JScrollPane(tblAllItems);
	    scrollAllItems.setBounds(20, 70, 560, 580);
	    leftPanel.add(scrollAllItems);

	    // Right panel with selected items
	    JPanel rightPanel = new JPanel(null);
	    rightPanel.setBounds(600, 0, 600, 700);
	    rightPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

	    // Table for selected items
	    String[] colNamesSelected = {"Tên món ăn", "SL", "Thành tiền", "Hủy"};
	    modelSelected = new DefaultTableModel(colNamesSelected, 0);
	    tblSelected = new JTable(modelSelected);
	    tblSelected.setRowHeight(30);
	    
	    tblSelected.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
	    tblSelected.getColumnModel().getColumn(3).setCellEditor(
	        new DeleteButtonEditor(new JCheckBox(), modelSelected)
	    );

	    JScrollPane scrollSelected = new JScrollPane(tblSelected);
	    scrollSelected.setBounds(20, 20, 560, 500);
	    rightPanel.add(scrollSelected);

	    // Bottom panel with buttons
	    JPanel bottomPanel = new JPanel(null);
	    bottomPanel.setBounds(20, 540, 560, 120);
	    rightPanel.add(bottomPanel);

	    lblMaBan = new JLabel("Mã bàn: ");
	    lblMaBan.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblMaBan.setBounds(280, 10, 270, 30);
	    bottomPanel.add(lblMaBan); // Initialize with an empty list
	    // Update the total label bounds to not overlap
	    lblTotal = new JLabel("Tổng tiền: 0 VNĐ");
	    lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblTotal.setBounds(10, 10, 260, 30);
	    bottomPanel.add(lblTotal);
	    updateLblMaBan();
	    // Buttons
	    JButton btnNote = new JButton("Ghi chú");
	    btnNote.setBounds(10, 60, 120, 40);
	    btnNote.setBackground(new Color(153, 153, 153));
	    btnNote.setForeground(Color.WHITE);
	    btnNote.setFont(new Font("Tahoma", Font.BOLD, 14));
	    bottomPanel.add(btnNote);

	    btnNote.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        hienThiHopThoaiGhiChu();
		    }
		});
	    
	    JButton btnCancel = new JButton("Hủy bỏ");
	    btnCancel.setBounds(140, 60, 120, 40);
	    btnCancel.setBackground(Color.BLACK);
	    btnCancel.setForeground(Color.WHITE);
	    btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
	    bottomPanel.add(btnCancel);

	    btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        huyBoTatCaMon();
		    }
		});
	    
	    JButton btnConfirm = new JButton("Xác nhận");
	    btnConfirm.setBounds(270, 60, 120, 40);
	    btnConfirm.setBackground(new Color(255, 153, 0));
	    btnConfirm.setForeground(Color.WHITE);
	    btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
	    bottomPanel.add(btnConfirm);

	    btnConfirm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (modelSelected.getRowCount() > 0) {
		            JOptionPane.showMessageDialog(null, "Đã lưu danh sách món thành công!");
		            lb_coc.setText(tongTienCoc+tongMonDatTruoc+"");
		        } else {
		            JOptionPane.showMessageDialog(null, "Chưa chọn món nào!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            tongMonDatTruoc=0.0;
		        }
		        dialog.dispose();
		    }
		});
	    // Add panels to dialog
	    dialog.add(leftPanel);
	    dialog.add(rightPanel);


	    cboCategory.addActionListener(e -> {
	    	 String selectedCategory = cboCategory.getSelectedItem().toString();
	    	    DefaultTableModel model = (DefaultTableModel) tblAllItems.getModel();
	    	    model.setRowCount(0); // Clear current table

	    	    // If "Tất cả" is selected, show all items
	    	    if (selectedCategory.equals("Tất cả")) {
	    	        loadAllItems(model);
	    	        return;
	    	    }

	    	    // Filter and show items by category
	    	    for (Mon mon : dsMon) {
	    	        if (mon.getLoaiMon().equals(selectedCategory)) {
	    	            Object[] row = {
	    	                mon.getTenMon(),
	    	                mon.getDonGia(),
	    	                mon.getLoaiMon(),
	    	                "Thêm"
	    	            };
	    	            model.addRow(row);
	    	        }
	    	    }
	    });
	    
	    btnConfirm.addActionListener(e -> {
	        // Add confirm functionality
	        dialog.dispose();
	    });

	    // Load initial data
	    loadAllItems(modelAllItems);
	    tblAllItems.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
	    tblAllItems.getColumnModel().getColumn(3).setCellEditor(
	        new ButtonEditor(new JCheckBox(), tblAllItems, modelSelected)
	    );
	 // Add search functionality
	    btnSearch.addActionListener(e -> {
	        String searchText = txtSearch.getText().toLowerCase().trim();
	        DefaultTableModel model = (DefaultTableModel) tblAllItems.getModel();
	        model.setRowCount(0); // Clear current table

	        // If search text is empty, show all items
	        if (searchText.isEmpty()) {
	            loadAllItems(model);
	            return;
	        }

	        // Search and add matching items
	        for (Mon mon : dsMon) {
	            if (mon.getTenMon().toLowerCase().contains(searchText)) {
	                Object[] row = {
	                    mon.getTenMon(),
	                    mon.getDonGia(),
	                    mon.getLoaiMon(),
	                    "Thêm"
	                };
	                model.addRow(row);
	            }
	        }
	    });

	    dialog.setVisible(true);
	}
	public void setDsBan(ArrayList<Ban> ds) {
		dsbd = ds;
		int tongban =ds.size();
		int tongghe =0;
	    DefaultTableModel dtm = new DefaultTableModel(
	    new Object[]{"Mã Bàn", "Loại Bàn", "Phụ Phí", "Tiền cọc"}, 0);
	    double tongTien =0;
	    for (Ban x : ds) {
	        System.out.println(x.getMaBan()); // Kiểm tra log
	        dtm.addRow(new Object[]{x.getMaBan(), x.getLoaiBan(), x.getPhuPhi(), x.getPhiCoc()});
	        tongghe+=x.getLoaiBan();
	    }
	    lb_tongban.setText(tongban+"");
	    lb_soghe.setText(tongghe+"");
	    SpinnerNumberModel model = (SpinnerNumberModel) tf_sokhach.getModel();
	    model.setMaximum(tongghe);
	    table.setModel(dtm); 
		tongTienCoc = 0.0;
		int rowCount = table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
		    Object value = table.getValueAt(i, 3); // Cột "Tiền cọc"
		    System.out.println("hihi"+value);
		    if (value != null) {
		        try {
		            tongTienCoc += Double.parseDouble(value.toString());
		        } catch (NumberFormatException e) {
		            System.err.println("Lỗi định dạng số tại dòng " + i + ": " + value);
		        }
		    }
		}
		lb_coc.setText(tongTienCoc+"");
	}
	private void updateLblMaBan() {
	    if (dsbd == null || dsbd.isEmpty()) {
	        lblMaBan.setText("Mã bàn: ");
	        return;
	    }

	    StringBuilder maBanList = new StringBuilder("Mã bàn: ");
	    for (Ban ban : dsbd) {
	        maBanList.append(ban.getMaBan()).append(", ");
	    }

	    // Remove trailing comma and space
	    maBanList.setLength(maBanList.length() - 2);
	    lblMaBan.setText(maBanList.toString());
	}
	private void xacNhanDonGoiMon() {
	    try {
	        // Get date and time values
	        String ngayChon = tf_ngayNhan.getText();
	        String hour = (String) combGio.getSelectedItem();
	        String minute = (String) combPhut.getSelectedItem();
	        String timeStr = ngayChon + " " + hour + ":" + minute;
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        LocalDateTime thoiGian = LocalDateTime.parse(timeStr, formatter);
	        LocalDate ngayDat = thoiGian.toLocalDate();

	        // Check if there are selected items
	        if (modelSelected == null || modelSelected.getRowCount() == 0) {
	            return;
	        }

	        // Generate order ID
	        String ngayThangNam = ngayDat.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	        int soThuTu = DonGoiMon_DAO.demSoDonTrongNgay(ngayDat) + 1;
	        String maDGM = String.format("GM%s%04d", ngayThangNam, soThuTu);

	        // Create and save DonGoiMon
	        DonGoiMon donGoiMon = new DonGoiMon(maDGM, thoiGian, ghiChu);
	        DonGoiMon_DAO.themDonGoiMon(donGoiMon);

	        // Update ChiTietDonDatBan for each table
	        for (Ban ban : dsbd) {
	            ChiTietDonDatBan_DAO.capNhatMaDGMTheoNgayVaBan(ngayDat, ban.getMaBan(), maDGM);
	        }

	        // Create ChiTietDonGoiMon for each selected item
	        for (int i = 0; i < modelSelected.getRowCount(); i++) {
	            String tenMon = modelSelected.getValueAt(i, 0).toString();
	            int soLuong = Integer.parseInt(modelSelected.getValueAt(i, 1).toString());
	            
	            Mon mon = Mon_DAO.getMonTheoTen(tenMon);
	            String maMon = mon.getMaMon();

	            ChiTietDonGoiMon chiTiet = new ChiTietDonGoiMon(maMon, maDGM, soLuong, soLuong);
	            ChiTietDonGoiMon_DAO.themChiTietDonGoiMon(chiTiet);
	        }

//	        JOptionPane.showMessageDialog(this, "Tạo đơn gọi món thành công!", 
//	            "Thành công", JOptionPane.INFORMATION_MESSAGE);

	        // Clear selected items and reset
	        modelSelected.setRowCount(0);
	        updateTotal(modelSelected, lblTotal);

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Lỗi khi tạo đơn: " + ex.getMessage(), 
	            "Lỗi", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {
	    public ButtonRenderer() {
	        setOpaque(true);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        setText((value == null) ? "" : value.toString());
	        return this;
	    }
	}
	private void hienThiHopThoaiGhiChu() {
	    // Tạo JTextArea để nhập ghi chú
	    txtGhiChu = new JTextArea(8, 25);
	    txtGhiChu.setText(ghiChu ); // Hiển thị ghi chú cũ nếu có
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
	    
	    cboCategory.setModel(model);
	}
	class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable tblAllItems;
    private DefaultTableModel modelSelected;

    public ButtonEditor(JCheckBox checkBox, JTable tblAllItems, DefaultTableModel modelSelected) {
        super(checkBox);
        this.tblAllItems = tblAllItems;
        this.modelSelected = modelSelected;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            int row = tblAllItems.getSelectedRow();
            String tenMon = tblAllItems.getValueAt(row, 0).toString();
            double donGia = Double.parseDouble(tblAllItems.getValueAt(row, 1).toString());
            
            // Check if item already exists in selected table
            boolean found = false;
            for (int i = 0; i < modelSelected.getRowCount(); i++) {
                if (modelSelected.getValueAt(i, 0).toString().equals(tenMon)) {
                    // Item exists - increase quantity
                    int currentQty = Integer.parseInt(modelSelected.getValueAt(i, 1).toString());
                    modelSelected.setValueAt(currentQty + 1, i, 1);
                    modelSelected.setValueAt(donGia * (currentQty + 1), i, 2);
                    found = true;
                    break;
                }
            }
            
            // If item not found, add new row
            if (!found) {
                modelSelected.addRow(new Object[]{tenMon, 1, donGia, "Hủy"});
            }

            // Update total after adding/updating item
            updateTotal(modelSelected, lblTotal);
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}


	class DeleteButtonEditor extends DefaultCellEditor {
	    protected JButton button;
	    private String label;
	    private boolean isPushed;
	    private final DefaultTableModel model;

	    public DeleteButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
	        super(checkBox);
	        this.model = model;
	        button = new JButton();
	        button.setOpaque(true);
	        button.addActionListener(e -> fireEditingStopped());
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	        label = (value == null) ? "" : value.toString();
	        button.setText(label);
	        isPushed = true;
	        return button;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        if (isPushed) {
	            // Get the row to delete
	            int row = tblSelected.getSelectedRow();
	            if (row >= 0) {
	                model.removeRow(row);
	                updateTotal(model, lblTotal);
	            }
	        }
	        isPushed = false;
	        return label;
	    }

	    @Override
	    public boolean stopCellEditing() {
	        isPushed = false;
	        return super.stopCellEditing();
	    }
	}

	private void loadAllItems(DefaultTableModel model) {
	    for (Mon mon : dsMon) {
	        Object[] row = {
	            mon.getTenMon(),
	            mon.getDonGia(),
	            mon.getLoaiMon(),
	            "Thêm"  // Text for the button
	        };
	        model.addRow(row);
	    }
	}
	private void huyBoTatCaMon() {
        int confirm = JOptionPane.showConfirmDialog(
    	        this, 
    	        "Bạn có chắc chắn muốn hủy tất cả món đã chọn?", 
    	        "Xác nhận hủy", 
    	        JOptionPane.YES_NO_OPTION
    	    );
    	    if (confirm == JOptionPane.YES_OPTION) {
    	    	modelSelected.setRowCount(0);
    	        updateTotal(modelSelected, lblTotal);
    	    }
	}
	private void updateTotal(DefaultTableModel model, JLabel lblTotal) {
	    double total = 0;
	    for (int i = 0; i < model.getRowCount(); i++) {
	        double thanhTien = Double.parseDouble(model.getValueAt(i, 2).toString());
	        total += thanhTien;
	    }
	    tongMonDatTruoc = total;
	    lblTotal.setText(String.format("Tổng tiền: %,.0f VNĐ", total));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		if (cmd.equals("XÁC NHẬN")) {
			if (JOptionPane.showConfirmDialog(null, "Xác nhận đặt bàn không?")==JOptionPane.YES_OPTION) {
				if (createDDB()) {
					xacNhanDonGoiMon();
					JOptionPane.showMessageDialog(null, "Đặt bàn thành công!");
					DatBan_GUI.hiddenButton.doClick();
					this.dispose();
				}
			}
		}
		
		if (cmd.equals("Dùng ngay")) {
			lb_coc.setText("0.0");
		}
		if (cmd.equals("Đặt trước")) {
			double tongTien =0;
		    for (Ban x : dsbd) {
		        tongTien+=x.getPhuPhi();
		        lb_coc.setText(tongTien+"");
		    }
		}
		if (cmd.equals("Cancel")) {
			this.dispose();
		}
	}
	private String convertBanListToString() {
	    if (dsbd == null || dsbd.isEmpty()) {
	        return "";
	    }
	    StringBuilder result = new StringBuilder();
	    for (Ban ban : dsbd) {
	        result.append(ban.getMaBan()).append(", ");
	    }
	    // Remove last comma and space
	    return result.substring(0, result.length() - 2);
	}

}
