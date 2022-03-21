package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;

public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	ManagerDao managerDao;
	
	public static final Logger LOG = LogManager.getLogger(ManagerServiceImpl.class);
	
	//ManagerDao managerDao = new ManagerJdbcDaoImpl();
	
	public ManagerPojo managerLogin(int managerId, String managerPassword) throws SystemException {
		LOG.info("Entering managerLogin() in Service Layer");
		
		ManagerPojo managerPojo = null;
		ManagerPojo loginAttempt = managerDao.fetchManager(managerId);
		if (loginAttempt.getManagerPassword().equals(managerPassword)) {
			managerPojo = loginAttempt;
		}
		
		LOG.info("Exiting managerLogin() in Service Layer");
		return managerPojo;
	}

	public ReimbursementPojo readPendingRequest(int reimbursementId) throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.readPendingRequest(reimbursementId);
	}

	public ReimbursementPojo approveOrDeny(ReimbursementPojo reimbursementPojo) throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.approveOrDeny(reimbursementPojo);
	}

	public List<ReimbursementPojo> viewAllPendingRequests() throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.viewAllPendingRequests();
	}

	public List<ReimbursementPojo> viewAllResolvedRequests() throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.viewAllResolvedRequests();
	}

	public List<ReimbursementPojo> viewAllRequests(int employeeId) throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.viewAllRequests(employeeId);
	}

	public List<EmployeePojo> viewAllEmployees() throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.viewAllEmployees();
	}

 
}

