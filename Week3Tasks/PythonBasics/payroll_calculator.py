def calculate_pay(hours, rate):
    return hours*rate;

# hours = int(input("Enter hours worked"));
# rate = int(input("Enter hourly rate"));
hours = 40;
rate = 15
total_pay = calculate_pay(hours, rate);
# print("Total pay", total_pay, " for employee who worked ", hours, " hours and ", rate, "rate");
print(f"Total pay for employee is: ${total_pay:.2f}");

