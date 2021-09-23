<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <title>Create Account</title>
    </head>

    <body>
        <div class="container">
            <h1 class="my-3">Create Your Account</h1>
            <hr>
            <form class="my-3" action="Registration" method="POST">
                <fieldset class="row mb-3">
                    <legend class="col-form-label col-sm-2 pt-0">Select Your Role</legend>
                    <div class="col-sm-10">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rd_role" id="rd_customer" value="customer" onclick="showPanel('customer')">
                            <label class="form-check-label" for="rd_customer">
                                Customer
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rd_role" id="rd_shopkeeper" value="shopkeeper" onclick="showPanel('shopkeeper')">
                            <label class="form-check-label" for="rd_shopkeeper">
                                Shopkeeper
                            </label>
                        </div>
                </fieldset>
                <hr>

                <panel id="image">
                    <h1>Oops!... Role is not selected</h1>
                </panel>
                
                <panel id="panelCommon">

                    <div class="row mb-3 mt-4">
                        <label for="txt_fname" class="col-sm-2 col-form-label">First Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="txt_fname" name="txt_fname">
                        </div>
                    </div>
                    <div class="row mb-3 mt-4">
                        <label for="txt_mname" class="col-sm-2 col-form-label">Middle Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="txt_mname" name="txt_mname">
                        </div>
                    </div>
                    <div class="row mb-3 mt-4">
                        <label for="txt_lname" class="col-sm-2 col-form-label">Last Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="txt_lname" name="txt_lname">
                        </div>
                    </div>
                    <div class="row mb-3 mt-4">
                        <label for="txt_mobile" class="col-sm-2 col-form-label">Mobile No.</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="txt_mobile" name="txt_mobile">
                        </div>
                    </div>
                    <div class="row mb-3 mt-4">
                        <label for="txt_email" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="txt_email" name="txt_email">
                        </div>
                    </div>

                    <panel id="panelShopkeeper">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label" for="ddl_country">Country</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="ddl_country" id="country" onchange="onCountryChange()">
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label" for="ddl_state">State</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="ddl_state" id="state" onchange="onStateChange()">
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label" for="ddl_city">City</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="ddl_city" id="city">
                                </select>
                            </div>
                        </div> 
                        <div class="row mb-3 mt-4">
                            <label for="txt_landmark" class="col-sm-2 col-form-label">Landmark</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="txt_landmark" name="txt_landmark">
                            </div>
                        </div>
                        <div class="row mb-3 mt-4">
                            <label for="txt_pincode" class="col-sm-2 col-form-label">Pincode</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="txt_pincode" name="txt_pincode">
                            </div>
                        </div>
                        <div class="row mb-3 mt-4">
                            <label for="txt_add" class="col-sm-2 col-form-label">Address</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="txt_add" rows="3" name="txt_add"></textarea>
                            </div>
                        </div>   
                    </panel>

                    <div class="row mb-3">
                        <label for="txt_pwd" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="txt_pwd" name="txt_pwd">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="txt_cpwd" class="col-sm-2 col-form-label">Confirm Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="txt_cpwd" name="txt_cpwd">
                        </div>
                    </div>
                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-outline-primary" name="btnSubmitNext" id="btnSubmit" value="submit">Submit</button>
                        <button type="submit" class="btn btn-outline-primary" name="btnSubmitNext" id="btnNext" value="next">Next</button>
                    </div>
                </panel>
            </form>
        </div>

        <!-- ---------------------------------------Country----------------------------------------- -->       

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


            //--------------Remove Function for removing options from State & City DropDownLists---------------------

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

            //-------------------------------------------------State--------------------------------------------------

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

            // ------------------------------------------------City-------------------------------------------------

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


        <!-- Optional JavaScript; choose one of the two! -->
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <!--
          <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
        -->
    </body>

    <!-- ------------------------------------ Panel Visibility Logic --------------------------------------------- -->

    <script>
        document.getElementById("panelCommon").style.display = "none";
        document.getElementById("panelShopkeeper").style.display = "none";
        document.getElementById("btnSubmit").style.display = "none";
        document.getElementById("btnNext").style.display = "none";
        document.getElementById("image").style.display = "none";

        let customer = document.getElementById("rd_customer");
        let shopkeeper = document.getElementById("rd_shopkeeper");
        if(customer.checked == false && shopkeeper.checked == false)
        {
            document.getElementById("panelCommon").style.display = "none";
            document.getElementById("panelShopkeeper").style.display = "none";
            document.getElementById("btnSubmit").style.display = "none";
            document.getElementById("btnNext").style.display = "none";
            document.getElementById("image").style.display = "block";
        }

        function showPanel(ch)
        {
            if (ch === 'customer')
            {
                document.getElementById("panelCommon").style.display = "block";
                document.getElementById("panelShopkeeper").style.display = "block";
                document.getElementById("btnSubmit").style.display = "block";
                document.getElementById("btnNext").style.display = "none";
                document.getElementById("image").style.display = "none";
            }
            if (ch === "shopkeeper")
            {
                document.getElementById("panelCommon").style.display = "block";
                document.getElementById("panelShopkeeper").style.display = "none";
                document.getElementById("btnSubmit").style.display = "none";
                document.getElementById("btnNext").style.display = "block";
                document.getElementById("image").style.display = "none";
            }
        }
    </script>
</html>