package dto;

import java.util.Date;

public class Review {
	
	private int pay_no;
	private int review_no;
	private int hotel_no;
	private int booking_no;
	private String user_email;
	private String review_content;
	private int review_score;
	private String review_date;
	private int user_no;
	private String room_type;
	

	public Review() {
		super();
	}



	public Review(int pay_no, int review_no, int hotel_no, int booking_no, String user_email, String review_content,
			int review_score, String review_date, int user_no, String room_type) {
		super();
		this.pay_no = pay_no;
		this.review_no = review_no;
		this.hotel_no = hotel_no;
		this.booking_no = booking_no;
		this.user_email = user_email;
		this.review_content = review_content;
		this.review_score = review_score;
		this.review_date = review_date;
		this.user_no = user_no;
		this.room_type = room_type;
	}





	@Override
	public String toString() {
		return "Review [pay_no=" + pay_no + ", review_no=" + review_no + ", hotel_no=" + hotel_no + ", booking_no="
				+ booking_no + ", user_email=" + user_email + ", review_content=" + review_content + ", review_score="
				+ review_score + ", review_date=" + review_date + ", user_no=" + user_no + ", room_type=" + room_type
				+ "]";
	}



	public int getPay_no() {
		return pay_no;
	}



	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}



	public int getReview_no() {
		return review_no;
	}



	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}



	public int getHotel_no() {
		return hotel_no;
	}



	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
	}



	public int getBooking_no() {
		return booking_no;
	}



	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}



	public String getUser_email() {
		return user_email;
	}



	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}



	public String getReview_content() {
		return review_content;
	}



	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}



	public int getReview_score() {
		return review_score;
	}



	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}



	public String getReview_date() {
		return review_date;
	}



	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}



	public int getUser_no() {
		return user_no;
	}



	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}



	public String getRoom_type() {
		return room_type;
	}



	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	
	

}
