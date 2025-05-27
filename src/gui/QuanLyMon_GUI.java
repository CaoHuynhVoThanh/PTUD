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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import dao.KhuyenMai_DAO;
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
	private JPanel pQuanLyMon = new JPanel();

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

		setContentPane(pQuanLyMon);
		pQuanLyMon.setLayout(null);

//		aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		
		
		JPanel panel_dropdown = new JPanel();
		panel_dropdown.setBounds(60, 60, 900, 40);
		pQuanLyMon.add(panel_dropdown);
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
		ImageIcon searchIcon = new ImageIcon(getClass().getResource("/images/App/iconSearch.png"));
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
		panel_table.setBounds(60, 100, 1150, 450); // vị trí & size tùy chỉnh
		panel_table.setLayout(new BorderLayout()); // dùng layout này để bảng tự mở rộng
		pQuanLyMon.add(panel_table);
		
		String[] columnNames = {"Mã món", "Tên món ăn", "Loại","Đơn giá", "Hình ảnh", "Xóa", "Chỉnh sửa","Path"};
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
		table.getColumnModel().getColumn(5).setCellRenderer(imageRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(imageRenderer);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (Mon mon : dsMon) {
		    // Load ảnh
			String path =  "src/images/imageMon/" + mon.getHinhAnh();
		    ImageIcon icon = new ImageIcon(path);
		    Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		    ImageIcon monAn = new ImageIcon(image);
		    
		    String path_delete = "src/images/App/iconDelete.png"; 
		    ImageIcon icon_delete = new ImageIcon(path_delete);
		    Image image_delete = icon_delete.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		    ImageIcon xoa = new ImageIcon(image_delete);
		    
		    String path_pencil = "src/images/App/edit.png"; 
		    ImageIcon icon_pencil = new ImageIcon(path_pencil);
		    Image image_pencil = icon_pencil.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		    ImageIcon sua = new ImageIcon(image_pencil);
		    
		    // Thêm hàng vào bảng
		    model.addRow(new Object[] {
		    	mon.getMaMon(),	
		        mon.getTenMon(),
		        mon.getLoaiMon(),
		        String.format("%,.0f VNĐ", mon.getDonGia()), // format tiền
		        monAn,
		        xoa, sua,
		        path
		    });
		}    
		TableColumn hiddenColumn = table.getColumnModel().getColumn(7);
		hiddenColumn.setMinWidth(0);
		hiddenColumn.setMaxWidth(0);
		hiddenColumn.setPreferredWidth(0);

		for (int i = 0; i < table.getColumnCount(); i++) {
			if(i==4 || i==5 || i==6) {
				continue;
			}else {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		    
		}
		
		
		
		
		JButton btn_themMon = new JButton("Thêm");
		btn_themMon.setBounds(1100, 580, 100, 40); 
		btn_themMon.setFont(new Font("Arial", Font.BOLD, 20)); 
		btn_themMon.setForeground(Color.BLACK);
		btn_themMon.setBackground(new Color(255, 153, 0));
		pQuanLyMon.add(btn_themMon);
		
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
		 		    
		 		    String path_delete = "src/images/App/iconDelete.png"; 
		 		    ImageIcon icon_delete = new ImageIcon(path_delete);
		 		    Image image_delete = icon_delete.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		 		    ImageIcon xoa = new ImageIcon(image_delete);
		 		    
		 		    String path_pencil = "src/images/App/edit.png"; 
		 		    ImageIcon icon_pencil = new ImageIcon(path_pencil);
		 		    Image image_pencil = icon_pencil.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		 		    ImageIcon sua = new ImageIcon(image_pencil);
		 		    
		 		    // Thêm hàng vào bảng
		 		    model.addRow(new Object[] {
		 		    	mon.getMaMon(),	
		 		        mon.getTenMon(),
		 		        mon.getLoaiMon(),
		 		        String.format("%,.0f VNĐ", mon.getDonGia()), // format tiền
		 		        monAn,
		 		        xoa, sua,
		 		        path
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
		            	URL imageUrl = getClass().getResource("/images/imageMon/" + mon.getHinhAnh());
		            	String path = "src/images/imageMon/" + mon.getHinhAnh();
		     		    ImageIcon icon = new ImageIcon(path);
		     		    Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		     		    ImageIcon monAn = new ImageIcon(image);
		     		    
		     		    String path_delete = "src/images/App/iconDelete.png"; 
		     		    ImageIcon icon_delete = new ImageIcon(path_delete);
		     		    Image image_delete = icon_delete.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		     		    ImageIcon xoa = new ImageIcon(image_delete);
		     		    
		     		    String path_pencil = "src/images/App/edit.png"; 
		     		    ImageIcon icon_pencil = new ImageIcon(path_pencil);
		     		    Image image_pencil = icon_pencil.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		     		    ImageIcon sua = new ImageIcon(image_pencil);
		     		    
		     		    // Thêm hàng vào bảng
		     		    model.addRow(new Object[] {
		     		    	mon.getMaMon(),	
		     		        mon.getTenMon(),
		     		        mon.getLoaiMon(),
		     		        String.format("%,.0f VNĐ", mon.getDonGia()), // format tiền
		     		        monAn,
		     		        xoa, sua,
		     		        path
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
		        		System.err.println("haha");
		        		frameThem_ChinhMon("Chỉnh sửa món ăn",dsMon,model.getValueAt(row, 0).toString(), model.getValueAt(row, 1).toString(), model.getValueAt(row, 2).toString(), model.getValueAt(row, 3).toString(), model.getValueAt(row, 7).toString(),"Chỉnh sửa",model);
		        	}
		        }
		    }
		});

		

		btn_themMon.addActionListener(new ActionListener() {
			private File selectedFile = null;

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	System.err.println("haha");
				frameThem_ChinhMon("Thêm món ăn", dsMon, "", "", "", "", "","Thêm",model);
		    }
		});	        	
}
	
	
    public void frameThem_ChinhMon(String title,ArrayList<Mon> dsMon, String maMon, String tenMon, String loai, String donGia, String hinhAnh, String currentBtn, DefaultTableModel model) {
    	JFrame frameThem_Chinh = new JFrame(title);
        frameThem_Chinh.setSize(800, 500);
        frameThem_Chinh.setLayout(null);
        frameThem_Chinh.setLocationRelativeTo(null);

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
        txtGia.setText(donGia);
        txtGia.setFont(fontTo);
        
        JLabel lblHinhAnh = new JLabel("Hình ảnh:");
        lblHinhAnh.setBounds(30, 200, 120, 30);
        lblHinhAnh.setFont(fontTo);
        
        
        JLabel lblAnhMon = new JLabel();
        lblAnhMon.setBounds(120, 130, 150, 150);
        lblAnhMon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JButton btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setBounds(135, 300, 120, 30);
        btnChonAnh.setFont(fontTo);
        
        final File[] selectedFile = { new File(hinhAnh) };
        if (hinhAnh != null && !hinhAnh.isEmpty()) {
            File file = new File(hinhAnh);
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image image = icon.getImage().getScaledInstance(lblAnhMon.getWidth(), lblAnhMon.getHeight(), Image.SCALE_SMOOTH);
                lblAnhMon.setIcon(new ImageIcon(image));
            }
        }
        final String[] duongDan = new String[1];
        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn ảnh món ăn");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                	selectedFile[0] = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile[0].getAbsolutePath());

                    Image image = icon.getImage().getScaledInstance(lblAnhMon.getWidth(), lblAnhMon.getHeight(), Image.SCALE_SMOOTH);
                    lblAnhMon.setIcon(new ImageIcon(image));
                    duongDan[0] = selectedFile[0].getAbsolutePath();
                }
            }
        });
        
        JButton btnThem_Chinh = new JButton(currentBtn);
        btnThem_Chinh.setFont(fontTo);
        btnThem_Chinh.setBounds(500, 400, 120, 30);
        btnThem_Chinh.setForeground(Color.BLACK);
        btnThem_Chinh.setBackground(new Color(255, 153, 0));
        
        btnThem_Chinh.addActionListener(new ActionListener() {
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

                double donGia;
                try {
                	donGiaStr = donGiaStr.replace(" VNĐ", "").replace(" VND", "").replace(",", "");
                	donGia = Double.parseDouble(donGiaStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Đơn giá phải là số.");
                    return;
                }
                
                String thuMucAnh = "src/images/imageMon/";  // thư mục lưu ảnh
                File fileDich = new File(thuMucAnh + hinhAnh);

                // Nếu ảnh chưa tồn tại thì sao chép vào thư mục
                if (!fileDich.exists()) {
                    try {
                        // Tạo thư mục nếu chưa tồn tại
                        File thuMuc = new File(thuMucAnh);
                        if (!thuMuc.exists()) {
                            thuMuc.mkdirs();
                        }

                        Files.copy(selectedFile[0].toPath(), fileDich.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Đã sao chép ảnh vào thư mục.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi sao chép ảnh: " + ex.getMessage());
                        return;
                    }
                }

                Mon monMoi = null;
                boolean result = false;
                if (currentBtn.equals("Thêm")) {
//                	System.err.println(duongDan[0]+hinhAnh);
                	String maMon = QuanLyMon_DAO.taoMaMon();
//                	monMoi.setMaMon(maMon);
                	monMoi = new Mon(maMon, tenMon, loaiMon, donGia, hinhAnh);
                	result = QuanLyMon_DAO.addMon(monMoi);
                	
                	
                } else if (currentBtn.equals("Chỉnh sửa")) {
                	result = QuanLyMon_DAO.updateMon(monMoi);
                }
          
                if (result) {
                	model.setRowCount(0);
    		        for (Mon mon : dsMon) {
    		        	URL imageUrl = getClass().getResource("/images/imageMon/" + mon.getHinhAnh());
    		        	String path = "src/images/imageMon/" + mon.getHinhAnh();
		     		    ImageIcon icon = new ImageIcon(path);
		     		    Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		     		    ImageIcon monAn = new ImageIcon(image);
		     		    
		     		    String path_delete = "src/images/App/iconDelete.png"; 
		     		    ImageIcon icon_delete = new ImageIcon(path_delete);
		     		    Image image_delete = icon_delete.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		     		    ImageIcon xoa = new ImageIcon(image_delete);
		     		    
		     		    String path_pencil = "src/images/App/edit.png"; 
		     		    ImageIcon icon_pencil = new ImageIcon(path_pencil);
		     		    Image image_pencil = icon_pencil.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		     		    ImageIcon sua = new ImageIcon(image_pencil);
		     		    
		     		    // Thêm hàng vào bảng
		     		    model.addRow(new Object[] {
		     		    	mon.getMaMon(),	
		     		        mon.getTenMon(),
		     		        mon.getLoaiMon(),
		     		        String.format("%,.0f VNĐ", mon.getDonGia()), // format tiền
		     		        monAn,
		     		        xoa, sua,
		     		        path
		     		    });
    		        }
                    JOptionPane.showMessageDialog(null, currentBtn+" thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, currentBtn+" thất bại!");
                }
            }
        });

        
        JButton btnHuy = new JButton("Hủy");
        btnHuy.setFont(fontTo);
        btnHuy.setBounds(630, 400, 80, 30);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setBackground(new Color(255, 0, 0));
        btnThem_Chinh.addActionListener(ev -> {
        	frameThem_Chinh.dispose();
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frameThem_Chinh.dispose();
                
            }
        });
        
        frameThem_Chinh.add(lblTenMon);
        frameThem_Chinh.add(txtTenMon);
        frameThem_Chinh.add(lblLoaiMon);
        frameThem_Chinh.add(cbLoaiMon);
        frameThem_Chinh.add(lblAnhMon);
        frameThem_Chinh.add(lblHinhAnh);
        frameThem_Chinh.add(btnChonAnh);
        frameThem_Chinh.add(lblGia);
        frameThem_Chinh.add(txtGia);
        frameThem_Chinh.add(btnThem_Chinh);
        frameThem_Chinh.add(btnHuy);
        
        
        frameThem_Chinh.setVisible(true);
    }
    public JPanel getPanel() {
        return pQuanLyMon;
    }

}
