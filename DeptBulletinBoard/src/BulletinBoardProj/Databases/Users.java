package BulletinBoardProj.Databases;

public class Users extends Database485 {
	private String name, pass;
	private boolean admin;
	
	public Users() {
		super();
	}
	
	Users(String name, String pass, boolean admin) {
		//super(name, pass, admin);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
