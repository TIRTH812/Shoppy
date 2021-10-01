package com.mycompany.first_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CustomerConnect", urlPatterns = {"/CustomerConnect"})
public class CustomerConnect extends HttpServlet {

    public static final String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    public static final String dbName = "root";
    public static final String dbPassword = "";

    Connection con = null;
    PreparedStatement stmt = null;
    HttpSession session = null;
    String sql = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbName, dbPassword);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        int shopID = Integer.parseInt(request.getParameter("shopID"));
        session = request.getSession();
        String customerID = (String) session.getAttribute("userName");

        // Executed when customer is not connected with shop and now he wants to connect with shop
        if ("N".equals(status)) 
        {
            try {
                sql = "INSERT INTO shop_cust_mapping_tb VALUES (?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, shopID);
                stmt.setString(2, customerID);
                stmt.setString(3, "P");
                stmt.executeUpdate();
            } catch (Exception e) {
                out.print(e);
            }
        }
        // Executed when customer has sended the request to shop and now he wants cancel his connection request
        if ("P".equals(status)) {
            try {
                sql = "DELETE FROM `shop_cust_mapping_tb` WHERE `Shop ID`=? AND `Customer ID`=?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, shopID);
                stmt.setString(2, customerID);
                stmt.executeUpdate();
            } catch (Exception e) {
                out.print(e);
            }
        }
        // Executed when customer is connected with shop and now he wants to disconnected from shop for any reason
        if ("C".equals(status)) {
            try {
                sql = "DELETE FROM `shop_cust_mapping_tb` WHERE `Shop ID`=? AND `Customer ID`=?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, shopID);
                stmt.setString(2, customerID);
                stmt.executeUpdate();
            } catch (Exception e) {
                out.print(e);
            }
        }
        
        con.close();
        
        request.setAttribute("shopID", shopID);
        RequestDispatcher rd = request.getRequestDispatcher("Customer_ShopInfo.jsp");
        rd.forward(request, response);
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
        } catch (SQLException ex) {
            Logger.getLogger(CustomerConnect.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(CustomerConnect.class.getName()).log(Level.SEVERE, null, ex);
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
