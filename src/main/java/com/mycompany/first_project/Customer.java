package com.mycompany.first_project;

import static com.mycompany.DAO.Shopkeeper_reg_implement.dbName;
import static com.mycompany.DAO.Shopkeeper_reg_implement.dbPassword;
import static com.mycompany.DAO.Shopkeeper_reg_implement.dbUrl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Customer", urlPatterns = {"/Customer"})
public class Customer extends HttpServlet {

    public static final String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    public static final String dbName = "root";
    public static final String dbPassword = "";

    PreparedStatement stmt = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");

        String btnLogout = request.getParameter("btnLogout");

        if (btnLogout.equals("logout")) {
            HttpSession session = request.getSession();
            session.removeAttribute("userName");
            session.invalidate();
            response.sendRedirect("Login_Page.jsp");
        }

        if (btnLogout.equals("Show list")) {
            Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(dbUrl, dbName, dbPassword);
                //out.println("Connection established successfully........");
                String query = "SELECT `shop_info_tb`.`Shop Name`, `shop_info_tb`.`Items` FROM `shop_addr_tb` JOIN `cust_addr_tb` ON `shop_addr_tb`.`Pin Code` = `cust_addr_tb`.`Pin code` JOIN `shop_info_tb` ON `shop_info_tb`.`Shop_addr_ID` = `shop_addr_tb`.`Sh_Addr_ID` WHERE `cust_addr_tb`.`Cust_Addr_ID` = '1001'";
                //out.println(query);
                stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    out.println(rs.getString(1));
                    out.println(rs.getString(2));//json
                }

                stmt.close();
                con.close();
            } catch (Exception ex) {

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
        processRequest(request, response);
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
        processRequest(request, response);
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
