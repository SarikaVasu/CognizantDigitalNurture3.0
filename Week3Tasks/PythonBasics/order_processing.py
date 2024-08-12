def apply_discount(order_amount):
    final_price = order_amount;
    if(order_amount > 100):
        final_price -= (0.1*order_amount);
    return final_price;
    
order_amount = 150;
print(f"The final price for ${order_amount} is ${apply_discount(order_amount):.2f}");

