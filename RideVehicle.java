import java.util.ArrayList;
import java.util.List;

// Abstract class RideVehicle
abstract class RideVehicle {
    private int vehicleId;
    private String driverName;
    private double ratePerKm;

    public RideVehicle(int vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
    }

    // Encapsulation - Getters
    public int getVehicleId() { return vehicleId; }
    public String getDriverName() { return driverName; }
    public double getRatePerKm() { return ratePerKm; }

    // Concrete method
    public void showVehicleDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Driver Name: " + driverName);
        System.out.println("Rate per Km: Rs." + ratePerKm);
    }

    // Abstract method
    public abstract double calculateFare(double distance);
}

// GPS Interface
interface GPSModule {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

// Car class
class RideCar extends RideVehicle implements GPSModule {
    private String currentLocation;

    public RideCar(int vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = currentLocation;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
}

// Bike class
class RideBike extends RideVehicle implements GPSModule {
    private String currentLocation;

    public RideBike(int vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = currentLocation;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm() * 0.9;
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
}

// Auto class
class RideAuto extends RideVehicle implements GPSModule {
    private String currentLocation;

    public RideAuto(int vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = currentLocation;
    }

    @Override
    public double calculateFare(double distance) {
        double fare = distance * getRatePerKm();
        return fare < 50 ? 50 : fare;
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
}

// Main Class
public class RideHailingService {
    public static void processRides(List<RideVehicle> vehicles, double distance) {
        for (RideVehicle v : vehicles) {
            System.out.println("\n--- Vehicle Details ---");
            v.showVehicleDetails();
            System.out.println("Estimated Fare for " + distance + " km: Rs." + v.calculateFare(distance));

            GPSModule gps = (GPSModule) v;
            System.out.println("Current Location: " + gps.getCurrentLocation());
        }
    }

    public static void main(String[] args) {
        List<RideVehicle> vehicles = new ArrayList<>();

        RideVehicle car = new RideCar(101, "Aman", 15, "Connaught Place");
        RideVehicle bike = new RideBike(102, "Raj", 10, "Karol Bagh");
        RideVehicle auto = new RideAuto(103, "Adarsh", 12, "India Gate");

        vehicles.add(car);
        vehicles.add(bike);
        vehicles.add(auto);

        processRides(vehicles, 10); // Calculate fare for 10 km ride
    }
}
