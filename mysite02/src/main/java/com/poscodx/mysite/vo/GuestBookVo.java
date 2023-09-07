package com.poscodx.mysite.vo;

public class GuestBookVo {
	private int no;
	private String name;
	private String password;
	private String contens;
	private String regDate;

	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password=" + password + ", contens=" + contens
				+ ", regDate=" + regDate + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContens() {
		return contens;
	}

	public void setContens(String contens) {
		this.contens = contens;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
