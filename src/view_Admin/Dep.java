package view_Admin;


public class Dep {

	private int depnum;
	private String depname;

	public int getDepnum() {
		return depnum;
	}

	public void setDepnum(int depnum) {
		this.depnum = depnum;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}
	
	
	@Override
	public String toString() {
		return this.depname;
	}
	
	

}