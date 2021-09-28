<%@page import="org.json.simple.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% String shopkeeperMobile = (String) request.getAttribute("shopkeeperMobile"); %>
<% String shopkeeperName = (String) request.getAttribute("shopkeeperName"); %>
<% String shopAddress = (String) request.getAttribute("shopAddress"); %>
<% String shopLandmark = (String) request.getAttribute("shopLandmark"); %>
<% String shopCity = (String) request.getAttribute("shopCity"); %>
<% String shopName = (String) request.getAttribute("shopName"); %>
<% String shopType = (String) request.getAttribute("shopType"); %>
<% JSONArray jArray = (JSONArray) request.getAttribute("jArray");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop Info Page</title>
    </head>
    <body>
        
        <h2>Mobile No. : ${shopkeeperMobile}</h2>
        <h2>Name : ${shopkeeperName}</h2>
        <h2>Address : ${shopAddress}</h2>
        <h2>Landmark : ${shopLandmark}</h2>
        <h2>City : ${shopCity}</h2>
        <h2>Shop Name : ${shopName}</h2>
        <h2>Shop Type : ${shopType}</h2>
        
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
    </body>
</html>
