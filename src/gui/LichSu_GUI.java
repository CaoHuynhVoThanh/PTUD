package gui;

import dao.LichSu_DAO;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class LichSu_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton detailButtonHoaDon;
    private JTable tableHoaDon;
    private LichSu_DAO lichSuDAO = new LichSu_DAO();
	private JPanel panel_trangchu;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                connectDB.ConnectDB.getInstance().connect();
                LichSu_GUI frame = new LichSu_GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LichSu_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setSize(1537, 864);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Header panel
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(UIManager.getColor("Button.foreground"));
        panel_1.setBounds(0, 0, 1556, 136);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel logo = new JLabel("");
        logo.setIcon(new ImageIcon("src\\images\\icon.png"));
        logo.setBounds(66, 22, 247, 89);
        panel_1.add(logo);

        JLabel avt = new JLabel("");
        ImageIcon originalIcon = new ImageIcon("src\\images\\avt.png");
        Image scaledImg = originalIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        avt.setIcon(new ImageIcon(scaledImg));
        avt.setBounds(1365, 22, 100, 104);
        panel_1.add(avt);

        JLabel name = new JLabel("Lê Vinh A");
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setFont(new Font("Arial", Font.BOLD, 22));
        name.setForeground(Color.WHITE);
        name.setBounds(1230, 43, 125, 41);
        panel_1.add(name);

        JLabel lblNewLabel = new JLabel("Nhân viên quèn");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(1220, 82, 135, 13);
        panel_1.add(lblNewLabel);

        JLabel lb_ngay_local = new JLabel("");
        lb_ngay_local.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lb_ngay_local.setForeground(Color.WHITE);
        lb_ngay_local.setBounds(475, 57, 263, 54);
        panel_1.add(lb_ngay_local);

        JLabel lb_thoiGian_local = new JLabel("");
        lb_thoiGian_local.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lb_thoiGian_local.setForeground(Color.WHITE);
        lb_thoiGian_local.setBounds(767, 57, 269, 54);
        panel_1.add(lb_thoiGian_local);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        java.util.Timer timer = new java.util.Timer(true);
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();
                SwingUtilities.invokeLater(() -> {
                    lb_ngay_local.setText("Ngày: " + currentDate.format(dateFormatter));
                    lb_thoiGian_local.setText("Thời gian: " + currentTime.format(timeFormatter));
                });
            }
        }, 0, 1000);

        // Sidebar panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 153, 0));
        panel.setBounds(-27, 112, 311, 748);
        contentPane.add(panel);
        panel.setLayout(null);

        JMenuItem mi_TrangChu = new JMenuItem("              TRANG CHỦ");
        mi_TrangChu.setBackground(new Color(255, 153, 0));
        mi_TrangChu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_TrangChu.setForeground(Color.WHITE);
        mi_TrangChu.setBounds(20, 48, 291, 61);
        panel.add(mi_TrangChu);

        JButton btnNewButton = new JButton("ĐĂNG XUẤT");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setBounds(89, 641, 164, 42);
        btnNewButton.addActionListener(e -> System.exit(0));
        panel.add(btnNewButton);

        JMenuItem mi_DatBan = new JMenuItem("              ĐẶT BÀN");
        mi_DatBan.setForeground(Color.WHITE);
        mi_DatBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_DatBan.setBackground(new Color(255, 153, 0));
        mi_DatBan.setBounds(20, 109, 291, 61);
        panel.add(mi_DatBan);

        JMenuItem mi_NhanBan = new JMenuItem("              NHẬN BÀN");
        mi_NhanBan.setForeground(Color.WHITE);
        mi_NhanBan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_NhanBan.setBackground(new Color(255, 153, 0));
        mi_NhanBan.setBounds(20, 171, 291, 61);
        panel.add(mi_NhanBan);

        JMenuItem mi_GoiMon = new JMenuItem("              GỌI MÓN");
        mi_GoiMon.setForeground(Color.WHITE);
        mi_GoiMon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_GoiMon.setBackground(new Color(255, 153, 0));
        mi_GoiMon.setBounds(20, 233, 291, 61);
        panel.add(mi_GoiMon);

        JMenuItem mi_ThanhToan = new JMenuItem("              THANH TOÁN");
        mi_ThanhToan.setForeground(Color.WHITE);
        mi_ThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThanhToan.setBackground(new Color(255, 153, 0));
        mi_ThanhToan.setBounds(20, 293, 291, 61);
        panel.add(mi_ThanhToan);

        JMenuItem mi_LichSu = new JMenuItem("              LỊCH SỬ");
        mi_LichSu.setForeground(Color.WHITE);
        mi_LichSu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_LichSu.setBackground(new Color(255, 153, 0));
        mi_LichSu.setBounds(20, 353, 291, 61);
        panel.add(mi_LichSu);

        JMenuItem mi_QuanLy = new JMenuItem("              QUẢN LÝ");
        mi_QuanLy.setForeground(Color.WHITE);
        mi_QuanLy.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_QuanLy.setBackground(new Color(255, 153, 0));
        mi_QuanLy.setBounds(20, 417, 291, 61);
        panel.add(mi_QuanLy);

        JMenuItem mi_ThongKe = new JMenuItem("              THỐNG KÊ");
        mi_ThongKe.setForeground(Color.WHITE);
        mi_ThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_ThongKe.setBackground(new Color(255, 153, 0));
        mi_ThongKe.setBounds(20, 478, 291, 61);
        panel.add(mi_ThongKe);

        JMenuItem mi_TroGiup = new JMenuItem("              TRỢ GIÚP");
        mi_TroGiup.setForeground(Color.WHITE);
        mi_TroGiup.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mi_TroGiup.setBackground(new Color(255, 153, 0));
        mi_TroGiup.setBounds(20, 541, 291, 61);
        panel.add(mi_TroGiup);

        // Main content panel
        panel_trangchu = new JPanel();
        panel_trangchu.setBackground(Color.WHITE);
        panel_trangchu.setBounds(285, 133, 1254, 704);
        contentPane.add(panel_trangchu);
        panel_trangchu.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(31, 32, 1199, 650);
        panel_trangchu.add(tabbedPane);

        // *** Tab "Lịch sử hóa đơn" ***
        JPanel panel_lichSuHoaDon = new JPanel();
        panel_lichSuHoaDon.setBackground(Color.WHITE);
        panel_lichSuHoaDon.setLayout(null);
        tabbedPane.addTab("Lịch sử hóa đơn", null, panel_lichSuHoaDon, null);

        // Panel chứa bộ lọc
        JPanel filterPanelHoaDon = new JPanel();
        filterPanelHoaDon.setBounds(10, 10, 1174, 91);
        filterPanelHoaDon.setLayout(null);
        panel_lichSuHoaDon.add(filterPanelHoaDon);

        // Hàng 1
        JLabel lblMaHoaDon = new JLabel("Mã hóa đơn:");
        lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaHoaDon.setBounds(24, 10, 80, 30);
        filterPanelHoaDon.add(lblMaHoaDon);

        JTextField maHoaDonField = new JTextField("Nhập mã hóa đơn...");
        maHoaDonField.setBounds(114, 10, 150, 30);
        filterPanelHoaDon.add(maHoaDonField);

        JLabel lblThongTinKH = new JLabel("Thông tin KH:");
        lblThongTinKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblThongTinKH.setBounds(274, 10, 100, 30);
        filterPanelHoaDon.add(lblThongTinKH);

        JTextField thongTinKHField = new JTextField("Nhập thông tin KH...");
        thongTinKHField.setBounds(374, 10, 150, 30);
        filterPanelHoaDon.add(thongTinKHField);

        JLabel lblStartDateHoaDon = new JLabel("Từ ngày:");
        lblStartDateHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStartDateHoaDon.setBounds(544, 10, 80, 30);
        filterPanelHoaDon.add(lblStartDateHoaDon);

        JDateChooser startDateChooserHoaDon = new JDateChooser();
        startDateChooserHoaDon.setDateFormatString("dd/MM/yyyy");
        startDateChooserHoaDon.setBounds(624, 10, 120, 30);
        filterPanelHoaDon.add(startDateChooserHoaDon);

        JLabel lblEndDateHoaDon = new JLabel("Đến ngày:");
        lblEndDateHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEndDateHoaDon.setBounds(764, 10, 80, 30);
        filterPanelHoaDon.add(lblEndDateHoaDon);

        JDateChooser endDateChooserHoaDon = new JDateChooser();
        endDateChooserHoaDon.setDateFormatString("dd/MM/yyyy");
        endDateChooserHoaDon.setBounds(844, 10, 120, 30);
        filterPanelHoaDon.add(endDateChooserHoaDon);

        // Hàng 2
        JLabel lblNguoiTao = new JLabel("Người tạo:");
        lblNguoiTao.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNguoiTao.setBounds(37, 47, 67, 30);
        filterPanelHoaDon.add(lblNguoiTao);

        Vector<String> nguoiTaoList = new Vector<>();
        nguoiTaoList.add("Tất cả");
        try {
            List<String> nguoiTaoFromDB = lichSuDAO.getAllNguoiTao();
            nguoiTaoList.addAll(nguoiTaoFromDB);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        JComboBox<String> nguoiTaoComboBox = new JComboBox<>(nguoiTaoList);
        nguoiTaoComboBox.setBounds(114, 50, 150, 30);
        filterPanelHoaDon.add(nguoiTaoComboBox);

        JLabel lblPaymentMethod = new JLabel("Phương thức:");
        lblPaymentMethod.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPaymentMethod.setBounds(274, 50, 94, 30);
        filterPanelHoaDon.add(lblPaymentMethod);

        String[] paymentMethods = {"Tất cả", "Tiền mặt", "Thẻ tín dụng", "Chuyển khoản"};
        JComboBox<String> paymentMethodComboBox = new JComboBox<>(paymentMethods);
        paymentMethodComboBox.setBounds(374, 50, 120, 30);
        filterPanelHoaDon.add(paymentMethodComboBox);

        JLabel lblMinTotal = new JLabel("Tổng tiền từ:");
        lblMinTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMinTotal.setBounds(522, 50, 100, 30);
        filterPanelHoaDon.add(lblMinTotal);

        JTextField minTotalField = new JTextField();
        minTotalField.setBounds(624, 50, 100, 30);
        filterPanelHoaDon.add(minTotalField);

        JLabel lblMaxTotal = new JLabel("đến:");
        lblMaxTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaxTotal.setBounds(740, 50, 40, 30);
        filterPanelHoaDon.add(lblMaxTotal);

        JTextField maxTotalField = new JTextField();
        maxTotalField.setBounds(780, 50, 100, 30);
        filterPanelHoaDon.add(maxTotalField);

        JButton applyButtonHoaDon = new JButton("Áp dụng");
        applyButtonHoaDon.setForeground(new Color(255, 255, 255));
        applyButtonHoaDon.setBackground(new Color(255, 153, 0));
        applyButtonHoaDon.setBounds(909, 49, 100, 30);
        filterPanelHoaDon.add(applyButtonHoaDon);

        JButton resetButtonHoaDon = new JButton("Khôi phục");
        resetButtonHoaDon.setForeground(Color.WHITE);
        resetButtonHoaDon.setBackground(Color.BLACK);
        resetButtonHoaDon.setBounds(1035, 49, 100, 30);
        filterPanelHoaDon.add(resetButtonHoaDon);

        // Thêm nút "Xem chi tiết" vào filterPanelHoaDon
        detailButtonHoaDon = new JButton("Xem chi tiết");
        detailButtonHoaDon.setBounds(1035, 12, 100, 30);
        detailButtonHoaDon.setForeground(Color.WHITE);
        detailButtonHoaDon.setBackground(new Color(0, 128, 0));
        detailButtonHoaDon.setEnabled(false);
        filterPanelHoaDon.add(detailButtonHoaDon);

        // Thêm FocusListener cho các trường nhập liệu
        maHoaDonField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (maHoaDonField.getText().equals("Nhập mã hóa đơn...")) {
                    maHoaDonField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (maHoaDonField.getText().isEmpty()) {
                    maHoaDonField.setText("Nhập mã hóa đơn...");
                }
            }
        });

        thongTinKHField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (thongTinKHField.getText().equals("Nhập thông tin KH...")) {
                    thongTinKHField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (thongTinKHField.getText().isEmpty()) {
                    thongTinKHField.setText("Nhập thông tin KH...");
                }
            }
        });

        // Bảng dữ liệu
        String[] columnsHoaDon = {"Mã HD", "Thời gian", "Người tạo", "Thông tin KH", "Phương thức", "Tổng tiền"};
        DefaultTableModel modelHoaDon = new DefaultTableModel(fetchHoaDonData(), columnsHoaDon);
        tableHoaDon = new JTable(modelHoaDon);
        tableHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tableHoaDon.setRowHeight(30);
        TableRowSorter<DefaultTableModel> sorterHoaDon = new TableRowSorter<>(modelHoaDon);
        tableHoaDon.setRowSorter(sorterHoaDon);
        JScrollPane scrollPaneHoaDon = new JScrollPane(tableHoaDon);
        scrollPaneHoaDon.setBounds(0, 111, 1194, 528);
        panel_lichSuHoaDon.add(scrollPaneHoaDon);

        // Kích hoạt nút "Xem chi tiết" khi chọn hàng trong bảng
        tableHoaDon.getSelectionModel().addListSelectionListener(e -> {
            detailButtonHoaDon.setEnabled(!e.getValueIsAdjusting() && tableHoaDon.getSelectedRow() != -1);
        });

        // Xử lý sự kiện nút "Xem chi tiết"
        detailButtonHoaDon.addActionListener(e -> {
            int selectedRow = tableHoaDon.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = tableHoaDon.convertRowIndexToModel(selectedRow);
                String maHD = (String) modelHoaDon.getValueAt(modelRow, 0);
                Timestamp thoiGian = (Timestamp) modelHoaDon.getValueAt(modelRow, 1);
                String nguoiTao = (String) modelHoaDon.getValueAt(modelRow, 2);
                String thongTinKH = (String) modelHoaDon.getValueAt(modelRow, 3);
                String phuongThuc = (String) modelHoaDon.getValueAt(modelRow, 4);
                double tongTien = (double) modelHoaDon.getValueAt(modelRow, 5);
                showHoaDonDetailDialog(maHD, thoiGian, nguoiTao, thongTinKH, phuongThuc, tongTien);
            }
        });

        // Sự kiện nút "Áp dụng"
        applyButtonHoaDon.addActionListener(e -> {
            String maHoaDonText = maHoaDonField.getText().trim();
            String thongTinKHText = thongTinKHField.getText().trim();
            String selectedNguoiTao = (String) nguoiTaoComboBox.getSelectedItem();
            java.util.Date startDate = startDateChooserHoaDon.getDate();
            java.util.Date endDate = endDateChooserHoaDon.getDate();
            String selectedPaymentMethod = (String) paymentMethodComboBox.getSelectedItem();
            String minTotalStr = minTotalField.getText().trim();
            String maxTotalStr = maxTotalField.getText().trim();

            List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();

            if (!maHoaDonText.isEmpty() && !maHoaDonText.equals("Nhập mã hóa đơn...")) {
                int columnIndex = modelHoaDon.findColumn("Mã HD");
                if (columnIndex != -1) {
                    filters.add(RowFilter.regexFilter("(?i)" + maHoaDonText, columnIndex));
                }
            }

            if (!thongTinKHText.isEmpty() && !thongTinKHText.equals("Nhập thông tin KH...")) {
                int columnIndex = modelHoaDon.findColumn("Thông tin KH");
                if (columnIndex != -1) {
                    filters.add(RowFilter.regexFilter("(?i)" + thongTinKHText, columnIndex));
                }
            }

            if (!selectedNguoiTao.equals("Tất cả")) {
                int columnIndex = modelHoaDon.findColumn("Người tạo");
                if (columnIndex != -1) {
                    filters.add(RowFilter.regexFilter("(?i)" + selectedNguoiTao, columnIndex));
                }
            }

            if (startDate != null && endDate != null) {
                try {
                    LocalDate start = startDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                    LocalDate end = endDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                    if (start.isAfter(end)) {
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể sau ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    filters.add(new RowFilter<DefaultTableModel, Object>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                            Object value = entry.getValue(1);
                            if (value instanceof Timestamp) {
                                Timestamp timestamp = (Timestamp) value;
                                LocalDate date = timestamp.toLocalDateTime().toLocalDate();
                                return (date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end));
                            }
                            return false;
                        }
                    });
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi lọc theo ngày: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (!selectedPaymentMethod.equals("Tất cả")) {
                int paymentMethodIndex = modelHoaDon.findColumn("Phương thức");
                filters.add(RowFilter.regexFilter("(?i)" + selectedPaymentMethod, paymentMethodIndex));
            }

            try {
                if (!minTotalStr.isEmpty()) {
                    double minTotal = Double.parseDouble(minTotalStr);
                    filters.add(new RowFilter<DefaultTableModel, Object>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                            Object value = entry.getValue(5);
                            if (value instanceof Double) {
                                return (Double) value >= minTotal;
                            }
                            return false;
                        }
                    });
                }
                if (!maxTotalStr.isEmpty()) {
                    double maxTotal = Double.parseDouble(maxTotalStr);
                    filters.add(new RowFilter<DefaultTableModel, Object>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                            Object value = entry.getValue(5);
                            if (value instanceof Double) {
                                return (Double) value <= maxTotal;
                            }
                            return false;
                        }
                    });
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Giá trị tổng tiền không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            sorterHoaDon.setRowFilter(filters.isEmpty() ? null : RowFilter.andFilter(filters));
        });

        // Sự kiện nút "Khôi phục"
        resetButtonHoaDon.addActionListener(e -> {
            maHoaDonField.setText("Nhập mã hóa đơn...");
            thongTinKHField.setText("Nhập thông tin KH...");
            nguoiTaoComboBox.setSelectedIndex(0);
            startDateChooserHoaDon.setDate(null);
            endDateChooserHoaDon.setDate(null);
            paymentMethodComboBox.setSelectedIndex(0);
            minTotalField.setText("");
            maxTotalField.setText("");
            sorterHoaDon.setRowFilter(null);
            tableHoaDon.clearSelection();
            detailButtonHoaDon.setEnabled(false);
        });

        // *** Tab "Lịch sử đặt bàn" ***
        JPanel panel_lichSuDatBan = new JPanel();
        panel_lichSuDatBan.setBackground(Color.WHITE);
        panel_lichSuDatBan.setLayout(null);
        tabbedPane.addTab("Lịch sử đặt bàn", null, panel_lichSuDatBan, null);

        // Panel chứa bộ lọc
        JPanel filterPanelDatBan = new JPanel();
        filterPanelDatBan.setBounds(10, 10, 1174, 93);
        filterPanelDatBan.setLayout(null);
        panel_lichSuDatBan.add(filterPanelDatBan);

        // Hàng 1
        JLabel lblThongTinKH2 = new JLabel("Thông tin KH:");
        lblThongTinKH2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblThongTinKH2.setBounds(23, 11, 100, 30);
        filterPanelDatBan.add(lblThongTinKH2);

        JTextField thongTinKHField2 = new JTextField("Nhập thông tin KH...");
        thongTinKHField2.setBounds(133, 11, 150, 30);
        filterPanelDatBan.add(thongTinKHField2);

        JLabel lblStartDateDatBan = new JLabel("Từ ngày:");
        lblStartDateDatBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStartDateDatBan.setBounds(314, 11, 80, 30);
        filterPanelDatBan.add(lblStartDateDatBan);

        JDateChooser startDateChooserDatBan = new JDateChooser();
        startDateChooserDatBan.setDateFormatString("dd/MM/yyyy");
        startDateChooserDatBan.setBounds(394, 11, 120, 30);
        filterPanelDatBan.add(startDateChooserDatBan);

        JLabel lblEndDateDatBan = new JLabel("Đến ngày:");
        lblEndDateDatBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEndDateDatBan.setBounds(534, 11, 80, 30);
        filterPanelDatBan.add(lblEndDateDatBan);

        JDateChooser endDateChooserDatBan = new JDateChooser();
        endDateChooserDatBan.setDateFormatString("dd/MM/yyyy");
        endDateChooserDatBan.setBounds(614, 11, 120, 30);
        filterPanelDatBan.add(endDateChooserDatBan);

        // Thêm nút "Xem chi tiết" vào filterPanelDatBan
        JButton detailButtonDatBan = new JButton("Xem chi tiết");
        detailButtonDatBan.setBounds(1008, 11, 100, 30);
        detailButtonDatBan.setForeground(Color.WHITE);
        detailButtonDatBan.setBackground(new Color(0, 128, 0));
        detailButtonDatBan.setEnabled(false);
        filterPanelDatBan.add(detailButtonDatBan);

        // Hàng 2
        JLabel lblStatus = new JLabel("Trạng thái:");
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStatus.setBounds(767, 11, 80, 30);
        filterPanelDatBan.add(lblStatus);

        String[] statuses = {"Tất cả trạng thái", "Đã hủy", "Chờ nhận bàn", "Đã nhận bàn", "Đã thanh toán"};
        JComboBox<String> statusComboBox = new JComboBox<>(statuses);
        statusComboBox.setBounds(857, 10, 120, 30);
        filterPanelDatBan.add(statusComboBox);

        JLabel lblMinDeposit = new JLabel("Đặt cọc từ:");
        lblMinDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMinDeposit.setBounds(38, 51, 85, 30);
        filterPanelDatBan.add(lblMinDeposit);

        JTextField minDepositField = new JTextField();
        minDepositField.setBounds(133, 51, 100, 30);
        filterPanelDatBan.add(minDepositField);

        JLabel lblMaxDeposit = new JLabel("đến:");
        lblMaxDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaxDeposit.setBounds(243, 51, 40, 30);
        filterPanelDatBan.add(lblMaxDeposit);

        JTextField maxDepositField = new JTextField();
        maxDepositField.setBounds(283, 51, 100, 30);
        filterPanelDatBan.add(maxDepositField);

        JLabel lblSoKhach = new JLabel("Số khách:");
        lblSoKhach.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSoKhach.setBounds(394, 51, 80, 30);
        filterPanelDatBan.add(lblSoKhach);

        JTextField soKhachField = new JTextField();
        soKhachField.setBounds(474, 51, 60, 30);
        filterPanelDatBan.add(soKhachField);

        // Thêm JLabel và JComboBox cho vị trí (tầng)
        JLabel lblViTri = new JLabel("Vị trí:");
        lblViTri.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblViTri.setBounds(544, 51, 50, 30);
        filterPanelDatBan.add(lblViTri);

        // Tạo JComboBox cho vị trí (tầng)
        Vector<String> tangListDisplay = new Vector<>();
        Vector<String> tangListValue = new Vector<>();
        tangListDisplay.add("Tất cả");
        tangListValue.add("Tất cả");
        try {
            List<Integer> viTriList = lichSuDAO.getDistinctViTri();
            for (Integer viTri : viTriList) {
                tangListValue.add(String.valueOf(viTri));
                tangListDisplay.add("Tầng " + viTri);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách vị trí: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        JComboBox<String> tangComboBox = new JComboBox<>(tangListDisplay);
        tangComboBox.setBounds(584, 51, 80, 30);
        filterPanelDatBan.add(tangComboBox);

        JLabel lblKhuVuc = new JLabel("Khu vực:");
        lblKhuVuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblKhuVuc.setBounds(674, 51, 60, 30);
        filterPanelDatBan.add(lblKhuVuc);

        // Tạo JComboBox cho Khu vực và tải tất cả khu vực ngay từ đầu
        Vector<String> khuVucList = new Vector<>();
        khuVucList.add("Tất cả");
        try {
            List<String> khuVucListFromDB = lichSuDAO.getAllKhuVuc();
            khuVucList.addAll(khuVucListFromDB);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách khu vực: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        JComboBox<String> khuVucComboBox = new JComboBox<>(khuVucList);
        khuVucComboBox.setBounds(740, 51, 80, 30);
        filterPanelDatBan.add(khuVucComboBox);

        JLabel lblBan = new JLabel("Bàn:");
        lblBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblBan.setBounds(840, 51, 40, 30);
        filterPanelDatBan.add(lblBan);

        // Thay JComboBox bằng JTextField cho Bàn
        JTextField banField = new JTextField();
        banField.setBounds(880, 51, 60, 30);
        filterPanelDatBan.add(banField);

        // Cập nhật danh sách khu vực khi chọn vị trí (tầng)
        tangComboBox.addActionListener(e -> {
            int selectedIndex = tangComboBox.getSelectedIndex();
            String selectedTang = tangListValue.get(selectedIndex);
            khuVucList.clear();
            khuVucList.add("Tất cả");

            if (!selectedTang.equals("Tất cả")) {
                try {
                    List<String> khuVucByViTri = lichSuDAO.getKhuVucByViTri(Integer.parseInt(selectedTang));
                    if (khuVucByViTri.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có khu vực nào ở " + tangComboBox.getSelectedItem(),
                                                      "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        khuVucList.addAll(khuVucByViTri);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách khu vực: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    List<String> allKhuVuc = lichSuDAO.getAllKhuVuc();
                    khuVucList.addAll(allKhuVuc);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách khu vực: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }

            khuVucComboBox.setModel(new DefaultComboBoxModel<>(khuVucList));
        });

        // Cập nhật danh sách khu vực khi chọn khu vực (không cần cập nhật bàn nữa)
        khuVucComboBox.addActionListener(e -> {
            // Không cần logic cập nhật bàn vì đã chuyển sang JTextField
        });

        JButton applyButtonDatBan = new JButton("Áp dụng");
        applyButtonDatBan.setBounds(950, 51, 100, 30);
        applyButtonDatBan.setForeground(Color.WHITE);
        applyButtonDatBan.setBackground(new Color(255, 153, 0));
        filterPanelDatBan.add(applyButtonDatBan);

        JButton resetButtonDatBan = new JButton("Khôi phục");
        resetButtonDatBan.setBounds(1064, 51, 100, 30);
        resetButtonDatBan.setForeground(Color.WHITE);
        resetButtonDatBan.setBackground(Color.BLACK);
        filterPanelDatBan.add(resetButtonDatBan);

        // Sửa lỗi FocusListener cho ô "Thông tin KH"
        thongTinKHField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (thongTinKHField2.getText().equals("Nhập thông tin KH...")) {
                    thongTinKHField2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (thongTinKHField2.getText().isEmpty()) {
                    thongTinKHField2.setText("Nhập thông tin KH...");
                }
            }
        });

        // Sửa cột của bảng chính: Giữ nguyên các cột
        String[] columnsDatBan = {"Mã DDB", "Thời gian nhận bàn", "Thông tin KH", "Đặt cọc", "Số khách", "Khu vực/Bàn", "Vị trí", "Trạng thái"};
        DefaultTableModel modelDatBan = new DefaultTableModel(fetchDatBanDataWithViTri(), columnsDatBan);
        JTable tableDatBan = new JTable(modelDatBan);
        tableDatBan.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tableDatBan.setRowHeight(30);
        // Ẩn cột "Mã DDB"
        tableDatBan.getColumnModel().getColumn(0).setMinWidth(0);
        tableDatBan.getColumnModel().getColumn(0).setMaxWidth(0);
        tableDatBan.getColumnModel().getColumn(0).setWidth(0);
        TableRowSorter<DefaultTableModel> sorterDatBan = new TableRowSorter<>(modelDatBan);
        tableDatBan.setRowSorter(sorterDatBan);
        JScrollPane scrollPaneDatBan = new JScrollPane(tableDatBan);
        scrollPaneDatBan.setBounds(0, 113, 1194, 510);
        panel_lichSuDatBan.add(scrollPaneDatBan);

        // Kích hoạt nút "Xem chi tiết" khi chọn hàng trong bảng
        tableDatBan.getSelectionModel().addListSelectionListener(e -> {
            detailButtonDatBan.setEnabled(!e.getValueIsAdjusting() && tableDatBan.getSelectedRow() != -1);
            if (thongTinKHField2.getText().equals("Nhập thông tin KH...")) {
                thongTinKHField2.setText("");
            }
        });

        // Xử lý sự kiện nút "Xem chi tiết"
        detailButtonDatBan.addActionListener(e -> {
            int selectedRow = tableDatBan.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = tableDatBan.convertRowIndexToModel(selectedRow);
                String maDDB = (String) modelDatBan.getValueAt(modelRow, 0);
                Timestamp thoiGianNhanBan = (Timestamp) modelDatBan.getValueAt(modelRow, 1);
                String khachHang = (String) modelDatBan.getValueAt(modelRow, 2);
                double datCoc = (double) modelDatBan.getValueAt(modelRow, 3);
                int soKhach = (int) modelDatBan.getValueAt(modelRow, 4);
                String trangThai = (String) modelDatBan.getValueAt(modelRow, 7);
                showDatBanDetailDialog(maDDB, thoiGianNhanBan, khachHang, datCoc, soKhach, trangThai);
            }
        });

        applyButtonDatBan.addActionListener(e -> {
            String thongTinKHText = thongTinKHField2.getText().trim();
            java.util.Date startDate = startDateChooserDatBan.getDate();
            java.util.Date endDate = endDateChooserDatBan.getDate();
            String selectedStatus = (String) statusComboBox.getSelectedItem();
            String minDepositStr = minDepositField.getText().trim();
            String maxDepositStr = maxDepositField.getText().trim();
            String soKhachStr = soKhachField.getText().trim();
            String selectedKhuVuc = (String) khuVucComboBox.getSelectedItem();
            String selectedBan = banField.getText().trim();
            int selectedIndex = tangComboBox.getSelectedIndex();
            String selectedTang = tangListValue.get(selectedIndex);

            List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();

            // Lọc theo thông tin khách hàng
            if (!thongTinKHText.isEmpty() && !thongTinKHText.equals("Nhập thông tin KH...")) {
                int columnIndex = modelDatBan.findColumn("Thông tin KH");
                if (columnIndex != -1) {
                    filters.add(RowFilter.regexFilter("(?i)" + thongTinKHText, columnIndex));
                }
            }

            // Lọc theo ngày
            if (startDate != null && endDate != null) {
                try {
                    LocalDate start = startDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                    LocalDate end = endDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                    if (start.isAfter(end)) {
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể sau ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    filters.add(new RowFilter<DefaultTableModel, Object>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                            Object value = entry.getValue(1);
                            if (value instanceof Timestamp) {
                                Timestamp timestamp = (Timestamp) value;
                                LocalDate date = timestamp.toLocalDateTime().toLocalDate();
                                return (date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end));
                            }
                            return false;
                        }
                    });
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi lọc theo ngày: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Lọc theo trạng thái
            if (!selectedStatus.equals("Tất cả trạng thái")) {
                int statusIndex = modelDatBan.findColumn("Trạng thái");
                filters.add(RowFilter.regexFilter("(?i)" + selectedStatus, statusIndex));
            }

            // Lọc theo tiền cọc
            try {
                if (!minDepositStr.isEmpty()) {
                    double minDeposit = Double.parseDouble(minDepositStr);
                    filters.add(new RowFilter<DefaultTableModel, Object>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                            Object value = entry.getValue(3);
                            if (value instanceof Double) {
                                return (Double) value >= minDeposit;
                            }
                            return false;
                        }
                    });
                }
                if (!maxDepositStr.isEmpty()) {
                    double maxDeposit = Double.parseDouble(maxDepositStr);
                    filters.add(new RowFilter<DefaultTableModel, Object>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                            Object value = entry.getValue(3);
                            if (value instanceof Double) {
                                return (Double) value <= maxDeposit;
                            }
                            return false;
                        }
                    });
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Giá trị đặt cọc không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lọc theo số khách
            try {
                if (!soKhachStr.isEmpty()) {
                    int soKhach = Integer.parseInt(soKhachStr);
                    filters.add(new RowFilter<DefaultTableModel, Object>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                            Object value = entry.getValue(4);
                            if (value instanceof Integer) {
                                return (Integer) value == soKhach;
                            }
                            return false;
                        }
                    });
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Giá trị số khách không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lọc theo khu vực, bàn và vị trí
            if (!selectedKhuVuc.equals("Tất cả") || !selectedBan.isEmpty() || !selectedTang.equals("Tất cả")) {
                int khuVucBanIndex = modelDatBan.findColumn("Khu vực/Bàn");
                int viTriIndex = modelDatBan.findColumn("Vị trí");
                filters.add(new RowFilter<DefaultTableModel, Object>() {
                    @Override
                    public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                        Object khuVucBanValue = entry.getValue(khuVucBanIndex);
                        Object viTriValue = entry.getValue(viTriIndex);
                        if (khuVucBanValue == null || viTriValue == null) return false;
                        String khuVucBan = khuVucBanValue.toString();
                        String viTri = viTriValue.toString();
                        boolean matchKhuVuc = selectedKhuVuc.equals("Tất cả") || khuVucBan.toLowerCase().contains(selectedKhuVuc.toLowerCase());
                        boolean matchBan = selectedBan.isEmpty() || khuVucBan.toLowerCase().contains("bàn " + selectedBan.toLowerCase());
                        boolean matchTang = selectedTang.equals("Tất cả") || viTri.equals(selectedTang.equals("0") ? "Tầng trệt" : "Tầng " + selectedTang);
                        return matchKhuVuc && matchBan && matchTang;
                    }
                });
            }

            sorterDatBan.setRowFilter(filters.isEmpty() ? null : RowFilter.andFilter(filters));
        });

        resetButtonDatBan.addActionListener(e -> {
            thongTinKHField2.setText("Nhập thông tin KH...");
            startDateChooserDatBan.setDate(null);
            endDateChooserDatBan.setDate(null);
            statusComboBox.setSelectedIndex(0);
            minDepositField.setText("");
            maxDepositField.setText("");
            soKhachField.setText("");
            tangComboBox.setSelectedIndex(0);
            khuVucList.clear();
            khuVucList.add("Tất cả");
            try {
                List<String> allKhuVuc = lichSuDAO.getAllKhuVuc();
                khuVucList.addAll(allKhuVuc);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách khu vực: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            khuVucComboBox.setModel(new DefaultComboBoxModel<>(khuVucList));
            banField.setText("");
            sorterDatBan.setRowFilter(null);
            tableDatBan.clearSelection();
            detailButtonDatBan.setEnabled(false);
        });
    }

    private void showHoaDonDetailDialog(String maHD, Timestamp thoiGian, String nguoiTao, String thongTinKH, String phuongThuc, double tongTien) {
        JDialog dialog = new JDialog(this, "Chi tiết hóa đơn", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6, 2, 10, 10));
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        infoPanel.add(new JLabel("Mã hóa đơn:"));
        infoPanel.add(new JLabel(maHD));

        infoPanel.add(new JLabel("Thời gian:"));
        infoPanel.add(new JLabel(thoiGian.toString()));

        infoPanel.add(new JLabel("Người tạo:"));
        infoPanel.add(new JLabel(nguoiTao));

        infoPanel.add(new JLabel("Thông tin KH:"));
        infoPanel.add(new JLabel(thongTinKH));

        infoPanel.add(new JLabel("Phương thức:"));
        infoPanel.add(new JLabel(phuongThuc));

        infoPanel.add(new JLabel("Tổng tiền:"));
        infoPanel.add(new JLabel(String.format("%.2f", tongTien)));

        dialog.getContentPane().add(infoPanel, BorderLayout.NORTH);

        JPanel monAnPanel = new JPanel();
        monAnPanel.setLayout(new BorderLayout());
        monAnPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        JLabel monAnLabel = new JLabel("Danh sách món ăn:");
        monAnLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        monAnPanel.add(monAnLabel, BorderLayout.NORTH);

        String[] monAnColumns = {"Tên món", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel monAnModel = new DefaultTableModel(new Object[][]{}, monAnColumns);
        JTable monAnTable = new JTable(monAnModel);
        monAnTable.setRowHeight(25);
        monAnTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane monAnScrollPane = new JScrollPane(monAnTable);
        monAnPanel.add(monAnScrollPane, BorderLayout.CENTER);

        try {
            List<Object[]> chiTietMonAnList = lichSuDAO.getChiTietMonAnByMaHD(maHD);
            for (Object[] mon : chiTietMonAnList) {
                Vector<Object> row = new Vector<>();
                row.add(mon[0]); // tenMon
                row.add(mon[1]); // soLuong
                row.add(mon[2]); // donGia
                row.add(mon[3]); // thanhTien
                monAnModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dialog, "Lỗi khi lấy danh sách món ăn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        dialog.getContentPane().add(monAnPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Đóng");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(255, 153, 0));
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void showDatBanDetailDialog(String maDDB, Timestamp thoiGianNhanBan, String khachHang, double datCoc, int soKhach, String trangThai) {
        JDialog dialog = new JDialog(this, "Chi tiết đặt bàn", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(8, 2, 10, 10));
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        LocalDateTime thoiGianDat = null;
        String nguoiDat = null;
        try {
            thoiGianDat = lichSuDAO.getThoiGianDatByMaDDB(maDDB);
            nguoiDat = lichSuDAO.getNguoiDatByMaDDB(maDDB);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dialog, "Lỗi khi lấy thông tin đơn đặt bàn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        infoPanel.add(new JLabel("Mã đơn đặt bàn:"));
        infoPanel.add(new JLabel(maDDB));

        infoPanel.add(new JLabel("Thời gian đặt:"));
        infoPanel.add(new JLabel(thoiGianDat != null ? thoiGianDat.format(formatter) : "Không có dữ liệu"));

        infoPanel.add(new JLabel("Thời gian nhận bàn:"));
        infoPanel.add(new JLabel(thoiGianNhanBan.toLocalDateTime().format(formatter)));

        infoPanel.add(new JLabel("Khách hàng:"));
        infoPanel.add(new JLabel(khachHang));

        infoPanel.add(new JLabel("Người đặt:"));
        infoPanel.add(new JLabel(nguoiDat != null ? nguoiDat : "Không có dữ liệu"));

        infoPanel.add(new JLabel("Số khách:"));
        infoPanel.add(new JLabel(String.valueOf(soKhach)));

        infoPanel.add(new JLabel("Tiền cọc:"));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        infoPanel.add(new JLabel(currencyFormat.format(datCoc)));

        infoPanel.add(new JLabel("Trạng thái:"));
        infoPanel.add(new JLabel(trangThai));

        dialog.getContentPane().add(infoPanel, BorderLayout.NORTH);

        JPanel banPanel = new JPanel();
        banPanel.setLayout(new BorderLayout());
        banPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        JLabel banLabel = new JLabel("Danh sách bàn:");
        banLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        banPanel.add(banLabel, BorderLayout.NORTH);

        String[] banColumns = {"Khu vực", "Số bàn", "Vị trí", "Phụ thu"};
        DefaultTableModel banModel = new DefaultTableModel(new Object[][]{}, banColumns);
        JTable banTable = new JTable(banModel);
        banTable.setRowHeight(25);
        banTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane banScrollPane = new JScrollPane(banTable);
        banPanel.add(banScrollPane, BorderLayout.CENTER);

        try {
            List<Object[]> chiTietBanList = lichSuDAO.getChiTietBanByMaDDB(maDDB);
            for (Object[] ban : chiTietBanList) {
                Vector<Object> row = new Vector<>();
                row.add(ban[0]); // khuVuc
                row.add(ban[1]); // soBan
                int viTri = (int) ban[2];
                row.add("Tầng " + viTri); // viTri
                row.add(currencyFormat.format((Double) ban[3])); // phuThu
                banModel.addRow(row);
            }
            if (banModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(dialog, "Không có bàn nào được đặt cho đơn này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dialog, "Lỗi khi lấy danh sách bàn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        dialog.getContentPane().add(banPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Đóng");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(255, 153, 0));
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private Object[][] fetchHoaDonData() {
        try {
            return lichSuDAO.getAllHoaDon();
        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[0][];
        }
    }

    private Object[][] fetchDatBanDataWithViTri() {
        try {
            return lichSuDAO.getAllDonDatBanWithViTri();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu đơn đặt bàn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return new Object[0][];
        }
    }
    public JPanel getPanel() {
    	return this.panel_trangchu;
    }
}