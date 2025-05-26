package mail;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import entities.DonDatBan;

public class TestSendMail {

    public static void main(String[] args) throws EmailException, MalformedURLException {
//        sendOTP(MailConfig.RECEIVE_EMAIL);
//        sendPromotionCode(MailConfig.RECEIVE_EMAIL, "KM2024");
     // Tạo danh sách đơn đặt bàn
        ArrayList<DonDatBan> bookings = new ArrayList<>();
        bookings.add(new DonDatBan("DDB123","", "","", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 4, 200000, 0));

        // Tạo danh sách mã bàn
        ArrayList<String> tableCodes = new ArrayList<>();
        tableCodes.add("B01");
        tableCodes.add("B02");

        // Tạo danh sách món ăn
        Map<String, Integer> dishes = new HashMap<>();
        dishes.put("Cơm chiên", 2);
        dishes.put("Gà nướng", 1);

        // Gửi mail
        sendBookingInfo(MailConfig.RECEIVE_EMAIL, bookings, tableCodes, dishes);
    }

 // Gửi mã OTP
    public static String sendOTP(String toEmail) throws EmailException, MalformedURLException {
        String otpCode = generateOTP(4);
        String subject = "Mã xác thực OTP của bạn";
        String htmlContent = "<h2>Nhà hàng Takudo xin chào,</h2>"
                + "<p>Mã xác thực (OTP) của bạn là:</p>"
                + "<h1 style='color:blue;'>" + otpCode + "</h1>"
                + "<p>Vui lòng không chia sẻ mã này với bất kỳ ai.</p>"
                + "{{image}}"
                + "<br><p>Trân trọng</p>";
        String textContent = "Mã OTP của bạn là: " + otpCode;

        sendEmail(toEmail, subject, htmlContent, textContent, null); // không gửi kèm hình ảnh
        System.out.println("Đã gửi mã OTP: " + otpCode + " tới " + toEmail);
        return otpCode;
    }


    // Gửi mã khuyến mãi
    public static void sendPromotionCode(String toEmail, String promoCode) throws EmailException, MalformedURLException {
        String subject = "Mã khuyến mãi dành riêng cho bạn!";
        String htmlContent = "<h2>Nhà hàng Takudo trân trọng cảm ơn bạn,</h2>"
                + "<p>Mã khuyến mãi đặc biệt của bạn là:</p>"
                + "<h1 style='color:green;'>" + promoCode + "</h1>"
                + "<p>Hãy sử dụng mã này khi đặt món để nhận ưu đãi.</p>"
                + "{{image}}"
                + "<br><p>Chúc bạn ngon miệng!</p>";

        String textContent = "Mã khuyến mãi của bạn là: " + promoCode;
        String imagePath = "src/images/App/logo.png"; // Đường dẫn hình ảnh

        sendEmail(toEmail, subject, htmlContent, textContent, imagePath);
        System.out.println("Đã gửi mã khuyến mãi: " + promoCode + " tới " + toEmail);
    }

    public static void sendBookingInfo(String toEmail, ArrayList<DonDatBan> bookings, ArrayList<String> tableCodes, Map<String, Integer> dishes)
            throws EmailException, MalformedURLException {

        String subject = "Thông tin đơn đặt bàn của bạn tại Nhà hàng Takudo";

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<h2>Chi tiết đơn đặt bàn:</h2>");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (DonDatBan ddb : bookings) {
            htmlBuilder.append("<div style='margin-bottom:15px;'>")
                .append("<b>Mã đơn:</b> ").append(ddb.getMaDDB()).append("<br>")
                .append("<b>Thời gian đặt:</b> ").append(ddb.getThoiGianDat().format(formatter)).append("<br>")
                .append("<b>Thời gian nhận:</b> ").append(ddb.getThoiGianNhan().format(formatter)).append("<br>")
                .append("<b>Số khách:</b> ").append(ddb.getSoKhach()).append("<br>")
                .append("<b>Tiền cọc:</b> ").append(String.format("%.0f VND", ddb.getTienCoc())).append("<br>")
                .append("<b>Trạng thái:</b> ").append(ddb.getTrangThai()).append("<br>")
                .append("</div>");
        }

        htmlBuilder.append("<h3>Danh sách bàn đã đặt:</h3><ul>");
        for (String tableCode : tableCodes) {
            htmlBuilder.append("<li>Bàn ").append(tableCode).append("</li>");
        }
        htmlBuilder.append("</ul>");

        htmlBuilder.append("<h3>Danh sách món ăn:</h3><ul>");
        for (Map.Entry<String, Integer> entry : dishes.entrySet()) {
            htmlBuilder.append("<li>").append(entry.getKey()).append(": ").append(entry.getValue()).append(" phần</li>");
        }
        htmlBuilder.append("</ul>");

        htmlBuilder.append("<p>Trân trọng cảm ơn quý khách!</p>");

        String htmlContent = htmlBuilder.toString();
        String textContent = "Thông tin đơn đặt bàn đã được gửi. Vui lòng kiểm tra email HTML.";

        sendEmail(toEmail, subject, htmlContent, textContent, null);
        System.out.println("Đã gửi thông tin đơn đặt bàn tới " + toEmail);
    }
    // Hàm gửi email chung
    private static void sendEmail(String toEmail, String subject, String htmlContent, String textContent, String imagePath)
            throws EmailException, MalformedURLException {

        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setHostName(MailConfig.HOST_NAME);
        email.setSmtpPort(MailConfig.SSL_PORT);
        email.setAuthenticator(new DefaultAuthenticator(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD));
        email.setSSLOnConnect(true);
        email.setFrom(MailConfig.APP_EMAIL);
        email.addTo(toEmail);
        email.setSubject(subject);
        email.setCharset("UTF-8");

        // Thiết lập resolver để tránh lỗi null
        email.setDataSourceResolver(new DataSourceUrlResolver(new URL("file:" + new File(".").getAbsolutePath() + "/")));

        // Nhúng ảnh nếu có
        if (imagePath != null && !imagePath.isEmpty()) {
            File imageFile = new File(imagePath);
            String cid = email.embed(imageFile, "EmbeddedImage");
            htmlContent = htmlContent.replace("{{image}}", "<img src='cid:" + cid + "' alt='Image'>");
        } else {
            htmlContent = htmlContent.replace("{{image}}", "");
        }

        email.setHtmlMsg(htmlContent);
        email.setTextMsg(textContent);
        email.send();
    }



    // Sinh mã OTP ngẫu nhiên 4 chữ số
    public static String generateOTP(int length) {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // từ 1000 đến 9999
        return String.valueOf(otp);
    }
}
