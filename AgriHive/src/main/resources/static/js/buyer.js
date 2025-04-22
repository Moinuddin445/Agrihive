const API_URL = "http://localhost:8080/api/products";

document.addEventListener("DOMContentLoaded", () => {
  const productGrid = document.getElementById("product-grid");
  const loadingSpinner = document.getElementById("loading-spinner");
  const cartItems = document.getElementById("cart-items");
  const categoryButtons = document.querySelectorAll(".category-btn");

  let allProducts = [];
  let cart = [];

  fetch(API_URL)
    .then(res => res.json())
    .then(data => {
      allProducts = data.sort((a, b) => b.productId - a.productId); // newest first
      renderProducts(allProducts);
    })
    .catch(err => {
      console.error("Error fetching products:", err);
      productGrid.innerHTML = "<p>Error loading products</p>";
    })
    .finally(() => {
      loadingSpinner.style.display = "none";
    });

  function renderProducts(products) {
    productGrid.innerHTML = "";
    if (products.length === 0) {
      productGrid.innerHTML = "<p>No products found.</p>";
      return;
    }

    products.forEach(product => {
      const card = document.createElement("div");
      card.className = "product-card";

      const imageUrl = `http://localhost:8080/Pimages/${product.imageName}`;

      card.innerHTML = `
        <img src="${imageUrl}" alt="${product.name}">
        <h3>${product.name}</h3>
        <p>${product.description}</p>
        <p>₹${(product.pricePerKg ?? 0).toFixed(2)}</p>
        <button class="add-to-cart" data-id="${product.productId}">Add to Cart</button>
        <a href="product-details.html?id=${product.productId}" class="view-details-btn">View Details</a>
      `;

      productGrid.appendChild(card);
    });

    // Re-bind click listeners after rendering
    bindAddToCartButtons();
  }

  function bindAddToCartButtons() {
    const buttons = document.querySelectorAll(".add-to-cart");
    buttons.forEach(button => {
      button.addEventListener("click", () => {
        const productId = parseInt(button.dataset.id);
        const product = allProducts.find(p => p.productId === productId);
        if (product) {
          cart.push(product);
          updateCartUI();
        }
      });
    });
  }

  categoryButtons.forEach(button => {
    button.addEventListener("click", () => {
      categoryButtons.forEach(btn => btn.classList.remove("active"));
      button.classList.add("active");

      const category = button.dataset.category;
      if (category === "all") {
        renderProducts(allProducts);
      } else {
        const filtered = allProducts.filter(p =>
          p.category && p.category.toLowerCase() === category.toLowerCase()
        );
        renderProducts(filtered);
      }
    });
  });

  window.filterProducts = function () {
    const searchTerm = document.getElementById("search-input").value.toLowerCase();
    const filtered = allProducts.filter(p =>
      p.name.toLowerCase().includes(searchTerm) ||
      (p.description && p.description.toLowerCase().includes(searchTerm))
    );
    renderProducts(filtered);
  };
});

function updateCartUI() {
  const cartItems = document.getElementById("cart-items");
  cartItems.innerHTML = "";
  let total = 0;

  cart.forEach(item => {
    total += item.pricePerKg ?? 0;
    const li = document.createElement("li");
    li.textContent = `${item.name} - ₹${(item.pricePerKg ?? 0).toFixed(2)}`;
    cartItems.appendChild(li);
  });

  const totalItem = document.createElement("li");
  totalItem.style.fontWeight = "bold";
  totalItem.textContent = `Total: ₹${total.toFixed(2)}`;
  cartItems.appendChild(totalItem);
}