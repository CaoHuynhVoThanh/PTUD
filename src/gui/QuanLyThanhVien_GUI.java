package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import com.toedter.calendar.JCalendar;

public class QuanLyThanhVien_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField maThanhVienField;
    private JTextField maKhachHangField;
    private JTextField emailField;
    private JTextField ngaySinhField;
    private JTable table;
    private JTextField searchField;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuanLyThanhVien_GUI frame = new QuanLyThanhVien_GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public QuanLyThanhVien_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setSize(1537, 864);
        this.setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Thanh trên cùng (header)
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(UIManager.getColor("Button.foreground"));
        panel_1.setBounds(0, 0, 1556, 136);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel logo = new JLabel("New label");
        logo.setIcon(new ImageIcon("src\\images\\icon.png"));
        logo.setBounds(66, 22, 247, 89);
        panel_1.add(logo);

        JLabel avt = new JLabel("");
        ImageIcon originalIcon = new ImageIcon("src\\images\\avt.png");
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

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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

        // Thanh bên trái (sidebar)
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

        JMenuItem mi_QuanLy = new JMenuItem("              QUẢN LÝ");
        mi_QuanLy.setSelected(true);
        mi_QuanLy.setHorizontalAlignment(SwingConstants.LEFT);
        mi_QuanLy.setForeground(Color.WHITE);
        mi_QuanLy.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_QuanLy.setBackground(new Color(255, 153, 0));
        mi_QuanLy.setBounds(20, 417, 291, 61);
        panel.add(mi_QuanLy);

        JMenuItem mi_ThongKe = new JMenuItem("              THỐNG KÊ");
        mi_ThongKe.setSelected(true);
        mi_ThongKe.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThongKe.setForeground(Color.WHITE);
        mi_ThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThongKe.setBackground(new Color(255, 153, 0));
        mi_ThongKe.setBounds(20, 478, 291, 61);
        panel.add(mi_ThongKe);

        JMenuItem mi_ThongKe_1 = new JMenuItem("              TRỢ GIÚP");
        mi_ThongKe_1.setSelected(true);
        mi_ThongKe_1.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThongKe_1.setForeground(Color.WHITE);
        mi_ThongKe_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThongKe_1.setBackground(new Color(255, 153, 0));
        mi_ThongKe_1.setBounds(20, 541, 291, 61);
        panel.add(mi_ThongKe_1);

        // Sự kiện cho các nút trong sidebar
        mi_TrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Trang Chủ");
            }
        });

        mi_DatBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Đặt Bàn");
            }
        });

        mi_NhanBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Nhận Bàn");
            }
        });

        mi_GoiMon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Gọi Món");
            }
        });

        mi_ThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Thanh Toán");
            }
        });

        mi_LichSu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Lịch Sử");
            }
        });

        mi_QuanLy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bạn đang ở giao diện Quản Lý");
            }
        });

        mi_ThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Thống Kê");
            }
        });

        mi_ThongKe_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Trợ Giúp");
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Đăng xuất thành công!");
                    System.exit(0);
                }
            }
        });

        // Panel chính chứa nội dung quản lý thành viên
        JPanel panel_trangchu = new JPanel();
        panel_trangchu.setBackground(new Color(255, 255, 255));
        panel_trangchu.setBounds(285, 133, 1254, 704);
        contentPane.add(panel_trangchu);
        panel_trangchu.setLayout(null);

        // Tiêu đề "QUẢN LÝ THÀNH VIÊN"
        JLabel titleLabel = new JLabel("QUẢN LÝ THÀNH VIÊN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBounds(0, 10, 1254, 40);
        panel_trangchu.add(titleLabel);

        // Panel chứa các ô nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 60, 1214, 173);
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setLayout(null);
        panel_trangchu.add(inputPanel);

        // Bên trái: "Mã thành viên" và "Email"
        JLabel maThanhVienLabel = new JLabel("Mã thành viên");
        maThanhVienLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        maThanhVienLabel.setBounds(240, 20, 100, 30);
        inputPanel.add(maThanhVienLabel);

        maThanhVienField = new JTextField();
        maThanhVienField.setBounds(340, 20, 150, 30);
        inputPanel.add(maThanhVienField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailLabel.setBounds(240, 70, 80, 30);
        inputPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(340, 70, 150, 30);
        inputPanel.add(emailField);

        // Bên phải: "Mã khách hàng" và "Ngày sinh"
        JLabel maKhachHangLabel = new JLabel("Mã khách hàng");
        maKhachHangLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        maKhachHangLabel.setBounds(651, 20, 100, 30);
        inputPanel.add(maKhachHangLabel);

        maKhachHangField = new JTextField();
        maKhachHangField.setBounds(761, 20, 150, 30);
        inputPanel.add(maKhachHangField);

        JLabel ngaySinhLabel = new JLabel("Ngày sinh");
        ngaySinhLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        ngaySinhLabel.setBounds(651, 70, 80, 30);
        inputPanel.add(ngaySinhLabel);

        ngaySinhField = new JTextField();
        ngaySinhField.setBounds(761, 70, 120, 30);
        ngaySinhField.setEditable(false);
        inputPanel.add(ngaySinhField);

        // Nút lịch nhỏ bên cạnh ô "Ngày sinh"
        JButton calendarButton = new JButton();
        calendarButton.setIcon(new ImageIcon("src/images/calendar.png")); 
        calendarButton.setBounds(881, 70, 30, 30);
        inputPanel.add(calendarButton);

        // Sự kiện cho nút lịch: Mở dialog chứa JCalendar
        calendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCalendarDialog(ngaySinhField);
            }
        });

        // Các nút "Thêm", "Cập nhật", "Xóa rỗng"
        JButton themButton = new JButton("Thêm");
        themButton.setBackground(new Color(34, 139, 34));
        themButton.setForeground(Color.WHITE);
        themButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        themButton.setBounds(370, 116, 120, 35);
        inputPanel.add(themButton);

        JButton capNhatButton = new JButton("Cập nhật");
        capNhatButton.setBackground(new Color(255, 165, 0));
        capNhatButton.setForeground(Color.WHITE);
        capNhatButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        capNhatButton.setBounds(510, 116, 120, 35);
        inputPanel.add(capNhatButton);

        JButton xoaRongButton = new JButton("Xóa rỗng");
        xoaRongButton.setBackground(new Color(0, 123, 255));
        xoaRongButton.setForeground(Color.WHITE);
        xoaRongButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        xoaRongButton.setBounds(650, 116, 120, 35);
        inputPanel.add(xoaRongButton);

        // Ô tìm kiếm và nút "Tìm", "Tải lại"
        searchField = new JTextField("Nhập từ khóa tìm kiếm...");
        searchField.setForeground(Color.GRAY);
        searchField.setBounds(20, 250, 300, 30);
        panel_trangchu.add(searchField);

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Nhập từ khóa tìm kiếm...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().trim().isEmpty()) {
                    searchField.setText("Nhập từ khóa tìm kiếm...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        JButton timButton = new JButton("Tìm");
        timButton.setBackground(Color.BLACK);
        timButton.setForeground(Color.WHITE);
        timButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        timButton.setBounds(330, 250, 80, 30);
        panel_trangchu.add(timButton);

        JButton taiLaiButton = new JButton("Tải lại");
        taiLaiButton.setBackground(Color.BLACK);
        taiLaiButton.setForeground(Color.WHITE);
        taiLaiButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        taiLaiButton.setBounds(1134, 250, 100, 30);
        panel_trangchu.add(taiLaiButton);

        // Bảng dữ liệu
        String[] columns = {"Mã thành viên", "Mã khách hàng", "Email", "Ngày sinh"};
        Object[][] data = {
            {"25000001", "250323001", "khachhang@gmail.com", "22/10/2004"},
            {"25000002", "250323002", "khachhang@gmail.com", "01/01/2004"},
            {"25000003", "250323003", "khachhang@gmail.com", "25/05/2004"}
        };
        table = new JTable(data, columns);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 300, 1214, 404);
        panel_trangchu.add(scrollPane);

        // Sự kiện cho nút "Thêm"
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maThanhVien = maThanhVienField.getText();
                String maKhachHang = maKhachHangField.getText();
                String email = emailField.getText();
                String ngaySinh = ngaySinhField.getText();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{maThanhVien, maKhachHang, email, ngaySinh});
            }
        });

        // Sự kiện cho nút "Cập nhật"
        capNhatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    table.setValueAt(maThanhVienField.getText(), selectedRow, 0);
                    table.setValueAt(maKhachHangField.getText(), selectedRow, 1);
                    table.setValueAt(emailField.getText(), selectedRow, 2);
                    table.setValueAt(ngaySinhField.getText(), selectedRow, 3);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật.");
                }
            }
        });

        // Sự kiện cho nút "Xóa rỗng"
        xoaRongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maThanhVienField.setText("");
                maKhachHangField.setText("");
                emailField.setText("");
                ngaySinhField.setText("");
            }
        });

        // Sự kiện cho nút "Tìm"
        timButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().toLowerCase();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                for (Object[] row : data) {
                    if (row[0].toString().toLowerCase().contains(keyword) ||
                        row[1].toString().toLowerCase().contains(keyword) ||
                        row[2].toString().toLowerCase().contains(keyword) ||
                        row[3].toString().toLowerCase().contains(keyword)) {
                        model.addRow(row);
                    }
                }
            }
        });

        // Sự kiện cho nút "Tải lại"
        taiLaiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                for (Object[] row : data) {
                    model.addRow(row);
                }
                searchField.setText("Nhập từ khóa tìm kiếm...");
                searchField.setForeground(Color.GRAY);
            }
        });
    }

    private void showCalendarDialog(JTextField targetField) {
        JDialog dialog = new JDialog(this, "Chọn ngày", true);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar();
        dialog.add(calendar, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDate = dateFormat.format(calendar.getDate());
                targetField.setText(selectedDate);
                dialog.dispose();
            }
        });
        buttonPanel.add(confirmButton);

        JButton cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}