// payment.js (Place in src/main/resources/static/Script/)
document.addEventListener('DOMContentLoaded', () => {
    const insuranceFormData = JSON.parse(localStorage.getItem('insuranceFormData'));

    if (insuranceFormData) {
        document.getElementById('planName').textContent = insuranceFormData.planName; // Set plan name
        document.getElementById('premiumAmount').textContent = insuranceFormData.premium; // Set premium
        document.getElementById('customerName').textContent = insuranceFormData.name;
        document.getElementById('customerAge').textContent = insuranceFormData.age;
        document.getElementById('customerGender').textContent = insuranceFormData.gender;
        document.getElementById('customerEmail').textContent = insuranceFormData.email;
        document.getElementById('customerPhone').textContent = insuranceFormData.phone;
        document.getElementById('customerAddress').textContent = insuranceFormData.address;
    } else {
        // Handle case where form data is not available (e.g., redirect or display a message)
        alert("Insurance form data is missing. Please fill out the form first.");
        window.location.href = document.referrer; // Redirect back to the previous page
    }
});

function processPayment() {
    // Implement your payment processing logic here.
    // This is a placeholder; you'll need to integrate with a payment gateway.

    // 1. Get payment information:
    const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
    // ... get card details if paymentMethod is creditcard or debitcard

    // 2. Validate payment information (client-side validation):
    // ... perform validation

    // 3. Send payment information to your server (Spring Boot backend):
    // ... use fetch or XMLHttpRequest to send data to your server

    // 4. Handle response from the server:
    // ... check if payment was successful

    // Example placeholder:
    alert('Payment processed successfully (placeholder)!');
}