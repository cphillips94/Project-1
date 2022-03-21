package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;

public class EmployeeServiceImpl implements EmployeeService{
	
	public static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);
	
	EmployeeDao employeeDao;
	
	public EmployeeServiceImpl() {
		employeeDao = new EmployeeJdbcDaoImpl();
	}
	// LOGIN
	public EmployeePojo employeeLogin(int employeeId, String employeePassword) throws SystemException {
		LOG.info("Entering employeeLogin() in Service Layer");
		
		EmployeePojo employeePojo = null;
		EmployeePojo loginAttempt = employeeDao.fetchEmployee(employeeId);
		if (loginAttempt.getEmployeePassword().equals(employeePassword)) {
			employeePojo = loginAttempt;
		}
		
		LOG.info("Exiting employeeLogin() in Service Layer");
		return employeePojo;
	}
	// SUBMIT A REIMBURSEMENT REQUEST
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws SystemException {
		return employeeDao.submitRequest(reimbursementPojo);
	}
	// VIEW PENDING REIMBUSEMENT REQUEST FOR LOGGED IN EMPLOYEE
	public List<ReimbursementPojo> viewPendingRequests(int employeeId) throws SystemException {
		return employeeDao.viewPendingRequests(employeeId);
	}
	// VIEW RESOLVED REIMBURSEMENT REQUESTS FOR LOGGED IN EMPLOYEE
	public List<ReimbursementPojo> viewResolvedRequests(int employeeId) throws SystemException {
		return employeeDao.viewResolvedRequests(employeeId);
	}
	// READ FROM EMPLOYEE DETAILS TABLE
	public EmployeePojo fetchEmployee(int employeeId) throws SystemException {
		return employeeDao.fetchEmployee(employeeId);
	}
	// UPDATE EMPLOYEE DETAILS TABLE
	public EmployeePojo updateEmployee(EmployeePojo employeePojo) throws SystemException {
		return employeeDao.updateEmployee(employeePojo);
	}
	
	
}
