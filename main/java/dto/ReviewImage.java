package dto;

public class ReviewImage {
	
	private int reviewimage_no;
	private int review_no;
	private String originname;
	private String storedname;
	public int getReviewimage_no() {
		return reviewimage_no;
	}
	public void setReviewimage_no(int reviewimage_no) {
		this.reviewimage_no = reviewimage_no;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public String getOriginname() {
		return originname;
	}
	public void setOriginname(String originname) {
		this.originname = originname;
	}
	public String getStoredname() {
		return storedname;
	}
	public void setStoredname(String storedname) {
		this.storedname = storedname;
	}
	@Override
	public String toString() {
		return "ReviewImage [reviewimage_no=" + reviewimage_no + ", review_no=" + review_no + ", originname="
				+ originname + ", storedname=" + storedname + "]";
	}
	public ReviewImage(int reviewimage_no, int review_no, String originname, String storedname) {
		super();
		this.reviewimage_no = reviewimage_no;
		this.review_no = review_no;
		this.originname = originname;
		this.storedname = storedname;
	}
	public ReviewImage() {
		super();
	}
	
	
	
	
	

}
