package studentTest;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Employees implements Serializable{

	private int employeeID;     
	private String	firstName;           
	private String lastName;		
	private String email;         
	private String phoneNumber;
	private Date hireDate;
	private String jobID;
	private int salary;
	private double commissionPCT;
	private int managerID;
	private int departmentID;
	
	public Employees(int employeeID, String firstName, String lastName, String email, String phoneNumber, Date hireDate,
			String jobID, int salary, double commissionPCT, int managerID, int departmentID) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.jobID = jobID;
		this.salary = salary;
		this.commissionPCT = commissionPCT;
		this.managerID = managerID;
		this.departmentID = departmentID;
	}


	public Employees(int employeeID, String firstName, int salary) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.salary = salary;
	}


	public int getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Date getHireDate() {
		return hireDate;
	}


	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}


	public String getJobID() {
		return jobID;
	}


	public void setJobID(String jobID) {
		this.jobID = jobID;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public double getCommissionPCT() {
		return commissionPCT;
	}


	public void setCommissionPCT(double commissionPCT) {
		this.commissionPCT = commissionPCT;
	}


	public int getManagerID() {
		return managerID;
	}


	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}


	public int getDepartmentID() {
		return departmentID;
	}


	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}


	@Override
	public int hashCode() {
		return Objects.hash(employeeID);
	}


	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Employees)) {
			return false;
		}	
		return employeeID == ((Employees)obj).employeeID;
	}


	@Override
	public String toString() {
		return "Employees [employeeID=" + employeeID + ", firstName=" + firstName + ", salary=" + salary + "]";
	}
	
	
	
	
}