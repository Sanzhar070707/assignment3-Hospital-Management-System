package controller;

import model.Patient;
import model.Doctor;
import model.BaseEntity;
import service.PatientService;
import service.DoctorService;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import utils.SortingUtils;
import utils.ReflectionUtils;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Show All Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Add Doctor");
            System.out.println("6. Show All Doctors");
            System.out.println("7. Update Doctor");
            System.out.println("8. Delete Doctor");
            System.out.println("9. Demo: SOLID Principles");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter patient first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter patient last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter birth date (YYYY-MM-DD): ");
                        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
                        System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();

                        Patient p = new Patient(0, firstName, lastName, birthDate, phone);
                        patientService.addPatient(p);
                        System.out.println("Patient added successfully.");
                    }
                    case 2 -> patientService.getAllPatients().forEach(Patient::printInfo);
                    case 3 -> {
                        System.out.print("Enter patient ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter new last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter new birth date (YYYY-MM-DD): ");
                        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
                        System.out.print("Enter new phone: ");
                        String phone = scanner.nextLine();

                        Patient updated = new Patient(id, firstName, lastName, birthDate, phone);
                        patientService.updatePatient(id, updated);
                        System.out.println("Patient updated successfully.");
                    }
                    case 4 -> {
                        System.out.print("Enter patient ID to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        patientService.deletePatient(id);
                        System.out.println("Patient deleted successfully.");
                    }
                    case 5 -> {
                        System.out.print("Enter doctor first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter doctor last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter specialization: ");
                        String specialization = scanner.nextLine();
                        System.out.print("Enter hire date (YYYY-MM-DD): ");
                        LocalDate hireDate = LocalDate.parse(scanner.nextLine());

                        Doctor d = new Doctor(0, firstName, lastName, specialization, hireDate);
                        doctorService.addDoctor(d);
                        System.out.println("Doctor added successfully.");
                    }
                    case 6 -> doctorService.getAllDoctors().forEach(Doctor::printInfo);
                    case 7 -> {
                        System.out.print("Enter doctor ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter new last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter new specialization: ");
                        String specialization = scanner.nextLine();
                        System.out.print("Enter new hire date (YYYY-MM-DD): ");
                        LocalDate hireDate = LocalDate.parse(scanner.nextLine());

                        Doctor updated = new Doctor(id, firstName, lastName, specialization, hireDate);
                        doctorService.updateDoctor(id, updated);
                        System.out.println("Doctor updated successfully.");
                    }
                    case 8 -> {
                        System.out.print("Enter doctor ID to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        doctorService.deleteDoctor(id);
                        System.out.println("Doctor deleted successfully.");
                    }
                    case 9 -> demoSolid(patientService, doctorService);
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (InvalidInputException | ResourceNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void demoSolid(PatientService patientService, DoctorService doctorService) {
        System.out.println("\n=== SOLID Demo ===");

        List<BaseEntity> entities = new ArrayList<>();
        entities.addAll(patientService.getAllPatients());
        entities.addAll(doctorService.getAllDoctors());

        System.out.println("→ Polymorphic printInfo:");
        for (BaseEntity entity : entities) {
            entity.printInfo();
        }

        System.out.println("\n→ Sorted by name:");
        SortingUtils.sortList(entities, Comparator.comparing(BaseEntity::getName));
        for (BaseEntity entity : entities) {
            System.out.println(entity.getName());
        }

        System.out.println("\n→ Reflection on 9Patient.class:");
        ReflectionUtils.printClassInfo(Patient.class);
    }
}