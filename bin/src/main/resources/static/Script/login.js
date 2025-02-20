    document.addEventListener('DOMContentLoaded', function() {
            document.getElementById("show-register").addEventListener("click", function() {
            document.getElementById("login-form").style.display = "none";
            document.getElementById("register-form").style.display = "block";
        });

        document.getElementById("show-login").addEventListener("click", function() {
            document.getElementById("register-form").style.display = "none";
            document.getElementById("login-form").style.display = "block";
        });

                  //login js logic--------------
        const loginForm = document.getElementById('loginForm');
        

        loginForm.addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent form from actually submitting (page reload)

            const email = document.getElementById('email-login').value;
            const password = document.getElementById('password-login').value;

            fetch('/api/custLogin', { // URL of your Spring Boot or Flask login endpoint
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json' // Important: Tell the backend you're sending JSON
                },
                body: JSON.stringify({ email: email, password: password }) // Convert data to JSON
            })
            .then(response => 			{
			        if (!response.ok) {
			            return response.json().then(data => { 
			                throw new Error(data.message || 'Login failed'); // Throw error with message
			            });
			        }
			        return response.json();
			    }) // Get JSON response from the backend
            .then(data => {
                alert(data.message); // Display the message (success or error)
                if (data.message === 'Login successful') {
                    // Redirect, set a session, etc. (Do something after successful login)
                    window.location.href = '/api/home'; // Example: Redirect to a dashboard page
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Invalid creditials. please try again'); // Handle network errors, etc.
            });
        });
		
         //registration js logic----------------
		
		const registerForm = document.getElementById('registerForm');
		
        registerForm.addEventListener('submit', function(event) {
            event.preventDefault();

            const fullname = document.getElementById('fullname').value;
            const email = document.getElementById('email-register').value;
            const password = document.getElementById('password-register').value;

		  //const formData = new FormData(registerForm);
		  
            fetch('/api/custReg', { // URL of your Spring Boot or Flask register endpoint
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ fullname: fullname, email: email, password: password })
            })
            .then(response => {
				console.log("Full Response:", response); // Log the entire response object
				        if (!response.ok) {
				            return response.text().then(err => {throw new Error(err)}); // get error text
				        }
				        return response.json(); // Try to parse JSON only if response is OK
				    })
            .then(data => {
				console.log("Parsed Data:", data);
				console.log(data);
                alert(data.message);
                if (data.message === 'Registration successful!,  Please log in') 
					{
                    // Redirect, clear the form, etc.
					window.location.href = "/login1";
                    //loginForm.style.display = "block"; // Switch to login form
                   // registerForm.style.display = "none";
                   // registerForm.reset(); // Clear the form fields
                }
            })
            .catch(error => 
				{
					console.error('Fetch Error:', error); // Log the error
					        alert('An error occurred. Please try again.'); 
            });
        });
		});