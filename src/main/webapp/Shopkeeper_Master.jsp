<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <div class="container-fluid">
        <a class="navbar-brand" href="#">Project Name</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="Shopkeeper_Page.jsp">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="Shopkeeper_ManageProfile.jsp">Manage Profile</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Customers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="Cust_item_list.jsp">Shop item</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">About Us</a>
                </li>            
            </ul>
            <form class="d-flex" action="Shopkeeper" method="POST">
                <!--<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">-->
                <input class="btn btn-outline-success" style="margin-top: 8px; margin-bottom: -8px;" type="submit" id="btnLogout" name="btnLogout" value="logout"></button>
            </form>
        </div>
    </div>
</nav>

<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
-->
