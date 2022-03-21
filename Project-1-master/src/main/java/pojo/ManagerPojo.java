package pojo;

import java.util.Objects;

public class ManagerPojo {
	
	private int managerId;
	private String managerFirstName;
	private String managerLastName;
	private String managerContact;
	private String managerAddress;
	private String managerPassword;
	private String managerImageURL;
	
	public ManagerPojo() {}
	
	public ManagerPojo(int managerId, String managerFirstName, String managerLastName, String managerContact,
			String managerAddress, String managerPassword, String managerImageUrl) {
		this.managerId = managerId;
		this.managerFirstName = managerFirstName;
		this.managerLastName = managerLastName;
		this.managerContact = managerContact;
		this.managerAddress = managerAddress;
		this.managerPassword = managerPassword;
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

	public String getManagerImageURL() {
		return managerImageURL;
	}

	public void setManagerImageURL(String employeeImageURL) {
		this.managerImageURL = employeeImageURL;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(managerFirstName, managerId, managerLastName, managerPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManagerPojo other = (ManagerPojo) obj;
		return Objects.equals(managerFirstName, other.managerFirstName) && managerId == other.managerId
				&& Objects.equals(managerLastName, other.managerLastName)
				&& Objects.equals(managerPassword, other.managerPassword);
	}

	@Override
	public String toString() {
		return "ManagerPojo [managerId=" + managerId + ", managerFirstName=" + managerFirstName + ", managerLastName="
				+ managerLastName + ", managerContact=" + managerContact + ", managerAddress=" + managerAddress
				+ ", managerPassword=" + managerPassword + ", managerImageURL=" + managerImageURL + "]";
	}

	
	
}
