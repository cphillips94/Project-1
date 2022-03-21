package dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.EmployeeEntity;




@Repository
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Integer> {
	
	// EMPLOYEE OPERATIONS
	// *****************************************************
	
	// READ FROM EMPLOYEE DETAILS TABLE
	//public EmployeeEntity fetchEmployee(int employeeId) throws SystemException;
	
	// UPDATE EMPLOYEE DETAILS TABLE
	//public EmployeeEntity updateEmployee(EmployeePojo employeePojo) throws SystemException;
	
	// VIEW ALL EMPLOYEES
	//List<EmployeeEntity> viewAllEmployees() throws SystemException;
			
}
