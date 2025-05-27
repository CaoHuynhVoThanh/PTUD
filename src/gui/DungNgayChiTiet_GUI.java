package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DonDatBan_DAO;
import dao.KhachHang_DAO;
import entities.Ban;
import entities.NhanVien;


public class DungNgayChiTiet_GUI extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tf_ngayNhan;
	private JTable table;
	JSpinner tf_sokhach;
	public static JButton okButton;
	ArrayList<Ban> dsbd;
	private JLabel lb_tongban;
	private JLabel lb_tongghe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DungNgayChiTiet_GUI dialog = new DungNgayChiTiet_GUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DungNgayChiTiet_GUI() {
		setBounds(400, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tf_ngayNhan = new JTextField();
	    tf_ngayNhan.setEditable(false);
		tf_ngayNhan.setColumns(10);
		tf_ngayNhan.setBounds(169, 75, 213, 27);
		contentPanel.add(tf_ngayNhan);
		
		JLabel lblNgyNhn = new JLabel("Ngày đặt:");
		lblNgyNhn.setForeground(Color.WHITE);
		lblNgyNhn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyNhn.setBounds(41, 73, 158, 27);
		contentPanel.add(lblNgyNhn);
		
		JLabel lblSKhch = new JLabel("Số khách:");
		lblSKhch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSKhch.setForeground(Color.WHITE);
		lblSKhch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSKhch.setBounds(419, 73, 77, 27);
		contentPanel.add(lblSKhch);
		
		tf_sokhach = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		tf_sokhach.setBounds(526, 75, 213, 27);
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
		
		JLabel lblTngSBn = new JLabel("Tổng số bàn:");
		lblTngSBn.setForeground(Color.WHITE);
		lblTngSBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngSBn.setBounds(510, 289, 116, 27);
		contentPanel.add(lblTngSBn);
		
		JLabel lblTngSGh = new JLabel("Tổng số ghế:");
		lblTngSGh.setForeground(Color.WHITE);
		lblTngSGh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngSGh.setBounds(510, 321, 116, 27);
		contentPanel.add(lblTngSGh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 279, 433, 229);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"M\u00E3 b\u00E0n", "S\u1ED1 gh\u1EBF", "Ph\u1EE5 ph\u00ED"
			}
		));
		scrollPane.setViewportView(table);
		
		lb_tongban = new JLabel("0");
		lb_tongban.setHorizontalAlignment(SwingConstants.LEFT);
		lb_tongban.setForeground(Color.WHITE);
		lb_tongban.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_tongban.setBounds(623, 289, 116, 27);
		contentPanel.add(lb_tongban);
		
		lb_tongghe = new JLabel("0");
		lb_tongghe.setHorizontalAlignment(SwingConstants.LEFT);
		lb_tongghe.setForeground(Color.WHITE);
		lb_tongghe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_tongghe.setBounds(623, 321, 116, 27);
		contentPanel.add(lb_tongghe);
		
		JLabel lblDngNgayKhng = new JLabel("Dùng ngay không tính cọc");
		lblDngNgayKhng.setForeground(Color.WHITE);
		lblDngNgayKhng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDngNgayKhng.setBounds(510, 400, 229, 27);
		contentPanel.add(lblDngNgayKhng);
		
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
	
		
		
	}
	
	public void setDate(String s) {
		tf_ngayNhan.setText(s);
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
	    lb_tongghe.setText(tongghe+"");
	    SpinnerNumberModel model = (SpinnerNumberModel) tf_sokhach.getModel();
	    model.setMaximum(tongghe);
	    table.setModel(dtm); 
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

		LocalDateTime now = LocalDateTime.now();
		
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		int soKhach = (int) tf_sokhach.getValue();
		double tiencoc = 0.0;
		LocalDateTime dateTime = now;
		String hoten = "Vãng lai";
		String sdt = "";
		System.out.println(dateTime);
		createKhachHang(makh, hoten, sdt);
		
		DonDatBan_DAO.insertDonDatBan(ma, null, Application.nhanvien.getMaNV(), makh, dateTime, dateTime, soKhach, tiencoc, 0);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		if (cmd.equals("XÁC NHẬN")) {
			if (JOptionPane.showConfirmDialog(null, "Xác nhận đặt bàn không?")==JOptionPane.YES_OPTION) {
				if (createDDB()) {
					JOptionPane.showMessageDialog(null, "Đặt bàn thành công!");
					DatBan_GUI.hiddenButton.doClick();
					this.dispose();
				}
			}
		}
	}
}
