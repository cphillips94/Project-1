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

@Table(name = "resolved_reimbursements")
public class ResolvedReimbursementEntity {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "resolved_reimbursement_id")
private int resolvedReimbursementId;


@Column(name = "reimbursement_id")
private String reimbursementId;

@Column(name = "request_approved")
private String requestApproved;

@Column(name = "date_resolved")
private String managerContact;

public ResolvedReimbursementEntity(int resolvedReimbursementId, String reimbursementId, String requestApproved,
		String managerContact) {
	super();
	this.resolvedReimbursementId = resolvedReimbursementId;
	this.reimbursementId = reimbursementId;
	this.requestApproved = requestApproved;
	this.managerContact = managerContact;
}

public int getResolvedReimbursementId() {
	return resolvedReimbursementId;
}

public void setResolvedReimbursementId(int resolvedReimbursementId) {
	this.resolvedReimbursementId = resolvedReimbursementId;
}

public String getReimbursementId() {
	return reimbursementId;
}

public void setReimbursementId(String reimbursementId) {
	this.reimbursementId = reimbursementId;
}

public String getRequestApproved() {
	return requestApproved;
}

public void setRequestApproved(String requestApproved) {
	this.requestApproved = requestApproved;
}

public String getManagerContact() {
	return managerContact;
}

public void setManagerContact(String managerContact) {
	this.managerContact = managerContact;
}

@Override
public String toString() {
	return "ResolvedReimbursementEntity [resolvedReimbursementId=" + resolvedReimbursementId + ", reimbursementId="
			+ reimbursementId + ", requestApproved=" + requestApproved + ", managerContact=" + managerContact + "]";
}




}