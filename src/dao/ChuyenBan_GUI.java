package dao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Ban;
import gui.DatBan_GUI;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ChuyenBan_GUI extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel pn = new JPanel();
	private JTextField tf_mabangoc;
	private ArrayList<Ban> ds = new ArrayList<>();
	private JComboBox<String> comb_mabanchuyen;
	private JButton btnChuyen, btnhuy;
	private JLabel lb_tongtang, lbvt1, lbvt2, lbkv1, lbkv2, lbpt1, lbpt2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChuyenBan_GUI dialog = new ChuyenBan_GUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChuyenBan_GUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pn.setBackground(Color.DARK_GRAY);
		pn.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pn, BorderLayout.CENTER);
		pn.setLayout(null);
		{
			tf_mabangoc = new JTextField();
			tf_mabangoc.setBounds(33, 57, 155, 30);
			pn.add(tf_mabangoc);
			tf_mabangoc.setColumns(10);
		}
		
		JLabel lblNewLabel = new JLabel("Bàn được chọn:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(33, 34, 103, 13);
		pn.add(lblNewLabel);
		
		JLabel lblBnChuynn = new JLabel("Bàn chuyển đến:");
		lblBnChuynn.setForeground(new Color(255, 255, 255));
		lblBnChuynn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBnChuynn.setBounds(241, 34, 118, 13);
		pn.add(lblBnChuynn);
		
		comb_mabanchuyen = new JComboBox<String>();
		comb_mabanchuyen.setBounds(241, 57, 155, 30);
		pn.add(comb_mabanchuyen);
		comb_mabanchuyen.addActionListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("Vị trí:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(33, 97, 73, 13);
		pn.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Khu vực:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(33, 114, 73, 13);
		pn.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Phụ thu:");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setBounds(33, 132, 73, 13);
		pn.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Phụ thu:");
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2.setBounds(241, 132, 73, 13);
		pn.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Khu vực:");
		lblNewLabel_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_2.setBounds(241, 114, 73, 13);
		pn.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Vị trí:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setBounds(241, 97, 73, 13);
		pn.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Phụ phí gia tăng:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(33, 183, 128, 24);
		pn.add(lblNewLabel_2);
		
		lbpt1 = new JLabel("Phụ thu:");
		lbpt1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbpt1.setForeground(Color.WHITE);
		lbpt1.setBounds(115, 132, 73, 13);
		pn.add(lbpt1);
		
		lbkv1 = new JLabel("Khu vực:");
		lbkv1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbkv1.setForeground(Color.WHITE);
		lbkv1.setBounds(115, 114, 73, 13);
		pn.add(lbkv1);
		
		lbvt1 = new JLabel("Vị trí:");
		lbvt1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbvt1.setForeground(Color.WHITE);
		lbvt1.setBounds(115, 97, 73, 13);
		pn.add(lbvt1);
		
		lbpt2 = new JLabel("Phụ thu:");
		lbpt2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbpt2.setForeground(Color.WHITE);
		lbpt2.setBounds(323, 132, 73, 13);
		pn.add(lbpt2);
		
		lbkv2 = new JLabel("Khu vực:");
		lbkv2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbkv2.setForeground(Color.WHITE);
		lbkv2.setBounds(323, 114, 73, 13);
		pn.add(lbkv2);
		
		lbvt2 = new JLabel("Vị trí:");
		lbvt2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbvt2.setForeground(Color.WHITE);
		lbvt2.setBounds(323, 97, 73, 13);
		pn.add(lbvt2);
		
		lb_tongtang = new JLabel("Phụ phí gia tăng");
		lb_tongtang.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tongtang.setForeground(Color.WHITE);
		lb_tongtang.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_tongtang.setBounds(266, 183, 128, 24);
		pn.add(lb_tongtang);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnChuyen = new JButton("Chuyển");
				btnChuyen.addActionListener(this);
				btnChuyen.setActionCommand("OK");
				buttonPane.add(btnChuyen);
				getRootPane().setDefaultButton(btnChuyen);
			}
			{
				btnhuy = new JButton("Hủy");
				btnhuy.setActionCommand("Cancel");
				buttonPane.add(btnhuy);
			}
		}
	}
	
	public void setModal(Ban ban, ArrayList<Ban> ds) {
		this.ds = ds;
		tf_mabangoc.setText(ban.getMaBan());
		tf_mabangoc.setEditable(false);
		lbkv1.setText(ban.getTenKV());
		lbvt1.setText("Lầu "+ban.getViTri());
		lbpt1.setText(""+ban.getPhuPhi());
		for (Ban x: ds) {
			comb_mabanchuyen.addItem(x.getMaBan());
		}
		comb_mabanchuyen.setSelectedItem(ban.getMaBan());
		double tongtang = 0.0;
		btnChuyen.setEnabled(false);
		if (ban.getTinhTrang()==2) lb_tongtang.setText("0.0");
		else lb_tongtang.setText(""+tongtang);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		if (cmd.equals("comboBoxChanged")) {
			Ban banchon = null;
			String maban = (String) comb_mabanchuyen.getSelectedItem();
			for (Ban x: ds) {
				if (x.getMaBan().equals(maban)) {
					banchon = x;
					break;
				}
			}
			lbkv2.setText(banchon.getTenKV());
			lbvt2.setText("Lầu "+banchon.getViTri());
			lbpt2.setText(""+banchon.getPhuPhi());
			String ma = (String) comb_mabanchuyen.getSelectedItem();
			for (Ban x: ds) {
				if (x.getMaBan().equals(ma)) {
					double s = x.getPhuPhi() - Double.parseDouble(lbpt1.getText());
					lb_tongtang.setText(s+"");
					break;
				}
			}
			if (banchon.getMaBan().equals(tf_mabangoc.getText())) btnChuyen.setEnabled(false);
			else btnChuyen.setEnabled(true);
		}
		if (cmd.equals("OK")) {
			if (JOptionPane.showConfirmDialog(null, "Xác nhận chuyển bàn?")==JOptionPane.YES_OPTION) {
				Ban_DAO.updateTrangThai(tf_mabangoc.getText(), (String)comb_mabanchuyen.getSelectedItem());
				JOptionPane.showMessageDialog(null, "Chuyển bàn thành công!");
				this.dispose();
				DatBan_GUI.hiddenButton.doClick();
			}
		}
	}
}
