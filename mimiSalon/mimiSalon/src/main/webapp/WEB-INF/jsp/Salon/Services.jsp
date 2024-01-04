<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.5.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-pzjw8f+ua8Y9jgT2mNKCqI5WzO5r5LG5om/Kf5C5t5B6f4orJw5D5w5fx5x5fo5f5nf5f5I5b5H5f5f5B5T5P5E5w5D5w5C5s5H5M5L5S5d5W5d5Z5g5O5M5D5w5L5S5g5J5V5Q=="
        crossorigin="anonymous">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">
<%@ include file="../include/header.jsp" %>

<div class="form-group">
    <label for="service">Service:</label>
    <select class="form-control" id="service" name="service" required>
        <option value="">Select a service</option>
        <c:forEach items="${services}" var="service">
            <c:choose>
                <c:when test="${service.name == 'Haircut'}">
                    <option value="${service.name}">${service.name} - $50.00</option>
                </c:when>
                <c:when test="${service.name == 'Hair Coloring'}">
                    <option value="${service.name}">${service.name} - $80.00</option>
                </c:when>
                <c:when test="${service.name == 'Hair Styling'}">
                    <option value="${service.name}">${service.name} - $60.00</option>
                </c:when>
                <c:when test="${service.name == 'Blowout'}">
                    <option value="${service.name}">${service.name} - $40.00</option>
                </c:when>
                <c:when test="${service.name == 'Shampoo and Conditioning'}">
                    <option value="${service.name}">${service.name} - $35.00</option>
                </c:when>
                <c:when test="${service.name == 'Highlights'}">
                    <option value="${service.name}">${service.name} - $90.00</option>
                </c:when>
                <c:when test="${service.name == 'Balayage'}">
                    <option value="${service.name}">${service.name} - $100.00</option>
                </c:when>
                <c:when test="${service.name == 'Perms'}">
                    <option value="${service.name}">${service.name} - $70.00</option>
                </c:when>
                <c:when test="${service.name == 'Extensions'}">
                    <option value="${service.name}">${service.name} - $120.00</option>
                </c:when>
                <c:when test="${service.name == 'Updos'}">
                    <option value="${service.name}">${service.name} - $55.00</option>
                </c:when>
                <c:when test="${service.name == 'Beard Trim'}">
                    <option value="${service.name}">${service.name} - $20.00</option>
                </c:when>
                <c:when test="${service.name == 'Facial'}">
                    <option value="${service.name}">${service.name} - $70.00</option>
                </c:when>
                <c:when test="${service.name == 'Manicure'}">
                    <option value="${service.name}">${service.name} - $25.00</option>
                </c:when>
                <c:when test="${service.name == 'Pedicure'}">
                    <option value="${service.name}">${service.name} - $30.00</option>
                </c:when>
                <c:when test="${service.name == 'Nail Extensions'}">
                    <option value="${service.name}">${service.name} - $50.00</option>
                </c:when>
                <c:when test="${service.name == 'Waxing'}">
                    <option value="${service.name}">${service.name} - $40.00</option>
                </c:when>
                <c:when test="${service.name == 'Eyebrow Shaping'}">
                    <option value="${service.name}">${service.name} - $15.00</option>
                </c:when>
                <c:when test="${service.name == 'Eyelash Extensions'}">
                    <option value="${service.name}">${service.name} - $70.00</option>
                </c:when>
                <c:when test="${service.name == 'Makeup Application'}">
                    <option value="${service.name}">${service.name} - $45.00</option>
                </c:when>
                <!-- Add more services and prices as needed -->
                <c:otherwise>
                    <option value="${service.name}">${service.name} - Price not available</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</div>
          <!-- Add an Edit button that redirects to the booking edit page -->
            <a href="/bookings/edit?bookingId=${booking.id}" class="btn btn-primary">Edit Booking</a>

    </form>
</div>
<jsp:include page="../include/footer.jsp"/>