import json
import time
import threading

class Inventory:
    def __init__(self):
        self.items = {}  #items and stock lvls

    def add_item(self, item_name,  quantity):
        if item_name in self.items:
            self.items[item_name] += quantity
        else:
            self.items[item_name] = quantity

    def remove_item(self, item_name, quantity):
        if item_name in self.items:
            if self.items[item_name] >= quantity:
                self.items[item_name] -= quantity
                if self.items[item_name] == 0:
                    del self.items[item_name]
            else:
                print(f"Not enough stock to remove {quantity} of {item_name}.")
        else:
            print(f"{item_name} not found in inventory.")
    
    def check_stock(self, item_name):
        return self.items.get(item_name, 0)

    def save_to_file(self, filename):
        try:
            with open(filename, 'w') as file:
                json.dump(self.items, file)
        except IOError as err:
            print(f"Error saving to file: {e}")

    def load_from_file(self, filename):
        try:
            with open(filename, 'r') as file:
                self.items = json.load(file)
        except IOError as err:
            print(f"Error loading from file: {e}")
        except json.JSONDecodeError as err:
            print(f"Error deconding JSON from file: {e}")

    def check_stock_periodically(self, interval, threshold):
        def check():
            while True:
                time.sleep(interval)
                for item, stock in self.items.items():
                    if stock <= threshold:
                        print(f"Alert: {item} is low on stock (only {stock} left)")
        thread = threading.Thread(target = check, daemon = True)
        thread.start()


if __name__ == "__main__":
    # Create an inventory instance
    inventory = Inventory()
    
    # Add some items
    inventory.add_item('Apples', 50)
    inventory.add_item('Bananas', 20)
    inventory.add_item('Oranges', 10)
    
    # Save the inventory to a file
    inventory.save_to_file('inventory.json')
    
    # Load the inventory from the file
    new_inventory = Inventory()
    new_inventory.load_from_file('inventory.json')
    
    # Print the loaded inventory
    print("Loaded Inventory:")
    for item, stock in new_inventory.items.items():
        print(f"{item}: {stock}")
    
    # Check stock levels periodically
    # This will run indefinitely; adjust the interval and threshold as needed
    new_inventory.check_stock_periodically(interval=5, threshold=15)
    
    # Allow some time for periodic checks to occur
    time.sleep(20)
        


    
