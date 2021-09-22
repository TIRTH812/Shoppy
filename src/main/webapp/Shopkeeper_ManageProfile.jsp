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
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <title>Manage profile</title>
    </head>
    <body>

        <%  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.

            if (session.getAttribute("userName") == null) {
                response.sendRedirect("Login_Page.jsp");
                
            }
        %>

        <%@include file="Shopkeeper_Master.jsp" %>

        <div class="container col-md-5 my-5">
            <div class="card">
                <div class="card-body">
                    <form action="ShopkeeperManageProfile" method="post">
                        
                        <%
                            
                         
                            try {

                                connection = DriverManager.getConnection(dbURL, dbUid, dbPwd);
                                statement = connection.createStatement();

                                //out.print("Connection establish successfully");
                                String sql = "SELECT * FROM `shopkeeper_info_tb`,`shop_addr_tb`,`shop_info_tb` WHERE `shopkeeper_info_tb`.`Shopkeeper ID` = "+id+" AND `shop_addr_tb`.`Sh_Addr_ID` = "+id+" AND `shop_info_tb`.`Shopkeeper_ID` = "+id+" ";
                               

                                resultset = statement.executeQuery(sql);

                                resultset.next();

                                request.setAttribute("fullname", resultset.getString("Full Name"));
                                request.setAttribute("email", resultset.getString("Email ID"));

                                request.setAttribute("societyname", resultset.getString("Society Name"));
                                request.setAttribute("landmark", resultset.getString("Landmark"));
                                request.setAttribute("city", resultset.getString("City"));
                                request.setAttribute("state", resultset.getString("State"));
                                request.setAttribute("country", resultset.getString("Country"));
                                request.setAttribute("pincode", resultset.getString("Pin Code"));

                                request.setAttribute("shopname", resultset.getString("Shop Name"));
                                request.setAttribute("shoptype", resultset.getString("Shop Type"));

                                String emailid = resultset.getString("Email ID");

                                connection.close();
                            } 
                            catch (Exception e) {
                                e.printStackTrace();
                                out.print(e);
                            }
                        %>

                        <fieldset class="form-group">
                            <label>Name</label> 
                            <input type="text" class="form-control" name="txt_name" value="<%=request.getAttribute("fullname")%>" >
                        </fieldset>
                        <br>                            
                        <fieldset class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" name="txt_email" value="<%=request.getAttribute("email")%>" >
                        </fieldset>
                        <br>                          
                        <fieldset class="form-group">
                            <label>Society Name</label> 
                            <textarea class="form-control" name="txt_address" ><%=request.getAttribute("societyname")%></textarea>
                        </fieldset>
                        <br>                            
                        <fieldset class="form-group">
                            <label>Landmark</label> 
                            <input type="text" class="form-control" name="txt_landmark" value="<%=request.getAttribute("landmark")%>">
                        </fieldset>
                        <br>

                        <fieldset class="form-group">   
                            <label for="ddl_country" id="lbl_country"> Country</label>
                            <select class="form-control" name="ddl_country" id="country" onchange="onCountryChange()"  >
                                <option value="<%=request.getAttribute("country")%>"><%=request.getAttribute("country")%></option>
                            </select>
                        </fieldset>
                        <br>

                        <fieldset class="form-group">
                            <label for="ddl_state">State</label>
                            <select class="form-control" name="ddl_state" id="state" onchange="onStateChange()"  >
                                <option value="<%=request.getAttribute("state")%>"><%=request.getAttribute("state")%></option>
                            </select>
                        </fieldset>
                        <br>

                        <fieldset class="form-group">
                            <label for="ddl_city">City</label>
                            <select class="form-control" name="ddl_city" id="city" >
                                <option value="<%=request.getAttribute("city")%>"><%=request.getAttribute("city")%></option>
                            </select>
                        </fieldset>
                        <br>

                        <fieldset class="form-group">
                            <label>Pin Code</label> 
                            <input type="text" class="form-control" name="txt_pincode" value="<%=request.getAttribute("pincode")%>">
                        </fieldset>
                        <br>

                        <fieldset class="form-group">
                            <label>Shop Name</label>
                            <input class="form-control" type="text" id="txtShopName" name="txt_ShopName" value="<%=request.getAttribute("shopname")%>">
                        </fieldset>
                        <br>

                        <fieldset class="form-group">
                            <label>Shop Type</label>
                            <select class="form-control" name="ddlShopType" >
                                <option>Grocery Shop</option>
                                <option>Sweet Shop</option>
                                <option>Stationary Shop</option>
                                <option>Bakery Shop</option>
                            </select>
                        </fieldset>
                        <br>
                        <br>



                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-outline-success">Save</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>



        <!-- ----------------------------------------------------------------Country----------------------------------------------------------------- -->       

        <script>

            var headers = new Headers();
            headers.append("X-CSCAPI-KEY", "OXkxc1IzVnYzeHJ2aTRSb2ttdm9IdE5vRm5FN1NJMFlRUTFuUGF0TQ==");

            var requestOptions = {
                method: 'GET',
                headers: headers,
                redirect: 'follow'
            };

            fetch("https://api.countrystatecity.in/v1/countries", requestOptions)
                    .then(response => response.json())
                    .then(response => response.forEach(function (value)
                        {
                            var op = document.createElement('option');
                            op.innerText = value.name;
                            op.setAttribute('id', value.iso2);
                            op.setAttribute('value', value.name);
                            document.getElementById('country').appendChild(op);
                        }))
                    .catch(error => console.log('error', error));


//------------------------------------Remove Function for removing options from State & City DropDownLists--------------------------------------

            function removeOptions(ddlName)
            {
                var st = document.getElementById(ddlName);
                var len = st.options.length;
                //console.log(len);

                //Master IDEA Think Differently
                for (var i = 0; i <= len - 1; i++)
                {
                    st.remove(0);
                }
                /*for (var i = len-1; i >= 0; i--) 
                 {
                 st.remove(i);   
                 }*/
            }

//----------------------------------------------------------------------State------------------------------------------------------------------

            function onCountryChange()
            {
                removeOptions("state");

                let Country = document.getElementById('country');
                selectedCountry = Country.options[Country.selectedIndex].id;
                console.log(selectedCountry);

                fetch("https://api.countrystatecity.in/v1/countries/" + selectedCountry + "/states", requestOptions)
                        .then(response => response.json())
                        .then(result => result.forEach(function (value)
                            {
                                var op = document.createElement('option');
                                op.innerText = value.name;
                                op.setAttribute('id', value.iso2);
                                op.setAttribute('value', value.name);
                                document.getElementById('state').appendChild(op);
                            }))
                        .catch(error => console.log('error', error));
            }

            //--------------------------------------------------------------------City---------------------------------------------------------------------

            function onStateChange()
            {
                removeOptions("city");

                let Country = document.getElementById('country');
                selectedCountry = Country.options[Country.selectedIndex].id;

                let State = document.getElementById('state');
                selectedState = State.options[State.selectedIndex].id;

                fetch("https://api.countrystatecity.in/v1/countries/" + selectedCountry + "/states/" + selectedState + "/cities", requestOptions)
                        .then(response => response.json())
                        .then(result => result.forEach(function (value)
                            {
                                var op = document.createElement('option');
                                op.innerText = value.name;
                                op.setAttribute('id', value.id);
                                op.setAttribute('value', value.name);
                                document.getElementById('city').appendChild(op);
                            }))
                        .catch(error => console.log('error', error));
            }

        </script>



    </body>
</html>
