package dto;

public class Mark {

	private int mark_no;
	private int hotel_no;
	private int user_no;
	
	public Mark() {}
	
	public Mark(int mark_no, int hotel_no, int user_no) {
		super();
		this.mark_no = mark_no;
		this.hotel_no = hotel_no;
		this.user_no = user_no;
	}

	@Override
	public String toString() {
		return "Mark [mark_no=" + mark_no + ", hotel_no=" + hotel_no + ", user_no=" + user_no + "]";
	}

	public int getMark_no() {
		return mark_no;
	}

	public void setMark_no(int mark_no) {
		this.mark_no = mark_no;
	}

	public int getHotel_no() {
		return hotel_no;
	}

	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
}
