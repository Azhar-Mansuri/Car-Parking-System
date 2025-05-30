package org.example;

import java.sql.*;

public class ParkingSpotDAO {

    public static void initializeParkingSpot(int spotID) {
        String query = "INSERT INTO parking_spot (spot_id, isAvailable) VALUES (?, ?);";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, spotID);
            preparedStatement.setBoolean(2, true);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                System.out.println("Parking spot initialization failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void assignCarToSpot(String carRegisterNum, int spotID) {
        String query = "UPDATE parking_spot SET car_register_num = ?, isAvailable = ? WHERE spot_id = ?;";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, carRegisterNum);
            preparedStatement.setBoolean(2, false);
            preparedStatement.setInt(3, spotID);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                System.out.println("Occupying spot failed!");
            }
        } catch (SQLException e){
            if (e.getErrorCode() == 1062) { // Duplicate entry
                System.out.println("Car already exists.");
            } else {
                e.printStackTrace();
            }
        }

    }

    public static void removeCarFromParking(int spotID) {
        String query = "UPDATE parking_spot SET car_register_num = ?, isAvailable = ? WHERE spot_id = ?;";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setNull(1, Types.VARCHAR); // Correct way to null a VARCHAR
            preparedStatement.setBoolean(2, true);
            preparedStatement.setInt(3, spotID);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                System.out.println("Removing car from parking failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean spotExists(int spotID) {
        String query = "SELECT spot_id FROM parking_spot WHERE spot_id = ?";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, spotID);
            return preparedStatement.executeQuery().next(); // true if exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ParkingSpot getSpotFromDatabase(int spotID){
        String query = "SELECT * FROM parking_spot WHERE spot_id = ?";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, spotID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String  regNum = resultSet.getString("car_register_num");
                ParkingSpot parkingSpot = new ParkingSpot(spotID);
                if (regNum != null) {
                    Car car = new Car(regNum);
                    parkingSpot.occupy(car); // this will set isAvailable = false
                }
                return parkingSpot;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    return null;
    }

}
