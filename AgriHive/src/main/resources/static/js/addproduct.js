document.getElementById("productForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const form = event.target;
    const formData = new FormData(form);

    // Set farmId here - hardcoded for now or fetch from session
    formData.append("farmId", 1); // TODO: Replace with dynamic farm ID based on login

    try {
        const response = await fetch("http://localhost:8080/api/products/add", {
            method: "POST",
            body: formData
        });

        const result = await response.json();
        document.getElementById("responseMessage").innerText = result.message || "Product added successfully!";
        form.reset();
    } catch (error) {
        document.getElementById("responseMessage").innerText = "Failed to add product. Try again.";
        console.error("Error:", error);
    }
});
