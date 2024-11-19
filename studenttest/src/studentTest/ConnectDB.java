package studentTest;

import java.sql.Connection;
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
		
		con = DBconnection.dbCon();
		
		//3. statement (query : c,u,r,d) 작업해야함
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employees");
			while(rs.next()) {
				int employeeID = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				int salary = rs.getInt("SALARY");
				Employees employees = new Employees(employeeID, firstName, salary);
				employeesList.add(employees);
			}

		} catch (SQLException e) {
			System.out.println("데이터베이스 실행문에서 에러");
		}
		
		//데이터 출력
		employeesListPrint(employeesList);
		
		//6. close
		DBconnection.dbClose(con,stmt,rs);
		
	}//end main

	private static void employeesListPrint(ArrayList<Employees> employeesList) {
		for(Employees e:employeesList) {
			System.out.println(e.toString());
		}
	}

}
