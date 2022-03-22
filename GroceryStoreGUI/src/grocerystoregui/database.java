/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grocerystoregui;
/**
 *
 * @author hoang
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class database {
    
    static Connection conn = null;
    
    public static void ConnectToDB(){
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            String user = "hnvo";
            String pass = "07086404";
            String url = "jdbc:oracle:thin:@oracle12c.scs.ryerson.ca:1521:orcl12c";
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connected with connection #1");
            }
        } 
        catch (ClassNotFoundException e){
            System.out.println("could not find the database driver " + e.getMessage());
        } 
        catch (SQLException e){
            System.out.println("could not connect to the database " + e.getMessage());
        }
    }
}