function showApplicationForm() {
    document.getElementById('applicationForm').style.display = 'block';
    document.getElementById('applicationForm').scrollIntoView({ behavior: 'smooth' });
}

function handleSubmit(event) {
    event.preventDefault();
    const formData = {
        name: document.getElementById('name').value,
        age: document.getElementById('age').value,
        gender: document.getElementById('gender').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value,
        address: document.getElementById('address').value
		
    };
    console.log('Form submitted:', formData);
    localStorage.setItem('insuranceFormData', JSON.stringify(formData));
    window.location.href = 'payment.html'; // Or whatever your payment page URL is
}