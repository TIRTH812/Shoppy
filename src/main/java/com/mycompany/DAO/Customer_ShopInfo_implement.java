package com.mycompany.DAO;

import com.mycompany.model.Customer_ShopInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Customer_ShopInfo_implement implements db_connection
{
    public static final String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    public static final String dbName = "root";
    public static final String dbPassword = "";

    PreparedStatement stmt = null;
    HttpSession session = null;
    String sql = null;
    
    @Override
    public Connection getConnection() 
    {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbName, dbPassword);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    @Override
    public void closeConnection() 
    {    
        try {
            stmt.close();
            //con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void insert(Customer_ShopInfo csi, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException 
    {
        PrintWriter out = response.getWriter();
        Connection con = getConnection();
        try {
            sql = "INSERT INTO shop_cust_mapping_tb VALUES (?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, csi.getShopID());
            stmt.setString(2, csi.getCutomerID());
            stmt.setString(3, "P");
            stmt.executeUpdate();
        } catch (Exception e) {
            out.print(e);
        }
        con.close();
    }
    
    public void delete(Customer_ShopInfo csi, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException
    {
        PrintWriter out = response.getWriter();
        Connection con = getConnection();
        try {
            sql = "DELETE FROM `shop_cust_mapping_tb` WHERE `Shop ID`=? AND `Customer ID`=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, csi.getShopID());
            stmt.setString(2, csi.getCutomerID());
            stmt.executeUpdate();
        } catch (Exception e) {
            out.print(e);
        }
        con.close();
    }
}