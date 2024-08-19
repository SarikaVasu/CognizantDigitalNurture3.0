class Employee:
    def __init__(self, name, hours_worked, hourly_rate):
        self.name = name
        self.hours_worked = hours_worked
        self.hourly_rate = hourly_rate

    def calculate_pay(self):
        pay = 0;
        if(self.hours_worked > 40):
            pay = 40 * self.hourly_rate
            pay += (self.hours_worked - 40) * self.hourly_rate * 1.5
        else:
            pay = self.hours_worked * self.hourly_rate
        return pay
    
class Manager(Employee):
    def __init__(self, name, hours_worked, hourly_rate, bonus):
        self.bonus = bonus
        super().__init__(name, hours_worked, hourly_rate)
    def calculate_pay(self):
        return super().calculate_pay() + self.bonus

employee = Employee(name="Alice", hours_worked=45, hourly_rate=20)
manager = Manager(name="Bob", hours_worked=50, hourly_rate=30, bonus=500)

print(f"Employee {employee.name} total pay: ${employee.calculate_pay():.2f}")
print(f"Manager {manager.name} total pay: ${manager.calculate_pay():.2f}")