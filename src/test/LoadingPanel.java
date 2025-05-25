package test;
import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class LoadingPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel loadingPanel;
    private final JPanel contentPanelWrapper;

    public LoadingPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setBackground(Color.white);

        // Panel loading với ảnh gif
        loadingPanel = new JPanel(new BorderLayout());
        loadingPanel.setBackground(Color.white);
        ImageIcon gif = new ImageIcon("E:\\progress.gif"); // chỉnh đường dẫn nếu cần
        Image scaled = gif.getImage();
        JLabel gifLabel = new JLabel(new ImageIcon(scaled));
        gifLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingPanel.add(gifLabel, BorderLayout.CENTER);

        // Wrapper cho nội dung thực tế
        contentPanelWrapper = new JPanel(new BorderLayout());

        add(loadingPanel, "loading");
        add(contentPanelWrapper, "content");
    }

    /**
     * Hiển thị ảnh loading rồi load GUI nặng ở background.
     * @param panelSupplier lambda trả về JPanel sau khi load
     * @param delayMillis delay giả lập hoặc thực tế nếu cần
     */
    public void loadAsync(Supplier<JPanel> panelSupplier, int delayMillis) {
        cardLayout.show(this, "loading");

        new SwingWorker<JPanel, Void>() {
            @Override
            protected JPanel doInBackground() throws Exception {
                Thread.sleep(delayMillis); // giả lập delay, có thể bỏ nếu không cần
                return panelSupplier.get(); // gọi lambda để lấy panel
            }

            @Override
            protected void done() {
                try {
                    JPanel panel = get();
                    contentPanelWrapper.removeAll();
                    contentPanelWrapper.add(panel, BorderLayout.CENTER);
                    contentPanelWrapper.revalidate();
                    contentPanelWrapper.repaint();
                    cardLayout.show(LoadingPanel.this, "content");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}
