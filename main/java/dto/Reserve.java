package dto;
// 결제페이지 반영하기 위한 전용 DTO
public class Reserve {

	private String user_name; // 회원이름
	private String user_phone; // 회원 전화번호
	private String room_price; // 객실가격
	private String hotel_name; // 호텔이름
	private int Hotel_no;
	private int booking_no;
	private int user_no;
	private int room_no;
	private String room_type; // 객실이름
	private String hotel_intime; // 입실시간
	private String hotel_outtime; // 퇴실시간
	private String hotel_in; // 체크인날짜
	private String hotel_out; // 체크아웃날짜
	
	public Reserve() {}

	public Reserve(String user_name, String user_phone, String room_price, String hotel_name, int hotel_no,
			int booking_no, int user_no, int room_no, String room_type, String hotel_intime, String hotel_outtime,
			String hotel_in, String hotel_out) {
		super();
		this.user_name = user_name;
		this.user_phone = user_phone;
		this.room_price = room_price;
		this.hotel_name = hotel_name;
		Hotel_no = hotel_no;
		this.booking_no = booking_no;
		this.user_no = user_no;
		this.room_no = room_no;
		this.room_type = room_type;
		this.hotel_intime = hotel_intime;
		this.hotel_outtime = hotel_outtime;
		this.hotel_in = hotel_in;
		this.hotel_out = hotel_out;
	}

	@Override
	public String toString() {
		return "Reserve [user_name=" + user_name + ", user_phone=" + user_phone + ", room_price=" + room_price
				+ ", hotel_name=" + hotel_name + ", Hotel_no=" + Hotel_no + ", booking_no=" + booking_no + ", user_no="
				+ user_no + ", room_no=" + room_no + ", room_type=" + room_type + ", hotel_intime=" + hotel_intime
				+ ", hotel_outtime=" + hotel_outtime + ", hotel_in=" + hotel_in + ", hotel_out=" + hotel_out + "]";
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getRoom_price() {
		return room_price;
	}

	public void setRoom_price(String room_price) {
		this.room_price = room_price;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public int getHotel_no() {
		return Hotel_no;
	}

	public void setHotel_no(int hotel_no) {
		Hotel_no = hotel_no;
	}

	public int getBooking_no() {
		return booking_no;
	}

	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
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

	public String getHotel_in() {
		return hotel_in;
	}

	public void setHotel_in(String hotel_in) {
		this.hotel_in = hotel_in;
	}

	public String getHotel_out() {
		return hotel_out;
	}

	public void setHotel_out(String hotel_out) {
		this.hotel_out = hotel_out;
	}

	
	
	
	}	