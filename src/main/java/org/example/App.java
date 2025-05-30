package org.example;

import java.util.Scanner;


class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(5);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Car Parking System Menu ---");
            System.out.println("1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. View Parking Status");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter car registration number: ");
                    String regNum = scanner.nextLine();
                    Car car = new Car(regNum.trim());
                    CarDAO.addCar(car);
                    parkingLot.parkCar(car);
                    break;

                case 2:
                    System.out.print("Enter car registration number to remove: ");
                    String regToRemove = scanner.nextLine();
                    parkingLot.removeCar(regToRemove.trim());
                    break;

                case 3:
                    System.out.println("\nCurrent Parking Status:\n");
                    parkingLot.displayParkingStatus();
                    break;

                case 4:
                    running = false;
                    System.out.println("\nExiting... Thank you!");
                    break;

                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
