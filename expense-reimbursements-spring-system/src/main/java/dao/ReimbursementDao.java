package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.ReimbursementEntity;
import exceptions.SystemException;

@Repository
public interface ReimbursementDao extends JpaRepository<ReimbursementEntity, Integer> {
	// READ ALL VALUES FROM PENDING REQUESTS TABLE
	//List<ReimbursementPojo> viewAllPendingRequests() throws SystemException;
	
	// VIEW PENDING REIMBUSEMENT REQUEST FOR LOGGED IN EMPLOYEE
	List<ReimbursementEntity> viewPendingRequests(int employeeId) throws SystemException;
	
	// SUBMIT A REIMBURSEMENT REQUEST
	//public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws SystemException;
	

	
	// READ ALL PENDING AND RESOLVED REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
	List<ReimbursementEntity> viewAllRequests(int employeeId) throws SystemException;
	

}
