  window.addEventListener('DOMContentLoaded', () => {

    document.getElementById('registrationForm').addEventListener('submit', async function (event) {
      event.preventDefault(); // Prevent default form submission

      const fullName = document.getElementById('fullName').value.trim();
      const email = document.getElementById('email').value.trim();
      const phone = document.getElementById('phone').value.trim();
      const address = document.getElementById('address').value.trim();
      const farmLocation = document.getElementById('farmLocation').value.trim();
      const farmName = document.getElementById('farmName').value.trim();
      const password = document.getElementById('password').value;
      const confirmPassword = document.getElementById('confirmPassword').value;

      if (fullName && email && phone && address && farmLocation && farmName && password && confirmPassword) {

        // Email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
          alert('Please enter a valid email address.');
          return;
        }

        // Phone number validation
        const phoneRegex = /^\d{10}$/;
        if (!phoneRegex.test(phone)) {
          alert('Please enter a valid 10-digit phone number.');
          return;
        }

        // Password match check
        if (password !== confirmPassword) {
          alert('Passwords do not match.');
          return;
        }

        const data = {
          name: fullName,
          email: email,
          phone: phone,
          address: address,
          password: password,
          farmName: farmName,
          location: farmLocation
        };

        try {
          const submitButton = document.getElementById('submitBtn');
          submitButton.disabled = true;

          const response = await fetch('/api/farmers/signup', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
          });

          const result = await response.text();

          if (response.ok) {
            alert('Registration successful!');
            document.getElementById('registrationForm').reset();
            window.location.href = './farmerlogin.html';

          } else {
            alert('Error: ' + result);
          }

        } catch (error) {
          console.error('Error:', error);
          alert('Something went wrong during registration.');
        } finally {
          document.getElementById('submitBtn').disabled = false;
        }

      } else {
        alert('Please fill out all required fields.');
      }
    });
  });