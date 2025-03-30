package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test1 {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("Dropdown Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 300);
        frame.setLayout(null);

        // Tạo JButton (Nút chính)
        JButton menuButton = new JButton("QUẢN LÝ ▼");
        menuButton.setBounds(20, 20, 150, 40);
        menuButton.setFont(new Font("Arial", Font.BOLD, 14));
        menuButton.setForeground(Color.WHITE);
        menuButton.setBackground(new Color(255, 140, 0)); // Màu cam
        menuButton.setFocusPainted(false);

        // Tạo JPopupMenu (Danh sách xổ xuống)
        JPopupMenu dropdownMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("Bàn");
        JMenuItem item2 = new JMenuItem("Món");
        JMenuItem item3 = new JMenuItem("Nhân sự");
        JMenuItem item4 = new JMenuItem("Thành viên");
        JMenuItem item5 = new JMenuItem("Phụ phí & Ưu đãi");

        // Thêm các mục vào menu
        dropdownMenu.add(item1);
        dropdownMenu.add(item2);
        dropdownMenu.add(item3);
        dropdownMenu.add(item4);
        dropdownMenu.add(item5);

        // Xử lý sự kiện khi nhấn nút
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownMenu.show(menuButton, 0, menuButton.getHeight());
            }
        });

        // Thêm vào frame
        frame.add(menuButton);
        frame.setVisible(true);
    }
}
