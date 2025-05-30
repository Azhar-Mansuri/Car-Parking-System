package org.example;

public class ParkingSpot {
    private final int parkingNo;
    private boolean isAvailable;
    private Car car;

    public ParkingSpot(int parkingNo){
        this.parkingNo = parkingNo;
        this.isAvailable = true;
    }

    public void occupy(Car car){
        this.car = car;
        isAvailable = false;
    }

    public void freeParkingSpot(Car car){
        this.car = null;
        isAvailable = true;
    }

    public Car getCar() {
        return car;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getParkingNo() {
        return parkingNo;
    }
}
