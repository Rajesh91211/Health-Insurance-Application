
/*
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
}*/



// payment.js
document.addEventListener('DOMContentLoaded', () => {
    // ... (Existing code for summary population) ...

    const paymentOptions = document.querySelectorAll('input[name="paymentMethod"]');
    const detailsContainer = document.getElementById('detailsContainer');
    const payNowButton = document.getElementById('payNowButton');

    paymentOptions.forEach(option => {
        option.addEventListener('change', () => {
            detailsContainer.innerHTML = ''; // Clear previous details

            if (option.value === 'creditcard') {
                detailsContainer.innerHTML = `
                    <div class="credit-card-details">
                        <h3>Credit Card Details</h3>
                        <div class="form-group">
                            <label for="cardNumber">Card Number:</label>
                            <input type="text" id="cardNumber" required>
                        </div>
                        <div class="form-group">
                            <label for="expiry">Expiry:</label>
                            <input type="month" id="expiry" required>
                        </div>
                        <div class="form-group">
                            <label for="cvv">CVV:</label>
                            <input type="number" id="cvv" required>
                        </div>
                    </div>
                `;
                payNowButton.style.display = 'block';
            } else if (option.value === 'debitcard') {
                detailsContainer.innerHTML = `
                    <div class="debit-card-details">
                        <h3>Debit Card Details</h3>
                        <div class="form-group">
                            <label for="debitCardNumber">Card Number:</label>
                            <input type="text" id="debitCardNumber" required>
                        </div>
                        <div class="form-group">
                            <label for="debitExpiry">Expiry:</label>
                            <input type="month" id="debitExpiry" required>
                        </div>
                        <div class="form-group">
                            <label for="debitCVV">CVV:</label>
                            <input type="number" id="debitCVV" required>
                        </div>
                    </div>
                `;
                payNowButton.style.display = 'block';
            } else if (option.value === 'upi') {
                detailsContainer.innerHTML = `
                    <div class="upi-details">
                        <h3>UPI Options</h3>
                        <label>
                            <input type="radio" name="upiOption" value="phonepay" checked> PhonePe
                        </label>
                        <label>
                            <input type="radio" name="upiOption" value="googlepay"> Google Pay
                        </label>
                        <label>
                            <input type="radio" name="upiOption" value="paytm"> Paytm
                        </label>
                    </div>
                `;
                payNowButton.style.display = 'block';
            }
        });
    });

    // ... (rest of the existing code) ...
});