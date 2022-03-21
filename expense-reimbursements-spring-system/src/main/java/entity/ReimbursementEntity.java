package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "reimbursement_details")
public class ReimbursementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimbursement_id")
	private int reimbursementId;

	@Column(name = "requesting_employee_id")
	private int requestingEmployeeId;

	@Column(name = "reimbursement_amount")
	private double reimbursementAmount;

	@Column(name = "reimbursement_pending")
	private boolean reimbursementPending;

	@Column(name = "date_of_request")
	private String dateOfRequest;

	public ReimbursementEntity(int requestingEmployeeId, double reimbursementAmount, boolean reimbursementPending) {
		super();
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementPending = reimbursementPending;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
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

	public String getDateOfRequest() {
		return dateOfRequest;
	}

	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	@Override
	public String toString() {
		return "ReimbursementEntity [reimbursementId=" + reimbursementId + ", requestingEmployeeId="
				+ requestingEmployeeId + ", reimbursementAmount=" + reimbursementAmount + ", reimbursementPending="
				+ reimbursementPending + ", dateOfRequest=" + dateOfRequest + "]";
	}
	
}
