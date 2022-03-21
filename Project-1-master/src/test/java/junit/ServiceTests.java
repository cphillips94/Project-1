package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.ManagerService;
import service.ManagerServiceImpl;

public class ServiceTests {
	
	EmployeeDao employeeDao = mock(EmployeeJdbcDaoImpl.class);
	ManagerDao managerDao = mock(ManagerJdbcDaoImpl.class);
	
	static EmployeeService employeeService;
	static ManagerService managerService;
	@BeforeAll
	public static void setUp() {
		// Instantiate Service Layer Objects
		employeeService = new EmployeeServiceImpl();
		managerService = new ManagerServiceImpl();
	}
	
	@Test
	public void testEmployeeLogin() {
		try {
			EmployeePojo actualResult = employeeService.employeeLogin(100,"1234");
			EmployeePojo expectedResult = new EmployeePojo(100, "Celia", "Mae",  "(546)654-1654" , "665 Weelia Ct." , "1234", "https://images.unsplash.com/photo-1611432579699-484f7990b127?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8aGVhZHNob3R8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
			when(employeeDao.fetchEmployee(100)).thenReturn(new EmployeePojo(100, "Celia", "Mae",  "(546)654-1654" , "665 Weelia Ct." , "1234", "https://images.unsplash.com/photo-1611432579699-484f7990b127?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8aGVhZHNob3R8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"));
			assertEquals(actualResult,expectedResult);
			//verify(employeeDao).fetchEmployee(100);
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	@Test
	public void testManagerLogin() {
		try {
			ManagerPojo actualResult = managerService.managerLogin(101,"2468");
			ManagerPojo expectedResult = new ManagerPojo(101, "Roz", "Slug",  "(546)354-3218" , "354 Watching Rd." , "2468", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg/800px-Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg?20220222130835");
			when(managerDao.fetchManager(101)).thenReturn(new ManagerPojo(101, "Roz", "Slug",  "(546)354-3218" , "354 Watching Rd." , "2468", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg/800px-Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg?20220222130835"));
			assertEquals(actualResult,expectedResult);
			//verify(employeeDao).fetchEmployee(100);
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
		
	}
}
