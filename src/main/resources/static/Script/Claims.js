
// Claim form handling
document.addEventListener('DOMContentLoaded', () => {
  setupEventListeners();
});

function setupEventListeners() {
  const individualForm = document.getElementById('individual-claim-form');
  const familyForm = document.getElementById('family-claim-form');

  if (individualForm) {
      individualForm.addEventListener('submit', handleClaimSubmit);
    }
    if (familyForm) {
      familyForm.addEventListener('submit', handleClaimSubmit);
    }
}

function showClaimType(type) 
{
  /*const sections = document.querySelectorAll('.claim-section');
  sections.forEach(section => section.classList.add('hidden'));

  const selectedSection = document.getElementById(`${type}-claim-section`);
  selectedSection.classList.remove('hidden');

  // Scroll to the section
  selectedSection.scrollIntoView({ behavior: 'smooth' }); */
  
  document.querySelectorAll('.claim-section').forEach(section => section.classList.add('hidden'));

    const selectedSection = document.getElementById(`${type}-claim-section`);
    if (selectedSection) {
      selectedSection.classList.remove('hidden');
      selectedSection.scrollIntoView({ behavior: 'smooth' });
    }
}


async function handleClaimSubmit(e) {
  e.preventDefault();
  const form = e.target;
  const button = form.querySelector('button');
  button.disabled = true;
  button.textContent = 'Processing...';
  
      let formData = new FormData();
      formData.append("claimId", document.getElementById("claimId").value);
      formData.append("hospitalName", document.getElementById("hospitalName").value);
      formData.append("diagnosis", document.getElementById("diagnosis").value);
      formData.append("treatmentType", document.getElementById("treatmentType").value);
      formData.append("claimAmount", document.getElementById("claimAmount").value);
      formData.append("dateOfAdmission", document.getElementById("admissionDate").value);
  	

  	
	  const fileInputs = ["hospitalBills", "dischargeSummary", "medicalReports", "idProof"];
	    fileInputs.forEach(file => {
	      let inputFile = form.querySelector(`[name='${file}']`);
	      if (inputFile && inputFile.files.length > 0) {
	        formData.append(file, inputFile.files[0]);
	      }
	    });
		
		const claimId = document.getElementById("claimId").value;

	    try {
	      let response = await fetch("/api/submit", {
	        method: "POST",
	        body: formData
	      });

	      if (response.ok) {
	        showSuccessMessage();
			
			setTimeout(() => {
			        fetchClaimData(claimId);
			      }, 2000);
			
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

	  // ✅ Display Claim Data on UI
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

 

	






