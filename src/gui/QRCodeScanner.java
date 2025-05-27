package gui;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import connectDB.ConnectDB;
import dao.QuanLyThanhVien_DAO;
import entities.ThanhVien;

import org.opencv.objdetect.QRCodeDetector;

public class QRCodeScanner {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Khong the mo webcam");
            return;
        }

        Mat frame = new Mat();
        QRCodeDetector detector = new QRCodeDetector();

        System.out.println("Dua ma QR truoc camera...");

        while (true) {
            if (camera.read(frame)) {
                String data = detector.detectAndDecode(frame);
                if (!data.isEmpty()) {
                    System.out.println("Da quet duoc thanh vien: " + data);

                    ConnectDB.getInstance().connect(); 
                    QuanLyThanhVien_DAO dao = new QuanLyThanhVien_DAO(ConnectDB.getInstance().getConnection());
                    ThanhVien tv = dao.timThanhVienTheoMa(data);

                    if (tv != null) {
                        System.out.println("== Thong tin thanh vien==");
                        System.out.println("Ma TV: " + tv.getMaTV());
                        System.out.println("Ten KH: " + tv.getTenTV());
                        System.out.println("Diem tich luy: " + tv.getDiemTichLuy());
                    } else {
                        System.out.println("Khong tim thay ma: " + data);
                    }

                    break;
                }
            }
        }


        camera.release();
        System.out.println("END.");
    }
}


