package stud;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

public class StudMain {
	public static final int PRINT = 1, INSERT = 2, UPDATE = 3, DELETE = 4, END = 5;
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case Menu.PRINT:
				studPrint();
				break;
			case Menu.INSERT:
				studInsert();
				break;
			case Menu.UPDATE:
				studUpdate();
				break;
			case Menu.DELETE:
				studDelete();
				break;
			case Menu.YEAR_UP_PROC:
				studUpdateProcedure();
				break;
			case Menu.YEAR_SERCH_FUNC:
				studSerchFunction();
				break;
			case Menu.END:
				exitFlag = true;
				break;
			default:
				break;
			}
		}

		System.out.println("THE END");
	}// end main
	
	private static void studSerchFunction() throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;

		con = DBconnection.dbCon();
		
		System.out.print("조회 할 ID를 입력하세요. : ");
		int id = Integer.parseInt(sc.nextLine());

		cstmt = con.prepareCall("{? = call STUS_FUNCTION(?)}");
		cstmt.setInt(2, id);
		cstmt.registerOutParameter(1, Types.VARCHAR);
		
		int result = cstmt.executeUpdate();
		System.out.println(cstmt.getString(1));

		System.out.println((result != 0) ? "function성공" : "function실패");
		DBconnection.dbClose(con, cstmt);
	}

	//year 증가/감소
	private static void studUpdateProcedure() throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;

		con = DBconnection.dbCon();
		
		System.out.println("인상할 ID를 입력하세요.");
		System.out.print(">>");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("인상할 YEAR을 입력하세요.");
		System.out.print(">>");
		int year = Integer.parseInt(sc.nextLine());
		
		cstmt = con.prepareCall("{call STUD_PROCEDURE(?,?,?)}");
		cstmt.setInt(1, id);
		cstmt.setInt(2, year);
		cstmt.registerOutParameter(3, Types.VARCHAR);
		
		int result = cstmt.executeUpdate();
		System.out.println(cstmt.getString(3));

		System.out.println((result != 0) ? "프로시저성공" : "프로시저실패");
		DBconnection.dbClose(con, cstmt);
	}

	// 삭제
	private static void studDelete() throws SQLException {
		Connection con = null;
		Statement stmt = null;

		con = DBconnection.dbCon();
		System.out.print("삭제할 번호 :");
		int id = Integer.parseInt(sc.nextLine());
		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE from STUD where ID = " + id);

		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		DBconnection.dbClose(con, stmt);
	}

	// 수정
	private static void studUpdate() throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;

		con = DBconnection.dbCon();
		
		System.out.print("수정할 번호 :");
		int id = Integer.parseInt(sc.nextLine());
		System.out.print("학생의 이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.print("학생의 주소를 입력하세요 : ");
		String addr = sc.nextLine();
		System.out.print("학생의 출생년도를 입력하세요 : ");
		String year = sc.nextLine();
		
		cstmt = con.prepareCall("{call STUD_UPDATE(?,?,?,?)}");
		cstmt.setInt(1, id);
		cstmt.setString(2, name);
		cstmt.setString(3, addr);
		cstmt.setString(4, year);
		int result = cstmt.executeUpdate();

		System.out.println((result != 0) ? "수정성공" : "수정실패");
		DBconnection.dbClose(con, cstmt);
	}

	// 삽입
	private static void studInsert() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBconnection.dbCon();

		System.out.print("학생의 이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.print("학생의 주소를 입력하세요 : ");
		String addr = sc.nextLine();
		System.out.print("학생의 출생년도를 입력하세요 : ");
		String year = sc.nextLine();
		Stud stud = new Stud(name, addr, year);
		pstmt = con.prepareStatement("UPDATE STUD SET NAME = ?, ADDR = ?, YEAR = ? WHERE ID=?");
		pstmt.setString(1, stud.getName());
		pstmt.setString(2, stud.getAddr());
		pstmt.setString(3, stud.getYear());
		pstmt.setInt(4, stud.getId());
		
		int result = pstmt.executeUpdate();

		System.out.println((result != 0) ? "삽입성공" : "삽입실패");
		DBconnection.dbClose(con, pstmt);
	}

	// 출력문
	public static void studPrint() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Stud> studList = new ArrayList<Stud>();

		con = DBconnection.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM STUD");
		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("NAME");
			String addr = rs.getString("ADDR");
			String year = rs.getString("YEAR");
			Stud stud = new Stud(id, name, addr, year);
			studList.add(stud);
		}
		studListPrint(studList);
		DBconnection.dbClose(con, stmt, rs);

	}

	private static void printMenu() {
		System.out.println("MENU");
		System.out.println("1. 출력, 2. 입력, 3.수정, 4.삭제, 5.year증가/감소, 6.조회, 7.종료");
		System.out.print(">>");
	}

	private static void studListPrint(ArrayList<Stud> studList) {
		for (Stud stud : studList) {
			System.out.println(stud.toString());
		}

	}
}