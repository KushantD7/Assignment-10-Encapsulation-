import java.util.ArrayList;

// Abstract class Vehicle
abstract class Vehicle {
    private String vehicleNumber;  // Encapsulation: private fields
    private String type;
    private double rentalRate;

    // Constructor
    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    // Getters and Setters
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getRentalRate() { return rentalRate; }
    public void setRentalRate(double rentalRate) { this.rentalRate = rentalRate; }

    // Abstract method
    public abstract double calculateRentalCost(int days);
}

// Insurable interface
interface Insurable {
    double calculateInsurance();
    void getInsuranceDetails();
}

// Car class
class Car extends Vehicle implements Insurable {
    private String policyNumber;  // Encapsulation: sensitive info

    public Car(String vehicleNumber, double rentalRate, String policyNumber) {
        super(vehicleNumber, "Car", rentalRate);
        this.policyNumber = policyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days; // Simple cost = rate * days
    }

    @Override
    public double calculateInsurance() {
        return 2000; // Flat insurance rate for cars
    }

    @Override
    public void getInsuranceDetails() {
        System.out.println("Car Insurance Policy: [PROTECTED]");
    }
}

// Bike class
class Bike extends Vehicle implements Insurable {
    private String policyNumber;

    public Bike(String vehicleNumber, double rentalRate, String policyNumber) {
        super(vehicleNumber, "Bike", rentalRate);
        this.policyNumber = policyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return (getRentalRate() * days) - 50; // Small discount on bikes
    }

    @Override
    public double calculateInsurance() {
        return 500; // Cheaper insurance
    }

    @Override
    public void getInsuranceDetails() {
        System.out.println("Bike Insurance Policy: [PROTECTED]");
    }
}

// Truck class
class Truck extends Vehicle implements Insurable {
    private String policyNumber;

    public Truck(String vehicleNumber, double rentalRate, String policyNumber) {
        super(vehicleNumber, "Truck", rentalRate);
        this.policyNumber = policyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days + 1000; // Extra handling fee
    }

    @Override
    public double calculateInsurance() {
        return 5000; // Expensive insurance
    }

    @Override
    public void getInsuranceDetails() {
        System.out.println("Truck Insurance Policy: [PROTECTED]");
    }
}

// Main Vehicle Rental System
public class VehicleRentalSystem {
    public static void main(String[] args) {
        ArrayList<Vehicle> rentals = new ArrayList<>();

        Vehicle car = new Car("CAR123", 1500, "CAR-INS-001");
        Vehicle bike = new Bike("BIKE456", 500, "BIKE-INS-002");
        Vehicle truck = new Truck("TRUCK789", 3000, "TRUCK-INS-003");

        rentals.add(car);
        rentals.add(bike);
        rentals.add(truck);

        int rentalDays = 5;

        // Polymorphism: Vehicle reference → subclass objects
        for (Vehicle v : rentals) {
            System.out.println("Vehicle Number: " + v.getVehicleNumber());
            System.out.println("Type: " + v.getType());
            System.out.println("Rental Rate (per day): " + v.getRentalRate());

            double rentalCost = v.calculateRentalCost(rentalDays);
            System.out.println("Rental Cost for " + rentalDays + " days: " + rentalCost);

            if (v instanceof Insurable) {
                Insurable ins = (Insurable) v;
                System.out.println("Insurance Cost: " + ins.calculateInsurance());
                ins.getInsuranceDetails();
            }

            System.out.println("-----------------------------");
        }
    }
}
