<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="../include/header.jsp" %>
<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Create Customer</h1>
            </div>
        </div>
    </div>
</section>



<section class="pt-5 pb-5">
    <div class="container">
        <c:if test="${not empty success}">
            <div class="row justify-content-center">
                <div class="col-6 text-center">
                    <div class="alert alert-success" role="alert">
                            ${success}
                    </div>
                </div>
            </div>
        </c:if>

        <!-- the action attribute on the form tag is the URL that the form will submit to when then user clicks the submit button -->
        <form method="get" action="/customer/createSubmit">
            <input type="hidden" name="id" value="${form.id}">

            <div class="mt-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="firstNameHelp" value="${form.firstName}">
                <div id="firstNameHelp" class="form-text">Please let us know your first name</div>
            </div>
            <c:if test="${errors.hasFieldErrors('firstName')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('firstName')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>


            <div class="mt-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${form.lastName}">
            </div>
            <c:if test="${errors.hasFieldErrors('lastName')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('lastName')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>



            <div class="mt-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${form.phone}">
            </div>
            <c:if test="${errors.hasFieldErrors('phone')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('phone')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>


            <div class="mt-3">
                <label for="city" class="form-label">City</label>
                <input type="text" class="form-control" id="city" name="city" value="${form.city}">
            </div>
            <c:if test="${errors.hasFieldErrors('city')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('city')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>


            <button type="submit" class="btn btn-primary mt-4">Submit</button>
        </form>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>
