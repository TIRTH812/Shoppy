package com.mycompany.first_project;

import com.mycompany.DAO.Shopkeeper_ManageProfile_implement;
import com.mycompany.model.Shopkeeper_ManageProfile;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ShopkeeperManageProfile", urlPatterns = {"/ShopkeeperManageProfile"})
public class ShopkeeperManageProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String fullName = request.getParameter("txt_name");
            String email = request.getParameter("txt_email");
            String address = request.getParameter("txt_address");
            String landmark = request.getParameter("txt_landmark");
            String country = request.getParameter("ddl_country");
            String state = request.getParameter("ddl_state");
            String city = request.getParameter("ddl_city");
            String pincode = request.getParameter("txt_pincode");
            String shopName = request.getParameter("txt_ShopName");
            String shopType = request.getParameter("ddlShopType");

            out.print(shopName);

            Shopkeeper_ManageProfile smp = new Shopkeeper_ManageProfile(fullName, email, address, landmark, country, state, city, pincode, shopName, shopType);

            Shopkeeper_ManageProfile_implement smp_impl = new Shopkeeper_ManageProfile_implement();

            smp_impl.updateShopkeeperProfile(smp, request, response);

            response.sendRedirect("Shopkeeper_Page.jsp");

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
