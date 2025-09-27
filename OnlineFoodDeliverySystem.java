import java.util.ArrayList;

abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    
    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Invalid quantity. Must be greater than 0.");
        }
    }

    public void getItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Price per item: Rs." + price);
        System.out.println("Quantity: " + quantity);
    }

    public abstract double calculateTotalPrice();
}

interface Discountable {
    double applyDiscount(double amount);
    void getDiscountDetails();
}

class VegItem extends FoodItem implements Discountable {
    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity(); // No extra charge for veg
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * 0.90; // 10% discount
    }

    @Override
    public void getDiscountDetails() {
        System.out.println("Veg Discount Applied: 10%");
    }
}

class NonVegItem extends FoodItem implements Discountable {
    private double nonVegCharge = 50; // Extra charge

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return (getPrice() * getQuantity()) + nonVegCharge; // Extra charge applied
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * 0.85; // 15% discount
    }

    @Override
    public void getDiscountDetails() {
        System.out.println("Non-Veg Discount Applied: 15%");
    }
}

public class OnlineFoodDeliverySystem {
    public static void processOrder(ArrayList<FoodItem> orderList) {
        System.out.println("\n--- Processing Order ---");
        double grandTotal = 0;

        for (FoodItem item : orderList) {
            item.getItemDetails();
            double totalPrice = item.calculateTotalPrice();
            System.out.println("Total Price before discount: Rs." + totalPrice);

            if (item instanceof Discountable) {
                Discountable d = (Discountable) item;
                totalPrice = d.applyDiscount(totalPrice);
                d.getDiscountDetails();
            }

            System.out.println("Final Price after discount: Rs." + totalPrice);
            System.out.println("----------------------------");

            grandTotal += totalPrice;
        }

        System.out.println("Grand Total of Order: Rs." + grandTotal);
    }

    public static void main(String[] args) {
        ArrayList<FoodItem> order = new ArrayList<>();

        FoodItem item1 = new VegItem("Paneer Butter Masala", 200, 2);
        FoodItem item2 = new NonVegItem("Chicken Biryani", 300, 1);
        FoodItem item3 = new VegItem("Veg Burger", 100, 3);

        order.add(item1);
        order.add(item2);
        order.add(item3);

        processOrder(order);
    }
}
