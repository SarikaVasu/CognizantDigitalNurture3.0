def check_inventory(inventory):
    out_of_stock = [];
    for item, quantity in inventory:
        if quantity==0:
            out_of_stock.append(item)
    return out_of_stock;

items = [("item1", 10), ("item2", 0), ("item3", 5)];
out_of_stock = check_inventory(items);

if out_of_stock:
    print("Items out of stock: ")
    for item in out_of_stock:
        print(f" {item}")
else:
    print("no out of stock item found")
