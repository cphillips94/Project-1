package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import entity.ResolvedReimbursementEntity;
import exceptions.SystemException;

@Repository
public interface ResolvedReimbursementDao extends JpaRepository<ResolvedReimbursementEntity, Integer> {
	
	// READ ALL VALUES FROM RESOLVED REQUESTS TABLE
	List<ResolvedReimbursementEntity> viewAllResolvedRequests() throws SystemException;
	
	
	// VIEW RESOLVED REIMBURSEMENT REQUESTS FOR LOGGED IN EMPLOYEE
	List<ResolvedReimbursementEntity> viewResolvedRequests(int employeeId) throws SystemException;
}
