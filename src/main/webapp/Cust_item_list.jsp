<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% JSONArray jArray = (JSONArray) request.getAttribute("jArray");%>

<html>
    <head>
        <title>Product Management</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
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

        <%@include file="Shopkeeper_Master.jsp" %>
        
        <div class="my-2 mx-5">
            <h3>Welcome ${userName}</h3>
        </div>
        <div class="container my-3">
            <div class="container">
                <h3 class="text-center">List of Products</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-primary">Add New Product</a>
                    <a href="<%=request.getContextPath()%>/display" class="btn btn-primary">Show Products</a>
                </div>
                <br>
                <c:if test="${jArray.size() == 0}">
                    <div class="container">
                        <p>No Data</p>
                        <center><img src="NoData.png" height="300" alt="Loading..."/></center>
                    </div>
                </c:if>
                <c:if test="${jArray.size() >= 1}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Unit</th>
                                <th>Quantity</th>
                                <th>Category</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${jArray}" var="obj">
                                <tr style="font-size: 18px">
                                    <td>${obj.get("ID")}</td>
                                    <td>${obj.get("Name")}</td>
                                    <td>${obj.get("Price")}</td>
                                    <td>${obj.get("Unit")}</td>
                                    <td>${obj.get("Quantity")}</td>
                                    <td>${obj.get("Category")}</td>
                                    <td>
                                        <a href="edit?id=<c:out value='${obj.get("ID")}' />" class="btn btn-outline-warning btn-sm">Edit</a>&nbsp;
                                        <a href="delete?id=<c:out value='${obj.get("ID")}' />" class="btn btn-outline-danger btn-sm">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>

        <!-- Optional JavaScript; choose one of the two! -->
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <!--
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        -->
    </body>
</html>
