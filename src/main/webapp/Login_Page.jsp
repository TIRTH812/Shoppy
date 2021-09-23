<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <title>Login</title>
    </head>

    <body>
        
        <div class="my-5 container d-flex justify-content-center ">
            
            <div class="card my-3" style="width: 23rem;">

                <form action="Login" class="my-2 mx-3 " method="POST">
                    <h1 class="my-3">Login</h1>

                    <label class="form-label">Select Your Role: </label><br>
                    <input class="form-check-input" type="radio" id="rd_customer" name="rd_role" value="customer" onclick="show(0)" checked>
                    <label class="form-label" for="rd_customer">Customer</label><br>
                    <input class="form-check-input"  type="radio" id="rd_shopkeeper" name="rd_role" value="shopkeeper" onclick="show(1)">
                    <label class="form-label" for="rd_shopkeeper">Shopkeeper</label><br>

                    <label class="form-label" for="txt_name">Username</label> <input type="text" name="txt_uname"><br>
                    <label class="form-label" for="txt_name">Password</label> <input type="password" name="txt_pwd"><br>

                    <button class="btn btn-primary" type="submit" name="btnLoginNoaccount" id="btn_login" value="Login">Login</button><br>
                    <a class="link" href="#">Forgot Password</a><br>
                    <button class="btn btn-primary btn-sm" type="submit" name="btnLoginNoaccount" id="btn_noaccount" value="No_Account">I have no Account</button><br>
                </form>
              
            </div>
        </div>
    </body>
</html>