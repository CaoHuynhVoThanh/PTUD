package entities;

import java.time.LocalDateTime;

public class DonGoiMon {
	private String maDGM;
	private LocalDateTime thoiGianGM;
	private String ghiChu;
	
	public DonGoiMon(String maDGM, LocalDateTime thoiGianGM, String ghiChu) {
		this.maDGM = maDGM;
		this.thoiGianGM = thoiGianGM;
		this.ghiChu = ghiChu;
	}
	public String getMaDGM() {
		return maDGM;
	}
	public LocalDateTime getThoiGianGM() {
		return thoiGianGM;
	}
	public void setThoiGianGM(LocalDateTime thoiGianGM) {
		this.thoiGianGM = thoiGianGM;
	}
	
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Override
	public String toString() {
		return "DonGoiMon [maDGM=" + maDGM + ", thoiGianGM=" + thoiGianGM + "]";
	}
	
}
