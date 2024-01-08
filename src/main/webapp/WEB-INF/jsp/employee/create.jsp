<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>

<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Employee</h1>
            </div>
        </div>
    </div>
</section>

  <div class="container">
        <h2>Create Employee</h2>

        <!-- Display success message -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>

        <!-- Display error message -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

<form action="/employee/createSubmit" method="post">
    <input type="hidden" name="id" value="${form.id}" />
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" name="name" class="form-control" id="name" value="${form.name}" required>
    </div>
    <div class="form-group">
        <label for="speciality">Speciality</label>
        <input type="text" name="speciality" class="form-control" id="speciality" value="${form.speciality}" required>
    </div>
    <!-- Other form fields go here -->
    <button type="submit" class="btn btn-primary">Save</button>
</form>
    </div>

<jsp:include page="../include/footer.jsp"/>

