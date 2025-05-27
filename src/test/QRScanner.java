package test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.LuminanceSource;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.Result;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class QRScanner {
    public static String readQRCode(String path) {
        try {
            // Đọc ảnh QR Code từ file
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);

            // Chuyển BufferedImage thành LuminanceSource
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            
            // Tạo BinaryBitmap từ LuminanceSource sử dụng GlobalHistogramBinarizer
            BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
            
            // Quét mã QR
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);
            
            // Trả về thông tin trong mã QR
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // Đọc thông tin mã QR từ file
        String qrCodeData = readQRCode("customer_qr.png");
        
        if (qrCodeData != null) {
            System.out.println("Thông tin khách hàng từ QR Code: " + qrCodeData);
        } else {
            System.out.println("Không thể đọc QR Code!");
        }
    }
}



