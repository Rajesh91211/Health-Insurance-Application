// Claim form handling
document.addEventListener('DOMContentLoaded', () => {
  setupEventListeners();
});

function setupEventListeners() {
  const individualForm = document.getElementById('individual-claim-form');
  const familyForm = document.getElementById('family-claim-form');

  individualForm.addEventListener('submit', handleClaimSubmit);
  familyForm.addEventListener('submit', handleClaimSubmit);
}

function showClaimType(type) {
  const sections = document.querySelectorAll('.claim-section');
  sections.forEach(section => section.classList.add('hidden'));

  const selectedSection = document.getElementById(`${type}-claim-section`);
  selectedSection.classList.remove('hidden');

  // Scroll to the section
  selectedSection.scrollIntoView({ behavior: 'smooth' });
}

function handleClaimSubmit(e) {
  e.preventDefault();
  const form = e.target;
  const button = form.querySelector('button');
  button.disabled = true;
  button.textContent = 'Processing...';

  // Simulate form submission
  setTimeout(() => {
    showSuccessMessage();
    button.disabled = false;
    button.textContent = form.id === 'individual-claim-form' ? 'Submit Claim' : 'Submit Family Claim';
    form.reset();
  }, 2000);
}

function showSuccessMessage() {
  const successMessage = document.getElementById('success-message');
  successMessage.classList.remove('hidden');
}

function closeSuccessMessage() {
  const successMessage = document.getElementById('success-message');
  successMessage.classList.add('hidden');
}