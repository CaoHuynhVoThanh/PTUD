package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.mail.EmailException;

import dao.NhanVien_DAO;
import entities.NhanVien;
import mail.TestSendMail;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class Sent_OTP extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tf_manv;
	private JTextField tf_otp;
	private JButton btn_gui;
	private JLabel count_down;
	private Timer timer;
	private int timeLeft;
	private String OTP;
	private JButton btn_khoiPhuc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sent_OTP dialog = new Sent_OTP();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sent_OTP() {
		setBounds(530, 280, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 153, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tf_manv = new JTextField();
			tf_manv.setBounds(53, 54, 227, 33);
			contentPanel.add(tf_manv);
			tf_manv.setColumns(10);
		}
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(57, 31, 123, 23);
		contentPanel.add(lblNewLabel);
		
		btn_gui = new JButton("Gửi");
		btn_gui.setForeground(new Color(255, 255, 255));
		btn_gui.setBackground(new Color(0, 0, 0));
		btn_gui.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_gui.setBounds(290, 54, 93, 33);
		btn_gui.addActionListener(this);
		contentPanel.add(btn_gui);
		
		JLabel lblOtp = new JLabel("OTP:");
		lblOtp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOtp.setBounds(57, 131, 123, 23);
		contentPanel.add(lblOtp);
		
		tf_otp = new JTextField();
		tf_otp.setColumns(10);
		tf_otp.setBounds(53, 154, 195, 33);
		contentPanel.add(tf_otp);
		
		count_down = new JLabel("");
		count_down.setFont(new Font("Tahoma", Font.PLAIN, 12));
		count_down.setBounds(53, 97, 330, 27);
		contentPanel.add(count_down);
		
		btn_khoiPhuc = new JButton("Khôi phục");
		btn_khoiPhuc.setBackground(new Color(0, 0, 0));
		btn_khoiPhuc.setForeground(new Color(255, 255, 255));
		btn_khoiPhuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_khoiPhuc.setBounds(258, 154, 125, 33);
		btn_khoiPhuc.addActionListener(this);
		contentPanel.add(btn_khoiPhuc);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		if (cmd.equals("Gửi")) {
			timeLeft = 60;
			timer = new Timer(1000, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                timeLeft--;
	                if (timeLeft >= 0) {
	                	count_down.setText("Vui lòng gửi lại sau " + timeLeft + " giây");
	                } else {
	                    timer.stop();
	                    count_down.setText("");
	                    btn_gui.setEnabled(true);
	                }
	            }
	        });
	        timer.start();  
			NhanVien nv = NhanVien_DAO.timNhanVienTheoMa(tf_manv.getText().trim());
			if (nv==null) {
		        JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin nhân viên này!");
		        }
		    else {
		        timeLeft = 60; // reset lại nếu bấm lại
		        count_down.setText("Vui lòng gửi lại sau " + timeLeft + " giây");
		        btn_gui.setEnabled(false);
	        	try {
	        		JOptionPane.showMessageDialog(null, "OPT đã được gửi, vui lòng kiểm tra hộp thư của bạn!");
		        	OTP = TestSendMail.sendOTP(nv.getEmail());
				} catch (MalformedURLException | EmailException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
        
		}
		if (cmd.equals("Khôi phục")) {
			String userOTP = tf_otp.getText().trim();
			System.out.println(userOTP);
			System.out.println(OTP);
			if (userOTP.equals(OTP)) {
				DangNhap_GUI.q = new QuenMatKhau_GUI();
				DangNhap_GUI.q.setVisible(true);
				QuenMatKhau_GUI.makp=tf_manv.getText();
				this.dispose();
			}
		}
	}
}
