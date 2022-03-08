package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;
import service.EmployeeServiceImpl;

public class ManagerJdbcDaoImpl implements ManagerDao {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);

	// READ FROM MANAGER DETAILS TABLE
	public ManagerPojo fetchManager(int managerId) throws SystemException {
		
		LOG.info("Entering fetchManager in DAO");
		ManagerPojo managerPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM manager_details WHERE manager_id="+managerId;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				
				managerPojo = new ManagerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		
		LOG.info("Exting fetchManager in DAO");
		
		return managerPojo;
	}
	
	// UPDATE REIMBURSEMENTS TABLE
	public ReimbursementPojo updatePendingRequest(int reimbursementId) throws SystemException {
		LOG.info("Entered updatePendingRequest() in DAO");
		ReimbursementPojo reimbursementPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			reimbursementPojo = readPendingRequest(reimbursementId);
			String query = "UPDATE reimbursement_details SET reimbursement_pending='f' WHERE reimbursement_id=" + reimbursementId;
			int rows = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		LOG.info("Exited updatePendingRequest() in DAO");
		return reimbursementPojo;
	}
	
	// READ A SPECIFIC PENDING REIMBURSEMENT FROM TABLE
	public ReimbursementPojo readPendingRequest(int reimbursementId) throws SystemException {
		
		LOG.info("Entering readPendingRequest() in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		ReimbursementPojo pendingRequest = null;
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM reimbursement_details WHERE reimbursement_id="+reimbursementId+" AND reimbursement_pending='t'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				 pendingRequest = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		
		
		LOG.info("Exiting readPendingRequest() in Manager DAO");
		
		return pendingRequest;
	}

	// ADD TO RESOLVED REIMBURSEMENTS TABLE
	public ReimbursementPojo addResolvedRequest(ReimbursementPojo reimbursementPojo) throws SystemException {
		
		LOG.info("Entering addResolvedRequest() in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		ReimbursementPojo resolvedRequest = null;
		
		try {
			Statement stmt = conn.createStatement();
			
			// For add resolved requests, reimbursementPending is always false
			String query = "INSERT INTO resolved_reimbursements(reimbursement_id, request_approved) VALUES(" + reimbursementPojo.getReimbursementId() + ", '" + reimbursementPojo.isRequestApproved() + "')";
			int rows = stmt.executeUpdate(query);
			System.out.println("INSERT query in addResolvedRequest() was successful");
			String query2 = "SELECT reimbursement_id, date_resolved FROM resolved_reimbursements WHERE reimbursement_id=MAX(reimbursement_id)";
			ResultSet rs = stmt.executeQuery(query2);
			System.out.println("SELECT query in addResolvedRequest() was successful");
			if(rs.next()) {
				reimbursementPojo.setReimbursementId(rs.getInt(1));
				reimbursementPojo.setDateResolved(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		
		
		LOG.info("Exiting addResolvedRequest() in Manager DAO");
		
		return resolvedRequest;
	}
		
	// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
	public ReimbursementPojo approveOrDeny(ReimbursementPojo reimbursementPojo) throws SystemException {
		LOG.info("Entering approveOrDeny() in Manager DAO");
		// Step 2 - pass the connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		try {
			
			conn.setAutoCommit(false);
			
			reimbursementPojo = updatePendingRequest(reimbursementPojo.getReimbursementId());
			if(reimbursementPojo.isRequestApproved()) {
				reimbursementPojo.setRequestApproved(true);
			} else {
				reimbursementPojo.setRequestApproved(false);
			}
			addResolvedRequest(new ReimbursementPojo(reimbursementPojo.getReimbursementId(), reimbursementPojo.isRequestApproved()));
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		
		LOG.info("Exiting approveOrDeny() in Manager DAO");
		return reimbursementPojo;
	}
	
	// READ ALL VALUES FROM PENDING REQUESTS TABLE
	public List<ReimbursementPojo> viewAllPendingRequests() throws SystemException {
		
		LOG.info("Entering viewAllPendingRequests() in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> pendingRequests = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM pending_reimbursements WHERE reimbursement_pending='t'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5));
				pendingRequests.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		
		
		LOG.info("Exiting viewAllPendingRequests() in Manager DAO");
		
		return pendingRequests;
	}
	
	// READ ALL VALUES FROM RESOLVED REQUESTS TABLE
	public List<ReimbursementPojo> viewAllResolvedRequests() throws SystemException {
		
		LOG.info("Entering viewResolvedRequests() in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> resolvedRequest = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT resolved_reimbursement_id, reimbursement_details.reimbursement_id, requesting_employee_id, reimbursement_amount, reimbursement_pending, request_approved, date_of_request, date_resolved FROM reimbursement_details INNER JOIN resolved_reimbursements ON reimbursement_details.reimbursement_id=resolved_reimbursements.reimbursement_id ORDER BY resolved_reimbursements.date_resolved";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getBoolean(5), rs.getBoolean(6), rs.getString(7), rs.getString(8));
				resolvedRequest.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		
		
		LOG.info("Exiting viewResolvedRequests() in Manager DAO");
		
		return resolvedRequest;
	}
	
	// READ ALL PENDING AND RESOLVED REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
	public List<ReimbursementPojo> viewAllRequests(int employeeId) throws SystemException {
		LOG.info("Entering viewAllRequests() in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> allRequests = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			// Make a left join to pull information from resolved_reimbursements for 
			String query = "SELECT resolved_reimbursement_id, reimbursement_details.reimbursement_id, requesting_employee_id, reimbursement_amount, reimbursement_pending, request_approved, date_of_request, date_resolved FROM reimbursement_details LEFT JOIN resolved_reimbursements ON reimbursement_details.reimbursement_id=resolved_reimbursements.reimbursement_id WHERE requesting_employee_id="+employeeId+" ORDER BY reimbursement_details.reimbursement_id";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				// Add all pending reimbursements to all requests array
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getBoolean(5), rs.getBoolean(6), rs.getString(7), rs.getString(8));
				allRequests.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		
		
		LOG.info("Exiting viewAllRequests() in Manager DAO");
		
		return allRequests;
	}
	
	// VIEW ALL EMPLOYEES
	public List<EmployeePojo> viewAllEmployees() throws SystemException {
		LOG.info("Entering viewAllEmployees() in DAO");
		List<EmployeePojo> allEmployees = new ArrayList<EmployeePojo>();

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee_details";
			
			ResultSet rs = stmt.executeQuery(query);
			System.out.println(rs);
			while (rs.next()) {
				EmployeePojo employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				allEmployees.add(employeePojo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		LOG.info("Exiting viewAllEmployees() in DAO");
		return allEmployees;
	}
		
}
