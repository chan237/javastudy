package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.StudentVO;
import com.kh.subjectMVCProject.model.TraineeVO;

public class TraineeRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	
	// 전체 학생 리스트를 출력하는 기능
	public void selectManager() {
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		traineeList = tdao.traineeSelect(new TraineeVO());
		if(traineeList.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다");
		}
		printTraineeList(traineeList);
	}
	
	// 전체 학생 리스트를 출력하는 기능(join포함)
	public void selectAllManager() {
		
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		studentList = StudentDAO.studentSelect();
		if(studentList == null) {
			System.out.println("데이터가 존재하지 않습니다");
		}
		
		// 원래는 뷰에 넘겨줘야하나 화면이 하나라서 여기서 찍음
		printTraineeAllList(null);
	}

	// 학생 입력하기
	public void insertManager() {
		TraineeDAO tdao = new TraineeDAO();
		StudentRegisterManager srm = new StudentRegisterManager();
		srm.selectNameSearchManager();
		//검색된 이름으로 학번 이름 이메일 출력 통해서 학번입력 처리
		System.out.print("학생번호 등록 >>");
		String s_num = sc.nextLine();
		
		//lesson에 과목약어, 과목명을 보여줘야함
		LessonRegisterManager lrm = new LessonRegisterManager();
		lrm.selectSortManager();
		//검색된 약어로 요약 입력
		System.out.print("과목요약 입력>>");
		String abbre = (sc.nextLine()).trim();
		
		//전공 부전공 교약 입력
		System.out.print("전공, 부전공, 교양 입력>>");
		String section = (sc.nextLine()).trim();
	
		
		TraineeVO traineeVO = new TraineeVO(0, s_num, abbre, section, null);
		boolean successFlag = tdao.traineeInsert(traineeVO);
		
		if(successFlag ==true) {
			System.out.println("입력처리 성공");
		}else {
			System.out.println("입력처리 실패");
		}
	}

	// 학생 정보 수정
	public void updateManager() {
		TraineeDAO tdao = new TraineeDAO();
		//전체 내용 보여줌
		selectManager();
		System.out.print("수정할 번호를 입력하세요>> ");
		int no = Integer.parseInt(sc.nextLine());
		
		System.out.print("과목요약 입력>>");
		String abbre = (sc.nextLine()).trim();
		
		//전공 부전공 교약 입력
		System.out.print("전공, 부전공, 교양 입력>>");
		String section = (sc.nextLine()).trim();

		TraineeVO traineeVO = new TraineeVO(no, section, abbre, section, null);
		boolean successFlag = tdao.traineeInsert(traineeVO);
		
		if(successFlag ==true) {
			System.out.println("입력처리 성공");
		}else {
			System.out.println("입력처리 실패");
		}
	}
	
	// 학생 정보 삭제
	public void deleteManager() {
		TraineeVO tvo = new TraineeVO();
		TraineeDAO tdao = new TraineeDAO();
		System.out.print("삭제할 학생 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		tvo.setNo(no);
		
		boolean successFlag = tdao.traineeDelete(tvo);
		
		if(successFlag == true) {
			System.out.println("삭제처리 성공");
		}else {
			System.out.println("삭제처리 실패");
		}

	}
	
	// 학생 정보 정렬
	public void traineeSortManager() throws SQLException {
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineeList = null;
		traineeList = tdao.traineeSelectSort(new TraineeVO());
		if(traineeList.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다");
		}
		printTraineeList(traineeList);
	}
	
	// 전체 출력 수행
	private void printTraineeList(ArrayList<TraineeVO> traineeList) {
		System.out.println("===================================");
		for (TraineeVO tvo : traineeList) {
			System.out.println(tvo.toString());
		}
		System.out.println("===================================");
	}
	
	// 전체 출력(join포함) 수행
	private void printTraineeAllList(ArrayList<TraineeVO> traineeList) {
		System.out.println("===================================");
		for (TraineeVO tvo : traineeList) {
			System.out.println(tvo.toString());
		}
		System.out.println("===================================");
	}

}
