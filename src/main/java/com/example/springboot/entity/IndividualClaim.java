package com.example.springboot.entity;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Individual_claims")
@Data
public class IndividualClaim 
{
	    @Id
	    @Column(nullable = false)
	    private String claimId;

	    @Column(nullable = false)
	    private String hospitalName;

	    @Column(nullable = false)
	    private LocalDate dateOfAdmission;

	    @Column(nullable = false)
	    private String diagnosis;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private TreatmentType treatmentType;

	    @Column(nullable = false)
	    private Double claimAmount;

	    @Lob
	    @Basic(fetch = FetchType.LAZY)
	    @Column(nullable = false)
	    private byte[] hospitalBills;

	    @Lob
	    @Basic(fetch = FetchType.LAZY)
	    @Column(nullable = false)
	    private byte[] dischargeSummary;

	    @Lob
	    @Basic(fetch = FetchType.LAZY)
	    @Column(nullable = false)
	    private byte[] medicalReports;

	    @Lob
	    @Basic(fetch = FetchType.LAZY)
	    @Column(nullable = false)
	    private byte[] idProof;

	    @Column(nullable = false)
	    private LocalDate submissionDate = LocalDate.now();

	    @Enumerated(EnumType.STRING)
	    private ClaimStatus status = ClaimStatus.PENDING;

		public String getClaimId() {
			return claimId;
		}

		public void setClaimId(String claimId) {
			this.claimId = claimId;
		}

		public String getHospitalName() {
			return hospitalName;
		}

		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}

		public LocalDate getDateOfAdmission() {
			return dateOfAdmission;
		}

		public void setDateOfAdmission(LocalDate dateOfAdmission) {
			this.dateOfAdmission = dateOfAdmission;
		}

		public String getDiagnosis() {
			return diagnosis;
		}

		public void setDiagnosis(String diagnosis) {
			this.diagnosis = diagnosis;
		}

		public TreatmentType getTreatmentType() {
			return treatmentType;
		}

		public void setTreatmentType(TreatmentType treatmentType) {
			this.treatmentType = treatmentType;
		}

		public Double getClaimAmount() {
			return claimAmount;
		}

		public void setClaimAmount(Double claimAmount) {
			this.claimAmount = claimAmount;
		}

		public byte[] getHospitalBills() {
			return hospitalBills;
		}

		public void setHospitalBills(byte[] hospitalBills) {
			this.hospitalBills = hospitalBills;
		}

		public byte[] getDischargeSummary() {
			return dischargeSummary;
		}

		public void setDischargeSummary(byte[] dischargeSummary) {
			this.dischargeSummary = dischargeSummary;
		}

		public byte[] getMedicalReports() {
			return medicalReports;
		}

		public void setMedicalReports(byte[] medicalReports) {
			this.medicalReports = medicalReports;
		}

		public byte[] getIdProof() {
			return idProof;
		}

		public void setIdProof(byte[] idProof) {
			this.idProof = idProof;
		}

		public LocalDate getSubmissionDate() {
			return submissionDate;
		}

		public void setSubmissionDate(LocalDate submissionDate) {
			this.submissionDate = submissionDate;
		}

		public ClaimStatus getStatus() {
			return status;
		}

		public void setStatus(ClaimStatus status) {
			this.status = status;
		}

}
