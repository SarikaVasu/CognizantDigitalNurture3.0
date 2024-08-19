from abc import ABC, abstractmethod

class DiscountStrategy(ABC):
    @abstractmethod
    def apply_discount(self, order_amount):
        pass

class RegularDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.95;
        
class PremiumDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.90;

class VIPDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.85;

class Order:
    def __init__ (self, customer_type, order_amount):
        self.customer_type = customer_type
        self.order_amount = order_amount
        self.discount_strategy = self._get_discount_strategy()

    def _get_discount_strategy(self):
        strategies = {
            "regular": RegularDiscount(),
            "premium": PremiumDiscount(),
            "vip": VIPDiscount()
        }
        return strategies.get(self.customer_type, RegularDiscount())

    def final_price(self):
        return self.discount_strategy.apply_discount(self.order_amount)

order_regular = Order(customer_type='regular', order_amount=100)
order_premium = Order(customer_type='premium', order_amount=200)
order_vip = Order(customer_type='vip', order_amount=300)

print(f"Regular customer final price: ${order_regular.final_price():.2f}")
print(f"Premium customer final price: ${order_premium.final_price():.2f}")
print(f"VIP customer final price: ${order_vip.final_price():.2f}")