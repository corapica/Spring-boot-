package tn.biat.model;

public class Bot {
	String id ;
	String status ;
	String adminManager ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdminManager() {
		return adminManager;
	}
	public void setAdminManager(String adminManager) {
		this.adminManager = adminManager;
	}
	public Bot(String status, String adminManager) {
		super();
		this.status = status;
		this.adminManager = adminManager;
	}
	public Bot() {
		super();
	}
	
	
}
