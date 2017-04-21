package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
	private String id;
	private String name;
	private Date birthday;
	private String description;
	private int avagscore;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAvagscore() {
		return avagscore;
	}
	public void setAvagscore(int avagscore) {
		this.avagscore = avagscore;
	}
	public String getBirthdayFormat(SimpleDateFormat dateFormat){
		return dateFormat.format(this.birthday);
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday="
				+ birthday + ", description=" + description + ", avagscore="
				+ avagscore + "]";
	}
	
}
