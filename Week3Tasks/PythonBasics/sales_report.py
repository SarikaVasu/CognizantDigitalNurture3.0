def generate_report(sales):
    print("Sales report")
    for amount in sales:
        if(amount > 500):
            print(f"Amount: ${amount} (Highlighted)")
        else:
            print(f"Amount: ${amount}")

# def total_sales(sales):
#     total_sales = 0;
#     for amount in sales:
#         total_sales += amount;
#     return total_sales;


sales_amount = [200, 600, 150, 800, 300];
generate_report(sales_amount);
# total_sales = total_sales(sales_amount);
total_sales = sum(sales_amount);
print(f"Teotal sales: ${total_sales}");