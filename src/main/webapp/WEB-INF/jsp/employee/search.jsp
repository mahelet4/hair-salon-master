<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<jsp:include page="../include/header.jsp"/>

<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Employee Search</h1>
            </div>
        </div>
    </div>
</section>

<section class="bg-light1 pt-5 pb-5">
    <div class="container">
        <div class="container">
                <form action="/employee/search" method="get">
                       <input type="text" name="nameSearch" placeholder="Search by Name" value="${param.nameSearch}">
                       <button type="submit">Search</button>
                   </form>
    </div>
</section>

  <div class="container mt-4">
        <h2>Search Results</h2>

        <c:if test="${not empty employeeVar}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Speciality</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="employee" items="${employeeVar}">
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.name}</td>
                            <td>${employee.speciality}</td>
                            <td>
                                <!-- Edit Button -->
                                <a href="${pageContext.request.contextPath}/employee/edit/${employee.id}" class="btn btn-primary">Edit</a>

                                <!-- Detail Button -->
                                <a href="${pageContext.request.contextPath}/employee/detail?id=${employee.id}" class="btn btn-info btn-sm">Detail</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty employeeVar}">
            <div class="alert alert-info">No employees found.</div>
        </c:if>
    </div>

    <!-- Bootstrap JS and its dependencies -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<jsp:include page="../include/footer.jsp"/>