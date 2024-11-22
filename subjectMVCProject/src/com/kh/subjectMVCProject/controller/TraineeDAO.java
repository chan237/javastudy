package com.kh.subjectMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.TraineeVO;

public class TraineeDAO {
	public static final String TRAINEE_SELECT = "SELECT * FROM STUDENT";
	public static final String TRAINEE_INSERT = "INSERT INTO STUDENT1(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT1_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public static final String TRAINEE_CALLRANKPROC = "{call STUDENT1_RANK_PROC()}";
	public static final String TRAINEE_UPDATE = "UPDATE STUDENT1 SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ? ";
	public static final String TRAINEE_DELETE = "DELETE FROM STUDENT1 WHERE NO = ?";
	public static final String TRAINEE_SORT = "SELECT * FROM STUDENT1 ORDER BY RANK";
	
	//전체출력
	public static ArrayList<TraineeVO> studentSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> studentList = new ArrayList<TraineeVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(TRAINEE_SELECT);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");

			TraineeVO stu = new TraineeVO();
			studentList.add(stu);
		}
		DBUtility.dbClose(con, stmt, rs);
		return studentList;
	}
	
	//학생 차트 삽입
	public static boolean studentInsert(TraineeVO svo) throws SQLException {
		// Conection
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(TRAINEE_INSERT);

		int result1 = pstmt.executeUpdate();
		System.out.println((result1 != 0) ? "입력성공" : "입력실패");

		cstmt = con.prepareCall(TRAINEE_CALLRANKPROC);
		int result2 = cstmt.executeUpdate();
		System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");


		DBUtility.dbClose(con, pstmt, cstmt);
		successFlag = (result1 != 0 && result2 != 0)? true : false;
		
		return successFlag;
	}
	
	//학생 차트 수정
	public static boolean studentUpdate(TraineeVO svo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(TRAINEE_UPDATE);
		pstmt.setInt(5, svo.getNo());

		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(TRAINEE_CALLRANKPROC);
		int result2 = cstmt.executeUpdate();
		
		successFlag = (result1 != 0 && result2 != 0)? true : false;
		DBUtility.dbClose(con, pstmt, cstmt);
		
		return successFlag;
	}
	
	//학생 차트 삭제
	public static boolean studentDelete(TraineeVO svo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(TRAINEE_DELETE);
		pstmt.setInt(1, svo.getNo());
		int result = pstmt.executeUpdate();

		successFlag = ((result != 0) ? true : false);
		DBUtility.dbClose(con, pstmt);
		
		return successFlag;
	}
	
	//학생 차트 정렬
	public static ArrayList<TraineeVO> studentSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> studentList = new ArrayList<TraineeVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(TRAINEE_SORT);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");

			TraineeVO stu = new TraineeVO();
			studentList.add(stu);
		}
		
		DBUtility.dbClose(con, stmt, rs);
		return studentList;
	}
}
