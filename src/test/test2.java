package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test2 {
    private JFrame frame;
    private JTextField dateTextField;
    private JDialog dateDialog;
    private JPanel datePanel;
    private Calendar calendar;
    
    public static void main(String[] args) {
        new test2().createAndShowGUI();
    }
    
    private void createAndShowGUI() {
        frame = new JFrame("Custom Date Picker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        dateTextField = new JTextField(10);
        dateTextField.setEditable(false);
        
        JButton pickDateButton = new JButton("Chọn ngày");
        pickDateButton.addActionListener(e -> showDatePicker());
        
        panel.add(new JLabel("Ngày:"));
        panel.add(dateTextField);
        panel.add(pickDateButton);
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        // Khởi tạo dialog chọn ngày
        initDatePicker();
    }
    
    private void initDatePicker() {
        dateDialog = new JDialog(frame, "Chọn ngày", true);
        datePanel = new JPanel(new GridLayout(7, 7));
        
        calendar = Calendar.getInstance();
        updateDatePanel();
        
        JButton prevButton = new JButton("<");
        prevButton.addActionListener(e -> {
            calendar.add(Calendar.MONTH, -1);
            updateDatePanel();
        });
        
        JButton nextButton = new JButton(">");
        nextButton.addActionListener(e -> {
            calendar.add(Calendar.MONTH, 1);
            updateDatePanel();
        });
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(new JLabel(new SimpleDateFormat("MMMM yyyy").format(calendar.getTime())), 
                        BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);
        
        dateDialog.add(headerPanel, BorderLayout.NORTH);
        dateDialog.add(datePanel, BorderLayout.CENTER);
        dateDialog.setSize(300, 200);
    }
    
    private void updateDatePanel() {
        datePanel.removeAll();
        
        // Thêm tên các ngày trong tuần
        String[] days = {"CN", "T2", "T3", "T4", "T5", "T6", "T7"};
        for (String day : days) {
            datePanel.add(new JLabel(day, JLabel.CENTER));
        }
        
        // Đặt lại ngày đầu tháng
        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
        
        int firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        // Thêm các ô trống trước ngày đầu tiên
        for (int i = 1; i < firstDayOfWeek; i++) {
            datePanel.add(new JLabel(""));
        }
        
        // Thêm các ngày trong tháng
        for (int i = 1; i <= daysInMonth; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            final int day = i;
            dayButton.addActionListener(e -> {
                calendar.set(Calendar.DAY_OF_MONTH, day);
                dateTextField.setText(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
                dateDialog.setVisible(false);
            });
            datePanel.add(dayButton);
        }
        
        datePanel.revalidate();
        datePanel.repaint();
    }
    
    private void showDatePicker() {
        dateDialog.setLocationRelativeTo(frame);
        dateDialog.setVisible(true);
    }
}