package pojo;

import java.util.Objects;

public class EmployeePojo {
	
	private int employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhoneNumber;
	private String employeeAddress;
	private String employeePassword;
	private String employeeImageUrl;
	
	
	public EmployeePojo() {

	}

	
	public EmployeePojo(int employeeId, String employeeFirstName, String employeeLastName, String employeePhoneNumber,
			String employeeAddress, String employeePassword, String employeeImageUrl) {
		super();
		this.employeeId = employeeId;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeePhoneNumber = employeePhoneNumber;
		this.employeeAddress = employeeAddress;
		this.employeePassword = employeePassword;
		this.employeeImageUrl = employeeImageUrl;
	}




	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(String employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public String getEmployeeImageUrl() {
		return employeeImageUrl;
	}

	public void setEmployeeImageUrl(String employeeImageUrl) {
		this.employeeImageUrl = employeeImageUrl;
	}

	@Override
	public String toString() {
		return "EmployeePojo [employeeId=" + employeeId + ", employeeFirstName=" + employeeFirstName
				+ ", employeeLastName=" + employeeLastName + ", employeePhoneNumber=" + employeePhoneNumber
				+ ", employeeAddress=" + employeeAddress + ", employeePassword=" + employeePassword
				+ ", employeeImageUrl=" + employeeImageUrl + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(employeeFirstName, employeeId, employeeLastName, employeePassword);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePojo other = (EmployeePojo) obj;
		return Objects.equals(employeeFirstName, other.employeeFirstName) && employeeId == other.employeeId
				&& Objects.equals(employeeLastName, other.employeeLastName)
				&& Objects.equals(employeePassword, other.employeePassword);
	}

	
	
}
