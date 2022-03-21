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
import pojo.ReimbursementPojo;

public class EmployeeJdbcDaoImpl implements EmployeeDao {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeJdbcDaoImpl.class);
	
	List<ReimbursementPojo> pendingRequests;
	List<ReimbursementPojo> resolvedRequests;
	
	// SUBMIT A REIMBURSEMENT REQUEST
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws SystemException {
		LOG.info("Entering submitRequest() in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			// Create a statement
			Statement stmt = conn.createStatement();
			
			// reimbursement_pending will always be true for this method
			String query1 = "INSERT INTO reimbursement_details( requesting_employee_id, reimbursement_amount, reimbursement_pending) VALUES("+reimbursementPojo.getRequestingEmployeeId()+", "+reimbursementPojo.getReimbursementAmount()+", 't')";
			int rows = stmt.executeUpdate(query1);
			LOG.debug("INSERT query in submitRequest() was successful");
			
			// Add reimbursement ID and date ID to reimbursement POJO for use in upper layers
			String query2 = "SELECT MAX(reimbursement_id), MAX(date_of_request) FROM reimbursement_details";
			ResultSet rs = stmt.executeQuery(query2);
			LOG.debug("SELECT query in submitRequest() was successful");
			
			if(rs.next()) {
				reimbursementPojo.setReimbursementId(rs.getInt(1));
				reimbursementPojo.setDateOfRequest(rs.getString(2));
			}
			
		} catch (SQLException e) {
		
			throw new SystemException();
		}
		
		LOG.info("Exiting submitRequest() in DAO");
		return reimbursementPojo;
	}
	
	// VIEW PENDING REIMBUSEMENT REQUEST FOR LOGGED IN EMPLOYEE
	public List<ReimbursementPojo> viewPendingRequests(int employeeId) throws SystemException {
		LOG.info("Entering viewPendingRequests in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> pendingRequests = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM reimbursement_details WHERE requesting_employee_id="+employeeId+" AND reimbursement_pending='t'";
			ResultSet rs = stmt.executeQuery(query);
			LOG.debug("Query executed successfully");
			// iterate through the result set
			while(rs.next()) {
				// copy each record into a ReinbursementPojo object
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5));
				// add the POJO to the collection
				pendingRequests.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
		
			throw new SystemException();
		}
		
		
		LOG.info("Exiting viewPendingRequests in DAO");
		return pendingRequests;
	}
	
	// VIEW RESOLVED REIMBURSEMENT REQUESTS FOR LOGGED IN EMPLOYEE
	public List<ReimbursementPojo> viewResolvedRequests(int employeeId) throws SystemException {
		LOG.info("Entering viewResolvedRequests in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> resolvedRequests = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT reimbursement_details.reimbursement_id, resolved_reimbursement_id, requesting_employee_id, reimbursement_amount, reimbursement_pending, request_approved, date_of_request, date_resolved FROM reimbursement_details INNER JOIN resolved_reimbursements ON reimbursement_details.reimbursement_id=resolved_reimbursements.reimbursement_id WHERE requesting_employee_id="+employeeId+" ORDER BY resolved_reimbursements.date_resolved";
			ResultSet rs = stmt.executeQuery(query);
			LOG.debug("Query executed successfully");
			// iterate through the result set
			while(rs.next()) {
				// copy each record into a ReinbursementPojo object
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getBoolean(5), rs.getBoolean(6), rs.getString(7), rs.getString(8));
				// add the POJO to the collection
				resolvedRequests.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
		
			throw new SystemException();
		}
		
		LOG.info("Exiting viewResolvedRequests in DAO");
		return resolvedRequests;
	}
	
	// READ FROM EMPLOYEE DETAILS TABLE
	public EmployeePojo fetchEmployee(int employeeId) throws SystemException {
		LOG.info("Entering fetchEmployee() in Dao");
		EmployeePojo employeePojo = null;
		// Step 2 - pass the connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		
		try {
			// Create a statement
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee_details WHERE employee_id="+employeeId;
			ResultSet rs = stmt.executeQuery(query);
			LOG.debug("Query executed successfully");
			
			if(rs.next()) {
				
				employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
			
		} catch (SQLException e){
		
			throw new SystemException();
		}
		
		LOG.info("Exiting fetchEmployee() in Dao");
		return employeePojo;
	}
	
	// UPDATE EMPLOYEE DETAILS TABLE
	public EmployeePojo updateEmployee(EmployeePojo employeePojo) throws SystemException {
		LOG.info("Entering updateEmployee() in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			// Update all values in employee_details table
			String query = "UPDATE employee_details SET employee_first_name='"+employeePojo.getEmployeeFirstName()+"', employee_last_name='"+employeePojo.getEmployeeLastName()+"', employee_phone_number='"+employeePojo.getEmployeePhoneNumber()+"', employee_address='"+employeePojo.getEmployeeAddress()+"', employee_password='"+employeePojo.getEmployeePassword()+"', employee_image_url='"+employeePojo.getEmployeeImageUrl()+"' WHERE employee_Id="+employeePojo.getEmployeeId();
						
			int rows = stmt.executeUpdate(query);
			LOG.debug("Query executed successfully");
		} catch (SQLException e) {
		
			throw new SystemException();
		}
		LOG.info("Exiting updateEmployee() in DAO");
		return employeePojo;
	}
	
}
