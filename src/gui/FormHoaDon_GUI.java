package gui;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FormHoaDon_GUI extends JFrame {

    public FormHoaDon_GUI() {
        JButton btnExport = new JButton("Export PDF");
        btnExport.addActionListener(e -> exportReportToPDF());

        this.add(btnExport);
        this.setTitle("JasperReports PDF Export Example");
        this.setSize(300, 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void exportReportToPDF() {
        try {
            // 1. Biên dịch file JRXML thành file Jasper
            JasperReport jasperReport = JasperCompileManager.compileReport("report.jrxml");

            // 2. Tạo dữ liệu giả (nếu không có database)
            JRDataSource dataSource = new JREmptyDataSource();

            // 3. Tham số nếu cần
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("title", "Báo cáo Demo");

            // 4. Điền dữ liệu vào báo cáo
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // 5. Export ra PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "output-report.pdf");

            JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất PDF: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
		FormHoaDon_GUI d = new FormHoaDon_GUI();
		d.setVisible(true);
	}
}
