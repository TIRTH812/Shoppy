<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>

<%
    String id = session.getAttribute("userName").toString();

    String dbURL = "jdbc:mysql://localhost:3306/ijavaprojectdbv1";
    String dbUid = "root";
    String dbPwd = "";

    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    Connection connection = null;
    Statement statement = null;
    ResultSet resultset = null;

%>

<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Customer shop list Page</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    </head>
    <body>
        <%            
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.

            if (session.getAttribute("userName") == null) {
                response.sendRedirect("Login_Page.jsp");
            }
        %>

        <%@include file="Customer_Master.jsp" %>        

        <%
            try {

                connection = DriverManager.getConnection(dbURL, dbUid, dbPwd);
                statement = connection.createStatement();

                //out.print("Connection establish successfully");
                String sql = "SELECT  `shop_info_tb`.`Shop ID`, `shop_info_tb`.`Shop Name`,`shop_info_tb`.`Shop Type`,`shop_info_tb`.`Items`,`shop_addr_tb`.`Society Name`,`shop_addr_tb`.`Landmark`,`shop_addr_tb`.`City`FROM `shop_addr_tb`JOIN `cust_addr_tb` ON `shop_addr_tb`.`Pin Code` = `cust_addr_tb`.`Pin code`JOIN `shop_info_tb` ON `shop_info_tb`.`Shop_addr_ID` = `shop_addr_tb`.`Sh_Addr_ID`WHERE `cust_addr_tb`.`Cust_Addr_ID` = " + id + "";   

                resultset = statement.executeQuery(sql);

        %>


        <div class="container my-3">
            <center><h1>Shop List</h1></center>
            <hr>
            <table class="table table-striped my-3">
                <thead>
                    <tr>
                        <th>Shop ID</th>
                        <th>Shop Name</th>
                        <th>Shop Type</th>                    
                        <th>Address</th>
                        <th>Landmark</th>
                        <th>City</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>


                    <%                    
                        while (resultset.next()) 
                        {
                            request.setAttribute("shopID", resultset.getString("Shop ID"));
                            request.setAttribute("shopName", resultset.getString("Shop Name"));
                            request.setAttribute("shopType", resultset.getString("Shop Type"));
                            request.setAttribute("shopAddress", resultset.getString("Society Name"));
                            request.setAttribute("shopLandmark", resultset.getString("Landmark"));
                            request.setAttribute("shopCity", resultset.getString("City"));
                    %>


                    <tr style="font-size: 18px">
                        <td>${shopID}</td>
                        <td>${shopName}</td>
                        <td>${shopType}</td>
                        <td>${shopAddress}</td>
                        <td>${shopLandmark}</td>
                        <td>${shopCity}</td>

                        <td>
                            <a href="CustomerShopList?id=<c:out value='${shopID}' />" class="btn btn-outline-warning btn-sm">More Info</a>&nbsp;
                        </td>
                    </tr>


                    <%                }//end of while loop

                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            out.print(e);
                        }
                    %>


                </tbody>
            </table>
        </div>

    </body>
</html>