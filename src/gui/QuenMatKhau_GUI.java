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

public class QuenMatKhau_GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField tf_mk2;
	private JButton btn_reset;
	private JPasswordField tf_mk1;
	public static String makp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuenMatKhau_GUI frame = new QuenMatKhau_GUI();
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
	public QuenMatKhau_GUI() {
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
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/App/login-logo.png")); // đổi thành đường dẫn ảnh của bạn
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 8));
        iconLabel.setSize(200, 200);

        // Đặt vị trí tuyệt đối cho icon trên panel
        iconLabel.setBounds(453, 94, 168, 208);
        // Thêm icon vào panel
        contentPane.add(iconLabel);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 153, 0));
        panel.setBounds(134, 55, 250, 286);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ĐẶT LẠI MẬT KHẨU");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(13, 15, 230, 28);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Mật khẩu mới:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(25, 65, 174, 13);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nhập lại mật khẩu mới:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(25, 124, 206, 13);
        panel.add(lblNewLabel_1_1);
        
        tf_mk2 = new JPasswordField();
        tf_mk2.setBounds(23, 143, 206, 28);
        panel.add(tf_mk2);
        
        btn_reset = new JButton("KHÔI PHỤC");
        btn_reset.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_reset.setForeground(new Color(255, 255, 255));
        btn_reset.setBackground(new Color(0, 0, 0));
        btn_reset.setBounds(25, 200, 206, 36);
        btn_reset.addActionListener(this);
        panel.add(btn_reset);
        
        tf_mk1 = new JPasswordField();
        tf_mk1.setBounds(25, 83, 206, 28);
        panel.add(tf_mk1);
        
        JButton btn_exit = new JButton("Quay lại");
        btn_exit.setForeground(Color.WHITE);
        btn_exit.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_exit.setBackground(Color.BLACK);
        btn_exit.setBounds(58, 246, 142, 28);
        panel.add(btn_exit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		if (cmd.equals("KHÔI PHỤC")) {
			String ma1 = String.copyValueOf(tf_mk1.getPassword());
			String ma2 = String.copyValueOf(tf_mk2.getPassword());
			if (ma1.equals(ma2)) {
				JOptionPane.showMessageDialog(null, "Khôi phục mật khẩu thành công, quay về đăng nhập");
				TaiKhoan_DAO.resetMK(makp, ma2);
				this.dispose();
				Application.g.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu không khớp!");
			}
			
		}
	}
}
