package gui;


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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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

import dao.QuanLyMon_DAO;
import entities.Mon;
import java.util.HashSet;
import java.util.Set;

//import dao.Ban_DAO;
//import entities.Ban;

public class QuanLyMon_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDateChooser JDC_ngaychon = new JDateChooser();
	private JTextField textField_nhapTenMon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyMon_GUI frame = new QuanLyMon_GUI();
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
	public QuanLyMon_GUI() {
		ArrayList<Mon> dsMon = QuanLyMon_DAO.getAllMon();
	
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
		
		Set<String> loaiSet = new HashSet<>();
		for (Mon mon : dsMon) {
		    loaiSet.add(mon.getLoaiMon()); // chỉ giữ loại duy nhất
		}
		
		for (String loai : loaiSet) {
		    comboBox_loai.addItem(loai);
		}
		
		panel_dropdown.add(comboBox_loai);
		

		        

		
//		JComboBox comboBox_ten = new JComboBox();
//		comboBox_ten.setBounds(190, 0, 180, 35);
//		panel_dropdown.add(comboBox_ten);
		
		textField_nhapTenMon = new JTextField("   Tên món ăn");
		textField_nhapTenMon.setBounds(410, 0, 300, 35);
		ImageIcon searchIcon = new ImageIcon("src/images/App/iconsearch.png");
		Image imageIcon = searchIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(imageIcon);
		JLabel searchLabel = new JLabel(resizedIcon);
		
		searchLabel.setBounds(710, 0, 35, 35); 
		panel_dropdown.add(textField_nhapTenMon);
		panel_dropdown.add(searchLabel);
		textField_nhapTenMon.setForeground(Color.GRAY); // Đặt màu chữ xám để giống placeholder
		
		textField_nhapTenMon.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (textField_nhapTenMon.getText().equals("   Tên món ăn")) {
		        	textField_nhapTenMon.setText("");
		        	textField_nhapTenMon.setForeground(Color.BLACK); // Đổi màu chữ về đen khi nhập
		        }
		    }
    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (textField_nhapTenMon.getText().trim().isEmpty()) {
		        	textField_nhapTenMon.setText("   Tên món ăn");
		        	textField_nhapTenMon.setForeground(Color.GRAY); // Đặt lại màu chữ xám
		        }
		    }
		});
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(293, 250, 1000, 450); // vị trí & size tùy chỉnh
		panel_table.setLayout(new BorderLayout()); // dùng layout này để bảng tự mở rộng
		contentPane.add(panel_table);
		
		String[] columnNames = {"Mã món", "Tên món ăn", "Loại","Đơn giá", "Hình ảnh", "Xóa", "Chỉnh sửa"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(30);
        table.getColumnModel().getColumn(6).setPreferredWidth(30);

		JScrollPane scrollPane = new JScrollPane(table);
		panel_table.add(scrollPane, BorderLayout.SOUTH);
		table.setRowHeight(60);
		
		
		DefaultTableCellRenderer imageRenderer = new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {
		        
		        if (value instanceof ImageIcon) {
		            JLabel label = new JLabel();
		            label.setHorizontalAlignment(SwingConstants.CENTER);
		            label.setIcon((ImageIcon) value);
		            return label;
		        }

		        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		    }
		};
		table.getColumnModel().getColumn(4).setCellRenderer(imageRenderer);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (Mon mon : dsMon) {
		    // Load ảnh
		    String path = "src/images/imageMon/" + mon.getHinhAnh();
		    ImageIcon icon = new ImageIcon(path);
		    Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		    ImageIcon monAn = new ImageIcon(image);
		    
		    // Thêm hàng vào bảng
		    model.addRow(new Object[] {
		    	mon.getMaMon(),	
		        mon.getTenMon(),
		        mon.getLoaiMon(),
		        String.format("%,.0f VNĐ", mon.getDonGia()), // format tiền
		        monAn,
		        "Xóa", "Sửa"
		    });
		}    

		for (int i = 0; i < table.getColumnCount(); i++) {
			if(i==4) {
				continue;
			}else {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		    
		}
		
		
		
		
		JButton btn_themMon = new JButton("Thêm");
		btn_themMon.setBounds(1150, 730, 100, 40); 
		btn_themMon.setFont(new Font("Arial", Font.BOLD, 20)); 
		btn_themMon.setForeground(Color.BLACK);
		btn_themMon.setBackground(new Color(255, 153, 0));
		contentPane.add(btn_themMon);
		
//		aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		
		comboBox_loai.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String loaiChon = (String) comboBox_loai.getSelectedItem();

		        ArrayList<Mon> dsMon;
		        if (loaiChon.equals("Tất cả")) {
		            dsMon = QuanLyMon_DAO.getAllMon();
		        } else {
		            dsMon = QuanLyMon_DAO.searchMonByLoai(loaiChon);
		        }

		        // Xóa dữ liệu cũ trong bảng
		        model.setRowCount(0);

		        // Load lại dữ liệu món ăn theo loại được chọn
		        for (Mon mon : dsMon) {
		            String path = "src/images/imageMon/" + mon.getHinhAnh();
		            ImageIcon icon = new ImageIcon(path);
		            Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		            ImageIcon monAn = new ImageIcon(image);

		            model.addRow(new Object[]{
		                mon.getMaMon(),
		                mon.getTenMon(),
		                mon.getLoaiMon(),
		                String.format("%,.0f VNĐ", mon.getDonGia()),
		                monAn,
		                "Xóa", "Sửa"
		            });
		        }
		    }
		});
		
		
		searchLabel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String tenMon = textField_nhapTenMon.getText().trim(); 
		        
		        if (tenMon.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên món để tìm.");
		        } else {
		            ArrayList<Mon> dsMon = QuanLyMon_DAO.searchMonByTen(tenMon); 

		            // Xóa dữ liệu cũ trong bảng
		            model.setRowCount(0);

		            // Đổ dữ liệu mới vào bảng
		            for (Mon mon : dsMon) {
		                String path = "src/images/imageMon/" + mon.getHinhAnh();
		                ImageIcon icon = new ImageIcon(path);
		                Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		                ImageIcon monAn = new ImageIcon(image);

		                model.addRow(new Object[]{
		                    mon.getMaMon(),
		                    mon.getTenMon(),
		                    mon.getLoaiMon(),
		                    String.format("%,.0f VNĐ", mon.getDonGia()),
		                    monAn,
		                    "Xóa", "Sửa"
		                });
		            }
		        }
		    }
		});

		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        int row = table.rowAtPoint(e.getPoint()); 
		        int column = table.columnAtPoint(e.getPoint());
		        if (column == 5) {
		            if (row >= 0 && row < table.getRowCount()) {
		                String maMon = (String) model.getValueAt(row, 0);
		                String tenMon = (String) model.getValueAt(row, 1);

		                int confirm = JOptionPane.showConfirmDialog(
		                        null, 
		                        "Bạn có chắc chắn muốn xóa món: " + model.getValueAt(row, 1) + "?", 
		                        "Xác nhận xóa", 
		                        JOptionPane.YES_NO_OPTION, 
		                        JOptionPane.WARNING_MESSAGE);
		                
		                if (confirm == JOptionPane.YES_OPTION) {	                	
		                    System.err.println(QuanLyMon_DAO.deleteMon(maMon));
		                    model.removeRow(row);
		                    JOptionPane.showMessageDialog(null, "Đã xóa: " + tenMon);

		                    
		                }
		            }
		        }
		        if(column == 6) {
		        	if (row >= 0 && row < table.getRowCount()) {
		        		hienThiChinhMon(dsMon,model.getValueAt(row, 0).toString(), model.getValueAt(row, 1).toString(), model.getValueAt(row, 2).toString(), model.getValueAt(row, 3).toString(), model.getValueAt(row, 4).toString());
		        	}
		        }
		    }
		});

		

		btn_themMon.addActionListener(new ActionListener() {
			private File selectedFile = null;

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Tạo Frame mới khi nhấn nút
		        JFrame frameThemMon = new JFrame("Thêm Món");
		        frameThemMon.setSize(800, 500);
		        frameThemMon.setLayout(null);
		        frameThemMon.setLocationRelativeTo(null); // Canh giữa màn hình

		        // Ví dụ: Thêm các component vào frame này
		        Font fontTo = new Font("Arial", Font.BOLD, 16); // Font to

		        JLabel lblTenMon = new JLabel("Tên món:");
		        lblTenMon.setBounds(30, 30, 100, 30);
		        lblTenMon.setFont(fontTo);
		        JTextField txtTenMon = new JTextField();
		        txtTenMon.setBounds(120, 30, 220, 30);
		        txtTenMon.setFont(fontTo);

		        JLabel lblLoaiMon = new JLabel("Loại món:");
		        lblLoaiMon.setBounds(380, 30, 100, 30);
		        lblLoaiMon.setFont(fontTo);
		        JComboBox<String> cbLoaiMon = new JComboBox<>();
		        for (String loai : loaiSet) {
		        	cbLoaiMon.addItem(loai);
				}
		        cbLoaiMon.setBounds(470, 30, 220, 30);
		        cbLoaiMon.setFont(fontTo);

		        JLabel lblGia = new JLabel("Giá:");
		        lblGia.setBounds(30, 80, 100, 30);
		        lblGia.setFont(fontTo);
		        JTextField txtGia = new JTextField();
		        txtGia.setBounds(120, 80, 220, 30);
		        txtGia.setFont(fontTo);
		        
		     // Khai báo JLabel để hiển thị ảnh
		        JLabel lblHinhAnh = new JLabel("Hình ảnh:");
		        lblHinhAnh.setBounds(30, 200, 120, 30);
		        lblHinhAnh.setFont(fontTo);
		        
		        
		        JLabel lblAnhMon = new JLabel();
		        lblAnhMon.setBounds(120, 130, 150, 150);
		        lblAnhMon.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		        // Nút chọn ảnh
		        JButton btnChonAnh = new JButton("Chọn ảnh");
		        btnChonAnh.setBounds(135, 300, 120, 30);
		        btnChonAnh.setFont(fontTo);

		        // Sự kiện chọn ảnh
		        btnChonAnh.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	JFileChooser fileChooser = new JFileChooser();
		                fileChooser.setDialogTitle("Chọn ảnh món ăn");
		                int result = fileChooser.showOpenDialog(null);
		                if (result == JFileChooser.APPROVE_OPTION) {
		                	selectedFile = fileChooser.getSelectedFile();
		                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());

		                    // Resize ảnh để hiển thị đẹp trong label
		                    Image image = icon.getImage().getScaledInstance(lblAnhMon.getWidth(), lblAnhMon.getHeight(), Image.SCALE_SMOOTH);
		                    lblAnhMon.setIcon(new ImageIcon(image));
		                }
		            }
		        });



		        JButton btnLuu = new JButton("Thêm");
		        btnLuu.setFont(fontTo);
		        btnLuu.setBounds(510, 400, 80, 30);
		        btnLuu.setForeground(Color.BLACK);
		        btnLuu.setBackground(new Color(255, 153, 0));
		        
		        btnLuu.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                String tenMon = txtTenMon.getText().trim();
		                String loaiMon = cbLoaiMon.getSelectedItem().toString();
		                String donGiaStr = txtGia.getText().trim();
		                String hinhAnh = selectedFile.getName();

		                if (tenMon.isEmpty() || donGiaStr.isEmpty() || hinhAnh.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
		                    return;
		                }

		                double donGia = 0;
		                try {
		                    donGia = Double.parseDouble(donGiaStr);
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(null, "Đơn giá phải là số.");
		                    return;
		                }

		                // Sinh mã món tự động
		                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		                StringBuilder randomPart = new StringBuilder();
		                Random rand = new Random();

		                for (int i = 0; i < 5; i++) {
		                    int index = rand.nextInt(characters.length());
		                    randomPart.append(characters.charAt(index));
		                }

		                String maMon = randomPart.toString();
		                Mon monMoi = new Mon(maMon, tenMon, loaiMon, donGia, hinhAnh);
		                boolean thanhCong = QuanLyMon_DAO.addMon(monMoi);
		                if (thanhCong) {
		                    JOptionPane.showMessageDialog(null, "Thêm món thành công!");
		                    model.setRowCount(0);
		                    ArrayList<Mon> dsMon = QuanLyMon_DAO.getAllMon();
		                    for (Mon mon : dsMon) {
				                String path = "src/images/imageMon/" + mon.getHinhAnh();
				                ImageIcon icon = new ImageIcon(path);
				                Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				                ImageIcon monAn = new ImageIcon(image);

				                model.addRow(new Object[]{
				                    mon.getMaMon(),
				                    mon.getTenMon(),
				                    mon.getLoaiMon(),
				                    String.format("%,.0f VNĐ", mon.getDonGia()),
				                    monAn,
				                    "Xóa", "Sửa"
				                });
				            }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Thêm món thất bại!");
		                }
		            }
		        });

		        
		        JButton btnHuy = new JButton("Hủy");
		        btnHuy.setFont(fontTo);
		        btnHuy.setBounds(610, 400, 80, 30);
		        btnHuy.setForeground(Color.WHITE);
		        btnHuy.setBackground(new Color(255, 0, 0));
		        btnLuu.addActionListener(ev -> {
		            String tenMon = txtTenMon.getText();
		            String gia = txtGia.getText();
		            // Bạn có thể xử lý logic lưu ở đây
		            JOptionPane.showMessageDialog(frameThemMon, "Đã lưu món: " + tenMon + " - " + gia + " VND");
		            frameThemMon.dispose(); // Đóng frame sau khi lưu
		        });
		        btnHuy.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	frameThemMon.dispose();
		                
		            }
		        });

		        // Thêm vào frame
		        frameThemMon.add(lblTenMon);
		        frameThemMon.add(txtTenMon);
		        frameThemMon.add(lblLoaiMon);
		        frameThemMon.add(cbLoaiMon);
		        frameThemMon.add(lblAnhMon);
		        frameThemMon.add(lblHinhAnh);
		        frameThemMon.add(btnChonAnh);
		        frameThemMon.add(lblGia);
		        frameThemMon.add(txtGia);
		        frameThemMon.add(btnLuu);
		        frameThemMon.add(btnHuy);

		        frameThemMon.setVisible(true);
		    }
		});
	}
	
	
    public void hienThiChinhMon(ArrayList<Mon> dsMon, String maMon, String tenMon, String loai, String donGia, String hinhAnh) {
        JFrame frameChinhMon = new JFrame("Chỉnh sửa món");
        frameChinhMon.setSize(800, 500);
        frameChinhMon.setLayout(null);
        frameChinhMon.setLocationRelativeTo(null);

        Font fontTo = new Font("Arial", Font.BOLD, 16);

        // Tên món
        JLabel lblTenMon = new JLabel("Tên món:");
        lblTenMon.setBounds(30, 30, 100, 30);
        lblTenMon.setFont(fontTo);
        JTextField txtTenMon = new JTextField();
        txtTenMon.setBounds(120, 30, 220, 30);
        txtTenMon.setText(tenMon);
        txtTenMon.setFont(fontTo);

        // Loại món
        JLabel lblLoaiMon = new JLabel("Loại món:");
        lblLoaiMon.setBounds(380, 30, 100, 30);
        lblLoaiMon.setFont(fontTo);
        JComboBox<String> cbLoaiMon = new JComboBox<>();
        Set<String> loaiSet = new HashSet<>();
		for (Mon mon : dsMon) {
		    loaiSet.add(mon.getLoaiMon()); // chỉ giữ loại duy nhất
		}
		for (String loai2 : loaiSet) {
			cbLoaiMon.addItem(loai2);
		}
        cbLoaiMon.setBounds(470, 30, 220, 30);
        cbLoaiMon.setSelectedItem(loai);
        cbLoaiMon.setFont(fontTo);

        // Giá
        JLabel lblGia = new JLabel("Giá:");
        lblGia.setBounds(30, 80, 100, 30);
        lblGia.setFont(fontTo);
        JTextField txtGia = new JTextField();
        txtGia.setBounds(120, 80, 220, 30);
        txtGia.setText(donGia+"");
        txtGia.setFont(fontTo);
        
        JLabel lblHinhAnh = new JLabel("Hình ảnh:");
        lblHinhAnh.setBounds(30, 200, 120, 30);
        lblHinhAnh.setFont(fontTo);
        
        
        JLabel lblAnhMon = new JLabel();
        lblAnhMon.setBounds(120, 130, 150, 150);
        lblAnhMon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        

        // Nút chọn ảnh
        JButton btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setBounds(135, 300, 120, 30);
        btnChonAnh.setFont(fontTo);
        System.out.println("Duong dan anh: " + hinhAnh);
        
        final File[] selectedFile = { new File(hinhAnh) };
     // Nếu đường dẫn hình ảnh không rỗng thì hiển thị ảnh luôn
        if (hinhAnh != null && !hinhAnh.isEmpty()) {
            File file = new File(hinhAnh);
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image image = icon.getImage().getScaledInstance(lblAnhMon.getWidth(), lblAnhMon.getHeight(), Image.SCALE_SMOOTH);
                lblAnhMon.setIcon(new ImageIcon(image));
            }
        }

        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn ảnh món ăn");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                	selectedFile[0] = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile[0].getAbsolutePath());

                    // Resize ảnh để hiển thị đẹp trong label
                    Image image = icon.getImage().getScaledInstance(lblAnhMon.getWidth(), lblAnhMon.getHeight(), Image.SCALE_SMOOTH);
                    lblAnhMon.setIcon(new ImageIcon(image));
                }
            }
        });
        
        JButton btnChinhSua = new JButton("Chỉnh sửa");
        btnChinhSua.setFont(fontTo);
        btnChinhSua.setBounds(500, 400, 120, 30);
        btnChinhSua.setForeground(Color.BLACK);
        btnChinhSua.setBackground(new Color(255, 153, 0));
        
        btnChinhSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenMon = txtTenMon.getText().trim();
                String loaiMon = cbLoaiMon.getSelectedItem().toString();
                String donGiaStr = txtGia.getText().trim();
                String hinhAnh = selectedFile[0].getName();

                if (tenMon.isEmpty() || donGiaStr.isEmpty() || hinhAnh.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
                    return;
                }

                double donGia = 0;
                try {
                    donGia = Double.parseDouble(donGiaStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Đơn giá phải là số.");
                    return;
                }

                Mon monMoi = new Mon(maMon, tenMon, loaiMon, donGia, hinhAnh);
                boolean thanhCong = QuanLyMon_DAO.addMon(monMoi);
                if (thanhCong) {
                    JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Chỉnh sửa thất bại!");
                }
            }
        });

        
        JButton btnHuy = new JButton("Hủy");
        btnHuy.setFont(fontTo);
        btnHuy.setBounds(630, 400, 80, 30);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setBackground(new Color(255, 0, 0));
//        btnChinhSua.addActionListener(ev -> {
//            String tenMon = txtTenMon.getText();
//            String gia = txtGia.getText();
//            // Bạn có thể xử lý logic lưu ở đây
//            JOptionPane.showMessageDialog(frameThemMon, "Đã lưu món: " + tenMon + " - " + gia + " VND");
//            frameThemMon.dispose(); // Đóng frame sau khi lưu
//        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frameChinhMon.dispose();
                
            }
        });
        
        frameChinhMon.add(lblTenMon);
        frameChinhMon.add(txtTenMon);
        frameChinhMon.add(lblLoaiMon);
        frameChinhMon.add(cbLoaiMon);
        frameChinhMon.add(lblAnhMon);
        frameChinhMon.add(lblHinhAnh);
        frameChinhMon.add(btnChonAnh);
        frameChinhMon.add(lblGia);
        frameChinhMon.add(txtGia);
        frameChinhMon.add(btnChinhSua);
        frameChinhMon.add(btnHuy);
        
        
        frameChinhMon.setVisible(true);
    }


}
