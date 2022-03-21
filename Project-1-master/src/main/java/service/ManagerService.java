package service;

import java.util.List;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;

public interface ManagerService {

	// LOGIN
	public ManagerPojo managerLogin(int managerId, String managerPassword) throws SystemException;

	// READ FROM PENDING REIMBURSEMENTS TABLE
	public ReimbursementPojo readPendingRequest(int reimbursementId) throws SystemException;

	// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
	public ReimbursementPojo approveOrDeny(ReimbursementPojo reimbursementPojo) throws SystemException;
	
	// READ ALL VALUES FROM PENDING REQUESTS TABLE
	public List<ReimbursementPojo> viewAllPendingRequests() throws SystemException;

	// READ ALL VALUES FROM RESOLVED REQUESTS TABLE
	public List<ReimbursementPojo> viewAllResolvedRequests() throws SystemException;

	// READ ALL PENDING AND RESOLVED REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
	public List<ReimbursementPojo> viewAllRequests(int employeeId) throws SystemException;

	// VIEW ALL EMPLOYEES
	public List<EmployeePojo> viewAllEmployees() throws SystemException;

}