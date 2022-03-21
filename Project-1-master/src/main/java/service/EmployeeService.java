package service;

import java.util.List;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;

public interface EmployeeService {

	// EMPLOYEE OPERATIONS
	// *****************************************************

	// LOGIN
	public EmployeePojo employeeLogin(int employeeId, String employeePassword) throws SystemException;
	
	// SUBMIT A REIMBURSEMENT REQUEST
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws SystemException;

	// VIEW PENDING REIMBUSEMENT REQUEST FOR LOGGED IN EMPLOYEE
	List<ReimbursementPojo> viewPendingRequests(int employeeId) throws SystemException;

	// VIEW RESOLVED REIMBURSEMENT REQUESTS FOR LOGGED IN EMPLOYEE
	List<ReimbursementPojo> viewResolvedRequests(int employeeId) throws SystemException;

	// READ FROM EMPLOYEE DETAILS TABLE
	public EmployeePojo fetchEmployee(int employeeId) throws SystemException;

	// UPDATE EMPLOYEE DETAILS TABLE
	public EmployeePojo updateEmployee(EmployeePojo employeePojo) throws SystemException;

	

	// LOGOUT
}
