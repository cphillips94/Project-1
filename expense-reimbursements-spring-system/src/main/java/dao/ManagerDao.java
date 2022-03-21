package dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.ManagerEntity;




@Repository
public interface ManagerDao extends JpaRepository<ManagerEntity, Integer> {
	
		// READ FROM MANAGER DETAILS TABLE
		//public ManagerPojo fetchManager(int managerId) throws SystemException;
		
		// READ FROM PENDING REIMBURSEMENTS TABLE
		//ReimbursementPojo readPendingRequest(int reimbursementId) throws SystemException;
		
		// ADD TO RESOLVED REIMBURSEMENTS TABLE
		//ReimbursementPojo addResolvedRequest(ReimbursementPojo reimbursementPojo) throws SystemException;
		
		// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
		//ReimbursementPojo approveOrDeny(ReimbursementPojo reimbursementPojo) throws SystemException;
		

}