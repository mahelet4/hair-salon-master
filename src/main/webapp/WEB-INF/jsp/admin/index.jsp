<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../include/header.jsp"/>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">


   <div class="container mt-5 mb-4">
       <h2>Admin Dashboard</h2>

<div class="container">
    <h2>Add Service</h2>
    <c:if test="${not empty success}">
        <div class="row justify-content-center">
            <div class="col-6 text-center">
                <div class="alert alert-success" role="alert">
                    ${success}
                </div>
            </div>
        </div>
    </c:if>

    <form action="/admin/index" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="serviceName">Service Name:</label>
            <input type="text" class="form-control" id="serviceName" name="name" required>
        </div>
        <div class="form-group">
            <label for="serviceImage">Image:</label>
            <input type="file" class="form-control" id="serviceImage" name="image" required>
        </div>
        <div class="form-group">
            <label for="servicePrice">Price:</label>
            <input type="number" class="form-control" id="servicePrice" name="price" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Service</button>
    </form>
</div>

<div class="container mt-5 mb-4">
   <h2>Users</h2>
       <table class="table">
           <thead>
               <tr>
                   <th>ID</th>
                   <th>Name</th>
                   <th>Email</th>
                   <!-- Other User Attributes -->
               </tr>
           </thead>
           <tbody>
               <c:forEach var="user" items="${users}">
                   <tr>
                       <td>${user.id}</td>
                       <td>${user.name}</td>
                       <td>${user.email}</td>

                   </tr>
               </c:forEach>
           </tbody>
       </table>
        <h2>Bookings</h2>
           <table class="table">
               <thead>
                   <tr>
                       <th>ID</th>
                       <th>User ID</th>
                       <th>Service ID</th>
                       <th>Appointment Time</th>
                       <!-- Other Booking Attributes -->
                   </tr>
               </thead>
               <tbody>
                   <c:forEach var="booking" items="${bookings}">
                       <tr>
                           <td>${booking.id}</td>
                           <td>${booking.user.id}</td>
                           <td>${booking.service.id}</td>
                           <td>${booking.appointmentTime}</td>
                           <!-- Other Booking Attributes -->
                       </tr>
                   </c:forEach>
               </tbody>
           </table>

           <!-- Display Services -->
           <h2>Services</h2>
           <table class="table">
               <thead>
                   <tr>
                       <th>ID</th>
                       <th>Name</th>
                       <th>Price</th>
                       <!-- Other Service Attributes -->
                   </tr>
               </thead>
               <tbody>
                   <c:forEach var="service" items="${services}">
                       <tr>
                           <td>${service.id}</td>
                           <td>${service.name}</td>
                           <td>${service.price}</td>
                           <!-- Other Service Attributes -->
                       </tr>
                   </c:forEach>
               </tbody>
           </table>

       </div>

       <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
       <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



<jsp:include page="../include/footer.jsp"/>