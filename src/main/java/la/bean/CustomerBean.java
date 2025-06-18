package la.bean;

import java.io.Serializable;

public class CustomerBean implements Serializable {

	private int id;
	private String name;
	private String address;
	private String tel;
	private String email;
	private String password;
	private String birthDay;
	private String startDay;
	private int userType;

	public CustomerBean() {

	}

	public CustomerBean(int id, String name, String address, String tel, String birthDay, String email,
			String password, String startDay, int userType) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.birthDay = birthDay;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

}
