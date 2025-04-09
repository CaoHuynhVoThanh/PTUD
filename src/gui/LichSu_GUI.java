package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LichSu_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LichSu_GUI frame = new LichSu_GUI();
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
    public LichSu_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setSize(1537, 864);
        this.setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
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
        
        JLabel lblGio = new JLabel();
        lblGio.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblGio.setBounds(21, 39, 229, 25);
        
        // Định dạng ngày và giờ
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Cập nhật ngày và giờ mỗi giây
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Lấy ngày và giờ hiện tại
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();

                // Cập nhật JLabel
                lb_ngay.setText("Ngày: " + currentDate.format(dateFormatter));
                lb_thoiGian.setText("Thời gian: " + currentTime.format(timeFormatter));
            }
        }, 0, 1000); // Cập nhật mỗi giây (1000ms)
        
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
        
        JPanel panel_trangchu = new JPanel();
        panel_trangchu.setBackground(new Color(255, 255, 255));
        panel_trangchu.setBounds(285, 133, 1254, 704);
        contentPane.add(panel_trangchu);
        panel_trangchu.setLayout(null);
        
     // Sử dụng JTabbedPane để tạo hai tab
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(31, 32, 1199, 650);
        panel_trangchu.add(tabbedPane);

        // Tab "Lịch sử hóa đơn"
        JPanel panel_lichSuHoaDon = new JPanel();
        panel_lichSuHoaDon.setBackground(Color.WHITE);
        panel_lichSuHoaDon.setLayout(null);
        tabbedPane.addTab("Lịch sử hóa đơn", null, panel_lichSuHoaDon, null);

        // Ô tìm kiếm cho "Lịch sử hóa đơn"
        JTextField searchFieldHoaDon = new JTextField("Nhập từ khóa cần tìm kiếm...");
        searchFieldHoaDon.setForeground(Color.GRAY);
        searchFieldHoaDon.setBounds(20, 10, 300, 30);
        // Không sử dụng setRequestFocusEnabled(false) nữa
        panel_lichSuHoaDon.add(searchFieldHoaDon);

        // Thêm FocusListener cho ô tìm kiếm "Lịch sử hóa đơn"
        searchFieldHoaDon.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchFieldHoaDon.getText().equals("Nhập từ khóa cần tìm kiếm...")) {
                    searchFieldHoaDon.setText("");
                    searchFieldHoaDon.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchFieldHoaDon.getText().trim().isEmpty()) {
                    searchFieldHoaDon.setText("Nhập từ khóa cần tìm kiếm...");
                    searchFieldHoaDon.setForeground(Color.GRAY);
                }
            }
        });

        // Thêm ComponentListener để ngăn tự động focus khi tab được chọn
        panel_lichSuHoaDon.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    panel_lichSuHoaDon.requestFocusInWindow(); // Chuyển focus về panel
                });
            }
        });

        // Bảng dữ liệu và nút "Xem chi tiết" cho "Lịch sử hóa đơn" 
        String[] columnsHoaDon = {"Mã HD", "Thời gian", "Người tạo", "Thông tin KH", "Phương thức", "Tổng tiền"};
        Object[][] dataHoaDon = {
            {"200325-111111-0001", "20/03/2025 18:00", "Lê Vinh A", "Lê Vinh B", "Tiền mặt", "600.000 đ"},
            {"200325-222222-0002", "20/03/2025 18:10", "Lê Vinh A", "Lê Vinh Vô", "Tiền mặt", "400.000 đ"},
            {"200325-333333-0003", "20/03/2025 19:00", "Lê Vinh A", "Chi Phèo", "Chuyển khoản", "800.000 đ"}
        };
        JTable tableHoaDon = new JTable(dataHoaDon, columnsHoaDon);
        tableHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tableHoaDon.setRowHeight(30);
        JScrollPane scrollPaneHoaDon = new JScrollPane(tableHoaDon);
        scrollPaneHoaDon.setBounds(0, 50, 1199, 570);
        panel_lichSuHoaDon.add(scrollPaneHoaDon);

        JButton viewDetailsButtonHoaDon = new JButton("Xem chi tiết");
        viewDetailsButtonHoaDon.setBackground(new Color(0, 123, 255));
        viewDetailsButtonHoaDon.setForeground(Color.WHITE);
        viewDetailsButtonHoaDon.setBounds(1064, 10, 120, 30);
        panel_lichSuHoaDon.add(viewDetailsButtonHoaDon);

        // Tab "Lịch sử đặt bàn"
        JPanel panel_lichSuDatBan = new JPanel();
        panel_lichSuDatBan.setBackground(Color.WHITE);
        panel_lichSuDatBan.setLayout(null);
        tabbedPane.addTab("Lịch sử đặt bàn", null, panel_lichSuDatBan, null);

        // Ô tìm kiếm cho "Lịch sử đặt bàn"
        JTextField searchFieldDatBan = new JTextField("Nhập từ khóa cần tìm kiếm...");
        searchFieldDatBan.setForeground(Color.GRAY);
        searchFieldDatBan.setBounds(20, 10, 300, 30);
        panel_lichSuDatBan.add(searchFieldDatBan);

        // Thêm FocusListener cho ô tìm kiếm "Lịch sử đặt bàn"
        searchFieldDatBan.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchFieldDatBan.getText().equals("Nhập từ khóa cần tìm kiếm...")) {
                    searchFieldDatBan.setText("");
                    searchFieldDatBan.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchFieldDatBan.getText().trim().isEmpty()) {
                    searchFieldDatBan.setText("Nhập từ khóa cần tìm kiếm...");
                    searchFieldDatBan.setForeground(Color.GRAY);
                }
            }
        });

        // Thêm ComponentListener để ngăn tự động focus khi tab được chọn
        panel_lichSuDatBan.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    panel_lichSuDatBan.requestFocusInWindow(); // Chuyển focus về panel
                });
            }
        });

        // Bảng dữ liệu cho "Lịch sử đặt bàn"
        String[] columnsDatBan = {"Thời gian nhận bàn", "Khách hàng", "Đặt cọc", "Số khách", "Khu vực/Bàn", "Trạng thái"};
        Object[][] dataDatBan = {
            {"20/03/2025 (8 - 10 giờ)", "Lê Thị C", "100.000 đ", "3", "Sảnh chính, bàn 2", "Đã nhận bàn"},
            {"20/03/2025 (8 - 10 giờ)", "Lê Văn A", "100.000 đ", "3", "Sảnh chính, bàn 3", "Chưa nhận bàn"},
            {"20/03/2025 (8 - 10 giờ)", "Văn Nhật A", "100.000 đ", "3", "Sảnh chính, bàn 4", "Chưa nhận bàn"}
        };
        JTable tableDatBan = new JTable(dataDatBan, columnsDatBan);
        tableDatBan.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tableDatBan.setRowHeight(30);
        JScrollPane scrollPaneDatBan = new JScrollPane(tableDatBan);
        scrollPaneDatBan.setBounds(0, 50, 1199, 570);
        panel_lichSuDatBan.add(scrollPaneDatBan);

        // Nút "Xem chi tiết" cho "Lịch sử đặt bàn"
        JButton viewDetailsButtonDatBan = new JButton("Xem chi tiết");
        viewDetailsButtonDatBan.setBackground(new Color(0, 123, 255));
        viewDetailsButtonDatBan.setForeground(Color.WHITE);
        viewDetailsButtonDatBan.setBounds(1064, 10, 120, 30);
        panel_lichSuDatBan.add(viewDetailsButtonDatBan);

    }
}