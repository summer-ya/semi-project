package dto;

public class Hotel {
	
	private int hotel_no;
	private String hotel_name;
	private String hotel_addr;
	private String hotel_tel;
	private String hotel_info;
	private String hotel_photo;
	private int mark_hit;
	private String hotel_intime;
	private String hotel_outtime;
	
	public Hotel() {
		super();
	}

	public Hotel(int hotel_no, String hotel_name, String hotel_addr, String hotel_tel, String hotel_info,
			String hotel_photo, int mark_hit, String hotel_intime, String hotel_outtime) {
		super();
		this.hotel_no = hotel_no;
		this.hotel_name = hotel_name;
		this.hotel_addr = hotel_addr;
		this.hotel_tel = hotel_tel;
		this.hotel_info = hotel_info;
		this.hotel_photo = hotel_photo;
		this.mark_hit = mark_hit;
		this.hotel_intime = hotel_intime;
		this.hotel_outtime = hotel_outtime;
	}

	public int getHotel_no() {
		return hotel_no;
	}

	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getHotel_addr() {
		return hotel_addr;
	}

	public void setHotel_addr(String hotel_addr) {
		this.hotel_addr = hotel_addr;
	}

	public String getHotel_tel() {
		return hotel_tel;
	}

	public void setHotel_tel(String hotel_tel) {
		this.hotel_tel = hotel_tel;
	}

	public String getHotel_info() {
		return hotel_info;
	}

	public void setHotel_info(String hotel_info) {
		this.hotel_info = hotel_info;
	}

	public String getHotel_photo() {
		return hotel_photo;
	}

	public void setHotel_photo(String hotel_photo) {
		this.hotel_photo = hotel_photo;
	}

	public int getMark_hit() {
		return mark_hit;
	}

	public void setMark_hit(int mark_hit) {
		this.mark_hit = mark_hit;
	}

	public String getHotel_intime() {
		return hotel_intime;
	}

	public void setHotel_intime(String hotel_intime) {
		this.hotel_intime = hotel_intime;
	}

	public String getHotel_outtime() {
		return hotel_outtime;
	}

	public void setHotel_outtime(String hotel_outtime) {
		this.hotel_outtime = hotel_outtime;
	}

	@Override
	public String toString() {
		return "Hotel [hotel_no=" + hotel_no + ", hotel_name=" + hotel_name + ", hotel_addr=" + hotel_addr
				+ ", hotel_tel=" + hotel_tel + ", hotel_info=" + hotel_info + ", hotel_photo=" + hotel_photo
				+ ", mark_hit=" + mark_hit + ", hotel_intime=" + hotel_intime + ", hotel_outtime=" + hotel_outtime
				+ "]";
	}
	
	

}
