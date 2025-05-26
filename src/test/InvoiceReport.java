package test;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class InvoiceReport {
	public static String norText(String str) {
        if (str == null) return null;

        // Chuẩn hóa Unicode thành dạng decomposed (NFD)
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);

        // Loại bỏ các dấu thanh (dấu sắc, huyền, hỏi, ngã, nặng)
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String noDiacritics = pattern.matcher(normalized).replaceAll("");

        // Thay thế riêng ký tự Đ và đ
        noDiacritics = noDiacritics.replace("Đ", "D").replace("đ", "d");

        return noDiacritics;
    }
    public static void main(String[] args) {
        try {
        	
            // Đường dẫn tới file jrxml bạn đã upload
            String jrxmlFile = "C:\\Users\\dell\\JaspersoftWorkspace\\MyReports\\Blank_Letter_2.jrxml";

            // Biên dịch file jrxml -> jasper
            JasperReport report = JasperCompileManager.compileReport(jrxmlFile);
        	
            // Dữ liệu danh sách sản phẩm dưới dạng Map
            List<Map<String, ?>> dataList = new ArrayList<>();

            Map<String, Object> item1 = new HashMap<>();
            item1.put("productName", norText("Sản phẩm A"));
            item1.put("quantity", 2);
            item1.put("unitPrice", 50000.0);
            item1.put("amount", 2 * 50000.0);
            dataList.add(item1);

            Map<String, Object> item2 = new HashMap<>();
            item2.put("productName", norText("Sản phẩm B"));
            item2.put("quantity", 1);
            item2.put("unitPrice", 75000.0);
            item2.put("amount", 1 * 75000.0);
            dataList.add(item2);
            
            Map<String, Object> item3 = new HashMap<>();
            item3.put("productName", norText("Sản phẩm C"));
            item3.put("quantity", 1);
            item3.put("unitPrice", 75000.0);
            item3.put("amount", 1 * 75000.0);
            dataList.add(item3);
            
            Map<String, Object> item4 = new HashMap<>();
            item4.put("productName", norText("Sản phẩm D"));
            item4.put("quantity", 1);
            item4.put("unitPrice", 75000.0);
            item4.put("amount", 1 * 75000.0);
            dataList.add(item4);

            // Tạo datasource
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(dataList);

            // Thêm tham số nếu có
            Map<String, Object> params = new HashMap<>();
            params.put("address", "So 13 Nguyen Van Bao, Phuong 7, Go Vap");
            params.put("cashier", norText("Cao Huỳnh Võ Thanh"));
            params.put("invoiceID", norText("HD202500001"));
            LocalDateTime d = LocalDateTime.now();
            params.put("dateTime", d.toString());
            

            // Fill và in ra PDF
            JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
//            JasperExportManager.exportReportToPdfFile(print, "invoice_output.pdf");
            JasperViewer.viewReport(print, false); // false: không thoát ứng dụng khi đóng viewer


            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
