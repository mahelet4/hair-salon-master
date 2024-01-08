<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/header.jsp"/>

<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">About Page</h1>
                 <img src="${pageContext.request.contextPath}/pub/images/${service.imageUrl}" alt="${service.name}" class="card-img-top" style="height: 200px; object-fit: cover;">
            </div>
        </div>
    </div>
</section>

<jsp:include page="include/footer.jsp"/>