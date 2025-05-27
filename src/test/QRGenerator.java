package test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRGenerator {
    public static void generateQRCode(String data, String path) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 300, 300);
            Path outputPath = FileSystems.getDefault().getPath(path);
            MatrixToImageWriter.writeToPath(matrix, "PNG", outputPath);
            System.out.println("Đã tạo QR Code tại: " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // Thông tin khách hàng
        String customerData = "Mã số: 12345, Họ tên: Nguyễn Văn A, Số điện thoại: 0901234567";
        
        // Đường dẫn lưu mã QR
        String filePath = "customer_qr.png";
        
        // Tạo mã QR
        generateQRCode(customerData, filePath);
    }
}
