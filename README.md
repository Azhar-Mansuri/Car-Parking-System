# 🚗 Car Parking System (Java + JDBC + MySQL)

A Java-based car parking management system that handles dynamic slot allocation, entry/exit tracking, and real-time parking status, using JDBC and MySQL for persistent storage.

---

## 🧰 Tech Stack

- **Language**: Java  
- **Database**: MySQL  
- **Connectivity**: JDBC  
- **Architecture**: DAO Pattern (Data Access Object)

---

## 📌 Features

- ✅ Allocate and free parking slots dynamically  
- ✅ Tracks car entry and exit with timestamps  
- ✅ Displays real-time parking spot status  
- ✅ Persists data in MySQL — state survives even after app restart  
- ✅ Modular code with clear separation using DAO classes

---

## 🏗️ Project Structure

CarParkingSystem/
│
├── Car.java
├── ParkingSpot.java
├── ParkingLot.java
│
├── DAO/
│ ├── CarDAO.java
│ ├── ParkingSpotDAO.java
│ └── ParkingEventDAO.java
│
├── DB/
│ └── DBConnection.java
│
├── Main.java
└── README.md

🗄️ Database Tables

1. car 
CREATE TABLE car (
    register_num VARCHAR(20) PRIMARY KEY
);

2. parking_spot
CREATE TABLE parking_spot (
    spot_id INT PRIMARY KEY,
    isAvailable BOOLEAN,
    car_register_num VARCHAR(20),
    FOREIGN KEY (car_register_num) REFERENCES car(register_num)
);

3. parking_event
CREATE TABLE parking_event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_register_num VARCHAR(20),
    spot_id INT,
    entry_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    exit_time TIMESTAMP NULL,
    FOREIGN KEY (car_register_num) REFERENCES car(register_num),
    FOREIGN KEY (spot_id) REFERENCES parking_spot(spot_id)
);

▶️ How to Run
1.Clone the repo

git clone https://github.com/yourusername/CarParkingSystem.git
cd CarParkingSystem

2.Set up the MySQL database using the schema above

3.Update your DBConnection.java file with your MySQL credentials

4.Compile and run Main.java

📸 Screenshots


📚 What I Learned :
->JDBC integration with MySQL

->Applying the DAO design pattern

->Handling real-time application state

->Designing relational DB schemas for live systems

📬 Contact
Mohd Azhar Mansuri
📧 mansuriazhar13@gmail.com
🔗 LinkedIn https://www.linkedin.com/in/azhar-mansuri/

⭐ If you like this project, don’t forget to star the repo!

