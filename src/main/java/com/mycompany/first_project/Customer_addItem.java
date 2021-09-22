package com.mycompany.first_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//@WebServlet(name = "Customer_addItem", urlPatterns = {"/Customer_addItem"})
@WebServlet(name = "Customer_addItem", urlPatterns = {"/"})
public class Customer_addItem extends HttpServlet {

    private final String dbUrl = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    private final String dbName = "root";
    private final String dbPassword = "";
    int temp = 0;

    static int autoIncr = 1;

    Statement stmt;
    PreparedStatement stmt1;

    JSONArray objList = new JSONArray();

    HttpSession session = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getServletPath();

        //out.print(action);
        session = request.getSession();

        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertProduct(request, response);
                break;
            case "/delete":
                deleteProduct(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            case "/display":
                display(request, response);
                break;
            default:
                //display(request, response);
                break;
        }

    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // TODO Auto-generated catch block
        return connection;
    }

    private void display(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, ServletException, IOException {
        Connection con = getConnection();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String InsertCustAddress = "SELECT * FROM shop_info_tb WHERE `Shopkeeper_ID`=?";
        stmt1 = con.prepareStatement(InsertCustAddress);
        stmt1.setString(1, session.getAttribute("userName").toString());

        ResultSet rs = stmt1.executeQuery();

        String json = null;

        while (rs.next()) {
            json = rs.getString("Items");
        }

        JSONParser jp = new JSONParser();
        Object ob = jp.parse(json);

        JSONObject jsonObj = (JSONObject) ob;
        JSONArray array = (JSONArray) jsonObj.get("Items");

        request.setAttribute("jArray", array);
        RequestDispatcher rd = request.getRequestDispatcher("Cust_item_list.jsp");
        rd.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        temp = 0;
        RequestDispatcher rd = request.getRequestDispatcher("Cust_item_form.jsp");
        request.setAttribute("temp", temp);
        rd.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ParseException, ServletException {
        PrintWriter out = response.getWriter();

        Connection con = getConnection();
        String InsertCustAddress = "SELECT * FROM shop_info_tb WHERE `Shopkeeper_ID`=?";
        stmt1 = con.prepareStatement(InsertCustAddress);
        stmt1.setString(1, session.getAttribute("userName").toString());//static value
        ResultSet rs = stmt1.executeQuery();

        String json = null;
        while (rs.next()) {
            json = rs.getString("Items");
//            out.print("Json :"+json+"<br><br>");
        }

        JSONParser jp = new JSONParser();
        Object ob = jp.parse(json);
        JSONObject jsonObj = (JSONObject) ob;
        JSONArray array = (JSONArray) jsonObj.get("Items");

        JSONObject jObj = null;
        String maxId = "" + 0;
        //auto-increment logic
        if (array.size() == 0) {

        } else {
            jObj = (JSONObject) array.get(array.size() - 1);
            maxId = jObj.get("ID").toString();
        }

        //out.println(maxId + ", ");
        if (autoIncr == 1) {
            autoIncr = Integer.parseInt(maxId);

        }

        //String id = request.getParameter("id");
        String id;
        //id = String.valueOf(autoIncr);
        id = "" + (++autoIncr);
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String unit = request.getParameter("ddl_item_unit");
        String quantity = request.getParameter("item_qnt").toString();
        String category = request.getParameter("ddl_item_ctg");
        //out.print(" Your Entered Data :<br> ID: "+id+" Name: "+name+" Price: "+price+"<br><br>");

        JSONObject obj = new JSONObject();
        obj.put("ID", id);
        obj.put("Name", name);
        obj.put("Price", price);
        obj.put("Unit", unit);
        obj.put("Quantity", quantity);
        obj.put("Category", category);
        array.add(obj);
        JSONObject objMain = new JSONObject();
        objMain.put("Items", array);

//        out.print(" JSON after: "+objMain.toJSONString()+"<br><br>");
//        out.print("Total Data After Insertion<br>");
        for (int i = 0; i < array.size(); i++) {
            JSONObject items = (JSONObject) array.get(i);
            String jID = (String) items.get("ID");
            String jName = (String) items.get("Name");
            String jPrice = (String) items.get("Price");
            String jUnit = (String) items.get("Unit");
            String jQuantity = (String) items.get("Quantity");
            String jCategory = (String) items.get("Category");
//            out.println("ID: "+jID+" Name: "+jName+" Price: "+jPrice+" <br>");
        }

        String sql1 = "UPDATE shop_info_tb SET items='" + objMain.toJSONString() + "' WHERE `Shopkeeper_ID`= " + session.getAttribute("userName").toString();//
        stmt = con.createStatement();
        stmt.executeUpdate(sql1);

        request.setAttribute("jArray", array);
        RequestDispatcher rd = request.getRequestDispatcher("Cust_item_list.jsp");
        rd.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ParseException, ServletException {
        PrintWriter out = response.getWriter();
        String deleteID = request.getParameter("id");
//        out.print("ID: "+a);

        Connection con = getConnection();
        String InsertCustAddress = "SELECT * FROM shop_info_tb WHERE `Shopkeeper_ID`=?";
        stmt1 = con.prepareStatement(InsertCustAddress);
        stmt1.setString(1, session.getAttribute("userName").toString());
        ResultSet rs = stmt1.executeQuery();

        String json = null;
        while (rs.next()) {
            json = rs.getString("Items");
        }

        JSONParser jp = new JSONParser();
        Object ob = jp.parse(json);
        JSONObject jsonObj = (JSONObject) ob;
        JSONArray array = (JSONArray) jsonObj.get("Items");

        for (int i = 0; i < array.size(); i++) {
            JSONObject items = (JSONObject) array.get(i);
            String jID = (String) items.get("ID");
            String jName = (String) items.get("Name");
            String jPrice = (String) items.get("Price");
            String jUnit = (String) items.get("Unit");
            String jQuantity = (String) items.get("Quantity");
            String jCategory = (String) items.get("Category");

            if (deleteID.equals(jID)) {
                array.remove(items);
            }
        }
        JSONObject objMain = new JSONObject();
        objMain.put("Items", array);

        String sql1 = "UPDATE shop_info_tb SET items='" + objMain.toJSONString() + "' WHERE `Shopkeeper_ID` = " + session.getAttribute("userName").toString();
        stmt = con.createStatement();
        stmt.executeUpdate(sql1);

        display(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException, ParseException {
        PrintWriter out = response.getWriter();

        //out.print("\nInside Edit function");
        String id = request.getParameter("id");
//        User existingUser = userDAO.selectUser(id);

        Connection con = getConnection();

        String InsertCustAddress = "SELECT * FROM shop_info_tb WHERE `Shopkeeper_ID`= ?";
        stmt1 = con.prepareStatement(InsertCustAddress);
        stmt1.setString(1, session.getAttribute("userName").toString());//static value 

        ResultSet rs;
        rs = stmt1.executeQuery();

        String json = null;

        while (rs.next()) {
            json = rs.getString("Items");
        }

        JSONParser jp = new JSONParser();
        Object ob = jp.parse(json);
        JSONObject jsonObj = (JSONObject) ob;
        JSONArray array = (JSONArray) jsonObj.get("Items");

        for (int i = 0; i < array.size(); i++) {
            JSONObject items = (JSONObject) array.get(i);
            String jID = (String) items.get("ID");
            String jName = (String) items.get("Name");
            String jPrice = (String) items.get("Price");
            String jUnit = (String) items.get("Unit");
            String jQuantity = (String) items.get("Quantity");
            String jCategory = (String) items.get("Category");

            if (jID.equals(id)) //if(id.equals(jID))
            {
                request.setAttribute("jID", jID);
                request.setAttribute("jName", jName);
                request.setAttribute("jPrice", jPrice);
                request.setAttribute("jUnit", jUnit);
                request.setAttribute("jQuantity", jQuantity);
                request.setAttribute("jCategory", jCategory);
            }
        }

        temp = 1;

        RequestDispatcher dispatcher = request.getRequestDispatcher("Cust_item_form.jsp");
        request.setAttribute("temp", temp);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, ServletException, IOException {

        PrintWriter out = response.getWriter();
        String updateID = request.getParameter("id");
        String newName = request.getParameter("name");
        String newPrice = request.getParameter("price");
        String newUnit = request.getParameter("ddl_item_unit");
        String newQuantity = request.getParameter("item_qnt").toString();
        String newCategory = request.getParameter("ddl_item_ctg");
// 
        //out.print(" ID: "+updateID+"<br> Name: "+newName+"<br> Price: "+newPrice);

        //out.print("\nUpdate User");
        Connection con = getConnection();
        String InsertCustAddress = "SELECT * FROM shop_info_tb WHERE `Shopkeeper_ID`=?";

        stmt1 = con.prepareStatement(InsertCustAddress);
        stmt1.setString(1, session.getAttribute("userName").toString());
        ResultSet rs = stmt1.executeQuery();

        String json = null;
        while (rs.next()) {
            json = rs.getString("Items");
        }

        JSONParser jp = new JSONParser();
        Object ob = jp.parse(json);
        JSONObject jsonObj = (JSONObject) ob;
        JSONArray array = (JSONArray) jsonObj.get("Items");

        for (int i = 0; i < array.size(); i++) {
            JSONObject items = (JSONObject) array.get(i);
            String jID = (String) items.get("ID");
            String jName = (String) items.get("Name");
            String jPrice = (String) items.get("Price");
            String jUnit = (String) items.get("Unit");
            String jQuantity = (String) items.get("Quantity");
            String jCategory = (String) items.get("Category");

            if (updateID.equals(jID)) {
                items.replace("Name", newName);
                items.replace("Price", newPrice);
                items.replace("Unit", newUnit);
                items.replace("Quantity", newQuantity);
                items.replace("Category", newCategory);
            }
        }
        JSONObject objMain = new JSONObject();
        objMain.put("Items", array);

        String sql1 = "UPDATE shop_info_tb SET items='" + objMain.toJSONString() + "' WHERE `Shopkeeper_ID`=" + session.getAttribute("userName").toString();
        stmt = con.createStatement();
        stmt.executeUpdate(sql1);

        display(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Customer_addItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Customer_addItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
