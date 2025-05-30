package org.example;

import java.sql.*;
import java.time.LocalDateTime;

public class ParkingEventDAO {

    public static void logEntry(String carRegisterNum, int spotId) {
        String query = "INSERT INTO parking_event (car_register_num, spot_id, entry_time) VALUES (?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, carRegisterNum);
            ps.setInt(2, spotId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logExit(String carRegisterNum) {
        String query = "UPDATE parking_event SET exit_time = ? " +
                "WHERE car_register_num = ? AND exit_time IS NULL";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(2, carRegisterNum);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printParkingDuration(String carReg) {
        String query = "SELECT entry_time, exit_time FROM parking_event " +
                "WHERE car_register_num = ? ORDER BY id DESC LIMIT 1";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, carReg);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Timestamp entry = rs.getTimestamp("entry_time");
                Timestamp exit = rs.getTimestamp("exit_time");

                if (exit != null) {
                    long minutes = (exit.getTime() - entry.getTime()) / 60000;
                    System.out.println("Parked duration: " + minutes + " minutes");
                } else {
                    System.out.println("Car is still parked.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
