


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;
import io.javalin.Javalin;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.ManagerService;
import service.ManagerServiceImpl;

public class ERSMain {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);
	
	public static void main(String[] args) {
		Javalin myServer = Javalin.create((config)-> config.enableCorsForAllOrigins()).start(4040);

		LOG.debug("Server listening at port 4040...");

		// This is a catch block for SystemException
		myServer.exception(SystemException.class,(se,ctx) -> {
			Map<String, String> error = new HashMap<String,String>();
			error.put("message",se.getMessage());
			error.put("datetime", LocalDateTime.now()+"");
			ctx.json(error);
		});

		ManagerService managerService = new ManagerServiceImpl();
		EmployeeService employeeService = new EmployeeServiceImpl();

		// ***********************************************************
		// Manager Operations
		// ***********************************************************
		
		// LOGIN
		myServer.get("/api/login/{manid}/{pswd}", ctx -> {
			String managerId = ctx.pathParam("manid");
			String password = ctx.pathParam("pswd");
			ManagerPojo loginAttempt = managerService.managerLogin(Integer.parseInt(managerId),password);
			ctx.json(loginAttempt);
		});

		// READ FROM PENDING REIMBURSEMENTS TABLE
		myServer.get("/api/p-reim/{reimid}", ctx -> {
			String reimbursementId = ctx.pathParam("reimid");
			ReimbursementPojo reimbursement = managerService.readPendingRequest(Integer.parseInt(reimbursementId));
			ctx.json(reimbursement);
		});

		// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
		myServer.post("/api/appdeny", ctx -> {
			LOG.debug("Entering the approve or deny lambda function");
			// there is an incoming book json in the request body, fetch the request body and store it in the POJO
			ReimbursementPojo reimbursmentPojo = ctx.bodyAsClass(ReimbursementPojo.class);
			// send the request to the service layer
			ReimbursementPojo reimbursement = managerService.approveOrDeny(reimbursmentPojo);
			ctx.json(reimbursement);
		});

		// READ ALL VALUES FROM PENDING REQUESTS TABLE
		myServer.get("/api/all-p-reims", ctx -> {
			List<ReimbursementPojo> reimbursement = managerService.viewAllPendingRequests();
			ctx.json(reimbursement);
		});

		// READ ALL VALUES FROM RESOLVED REQUESTS TABLE
		myServer.get("/api/all-r-reims", ctx -> {
		List<ReimbursementPojo> allResolvedReimbursement = managerService.viewAllResolvedRequests();
		ctx.json(allResolvedReimbursement);
		});

		// READ ALL PENDING AND RESOLVED REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
		myServer.get("/api/all-reims/{empid}", ctx -> {
			LOG.debug("viewAllRequests() Entering Lambda Function");
			String employeeId = ctx.pathParam("empid");
			List<ReimbursementPojo> allPendingReimbursement = managerService.viewAllRequests(Integer.parseInt(employeeId));
			ctx.json(allPendingReimbursement);
		});

		// VIEW ALL EMPLOYEES
		myServer.get("/api/employees", ctx -> {
			List<EmployeePojo> allEmployees = managerService.viewAllEmployees();
			ctx.json(allEmployees);
		});

		// *****************************************************************
		// Employee Operations
		// *****************************************************************
		
		// LOGIN
		myServer.get("/api/e-log/{empid}/{pswd}", ctx -> {
			LOG.debug("Entered e-log lambda function");
			String employeeId = ctx.pathParam("empid");
			String password = ctx.pathParam("pswd");
			EmployeePojo loginAttempt = employeeService.employeeLogin(Integer.parseInt(employeeId),password);
			ctx.json(loginAttempt);
		});

		//SUBMIT REIMBURSEMENT REQUEST
		myServer.post("/api/p-reims", ctx ->{
			LOG.debug("Entered submit lambda function");
			ReimbursementPojo reimbursementPojo = ctx.bodyAsClass(ReimbursementPojo.class);
			ReimbursementPojo submitRequest = employeeService.submitRequest(reimbursementPojo);
			ctx.json(submitRequest);
		});

		//VIEW PENDING REQUEST
		myServer.get("/api/p-reims/{empid}", ctx ->{
			LOG.debug("Entered pendreq lambda function");
			String employeeId = ctx.pathParam("empid");
			List<ReimbursementPojo> viewPendingRequests = employeeService.viewPendingRequests(Integer.parseInt(employeeId));
			ctx.json(viewPendingRequests);
		});

		//VIEW RESOLVED REQUEST
		myServer.get("/api/r-reims/{empid}", ctx ->{
			LOG.debug("Entered resreq lambda function");
			String employeeId = ctx.pathParam("empid");
			List<ReimbursementPojo> viewResolvedRequests = employeeService.viewResolvedRequests(Integer.parseInt(employeeId));
			ctx.json(viewResolvedRequests);
		});
		
		//GET EMPLOYEE
		myServer.get("/api/employee/{empid}", ctx ->{
			LOG.debug("Entered getEmpl lambda function");
			String employeeId = ctx.pathParam("empid");
			EmployeePojo fetchEmployee = employeeService.fetchEmployee(Integer.parseInt(employeeId));
			ctx.json(fetchEmployee);
		});

		//UPDATE EMPLOYEE INFORMATION
		myServer.put("/api/employees", ctx ->{
			LOG.debug("Entered emplinfo lambda function");
			EmployeePojo employeePojo = ctx.bodyAsClass(EmployeePojo.class);
			EmployeePojo updateEmployee = employeeService.updateEmployee(employeePojo);
			ctx.json(updateEmployee);
		});
	}
}