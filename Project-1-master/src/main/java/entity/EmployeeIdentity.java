package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="employee_details")
public class EmployeeIdentity{

    @Id
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "employee_first_name")
    private String employeeFirstName;

    @Column(name = "employee_last_name")
    private String employeeLastName;

    @Column(name = "employee_phone_number")
    private String employeePhoneNumber;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_password")
    private String password;

    @Column(name = "employee_image_url")
    private String employeeImageUrl;

    public EmployeeIdentity(){

    }

    public EmployeeIdentity(int employeeId, String employeeFirstName, String employeeLastName,
            String employeePhoneNumber, String employeeAddress, String password, String employeeImageUrl) {
        super();
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeAddress = employeeAddress;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeImageUrl() {
        return employeeImageUrl;
    }

    public void setEmployeeImageUrl(String employeeImageUrl) {
        this.employeeImageUrl = employeeImageUrl;
    }

    @Override
    public String toString() {
        return "EmployeeIdentity [employeeAddress=" + employeeAddress + ", employeeFirstName=" + employeeFirstName
                + ", employeeId=" + employeeId + ", employeeImageUrl=" + employeeImageUrl + ", employeeLastName="
                + employeeLastName + ", employeePhoneNumber=" + employeePhoneNumber + ", password=" + password + "]";
    }

    
    
}