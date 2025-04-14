package gui;

import dao.QuanLyThanhVien_DAO;
import connectDB.ConnectDB;
import entities.ThanhVien;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.toedter.calendar.JDateChooser;

public class QuanLyThanhVien_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField maTVField;
    private JTextField tenKHField;
    private JTextField emailField;
    private JDateChooser ngaySinhChooser;
    private JTextField hangTheField;
    private JTextField diemTichLuyField;
    private JDateChooser ngayCapChooser;
    private JTable table;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private QuanLyThanhVien_DAO dao;
    private DefaultTableModel model;

    // Các trường lọc
    private JTextField filterMaTVField;
    private JTextField filterTenKHField;
    private JTextField filterEmailField;
    private JDateChooser filterNgaySinhChooser;
    private JComboBox<String> filterHangTheCombo;
    private JTextField filterDiemTichLuyField;
    private JDateChooser filterNgayCapChooser;
    private JTextField filterNgaySinhPlaceholder;
    private JTextField filterNgayCapPlaceholder;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QuanLyThanhVien_GUI frame = new QuanLyThanhVien_GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public QuanLyThanhVien_GUI() {
        Connection conn = ConnectDB.getInstance().getConnection();
        dao = new QuanLyThanhVien_DAO(conn);

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
        mi_DatBan.setHorizontalAlignment(SwingConstants.LEFT);
        mi_DatBan.setForeground(Color.WHITE);
        mi_DatBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_DatBan.setBackground(new Color(255, 153, 0));
        mi_DatBan.setBounds(20, 109, 291, 61);
        panel.add(mi_DatBan);

        JMenuItem mi_NhanBan = new JMenuItem("              NHẬN BÀN");
        mi_NhanBan.setHorizontalAlignment(SwingConstants.LEFT);
        mi_NhanBan.setForeground(Color.WHITE);
        mi_NhanBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_NhanBan.setBackground(new Color(255, 153, 0));
        mi_NhanBan.setBounds(20, 171, 291, 61);
        panel.add(mi_NhanBan);

        JMenuItem mi_GoiMon = new JMenuItem("              GỌI MÓN");
        mi_GoiMon.setHorizontalAlignment(SwingConstants.LEFT);
        mi_GoiMon.setForeground(Color.WHITE);
        mi_GoiMon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_GoiMon.setBackground(new Color(255, 153, 0));
        mi_GoiMon.setBounds(20, 233, 291, 61);
        panel.add(mi_GoiMon);

        JMenuItem mi_ThanhToan = new JMenuItem("              THANH TOÁN");
        mi_ThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThanhToan.setForeground(Color.WHITE);
        mi_ThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThanhToan.setBackground(new Color(255, 153, 0));
        mi_ThanhToan.setBounds(20, 293, 291, 61);
        panel.add(mi_ThanhToan);

        JMenuItem mi_LichSu = new JMenuItem("              LỊCH SỬ");
        mi_LichSu.setHorizontalAlignment(SwingConstants.LEFT);
        mi_LichSu.setForeground(Color.WHITE);
        mi_LichSu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_LichSu.setBackground(new Color(255, 153, 0));
        mi_LichSu.setBounds(20, 353, 291, 61);
        panel.add(mi_LichSu);

        JMenuItem mi_QuanLy = new JMenuItem("              QUẢN LÝ");
        mi_QuanLy.setHorizontalAlignment(SwingConstants.LEFT);
        mi_QuanLy.setForeground(Color.WHITE);
        mi_QuanLy.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_QuanLy.setBackground(new Color(255, 153, 0));
        mi_QuanLy.setBounds(20, 417, 291, 61);
        panel.add(mi_QuanLy);

        JMenuItem mi_ThongKe = new JMenuItem("              THỐNG KÊ");
        mi_ThongKe.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThongKe.setForeground(Color.WHITE);
        mi_ThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThongKe.setBackground(new Color(255, 153, 0));
        mi_ThongKe.setBounds(20, 478, 291, 61);
        panel.add(mi_ThongKe);

        JMenuItem mi_ThongKe_1 = new JMenuItem("              TRỢ GIÚP");
        mi_ThongKe_1.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThongKe_1.setForeground(Color.WHITE);
        mi_ThongKe_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThongKe_1.setBackground(new Color(255, 153, 0));
        mi_ThongKe_1.setBounds(20, 541, 291, 61);
        panel.add(mi_ThongKe_1);

        // Sự kiện cho các nút trong sidebar
        mi_TrangChu.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Trang Chủ"));
        mi_DatBan.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Đặt Bàn"));
        mi_NhanBan.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Nhận Bàn"));
        mi_GoiMon.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Gọi Món"));
        mi_ThanhToan.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Thanh Toán"));
        mi_LichSu.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Lịch Sử"));
        mi_QuanLy.addActionListener(e -> JOptionPane.showMessageDialog(null, "Bạn đang ở giao diện Quản Lý"));
        mi_ThongKe.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Thống Kê"));
        mi_ThongKe_1.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chuyển đến giao diện Trợ Giúp"));

        btnNewButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Đăng xuất thành công!");
                System.exit(0);
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
        inputPanel.setBounds(20, 60, 1214, 123);
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setLayout(null);
        panel_trangchu.add(inputPanel);

        // Mã thành viên
        JLabel maTVLabel = new JLabel("Mã thành viên");
        maTVLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        maTVLabel.setBounds(24, 20, 100, 30);
        inputPanel.add(maTVLabel);
        maTVField = new JTextField();
        maTVField.setBounds(134, 20, 100, 30);
        maTVField.setEditable(false);
        inputPanel.add(maTVField);

        // Tên khách hàng
        JLabel tenKHLabel = new JLabel("Tên khách hàng");
        tenKHLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tenKHLabel.setBounds(256, 20, 134, 30);
        inputPanel.add(tenKHLabel);
        tenKHField = new JTextField();
        tenKHField.setBounds(386, 20, 150, 30);
        inputPanel.add(tenKHField);

        // Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailLabel.setBounds(561, 20, 80, 30);
        inputPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(630, 20, 150, 30);
        inputPanel.add(emailField);

        // Ngày sinh
        JLabel ngaySinhLabel = new JLabel("Ngày sinh");
        ngaySinhLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        ngaySinhLabel.setBounds(790, 20, 80, 30);
        inputPanel.add(ngaySinhLabel);
        ngaySinhChooser = new JDateChooser();
        ngaySinhChooser.setDateFormatString("dd/MM/yyyy");
        ngaySinhChooser.setBounds(870, 20, 120, 30);
        inputPanel.add(ngaySinhChooser);

        // Hạng thẻ
        JLabel hangTheLabel = new JLabel("Hạng thẻ");
        hangTheLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        hangTheLabel.setBounds(1000, 20, 80, 30);
        inputPanel.add(hangTheLabel);
        hangTheField = new JTextField();
        hangTheField.setBounds(1080, 20, 100, 30);
        hangTheField.setEditable(false);
        inputPanel.add(hangTheField);

        // Điểm tích lũy
        JLabel diemTichLuyLabel = new JLabel("Điểm tích lũy");
        diemTichLuyLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        diemTichLuyLabel.setBounds(24, 64, 100, 30);
        inputPanel.add(diemTichLuyLabel);
        diemTichLuyField = new JTextField();
        diemTichLuyField.setBounds(134, 64, 100, 30);
        diemTichLuyField.setEditable(false);
        inputPanel.add(diemTichLuyField);

        // Ngày cấp
        JLabel ngayCapLabel = new JLabel("Ngày cấp");
        ngayCapLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        ngayCapLabel.setBounds(302, 64, 80, 30);
        inputPanel.add(ngayCapLabel);
        ngayCapChooser = new JDateChooser();
        ngayCapChooser.setDateFormatString("dd/MM/yyyy");
        ngayCapChooser.setBounds(386, 64, 120, 30);
        ngayCapChooser.setEnabled(false);
        inputPanel.add(ngayCapChooser);

        // Các nút "Thêm", "Cập nhật", "Xóa rỗng"
        JButton themButton = new JButton("Thêm");
        themButton.setBackground(new Color(34, 139, 34));
        themButton.setForeground(Color.WHITE);
        themButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        themButton.setBounds(561, 64, 120, 35);
        inputPanel.add(themButton);

        JButton capNhatButton = new JButton("Cập nhật");
        capNhatButton.setBackground(new Color(255, 165, 0));
        capNhatButton.setForeground(Color.WHITE);
        capNhatButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        capNhatButton.setBounds(731, 64, 120, 35);
        inputPanel.add(capNhatButton);

        JButton xoaRongButton = new JButton("Xóa rỗng");
        xoaRongButton.setBackground(new Color(0, 123, 255));
        xoaRongButton.setForeground(Color.WHITE);
        xoaRongButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        xoaRongButton.setBounds(891, 64, 120, 35);
        inputPanel.add(xoaRongButton);

        // Panel lọc
        JPanel filterPanel = new JPanel();
        filterPanel.setBounds(20, 199, 1214, 60);
        filterPanel.setBackground(new Color(240, 240, 240));
        filterPanel.setLayout(null);
        panel_trangchu.add(filterPanel);

        JLabel filterLabel = new JLabel("Lọc:");
        filterLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        filterLabel.setBounds(10, 15, 40, 30);
        filterPanel.add(filterLabel);

        filterMaTVField = new JTextField("Mã TV");
        filterMaTVField.setForeground(Color.GRAY);
        filterMaTVField.setBounds(60, 15, 100, 30);
        filterPanel.add(filterMaTVField);
        filterMaTVField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (filterMaTVField.getText().equals("Mã TV")) {
                    filterMaTVField.setText("");
                    filterMaTVField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (filterMaTVField.getText().trim().isEmpty()) {
                    filterMaTVField.setText("Mã TV");
                    filterMaTVField.setForeground(Color.GRAY);
                }
            }
        });

        filterTenKHField = new JTextField("Tên KH");
        filterTenKHField.setForeground(Color.GRAY);
        filterTenKHField.setBounds(170, 15, 100, 30);
        filterPanel.add(filterTenKHField);
        filterTenKHField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (filterTenKHField.getText().equals("Tên KH")) {
                    filterTenKHField.setText("");
                    filterTenKHField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (filterTenKHField.getText().trim().isEmpty()) {
                    filterTenKHField.setText("Tên KH");
                    filterTenKHField.setForeground(Color.GRAY);
                }
            }
        });

        filterEmailField = new JTextField("Email");
        filterEmailField.setForeground(Color.GRAY);
        filterEmailField.setBounds(280, 15, 100, 30);
        filterPanel.add(filterEmailField);
        filterEmailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (filterEmailField.getText().equals("Email")) {
                    filterEmailField.setText("");
                    filterEmailField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (filterEmailField.getText().trim().isEmpty()) {
                    filterEmailField.setText("Email");
                    filterEmailField.setForeground(Color.GRAY);
                }
            }
        });

        // Ngày sinh (lọc)
        filterNgaySinhChooser = new JDateChooser();
        filterNgaySinhChooser.setDateFormatString("dd/MM/yyyy");
        filterNgaySinhChooser.setBounds(390, 15, 100, 30);
        filterPanel.add(filterNgaySinhChooser);

        // Thêm placeholder cho Ngày sinh
        filterNgaySinhPlaceholder = new JTextField("Ngày sinh");
        filterNgaySinhPlaceholder.setForeground(Color.GRAY);
        filterNgaySinhPlaceholder.setBounds(390, 15, 100, 30);
        filterNgaySinhPlaceholder.setBackground(filterNgaySinhChooser.getBackground());
        filterPanel.add(filterNgaySinhPlaceholder);

        // Sự kiện để hiển thị/ẩn placeholder
        filterNgaySinhPlaceholder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                filterNgaySinhPlaceholder.setVisible(false);
                filterNgaySinhChooser.requestFocus();
            }
        });
        filterNgaySinhChooser.getDateEditor().addPropertyChangeListener("date", evt -> {
            if (filterNgaySinhChooser.getDate() == null) {
                filterNgaySinhPlaceholder.setVisible(true);
            } else {
                filterNgaySinhPlaceholder.setVisible(false);
            }
        });

        // Hạng thẻ
        filterHangTheCombo = new JComboBox<>(new String[]{"Tất cả", "Silver", "Gold", "Diamond"});
        filterHangTheCombo.setBounds(530, 15, 100, 30);
        filterPanel.add(filterHangTheCombo);

        // Điểm tích lũy
        filterDiemTichLuyField = new JTextField("Điểm");
        filterDiemTichLuyField.setForeground(Color.GRAY);
        filterDiemTichLuyField.setBounds(640, 15, 100, 30);
        filterPanel.add(filterDiemTichLuyField);
        filterDiemTichLuyField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (filterDiemTichLuyField.getText().equals("Điểm")) {
                    filterDiemTichLuyField.setText("");
                    filterDiemTichLuyField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (filterDiemTichLuyField.getText().trim().isEmpty()) {
                    filterDiemTichLuyField.setText("Điểm");
                    filterDiemTichLuyField.setForeground(Color.GRAY);
                }
            }
        });

        // Ngày cấp (lọc)
        filterNgayCapChooser = new JDateChooser();
        filterNgayCapChooser.setDateFormatString("dd/MM/yyyy");
        filterNgayCapChooser.setBounds(750, 15, 100, 30);
        filterPanel.add(filterNgayCapChooser);

        // Thêm placeholder cho Ngày cấp
        filterNgayCapPlaceholder = new JTextField("Ngày cấp");
        filterNgayCapPlaceholder.setForeground(Color.GRAY);
        filterNgayCapPlaceholder.setBounds(750, 15, 100, 30);
        filterNgayCapPlaceholder.setBackground(filterNgayCapChooser.getBackground());
        filterPanel.add(filterNgayCapPlaceholder);

        // Sự kiện để hiển thị/ẩn placeholder
        filterNgayCapPlaceholder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                filterNgayCapPlaceholder.setVisible(false);
                filterNgayCapChooser.requestFocus();
            }
        });
        filterNgayCapChooser.getDateEditor().addPropertyChangeListener("date", evt -> {
            if (filterNgayCapChooser.getDate() == null) {
                filterNgayCapPlaceholder.setVisible(true);
            } else {
                filterNgayCapPlaceholder.setVisible(false);
            }
        });

        JButton applyFilterButton = new JButton("Áp dụng");
        applyFilterButton.setBackground(new Color(34, 139, 34));
        applyFilterButton.setForeground(Color.WHITE);
        applyFilterButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        applyFilterButton.setBounds(878, 15, 100, 30);
        filterPanel.add(applyFilterButton);

        JButton taiLaiButton = new JButton("Tải lại");
        taiLaiButton.setBackground(new Color(255, 165, 0));
        taiLaiButton.setForeground(Color.WHITE);
        taiLaiButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        taiLaiButton.setBounds(988, 15, 89, 30);
        filterPanel.add(taiLaiButton);

        JButton khoiPhucButton = new JButton("Khôi phục");
        khoiPhucButton.setBackground(Color.black);
        khoiPhucButton.setForeground(Color.WHITE);
        khoiPhucButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        khoiPhucButton.setBounds(1087, 15, 117, 30);
        filterPanel.add(khoiPhucButton);

        // Bảng dữ liệu
        String[] columns = {"Mã thành viên", "Tên khách hàng", "Email", "Ngày sinh", "Hạng thẻ", "Điểm tích lũy", "Ngày cấp"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 278, 1214, 426);
        panel_trangchu.add(scrollPane);

        // Load data
        loadData();

        // Sự kiện cho nút "Thêm"
        themButton.addActionListener(e -> {
            try {
                String maTV = dao.generateMaTV();
                String tenKH = tenKHField.getText();
                String email = emailField.getText();
                Date ngaySinh = ngaySinhChooser.getDate();
                if (tenKH.isEmpty() || email.isEmpty() || ngaySinh == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ Tên KH, Email và Ngày sinh!");
                    return;
                }
                String hangThe = "Silver";
                int diemTichLuy = 0;
                Date ngayCap = new Date();

                ThanhVien tv = new ThanhVien(maTV, "25011001", tenKH, email, ngaySinh, hangThe, diemTichLuy, ngayCap);
                dao.addThanhVien(tv);

                maTVField.setText(maTV);
                hangTheField.setText(hangThe);
                diemTichLuyField.setText(String.valueOf(diemTichLuy));
                ngayCapChooser.setDate(ngayCap);
                loadData();
                JOptionPane.showMessageDialog(null, "Thêm thành viên thành công!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm thành viên!");
            }
        });

        // Sự kiện cho nút "Cập nhật"
        capNhatButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    String maTV = (String) model.getValueAt(selectedRow, 0);
                    String tenKH = tenKHField.getText();
                    String email = emailField.getText();
                    Date ngaySinh = ngaySinhChooser.getDate();
                    if (tenKH.isEmpty() || email.isEmpty() || ngaySinh == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ Tên KH, Email và Ngày sinh!");
                        return;
                    }
                    String hangThe = (String) model.getValueAt(selectedRow, 4);
                    int diemTichLuy = (int) model.getValueAt(selectedRow, 5);
                    Date ngayCap = dateFormat.parse((String) model.getValueAt(selectedRow, 6));

                    ThanhVien tv = new ThanhVien(maTV, null, tenKH, email, ngaySinh, hangThe, diemTichLuy, ngayCap);
                    dao.updateThanhVien(tv);
                    loadData();
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật.");
            }
        });

        // Sự kiện cho nút "Xóa rỗng"
        xoaRongButton.addActionListener(e -> {
            maTVField.setText("");
            tenKHField.setText("");
            emailField.setText("");
            ngaySinhChooser.setDate(null);
            hangTheField.setText("");
            diemTichLuyField.setText("");
            ngayCapChooser.setDate(null);
        });

        // Sự kiện cho nút "Áp dụng" lọc
        applyFilterButton.addActionListener(e -> {
            model.setRowCount(0);
            try {
                List<ThanhVien> list = dao.getAllThanhVien();
                for (ThanhVien tv : list) {
                    boolean match = true;
                    if (!filterMaTVField.getText().equals("Mã TV") && !tv.getMaTV().toLowerCase().contains(filterMaTVField.getText().toLowerCase()))
                        match = false;
                    if (!filterTenKHField.getText().equals("Tên KH") && !tv.getTenKH().toLowerCase().contains(filterTenKHField.getText().toLowerCase()))
                        match = false;
                    if (!filterEmailField.getText().equals("Email") && !tv.getEmail().toLowerCase().contains(filterEmailField.getText().toLowerCase()))
                        match = false;
                    if (filterNgaySinhChooser.getDate() != null && !dateFormat.format(tv.getNgaySinh()).equals(dateFormat.format(filterNgaySinhChooser.getDate())))
                        match = false;
                    if (filterHangTheCombo.getSelectedIndex() > 0 && !tv.getHangThe().equals(filterHangTheCombo.getSelectedItem()))
                        match = false;
                    if (!filterDiemTichLuyField.getText().equals("Điểm") && !filterDiemTichLuyField.getText().isEmpty()) {
                        try {
                            if (tv.getDiemTichLuy() != Integer.parseInt(filterDiemTichLuyField.getText()))
                                match = false;
                        } catch (NumberFormatException ex) {
                            match = false;
                        }
                    }
                    if (filterNgayCapChooser.getDate() != null && !dateFormat.format(tv.getNgayCap()).equals(dateFormat.format(filterNgayCapChooser.getDate())))
                        match = false;

                    if (match) {
                        model.addRow(new Object[]{tv.getMaTV(), tv.getTenKH(), tv.getEmail(), dateFormat.format(tv.getNgaySinh()), tv.getHangThe(), tv.getDiemTichLuy(), dateFormat.format(tv.getNgayCap())});
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Sự kiện cho nút "Tải lại"
        taiLaiButton.addActionListener(e -> {
            loadData();
            resetFilterFields();
        });

        // Sự kiện cho nút "Khôi phục"
        khoiPhucButton.addActionListener(e -> resetFilterFields());

        // Sự kiện khi chọn dòng trong JTable
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                maTVField.setText((String) model.getValueAt(selectedRow, 0));
                tenKHField.setText((String) model.getValueAt(selectedRow, 1));
                emailField.setText((String) model.getValueAt(selectedRow, 2));
                try {
                    Date ngaySinh = dateFormat.parse((String) model.getValueAt(selectedRow, 3));
                    ngaySinhChooser.setDate(ngaySinh);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                hangTheField.setText((String) model.getValueAt(selectedRow, 4));
                diemTichLuyField.setText(String.valueOf(model.getValueAt(selectedRow, 5)));
                try {
                    Date ngayCap = dateFormat.parse((String) model.getValueAt(selectedRow, 6));
                    ngayCapChooser.setDate(ngayCap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            List<ThanhVien> list = dao.getAllThanhVien();
            list.sort((tv1, tv2) -> tv1.getMaTV().compareTo(tv2.getMaTV()));
            for (ThanhVien tv : list) {
                model.addRow(new Object[]{tv.getMaTV(), tv.getTenKH(), tv.getEmail(), dateFormat.format(tv.getNgaySinh()), tv.getHangThe(), tv.getDiemTichLuy(), dateFormat.format(tv.getNgayCap())});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resetFilterFields() {
        filterMaTVField.setText("Mã TV");
        filterMaTVField.setForeground(Color.GRAY);
        filterTenKHField.setText("Tên KH");
        filterTenKHField.setForeground(Color.GRAY);
        filterEmailField.setText("Email");
        filterEmailField.setForeground(Color.GRAY);
        filterNgaySinhChooser.setDate(null);
        filterNgaySinhPlaceholder.setVisible(true);
        filterHangTheCombo.setSelectedIndex(0);
        filterDiemTichLuyField.setText("Điểm");
        filterDiemTichLuyField.setForeground(Color.GRAY);
        filterNgayCapChooser.setDate(null);
        filterNgayCapPlaceholder.setVisible(true);
    }
}