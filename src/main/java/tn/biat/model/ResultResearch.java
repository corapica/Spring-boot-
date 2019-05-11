package tn.biat.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ResultResearch {

	@Id
	String id;
	String idClient ;
	String firstName ;
	String lastName ;
	String Results;
	Boolean flag ;
	String adminManager ;
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResults() {
		return Results;
	}
	public void setResults(String Results) {
		this.Results = Results;
	}
	
	public ResultResearch() {
		super()	;
	}
	
	
	public String getAdminManager() {
		return adminManager;
	}
	public void setAdminManager(String adminManager) {
		this.adminManager = adminManager;
	}
	@Override
	public String toString() {
		return "ResultResearch [id=" + id + ", Results=" + Results + "]";
	}
	
	
}