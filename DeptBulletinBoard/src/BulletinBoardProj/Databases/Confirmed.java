package BulletinBoardProj.Databases;
import java.sql.Date;

public class Confirmed extends Database485 implements Event {
	private String id, title, description, location, department;
	private Date date;
	private double fee;
	private int room;
	
	public Confirmed() {
		super();
	}
	
	public Confirmed(String title, String description, String location, String department, Date date, int room, int fee) {
		//super(title, description, location, department, date, room, fee);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
}
