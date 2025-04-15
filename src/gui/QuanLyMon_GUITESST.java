package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.*;

public class QuanLyMon_GUITESST extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nhapTenMon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyMon_GUITESST frame = new QuanLyMon_GUITESST();
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
	public QuanLyMon_GUITESST(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1029, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_dropdown = new JPanel();
		panel_dropdown.setBounds(320, 160, 900, 40);
		contentPane.add(panel_dropdown);
		panel_dropdown.setLayout(null);
		
		JComboBox<String> comboBox_loai = new JComboBox<>();
		comboBox_loai.setBounds(0, 0, 150, 35); 
		comboBox_loai.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_loai.addItem("Tất cả");
		comboBox_loai.addItem("Nước");
		comboBox_loai.addItem("Thức ăn");
		
		panel_dropdown.add(comboBox_loai);
		
		JComboBox comboBox_ten = new JComboBox();
		comboBox_ten.setBounds(190, 0, 180, 35);
		panel_dropdown.add(comboBox_ten);
		
		textField_nhapTenMon = new JTextField();
		textField_nhapTenMon.setBounds(410, 0, 300, 35);
		panel_dropdown.add(textField_nhapTenMon);
		textField_nhapTenMon.setColumns(10);
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(293, 250, 1000, 450); // vị trí & size tùy chỉnh
		panel_table.setLayout(new BorderLayout()); // dùng layout này để bảng tự mở rộng
		contentPane.add(panel_table);
		
		String[] columnNames = { "Tên món ăn", "Đơn giá", "Hình ảnh", "Xóa", "Chỉnh sửa"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		panel_table.add(scrollPane, BorderLayout.SOUTH);
		table.setRowHeight(60);
		
		
		table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
		    @Override
		    public void setValue(Object value) {
		        if (value instanceof ImageIcon) {
		            setIcon((ImageIcon) value);
		            setText("");
		        } else {
		            super.setValue(value);
		        }
		    }
		});
		table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
		    @Override
		    public void setValue(Object value) {
		        if (value instanceof ImageIcon) {
		            setIcon((ImageIcon) value);
		            setText("");
		        } else {
		            super.setValue(value);
		        }
		    }
		});
		table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
		    @Override
		    public void setValue(Object value) {
		        if (value instanceof ImageIcon) {
		            setIcon((ImageIcon) value);
		            setText("");
		        } else {
		            super.setValue(value);
		        }
		    }
		});
		
		ImageIcon icon = new ImageIcon("src/images/imageMon/bachquanuong.png"); 
		Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon monAn = new ImageIcon(image);
		model.addRow(new Object[] { "Sushi", "150.000 VNĐ", monAn, "Xóa", "Sửa" });
		model.addRow(new Object[] { "Sushi", "150.000 VNĐ", monAn, "Xóa", "Sửa" });
		model.addRow(new Object[] { "Sushi", "150.000 VNĐ", monAn, "Xóa", "Sửa" });
		model.addRow(new Object[] { "Sushi", "150.000 VNĐ", monAn, "Xóa", "Sửa" });
		model.addRow(new Object[] { "Sushi", "150.000 VNĐ", monAn, "Xóa", "Sửa" });
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < table.getColumnCount(); i++) {
			if(i==2) {
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
		
		btn_themMon.addActionListener(new ActionListener() {
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
		        String[] loaiMonOptions = {"Món khai vị", "Món chính", "Tráng miệng", "Nước uống"};
		        JComboBox<String> cbLoaiMon = new JComboBox<>(loaiMonOptions);
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
		        
		        JLabel lblMota = new JLabel("Mô tả:");
		        lblMota.setBounds(30, 350, 120, 30);
		        lblMota.setFont(fontTo);
		        JTextField txtMota = new JTextField();
		        txtMota.setBounds(120, 350, 570, 30);
		        txtMota.setFont(fontTo);
		        
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
		                    File selectedFile = fileChooser.getSelectedFile();
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

		        // Thêm vào frame
		        frameThemMon.add(lblTenMon);
		        frameThemMon.add(txtTenMon);
		        frameThemMon.add(lblLoaiMon);
		        frameThemMon.add(cbLoaiMon);
		        frameThemMon.add(lblAnhMon);
		        frameThemMon.add(lblHinhAnh);
		        frameThemMon.add(btnChonAnh);
		        frameThemMon.add(lblMota);
		        frameThemMon.add(txtMota);
		        frameThemMon.add(lblGia);
		        frameThemMon.add(txtGia);
		        frameThemMon.add(btnLuu);
		        frameThemMon.add(btnHuy);

		        frameThemMon.setVisible(true);
		    }
		});

	    
	}
}
