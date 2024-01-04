 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

  <%@ include file="../include/header.jsp" %>
   <h1>Book an Appointment</h1>
<div class="container mt-5">
    <h2>Booking Form</h2>
    <form action="/bookingSubmit" method="post">
        <div class="form-group">
            <label for="customerName">Customer Name:</label>
            <input type="text" class="form-control" id="customerName" name="customerName" required>
        </div>
        <div class="form-group">
            <label for="service">Service:</label>
            <input type="text" class="form-control" id="service" name="service" required>
        </div>
        <div class="form-group">
            <label for="date">Date:</label>
            <input type="date" class="form-control" id="date" name="date" required>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <input type="text" class="form-control" id="status" name="status" required>
        </div>
        <!-- Add more fields as needed -->
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


<jsp:include page="../include/footer.jsp"/>SalonServiceController