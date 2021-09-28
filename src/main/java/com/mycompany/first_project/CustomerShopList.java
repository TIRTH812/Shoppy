package com.mycompany.first_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet(name = "CustomerShopList", urlPatterns = {"/CustomerShopList"})
public class CustomerShopList extends HttpServlet 
{
    String dbURL = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    String dbUid = "root";
    String dbPwd = "";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int id = Integer.parseInt(request.getParameter("id"));

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, dbUid, dbPwd);

            String sql = "SELECT `shopkeeper_info_tb`.`Shopkeeper ID`, `shopkeeper_info_tb`.`Full Name`, `shop_addr_tb`.`Society Name`, `shop_addr_tb`.`Landmark`, `shop_addr_tb`.`City`, `shop_info_tb`.`Shop Name`, `shop_info_tb`.`Shop Type`, `shop_info_tb`.`Items` FROM `shopkeeper_info_tb` JOIN `shop_addr_tb` ON `shop_addr_tb`.`Sh_Addr_ID` = `shopkeeper_info_tb`.`Shopkeeper ID` JOIN `shop_info_tb` ON `shop_info_tb`.`Shopkeeper_ID` = `shopkeeper_info_tb`.`Shopkeeper ID` WHERE `shop_info_tb`.`Shop ID` = ?" ;
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);  
            rs = pstmt.executeQuery();
            rs.next();            

            request.setAttribute("shopkeeperMobile", rs.getString("Shopkeeper ID"));
            request.setAttribute("shopkeeperName", rs.getString("Full Name"));
            request.setAttribute("shopAddress", rs.getString("Society Name"));
            request.setAttribute("shopLandmark", rs.getString("Landmark"));
            request.setAttribute("shopCity", rs.getString("City"));
            request.setAttribute("shopName", rs.getString("Shop Name"));
            request.setAttribute("shopType", rs.getString("shop Type"));
            
            String json = rs.getString("Items");
            JSONArray array = new JSONArray();
            if(json != null)
            {
                JSONParser jp = new JSONParser();
                Object ob = jp.parse(json);

                JSONObject jsonObj = (JSONObject) ob;
                array = (JSONArray) jsonObj.get("Items");
            }
            request.setAttribute("jArray", array);
            
            rs.close();
            pstmt.close();
            con.close();
            
            RequestDispatcher rd = request.getRequestDispatcher("Customer_ShopInfo.jsp");
            rd.forward(request, response);
        } 
        catch (Exception ex) 
        {
            out.print(ex);
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
