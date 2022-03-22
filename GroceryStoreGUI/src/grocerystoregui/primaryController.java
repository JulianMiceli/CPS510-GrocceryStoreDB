/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grocerystoregui;

import static grocerystoregui.database.conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class primaryController {
    static Connection conn = null;
    @FXML
    private Button help;
    @FXML
    private Text message;
    @FXML
    private Button exit;
    @FXML
    private Button back;
    @FXML
    private Button drop;
    @FXML
    private Button create;
    @FXML
    private Button populate;
    @FXML
    private Button queries;

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void helpAction(ActionEvent event) {
        message.setText("Click the following buttons and the results will be displayed.");
    }
    
    //When the "CREATE" button is clicked on the GUI it should create all tables from our database
    @FXML
    void createAction(ActionEvent event) {
        database.ConnectToDB();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE customers("+
            "CustomerID NUMBER,"+
            "CustomerName VARCHAR(25),"+
            "Membership NUMBER(1),"+
            "PRIMARY KEY(CustomerID)"+
            ");");
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE registered("+
            "CustomerID NUMBER,"+
            "MemberID NUMBER,"+
            "EMail VARCHAR2(25),"+
            "Phone NUMBER UNIQUE,"+
            "Address VARCHAR2(25) NOT NULL,"+
            "PRIMARY KEY(CustomerID,MemberID),"+
            "FOREIGN KEY(CustomerID) REFERENCES customers (CustomerID)"+
            ");");
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE inventory("+
            "ProductID NUMBER,"+
            "ProductName VARCHAR2(25) NOT NULL,"+
            "Quantity NUMBER NOT NULL,"+
            "Price NUMBER NOT NULL,"+
            "Weight NUMBER,"+    
            "Department VARCHAR2(25) NOT NULL,"+    
            "PRIMARY KEY(ProductID)"+
            ");");
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        } 
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE transactions("+
            "TransactionID NUMBER,"+
            "Total NUMBER NOT NULL,"+
            "Products VARCHAR2(255),"+
            "PRIMARY KEY(CustomerID,TransactionID),"+
            "FOREIGN KEY(CustomerID) REFERENCES customers (CustomerID)"+
            ");" );
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE Employee("+
            "EmployeeID NUMBER,"+
            "EmployeeName VARCHAR2(25),"+
            "SIN NUMBER UNIQUE NOT NULL,"+
            "Schedule_Hours VARCHAR(25) NOT NULL,"+
            "PAYRATE NUMBER NOT NULL,"+
            "Address VARCHAR2(25) NOT NULL,"+
            "PRIMARY KEY(EmployeeID,EmployeeName)"+
            ");");
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE Manager("+
            "EmployeeID NUMBER,"+
            "Name VARCHAR2(25),"+
            "PRIMARY KEY(EmployeeID,Name),"+
            "FOREIGN KEY(EmployeeID,Name) REFERENCES Employee(EmployeeID,EmployeeName)"+
            ");");
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE Cashier("+
            "EmployeeID NUMBER,"+
            "Name VARCHAR2(25),"+
            "PRIMARY KEY(EmployeeID,Name),"+
            "FOREIGN KEY(EmployeeID,Name) REFERENCES Employee(EmployeeID,EmployeeName)"+
            ");");
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE Stocker("+
            "EmployeeID NUMBER,"+
            "Name VARCHAR2(25),"+
            "PRIMARY KEY(EmployeeID,Name),"+
            "FOREIGN KEY(EmployeeID,Name) REFERENCES Employee(EmployeeID,EmployeeName)"+
            ");");
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //When the "POPULATE" button is clicked on the GUI it should populate all of our tables in out database
    @FXML
    void populateAction(ActionEvent event) {
        message.setText("2");
        database.ConnectToDB();
        //Populating Customers
        String[] arr = {
            "INSERT INTO customers VALUES (1,'Hayaan',1)",
            "INSERT INTO customers VALUES (2,'Julian',1)",
            "INSERT INTO customers VALUES (3,'Hoang',1)",
            "INSERT INTO customers VALUES (4,'Hoang Vo',0)",
            "INSERT INTO customers VALUES (69,'Deez Nutz',0)",
            "INSERT INTO customers VALUES (5,'Person1',1)",
            "INSERT INTO customers VALUES (6,'Person2',1)",
            "INSERT INTO customers VALUES (7,'Person3',1)",
            "INSERT INTO customers VALUES (8,'Person4',1)",
            "INSERT INTO customers VALUES (9,'Person5',1)"
        };
        String[] attr = new String[20];
        String temp = "";
        for(int a = 0; a < arr.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        } 
        //Populating Registered
        String[] arr2 = {
            "INSERT INTO registered VALUES (1,1,'hayaan@gmail.com',911,'?')",
            "INSERT INTO registered VALUES (2,2,'julian@gmail.com',912,'?')",
            "INSERT INTO registered VALUES (3,69,'hoang@gmail.com',913,'?')",
            "INSERT INTO registered VALUES (5,3,'p1@gmail.com',914,'?')",
            "INSERT INTO registered VALUES (6,4,'p2@gmail.com',915,'?')",
            "INSERT INTO registered VALUES (7,5,'p3@gmail.com',916,'?')",
            "INSERT INTO registered VALUES (8,6,'p4@gmail.com',917,'?')",
            "INSERT INTO registered VALUES (9,7,'p5@gmail.com',918,'?')"
        };
        attr = new String[20];
        temp = "";
        for(int a = 0; a < arr2.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr2[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        }
        //Populating Inventory
        String[] arr3 = {
            "INSERT INTO inventory VALUES(1,'Apples',69,1.99,66.123,'Produce')",
            "INSERT INTO inventory VALUES(2,'Oranges',69,7.99,43.123,'Produce')",
            "INSERT INTO inventory VALUES(3,'Mangos',69,10.99,89.123,'Produce')",
            "INSERT INTO inventory VALUES(4,'Bananas',69,2.99,77.123,'Produce')"
        };
        attr = new String[20];
        temp = "";
        for(int a = 0; a < arr3.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr3[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        }
        //Populating Transactions
        String[] arr4 = {
            "INSERT INTO transactions VALUES(4,1190283,78.97,'List of stuff')"
        };
        attr = new String[20];
        temp = "";
        for(int a = 0; a < arr4.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr4[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        }
        //Populating Employees
        String[] arr5 = {
            "INSERT INTO Employee VALUES (1,'Hayaan',696969,'Full-Time,8 Hours',15,'?')",
            "INSERT INTO Employee VALUES (3,'Hoang',66669,'Full-Time,8 Hours',15,'?')",
            "INSERT INTO Employee VALUES (5,'Julian',69669,'Full-Time,8 Hours',15,'?')",
            "INSERT INTO Employee VALUES (9,'Hoang Vo',6966969,'Full-Time,8 Hours',15,'?')",
            "INSERT INTO Employee VALUES (69,'Deez',6696969,'Full-Time,8 Hours',15,'?')"
        };
        attr = new String[20];
        temp = "";
        for(int a = 0; a < arr5.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr5[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        }
        //Populating Managers
        String[] arr6 = {
            "INSERT INTO Manager VALUES (1,'Hayaan')",
            "INSERT INTO Manager VALUES (3,'Hoang')",
            "INSERT INTO Manager VALUES (5,'Julian')"
        };
        attr = new String[20];
        temp = "";
        for(int a = 0; a < arr6.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr6[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        }
        //Populating Stockers
        String[] arr7 = {
            "INSERT INTO Cashier VALUES (9,'Hoang Vo')"
        };
        attr = new String[20];
        temp = "";
        for(int a = 0; a < arr7.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr7[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        }
        //Populating Cashiers
        String[] arr8 = {
            "INSERT INTO Stocker VALUES (69,'Deez')"
        };
        attr = new String[20];
        temp = "";
        for(int a = 0; a < arr8.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr8[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        }
    }
    
    //When the "QUERIES" button is clicked on the GUI it should execute all query commands
    @FXML
    void queriesAction(ActionEvent event) {
        message.setText("3");
        database.ConnectToDB();
        String[] arr = {
            "SELECT * FROM customers",
            "SELECT * FROM registered",
            "SELECT * FROM Employee",
            "SELECT * FROM Manager",
            "SELECT * FROM Cashier",
            "SELECT * FROM Stocker",
            "SELECT * FROM customers WHERE CustomerName = 'Hoang Vo'",
            "SELECT * FROM registered WHERE Address = '?'",
            "SELECT MemberID, CustomerID FROM registered",
            "SELECT * FROM registered WHERE MemberID = 1",
            "SELECT * FROM Employee WHERE EmployeeID = 1",
            "SELECT * FROM Employee WHERE EmployeeID <= 4",
            "SELECT customers.CustomerName, customers.CustomerID, registered.MemberID FROM customers, registered WHERE (customers.CustomerID = registered.CustomerID) AND registered.MemberID >0",
            "SELECT customers.CustomerName, customers.CustomerID, registered.MemberID FROM customers, registered WHERE (customers.CustomerID = registered.CustomerID) AND registered.MemberID <6",
            "SELECT customers.CustomerName, registered.MemberID FROM customers, registered WHERE (customers.CustomerID = registered.CustomerID) AND registered.MemberID <6 ORDER BY CustomerName DESC", 
            "SELECT customers.CustomerName, customers.CustomerID FROM customers WHERE (Membership = 0) ORDER BY CustomerID DESC",
            "SELECT ProductName,Weight,Price FROM inventory WHERE (Quantity > 0) AND (Weight <= 67.69) ORDER BY Weight DESC",
            "SELECT Employee.EmployeeName, Employee.EmployeeID, Employee.PAYRATE FROM Employee, Manager WHERE (Employee.EmployeeName = Manager.Name) ORDER BY Manager.Name DESC"
        };
        String[] attr = new String[20];
        String temp = "";
        for(int a = 0; a < arr.length; a++ ){
            try{
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(arr[a]);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cn = rsmd.getColumnCount();
                while (rs.next()) {
                    for(int i = 0; i<cn; i++){
                        attr[i] = rs.getString(i+1).toString();
                    }
                }
                rs.close();
            } 
            catch (Exception e){
                e.printStackTrace();
            } 
        } 
    }
    
    //When the "DROP" button is clicked on the GUI it should drop all tables from our database
    @FXML
    void dropAction(ActionEvent event) {
        message.setText("4");
        database.ConnectToDB();
	try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Drop Table transactions");    
            rs.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Drop Table registered");    
            rs.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Drop Table customers");    
            rs.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Drop Table inventory");    
            rs.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Drop Table Manager");    
            rs.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Drop Table Cashier");    
            rs.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Drop Table Stocker");    
            rs.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Drop Table Employee");    
            rs.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


