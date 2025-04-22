// product-details.js

function showToast(message) {
  const toast = document.getElementById("toast");
  if (!toast) return;

  toast.textContent = message;
  toast.classList.add("show");

  setTimeout(() => {
    toast.classList.remove("show");
  }, 3000);
}

document.addEventListener("DOMContentLoaded", () => {
  const productInfo = document.getElementById("product-info");
  const productId = new URLSearchParams(window.location.search).get('id'); // Get the 'id' from the URL

  if (!productId) {
    productInfo.innerHTML = "<p>Product not found.</p>";
    return;
  }

  // Fetch product details
  fetch(`http://localhost:8080/api/products/${productId}`)
    .then(res => res.json())
    .then(product => {
      if (!product) {
        productInfo.innerHTML = "<p>Product not found.</p>";
        return;
      }

      // Display product details
      productInfo.innerHTML = `
        <img src="${product.imageName ? '/Pimages/' + product.imageName : 'https://via.placeholder.com/200'}" alt="${product.name}">
        <h3>${product.name}</h3>
        <p>${product.description}</p>
        <p><strong>Category:</strong> ${product.category}</p>
        <p><strong>Price:</strong> ₹${(product.pricePerKg ?? 0).toFixed(2)} per kg</p>
        <p><strong>Available Quantity:</strong> ${product.quantity} kg</p>
        <p><strong>Farm Name:</strong> ${product.farmName || 'N/A'}</p>
        <p><strong>Location:</strong> ${product.location || 'N/A'}</p>
        <p><strong>Farmer:</strong> ${product.farmerName || 'N/A'}</p>
        <button class="add-to-cart" data-id="${product.productId}">Add to Cart</button>
        <button class="buy-now" data-id="${product.productId}">Buy Now</button>
      `;

      // Handle Add to Cart click by calling the function from cartUtils.js
      document.querySelector(".add-to-cart").addEventListener("click", () => {
        addToCart(product);  // Calling addToCart function from cartUtils.js
      });

      // Handle Buy Now click
      document.querySelector(".buy-now").addEventListener("click", () => {
        // Check if there is enough stock for the purchase
        if (product.quantity > 0) {
          window.location.href = "checkout.html"; // Redirect to checkout page
        } else {
          showToast("❌ Sorry, this product is out of stock.");
        }
      });
    })
    .catch(err => {
      console.error("Error fetching product details:", err);
      productInfo.innerHTML = "<p>Error loading product details</p>";
    });
});
