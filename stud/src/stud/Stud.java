package stud;

public class Stud {
	private int id; 
	private String name;
	private String addr;  
	private String year;
	public Stud(int id, String name, String addr, String year) {
		super();
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.year = year;
	}
	public Stud(String name, String addr, String year) {
		super();
		this.name = name;
		this.addr = addr;
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "Stud [id=" + id + ", name=" + name + ", addr=" + addr + ", year=" + year + "]";
	}
	
	
}
