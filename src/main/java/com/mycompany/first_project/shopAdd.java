package com.mycompany.first_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "shopAdd", urlPatterns = {"/shopAdd"})
public class shopAdd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("hello");

        String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
        String dbName = "root";
        String dbPassword = "";

        String shopName = request.getParameter("txtShopName");
        String shopType = request.getParameter("ddlShopType");
        String landmark = request.getParameter("txt_landmark");
        String pincode = request.getParameter("txt_pincode").toString();
        String address = request.getParameter("txt_add");
        String country = request.getParameter("ddl_country");
        String state = request.getParameter("ddl_state");
        String city = request.getParameter("ddl_city");
        String btnAdd = request.getParameter("btnAdd");
        String item = null;

        out.print(shopName + shopType + btnAdd);

        PreparedStatement stmt = null;

        HttpSession session = request.getSession();
        String mobile = session.getAttribute("userName").toString();

        out.print(mobile);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbName, dbPassword);

            out.print("Hey There");

            String InsertCustAddress = "INSERT INTO shop_addr_tb VALUES (?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(InsertCustAddress);
            stmt.setString(1, mobile);
            stmt.setString(2, address);
            stmt.setString(3, landmark);
            stmt.setString(4, city);
            stmt.setString(5, state);
            stmt.setString(6, country);
            stmt.setString(7, pincode);
            int countInsert = stmt.executeUpdate();
            out.println(countInsert + " records affected.\n");

            String InsertCustAddress1 = "INSERT INTO `shop_info_tb`(Shopkeeper_ID, `Shop Name`, `Shop Type`, Shop_addr_ID, Items) VALUES (?,?,?,?,?)";
            stmt = con.prepareStatement(InsertCustAddress1);
            stmt.setString(1, mobile);
            stmt.setString(2, shopName);
            stmt.setString(3, shopType);
            stmt.setString(4, mobile);
            stmt.setString(5, item);

            int countInsert1 = stmt.executeUpdate();
            out.println(countInsert1 + " records affected.\n");

            stmt.close();
            con.close();

            response.sendRedirect("Shopkeeper_Page.jsp");
        } catch (Exception e) {
            out.println(e);
            e.printStackTrace();
        }
        /*finally
        {
            response.sendRedirect("Shopkeeper_Page.jsp");
        }
         */

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
