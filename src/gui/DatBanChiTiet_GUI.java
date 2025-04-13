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

import connectDB.ConnectDB;
import dao.DonDatBan_DAO;
import dao.DonGoiMon_DAO;
import dao.KhachHang_DAO;
import entities.Ban;
import entities.NhanVien;

import javax.swing.JRadioButton;

public class DatBanChiTiet_GUI extends JDialog implements ActionListener{
	private NhanVien currenUser = new NhanVien("25000001", "Lê Vinh Quang", "quankle@gmail.com", "0987654321", "Gò Vấp", "Nhân Viên Quầy", null, true);
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tf_sdt;
	private JTextField tf_ngayNhan;
	private JTextField tf_tenkh;
	private JTable table;
	JRadioButton rd_dungngay;
	JRadioButton rd_dattruoc;
	JSpinner tf_sokhach;
	JComboBox<String> combPhut;
	JComboBox<String> combGio;
	public static JButton okButton;
	JLabel lb_coc;
	ArrayList<Ban> dsbd;
	ConnectDB con;

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
		
		JLabel lblTngSBn = new JLabel("Tổng số bàn:");
		lblTngSBn.setForeground(Color.WHITE);
		lblTngSBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngSBn.setBounds(510, 280, 116, 27);
		contentPanel.add(lblTngSBn);
		
		JLabel lblTngSGh = new JLabel("Tổng số ghế:");
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
		
		lb_coc = new JLabel("200,000");
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
				{null, null, null},
			},
			new String[] {
				"M\u00E3 b\u00E0n", "S\u1ED1 gh\u1EBF", "Ph\u1EE5 ph\u00ED"
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
		
		ButtonGroup group = new ButtonGroup();

		rd_dattruoc = new JRadioButton("Đặt trước");
		rd_dattruoc.setBounds(421, 186, 103, 21);
		contentPanel.add(rd_dattruoc);
		rd_dattruoc.addActionListener(this);
		group.add(rd_dattruoc);
		
		rd_dungngay = new JRadioButton("Dùng ngay");
		rd_dungngay.setBounds(559, 186, 103, 21);
		contentPanel.add(rd_dungngay);
		rd_dungngay.addActionListener(this);
		group.add(rd_dungngay);
		rd_dattruoc.setSelected(true);
		
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
	    DefaultTableModel dtm = new DefaultTableModel(
	    new Object[]{"Mã Bàn", "Loại Bàn", "Phụ Phí"}, 0);
	    double tongTien =0;
	    for (Ban x : ds) {
	        System.out.println(x.getMaBan()); // Kiểm tra log
	        dtm.addRow(new Object[]{x.getMaBan(), x.getLoaiBan(), x.getPhuPhi()});
	        tongTien+=x.getPhuPhi();
	        lb_coc.setText(tongTien+"");
	    }

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

		String hour = (String) combGio.getSelectedItem();   
		String minute = (String) combPhut.getSelectedItem();
		String ngayDat = tf_ngayNhan.getText();
		
		String input = ngayDat+" "+hour+":"+minute;
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		int soKhach = (int) tf_sokhach.getValue();
		double tiencoc = Double.parseDouble(lb_coc.getText());
		LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
		String hoten = tf_tenkh.getText();
		String sdt = tf_sdt.getText();
		System.out.println(dateTime);
		createKhachHang(makh, hoten, sdt);
		
		boolean isDungNgay = rd_dungngay.isSelected();
		DonDatBan_DAO.insertDonDatBan(ma, null, currenUser.getMaNV(), makh, dateTime, dateTime, soKhach, tiencoc, isDungNgay);
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
	}
}
