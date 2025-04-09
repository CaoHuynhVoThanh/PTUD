package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class QuanLyBan_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField maBanField;
    private JComboBox<String> soChoNgoiComboBox;
    private JComboBox<String> khuVucComboBox;
    private JComboBox<String> tinhTrangComboBox;
    private JTable table;
    private JTextField searchField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuanLyBan_GUI frame = new QuanLyBan_GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public QuanLyBan_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setSize(1537, 864);
        this.setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Thanh trên cùng (header) - giữ nguyên từ LichSu_GUI
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

        // Định dạng ngày và giờ
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Cập nhật ngày và giờ mỗi giây
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

        // Thanh bên trái (sidebar) - giữ nguyên từ LichSu_GUI
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

        JMenuItem mi_ThongKe = new JMenuItem("              THỐNG KÊ");
        mi_ThongKe.setSelected(true);
        mi_ThongKe.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThongKe.setForeground(Color.WHITE);
        mi_ThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThongKe.setBackground(new Color(255, 153, 0));
        mi_ThongKe.setBounds(20, 478, 291, 61);
        panel.add(mi_ThongKe);

        JMenuItem mi_QuanLy = new JMenuItem("              QUẢN LÝ");
        mi_QuanLy.setSelected(true);
        mi_QuanLy.setHorizontalAlignment(SwingConstants.LEFT);
        mi_QuanLy.setForeground(Color.WHITE);
        mi_QuanLy.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_QuanLy.setBackground(new Color(255, 153, 0));
        mi_QuanLy.setBounds(20, 417, 291, 61);
        panel.add(mi_QuanLy);

        JMenuItem mi_ThongKe_1 = new JMenuItem("              TRỢ GIÚP");
        mi_ThongKe_1.setSelected(true);
        mi_ThongKe_1.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThongKe_1.setForeground(Color.WHITE);
        mi_ThongKe_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThongKe_1.setBackground(new Color(255, 153, 0));
        mi_ThongKe_1.setBounds(20, 541, 291, 61);
        panel.add(mi_ThongKe_1);

        // Panel chính chứa nội dung quản lý bàn
        JPanel panel_trangchu = new JPanel();
        panel_trangchu.setBackground(new Color(255, 255, 255));
        panel_trangchu.setBounds(285, 133, 1254, 704);
        contentPane.add(panel_trangchu);
        panel_trangchu.setLayout(null);

        // Tiêu đề "QUẢN LÝ BÀN"
        JLabel titleLabel = new JLabel("QUẢN LÝ BÀN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBounds(0, 10, 1201, 40);
        panel_trangchu.add(titleLabel);

        // Panel chứa các ô nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 60, 1214, 180); // Tăng chiều cao để chứa các nút bên dưới
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setLayout(null);
        panel_trangchu.add(inputPanel);

        // Bên trái: "Mã bàn" và "Khu vực"
        JLabel maBanLabel = new JLabel("Mã bàn");
        maBanLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        maBanLabel.setBounds(235, 20, 80, 30);
        inputPanel.add(maBanLabel);

        maBanField = new JTextField();
        maBanField.setBounds(357, 23, 150, 30);
        inputPanel.add(maBanField);

        JLabel khuVucLabel = new JLabel("Khu vực");
        khuVucLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        khuVucLabel.setBounds(235, 70, 80, 30);
        inputPanel.add(khuVucLabel);

        String[] khuVucOptions = {"Tầng 1", "Tầng 2", "Tầng 3"};
        khuVucComboBox = new JComboBox<>(khuVucOptions);
        khuVucComboBox.setBounds(357, 72, 150, 30);
        inputPanel.add(khuVucComboBox);

        // Bên phải: "Số chỗ ngồi" và "Tình trạng"
        JLabel soChoNgoiLabel = new JLabel("Số chỗ ngồi");
        soChoNgoiLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        soChoNgoiLabel.setBounds(680, 20, 100, 30);
        inputPanel.add(soChoNgoiLabel);

        String[] soChoNgoiOptions = {"2", "4", "8"};
        soChoNgoiComboBox = new JComboBox<>(soChoNgoiOptions);
        soChoNgoiComboBox.setBounds(798, 22, 60, 30);
        inputPanel.add(soChoNgoiComboBox);

        JLabel tinhTrangLabel = new JLabel("Tình trạng");
        tinhTrangLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tinhTrangLabel.setBounds(680, 70, 80, 30);
        inputPanel.add(tinhTrangLabel);

        String[] tinhTrangOptions = {"Đang trống", "Đã được đặt"};
        tinhTrangComboBox = new JComboBox<>(tinhTrangOptions);
        tinhTrangComboBox.setBounds(798, 72, 150, 30);
        inputPanel.add(tinhTrangComboBox);

        // Các nút "Thêm", "Cập nhật", "Thiết lập lại"
        JButton themButton = new JButton("Thêm");
        themButton.setBackground(new Color(34, 139, 34));
        themButton.setForeground(Color.WHITE);
        themButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        themButton.setBounds(387, 120, 120, 35);
        inputPanel.add(themButton);

        JButton capNhatButton = new JButton("Cập nhật");
        capNhatButton.setBackground(new Color(255, 165, 0));
        capNhatButton.setForeground(Color.WHITE);
        capNhatButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        capNhatButton.setBounds(535, 120, 120, 35);
        inputPanel.add(capNhatButton);

        JButton thietLapLaiButton = new JButton("Thiết lập lại");
        thietLapLaiButton.setBackground(new Color(0, 123, 255));
        thietLapLaiButton.setForeground(Color.WHITE);
        thietLapLaiButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        thietLapLaiButton.setBounds(680, 120, 120, 35);
        inputPanel.add(thietLapLaiButton);

        // Ô tìm kiếm và nút "Tìm", "Tải lại"
        searchField = new JTextField("Nhập từ khóa tìm kiếm...");
        searchField.setForeground(Color.GRAY);
        searchField.setBounds(20, 260, 300, 30);
        panel_trangchu.add(searchField);

        // Thêm FocusListener cho ô tìm kiếm
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
        timButton.setBounds(330, 260, 80, 30);
        panel_trangchu.add(timButton);

        JButton taiLaiButton = new JButton("Tải lại");
        taiLaiButton.setBackground(Color.BLACK);
        taiLaiButton.setForeground(Color.WHITE);
        taiLaiButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        taiLaiButton.setBounds(1134, 260, 100, 30);
        panel_trangchu.add(taiLaiButton);

        // Bảng dữ liệu
        String[] columns = {"Mã bàn", "Số chỗ ngồi", "Khu vực", "Trạng thái"};
        Object[][] data = {
            {"A1-01", "2", "Tầng 1", "Đang trống"},
            {"B2-01", "4", "Tầng 2", "Đang trống"},
            {"C3-01", "8", "Tầng 3", "Đã được đặt"}
        };
        table = new JTable(data, columns);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 310, 1214, 374);
        panel_trangchu.add(scrollPane);

        // Sự kiện cho nút "Thêm"
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maBan = maBanField.getText();
                String soChoNgoi = (String) soChoNgoiComboBox.getSelectedItem();
                String khuVuc = (String) khuVucComboBox.getSelectedItem();
                String tinhTrang = (String) tinhTrangComboBox.getSelectedItem();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{maBan, soChoNgoi, khuVuc, tinhTrang});
            }
        });

        // Sự kiện cho nút "Cập nhật"
        capNhatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    table.setValueAt(maBanField.getText(), selectedRow, 0);
                    table.setValueAt(soChoNgoiComboBox.getSelectedItem(), selectedRow, 1);
                    table.setValueAt(khuVucComboBox.getSelectedItem(), selectedRow, 2);
                    table.setValueAt(tinhTrangComboBox.getSelectedItem(), selectedRow, 3);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật.");
                }
            }
        });

        // Sự kiện cho nút "Thiết lập lại"
        thietLapLaiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maBanField.setText("");
                soChoNgoiComboBox.setSelectedIndex(0);
                khuVucComboBox.setSelectedIndex(0);
                tinhTrangComboBox.setSelectedIndex(0);
            }
        });

        // Sự kiện cho nút "Tìm"
        timButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().toLowerCase();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Xóa các hàng hiện tại
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
                model.setRowCount(0); // Xóa các hàng hiện tại
                for (Object[] row : data) {
                    model.addRow(row); // Tải lại dữ liệu gốc
                }
                searchField.setText("Nhập từ khóa tìm kiếm...");
                searchField.setForeground(Color.GRAY);
            }
        });
    }
}
