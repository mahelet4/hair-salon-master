<%@ include file="../include/header.jsp" %>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

<div class="container mt-4">
    <h2>Select a Service</h2>
   <form action="/booking/service" method="post">
<div class="row">
    <c:forEach items="${services}" var="service">
        <div class="col-md-4 mb-4">
            <div class="card">
                <!-- Service Image -->
                  <img src="${pageContext.request.contextPath}/pub/images/${service.imageUrl}" alt="${service.name}" width="200" height="150">

                <div class="card-body">
                    <!-- Checkbox for Service Selection -->
                    <input type="checkbox" id="service-${service.id}" name="selectedServiceIds" value="${service.id}" class="mb-2">
                    <label class="form-check-label mb-3" for="service-${service.id}"><strong>${service.name}</strong></label><br>

                    <!-- Service Price -->
                    <p class="card-text">Price: ${service.price}</p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
        <div class="form-group mt-4">
            <label for="employeeId">Select Employee:</label>
            <select id="employeeId" name="selectedEmployeeId" class="form-control">
                <c:forEach items="${employees}" var="employee">
                    <option value="${employee.id}">${employee.name}</option>
                </c:forEach>
            </select>
        </div>
        <!-- Add some margin or padding below the dropdown box -->
        <div class="mt-3"></div>
        <!-- "Next" Button -->
        <input type="submit" name="action" value="Next" class="btn btn-primary mt-3">
    </form>
</div>



<jsp:include page="../include/footer.jsp"/>
