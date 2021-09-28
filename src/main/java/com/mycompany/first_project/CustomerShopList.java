
package com.mycompany.first_project;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerShopList", urlPatterns = {"/CustomerShopList"})
public class CustomerShopList extends HttpServlet {

        //String id = session.getAttribute("userName").toString();

    String dbURL = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    String dbUid = "root";
    String dbPwd = "";

  

    Connection connection = null;
    PreparedStatement pstatement = null;
    ResultSet resultset = null;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
           
           out.println("Hello");
           
           int id = Integer.parseInt(request.getParameter("id"));
           
            try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUid, dbPwd);

            System.out.println("Connection established successfully........");
    
            String sql = "SELECT `shopkeeper_info_tb`.`Shopkeeper ID`, `shopkeeper_info_tb`.`Full Name`, `shop_addr_tb`.`Society Name`, `shop_addr_tb`.`Landmark`, `shop_addr_tb`.`City`, `shop_info_tb`.`Shop Name`, `shop_info_tb`.`Shop Type`, `shop_info_tb`.`Items` FROM `shopkeeper_info_tb` JOIN `shop_addr_tb` ON `shop_addr_tb`.`Sh_Addr_ID` = `shopkeeper_info_tb`.`Shopkeeper ID` JOIN `shop_info_tb` ON `shop_info_tb`.`Shopkeeper_ID` = `shopkeeper_info_tb`.`Shopkeeper ID` WHERE `shop_info_tb`.`Shop ID` = ?" ;
            
            pstatement.setInt(1,id);  
            
            pstatement = connection.prepareStatement(sql);
            
            resultset = pstatement.executeQuery();
            
            resultset.next();            
            
            request.setAttribute("shopkeeperMobile", resultset.getString("Shopkeeper ID"));
            request.setAttribute("shopkeeperName", resultset.getString("Full Name"));
            request.setAttribute("shopAddress", resultset.getString("Society Name"));
            request.setAttribute("shopLandmark", resultset.getString("Landmark"));
            request.setAttribute("shopCity", resultset.getString("City"));
            request.setAttribute("shopName", resultset.getString("Shop Name"));
            request.setAttribute("shopType", resultset.getString("shop Type"));
            request.setAttribute("shopItems", resultset.getString("Items"));
            
            
            
            
            connection.close();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
           
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerShopList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerShopList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
