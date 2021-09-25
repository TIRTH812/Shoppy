<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

        <title>Shopkeeper</title>  
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
        <div class="container d-flex justify-content-between">

            <div class="my-5 mx-5">
                <h1 style="margin-top: 12rem">Welcome ${userName}</h1>
            </div>

            <div id="carouselExampleDark" class="carousel carousel-dark slide my-5" data-bs-ride="carousel" style="width: 500px">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="3" aria-label="Slide 4"></button>
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="4" aria-label="Slide 5"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active" data-bs-interval="3000">
                        <img src="https://stories.freepiklabs.com/storage/15165/Online-groceries_Mesa-de-trabajo-1.svg" height="450" class="d-block w-100" alt="...">
                        <!--                        <div class="carousel-caption d-none d-md-block">
                                                    <h5>First slide label</h5>
                                                    <p>Some representative placeholder content for the first slide.</p>
                                                </div>-->
                    </div>
                    <div class="carousel-item" data-bs-interval="3000">
                        <img src="https://stories.freepiklabs.com/storage/8147/Online-Groceries_Mesa-de-trabajo-1.svg" height="450" class="d-block w-100" alt="...">
                        <!--                        <div class="carousel-caption d-none d-md-block">
                                                    <h5>Second slide label</h5>
                                                    <p>Some representative placeholder content for the second slide.</p>
                                                </div>-->
                    </div>
                    <div class="carousel-item" data-bs-interval="3000">
                        <img src="https://stories.freepiklabs.com/storage/8148/Online-groceries-01.svg" height="450" class="d-block w-100" alt="...">
                        <!--                        <div class="carousel-caption d-none d-md-block">
                                                    <h5>Third slide label</h5>
                                                    <p>Some representative placeholder content for the third slide.</p>
                                                </div>-->
                    </div>
                    <div class="carousel-item" data-bs-interval="3000">
                        <img src="https://stories.freepiklabs.com/storage/28545/In-No-Time-01.svg" height="450" class="d-block w-100" alt="...">
                        <!--                        <div class="carousel-caption d-none d-md-block">
                                                    <h5>Forth slide label</h5>
                                                    <p>Some representative placeholder content for the forth slide.</p>
                                                </div>-->
                    </div>
                    <div class="carousel-item">
                        <img src="https://stories.freepiklabs.com/storage/30275/_In-no-time-01.svg" height="450" class="d-block w-100" alt="...">
                        <!--                        <div class="carousel-caption d-none d-md-block">
                                                    <h5>Fifth slide label</h5>
                                                    <p>Some representative placeholder content for the fifth slide.</p>
                                                </div>-->
                    </div>
                </div>
                <!--            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>-->
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