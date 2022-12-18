package dto;

public class Semi_User {
	
	private int user_no;
	private String user_name;
	private String user_email;
	private String user_phone;
	private String user_pw;
	private String user_pic;
	
	
	
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	@Override
	public String toString() {
		return "semi_User [user_no=" + user_no + ", user_name=" + user_name + ", user_email=" + user_email
				+ ", user_phone=" + user_phone + ", user_pw=" + user_pw + ", user_pic=" + user_pic + "]";
	}
	public Semi_User(int user_no, String user_name, String user_email, String user_phone, String user_pw,
			String user_pic) {
		super();
		this.user_no = user_no;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_pw = user_pw;
		this.user_pic = user_pic;
	}
	public Semi_User() {
		super();
	}
	
	

}
