package EVote;

import java.time.LocalDate;

public class GetUnVerified{
	
	private String Phone;
	private String Name;
	private String dob;
	
	private String address;
	private String citizenshipno;
	private String photo;
	private String employee_id;
	private String citizenshipfront;
	private String citizenshipback;
	private int id;
	private String email;
	
	 public GetUnVerified(int id,String email,String phone,String Name,String dob,
			String address,String citizenshipno,String photo,String employee_id,
			String citizenshipfront,String citizenshipback) {
		 
		 this.id = id;
		 this.email=email;
		 this.Phone = phone;
		 this.Name = Name;
		 this.dob = dob;
		 this.address = address;
		 this.citizenshipno= citizenshipno;
		 this.photo= photo;
		 this.employee_id= employee_id;
		 this.citizenshipfront= citizenshipfront;
		 this.citizenshipback= citizenshipback;	 	 
	}
	 
	 
	 public int getId() {
		 return this.id;
	 }
	 public String getEmail() {
		 return this.email;
	 }
	 
	 public String getPhone() {
		 return this.Phone;
	 }
	 public String getName() {
		 return this.Name;
	 }
	 public String getDob() {
		 return this.dob;
	 }
	 public String getAddress() {
		 return this.address;
	 }
	 public String getCitizenshipno() {
		 return this.citizenshipno;
	 }
	 public String getPhoto() {
		 return this.photo;
	 }
	 public String getEmployeeid() {
		 return this.employee_id;
	 }
	 public String getCitizenshipfront() {
		 return this.citizenshipfront;
	 }
	 public String getCitizenshipback() {
		 return this.citizenshipback;
	 }
	
	
}