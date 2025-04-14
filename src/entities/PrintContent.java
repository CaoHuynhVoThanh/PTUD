package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintContent implements Printable {
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Nội dung hóa đơn vẽ ra
        g.drawString("HÓA ĐƠN THANH TOÁN", 100, 100);
        g.drawString("Mã hóa đơn: HD001", 100, 120);
        g.drawString("Tổng tiền: 150,000 VND", 100, 140);
        // Thêm các nội dung khác...

        return PAGE_EXISTS;
    }
}
