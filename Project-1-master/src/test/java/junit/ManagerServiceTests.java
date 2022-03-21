package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;
import service.ManagerService;
import service.ManagerServiceImpl;

public class ManagerServiceTests {
	
	// add mocking for Dao methods
	ManagerDao managerDao = mock(ManagerJdbcDaoImpl.class);
	
	// Declare Service variables
	static ManagerService managerService = null;
	
	@BeforeAll
	public static void setUp() {
		managerService = new ManagerServiceImpl();
	}
	
	@Test
	public void testReadPendingReimbursement() {
		try {
			ReimbursementPojo expectedResult = managerService.readPendingRequest(125);
			ReimbursementPojo actualResult = new ReimbursementPojo(125,100,35,true);
			when(managerDao.readPendingRequest(125)).thenReturn(new ReimbursementPojo(125,100,35,true));
			assertEquals(expectedResult, actualResult);
		} catch (SystemException e) {
			
		}
		
	}
	
	@Test
	public void testApproveOrDeny() {
		try {
			ReimbursementPojo expectedResult = managerService.approveOrDeny(new ReimbursementPojo(125,100,35,true));
			ReimbursementPojo actualResult = new ReimbursementPojo(125,100,35,true);
			when(managerDao.readPendingRequest(125)).thenReturn(new ReimbursementPojo(125,100,35,true));
			assertEquals(expectedResult, actualResult);
		} catch (SystemException e) {
			
		}
		
	}
	
	@Test
	public void testViewAllPendingRequests() {
		try {
			List<ReimbursementPojo> expectedResult = managerService.viewAllPendingRequests();
			List<ReimbursementPojo> actualResult = new ArrayList<>();
			when(managerDao.readPendingRequest(125)).thenReturn(new ReimbursementPojo(125,100,35,true));
			assertEquals(expectedResult, actualResult);
		} catch (SystemException e) {
			
		}
		
	}
	
	@Test
	public void testViewAllResolvedRequests() {
		try {
			List<ReimbursementPojo> expectedResult = managerService.viewAllResolvedRequests();
			List<ReimbursementPojo> actualResult = new ArrayList<ReimbursementPojo>();
			when(managerDao.readPendingRequest(125)).thenReturn(new ReimbursementPojo(125,100,35,true));
			assertEquals(expectedResult, actualResult);
		} catch (SystemException e) {
			
		}
		
	}
	
	@Test
	public void testViewAllRequests() {
		try {
			List<ReimbursementPojo> expectedResult = managerService.viewAllRequests(125);
			List<ReimbursementPojo> actualResult = new ArrayList();
			when(managerDao.readPendingRequest(125)).thenReturn(new ReimbursementPojo(125,100,35,true));
			assertEquals(expectedResult, actualResult);
		} catch (SystemException e) {
			
		}
		
	}
	
	@Test
	public void testViewAllEmployees() {
		try {
			List<EmployeePojo> expectedResult = managerService.viewAllEmployees();
			ReimbursementPojo actualResult = new ReimbursementPojo(125,100,35,true);
			when(managerDao.readPendingRequest(125)).thenReturn(new ReimbursementPojo(125,100,35,true));
			assertEquals(expectedResult, actualResult);
		} catch (SystemException e) {
			
		}
		
	}
}
