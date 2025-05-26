package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import dao.QuanLyBan_DAO;
import entities.Ban;
import entities.KhuVuc;

public class QuanLyBan_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField maBanField;
    private JComboBox<String> soBanComboBox;
    private JComboBox<String> soChoNgoiComboBox;
    private JComboBox<String> khuVucComboBox;
    private JComboBox<String> viTriComboBox;
    private JComboBox<String> tinhTrangComboBox;
    private JTable table;
    private JComboBox<String> khuVucFilterComboBox;
    private JComboBox<String> viTriFilterComboBox;
    private JComboBox<String> tinhTrangFilterComboBox;
    private JComboBox<String> soChoNgoiFilterComboBox;
    private JTextField maBanFilterField;
    private QuanLyBan_DAO quanLyBanDAO;
    private DefaultTableModel model;
    private ArrayList<KhuVuc> khuVucList;
    private boolean isUpdating = false;
	private JPanel mainPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QuanLyBan_GUI frame = new QuanLyBan_GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public QuanLyBan_GUI() {
        quanLyBanDAO = new QuanLyBan_DAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setSize(1537, 864);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Thanh trên cùng (header)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        headerPanel.setBounds(0, 0, 1556, 136);
        contentPane.add(headerPanel);
        headerPanel.setLayout(null);

        JLabel logo = new JLabel("New label");
        logo.setIcon(new ImageIcon(getClass().getResource("/images/App/logo.png")));
        logo.setBounds(66, 22, 247, 89);
        headerPanel.add(logo);

        JLabel avt = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/App/avt.png"));
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        avt.setIcon(scaledIcon);
        avt.setBounds(1365, 22, 100, 104);
        headerPanel.add(avt);

        JLabel name = new JLabel("Lê Vinh A");
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setFont(new Font("Arial", Font.BOLD, 22));
        name.setForeground(Color.WHITE);
        name.setBounds(1230, 43, 125, 41);
        headerPanel.add(name);

        JLabel lblNewLabel = new JLabel("Nhân viên quèn");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(1220, 82, 135, 13);
        headerPanel.add(lblNewLabel);

        JLabel lb_ngay = new JLabel("");
        lb_ngay.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lb_ngay.setForeground(Color.WHITE);
        lb_ngay.setBounds(475, 57, 263, 54);
        headerPanel.add(lb_ngay);

        JLabel lb_thoiGian = new JLabel("");
        lb_thoiGian.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lb_thoiGian.setForeground(Color.WHITE);
        lb_thoiGian.setBounds(767, 57, 269, 54);
        headerPanel.add(lb_thoiGian);

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
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(255, 153, 0));
        sidebarPanel.setBounds(-27, 112, 311, 748);
        contentPane.add(sidebarPanel);
        sidebarPanel.setLayout(null);

        JMenuItem mi_TrangChu = new JMenuItem("              TRANG CHỦ");
        mi_TrangChu.setBackground(new Color(255, 153, 0));
        mi_TrangChu.setSelected(true);
        mi_TrangChu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_TrangChu.setHorizontalAlignment(SwingConstants.LEFT);
        mi_TrangChu.setForeground(Color.WHITE);
        mi_TrangChu.setBounds(20, 48, 291, 61);
        sidebarPanel.add(mi_TrangChu);

        JButton btnDangXuat = new JButton("ĐĂNG XUẤT");
        btnDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnDangXuat.setForeground(Color.WHITE);
        btnDangXuat.setBackground(Color.BLACK);
        btnDangXuat.setBounds(89, 641, 164, 42);
        sidebarPanel.add(btnDangXuat);

        JMenuItem mi_DatBan = new JMenuItem("              ĐẶT BÀN");
        mi_DatBan.setSelected(true);
        mi_DatBan.setHorizontalAlignment(SwingConstants.LEFT);
        mi_DatBan.setForeground(Color.WHITE);
        mi_DatBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_DatBan.setBackground(new Color(255, 153, 0));
        mi_DatBan.setBounds(20, 109, 291, 61);
        sidebarPanel.add(mi_DatBan);

        JMenuItem mi_NhanBan = new JMenuItem("              NHẬN BÀN");
        mi_NhanBan.setSelected(true);
        mi_NhanBan.setHorizontalAlignment(SwingConstants.LEFT);
        mi_NhanBan.setForeground(Color.WHITE);
        mi_NhanBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_NhanBan.setBackground(new Color(255, 153, 0));
        mi_NhanBan.setBounds(20, 171, 291, 61);
        sidebarPanel.add(mi_NhanBan);

        JMenuItem mi_GoiMon = new JMenuItem("              GỌI MÓN");
        mi_GoiMon.setSelected(true);
        mi_GoiMon.setHorizontalAlignment(SwingConstants.LEFT);
        mi_GoiMon.setForeground(Color.WHITE);
        mi_GoiMon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_GoiMon.setBackground(new Color(255, 153, 0));
        mi_GoiMon.setBounds(20, 233, 291, 61);
        sidebarPanel.add(mi_GoiMon);

        JMenuItem mi_ThanhToan = new JMenuItem("              THANH TOÁN");
        mi_ThanhToan.setSelected(true);
        mi_ThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThanhToan.setForeground(Color.WHITE);
        mi_ThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThanhToan.setBackground(new Color(255, 153, 0));
        mi_ThanhToan.setBounds(20, 293, 291, 61);
        sidebarPanel.add(mi_ThanhToan);

        JMenuItem mi_LichSu = new JMenuItem("              LỊCH SỬ");
        mi_LichSu.setSelected(true);
        mi_LichSu.setHorizontalAlignment(SwingConstants.LEFT);
        mi_LichSu.setForeground(Color.WHITE);
        mi_LichSu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_LichSu.setBackground(new Color(255, 153, 0));
        mi_LichSu.setBounds(20, 353, 291, 61);
        sidebarPanel.add(mi_LichSu);

        JMenuItem mi_QuanLy = new JMenuItem("              QUẢN LÝ");
        mi_QuanLy.setSelected(true);
        mi_QuanLy.setHorizontalAlignment(SwingConstants.LEFT);
        mi_QuanLy.setForeground(Color.WHITE);
        mi_QuanLy.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_QuanLy.setBackground(new Color(255, 153, 0));
        mi_QuanLy.setBounds(20, 417, 291, 61);
        sidebarPanel.add(mi_QuanLy);

        JMenuItem mi_ThongKe = new JMenuItem("              THỐNG KÊ");
        mi_ThongKe.setSelected(true);
        mi_ThongKe.setHorizontalAlignment(SwingConstants.LEFT);
        mi_ThongKe.setForeground(Color.WHITE);
        mi_ThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThongKe.setBackground(new Color(255, 153, 0));
        mi_ThongKe.setBounds(20, 478, 291, 61);
        sidebarPanel.add(mi_ThongKe);

        JMenuItem mi_TroGiup = new JMenuItem("              TRỢ GIÚP");
        mi_TroGiup.setSelected(true);
        mi_TroGiup.setHorizontalAlignment(SwingConstants.LEFT);
        mi_TroGiup.setForeground(Color.WHITE);
        mi_TroGiup.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_TroGiup.setBackground(new Color(255, 153, 0));
        mi_TroGiup.setBounds(20, 541, 291, 61);
        sidebarPanel.add(mi_TroGiup);

        // Panel chính chứa nội dung quản lý bàn
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(285, 133, 1254, 704);
        contentPane.add(mainPanel);
        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("QUẢN LÝ BÀN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBounds(0, 10, 1201, 40);
        mainPanel.add(titleLabel);

        // Panel chứa các ô nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 60, 1214, 124);
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setLayout(null);
        mainPanel.add(inputPanel);

        // Số bàn
        JLabel soBanLabel = new JLabel("Số bàn");
        soBanLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        soBanLabel.setBounds(636, 15, 51, 30);
        inputPanel.add(soBanLabel);

        soBanComboBox = new JComboBox<>();
        soBanComboBox.setBounds(697, 17, 80, 30);
        inputPanel.add(soBanComboBox);

        // Mã bàn
        JLabel maBanLabel = new JLabel("Mã bàn");
        maBanLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        maBanLabel.setBounds(34, 17, 80, 30);
        inputPanel.add(maBanLabel);

        maBanField = new JTextField();
        maBanField.setBounds(110, 20, 120, 30);
        maBanField.setEditable(false);
        inputPanel.add(maBanField);

        // Khu vực
        JLabel khuVucLabel = new JLabel("Khu vực");
        khuVucLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        khuVucLabel.setBounds(254, 17, 80, 30);
        inputPanel.add(khuVucLabel);

        khuVucComboBox = new JComboBox<>();
        khuVucList = quanLyBanDAO.getAllKhuVuc();
        for (KhuVuc kv : khuVucList) {
            khuVucComboBox.addItem(kv.getTenKV());
        }
        khuVucComboBox.setBounds(323, 20, 120, 30);
        inputPanel.add(khuVucComboBox);

        // Vị trí
        JLabel viTriLabel = new JLabel("Vị trí");
        viTriLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        viTriLabel.setBounds(465, 17, 51, 30);
        inputPanel.add(viTriLabel);

        viTriComboBox = new JComboBox<>();
        ArrayList<Integer> viTriList = quanLyBanDAO.getDistinctViTri();
        for (Integer viTri : viTriList) {
            if (viTri >= 1 && viTri <= 5) {
                viTriComboBox.addItem("Tầng " + viTri);
            }
        }
        viTriComboBox.setBounds(526, 17, 100, 30);
        inputPanel.add(viTriComboBox);

        // Số chỗ ngồi
        JLabel soChoNgoiLabel = new JLabel("Số chỗ ngồi");
        soChoNgoiLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        soChoNgoiLabel.setBounds(800, 17, 100, 30);
        inputPanel.add(soChoNgoiLabel);

        soChoNgoiComboBox = new JComboBox<>();
        String[] defaultSoChoNgoiOptions = {"2", "4", "8"};
        for (String option : defaultSoChoNgoiOptions) {
            soChoNgoiComboBox.addItem(option);
        }
        soChoNgoiComboBox.setBounds(900, 19, 60, 30);
        inputPanel.add(soChoNgoiComboBox);

        // Cập nhật số chỗ ngồi khi chọn khu vực
        khuVucComboBox.addActionListener(e -> {
            String tenKhuVuc = (String) khuVucComboBox.getSelectedItem();
            soChoNgoiComboBox.removeAllItems();
            if (tenKhuVuc != null && tenKhuVuc.equals("Phòng riêng")) {
                String[] phongRiengOptions = {"2", "4", "8", "16"};
                for (String option : phongRiengOptions) {
                    soChoNgoiComboBox.addItem(option);
                }
            } else {
                for (String option : defaultSoChoNgoiOptions) {
                    soChoNgoiComboBox.addItem(option);
                }
            }
        });

        // Tình trạng
        JLabel tinhTrangLabel = new JLabel("Tình trạng");
        tinhTrangLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tinhTrangLabel.setBounds(970, 17, 80, 30);
        inputPanel.add(tinhTrangLabel);

        String[] tinhTrangOptions = {"Đang hoạt động", "Ngừng hoạt động"};
        tinhTrangComboBox = new JComboBox<>(tinhTrangOptions);
        tinhTrangComboBox.setBounds(1060, 19, 120, 30);
        inputPanel.add(tinhTrangComboBox);

        // Cập nhật danh sách số bàn khả dụng và mã bàn
        ActionListener updateSoBanListener = e -> {
            if (isUpdating) return;

            String tenKhuVuc = (String) khuVucComboBox.getSelectedItem();
            String viTriStr = (String) viTriComboBox.getSelectedItem();
            if (tenKhuVuc != null && viTriStr != null) {
                String maKV = "";
                for (KhuVuc kv : khuVucList) {
                    if (kv.getTenKV().equals(tenKhuVuc)) {
                        maKV = kv.getMaKV();
                        break;
                    }
                }
                int viTri = Integer.parseInt(viTriStr.replace("Tầng ", ""));

                ArrayList<String> availableSoBan = quanLyBanDAO.getAvailableSoBan(maKV, viTri);
                soBanComboBox.removeAllItems();
                for (String soBan : availableSoBan) {
                    soBanComboBox.addItem(soBan);
                }

                if (soBanComboBox.getItemCount() > 0) {
                    String soBan = (String) soBanComboBox.getSelectedItem();
                    String maBan = String.format("%s%d%s", maKV, viTri, soBan);
                    maBanField.setText(maBan);
                } else {
                    maBanField.setText("");
                    JOptionPane.showMessageDialog(null, "Khu vực và vị trí này đã đầy số bàn! Vui lòng chọn khu vực hoặc vị trí khác.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        };
        khuVucComboBox.addActionListener(updateSoBanListener);
        viTriComboBox.addActionListener(updateSoBanListener);

        // Cập nhật mã bàn khi chọn số bàn
        soBanComboBox.addActionListener(e -> {
            String tenKhuVuc = (String) khuVucComboBox.getSelectedItem();
            String viTriStr = (String) viTriComboBox.getSelectedItem();
            String soBan = (String) soBanComboBox.getSelectedItem();
            if (tenKhuVuc != null && viTriStr != null && soBan != null) {
                String maKV = "";
                for (KhuVuc kv : khuVucList) {
                    if (kv.getTenKV().equals(tenKhuVuc)) {
                        maKV = kv.getMaKV();
                        break;
                    }
                }
                int viTri = Integer.parseInt(viTriStr.replace("Tầng ", ""));
                String maBan = String.format("%s%d%s", maKV, viTri, soBan);
                maBanField.setText(maBan);
            }
        });

        // Nút "Thêm"
        JButton themButton = new JButton("Thêm");
        themButton.setBackground(new Color(34, 139, 34));
        themButton.setForeground(Color.WHITE);
        themButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        themButton.setBounds(380, 68, 120, 35);
        inputPanel.add(themButton);

        // Nút "Cập nhật"
        JButton capNhatButton = new JButton("Cập nhật");
        capNhatButton.setBackground(new Color(255, 165, 0));
        capNhatButton.setForeground(Color.WHITE);
        capNhatButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        capNhatButton.setBounds(530, 68, 120, 35);
        inputPanel.add(capNhatButton);

        // Nút "Thiết lập lại"
        JButton thietLapLaiButton = new JButton("Thiết lập lại");
        thietLapLaiButton.setBackground(new Color(0, 123, 255));
        thietLapLaiButton.setForeground(Color.WHITE);
        thietLapLaiButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        thietLapLaiButton.setBounds(680, 68, 120, 35);
        inputPanel.add(thietLapLaiButton);

        // Bộ lọc
        JLabel maBanFilterLabel = new JLabel("Mã bàn");
        maBanFilterLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        maBanFilterLabel.setBounds(116, 201, 80, 30);
        mainPanel.add(maBanFilterLabel);

        maBanFilterField = new JTextField();
        maBanFilterField.setBounds(189, 204, 120, 30);
        mainPanel.add(maBanFilterField);

        // Thêm placeholder "Nhập mã bàn..."
        maBanFilterField.setText("Nhập mã bàn...");
        maBanFilterField.setForeground(Color.GRAY);
        maBanFilterField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (maBanFilterField.getText().equals("Nhập mã bàn...")) {
                    maBanFilterField.setText("");
                    maBanFilterField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (maBanFilterField.getText().isEmpty()) {
                    maBanFilterField.setText("Nhập mã bàn...");
                    maBanFilterField.setForeground(Color.GRAY);
                }
            }
        });

        JLabel khuVucFilterLabel = new JLabel("Khu vực");
        khuVucFilterLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        khuVucFilterLabel.setBounds(326, 201, 80, 30);
        mainPanel.add(khuVucFilterLabel);

        khuVucFilterComboBox = new JComboBox<>();
        khuVucFilterComboBox.addItem("Tất cả");
        for (KhuVuc kv : khuVucList) {
            khuVucFilterComboBox.addItem(kv.getTenKV());
        }
        khuVucFilterComboBox.setBounds(406, 203, 120, 30);
        mainPanel.add(khuVucFilterComboBox);

        JLabel viTriFilterLabel = new JLabel("Vị trí");
        viTriFilterLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        viTriFilterLabel.setBounds(545, 201, 35, 30);
        mainPanel.add(viTriFilterLabel);

        viTriFilterComboBox = new JComboBox<>();
        viTriFilterComboBox.addItem("Tất cả");
        for (Integer viTri : viTriList) {
            if (viTri >= 1 && viTri <= 5) {
                viTriFilterComboBox.addItem("Tầng " + viTri);
            }
        }
        viTriFilterComboBox.setBounds(593, 203, 100, 30);
        mainPanel.add(viTriFilterComboBox);

        JLabel soChoNgoiFilterLabel = new JLabel("Số chỗ ngồi");
        soChoNgoiFilterLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        soChoNgoiFilterLabel.setBounds(713, 201, 100, 30);
        mainPanel.add(soChoNgoiFilterLabel);

        soChoNgoiFilterComboBox = new JComboBox<>();
        soChoNgoiFilterComboBox.addItem("Tất cả");
        for (String option : defaultSoChoNgoiOptions) {
            soChoNgoiFilterComboBox.addItem(option);
        }
        soChoNgoiFilterComboBox.setBounds(807, 203, 60, 30);
        mainPanel.add(soChoNgoiFilterComboBox);

        // Cập nhật số chỗ ngồi lọc khi chọn khu vực
        khuVucFilterComboBox.addActionListener(e -> {
            String tenKhuVuc = (String) khuVucFilterComboBox.getSelectedItem();
            soChoNgoiFilterComboBox.removeAllItems();
            soChoNgoiFilterComboBox.addItem("Tất cả");
            if (tenKhuVuc != null && tenKhuVuc.equals("Phòng riêng")) {
                String[] phongRiengOptions = {"2", "4", "8", "16"};
                for (String option : phongRiengOptions) {
                    soChoNgoiFilterComboBox.addItem(option);
                }
            } else {
                for (String option : defaultSoChoNgoiOptions) {
                    soChoNgoiFilterComboBox.addItem(option);
                }
            }
        });

        JLabel tinhTrangFilterLabel = new JLabel("Tình trạng");
        tinhTrangFilterLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tinhTrangFilterLabel.setBounds(886, 201, 80, 30);
        mainPanel.add(tinhTrangFilterLabel);

        tinhTrangFilterComboBox = new JComboBox<>();
        tinhTrangFilterComboBox.addItem("Tất cả");
        tinhTrangFilterComboBox.addItem("Đang hoạt động");
        tinhTrangFilterComboBox.addItem("Ngừng hoạt động");
        tinhTrangFilterComboBox.setBounds(976, 203, 127, 30);
        mainPanel.add(tinhTrangFilterComboBox);

        JButton locButton = new JButton("Áp dụng");
        locButton.setBackground(new Color(255, 165, 0));
        locButton.setForeground(Color.WHITE);
        locButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        locButton.setBounds(474, 244, 106, 30);
        mainPanel.add(locButton);

        JButton taiLaiButton = new JButton("Tải lại");
        taiLaiButton.setBackground(new Color(0, 123, 255));
        taiLaiButton.setForeground(Color.WHITE);
        taiLaiButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        taiLaiButton.setBounds(620, 244, 100, 30);
        mainPanel.add(taiLaiButton);

        // Khởi tạo bảng
        String[] columns = {"Mã bàn", "Vị trí", "Khu vực", "Số chỗ ngồi", "Tình trạng"};
        model = new DefaultTableModel(new Object[][]{}, columns);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 286, 1214, 398);
        mainPanel.add(scrollPane);

        // Tải dữ liệu với bộ lọc mặc định ngay từ đầu
        loadFilteredData();

        // Xử lý nút "Thêm"
        themButton.addActionListener(e -> {
            String maBan = maBanField.getText();
            String soChoNgoiStr = (String) soChoNgoiComboBox.getSelectedItem();
            String tenKhuVuc = (String) khuVucComboBox.getSelectedItem();
            String viTriStr = (String) viTriComboBox.getSelectedItem();
            String tinhTrangStr = (String) tinhTrangComboBox.getSelectedItem();
            String soBan = (String) soBanComboBox.getSelectedItem();

            if (maBan.isEmpty() || soBan == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn khu vực, vị trí và số bàn hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int loaiBan = Integer.parseInt(soChoNgoiStr);
            int viTri = Integer.parseInt(viTriStr.replace("Tầng ", ""));
            int tinhTrang = tinhTrangStr.equals("Đang hoạt động") ? 1 : 0;
            String maKV = "";
            for (KhuVuc kv : khuVucList) {
                if (kv.getTenKV().equals(tenKhuVuc)) {
                    maKV = kv.getMaKV();
                    break;
                }
            }

            boolean success = quanLyBanDAO.themBan(maBan, loaiBan, viTri, soBan, maKV, tinhTrang);
            if (success) {
                model.addRow(new Object[]{maBan, "Tầng " + viTri, tenKhuVuc, soChoNgoiStr, tinhTrangStr});
                JOptionPane.showMessageDialog(null, "Thêm bàn thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm bàn thất bại! Kiểm tra khu vực, vị trí hoặc số bàn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Xử lý nút "Cập nhật"
        capNhatButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String maBan = table.getValueAt(selectedRow, 0).toString();
                String soChoNgoiStr = (String) soChoNgoiComboBox.getSelectedItem();
                String tinhTrangStr = (String) tinhTrangComboBox.getSelectedItem();

                int loaiBan = Integer.parseInt(soChoNgoiStr);
                int tinhTrang = tinhTrangStr.equals("Đang hoạt động") ? 1 : 0;

                boolean success = quanLyBanDAO.capNhatBan(maBan, loaiBan, tinhTrang);
                if (success) {
                    loadFilteredData();
                    JOptionPane.showMessageDialog(null, "Cập nhật bàn thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật bàn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật.");
            }
        });

        // Xử lý nút "Thiết lập lại"
        thietLapLaiButton.addActionListener(e -> {
            maBanField.setText("");
            soChoNgoiComboBox.setSelectedIndex(0);
            khuVucComboBox.setSelectedIndex(0);
            viTriComboBox.setSelectedIndex(0);
            tinhTrangComboBox.setSelectedIndex(0);
            soBanComboBox.removeAllItems();

            // Bật lại các trường
            khuVucComboBox.setEnabled(true);
            viTriComboBox.setEnabled(true);
            soBanComboBox.setEnabled(true);
        });

        // Xử lý nút "Áp dụng"
        locButton.addActionListener(e -> {
            loadFilteredData();
        });

        // Xử lý nút "Tải lại"
        taiLaiButton.addActionListener(e -> {
            khuVucFilterComboBox.setSelectedIndex(0);
            viTriFilterComboBox.setSelectedIndex(0);
            soChoNgoiFilterComboBox.setSelectedIndex(0);
            tinhTrangFilterComboBox.setSelectedIndex(0);
            maBanFilterField.setText("Nhập mã bàn..."); 
            maBanFilterField.setForeground(Color.GRAY); 
            loadFilteredData();
        });

        // Khi chọn hàng trong bảng
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                isUpdating = true;

                String maBan = table.getValueAt(selectedRow, 0).toString();
                String viTriStr = table.getValueAt(selectedRow, 1).toString();
                String tenKhuVuc = table.getValueAt(selectedRow, 2).toString();
                String soChoNgoi = table.getValueAt(selectedRow, 3).toString();
                String tinhTrang = table.getValueAt(selectedRow, 4).toString();

                maBanField.setText(maBan);
                khuVucComboBox.setSelectedItem(tenKhuVuc);
                viTriComboBox.setSelectedItem(viTriStr);
                soChoNgoiComboBox.setSelectedItem(soChoNgoi);
                tinhTrangComboBox.setSelectedItem(tinhTrang);

                // Vô hiệu hóa các trường không cho cập nhật
                khuVucComboBox.setEnabled(false);
                viTriComboBox.setEnabled(false);
                soBanComboBox.setEnabled(false);

                String soBan = maBan.substring(maBan.length() - 2);
                soBanComboBox.removeAllItems();
                soBanComboBox.addItem(soBan);
                soBanComboBox.setSelectedItem(soBan);

                isUpdating = false;
            }
        });

        // Xử lý nút "Đăng xuất"
        btnDangXuat.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void loadFilteredData() {
        String maBanFilter = maBanFilterField.getText().trim();
        if (maBanFilter.equals("Nhập mã bàn...")) {
            maBanFilter = "";
        }
        String tenKhuVuc = (String) khuVucFilterComboBox.getSelectedItem();
        String viTriStr = (String) viTriFilterComboBox.getSelectedItem();
        String soChoNgoiStr = (String) soChoNgoiFilterComboBox.getSelectedItem();
        String tinhTrangStr = (String) tinhTrangFilterComboBox.getSelectedItem();

        String tenKhuVucFilter = tenKhuVuc.equals("Tất cả") ? null : tenKhuVuc;
        Integer viTriFilter = viTriStr.equals("Tất cả") ? null : Integer.parseInt(viTriStr.replace("Tầng ", ""));
        Integer soChoNgoiFilter = soChoNgoiStr.equals("Tất cả") ? null : Integer.parseInt(soChoNgoiStr);
        Integer tinhTrangFilter = tinhTrangStr.equals("Tất cả") ? null : tinhTrangStr.equals("Đang hoạt động") ? 1 : 0;

        model.setRowCount(0);
        ArrayList<Ban> banList = quanLyBanDAO.locBan(tenKhuVucFilter, viTriFilter, tinhTrangFilter);
        for (Ban ban : banList) {
            if ((maBanFilter.isEmpty() || ban.getMaBan().contains(maBanFilter)) &&
                (soChoNgoiFilter == null || ban.getLoaiBan() == soChoNgoiFilter)) {
                int soChoNgoi = ban.getLoaiBan();
                String tinhTrang = ban.getTinhTrang() == 1 ? "Đang hoạt động" : "Ngừng hoạt động";
                model.addRow(new Object[]{
                    ban.getMaBan(),
                    "Tầng " + ban.getViTri(),
                    ban.getTenKV(),
                    soChoNgoi,
                    tinhTrang
                });
            }
        }
    }
    public JPanel getPanel() {
        return mainPanel;
    }
}