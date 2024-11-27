package stud;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case StuMenu.PRINT: {
				stuPrint();
				break;
			}
			case StuMenu.INSERT: {
				stuInsert();
				break;
			}
			case StuMenu.UPDATE: {
				stuUpdate();
				break;
			}
			case StuMenu.DELETE: {
				stuDelete();
				break;
			}
			case StuMenu.SORT: {
				stuSort();
				break;
			}
			case StuMenu.EXIT: {
				exitFlag = true;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
			System.out.println("The end");
		}
	}

	// 정렬
	private static void stuSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Student> stuList = new ArrayList<Student>();

		con = DBconnection.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM STUDENT");

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");

			Student stu = new Student(no, name, kor, eng, mat, total, ave, rank);
			stuList.add(stu);
		}

		// rank 순서대로 정렬
		Collections.sort(stuList, Comparator.comparingInt(Student::getRank));

		System.out.println("정렬된 학생 정보");
		stuListPrint(stuList);
		DBconnection.dbClose(con, stmt, rs);

	}

	// 출력
	private static void stuPrint() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Student> stuList = new ArrayList<Student>();

		con = DBconnection.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM STUDENT");

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");

			Student stu = new Student(no, name, kor, eng, mat, total, ave, rank);
			stuList.add(stu);
		}
		stuListPrint(stuList);
		DBconnection.dbClose(con, stmt, rs);
	}

	// 입력
	private static void stuInsert() throws SQLException {
		// Conection
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBconnection.dbCon();

		// 트랜잭션 시작
		//con.setAutoCommit(false);

		// 3.statement
		System.out.print("학생 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("수학 점수를 입력하세요: ");
		int mat = Integer.parseInt(sc.nextLine());

		pstmt = con.prepareStatement(
				"INSERT INTO STUDENT(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT_NO_SEQ.NEXTVAL, ?, ?, ?, ?)");
		pstmt.setString(1, name);
		pstmt.setInt(2, kor);
		pstmt.setInt(3, eng);
		pstmt.setInt(4, mat);

		int result = pstmt.executeUpdate();
		System.out.println((result != 0) ? "입력성공" : "입력실패");

		cstmt = con.prepareCall("{call STUDENT_RANK_PROC()}");
		int result1 = cstmt.executeUpdate();
		System.out.println((result1 != 0) ? "프로시저성공" : "프로시저실패");


		DBconnection.dbClose(con, pstmt);
	}

	// 수정
	private static void stuUpdate() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBconnection.dbCon();

		System.out.print("수정할 학생의 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());

		System.out.print("새로운 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("새로운 국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 수학 점수를 입력하세요: ");
		int mat = Integer.parseInt(sc.nextLine());

		pstmt = con.prepareStatement("UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ? ");
		pstmt.setString(1, name);
		pstmt.setInt(2, kor);
		pstmt.setInt(3, eng);
		pstmt.setInt(4, mat);
		pstmt.setInt(5, no);

		int result = pstmt.executeUpdate();

		System.out.println((result != 0) ? "수정성공" : "수정실패");

		DBconnection.dbClose(con, pstmt);
	}

	// 삭제
	private static void stuDelete() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBconnection.dbCon();

		System.out.print("삭제할 학생 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		pstmt = con.prepareStatement("DELETE FROM STUDENT WHERE NO = ?");
		pstmt.setInt(1, no);
		int result = pstmt.executeUpdate();

		System.out.println((result != 0) ? "삭제성공" : "삭제실패");

		DBconnection.dbClose(con, pstmt);
	}

	// 메뉴
	private static void printMenu() {
		System.out.println("+++++++++++++++++++++");
		System.out.println("1.학생정보출력");
		System.out.println("2.학생정보입력");
		System.out.println("3.학생정보수정");
		System.out.println("4.학생정보삭제");
		System.out.println("5.총점순서정렬");
		System.out.println("6.종료");
		System.out.println("+++++++++++++++++++++");
		System.out.print(">>");
	}

	private static void stuListPrint(ArrayList<Student> stuList) {
		for (Student student : stuList) {
			System.out.println(student.toString());
		}
	}

}