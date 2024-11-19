package studentTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {

	public static void main(String[] args) {
		//객체참조변수 선언
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Employees> employeesList = new ArrayList<Employees>();
		
		//1. jdbc driver load
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 드라이버 적재성공");
		} catch (ClassNotFoundException e) {
			System.out.println("1. 드라이버 적재실패 "+e.toString());
		}
		
		//2. connection
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "hr", "hr");
			System.out.println("2. oracle 접속성공");
		} catch (SQLException e) {
			System.out.println("2. oracle 접속실패"+e.toString());
		}
		
		//3. statement (query : c,u,r,d) 작업해야함
		
		try {
			stmt = con.createStatement();
			System.out.println("3. Statement 객체성공");
		} catch (SQLException e) {
			System.out.println("3. Statement 객체실패");
		}
		
		
		//4. result set 작업해야함
		try {
			rs = stmt.executeQuery("select * from employees");
			System.out.println("4. result set 객체성공");
		} catch (SQLException e) {
			System.out.println("4. result set 객체실패");
		}
		
		//5. 데이터 저장 진행
		try {
			while(rs.next()) {
				int employeeID = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				int salary = rs.getInt("SALARY");
				Employees employees = new Employees(employeeID, firstName, salary);
				employeesList.add(employees);
			}
		} catch (SQLException e) {
			System.out.println("5. 데이터 저장 실패");
		}
		
		//데이터 출력
		employeesListPrint(employeesList);
		
		//6. close
		if(con!=null) {
			try {
				con.close();
				System.out.println("6. con close 성공");
			} catch (SQLException e) {
				System.out.println("6. con close 실패"+e.toString());
			}
		}
		if(stmt!=null) {
			try {
				stmt.close();
				System.out.println("6. stmt close 성공");
			} catch (SQLException e) {
				System.out.println("6. stmt close 실패"+e.toString());
			}
		}
		if(rs!=null) {
			try {
				rs.close();
				System.out.println("6. rs close 성공");
			} catch (SQLException e) {
				System.out.println("6. rs close 실패"+e.toString());
			}
		}
		
	}//end main

	private static void employeesListPrint(ArrayList<Employees> employeesList) {
		for(Employees e:employeesList) {
			System.out.println(e.toString());
		}
	}

}
