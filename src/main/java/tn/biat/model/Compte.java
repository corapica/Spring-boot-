package tn.biat.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Compte {

	@Id
	String id ;
	String projectName ;
	String key ;
	String adminManager ;
	int RemainingNumber ;
	
	
	
	public Compte( String projectName, String key, String adminManager, int remainingNumber) {
		super();
		this.projectName = projectName;
		this.key = key;
		this.adminManager = adminManager;
		this.RemainingNumber = remainingNumber;
	}
	@Override
	public String toString() {
		return "Compte [id=" + id + ", ProjectName=" + projectName + ", key=" + key + ", idUser=" + adminManager
				+ ", RemainingNumber=" + RemainingNumber + "]";
	}
	public String getprojectName() {
		return projectName;
	}
	public void setprojectName(String projectName) {
		this.projectName = projectName;
	}
	public String getAdminManager() {
		return adminManager;
	}
	public void setIdUser(String adminManager) {
		this.adminManager = adminManager;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public int getRemainingNumber() {
		return RemainingNumber;
	}
	public void setRemainingNumber(int remainingNumber) {
		RemainingNumber = remainingNumber;
	}
	public Compte() {
		super();
	}
	

}