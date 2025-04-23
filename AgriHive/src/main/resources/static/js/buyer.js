const API_URL = "http://localhost:8080/api/products";

document.addEventListener("DOMContentLoaded", () => {
  const productGrid = document.getElementById("product-grid");
  const loadingSpinner = document.getElementById("loading-spinner");
  const cartItems = document.getElementById("cart-items");
  const categoryButtons = document.querySelectorAll(".category-btn");

  let allProducts = [];
  let cart = [];

  // Fetch products from API
  fetch(API_URL)
    .then(res => res.json())
    .then(data => {
      console.log(data); // Log API response to check the data structure
      allProducts = data.sort((a, b) => b.productId - a.productId); // Sort newest first
      renderProducts(allProducts);
    })
    .catch(err => {
      console.error("Error fetching products:", err);
      productGrid.innerHTML = "<p>Error loading products</p>";
    })
    .finally(() => {
      loadingSpinner.style.display = "none";
    });

  // Render products to the grid
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

  // Bind the add to cart buttons after rendering
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

  // Update the cart UI
  function updateCartUI() {
    cartItems.innerHTML = ""; // Clear existing items
    let total = 0;

    cart.forEach(item => {
      total += item.pricePerKg ?? 0;
      const li = document.createElement("li");

      // Create a link to the product details page
      const link = document.createElement("a");
      link.href = `product-details.html?id=${item.productId}`;
      link.textContent = `${item.name} - ₹${(item.pricePerKg ?? 0).toFixed(2)}`;

      // Append the link inside the list item
      li.appendChild(link);
      cartItems.appendChild(li);
    });

    const totalItem = document.createElement("li");
    totalItem.style.fontWeight = "bold";
    totalItem.textContent = `Total: ₹${total.toFixed(2)}`;
    cartItems.appendChild(totalItem);

    // Update cart count (optional: if you have a cart icon)
    const cartCountElement = document.getElementById('cart-count');
    if (cartCountElement) {
      cartCountElement.textContent = cart.length;
    }
  }

  // Category filter
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

  // Search filter for products
  window.filterProducts = function () {
    const searchTerm = document.getElementById("search-input").value.toLowerCase();
    const filtered = allProducts.filter(p =>
      p.name.toLowerCase().includes(searchTerm) ||
      (p.description && p.description.toLowerCase().includes(searchTerm))
    );
    renderProducts(filtered);
  };

  // Add product button for the owner page
  document.getElementById('add-product-btn').addEventListener('click', () => {
    window.location.href = './owner.html';
  });
});
