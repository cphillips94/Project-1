package pojo;

import java.util.Objects;

public class ReimbursementPojo {
	private int reimbursementId;
	private int resolvedReimbursementId;
	private int requestingEmployeeId;
	private double reimbursementAmount;
	private boolean reimbursementPending;
	private boolean requestApproved;
	private String dateOfRequest;
	private String dateResolved;
	
	public ReimbursementPojo() {}
	
	// Constructor for testing Pending Requests
	public ReimbursementPojo(int reimbusermentId, int requestingEmployeeId, double reimbursementAmount, boolean reimbursementPending) {
		this.reimbursementId = reimbusermentId;
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementPending = reimbursementPending;
	}

	// Constructor for Pending Requests
	public ReimbursementPojo(int reimbursementId, int requestingEmployeeId, double reimbursementAmount,
			boolean reimbursementPending, String dateOfRequest) {
		this.reimbursementId = reimbursementId;
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementPending = reimbursementPending;
		this.dateOfRequest = dateOfRequest;
	}
	
	// Constructor for adding Resolved Requests
	public ReimbursementPojo(int reimbursementId, boolean requestApproved) {		
		this.reimbursementId = reimbursementId;
		this.requestApproved = requestApproved;
	}
	// Constructor for testing Resolved Requests
	public ReimbursementPojo(int resolvedReimbusementId, int reimbursementId, int requestingEmployeeId,
			double reimbursementAmount, boolean reimbursementPending, boolean requestApproved) {
		this.resolvedReimbursementId = resolvedReimbusementId;
		this.reimbursementId = reimbursementId;
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementPending = reimbursementPending;
		this.requestApproved = requestApproved;
	}

	// Constructor for Resolved Requests
	public ReimbursementPojo(int resolvedReimbusementId, int reimbursementId, int requestingEmployeeId,
			double reimbursementAmount, boolean reimbursementPending, boolean requestApproved, String dateOfRequest,
			String dateResolved) {
		this.resolvedReimbursementId = resolvedReimbusementId;
		this.reimbursementId = reimbursementId;
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementPending = reimbursementPending;
		this.requestApproved = requestApproved;
		this.dateOfRequest = dateOfRequest;
		this.dateResolved = dateResolved;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getResolvedReimbursementId() {
		return resolvedReimbursementId;
	}

	public void setResolvedReimbursementId(int resolvedReimbursementId) {
		this.resolvedReimbursementId = resolvedReimbursementId;
	}

	public int getRequestingEmployeeId() {
		return requestingEmployeeId;
	}

	public void setRequestingEmployeeId(int requestingEmployeeId) {
		this.requestingEmployeeId = requestingEmployeeId;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public boolean isReimbursementPending() {
		return reimbursementPending;
	}

	public void setReimbursementPending(boolean reimbursementPending) {
		this.reimbursementPending = reimbursementPending;
	}

	public boolean isRequestApproved() {
		return requestApproved;
	}

	public void setRequestApproved(boolean requestApproved) {
		this.requestApproved = requestApproved;
	}

	public String getDateOfRequest() {
		return dateOfRequest;
	}

	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	public String getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(String dateResolved) {
		this.dateResolved = dateResolved;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(reimbursementAmount, reimbursementId, reimbursementPending, requestApproved,
				requestingEmployeeId, resolvedReimbursementId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementPojo other = (ReimbursementPojo) obj;
		return Double.doubleToLongBits(reimbursementAmount) == Double.doubleToLongBits(other.reimbursementAmount)
				&& reimbursementId == other.reimbursementId && reimbursementPending == other.reimbursementPending
				&& requestApproved == other.requestApproved && requestingEmployeeId == other.requestingEmployeeId
				&& resolvedReimbursementId == other.resolvedReimbursementId;
	}

	@Override
	public String toString() {
		return "ReimbursementPojo [reimbursementId=" + reimbursementId + ", resolvedReimbursementId="
				+ resolvedReimbursementId + ", requestingEmployeeId=" + requestingEmployeeId + ", reimbursementAmount="
				+ reimbursementAmount + ", reimbursementPending=" + reimbursementPending + ", requestApproved="
				+ requestApproved + "]";
	}

	
}
