import { API_URL } from './config.js';

document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const name = document.getElementById('name').value;
    const phone = document.getElementById('phone').value;
    const password = document.getElementById('password').value;

    const response = await fetch(`${API_URL}/users/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, phone, password })
    });

    const messageEl = document.getElementById('message');
    const result = await response.text();

    if (response.ok && result === 'Login successful') {
        messageEl.style.color = 'green';
        messageEl.textContent = result;

        // Give it a second to show message (optional)
        setTimeout(() => {
            window.location.href = './BuyerDash.html';
        }, 1000);
    } else {
        messageEl.style.color = 'red';
        messageEl.textContent = result || 'Login failed';
    }
});
