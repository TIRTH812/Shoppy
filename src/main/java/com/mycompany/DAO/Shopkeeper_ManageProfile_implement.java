package com.mycompany.DAO;

import com.mycompany.model.Shopkeeper_ManageProfile;
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

public class Shopkeeper_ManageProfile_implement implements db_connection {

    PreparedStatement stmt = null;
    //Shopkeeper_registration shopkeeperReg;

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

    public int updateShopkeeperProfile(Shopkeeper_ManageProfile smp, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection con = getConnection();

        PrintWriter out = response.getWriter();
        out.println("Inside update shopkeeper profile method");
        out.println(smp.getShopName());
        int return_value = 0;

        String sqlUpdateShopkeeperInfo = "UPDATE `shopkeeper_info_tb` SET `Full Name`=?, `Email ID`=?   WHERE `Shopkeeper ID`=? ";
        String sqlUpdateShopAddress = "UPDATE `shop_addr_tb` SET `Society Name`=?, `Landmark`=?, `City`=?, `State`=?, `Country`=?, `Pin Code`=? WHERE `Sh_Addr_ID`= ?  ";
        String sqlUpdateShopInfo = "UPDATE `shop_info_tb` SET `Shop Name` = ?, `Shop Type` = ? WHERE `Shopkeeper_ID` = ?";

        session = request.getSession();//error

        try {
            stmt = con.prepareStatement(sqlUpdateShopkeeperInfo);
            stmt.setString(1, smp.getFullName());
            stmt.setString(2, smp.getEmail());
            stmt.setString(3, session.getAttribute("userName").toString());
            int countUpdate1 = stmt.executeUpdate();

            stmt = con.prepareStatement(sqlUpdateShopAddress);
            stmt.setString(1, smp.getAddress());
            stmt.setString(2, smp.getLandmark());
            stmt.setString(3, smp.getCity());
            stmt.setString(4, smp.getState());
            stmt.setString(5, smp.getCountry());
            stmt.setString(6, smp.getPincode());
            stmt.setString(7, session.getAttribute("userName").toString());
            int countUpdate2 = stmt.executeUpdate();

            stmt = con.prepareStatement(sqlUpdateShopInfo);
            stmt.setString(1, smp.getShopName());
            stmt.setString(2, smp.getShopType());
            stmt.setString(3, session.getAttribute("userName").toString());
            int countUpdate3 = stmt.executeUpdate();

            return_value = (countUpdate1 > 0) && (countUpdate2 > 0) && (countUpdate3 > 0) ? 1 : 0;

            closeConnection();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Shopkeeper_reg_implement.class.getName()).log(Level.SEVERE, null, ex);
            out.println("\nInside Catch\n" + ex + "\n");
            ex.printStackTrace();

        }

        return return_value;
    }

}//end of class 
