<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop Info Page</title>
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
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(dbURL, dbUid, dbPwd);

                int shopID = Integer.parseInt(request.getAttribute("shopID").toString());

                String sql = "SELECT `shopkeeper_info_tb`.`Shopkeeper ID`, `shopkeeper_info_tb`.`Full Name`, `shop_addr_tb`.`Society Name`, `shop_addr_tb`.`Landmark`, `shop_addr_tb`.`City`, `shop_info_tb`.`Shop Name`, `shop_info_tb`.`Shop Type`, `shop_info_tb`.`Items` FROM `shopkeeper_info_tb` JOIN `shop_addr_tb` ON `shop_addr_tb`.`Sh_Addr_ID` = `shopkeeper_info_tb`.`Shopkeeper ID` JOIN `shop_info_tb` ON `shop_info_tb`.`Shopkeeper_ID` = `shopkeeper_info_tb`.`Shopkeeper ID` WHERE `shop_info_tb`.`Shop ID` = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, shopID);
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
                if (json != null) {
                    JSONParser jp = new JSONParser();
                    Object ob = jp.parse(json);

                    JSONObject jsonObj = (JSONObject) ob;
                    array = (JSONArray) jsonObj.get("Items");
                }
                request.setAttribute("jArray", array);
//-----------------------------------------------------------------
                String customerID = session.getAttribute("userName").toString();
                sql = "SELECT Status from shop_cust_mapping_tb WHERE `Shop ID` = ? AND `Customer ID` = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, shopID);
                pstmt.setString(2, customerID);
                rs = pstmt.executeQuery();
//                out.print("Result Set : "+rs+" : Result Set");
                if (rs.next()) {
                    request.setAttribute("status", rs.getString("Status"));
                } else {
                    request.setAttribute("status", "N");
                }

        %>

        <div class="container my-5 d-flex justify-content-between-">
            <div class="container">
                <h2>Shop ID : ${shopID}</h2>
                <h2>Mobile No. : ${shopkeeperMobile}</h2>
                <h2>Name : ${shopkeeperName}</h2>
                <h2>Address : ${shopAddress}</h2>
                <h2>Landmark : ${shopLandmark}</h2>
                <h2>City : ${shopCity}</h2>
                <h2>Shop Name : ${shopName}</h2>
                <h2>Shop Type : ${shopType}</h2>
                <h2>Status : ${status}</h2>

            </div>
            <div class="container">
                <c:if test="${jArray.size() == 0}">
                    <h2>Shop Produtcs :  Oops!..Please add some products.</h2>
                </c:if>
                <c:if test="${jArray.size() >= 1}">
                    <h2>Shop Produtcs List</h2>
                    <c:forEach items="${jArray}" var="obj">
                        <ol>
                            <li><h3>${obj.get("Name")}</h3></li>
                        </ol>
                    </c:forEach>    
                </c:if>
            </div>
        </div>
        <div class="container d-grid gap-2">
            <c:if test="${status == 'N'}">
                <a href="CustomerShopInfo?status=N&shopID=<c:out value='${shopID}' />" class="btn btn-outline-success">Connect</a>&nbsp;
            </c:if>
            <c:if test="${status == 'P'}">
                <a href="CustomerShopInfo?status=P&shopID=<c:out value='${shopID}' />" class="btn btn-outline-success">Cancel Request</a>&nbsp;
            </c:if>
            <c:if test="${status == 'C'}">
                <a href="CustomerShopInfo?status=C&shopID=<c:out value='${shopID}' />" class="btn btn-outline-success">Disconnect</a>&nbsp;
            </c:if>
        </div>
        <%                
                rs.close();
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.print(e);
            }
        %>
    </body>
</html>