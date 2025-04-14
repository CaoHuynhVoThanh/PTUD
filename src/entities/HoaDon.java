package entities;

import java.time.LocalDateTime;

public class HoaDon {
	private String maHD;
	private String maNV;
	private LocalDateTime thoiGianThanhToan;
	private String maCTKM;
	private double tongTien;
	private String phuongThucThanhToan;
	private String maTV;
	private double diemTL;
	
	public HoaDon(String maHD, String maNV, LocalDateTime thoiGianThanhToan, String maCTKM, double tongTien,
			String phuongThucThanhToan, String maTV, double diemTL) {
		this.maHD = maHD;
		this.maNV = maNV;
		this.thoiGianThanhToan = thoiGianThanhToan;
		this.maCTKM = maCTKM;
		this.tongTien = tongTien;
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.maTV = maTV;
		this.diemTL = diemTL;
	}
	
	
	public double getDiemTL() {
		return diemTL;
	}


	public void setDiemTL(double diemTL) {
		this.diemTL = diemTL;
	}


	public String getMaTV() {
		return maTV;
	}

	public void setMaTV(String maTV) {
		this.maTV = maTV;
	}

	public String getMaHD() {
		return maHD;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public LocalDateTime getThoiGianThanhToan() {
		return thoiGianThanhToan;
	}
	public void setThoiGianThanhToan(LocalDateTime thoiGianThanhToan) {
		this.thoiGianThanhToan = thoiGianThanhToan;
	}
	public String getMaCTKM() {
		return maCTKM;
	}
	public void setMaCTKM(String maCTKM) {
		this.maCTKM = maCTKM;
	}
	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}
	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", maNV=" + maNV + ", thoiGianThanhToan=" + thoiGianThanhToan + ", maCTKM="
				+ maCTKM + ", tongTien=" + tongTien + ", phuongThucThanhToan=" + phuongThucThanhToan + "]";
	}
	
	
}
