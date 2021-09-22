package com.mycompany.DAO;

import com.mycompany.model.Customer_registration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer_reg_implement implements db_connection {

    PreparedStatement stmt = null;
    //Shopkeeper_registration shopkeeperReg;

    public static final String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    public static final String dbName = "root";
    public static final String dbPassword = "";

    @Override
    public Connection getConnection() {

        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbName, dbPassword);

            System.out.println("Connection established successfully........");
        } catch (Exception ex) {
        }

        return con;
    }

    @Override
    public void closeConnection() {
        try {
            stmt.close();
            //con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int insertCustomer(Customer_registration CustomerReg) {

        Connection con = getConnection();

        int return_value = 0;

        String InsertCustAddress = "INSERT INTO cust_addr_tb VALUES (?,?,?,?,?,?,?)";
        String InsertCustLogin = "INSERT INTO `cust_login_tb`(Cust_ID, Password) VALUES (?,?)";
        String InsertCustInfo = "INSERT INTO customer_info_tb VALUES (?,?,?,?)";

        try {
            //address
            stmt = con.prepareStatement(InsertCustAddress);
            stmt.setString(1, CustomerReg.getMobile());
            stmt.setString(2, CustomerReg.getAddress());
            stmt.setString(3, CustomerReg.getLandmark());
            stmt.setString(4, CustomerReg.getCity());
            stmt.setString(5, CustomerReg.getState());
            stmt.setString(6, CustomerReg.getCountry());
            stmt.setString(7, CustomerReg.getPincode());
            int countInsert = stmt.executeUpdate();

            //customer info
            stmt = con.prepareStatement(InsertCustInfo);
            stmt.setString(1, CustomerReg.getMobile());
            stmt.setString(2, CustomerReg.getFullname());
            stmt.setString(3, CustomerReg.getEmail());
            stmt.setString(4, CustomerReg.getMobile());
            int countInsert1 = stmt.executeUpdate();

            //customer login
            stmt = con.prepareStatement(InsertCustLogin);
            stmt.setString(1, CustomerReg.getMobile());
            stmt.setString(2, CustomerReg.getPwd());
            int countInsert2 = stmt.executeUpdate();

            return_value = (countInsert > 0) && (countInsert1 > 0) && (countInsert2 > 0) ? 1 : 0;

            closeConnection();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Customer_reg_implement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return return_value;
    }

    public int deleteCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int updateCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
