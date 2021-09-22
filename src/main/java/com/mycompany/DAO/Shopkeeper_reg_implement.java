package com.mycompany.DAO;

import com.mycompany.model.Shopkeeper_registration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class Shopkeeper_reg_implement implements db_connection {

    //Connection con = null;
    PreparedStatement stmt = null;
    //Shopkeeper_registration shopkeeperReg;
    public static final String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    public static final String dbName = "root";
    public static final String dbPassword = "";

    HttpSession session = null;

    @Override
    public Connection getConnection() {
       Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbName, dbPassword);

            System.out.println("Connection established successfully........");

        } 
        catch (Exception ex) {}

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

    public int insertShopkeeper(Shopkeeper_registration shopkeeperReg) {

        Connection con = getConnection();

        int return_value = 0;

        String sqlInsertShopkeeperInfo = "INSERT INTO shopkeeper_info_tb VALUES (?,?,?)";

        String InsertShopkeeperLogin = "INSERT INTO shopkeeper_login_tb (Shopkeeper_ID, Password) VALUES (?,?)";

        try {
            stmt = con.prepareStatement(sqlInsertShopkeeperInfo);

            stmt.setString(1, shopkeeperReg.getMobile());
            stmt.setString(2, shopkeeperReg.getFullName());
            stmt.setString(3, shopkeeperReg.getEmail());
            int countInsert = stmt.executeUpdate();

            stmt = con.prepareStatement(InsertShopkeeperLogin);
            stmt.setString(1, shopkeeperReg.getMobile());
            stmt.setString(2, shopkeeperReg.getPwd());
            int countInsert2 = stmt.executeUpdate();
            //out.println(countInsert2 + " records affected.\n");

            return_value = (countInsert > 0) && (countInsert2 > 0) ? 1 : 0;

            closeConnection();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Shopkeeper_reg_implement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return return_value;
    }

    public int deleteShopkeeper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int updateShopkeeper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
