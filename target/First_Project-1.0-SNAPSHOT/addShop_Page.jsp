<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <title>Add Shop</title>
        <style>
            .row
            {
                display: inline-block;
                width:10%;
            }
        </style>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.

            if (session.getAttribute("userName") == null) {
                response.sendRedirect("Registration_Page.jsp");
            }
        %>
        <%@include file="Shopkeeper_Master.jsp" %>
        <div class="my-5 mx-5">
            <form action="shopAdd" method="POST">
                <label class="row">Shop Name</label>
                <input class="row" type="text" id="txtShopName" name="txtShopName">

                <div>
                    <label class="row" for="ddlShopType" id="lblShopType">Shop Type</label>
                    <select class="row" name="ddlShopType" id="ddlShopType">
                        <option>Grocery Shop</option>
                        <option>Sweet Shop</option>
                        <option>Stationary Shop</option>
                        <option>Bakery Shop</option>
                    </select>
                </div>

                <div>
                    <label class="row" for="ddl_country" id="lbl_country">Country</label>
                    <select class="row" name="ddl_country" id="country" onchange="onCountryChange()">

                    </select>
                </div>
                <div>
                    <label class="row" for="ddl_state">State</label>
                    <select class="row" name="ddl_state" id="state" onchange="onStateChange()">

                    </select>
                </div>
                <div>
                    <label class="row" for="ddl_city">City</label>
                    <select class="row" name="ddl_city" id="city">

                    </select>
                </div>
                <div>
                    <label class="row" for="txt_landmark">Landmark</label>
                    <input class="row" type="text" name="txt_landmark">
                </div>
                <div>
                    <label class="row" for="txt_pincode">Pin Code</label>
                    <input class="row" type="number" name="txt_pincode">
                </div>
                <div>
                    <label class="row" for="txt_add">Address</label>
                    <textarea class="row" name="txt_add"></textarea>
                </div>
                <br>
                <button type="submit" id="btnAdd" name="btnAdd" value="add">Add</button>


            </form>
        </div>
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