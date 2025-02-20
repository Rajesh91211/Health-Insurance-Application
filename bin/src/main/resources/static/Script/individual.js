// Insurance plans data
const plans = [
  {
    id: 'silver',
    name: 'Silver Plan',
    price: 2999,
    color: 'bg-gray-200',
    benefits: [
      'Coverage up to ₹5 Lakhs',
      'Cashless hospitalization',
      'Day care procedures',
      'Pre & post hospitalization expenses',
      'Ambulance charges up to ₹2000',
      'Free health checkup after 4 years',
      'No claim bonus up to 50%'
    ]
  },
  {
    id: 'gold',
    name: 'Gold Plan',
    price: 4999,
    color: 'bg-yellow-100',
    benefits: [
      'Coverage up to ₹10 Lakhs',
      'All Gold plan benefits',
      'AYUSH treatment coverage',
      'Organ donor expenses',
      'Alternative treatments',
      'Daily hospital cash ₹1000',
      'Restoration of sum insured',
      'Free health checkup after 2 years'
    ]
  },
  {
    id: 'diamond',
    name: 'Diamond Plan',
    price: 7999,
    color: 'bg-blue-100',
    benefits: [
      'Coverage up to ₹25 Lakhs',
      'All Diamond plan benefits',
      'International treatment coverage',
      'Air ambulance coverage',
      'Critical illness coverage',
      'Personal accident cover ₹10 Lakhs',
      'Daily hospital cash ₹2000',
      'Annual health checkup',
      'Zero waiting period'
    ]
  }
];

let selectedPlan = null;
let customerInfo = null;

// Payment method configurations
const paymentMethods = {
  debitcard: {
    title: 'Debit Card Payment',
    fields: `
      <div class="space-y-4">
        <div class="form-group">
          <label class="block text-sm font-medium mb-1">Card Number</label>
          <input type="text" class="w-full p-2 border rounded-lg" placeholder="1234 5678 9012 3456" maxlength="16" pattern="[0-9]{16}" required>
        </div>
        <div class="form-group">
          <label class="block text-sm font-medium mb-1">Card Holder Name</label>
          <input type="text" class="w-full p-2 border rounded-lg" placeholder="John Doe" required>
        </div>
        <div class="grid grid-cols-2 gap-4">
          <div class="form-group">
            <label class="block text-sm font-medium mb-1">Expiry Date</label>
            <input type="text" class="w-full p-2 border rounded-lg" placeholder="MM/YY" maxlength="5" pattern="(0[1-9]|1[0-2])\/([0-9]{2})" required>
          </div>
          <div class="form-group">
            <label class="block text-sm font-medium mb-1">CVV</label>
            <input type="password" class="w-full p-2 border rounded-lg" placeholder="***" maxlength="3" pattern="[0-9]{3}" required>
          </div>
        </div>
      </div>
    `
  },
  creditcard: {
    title: 'Credit Card Payment',
    fields: `
      <div class="space-y-4">
        <div class="form-group">
          <label class="block text-sm font-medium mb-1">Card Number</label>
          <input type="text" class="w-full p-2 border rounded-lg" placeholder="1234 5678 9012 3456" maxlength="16" pattern="[0-9]{16}" required>
        </div>
        <div class="form-group">
          <label class="block text-sm font-medium mb-1">Card Holder Name</label>
          <input type="text" class="w-full p-2 border rounded-lg" placeholder="John Doe" required>
        </div>
        <div class="grid grid-cols-2 gap-4">
          <div class="form-group">
            <label class="block text-sm font-medium mb-1">Expiry Date</label>
            <input type="text" class="w-full p-2 border rounded-lg" placeholder="MM/YY" maxlength="5" pattern="(0[1-9]|1[0-2])\/([0-9]{2})" required>
          </div>
          <div class="form-group">
            <label class="block text-sm font-medium mb-1">CVV</label>
            <input type="password" class="w-full p-2 border rounded-lg" placeholder="***" maxlength="3" pattern="[0-9]{3}" required>
          </div>
        </div>
      </div>
    `
  },
  upi: {
    title: 'UPI Payment',
    fields: `
      <div class="space-y-4">
        <div class="grid grid-cols-3 gap-4">
          <div class="upi-option p-4 border rounded-lg cursor-pointer hover:border-blue-500 hover:bg-blue-50" data-upi="gpay">
            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_pay_logo.svg/2560px-Google_pay_logo.svg.png" alt="Google Pay" class="h-8 mx-auto mb-2">
            <p class="text-center text-sm">Google Pay</p>
          </div>
          <div class="upi-option p-4 border rounded-lg cursor-pointer hover:border-blue-500 hover:bg-blue-50" data-upi="phonepe">
            <img src="https://download.logo.wine/logo/PhonePe/PhonePe-Logo.wine.png" alt="PhonePe" class="h-8 mx-auto mb-2">
            <p class="text-center text-sm">PhonePe</p>
          </div>
          <div class="upi-option p-4 border rounded-lg cursor-pointer hover:border-blue-500 hover:bg-blue-50" data-upi="paytm">
            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Paytm_Logo_%28standalone%29.svg/2560px-Paytm_Logo_%28standalone%29.svg.png" alt="Paytm" class="h-8 mx-auto mb-2">
            <p class="text-center text-sm">Paytm</p>
          </div>
        </div>
        <div class="form-group">
          <label class="block text-sm font-medium mb-1">UPI ID</label>
          <input type="text" class="w-full p-2 border rounded-lg" placeholder="username@upi" pattern="[a-zA-Z0-9\.\-_]{2,256}@[a-zA-Z]{2,64}" required>
        </div>
      </div>
    `
  }
};

