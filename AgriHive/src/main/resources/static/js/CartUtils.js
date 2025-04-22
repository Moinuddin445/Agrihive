// cartUtils.js

function showToast(message) {
  const toast = document.getElementById("toast");
  if (!toast) return;

  toast.textContent = message;
  toast.classList.add("show");

  setTimeout(() => {
    toast.classList.remove("show");
  }, 3000);
}

function addToCart(product) {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const alreadyInCart = cart.find(p => String(p.productId) === String(product.productId));

  if (!alreadyInCart) {
    cart.push(product);
    localStorage.setItem("cart", JSON.stringify(cart));
    showToast(`🛒 ${product.name} added to cart for ₹${product.pricePerKg.toFixed(2)}`);
  } else {
    showToast(`⚠️ ${product.name} is already in your cart`);
  }

  // Update the cart count
  updateCartCount();
}

function updateCartCount() {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const countElement = document.getElementById("cart-count");
  if (countElement) {
    countElement.textContent = cart.length;
  }
}

// Ensure cart count is updated when page loads
document.addEventListener("DOMContentLoaded", () => {
  updateCartCount();
});
