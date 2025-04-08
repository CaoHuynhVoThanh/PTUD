package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class test1 extends JFrame {
    private JPanel infoPanel;
    private JPanel tablesPanel;
    private Map<String, Table> tables = new HashMap<>();
    
    public test1() {
        setTitle("Hệ thống đặt bàn nhà hàng");
        setSize(800, 500);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Tạo panel thông tin bên phải
        createInfoPanel();
        
        // Tạo panel bàn bên trái
        createTablesPanel();
        
        // Thêm dữ liệu mẫu
        addSampleTables();
    }
    
    private void createInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setPreferredSize(new Dimension(300, getHeight()));
        
        // Thêm các trường thông tin
        String[] labels = {
            "Mã đơn đặt bàn:", "Mã bàn:", "Loại bàn", "Vị trí:", 
            "Khu vực:", "Trạng thái:", "Mã đơn:", "Tên khách hàng:", "Số điện thoại:"
        };
        
        for (String label : labels) {
            JLabel lbl = new JLabel(label);
            JTextField txt = new JTextField(20);
            txt.setEditable(false);
            
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel.add(lbl);
            panel.add(txt);
            infoPanel.add(panel);
        }
        
        // Thêm các nút chức năng
        JButton bookBtn = new JButton("Đặt bàn");
        JButton moveBtn = new JButton("Chuyển bàn");
        JButton cancelBtn = new JButton("Hủy bàn");
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(bookBtn);
        buttonPanel.add(moveBtn);
        buttonPanel.add(cancelBtn);
        
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(buttonPanel);
        
        add(infoPanel, BorderLayout.EAST);
    }
    
    private void createTablesPanel() {
        tablesPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(tablesPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void addSampleTables() {
        // Thêm các bàn mẫu
        addTable("B01", "Bàn 2 người", "Gần cửa sổ", "Khu A", "Trống");
        addTable("B02", "Bàn 4 người", "Góc phải", "Khu A", "Đã đặt");
        addTable("B03", "Bàn 6 người", "Giữa nhà", "Khu B", "Trống");
        addTable("B04", "Bàn 2 người", "Gần quầy bar", "Khu C", "Đã đặt");
        addTable("B05", "Bàn 8 người", "Phòng VIP", "Khu VIP", "Trống");
        addTable("B06", "Bàn 4 người", "Gần lối ra", "Khu B", "Trống");
        addTable("B07", "Bàn 2 người", "Góc trái", "Khu A", "Đã đặt");
        addTable("B08", "Bàn 10 người", "Phòng lớn", "Khu VIP", "Trống");
    }
    
    private void addTable(String id, String type, String position, String area, String status) {
        Table table = new Table(id, type, position, area, status);
        tables.put(id, table);
        
        JButton tableBtn = new JButton(table.getStatusIcon());
        tableBtn.setToolTipText("Bàn " + id);
        tableBtn.setPreferredSize(new Dimension(80, 80));
        tableBtn.addActionListener(new TableClickListener(id));
        
        tablesPanel.add(tableBtn);
    }
    
    private void displayTableInfo(String tableId) {
        Table table = tables.get(tableId);
        if (table != null) {
            Component[] components = infoPanel.getComponents();
            int index = 0;
            
            // Cập nhật thông tin
            ((JTextField)((JPanel)components[index++]).getComponent(1)).setText("OD" + tableId.hashCode());
            ((JTextField)((JPanel)components[index++]).getComponent(1)).setText(tableId);
            ((JTextField)((JPanel)components[index++]).getComponent(1)).setText(table.getType());
            ((JTextField)((JPanel)components[index++]).getComponent(1)).setText(table.getPosition());
            ((JTextField)((JPanel)components[index++]).getComponent(1)).setText(table.getArea());
            ((JTextField)((JPanel)components[index++]).getComponent(1)).setText(table.getStatus());
            
            // Nếu bàn đã đặt, hiển thị thông tin đặt
            if ("Đã đặt".equals(table.getStatus())) {
                ((JTextField)((JPanel)components[index++]).getComponent(1)).setText("INV" + tableId.hashCode());
                ((JTextField)((JPanel)components[index++]).getComponent(1)).setText("Khách hàng " + tableId);
                ((JTextField)((JPanel)components[index++]).getComponent(1)).setText("0987" + (1000 + tableId.hashCode() % 9000));
            } else {
                // Xóa thông tin nếu bàn trống
                ((JTextField)((JPanel)components[index++]).getComponent(1)).setText("");
                ((JTextField)((JPanel)components[index++]).getComponent(1)).setText("");
                ((JTextField)((JPanel)components[index++]).getComponent(1)).setText("");
            }
        }
    }
    
    private class TableClickListener implements ActionListener {
        private String tableId;
        
        public TableClickListener(String tableId) {
            this.tableId = tableId;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            displayTableInfo(tableId);
        }
    }
    
    private class Table {
        private String id;
        private String type;
        private String position;
        private String area;
        private String status;
        
        public Table(String id, String type, String position, String area, String status) {
            this.id = id;
            this.type = type;
            this.position = position;
            this.area = area;
            this.status = status;
        }
        
        public Icon getStatusIcon() {
            // Tạo icon đơn giản dựa trên trạng thái
            if ("Đã đặt".equals(status)) {
                return new ColorIcon(Color.RED, 60, 60);
            } else {
                return new ColorIcon(Color.GREEN, 60, 60);
            }
        }
        
        // Getter methods
        public String getId() { return id; }
        public String getType() { return type; }
        public String getPosition() { return position; }
        public String getArea() { return area; }
        public String getStatus() { return status; }
    }
    
    // Lớp helper để tạo icon màu đơn giản
    private static class ColorIcon implements Icon {
        private Color color;
        private int width;
        private int height;
        
        public ColorIcon(Color color, int width, int height) {
            this.color = color;
            this.width = width;
            this.height = height;
        }
        
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillOval(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, width, height);
        }
        
        @Override
        public int getIconWidth() { return width; }
        
        @Override
        public int getIconHeight() { return height; }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            test1 system = new test1();
            system.setVisible(true);
        });
    }
}