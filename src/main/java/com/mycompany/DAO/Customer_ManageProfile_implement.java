package com.mycompany.DAO;

import com.mycompany.model.Customer_ManageProfile;
import com.mycompany.model.Customer_ManageProfile;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Customer_ManageProfile_implement implements db_connection{

    PreparedStatement stmt = null;
    //Customer_registration shopkeeperReg;

    HttpSession session = null;

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

        }
        catch (Exception ex) {
            ex.printStackTrace();
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

public int updateCustomerProfile(Customer_ManageProfile cmp, HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        Connection con = getConnection();

        PrintWriter out = response.getWriter();
        out.println("Inside update Customer profile method");
        
        int return_value = 0;

        String sqlUpdateCustomerInfo = "UPDATE `customer_info_tb` SET `Full Name`=?, `Email ID`=?   WHERE `Customer ID`=? ";
        String sqlUpdateShopAddress = "UPDATE `cust_addr_tb` SET `Society Name`=?, `Landmark`=?, `City`=?, `State`=?, `Country`=?, `Pin Code`=? WHERE `Cust_Addr_ID`= ?  ";

        session = request.getSession();//error

        try {
            stmt = con.prepareStatement(sqlUpdateCustomerInfo);
            stmt.setString(1, cmp.getFullName());
            stmt.setString(2, cmp.getEmail());
            stmt.setString(3, session.getAttribute("userName").toString());
            int countUpdate1 = stmt.executeUpdate();

            stmt = con.prepareStatement(sqlUpdateShopAddress);
            stmt.setString(1, cmp.getAddress());
            stmt.setString(2, cmp.getLandmark());
            stmt.setString(3, cmp.getCity());
            stmt.setString(4, cmp.getState());
            stmt.setString(5, cmp.getCountry());
            stmt.setString(6, cmp.getPincode());
            stmt.setString(7, session.getAttribute("userName").toString());
            int countUpdate2 = stmt.executeUpdate();

            
            return_value = ( (countUpdate1 > 0) && (countUpdate2 > 0) ) ? 1 : 0;

            closeConnection();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Customer_reg_implement.class.getName()).log(Level.SEVERE, null, ex);
            out.println("\nInside Catch\n" + ex + "\n");
            ex.printStackTrace();

        }

        return return_value;

    }
  
}
