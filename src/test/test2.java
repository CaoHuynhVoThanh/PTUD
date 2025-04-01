package test;

import javax.swing.*;
import java.awt.*;

public class test2 {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("JButton với Hình ảnh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Tạo ImageIcon
        ImageIcon icon = new ImageIcon("D:\\ProjectPTUD\\PTUD\\src\\images\\iconSearch.png"); // Đường dẫn ảnh
        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        
        // Tạo JButton với hình ảnh
        JButton button = new JButton();
        button.setIcon(new ImageIcon(img));
//        button.setBorderPainted(false);
//        button.setContentAreaFilled(false);
//        button.setFocusPainted(false);
        button.setOpaque(false);
        // Thêm nút vào frame
        frame.add(button);

        // Hiển thị JFrame
        frame.setVisible(true);
    }
}
