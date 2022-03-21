package controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;
import service.EmployeeService;





@RestController
@RequestMapping("api")
public class EmployeeController {
@Autowired
EmployeeService employeeService;


//LOGIN
@GetMapping("Employees/{employeeId}/{employeePassword}")
public EmployeePojo employeeLogin(@PathVariable("employeeId") int employeeId, @PathVariable("employeePassword") String employeePassword) throws SystemException {
    return employeeService.employeeLogin(employeeId, employeePassword);
}
// SUBMIT A REIMBURSEMENT REQUEST
@PostMapping("reimbursements")
ReimbursementPojo submitRequest(@RequestBody ReimbursementPojo reimbursementPojo) throws SystemException {
    return employeeService.submitRequest(reimbursementPojo);
}
// VIEW PENDING REIMBUSEMENT REQUEST FOR LOGGED IN EMPLOYEE
@GetMapping("p-reimbursements/{employeeId}")
public List<ReimbursementPojo> viewPendingRequests(@PathVariable("employeeId") int employeeId) throws SystemException{
    return employeeService.viewPendingRequests(employeeId);
}
// VIEW RESOLVED REIMBURSEMENT REQUESTS FOR LOGGED IN EMPLOYEE
@GetMapping("r-reimbursements/{employeeId}")
List<ReimbursementPojo> viewResolvedRequests(@PathVariable("employeeId") int employeeId) throws SystemException {
    return employeeService.viewResolvedRequests(employeeId);
}
// READ FROM EMPLOYEE DETAILS TABLE
@GetMapping("employees/{employeeId}")
public EmployeePojo fetchEmployee(@PathVariable("employeeId") int employeeId) throws SystemException {
    return employeeService.fetchEmployee(employeeId);
}
// UPDATE EMPLOYEE DETAILS TABLE
@PutMapping("employees")
public EmployeePojo updateEmployee(@RequestBody EmployeePojo employeePojo) throws SystemException {
    return employeeService.updateEmployee(employeePojo);
}
}
