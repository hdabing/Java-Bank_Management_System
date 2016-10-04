package view_Admin;

public class Emp {

	private int empnum;
	private String empname;
	public int getEmpnum() {
		return empnum;
	}
	public void setEmpnum(int empnum) {
		this.empnum = empnum;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return empnum+"";
	}
	

	

}
