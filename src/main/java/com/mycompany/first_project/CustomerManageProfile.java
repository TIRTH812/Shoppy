package com.mycompany.first_project;

import com.mycompany.DAO.Customer_ManageProfile_implement;
import com.mycompany.DAO.Shopkeeper_ManageProfile_implement;
import com.mycompany.model.Customer_ManageProfile;
import com.mycompany.model.Shopkeeper_ManageProfile;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerManageProfile", urlPatterns = {"/CustomerManageProfile"})
public class CustomerManageProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {


            String fullName = request.getParameter("txt_name");
            String email = request.getParameter("txt_email");
            String address = request.getParameter("txt_address");
            String landmark = request.getParameter("txt_landmark");
            String country = request.getParameter("ddl_country");
            String state = request.getParameter("ddl_state");
            String city = request.getParameter("ddl_city");
            String pincode = request.getParameter("txt_pincode");
            
            //out.print(shopName);

            Customer_ManageProfile cmp = new Customer_ManageProfile(fullName, email, address, landmark, country, state, city, pincode);

            Customer_ManageProfile_implement cmp_impl = new Customer_ManageProfile_implement();

            cmp_impl.updateCustomerProfile(cmp, request, response);

            response.sendRedirect("Customer_Page.jsp");


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
