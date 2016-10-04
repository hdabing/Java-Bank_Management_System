package pojo;

public class Dep {

	private int depnum;
	private String depname;

	public int getDepnum() {
		return depnum;
	}

	public void setDepnum(int depid) {
		this.depnum = depid;
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
