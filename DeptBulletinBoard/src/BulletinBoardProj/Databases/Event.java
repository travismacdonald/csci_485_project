package BulletinBoardProj.Databases;

import java.sql.Date;

public interface Event {
	
	public String getId();

	public void setId(String id);
	
	public String getTitle();

	public void setTitle(String title);
	
	public String getDescription();

	public void setDescription(String description);

	public String getLocation();

	public void setLocation(String location);

	public int getRoom();

	public void setRoom(int room);

	public String getDepartment();

	public void setDepartment(String department);

	public Date getDate();

	public void setDate(Date date);

	public double getFee();

	public void setFee(double fee);


}
