package dao;

import java.util.List;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;

public interface ManagerDao {
	
		// READ FROM MANAGER DETAILS TABLE
		public ManagerPojo fetchManager(int managerId) throws SystemException;
		
		// READ FROM PENDING REIMBURSEMENTS TABLE
		ReimbursementPojo readPendingRequest(int reimbursementId) throws SystemException;
		
		// ADD TO RESOLVED REIMBURSEMENTS TABLE
		ReimbursementPojo addResolvedRequest(ReimbursementPojo reimbursementPojo) throws SystemException;
		
		// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
		ReimbursementPojo approveOrDeny(ReimbursementPojo reimbursementPojo) throws SystemException;
		
		// READ ALL VALUES FROM PENDING REQUESTS TABLE
		List<ReimbursementPojo> viewAllPendingRequests() throws SystemException;
		
		// READ ALL VALUES FROM RESOLVED REQUESTS TABLE
		List<ReimbursementPojo> viewAllResolvedRequests() throws SystemException;
		
		// READ ALL PENDING AND RESOLVED REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
		List<ReimbursementPojo> viewAllRequests(int employeeId) throws SystemException;
		
		// VIEW ALL EMPLOYEES
		List<EmployeePojo> viewAllEmployees() throws SystemException;
}
