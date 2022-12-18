package dto;

public class SortedHotel {

	private int hotel_no;
	private String hotel_tel;
	private String hotel_name;
	private String hotel_photo;
	private double hotel_score; 
	private int hotel_reviewCnt; 

	public SortedHotel() {}

	public SortedHotel(int hotel_no, String hotel_tel, String hotel_name, String hotel_photo, double hotel_score,
			int hotel_reviewCnt) {
		super();
		this.hotel_no = hotel_no;
		this.hotel_tel = hotel_tel;
		this.hotel_name = hotel_name;
		this.hotel_photo = hotel_photo;
		this.hotel_score = hotel_score;
		this.hotel_reviewCnt = hotel_reviewCnt;
	}

	@Override
	public String toString() {
		return "SortedHotel [hotel_no=" + hotel_no + ", hotel_tel=" + hotel_tel + ", hotel_name=" + hotel_name
				+ ", hotel_photo=" + hotel_photo + ", hotel_score=" + hotel_score + ", hotel_reviewCnt="
				+ hotel_reviewCnt + "]";
	}

	public int getHotel_no() {
		return hotel_no;
	}

	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
	}

	public String getHotel_tel() {
		return hotel_tel;
	}

	public void setHotel_tel(String hotel_tel) {
		this.hotel_tel = hotel_tel;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getHotel_photo() {
		return hotel_photo;
	}

	public void setHotel_photo(String hotel_photo) {
		this.hotel_photo = hotel_photo;
	}

	public double getHotel_score() {
		return hotel_score;
	}

	public void setHotel_score(double hotel_score) {
		this.hotel_score = hotel_score;
	}

	public int getHotel_reviewCnt() {
		return hotel_reviewCnt;
	}

	public void setHotel_reviewCnt(int hotel_reviewCnt) {
		this.hotel_reviewCnt = hotel_reviewCnt;
	}

}
