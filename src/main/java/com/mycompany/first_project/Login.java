package com.mycompany.first_project;

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

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    //MD5 Class
    MD5 md5 = new MD5();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
        String dbName = "root";
        String dbPassword = "";

        String uname = request.getParameter("txt_uname");
        String pwd = md5.getMd5(request.getParameter("txt_pwd"));
        String role = request.getParameter("rd_role");
        String btn = request.getParameter("btnLoginNoaccount");

        PreparedStatement stmt = null;

        PrintWriter out = response.getWriter();

        if (btn.equals("No_Account")) {
            response.sendRedirect("Registration_Page.jsp");
        }

        if (btn.equals("Login")) {
            if (role.equals("customer")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(dbUrl, dbName, dbPassword);

                    String InsertCustAddress = "SELECT * FROM cust_login_tb where Cust_ID=? AND Password=?";
                    stmt = con.prepareStatement(InsertCustAddress);
                    stmt.setString(1, uname);
                    stmt.setString(2, pwd);

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userName", uname);
                        response.sendRedirect("Customer_Page.jsp");
                        out.println("Successfull Customer");
                    } else {
                        response.sendRedirect("Login_Page.jsp");
                    }
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    out.println(e);
                }
            }
            if (role.equals("shopkeeper")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(dbUrl, dbName, dbPassword);

                    String InsertCustAddress = "SELECT * FROM shopkeeper_login_tb where Shopkeeper_ID=? AND Password=?";
                    stmt = con.prepareStatement(InsertCustAddress);
                    stmt.setString(1, uname);
                    stmt.setString(2, pwd);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userName", uname);
                        response.sendRedirect("Shopkeeper_Page.jsp");
//                        out.println("Successfull Shopkeeper");
                    } else {
                        response.sendRedirect("Login_Page.jsp");
                    }
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    out.println(e);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
