package dto;

import java.util.Date;

public class Board {
	
	private int boardno;
	private String title;
	private int user_no;
	private String content;
	private int hit;
	private Date writeDate;

	public Board() {}


	public Board(int boardno, String title, int user_no, String content, int hit, Date writeDate) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.user_no = user_no;
		this.content = content;
		this.hit = hit;
		this.writeDate = writeDate;
	}


	@Override
	public String toString() {
		return "Board [boardno=" + boardno + ", title=" + title + ", user_no=" + user_no + ", content=" + content
				+ ", hit=" + hit + ", writeDate=" + writeDate + "]";
	}


	public int getBoardno() {
		return boardno;
	}


	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getUser_no() {
		return user_no;
	}


	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}


	public Date getWriteDate() {
		return writeDate;
	}


	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}


	public void setUser_no(String attribute) {

		this.user_no = user_no;
	}
	
}
	
	

	