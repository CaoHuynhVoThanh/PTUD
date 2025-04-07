package test;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class test3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Time Picker Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        // Tạo JComboBox cho giờ
        JComboBox<String> hourComboBox = new JComboBox<>();
        for (int i = 10; i <= 21; i++) {
            hourComboBox.addItem(String.format("%02d", i)); // Định dạng 2 chữ số
        }

        // Tạo JComboBox cho phút
        JComboBox<String> minuteComboBox = new JComboBox<>();
        minuteComboBox.addItem("00");
        minuteComboBox.addItem("30");

        // Tạo nút để hiển thị thời gian đã chọn
        JButton showTimeButton = new JButton("Show Selected Time");
        showTimeButton.addActionListener(e -> {
            String selectedHour = (String) hourComboBox.getSelectedItem();
            String selectedMinute = (String) minuteComboBox.getSelectedItem();
            JOptionPane.showMessageDialog(frame, 
                "Selected Time: " + selectedHour + ":" + selectedMinute);
        });

        // Thêm các component vào frame
        frame.add(new JLabel("Hour:"));
        frame.add(hourComboBox);
        frame.add(new JLabel("Minute:"));
        frame.add(minuteComboBox);
        frame.add(showTimeButton);

        frame.setVisible(true);
    }
}