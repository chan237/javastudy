package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class TraineeVO {
	private int no; 		//pk seq
	private String snum; 	//fk(student) 학생번호
	private String abbre; 	//fk(lesson) 과목약어
	private String section; //전공
	private Date tdate; 	//수강신청일
	public TraineeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TraineeVO(int no, String snum, String abbre, String section, Date tdate) {
		super();
		this.no = no;
		this.snum = snum;
		this.abbre = abbre;
		this.section = section;
		this.tdate = tdate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSnum() {
		return snum;
	}
	public void setSnum(String snum) {
		this.snum = snum;
	}
	public String getAbbre() {
		return abbre;
	}
	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Date getTdate() {
		return tdate;
	}
	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}
	@Override
	public String toString() {
		return "TraineeVO [no=" + no + ", snum=" + snum + ", abbre=" + abbre + ", section=" + section + ", tdate="
				+ tdate + "]";
	}
}
