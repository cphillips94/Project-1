package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "manager_details")
public class ManagerEntity {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "manager_id")
private int managerId;

@Column(name = "manager_first_name")
private String managerFirstName;

@Column(name = "manager_last_name")
private String managerLastName;

@Column(name = "manager_phone_number")
private String managerContact;

@Column(name = "manager_address")
private String managerAddress;

@Column(name = "manager_password")
private String managerPassword;

@Column(name = "manager_image_url")
private String managerImageUrl;

public ManagerEntity(int managerId, String managerFirstName, String managerLastName, String managerContact,
		String managerAddress, String managerPassword, String managerImageUrl) {
	super();
	this.managerId = managerId;
	this.managerFirstName = managerFirstName;
	this.managerLastName = managerLastName;
	this.managerContact = managerContact;
	this.managerAddress = managerAddress;
	this.managerPassword = managerPassword;
	this.managerImageUrl = managerImageUrl;
}

public int getManagerId() {
	return managerId;
}

public void setManagerId(int managerId) {
	this.managerId = managerId;
}

public String getManagerFirstName() {
	return managerFirstName;
}

public void setManagerFirstName(String managerFirstName) {
	this.managerFirstName = managerFirstName;
}

public String getManagerLastName() {
	return managerLastName;
}

public void setManagerLastName(String managerLastName) {
	this.managerLastName = managerLastName;
}

public String getManagerContact() {
	return managerContact;
}

public void setManagerContact(String managerContact) {
	this.managerContact = managerContact;
}

public String getManagerAddress() {
	return managerAddress;
}

public void setManagerAddress(String managerAddress) {
	this.managerAddress = managerAddress;
}

public String getManagerPassword() {
	return managerPassword;
}

public void setManagerPassword(String managerPassword) {
	this.managerPassword = managerPassword;
}

public String getManagerImageUrl() {
	return managerImageUrl;
}

public void setManagerImageUrl(String managerImageUrl) {
	this.managerImageUrl = managerImageUrl;
}

@Override
public String toString() {
	return "ManagerEntity [managerId=" + managerId + ", managerFirstName=" + managerFirstName + ", managerLastName="
			+ managerLastName + ", managerContact=" + managerContact + ", managerAddress=" + managerAddress
			+ ", managerPassword=" + managerPassword + ", managerImageUrl=" + managerImageUrl + "]";
}




}
