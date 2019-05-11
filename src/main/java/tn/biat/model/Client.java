package tn.biat.model;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Client {

	@Id
	String id;
	String firstName;
	String lastName;
	String email;
	boolean flag;
	String adminManager;
	String label;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getAdminManager() {
		return adminManager;
	}

	public void setAdminManager(String adminManager) {
		this.adminManager = adminManager;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Client(String firstName, String lastName, String email, String label, String adminManager) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.label = label;
		this.adminManager = adminManager;
	}

	public Client() {

	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
