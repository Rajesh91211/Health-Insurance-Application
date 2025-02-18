
	  /* ✅ Display Claim Data on UI
	  function displayClaimDetails(claimData) {
	    const claimDetailsSection = document.getElementById("claim-details");

	    claimDetailsSection.innerHTML = `
	      <h3>Claim Details</h3>
	      <p><strong>Claim ID:</strong> ${claimData.claimId}</p>
	      <p><strong>Hospital Name:</strong> ${claimData.hospitalName}</p>
	      <p><strong>Diagnosis:</strong> ${claimData.diagnosis}</p>
	      <p><strong>Treatment Type:</strong> ${claimData.treatmentType}</p>
	      <p><strong>Claim Amount:</strong> $${claimData.claimAmount}</p>
	      <p><strong>Admission Date:</strong> ${claimData.dateOfAdmission}</p>

	      <h3>Uploaded Documents</h3>
	      <div class="image-gallery">
	        <img src="/uploads/${claimData.hospitalBills}" alt="Hospital Bill" class="preview-image">
	        <img src="/uploads/${claimData.dischargeSummary}" alt="Discharge Summary" class="preview-image">
	        <img src="/uploads/${claimData.medicalReports}" alt="Medical Report" class="preview-image">
	        <img src="/uploads/${claimData.idProof}" alt="ID Proof" class="preview-image">
	      </div>
	    `;

	    claimDetailsSection.classList.remove("hidden");
	  }

 */

	  // Claim form handling

	  document.addEventListener("DOMContentLoaded", () => {
	    setupEventListeners();
	  });

	  function setupEventListeners() {
	    const forms = document.querySelectorAll("form.claim-form");
	    forms.forEach((form) => {
	      form.addEventListener("submit", handleClaimSubmit);
	    });
	  }

	  function showClaimType(type) {
	    document.querySelectorAll(".claim-section").forEach((section) => section.classList.add("hidden"));
	    
	    const selectedSection = document.getElementById(`${type}-claim-section`);
	    if (selectedSection) {
	      selectedSection.classList.remove("hidden");
	      selectedSection.scrollIntoView({ behavior: "smooth" });
	    }
	  }

	  async function handleClaimSubmit(event) {
	    event.preventDefault();
	    const form = event.target;
	    const button = form.querySelector("button");
	    button.disabled = true;
	    button.textContent = "Processing...";

	    let formData = new FormData();
	    formData.append("claimId", document.getElementById("claimId").value);
	    formData.append("hospitalName", document.getElementById("hospitalName").value);
	    formData.append("diagnosis", document.getElementById("diagnosis").value);
	    formData.append("treatmentType", document.getElementById("treatmentType").value);
	    formData.append("claimAmount", document.getElementById("claimAmount").value);
	    formData.append("dateOfAdmission", document.getElementById("dateOfAdmission").value);

	    if (form.id === "family-claim-form") {
	      formData.append("primaryInsuredName", document.getElementById("primaryInsuredName").value);
	      formData.append("patientName", document.getElementById("patientName").value);
	      formData.append("relationPrimaryInsured", document.getElementById("relationPrimaryInsured").value);
	    }

	    const fileInputs = form.id === "family-claim-form" 
	      ? ["hospitalBills", "dischargeSummary", "relationshipProof", "patientIdProof"]
	      : ["hospitalBills", "dischargeSummary", "medicalReports", "idProof"];
		  
		  let missingFiles = []; //check files are null or  missing files

	    fileInputs.forEach((file) => {
	      let inputFile = form.querySelector(`[name='${file}']`);
	      if (inputFile && inputFile.files.length > 0) {
	        formData.append(file, inputFile.files[0]);
	      }
		  else {
		              console.error(`File input '${file}' not found or no file selected.`); // Improved error message
					  missingFiles.push(file);//missing file
		          }
	    });
		
		//missing file code
		if (missingFiles.length > 0) {
		        alert(`Please upload the following files: ${missingFiles.join(", ")}`);
		        button.disabled = false;
		        button.textContent = form.id === "individual-claim-form" ? "Submit Claim" : "Submit Family Claim";
		        return;
		    }
		//--end of missing file

	    const endpoint = form.id === "family-claim-form" ? "/api/submit1" : "/api/submit";

	    try {
	      let response = await fetch(endpoint, {
	        method: "POST",
	        body: formData,
	      });

	      if (response.ok) 
			{
			document.getElementById("success-message").classList.remove("hidden");
	        form.reset();
	      } else {
	        let errorMessage = await response.text();
	        alert("Error: " + errorMessage);
	      }
	    } catch (error) {
	      console.error("Error submitting claim:", error);
	      alert("Submission failed. Please try again.");
	    } finally {
	      button.disabled = false;
	      button.textContent = form.id === "individual-claim-form" ? "Submit Claim" : "Submit Family Claim";
	    }
	  }

	  function showSuccessMessage() {
	    document.getElementById("success-message").classList.remove("hidden");
	  }

	  function closeSuccessMessage() {
	    document.getElementById("success-message").classList.add("hidden");
	  }

	  
	  
	  	






