<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

</head>
<body>

<nav class="navbar navbar-expand-lg ">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Mimi-Salon</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/employee/create">Create Employee</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/employee/search">Search Employee</a>
                </li>
                  <li class="nav-item">
                         <a class="nav-link" href="/booking/service">Services</a>
                     </li>
                 <li class="nav-item">
                    <a class="nav-link" href="/booking/create">Booking</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="/booking/search">Search Booking</a>
                   </li>
                <sec:authorize access="!isAuthenticated()">
                   <li class="nav-item">
                       <a class="nav-link" href="/auth/register">User Registration</a>
                     </li>
                   <li class="nav-item">
                       <a class="nav-link" href="/auth/login">Login</a>
                     </li>
                   </sec:authorize>
                  <sec:authorize access="hasAnyAuthority('ADMIN')">
                      <li class="nav-item">
                        <a class="nav-link" href="/admin/index">Admin</a>
                       </li>
                    </sec:authorize>
                  <sec:authorize access="isAuthenticated()">
                       <li class="nav-item">
                     <a class="nav-link" href="/auth/logout">Logout</a>
                   </li>
                    <li class="nav-item">
                       <a class="nav-link" href=""><sec:authentication property="principal.username" /></a>
                      </li>
                    </sec:authorize>
                       <li class="nav-item">
                          <a class="nav-link" href="/admin/index">Secured Request</a>
                      </li>
            </ul>
        </div>
    </div>
</nav>
