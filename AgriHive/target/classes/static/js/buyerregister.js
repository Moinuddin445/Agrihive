document.getElementById("registrationForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const buyerData = {
        fullName: document.getElementById("fullName").value,
        phone: document.getElementById("phone").value,
        email: document.getElementById("email").value,
        shippingAddress: document.getElementById("shippingAddress").value,
        password: document.getElementById("password").value,
        confirmPassword: document.getElementById("confirmPassword").value
    };

    if (buyerData.password !== buyerData.confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/buyers/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(buyerData)
        });

        if (response.ok) {
            alert("Registration successful!");
            window.location.href = "login.html";
        } else {
            const error = await response.text();
            alert("Registration failed: " + error);
        }
    } catch (error) {
        alert("Error: " + error);
    }
});
