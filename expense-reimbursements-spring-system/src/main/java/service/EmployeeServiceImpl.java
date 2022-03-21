package service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import dao.EmployeeDao;
import dao.ReimbursementDao;
import entity.EmployeeEntity;
import entity.ReimbursementEntity;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;

public class EmployeeServiceImpl implements EmployeeService{
	
	public static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	ReimbursementDao reimbursementDao;
	
	public EmployeeServiceImpl() {
		//employeeDao = new EmployeeJdbcDaoImpl();
	}
	
	
	
	// LOGIN
	public EmployeePojo employeeLogin(int employeeId, String employeePassword) throws SystemException {
		LOG.info("Entering employeeLogin() in Service Layer");
		
		EmployeePojo employeePojo = null;
		EmployeePojo loginAttempt = EmployeeService.fetchEmployee(employeeId);
		if (loginAttempt.getEmployeePassword().equals(employeePassword)) {
			employeePojo = loginAttempt;
		}
		
		LOG.info("Exiting employeeLogin() in Service Layer");
		return employeePojo;
	}
	// SUBMIT A REIMBURSEMENT REQUEST
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws SystemException {
	ReimbursementEntity reimbursementEntity =new ReimbursementEntity(reimbursementPojo.getRequestingEmployeeId(),reimbursementPojo.getReimbursementAmount(),true);
	reimbursementDao.saveAndFlush(reimbursementEntity);
	reimbursementPojo= new ReimbursementPojo(reimbursementEntity.getReimbursementId(), reimbursementEntity.getRequestingEmployeeId(), reimbursementEntity.getReimbursementAmount(),true);
		
			return reimbursementPojo;
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
		Optional<EmployeeEntity> optional=employeeDao.findById(employeeId);
		EmployeePojo employeePojo =null;
		if(optional.isPresent()) {
			EmployeeEntity employeeEntity=optional.get();
			employeePojo =new EmployeePojo(employeeEntity.getEmployeeId(),employeeEntity.getEmployeeFirstName(),employeeEntity.getEmployeeLastName(),employeeEntity.getEmployeePhoneNumber(),employeeEntity.getEmployeeAddress(),employeeEntity.getPassword(), employeeEntity.getEmployeeImageUrl());
		}
		
		return employeePojo;
	}
	// UPDATE EMPLOYEE DETAILS TABLE
	public EmployeePojo updateEmployee(EmployeePojo employeePojo) throws SystemException {
		EmployeeEntity employeeEntity =new EmployeeEntity(employeePojo.getEmployeeId(),employeePojo.getEmployeeFirstName(),employeePojo.getEmployeeLastName(),employeePojo.getEmployeePhoneNumber(),employeePojo.getEmployeeAddress(),employeePojo.getEmployeePassword(),employeePojo.getEmployeeImageUrl());
		employeeDao.save(employeeEntity);
		employeePojo =new EmployeePojo(employeeEntity.getEmployeeId(),employeeEntity.getEmployeeFirstName(),employeeEntity.getEmployeeLastName(),employeeEntity.getEmployeePhoneNumber(),employeeEntity.getEmployeeAddress(),employeeEntity.getPassword(),employeeEntity.getEmployeeImageUrl());
		
		
		return employeePojo;
	}




	
}