// Initialize the application
document.addEventListener('DOMContentLoaded', () => {
  displayPlans();
  setupEventListeners();
  initializePaymentMethods();
});

// Format price in Indian Rupees
function formatPrice(price) {
  return new Intl.NumberFormat('en-IN', {
    style: 'currency',
    currency: 'INR',
    maximumFractionDigits: 0
  }).format(price);
}

// Display all insurance plans
function displayPlans() {
  const container = document.getElementById('plans-container');
  container.innerHTML = plans.map(plan => `
    <div class="plan-card ${plan.color} p-6 rounded-lg shadow-lg">
      <div class="flex items-center gap-3 mb-4">
        <svg xmlns="http://www.w3.org/2000/svg" class="w-8 h-8" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M20.24 12.24a6 6 0 0 0-8.49-8.49L5 10.5V19h8.5z"></path>
          <line x1="16" y1="8" x2="2" y2="22"></line>
          <line x1="17.5" y1="15" x2="9" y2="15"></line>
        </svg>
        <h3 class="text-2xl font-bold">${plan.name}</h3>
      </div>
      <p class="text-3xl font-bold mb-6">${formatPrice(plan.price)}<span class="text-sm font-normal">/month</span></p>
      <ul class="space-y-2 mb-6">
        ${plan.benefits.map(benefit => `
          <li class="flex items-center gap-2">
            <span class="w-2 h-2 bg-green-500 rounded-full"></span>
            ${benefit}
          </li>
        `).join('')}
      </ul>
      <button
        onclick="selectPlan('${plan.id}')"
        class="w-full bg-blue-600 text-white py-3 rounded-lg font-semibold hover:bg-blue-700 transition-colors"
      >
        Select Plan
      </button>
    </div>
  `).join('');
}

// Initialize payment methods
function initializePaymentMethods() {
  const paymentMethodsContainer = document.querySelector('.payment-options');
  if (!paymentMethodsContainer) return;

  // Add event listener for payment method selection
  paymentMethodsContainer.addEventListener('change', (e) => {
    if (e.target.name === 'paymentMethod') {
      const method = e.target.value;
      const detailsContainer = document.getElementById('detailsContainer');
      const payNowButton = document.getElementById('payNowButton');

      if (paymentMethods[method]) {
        detailsContainer.innerHTML = paymentMethods[method].fields;
        payNowButton.style.display = 'block';
        payNowButton.className = 'w-full bg-blue-600 text-white py-3 rounded-lg font-semibold hover:bg-blue-700 transition-colors mt-4';

        if (method === 'upi') {
          setupUPIOptions();
        }
      }
    }
  });
}

