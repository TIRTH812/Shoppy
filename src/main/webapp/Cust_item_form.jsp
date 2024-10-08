<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% int temp = Integer.parseInt(request.getAttribute("temp").toString()); %>
<% String jID = (String) request.getAttribute("jID"); %>
<% String jName = (String) request.getAttribute("jName"); %>
<% String jPrice = (String) request.getAttribute("jPrice"); %>
<% String jUnit = (String) request.getAttribute("jUnit"); %>
<% String jQuantity = (String) request.getAttribute("jQuantity"); %>
<% String jCategory = (String) request.getAttribute("jCategory"); %>

<html>
    <head>
        <title>Product Management</title>
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
        
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">

                    <c:if test="${temp == 1}">
                        <form action="update" method="POST">
                        </c:if>

                        <c:if test="${temp == 0}">
                            <form action="insert" method="POST">
                            </c:if>

                            <h2>
                                <c:if test="${temp == 1}">
                                    Edit Product
                                </c:if>
                                <c:if test="${temp == 0}">
                                    Add New Product
                                </c:if>
                            </h2>
                            <hr>
                            <c:if test="${temp == 1}">
                                <input type="hidden" name="id" value="<c:out value='${jID}' />" />
                            </c:if>

                            <c:if test="${temp == 0}">
                                <fieldset class="form-group">
                                    <input type="hidden" value="<c:out value='${jID}' />" class="form-control" name="id">
                                </fieldset>
                            </c:if>  
                            <br>
                            <fieldset class="form-group">
                                <label>Product Name</label> 
                                <input type="text" value="<c:out value='${jName}' />" class="form-control" name="name">
                            </fieldset>
                            <br>
                            <fieldset class="form-group">
                                <label>Product Price</label> 
                                <input type="text" value="<c:out value='${jPrice}' />" class="form-control" name="price">
                            </fieldset>
                            <br>
                            <fieldset class="form-group">
                                <label for="ddl_item_unit">Item Unit</label>
                                <select class="form-control" id="ddl_item_unit"  name="ddl_item_unit">
                                    <option id="Kg" value="Kg">Kg</option>         
                                    <option id="Litre" value="Litre">Litre</option>
                                    <option id="ML" value="ML">ML</option>
                                    <option id="Gram" value="Gram">Gram</option>
                                    <option id="other" value="other">other</option>
                                </select>
                            </fieldset>
                            <br>
                            <fieldset class="form-group">
                                <label>Item Quantity</label> 
                                <input type="number" class="form-control" name="item_qnt" value="<c:out value='${jQuantity}' />">
                            </fieldset>
                            <br>
                            <fieldset class="form-group">
                                <label for="ddl_item_ctg">Item Category</label>
                                <select class="form-control" id="ddl_item_ctg" name="ddl_item_ctg">
                                    <option id="Dairy" value="Dairy">Dairy</option>
                                    <option id="Grocery" value="Grocery">Grocery</option>
                                    <option id="Cosmetics" value="Cosmetics">Cosmetics</option>
                                    <option id="other" value="other">other</option>
                                </select>
                            </fieldset>
                            <br>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-outline-success">Save</button>
                            </div>

                        </form>
                </div>
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

    <script>
//        For Unit Drop Down List
        let option = document.getElementById("ddl_item_unit");
        for (let i = 0; i < option.length; i++) {
            if (option[i].value === `${jUnit}`) {
                const e = option[i].id;
                document.getElementById(e).selected = true;
            }
        }

//        For Category Drop Down List
        option = document.getElementById("ddl_item_ctg");
        for (let i = 0; i < option.length; i++) {
            if (option[i].value === `${jCategory}`) {
                const e = option[i].id;
                document.getElementById(e).selected = true;
            }
        }
    </script>
</html>