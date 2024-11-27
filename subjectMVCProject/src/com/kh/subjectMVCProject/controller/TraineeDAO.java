package com.kh.subjectMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.TraineeVO;

public class TraineeDAO {
	public final String TRAINEE_SELECT = "select * from TRAINEE"; 
	public final String TRAINEE_ALL_SELECT = "select t.no, t.section, t.registdate, s.num, s.name as sname, l.abbre, l.name as lname "
			+ "from trainee T inner join student S on t.s_num = s.num "
			+ "inner join lesson L on t.abbre = l.abbre order by T.no;"; 
	public final String TRAINEE_SELECT_SORT = "select * from TRAINEE ORDER BY S_NUM"; 
	public final String TRAINEE_DELETE = "delete from TRAINEE where no = ?";
	public final String TRAINEE_UPDATE = "UPDATE TRAINEE SET S_NUM = ?, ABBRE = ?, SECTION = ? WHERE no = ?";
	public final String TRAINEE_INSERT = "INSERT INTO TRAINEE VALUES(TRAINEE_SEQ.NEXTVAL, ?, ?, ?,SYSDATE) ";
	
	//전체출력
	public ArrayList<TraineeVO> traineeSelect(TraineeVO tvo){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("NO");
				String s_num = rs.getString("S_NUM");
				String abbre = rs.getString("ABBRE");
				String section = rs.getString("SECTION");
				Date registDate = rs.getDate("RESISTDATE");
				TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, registDate);
				traineeList.add(traineeVO);
				
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		
		return traineeList;
	}

	//삽입
	public boolean traineeInsert(TraineeVO tvo) {
		Connection con = null; 				//오라클접속관문
		PreparedStatement pstmt = null; 	//오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false; 
		
		try {
			con = DBUtility.dbCon(); 
			pstmt = con.prepareStatement(TRAINEE_INSERT);
			pstmt.setString(1, tvo.getS_num());
			pstmt.setString(2, tvo.getAbbre()); 
			pstmt.setString(3, tvo.getSection()); 

			int count = pstmt.executeUpdate();
			successFlag = (count != 0)?(true):(false); 
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag ;
	}

	//수정
	public boolean traineeUpdate(TraineeVO tvo) {
		Connection con = null; 				//오라클접속관문
		PreparedStatement pstmt = null; 	//오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false; 
		try {
			con = DBUtility.dbCon(); 
			pstmt = con.prepareStatement(TRAINEE_UPDATE);
			pstmt.setString(1, tvo.getS_num());
			pstmt.setString(2, tvo.getAbbre()); 
			pstmt.setString(3, tvo.getSection()); 
			pstmt.setInt(4, tvo.getNo());
			
			int count = pstmt.executeUpdate();
			successFlag = (count != 0)?(true):(false); 
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag ;
	}

	//삭제
	public boolean traineeDelete(TraineeVO tvo) {
        Connection con = null; // 오라클에 DB접속
        PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
        boolean successFlag = false;
        

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(TRAINEE_DELETE);
            pstmt.setInt(1, tvo.getNo());
            int count = pstmt.executeUpdate();
            successFlag = (count != 0)? true : false;        
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;

    }

	//정렬
	public ArrayList<TraineeVO> traineeSelectSort(TraineeVO tvo) {
		Connection con = null; 				//오라클접속관문
		PreparedStatement pstmt = null; 	//오라클에서 작업할 쿼리문 사용할게 하는 명령문
		ResultSet rs = null;				//오라클에서 결과물을 받는객체
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();	//결과값을 다른객체전달하기 위해서 사용하는객체
		
		try {
			con = DBUtility.dbCon(); 
			pstmt = con.prepareStatement(TRAINEE_SELECT_SORT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("NO");
				String s_num = rs.getString("S_NUM");
				String abbre = rs.getString("ABBRE");
				String section = rs.getString("SECTION");
				Date registDate = rs.getDate("RESISTDATE");
				TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, registDate);
				traineeList.add(traineeVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		
		return traineeList;
	}
	
	//traineeAllSelect(train join Lesson)
	public ArrayList<TraineeVO> traineeAllSelect(TraineeVO tvo){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_ALL_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("NO");
				String section = rs.getString("SECTION");
				Date registDate = rs.getDate("RESISTDATE");
				String s_num = rs.getString("NUM");
				String s_name = rs.getString("SNAME");
				String abbre = rs.getString("ABBRE");
				String l_name = rs.getString("LNAME");
				TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, registDate, s_name, l_name);
				traineeList.add(traineeVO);	
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		
		return traineeList;
	}
}
