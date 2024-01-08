<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../include/header.jsp" %>

   <div class="container mt-5">
        <h2 class="mb-4">Review Your Selections</h2>

        <!-- Display customer name and appointment time -->
        <div class="row">
            <div class="col-md-6">

                <p><strong>Appointment Time:</strong> ${booking.appointmentTime}</p>
            </div>
        </div>

        <!-- Display selected services in a table with Bootstrap classes -->
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Service Name</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Detail</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${selectedServices}" var="service">
                    <tr>
                        <td>${service.name}</td>
                        <td>${service.price}</td>
                        <td><img src="${pageContext.request.contextPath}/pub/images/${service.image_url}" alt="${service.name}" style="max-width: 100px; height: auto;"></td>
                        <td><a href="/bookings/edit/${booking.id}">Edit</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Other booking information inputs (e.g., user details) go here -->

        <!-- Submit button to complete the booking -->
        <form action="/bookings/complete" method="post">
            <!-- Add other input fields for user details if needed -->
            <input type="submit" class="btn btn-primary" value="Complete Booking">
        </form>
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>