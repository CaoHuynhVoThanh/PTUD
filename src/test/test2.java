package test;

import javax.swing.*;
import java.awt.*;

public class test2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(test2::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Food Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 10, 10)); // 3 hàng, 3 cột, khoảng cách 10px
        panel.setBackground(Color.WHITE);

        // Dữ liệu món ăn (Ảnh, Giá, Tên)
        String[][] foods = {
            {"avt.png", "200.000 VND", "Mì Soba"},
            {"avt.png", "100.000 VND", "Bánh xếp Gyoza"},
            {"avt.png", "150.000 VND", "Kimpira"},
            {"avt.png", "110.000 VND", "Cơm Taco"},
            {"avt.png", "30.000 VND", "Súp Miso"},
            {"avt.png", "300.000 VND", "Sushi"},
            {"avt.png", "350.000 VND", "Sashimi"},
            {"avt.png", "100.000 VND", "Tempura"},
            {"avt.png", "220.000 VND", "Cơm cà ri"}
        };

        // Tạo các ô món ăn
        for (String[] food : foods) {
            panel.add(createFoodPanel(food[0], food[1], food[2]));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private static JPanel createFoodPanel(String imagePath, String price, String name) {
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(new BorderLayout());
        foodPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        foodPanel.setBackground(Color.WHITE);

        // Ảnh món ăn
        ImageIcon icon = new ImageIcon("src/images/" + imagePath); // Đường dẫn ảnh
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Giá
        JLabel priceLabel = new JLabel(price);
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel.setForeground(Color.BLACK);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Tên món
        JLabel nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setForeground(Color.ORANGE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Sắp xếp các thành phần trong Panel
        foodPanel.add(imageLabel, BorderLayout.CENTER);
        foodPanel.add(priceLabel, BorderLayout.NORTH);
        foodPanel.add(nameLabel, BorderLayout.SOUTH);

        return foodPanel;
    }
}

