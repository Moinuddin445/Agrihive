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
      productInfo.innerHTML = `
        <img src="${product.imageUrl || 'https://via.placeholder.com/200'}" alt="${product.name}">
        <h3>${product.name}</h3>
        <p>${product.description}</p>
        <p><strong>Price:</strong> ₹${(product.pricePerKg ?? 0).toFixed(2)} per kg</p>
        <p><strong>Farm Name:</strong> ${product.farmName}</p>
        <p><strong>Location:</strong> ${product.location}</p>
        <p><strong>Harvest Date:</strong> ${new Date(product.harvestDate).toLocaleDateString()}</p>
        <button class="add-to-cart" data-id="${product.productId}">Add to Cart</button>
        <button class="buy-now" data-id="${product.productId}">Buy Now</button>
      `;

      // Handle Add to Cart click
      document.querySelector(".add-to-cart").addEventListener("click", () => {
        // Add to cart logic here
        const cart = JSON.parse(localStorage.getItem("cart")) || [];
        cart.push(product);
        localStorage.setItem("cart", JSON.stringify(cart));
      });

      // Handle Buy Now click
      document.querySelector(".buy-now").addEventListener("click", () => {
        // Redirect to checkout page (or implement checkout logic)
        window.location.href = "checkout.html"; // Example: redirecting to a checkout page
      });
    })
    .catch(err => {
      console.error("Error fetching product details:", err);
      productInfo.innerHTML = "<p>Error loading product details</p>";
    });
});
