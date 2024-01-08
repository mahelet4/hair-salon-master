<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../include/header.jsp" %>


<div class="container mt-4">
    <h2>Search Bookings</h2>
    <form action="/bookings/search" method="post" class="mb-3">
        <div class="form-row align-items-center">
            <div class="col-md-4 mb-2">
                <label for="appointmentDate" class="sr-only">Appointment Date</label>
                <input type="date" name="appointmentDate" id="appointmentDate" class="form-control" placeholder="Appointment Date">
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>

    <c:choose>
        <c:when test="${not empty booking or not empty bookings}">
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>User ID</th>
                        <th>Service ID</th>
                        <th>Employee ID</th>
                        <th>Appointment Time</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty booking}">
                        <tr>
                            <td>${booking.id}</td>
                            <td>${booking.user.id}</td>
                            <td>${booking.service.id}</td>
                            <td>${booking.employee.id}</td>
                            <td>${booking.appointmentTime}</td>
                            <td>
                                <a href="/booking/edit/${booking.id}" class="btn btn-primary btn-sm">Edit</a>
                                <a href="/booking/detail?id=${booking.id}" class="btn btn-secondary btn-sm">Details</a>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach var="booking" items="${bookings}">
                        <tr>
                            <td>${booking.id}</td>
                            <td>${booking.user.id}</td>
                            <td>${booking.service.id}</td>
                            <td>${booking.employee.id}</td>
                            <td>${booking.appointmentTime}</td>
                            <td>
                                <a href="/edit/${booking.id}" class="btn btn-primary btn-sm">Edit</a>
                                <a href="/details/${booking.id}" class="btn btn-secondary btn-sm">Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <c:if test="${param.bookingId != null || param.serviceId != null}">
                <div class="alert alert-warning">No bookings found.</div>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="../include/footer.jsp"/>