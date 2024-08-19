product_names = ["Laptop", "Smartphone", "Tablet"]

products = {
    "Laptop": {"quantity": 10, "price": 1200},
    "Smartphone": {"quantity": 50, "price": 800},
    "Tablet": {"quantity": 30, "price": 500}
}

# Tuples representing immutable product data
product_catalog = (
    ("Laptop", 1200),
    ("Smartphone", 800),
    ("Tablet", 500)
)

# Set to track unique product categories
product_categories = {"Electronics", "Home Appliances", "Gadgets"}

#list
def add_product(product_name):
    if product_name not in product_names:
        product_names.append(product_name)
    else:
        print(f"{product_name} is already in the list.")

def remove_product(product_name):
    if product_name in product_names:
        product_names.remove(product_name)
    else:
        print(f"{product_name} not found in the list.")

def update_product(old_name, new_name):
    if old_name in product_names:
        index = product_names.index(old_name)
        product_names[index] = new_name
    else:
        print(f"{old_name} not found in the list.")

# //dictionary
def add_product_detail(name, quantity, price):
    if name not in products:
        products[name] = {"quantity": quantity, "price": price}
    else:
        print(f"{name} already exists in the inventory.")

def update_product_detail(name, quantity=None, price=None):
    if name in products:
        if quantity is not None:
            products[name]["quantity"] = quantity
        if price is not None:
            products[name]["price"] = price
    else:
        print(f"{name} not found in the inventory.")

def delete_product_detail(name):
    if name in products:
        del products[name]
    else:
        print(f"{name} not found in the inventory.")


# //tuples
def display_catalog():
    for product in product_catalog:
        print(f"Product: {product[0]}, Price: {product[1]}")

#set
def add_category(category):
    product_categories.add(category)

def remove_category(category):
    if category in product_categories:
        product_categories.remove(category)
    else:
        print(f"Category '{category}' not found.")


# Function to generate a report of products sorted by price
def sorted_product_report():
    # print(products.items())
    sorted_products = sorted(products.items(), key=lambda x: x[1]["price"])
    for product, details in sorted_products:
        print(f"Product: {product}, Price: {details['price']}, Quantity: {details['quantity']}")

# Function to find products within a certain price range
def find_products_in_price_range(min_price, max_price):
    products_in_range = {name for name, details in products.items() if min_price <= details["price"] <= max_price}
    return products_in_range

print(find_products_in_price_range(200, 1000))
print(sorted_product_report())