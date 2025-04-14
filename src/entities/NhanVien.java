package entities;

import java.time.LocalDate;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String email;
	private String sdt;
	private String diaChi;
	private String chucVu;
	private LocalDate ngaySinh;
	private boolean trangThai;
	public NhanVien(String maNV, String tenNV, String email, String sdt, String diaChi, String chucVu,
			LocalDate ngaySinh, boolean trangThai) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.email = email;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
		this.ngaySinh = ngaySinh;
		this.trangThai = trangThai;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
