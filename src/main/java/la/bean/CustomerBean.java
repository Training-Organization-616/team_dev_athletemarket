package la.bean;

<<<<<<< HEAD
import java.io.Serializable;

public class CustomerBean implements Serializable {
=======
public class CustomerBean {

>>>>>>> 065f8a235f91d7ca40b5a23784f0b7af14154891
	private int id;
	private String name;
	private String address;
	private String tel;
<<<<<<< HEAD
	//６月17直し
	//private String birthDay;
	private String birth_day;
	//６月17直し----
	private String email;
	private String password;
=======
	private String birthDay;
	private String email;
	private String password;
	private String startDay;
>>>>>>> 065f8a235f91d7ca40b5a23784f0b7af14154891
	private int userType;

	public CustomerBean() {

	}

<<<<<<< HEAD
	public CustomerBean(int id, String name, String address, String tel, String birth_day, String email,
			String password, int userType) {
		//	public CustomerBean(int id, String name, String address, String tel, String birthDay, String email, String password,
		//			int userType) {
=======
	public CustomerBean(int id, String name, String address, String tel, String birthDay, String email,
			String password, String startDay, int userType) {
>>>>>>> 065f8a235f91d7ca40b5a23784f0b7af14154891
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
<<<<<<< HEAD
		//６月17直し
		//		this.birthDay = birthDay;
		this.birth_day = birth_day;
		//６月17直し-----

		this.email = email;
		this.password = password;
		this.userType = userType;
	}

=======
		this.birthDay = birthDay;
		this.email = email;
		this.password = password;
		this.startDay = startDay;
		this.userType = userType;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

>>>>>>> 065f8a235f91d7ca40b5a23784f0b7af14154891
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

<<<<<<< HEAD
	//	public String getBirthDay() {
	//		return birthDay;
	//	}
	//
	//	public void setBirthDay(String birthDay) {
	//		this.birthDay = birthDay;
	//	}
	public String getBirth_Day() {
		return birth_day;
	}

	public void setBirth_Day(String birth_day) {
		this.birth_day = birth_day;
=======
	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
>>>>>>> 065f8a235f91d7ca40b5a23784f0b7af14154891
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
