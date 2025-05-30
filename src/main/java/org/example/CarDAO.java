package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarDAO {
    public static void addCar(Car car){
        String addCarQuery = "Insert into car(register_num) values (?);";
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addCarQuery)){
            preparedStatement.setString(1,car.getRegistrationNo());
            int rows = preparedStatement.executeUpdate();
            if(rows == 0){
                System.out.println("Car was not Added !");
            }else{
                System.out.println("Car was Added SUCCESSFULLY !");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
