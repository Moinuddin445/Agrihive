document.getElementById('owner-login-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const phone = document.getElementById('phone').value.trim();
    const password = document.getElementById('password').value.trim();
    const message = document.getElementById('login-message');

    try {
        const response = await axios.post('http://localhost:8080/api/farmers/login', {
            phone,
            password
        });

        // On successful login
        if (response.status === 200) {
            window.location.href = './AddProduct.html';
        }
    } catch (error) {
        console.error(error);
        if (error.response && error.response.status === 401) {
            message.textContent = 'Invalid password. Please try again.';
        } else if (error.response && error.response.status === 404) {
            message.textContent = 'Phone number not found.';
        } else {
            message.textContent = 'Server error. Please try again later.';
        }
    }
});
