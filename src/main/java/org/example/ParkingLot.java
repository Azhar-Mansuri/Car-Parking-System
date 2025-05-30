package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    List<ParkingSpot> spot;
    public ParkingLot(int capacity) {
        spot = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            if (!ParkingSpotDAO.spotExists(i)) {
                ParkingSpotDAO.initializeParkingSpot(i);
                spot.add(new ParkingSpot(i)); // It's new, so initialize with default
            } else {
                ParkingSpot dbSpot = ParkingSpotDAO.getSpotFromDatabase(i); // Fetch real data
                spot.add(dbSpot); // Use the actual DB values (available status, car if any)
            }
        }
    }

    public void parkCar(Car car){
        for(ParkingSpot parkingSpot : spot){
            if(parkingSpot.isAvailable()){
                parkingSpot.occupy(car);
                ParkingSpotDAO.assignCarToSpot(car.getRegistrationNo(), parkingSpot.getParkingNo());
                ParkingEventDAO.logEntry(car.getRegistrationNo(), parkingSpot.getParkingNo());
                System.out.println("Your can park your car "+car.getRegistrationNo()+" at parking number : "+parkingSpot.getParkingNo());
                return;
            }
        }
        System.out.println("Parking is Full !");
    }
    public void removeCar(String registrationNo) {
        for (ParkingSpot parkingSpot : spot) {
            if (parkingSpot.getCar() != null &&
                parkingSpot.getCar().getRegistrationNo().equals(registrationNo)) {

                parkingSpot.freeParkingSpot(parkingSpot.getCar());
                ParkingSpotDAO.removeCarFromParking(parkingSpot.getParkingNo());
                ParkingEventDAO.logExit(registrationNo);
                System.out.println("Car " + registrationNo + " is removed from parking number: " + parkingSpot.getParkingNo());
                ParkingEventDAO.printParkingDuration(registrationNo);
                return;
            }
        }
        System.out.println("No such car found in parking!");
    }

    public void displayParkingStatus() {
        for (ParkingSpot spot : spot) {
            String status = spot.isAvailable() ? "Available" : "Occupied by " + spot.getCar().getRegistrationNo();
            System.out.println("Spot " + spot.getParkingNo() + ": " + status);
        }
    }
}
