package test;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.print.*;

public class HoaDonForm extends JFrame {

    private JPanel panelHoaDon;

    public HoaDonForm() {
        setTitle("In hóa đơn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 600);
        getContentPane().setLayout(null);

        panelHoaDon = new JPanel();
        panelHoaDon.setLayout(new BoxLayout(panelHoaDon, BoxLayout.Y_AXIS));
        panelHoaDon.setBounds(10, 10, 360, 500);
        panelHoaDon.setBackground(Color.WHITE);
        getContentPane().add(panelHoaDon);

        JButton btnIn = new JButton("In hóa đơn");
        btnIn.setBounds(130, 520, 120, 30);
        getContentPane().add(btnIn);

        // Sự kiện nút In
        btnIn.addActionListener(e -> prePrint(panelHoaDon));
    }

    // Hàm mở cửa sổ preprint
    private void prePrint(JPanel panel) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("In hóa đơn");

        printerJob.setPrintable(new Printable() {
            public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
                if (page > 0) return NO_SUCH_PAGE;
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pf.getImageableX(), pf.getImageableY());
                panel.printAll(g2d);
                return PAGE_EXISTS;
            }
        });

        boolean accept = printerJob.printDialog(); // Hiển thị cửa sổ preview và chọn máy in
        if (accept) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi in: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            HoaDonForm frame = new HoaDonForm();
            frame.setVisible(true);
        });
    }
}