// Setup UPI options
function setupUPIOptions() {
  const upiOptions = document.querySelectorAll('.upi-option');
  upiOptions.forEach(option => {
    option.addEventListener('click', () => {
      upiOptions.forEach(opt => opt.classList.remove('selected', 'border-blue-500', 'bg-blue-50'));
      option.classList.add('selected', 'border-blue-500', 'bg-blue-50');
    });
  });
}

// Set up event listeners
function setupEventListeners() {
  document.getElementById('customer-form').addEventListener('submit', handleCustomerSubmit);
}

// Handle plan selection
function selectPlan(planId) {
  selectedPlan = plans.find(p => p.id === planId);
  document.getElementById('plans-section').classList.add('hidden');
  document.getElementById('customer-form-section').classList.remove('hidden');
  
  document.getElementById('selected-plan-summary').innerHTML = `
    <h3 class="font-semibold">Selected Plan: ${selectedPlan.name}</h3>
    <p class="text-gray-600">${formatPrice(selectedPlan.price)}/month</p>
  `;
}

// Handle customer form submission
function handleCustomerSubmit(e) {
  e.preventDefault();
  const form = e.target;
  customerInfo = {
    firstName: form.firstName.value,
    lastName: form.lastName.value,
    email: form.email.value,
    phone: form.phone.value,
    dateOfBirth: form.dateOfBirth.value,
    address: form.address.value,
    city: form.city.value,
    state: form.state.value,
    pinCode: form.pinCode.value
  };

  document.getElementById('customer-form-section').classList.add('hidden');
  document.getElementById('payment-section').classList.remove('hidden');
  
  document.getElementById('payment-summary').innerHTML = `
    <h3 class="font-semibold">Order Summary</h3>
    <p class="text-gray-600">${selectedPlan.name} - ${formatPrice(selectedPlan.price)}/month</p>
  `;
}

// Process payment
function processPayment() {
  const selectedMethod = document.querySelector('input[name="paymentMethod"]:checked');
  
  if (!selectedMethod) {
    alert('Please select a payment method');
    return;
  }

  const detailsContainer = document.getElementById('detailsContainer');
  const inputs = detailsContainer.querySelectorAll('input[required]');
  let isValid = true;

  inputs.forEach(input => {
    if (!input.value || (input.pattern && !new RegExp(input.pattern).test(input.value))) {
      isValid = false;
      input.classList.add('border-red-500');
    } else {
      input.classList.remove('border-red-500');
    }
  });

  if (!isValid) {
    alert('Please fill in all required fields correctly');
    return;
  }

  // If UPI selected, check if a UPI option is selected
  if (selectedMethod.value === 'upi') {
    const selectedUpiOption = document.querySelector('.upi-option.selected');
    if (!selectedUpiOption) {
      alert('Please select a UPI payment option');
      return;
    }
  }

  // Disable the pay button and show processing state
  const payButton = document.getElementById('payNowButton');
  payButton.disabled = true;
  payButton.textContent = 'Processing Payment...';
  payButton.classList.add('opacity-75', 'cursor-not-allowed');

  // Simulate payment processing
  setTimeout(() => {
    document.getElementById('payment-section').classList.add('hidden');
    document.getElementById('success-section').classList.remove('hidden');
    document.getElementById('success-message').textContent = 
      `Thank you for choosing our ${selectedPlan.name}. Your policy details will be sent to your email shortly.`;
    
    // Reset forms after 3 seconds
    setTimeout(() => {
      resetForms();
      document.getElementById('success-section').classList.add('hidden');
      document.getElementById('plans-section').classList.remove('hidden');
    }, 3000);
  }, 2000);
}

// Reset all forms
function resetForms() {
  document.getElementById('customer-form').reset();
  const detailsContainer = document.getElementById('detailsContainer');
  if (detailsContainer) {
    detailsContainer.innerHTML = '';
  }
  const payButton = document.getElementById('payNowButton');
  if (payButton) {
    payButton.style.display = 'none';
    payButton.disabled = false;
    payButton.textContent = 'Pay Now';
    payButton.classList.remove('opacity-75', 'cursor-not-allowed');
  }
  selectedPlan = null;
  customerInfo = null;
}