import java.util.ArrayList;

// Abstract class Product
abstract class Product {
    private int productId;   // Encapsulation: private fields
    private String name;
    private double price;

    // Constructor
    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Abstract method
    public abstract double calculateDiscount();
}

// Taxable interface
interface Taxable {
    double calculateTax();
    void getTaxDetails();
}

// Electronics class
class Electronics extends Product implements Taxable {
    private double warrantyFee;

    public Electronics(int productId, String name, double price, double warrantyFee) {
        super(productId, name, price);
        this.warrantyFee = warrantyFee;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.10;  // 10% discount
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.18; // 18% GST
    }

    @Override
    public void getTaxDetails() {
        System.out.println("Tax Category: Electronics (18%)");
    }
}

// Clothing class
class Clothing extends Product implements Taxable {
    private String size;

    public Clothing(int productId, String name, double price, String size) {
        super(productId, name, price);
        this.size = size;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.20; // 20% discount
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.05; // 5% GST
    }

    @Override
    public void getTaxDetails() {
        System.out.println("Tax Category: Clothing (5%)");
    }
}

// Groceries class
class Groceries extends Product {
    private double weight;

    public Groceries(int productId, String name, double price, double weight) {
        super(productId, name, price);
        this.weight = weight;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.05; // 5% discount
    }

    // Groceries are tax-free (no Taxable implemented)
}

// Main E-Commerce Platform
public class ECommercePlatform {
    // Polymorphic method to calculate final price
    public static void printFinalPrice(Product product) {
        double discount = product.calculateDiscount();
        double tax = 0;

        if (product instanceof Taxable) {
            Taxable t = (Taxable) product;
            tax = t.calculateTax();
            t.getTaxDetails();
        } else {
            System.out.println("Tax Category: Groceries (No Tax)");
        }

        double finalPrice = product.getPrice() + tax - discount;

        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Name: " + product.getName());
        System.out.println("Base Price: " + product.getPrice());
        System.out.println("Discount: " + discount);
        System.out.println("Tax: " + tax);
        System.out.println("Final Price: " + finalPrice);
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        ArrayList<Product> cart = new ArrayList<>();

        Product p1 = new Electronics(101, "Laptop", 60000, 2000);
        Product p2 = new Clothing(102, "T-Shirt", 1000, "L");
        Product p3 = new Groceries(103, "Rice Bag", 1200, 10);

        cart.add(p1);
        cart.add(p2);
        cart.add(p3);

        // Process each product polymorphically
        for (Product p : cart) {
            printFinalPrice(p);
        }
    }
}
