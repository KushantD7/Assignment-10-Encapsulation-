import java.util.ArrayList;
import java.util.List;

// Abstract class
abstract class Patient {
    private int patientId;
    private String name;
    private int age;

    // Encapsulated sensitive details
    private String diagnosis;
    private List<String> medicalHistory = new ArrayList<>();

    // Constructor
    public Patient(int patientId, String name, int age, String diagnosis) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    // Getters (encapsulation)
    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }

    // Secured access to sensitive data
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public List<String> getMedicalHistory() { return medicalHistory; }

    // Concrete method
    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Diagnosis: " + diagnosis);
    }

    // Abstract method
    public abstract double calculateBill();
}

// Interface
interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

// InPatient Class
class InPatient extends Patient implements MedicalRecord {
    private int daysAdmitted;
    private double roomChargePerDay;

    public InPatient(int patientId, String name, int age, String diagnosis, int daysAdmitted, double roomChargePerDay) {
        super(patientId, name, age, diagnosis);
        this.daysAdmitted = daysAdmitted;
        this.roomChargePerDay = roomChargePerDay;
    }

    @Override
    public double calculateBill() {
        return daysAdmitted * roomChargePerDay; // Billing logic
    }

    @Override
    public void addRecord(String record) {
        getMedicalHistory().add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical Records of " + getName() + ": " + getMedicalHistory());
    }
}

// OutPatient Class
class OutPatient extends Patient implements MedicalRecord {
    private double consultationFee;

    public OutPatient(int patientId, String name, int age, String diagnosis, double consultationFee) {
        super(patientId, name, age, diagnosis);
        this.consultationFee = consultationFee;
    }

    @Override
    public double calculateBill() {
        return consultationFee; // Fixed consultation fee
    }

    @Override
    public void addRecord(String record) {
        getMedicalHistory().add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical Records of " + getName() + ": " + getMedicalHistory());
    }
}

// Main Class
public class HospitalPatientManagement {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();

        Patient p1 = new InPatient(101, "Aman", 30, "Pneumonia", 5, 2000);
        Patient p2 = new OutPatient(102, "Adarsh", 25, "Fever", 500);

        // Adding patients
        patients.add(p1);
        patients.add(p2);

        // Adding medical records
        ((MedicalRecord) p1).addRecord("Admitted with Pneumonia");
        ((MedicalRecord) p1).addRecord("Prescribed Antibiotics");

        ((MedicalRecord) p2).addRecord("Visited for fever");
        ((MedicalRecord) p2).addRecord("Prescribed Paracetamol");

        // Processing patients using polymorphism
        for (Patient p : patients) {
            System.out.println("\n--- Patient Details ---");
            p.getPatientDetails();
            System.out.println("Total Bill: Rs." + p.calculateBill());
            ((MedicalRecord) p).viewRecords();
            System.out.println("-----------------------------");
        }
    }
}
