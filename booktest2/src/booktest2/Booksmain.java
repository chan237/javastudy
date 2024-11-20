package booktest2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Booksmain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		// 사용자로부터 출력,입력,수정,삭제를 요청받는다.
		boolean exitFlag = false;
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case BookMenu.PRINT:
				booksPrint();
				break;
			case BookMenu.INSERT:
				booksInsert();
				break;
			case BookMenu.UPDATE:
				booksUpdate();
				break;
			case BookMenu.DELETE:
				booksDelete();
				break;
			case BookMenu.END:
				exitFlag = true;
				break;
			default:
				break;
			}
		}

		System.out.println("THE END");
	}// end main

	// 삭제
	private static void booksDelete() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1.load
		con = DBconnection.dbCon();
		// 2.statement
		System.out.print("삭제할 번호 :");
		int no = Integer.parseInt(sc.nextLine());
		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE from books where ID = " + no);

		// 내용입력 체크하기
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		// 출력하기
		// 객체반납
		DBconnection.dbClose(con, stmt);
	}

	// 수정
	private static void booksUpdate() throws SQLException {
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1.load
		con = DBconnection.dbCon();
		// 2.statement
		//수정할 데이터 입력
		Books books = new Books(3,"kkk","javajava","2024",50000);
		pstmt = con.prepareStatement("update books set title = ?, publisher = ?, year = ?, price = ? where id = ?");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		pstmt.setInt(5, books.getId());
		int result = pstmt.executeUpdate();

		// 내용입력 체크하기
		System.out.println((result != 0) ? "입력성공" : "입력실패");
		// 출력하기
		// 객체반납
		DBconnection.dbClose(con, pstmt);
	}

	// 삽입
	private static void booksInsert() throws SQLException {
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1.load
		con = DBconnection.dbCon();
		Books books = new Books(0, "Head First Java","kdj","2008",23000);
		// 2.statement
		pstmt = con.prepareStatement("INSERT INTO books VALUES (book_id_seq.nextval, ?, ?, ?, ?)");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		int result = pstmt.executeUpdate();
		// 내용입력 체크하기
		System.out.println((result != 0) ? "입력성공" : "입력실패");
		// 출력하기
		// 객체반납
		DBconnection.dbClose(con, pstmt);
	}

	// 출력문
	public static void booksPrint() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Books> booksList = new ArrayList<Books>();

		// 1.load
		con = DBconnection.dbCon();
		// 2.statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from books");
		// 테이블 가져오기
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String publisher = rs.getString("PUBLISHER");
			String year = rs.getString("YEAR");
			int price = rs.getInt("PRICE");
			Books books = new Books(id, title, publisher, year, price);
			booksList.add(books);
		}
		// 출력하기
		booksListPrint(booksList);
		// 객체반납
		DBconnection.dbClose(con, stmt, rs);

	}

	private static void printMenu() {
		System.out.println("BOOKS MENU(1. 출력, 2. 입력, 3.수정, 4.삭제, 5.종료)");
		System.out.println(">>");
	}

	private static void booksListPrint(ArrayList<Books> booksList) {
		for (Books books : booksList) {
			System.out.println(books.toString());
		}

	}

}
