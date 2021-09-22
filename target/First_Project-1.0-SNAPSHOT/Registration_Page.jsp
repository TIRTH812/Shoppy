<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>

        <style>
            .row
            {
                display: inline-block;
                width:10%;
            }
        </style>

    </head>
    <body>

        <form action="Registration" method="POST">

            <input type="radio" id="rd_customer" name="rd_role" value="customer" onclick="showPanel('customer')" checked>
            <label for="rd_customer">Customer</label>
            <input type="radio" id="rd_shopkeeper" name="rd_role" value="shopkeeper" onclick="showPanel('shopkeeper')">
            <label for="rd_shopkeeper">Shopkeeper</label><br>

            <panel id="panelCommon">
                <div>
                    <div>
                        <div class="row"><label for="txt_fname">First Name</label></div>
                        <div class="row"><input type="text" name="txt_fname"></div>
                    </div>
                    <div>
                        <div class="row""><label for="txt_mname">Middle Name</label></div>
                        <div class="row""><input type="text" name="txt_mname"></div>
                    </div>
                    <div>
                        <label class="row" for="txt_lname">Last Name</label>
                        <input class="row" type="text" name="txt_lname">
                    </div>
                    <div>
                        <label class="row" for="txt_mobile">Mobile No.</label>
                        <input class="row" type="number" name="txt_mobile">
                    </div>
                    <div>
                        <label class="row" for="txt_email">Email</label>
                        <input class="row" type="email" name="txt_email">
                    </div>

                    <panel id="panelShopkeeper">
                        <div>
                            <label class="row" for="ddl_country" id="lbl_co untry">Country</label>
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
                    </panel>
                    <div>
                        <label class="row" for="txt_pwd">Password</label>
                        <input class="row" type="password" name="txt_pwd">
                    </div>
                    <div>
                        <label class="row" for="txt_cpwd">Confirm Password</label>
                        <input class="row" type="password" name="txt_cpwd">
                    </div>
                    <div>
                        <button type="submit" name="btnSubmitNext" id="btnSubmit" value="submit">Submit</button>
                        <button type="submit" name="btnSubmitNext" id="btnNext" value="next">Next</button>
                    </div>
                </div>
            </panel>
        </form>

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

    <!-- ------------------------------------------------------------ Panel Visibility Logic ------------------------------------------------------->    

    <script>
        document.getElementById("panelCommon").style.display = "block";
        document.getElementById("panelShopkeeper").style.display = "block";
        document.getElementById("btnSubmit").style.display = "block";
        document.getElementById("btnNext").style.display = "none";

        function showPanel(ch)
        {
            if (ch === 'customer')
            {
                document.getElementById("panelCommon").style.display = "block";
                document.getElementById("panelShopkeeper").style.display = "block";
                document.getElementById("btnSubmit").style.display = "block";
                document.getElementById("btnNext").style.display = "none";
            }
            if (ch === "shopkeeper")
            {
                document.getElementById("panelCommon").style.display = "block";
                document.getElementById("panelShopkeeper").style.display = "none";
                document.getElementById("btnSubmit").style.display = "none";
                document.getElementById("btnNext").style.display = "block";
            }
        }
    </script>

</html>