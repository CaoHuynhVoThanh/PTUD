package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingScreen extends JFrame {

    public LoadingScreen() {
        // Set up the frame
        setTitle("Loading");
        setBounds(400, 200, 750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create logo
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/images/App/logo.png")));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create loading label
        JLabel loadingLabel = new JLabel("Đang tải...");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        loadingLabel.setForeground(new Color(255, 111, 0));
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create progress bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(200, 20));
        progressBar.setForeground(new Color(255, 111, 0));

        // Inner panel with padding
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(18, 18, 18));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Padding
        innerPanel.add(Box.createVerticalStrut(50));
        innerPanel.add(logoLabel);
        innerPanel.add(Box.createVerticalStrut(20));
        innerPanel.add(loadingLabel);
        innerPanel.add(Box.createVerticalStrut(20));
        innerPanel.add(progressBar);
        innerPanel.add(Box.createVerticalStrut(50));

        // Outer panel to center the content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(18, 18, 18));
        contentPanel.add(innerPanel, BorderLayout.CENTER);

        add(contentPanel);

        // Timer to simulate loading
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dispose(); // Close the loading screen
                showMainApp(); // Open the main application
            }
        }, 3000); // 3 seconds delay
    }

    private void showMainApp() {
        // Create and display the main application window
//        DangNhap_GUI mainApp = new DangNhap_GUI();
//        mainApp.setVisible(true);
//        mainApp.toFront();
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.setVisible(true);
        });
    }
}
