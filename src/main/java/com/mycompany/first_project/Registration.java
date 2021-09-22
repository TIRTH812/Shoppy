package com.mycompany.first_project;

import com.mycompany.DAO.Shopkeeper_reg_implement;
import com.mycompany.DAO.Customer_reg_implement;
import com.mycompany.model.Shopkeeper_registration;
import com.mycompany.model.Customer_registration;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {

    MD5 md5 = new MD5();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");

        String btn = request.getParameter("btnSubmitNext");

        PrintWriter out = response.getWriter();

        String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
        String dbName = "root";
        String dbPassword = "";

        //Customer Details
        String fullName = request.getParameter("txt_fname") + " " + request.getParameter("txt_mname") + " " + request.getParameter("txt_lname");
        String mobile = request.getParameter("txt_mobile");
        String email = request.getParameter("txt_email");
        String landmark = request.getParameter("txt_landmark");
        String pincode = request.getParameter("txt_pincode");
        String address = request.getParameter("txt_add");
        String country = request.getParameter("ddl_country");
        String state = request.getParameter("ddl_state");
        String city = request.getParameter("ddl_city");
        String pwd = md5.getMd5(request.getParameter("txt_pwd"));
        String cpwd = request.getParameter("txt_cpwd");

        PreparedStatement stmt = null;

        System.out.println("out----------------------------------------err");
        System.err.println("out----------------------------------------err");
        out.println("out----------------------------------------");

        //Keep in mind that the name of the both buttons must be same.
        if (btn.equals("submit")) {

            Customer_registration cust_reg = new Customer_registration(mobile, address, landmark, city, state, country, pincode, fullName, email, pwd);

            Customer_reg_implement cust_reg_impl = new Customer_reg_implement();

            int var = cust_reg_impl.insertCustomer(cust_reg);

            response.sendRedirect("Login_Page.jsp");

        }

        //-----------------------------------------------------------------------------------
        //shopkeeper
        if (btn.equals("next")) {

            Shopkeeper_registration shkp_reg = new Shopkeeper_registration(fullName, mobile, email, pwd);

            Shopkeeper_reg_implement shkp_reg_impl = new Shopkeeper_reg_implement();

            int var = shkp_reg_impl.insertShopkeeper(shkp_reg);

            try {
                HttpSession session = request.getSession();
                session.setAttribute("userName", mobile);
                response.sendRedirect("addShop_Page.jsp");
            } catch (Exception e) {
                out.println(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
