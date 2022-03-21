package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class EmployeeServiceTests {
    
    //add mocking for Dao methods
    EmployeeDao employeeDao = mock(EmployeeJdbcDaoImpl.class);

    //Declare static variables
    static EmployeeService employeeService = null;

    @BeforeAll
    public static void setUp(){
        employeeService = new EmployeeServiceImpl();
    }

    @Test 
    public void testSubmitRequest(){
        ReimbursementPojo expectedResult;
        try {
            expectedResult = employeeService.submitRequest(new ReimbursementPojo(101, 101, 35.0, true));
            ReimbursementPojo actualResult = new ReimbursementPojo(101, 101, 35.0, true);
        when(employeeDao.submitRequest(new ReimbursementPojo(101,101, 35.0, true))).thenReturn(new ReimbursementPojo(101,101, 35.0, true));
        assertEquals(expectedResult, actualResult);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
        public void testViewPendingRequest(){
           List<ReimbursementPojo> expectedResult;

           try {
            expectedResult = new ArrayList<ReimbursementPojo>();
            List<ReimbursementPojo> actualResult = employeeService.viewPendingRequests(101);
            when(employeeDao.viewPendingRequests(101)).thenReturn(new ArrayList<ReimbursementPojo>());
            assertEquals(expectedResult, actualResult);
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testViewResolvedRequest(){
        List<ReimbursementPojo> expectedResult;
        try{
            expectedResult = new ArrayList<ReimbursementPojo>();
            List<ReimbursementPojo> actualResult = employeeService.viewResolvedRequests(101);
            when(employeeDao.viewResolvedRequests(101)).thenReturn(new ArrayList<ReimbursementPojo>());
            assertEquals(expectedResult, actualResult);
        }catch(SystemException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchEmployee(){
        EmployeePojo expectedResult;
        try {
            expectedResult = employeeService.fetchEmployee(101);
            EmployeePojo actualResult = new EmployeePojo(101, "James P.", "Sullivan", " ", " ", "6789", " ");
            when(employeeDao.fetchEmployee(101)).thenReturn(new EmployeePojo(101, "James P.", "Sullivan", " ", " ", "6789", " "));
            assertEquals(expectedResult, actualResult);
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateEmployee(){
        EmployeePojo expectedResult;
        try {
            expectedResult = employeeService.updateEmployee(new EmployeePojo(101, "James P.", "Sullivan", " ", " ", "6789", " "));
            EmployeePojo actualResult = new EmployeePojo(101, "James P.", "Sullivan", " ", " ", "6789", " ");
            when(employeeDao.updateEmployee(new EmployeePojo(101, "James P.", "Sullivan", " ", " ", "6789", " "))).thenReturn(new EmployeePojo(101, "James P.", "Sullivan", " ", " ", "6789", " "));
            assertEquals(expectedResult, actualResult);
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }
}