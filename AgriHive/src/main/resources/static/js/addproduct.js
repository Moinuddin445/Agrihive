document.getElementById("productForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const form = event.target;
    const formData = new FormData(form);

    // 1. Get farmId from sessionStorage
    const farmId = sessionStorage.getItem("farmId") || 1; // Default to 1 if not set
    formData.append("farmId", farmId);

    const responseMessage = document.getElementById("responseMessage");

    // 2. Show loading message
    responseMessage.innerText = "Submitting product... Please wait.";

    try {
        const response = await fetch("http://localhost:8080/api/products/add", {
            method: "POST",
            body: formData
        });

        const result = await response.json();

        // 3. Handle success or backend error
        if (!response.ok) {
            throw new Error(result.message || "Server error");
        }

        responseMessage.innerText = result.message || "Product added successfully!";
        form.reset();
    } catch (error) {
        responseMessage.innerText = `Failed to add product: ${error.message}`;
        console.error("Error:", error);
    }
});