package stud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			case PRINT:
				studPrint();
				break;
			case INSERT:
				studInsert();
				break;
			case UPDATE:
				studUpdate();
				break;
			case DELETE:
				studDelete();
				break;
			case END:
				exitFlag = true;
				break;
			default:
				break;
			}
		}

		System.out.println("THE END");
	}// end main

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
		Statement stmt = null;

		con = DBconnection.dbCon();
		stmt = con.createStatement();
		System.out.print("수정할 번호 :");
		int id = Integer.parseInt(sc.nextLine());
		System.out.print("학생의 이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.print("학생의 주소를 입력하세요 : ");
		String addr = sc.nextLine();
		System.out.print("학생의 출생년도를 입력하세요 : ");
		String year = sc.nextLine();
		Stud stud = new Stud(name,addr,year);
		
		int result = stmt.executeUpdate(
				"UPDATE STUD SET NAME = '"+stud.getName()+"', ADDR = '"+stud.getAddr()+"', YEAR = '"+stud.getYear()+"' WHERE ID= "+id);

		System.out.println((result != 0) ? "수정성공" : "수정실패");
		DBconnection.dbClose(con, stmt);
	}

	// 삽입
	private static void studInsert() throws SQLException {
		Connection con = null;
		Statement stmt = null;

		con = DBconnection.dbCon();
		stmt = con.createStatement();
		
		System.out.print("학생의 이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.print("학생의 주소를 입력하세요 : ");
		String addr = sc.nextLine();
		System.out.print("학생의 출생년도를 입력하세요 : ");
		String year = sc.nextLine();
		Stud stud = new Stud(name,addr,year);
		
		int result = stmt.executeUpdate(
				"INSERT INTO STUD VALUES(STUD_ID_SEQ.NEXTVAL, '"+stud.getName()+"', '"+stud.getAddr()+"', '"+stud.getYear()+"')");

		System.out.println((result != 0) ? "삽입성공" : "삽입실패");
		DBconnection.dbClose(con, stmt);
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
		System.out.println("MENU(1. 출력, 2. 입력, 3.수정, 4.삭제, 5.종료)");
		System.out.print(">>");
	}

	private static void studListPrint(ArrayList<Stud> studList) {
		for (Stud stud : studList) {
			System.out.println(stud.toString());
		}

	}

}