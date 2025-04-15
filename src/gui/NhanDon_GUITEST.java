package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import java.awt.event.*;

public class NhanDon_GUITEST extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSoDienThoai;
	private JTextField txtNgayNhan;
	private JLabel lblTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanDon_GUITEST frame = new NhanDon_GUITEST();
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
	public NhanDon_GUITEST() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1079, 742);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); // BỔ SUNG DÒNG NÀY QUAN TRỌNG
		setContentPane(contentPane);

		JPanel panel_DSDon = new JPanel();
		panel_DSDon.setLayout(null);
//		panel_DSDon.setBounds(320, 160, 1100, 600); 
		panel_DSDon.setBounds(0, 0, 1100, 600); 
		panel_DSDon.setBackground(Color.red);
		contentPane.add(panel_DSDon);
		
		panel_DSDon.add(lblTitle = new JLabel("Danh sách đơn chờ nhận"));
		lblTitle.setBounds(100, 10, 300, 30);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JPanel panel_TimKiem = new JPanel();
		panel_TimKiem.setBounds(0, 50, 1100, 30);
		panel_DSDon.add(panel_TimKiem);
		panel_TimKiem.setLayout(null); 

		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(0, 0, 200, 30);
		panel_TimKiem.add(txtSoDienThoai);
		
		txtNgayNhan = new JTextField();
		txtNgayNhan.setBounds(230, 0, 200, 30);
		panel_TimKiem.add(txtNgayNhan);
		txtNgayNhan.setColumns(10);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(0, 0, 0));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTim.setBounds(530, 0, 80, 30);
		panel_TimKiem.add(btnTim);

		
		JPanel panelDanhSach = new JPanel();
        panelDanhSach.setLayout(new BoxLayout(panelDanhSach, BoxLayout.Y_AXIS));
        panelDanhSach.setBackground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            panelDanhSach.add(taoPanelDon("210325-0123", 2, 4, "11h 59p", "0987654321"));
        }

       
        JScrollPane scrollPane = new JScrollPane(panelDanhSach);
        scrollPane.setBounds(0, 90, 600, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel_DSDon.add(scrollPane);
        
//        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        JPanel panelRight = new JPanel();
        panelRight.setBounds(610, 90, 430, 500);
		panelRight.setBackground(Color.LIGHT_GRAY);
		Box box2 = Box.createVerticalBox();
		Box br1,br2,br3,br4,br5,br6,br7,br8,br9;
		Box boxHuyDon_GiaHan,boxNhanBan;
		JButton btnHuyDon,btnGiaHan,btnNhanBan;
		JLabel lblTenKH,lblSoDT,lblSoBan,lblSoMon,lblThoiGianDat,lblThoiGianNhan,lblTienCoc,lblNhanVien;
		
		
		box2.add(br1 = Box.createHorizontalBox());
		box2.add(Box.createVerticalStrut(10));
		br1.add(new JLabel("Thông tin đặt bàn") {{
		    setFont(new Font("SansSerif", Font.BOLD, 18));
		}});
		box2.add(Box.createVerticalStrut(40));
		
		box2.add(br2 = Box.createHorizontalBox());
		br2.setPreferredSize(new Dimension(300, 30));
		br2.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br2.add(new JLabel("Tên khách hàng:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br2.add(Box.createHorizontalGlue());
		br2.add(lblTenKH = new JLabel("AAA"));
		
		box2.add(br3 = Box.createHorizontalBox());
		br3.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br3.add(new JLabel("Số điện thoại:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br3.add(Box.createHorizontalGlue());
		br3.add(lblSoDT = new JLabel("123"));
		
		box2.add(br4 = Box.createHorizontalBox());
		br4.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br4.add(new JLabel("Số bàn:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br4.add(Box.createHorizontalGlue());
		br4.add(lblSoBan = new JLabel("-1"));
		
		box2.add(br5 = Box.createHorizontalBox());
		br5.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br5.add(new JLabel("Số món:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br5.add(Box.createHorizontalGlue());
		br5.add(lblSoMon = new JLabel("-1"));
		
		box2.add(br6 = Box.createHorizontalBox());
		br6.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br6.add(new JLabel("Thời gian đặt:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br6.add(Box.createHorizontalGlue());
		br6.add(lblThoiGianDat = new JLabel("11h"));
		
		box2.add(br7 = Box.createHorizontalBox());
		br7.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br7.add(new JLabel("Thời gian nhận:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br7.add(Box.createHorizontalGlue());
		br7.add(lblThoiGianNhan = new JLabel("11h30"));
		
		box2.add(br8 = Box.createHorizontalBox());
		br8.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br8.add(new JLabel("Tiền cọc:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br8.add(Box.createHorizontalGlue());
		br8.add(lblTienCoc = new JLabel("200,000"));
		
		box2.add(br9 = Box.createHorizontalBox());
		br9.setMaximumSize(new Dimension(300, 30));
		box2.add(Box.createVerticalStrut(10));
		br9.add(new JLabel("Nhân viên thực hiện:") {{
		    setFont(new Font("SansSerif", Font.BOLD, 14));
		}});
		br9.add(Box.createHorizontalGlue());
		br9.add(lblNhanVien = new JLabel("AAA"));
		box2.add(Box.createVerticalStrut(50));
		
		box2.add(boxHuyDon_GiaHan = Box.createHorizontalBox());
		box2.add(Box.createVerticalStrut(10));
		boxHuyDon_GiaHan.add(btnHuyDon = new JButton("HỦY ĐƠN") {{
		    setBackground(Color.BLACK);                     
		    setForeground(Color.WHITE);                    
		    setFont(new Font("SansSerif", Font.BOLD, 17));  
		    setFocusPainted(false);                  
		    setPreferredSize(new Dimension(130, 60));       
		}});
		boxHuyDon_GiaHan.add(Box.createHorizontalGlue());
		boxHuyDon_GiaHan.add(btnGiaHan = new JButton("GIA HẠN") {{
		    setBackground(Color.GRAY);                     
		    setForeground(Color.WHITE);                    
		    setFont(new Font("SansSerif", Font.BOLD, 17));  
		    setFocusPainted(false);                  
		    setPreferredSize(new Dimension(130, 60));       
		}});
		
		box2.add(boxNhanBan = Box.createHorizontalBox());
		box2.add(Box.createVerticalStrut(10));
		boxNhanBan.add(btnNhanBan = new JButton("NHẬN BÀN") {{
		    setBackground(Color.ORANGE);                     
		    setForeground(Color.WHITE);                    
		    setFont(new Font("SansSerif", Font.BOLD, 17));  
		    setFocusPainted(false);                  
		    setPreferredSize(new Dimension(300, 35));
		    setMaximumSize(new Dimension(300, 35)); 
		}});
		panelRight.add(box2, BorderLayout.EAST);
		panel_DSDon.add(panelRight);
        
        
		
		
	}
	private JPanel taoPanelDon(String maDon, int soBan, int soMon, String gioDen, String soDT) {
        JPanel panelDon = new JPanel(null);
        panelDon.setPreferredSize(new Dimension(1000, 90));
        panelDon.setMaximumSize(new Dimension(1000, 90));
        panelDon.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelDon.setBackground(Color.WHITE);

        // Icon bàn
        ImageIcon iconBan = new ImageIcon("src/images/App/tabbleddb.png");
        Image img = iconBan.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(img));
        lblIcon.setBounds(15, 10, 90, 90); // nhớ cập nhật kích thước
        panelDon.add(lblIcon);

        // Thông tin đơn
        Font fontLon = new Font("Arial", Font.BOLD, 16);
        Font fontThuong = new Font("Arial", Font.PLAIN, 15);

        JLabel lblMaDon = new JLabel("Mã đơn: " + maDon);
        lblMaDon.setFont(fontLon);
        lblMaDon.setBounds(115, 10, 300, 25);
        panelDon.add(lblMaDon);

        JLabel lblSoBan = new JLabel("Số bàn: " + soBan);
        lblSoBan.setFont(fontThuong);
        lblSoBan.setBounds(115, 40, 150, 25);
        panelDon.add(lblSoBan);

        JLabel lblSoMon = new JLabel("Số món: " + soMon);
        lblSoMon.setFont(fontThuong);
        lblSoMon.setBounds(115, 65, 150, 25);
        panelDon.add(lblSoMon);

        JLabel lblGioDen = new JLabel("Giờ đến: " + gioDen);
        lblGioDen.setFont(fontThuong);
        lblGioDen.setBounds(275, 40, 200, 25);
        panelDon.add(lblGioDen);

        JLabel lblSDT = new JLabel("Điện thoại: " + soDT);
        lblSDT.setFont(fontThuong);
        lblSDT.setBounds(275, 65, 200, 25);
        panelDon.add(lblSDT);
   
        

        return panelDon;
    }
}
