package test;
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class PrePrintWindow extends JFrame implements Printable {

    private JPanel panelToPrint;

    public PrePrintWindow() {
        setTitle("Xem trước và In hóa đơn");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Nội dung hóa đơn (có thể thay đổi tuỳ ý)
        panelToPrint = new JPanel();
        panelToPrint.setLayout(new BoxLayout(panelToPrint, BoxLayout.Y_AXIS));
        panelToPrint.setBackground(Color.WHITE);

        panelToPrint.add(new JLabel("===== HÓA ĐƠN ====="));
        panelToPrint.add(new JLabel("Mã HD: HD001"));
        panelToPrint.add(new JLabel("Nhân viên: NV01"));
        panelToPrint.add(new JLabel("Tổng tiền: 150.000 VNĐ"));
        panelToPrint.add(Box.createVerticalStrut(20));
        panelToPrint.add(new JLabel("Cảm ơn quý khách!"));

        JScrollPane scrollPane = new JScrollPane(panelToPrint);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Nút In
        JButton btnPrint = new JButton("In");
        btnPrint.addActionListener(e -> printInvoice());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnPrint);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    // Hàm in
    private void printInvoice() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("In hóa đơn");
        job.setPrintable(this);

        boolean doPrint = job.printDialog(); // Hiện hộp thoại in
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Cài đặt in
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        panelToPrint.printAll(g2d);
        return PAGE_EXISTS;
    }

    // Main để chạy thử
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrePrintWindow window = new PrePrintWindow();
            window.setVisible(true);
        });
    }
}
