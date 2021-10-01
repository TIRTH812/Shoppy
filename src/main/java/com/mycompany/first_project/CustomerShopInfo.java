package com.mycompany.first_project;

import com.mycompany.DAO.Customer_ShopInfo_implement;
import com.mycompany.model.Customer_ShopInfo;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "CustomerShopInfo", urlPatterns = {"/CustomerShopInfo"})
public class CustomerShopInfo extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String status = request.getParameter("status");
        int shopID = Integer.parseInt(request.getParameter("shopID"));
        HttpSession session = null;
        session = request.getSession();
        String customerID = (String) session.getAttribute("userName");

        Customer_ShopInfo csi = new Customer_ShopInfo(shopID, customerID, status);
        Customer_ShopInfo_implement csi_impl = new Customer_ShopInfo_implement();
        
        if ("N".equals(status)) 
        {
            csi_impl.insert(csi, request, response);
        }
        if ("P".equals(status)) 
        {
            csi_impl.delete(csi, request, response);
        }
        if ("C".equals(status)) 
        {
            csi_impl.delete(csi, request, response);
        }
        
        request.setAttribute("shopID", shopID);
        RequestDispatcher rd = request.getRequestDispatcher("Customer_ShopInfo.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerShopInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerShopInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }
}