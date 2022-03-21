package controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;
import service.ManagerService;







@RestController
@RequestMapping("api")

public class ManagerController {
@Autowired
ManagerService managerService;

// LOGIN
@GetMapping("managers/{managerId}/{managerPassword}")
public ManagerPojo managerLogin(@PathVariable("managerId") int managerId, @PathVariable("managerPassword") String managerPassword) throws SystemException {
    return managerService.managerLogin(managerId, managerPassword);
}

// READ FROM PENDING REIMBURSEMENTS TABLE
@GetMapping("p-reimbursement/{reimbursementId}")
public ReimbursementPojo readPendingRequest(@PathVariable("reimbursementId") int reimbursementId) throws SystemException{
    return managerService.readPendingRequest(reimbursementId);
}

// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
@PostMapping("reimbursement")
public ReimbursementPojo approveOrDeny(@RequestBody ReimbursementPojo reimbursementPojo) throws SystemException{
    return managerService.approveOrDeny(reimbursementPojo);
}
    
// READ ALL VALUES FROM PENDING REQUESTS TABLE
@GetMapping("p-reimbursements")
public List<ReimbursementPojo> viewAllPendingRequests() throws SystemException {
    return managerService.viewAllPendingRequests();
}

// READ ALL VALUES FROM RESOLVED REQUESTS TABLE
@GetMapping("r-reimbursements")
public List<ReimbursementPojo> viewAllResolvedRequests() throws SystemException {
    return managerService.viewAllResolvedRequests();
}

// READ ALL PENDING AND RESOLVED REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
@GetMapping("reimbursements/{employeeId}")
public List<ReimbursementPojo> viewAllRequests(@PathVariable("employeeId") int employeeId) throws SystemException {
    return managerService.viewAllRequests(employeeId);
}

// VIEW ALL EMPLOYEES
@GetMapping("employees")
public List<EmployeePojo> viewAllEmployees() throws SystemException {
    return managerService.viewAllEmployees();
}
}