package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class TraineeVO {
	private int no; 		//pk seq
	private String s_num; 	//fk(student) 학생번호
	private String abbre; 	//fk(lesson) 과목약어
	private String section; //전공
	private Date registDate; 	//수강신청일
	//JOIN Student
	private String s_name;
	//JOIN Lesson
	private String l_name;
	
	public TraineeVO() {
		super();
	}
	
	public TraineeVO(int no, String s_num, String abbre, String section, Date registDate, String s_name,
			String l_name) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
		this.registDate = registDate;
		this.s_name = s_name;
		this.l_name = l_name;
	}
	public TraineeVO(int no, String s_num, String abbre, String section, Date registDate) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
		this.registDate = registDate;
	}
	public TraineeVO(String s_num, String abbre, String section) {
		super();
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getS_num() {
		return s_num;
	}
	public void setS_num(String s_num) {
		this.s_num = s_num;
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
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	@Override
	public String toString() {
		return "TraineeVO [no=" + no + ", s_num=" + s_num + ", abbre=" + abbre + ", section=" + section
				+ ", registDate=" + registDate + ", s_name=" + s_name + ", l_name=" + l_name + "]";
	}

	public String toAllString() {
		return "TraineeVO [no=" + no + ", s_num=" + s_num + ", abbre=" + abbre + ", section=" + section
				+ ", registDate=" + registDate + "]";
	}
	
}
