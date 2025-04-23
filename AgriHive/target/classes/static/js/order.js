document.addEventListener("DOMContentLoaded", () => {
  const productDetails = document.getElementById("product-details");
  const orderForm = document.getElementById("orderForm");
  const buyerNameInput = document.getElementById("buyerName");
  const shippingAddressInput = document.getElementById("shippingAddress");
  const quantityInput = document.getElementById("quantity");
  const totalPriceInput = document.getElementById("totalPrice");

  const productId = new URLSearchParams(window.location.search).get('productId');

  if (!productId) {
    productDetails.innerHTML = "<p>Product not found.</p>";
    return;
  }

  // Fetch product details
  fetch(`http://localhost:8080/api/products/${productId}`)
    .then(res => res.json())
    .then(product => {
      if (!product) {
        productDetails.innerHTML = "<p>Product not found.</p>";
        return;
      }

      // Display product details
      productDetails.innerHTML = `
        <h3>${product.name}</h3>
        <p>${product.description}</p>
        <p><strong>Price:</strong> ₹${(product.pricePerKg ?? 0).toFixed(2)} per kg</p>
        <p><strong>Available Quantity:</strong> ${product.quantity} kg</p>
      `;

      // Calculate Total Price whenever quantity changes
      quantityInput.addEventListener("input", () => {
        const quantity = parseInt(quantityInput.value);
        if (quantity > 0 && quantity <= product.quantity) {
          totalPriceInput.value = (quantity * product.pricePerKg).toFixed(2);
        } else {
          totalPriceInput.value = "Invalid Quantity";
        }
      });

      // Handle form submission to place order
      orderForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const buyerName = buyerNameInput.value;
        const shippingAddress = shippingAddressInput.value;
        const quantity = parseInt(quantityInput.value);

        if (quantity <= 0 || quantity > product.quantity) {
          alert("Please enter a valid quantity.");
          return;
        }

        const orderRequest = {
          buyerName,
          shippingAddress,
          quantity,
          productId
        };

        // Make a POST request to place the order
        fetch("http://localhost:8080/api/orders", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(orderRequest)
        })
        .then(res => res.json())
        .then(response => {
          if (response.status === "success") {
            showToast(response.message);
          } else {
            showToast(response.message);
          }
        })
        .catch(err => {
          console.error("Error placing order:", err);
          showToast("❌ Error placing the order. Please try again.");
        });
      });
    })
    .catch(err => {
      console.error("Error fetching product details:", err);
      productDetails.innerHTML = "<p>Error loading product details.</p>";
    });

  // Show toast notification
  function showToast(message) {
    const toast = document.getElementById("toast");
    if (!toast) return;

    toast.textContent = message;
    toast.style.display = "block";

    setTimeout(() => {
      toast.style.display = "none";
    }, 3000);
  }
});
