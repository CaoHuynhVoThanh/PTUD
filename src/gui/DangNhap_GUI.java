package gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entities.NhanVien;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class DangNhap_GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_manv;
	private JPasswordField tf_mk;
	private JButton btn_dangnhap;
	private JButton btnNewButton_1;
	public static QuenMatKhau_GUI q;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap_GUI frame = new DangNhap_GUI();
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
	public DangNhap_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 750, 450);
		setResizable(false);
		contentPane = new JPanel() {
            private Image background = new ImageIcon(getClass().getResource("/images/App/login-background.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); 

        setContentPane(contentPane); 
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/App/logo.png"));

	     // Resize ảnh về đúng kích thước bạn muốn
	     Image scaledImage = icon.getImage().getScaledInstance(300, 90, Image.SCALE_SMOOTH);
	     ImageIcon resizedIcon = new ImageIcon(scaledImage);
	
	     // Tạo JLabel chứa ảnh đã resize
	     JLabel iconLabel = new JLabel(resizedIcon);
	     iconLabel.setBounds(413, 94, 300, 200);
	
	     // Thêm vào panel
	     contentPane.add(iconLabel);

        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 153, 0));
        panel.setBounds(134, 55, 250, 286);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(13, 15, 230, 28);
        panel.add(lblNewLabel);
        
        tf_manv = new JTextField();
        tf_manv.setBounds(23, 82, 206, 28);
        panel.add(tf_manv);
        tf_manv.setColumns(10);
        tf_manv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_mk.requestFocusInWindow();
            }
        });
        
        JLabel lblNewLabel_1 = new JLabel("Mã nhân viên:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(25, 65, 126, 13);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(25, 124, 126, 13);
        panel.add(lblNewLabel_1_1);
        
        tf_mk = new JPasswordField();
        tf_mk.setBounds(23, 143, 206, 28);
        panel.add(tf_mk);
        tf_mk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_dangnhap.doClick();
            }
        });
        
        btn_dangnhap = new JButton("ĐĂNG NHẬP");
        btn_dangnhap.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_dangnhap.setForeground(new Color(255, 255, 255));
        btn_dangnhap.setBackground(new Color(0, 0, 0));
        btn_dangnhap.setBounds(23, 221, 201, 38);
        btn_dangnhap.addActionListener(this);
        panel.add(btn_dangnhap);
        	
        btnNewButton_1 = new JButton("<html><u>Quên mật khẩu</u></html>");
        btnNewButton_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
        btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
        btnNewButton_1.setBounds(128, 175, 115, 21);
        panel.add(btnNewButton_1);
        btnNewButton_1.addActionListener(this);
        
        btnNewButton_1.setOpaque(false);
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setBorderPainted(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		if (cmd.equals("ĐĂNG NHẬP")) {
			String ma = tf_manv.getText().trim();
			Application.nhanvien = NhanVien_DAO.timNhanVienTheoMa(ma);
			String pass =String.copyValueOf(tf_mk.getPassword());
			String mk = TaiKhoan_DAO.getMatKhau(ma);
			System.out.println(pass);
			System.out.println(mk);
			if (Application.nhanvien==null || mk==null || !mk.equals(pass)) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên hoặc mật khẩu không đúng");
			}
			else {
				JOptionPane.showMessageDialog(null, "Xin chào "+Application.nhanvien.getTenNV()+" !");
				Application.setUser();
				this.setVisible(false);
				Application.frame.setVisible(true);
			}
		}
		if (cmd.equals("<html><u>Quên mật khẩu</u></html>")) {
			System.out.println("Hihi");
			Sent_OTP o = new Sent_OTP();
			o.setVisible(true);
			o.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
	}
	
	public void clearAll() {
		tf_manv.setText("");
		tf_mk.setText("");
	}
}
