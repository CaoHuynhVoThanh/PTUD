package entities;

public class KhuVuc {
	private String maKV;
	private String tenKV;
	private Double phuThu;
	public KhuVuc(String maKV, String tenKV, Double phuThu) {
		super();
		this.maKV = maKV;
		this.tenKV = tenKV;
		this.phuThu = phuThu;
	}
	public String getMaKV() {
		return maKV;
	}
	public void setMaKV(String maKV) {
		this.maKV = maKV;
	}
	public String getTenKV() {
		return tenKV;
	}
	public void setTenKV(String tenKV) {
		this.tenKV = tenKV;
	}
	public Double getPhuThu() {
		return phuThu;
	}
	public void setPhuThu(Double phuThu) {
		this.phuThu = phuThu;
	}
	
	
}
