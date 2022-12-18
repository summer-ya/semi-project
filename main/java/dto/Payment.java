package dto;

import java.util.Date;

public class Payment {

	private int pay_no;
	private int booking_no;
	private int user_no;
	private int pay_total;
	private String pay_kind;
	private int pay_ok;
	private Date pay_date;

	
	@Override
	public String toString() {
		return "Payment [pay_no=" + pay_no + ", booking_no=" + booking_no + ", user_no=" + user_no + ", pay_total="
				+ pay_total + ", pay_kind=" + pay_kind + ", pay_ok=" + pay_ok + ", pay_date=" + pay_date + "]";
	}


	public int getPay_no() {
		return pay_no;
	}


	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
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


	public int getPay_total() {
		return pay_total;
	}


	public void setPay_total(int pay_total) {
		this.pay_total = pay_total;
	}


	public String getPay_kind() {
		return pay_kind;
	}


	public void setPay_kind(String pay_kind) {
		this.pay_kind = pay_kind;
	}


	public int getPay_ok() {
		return pay_ok;
	}


	public void setPay_ok(int pay_ok) {
		this.pay_ok = pay_ok;
	}


	public Date getPay_date() {
		return pay_date;
	}


	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}


	public Payment(int pay_no, int booking_no, int user_no, int pay_total, String pay_kind, int pay_ok, Date pay_date) {
		super();
		this.pay_no = pay_no;
		this.booking_no = booking_no;
		this.user_no = user_no;
		this.pay_total = pay_total;
		this.pay_kind = pay_kind;
		this.pay_ok = pay_ok;
		this.pay_date = pay_date;
	}


	public Payment() {
		super();
	}
	
	
	
	
}
