<%-- 
    Document   : Add_Item
    Created on : 17-Jun-2021, 10:39:12 PM
    Author     : meet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

        <title>Add Item</title>

        <style>
            .row
            {
                display: inline-block;
                width:10%;
            }
        </style>


        <title>Add Item</title>
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


        <form action="AddItem" method="POST">

            <div>
                <div>
                    <div class="row"><label for="txt_itemname">Item name</label></div>
                    <div class="row"><input type="text" name="txt_itemname"></div>
                </div>

                <div>
                    <div class="row"><label for="txt_itemprice">Item price.</label></div>
                    <div class="row"><input type="number" name="txt_itemprice"></div>
                </div>
                <div>
                    <div class="row"><label  for="txt_quantity">Quantity</label></div>
                    <div class="row"><input  type="text" name="txt_quantity"></div>
                </div>

                <div>
                    <div class="row"><label  for="ddl_itemunit" id="lblitemunit">Item unit</label></div>
                    <div class="row"><select  name="ddl_itemunit" id="ddlitemunit"></div>
                    <option>KG</option>
                    <option>G</option>
                    <option>LITRE</option>
                    <option>ML</option>
                    <option>OTHERS</option>
                    <option>NONE</option>
                    </select>
                </div>

                <div>
                    <button type="submit" name="btnAdd" id="btnAdd" value="Add">Add Item</button>
                    <button type="submit" name="btnEdit" id="btnEdit" value="Edit">Edit Item</button>
                    <button type="submit" name="btnDelete" id="btnDelete" value="Delete">Delete</button>
                    <button type="submit" name="btnShowItem" id="btnShowItem" value="Show">Show Item</button>
                </div>
                // Include Item category

            </div>


        </form>
    </body>
</html>
