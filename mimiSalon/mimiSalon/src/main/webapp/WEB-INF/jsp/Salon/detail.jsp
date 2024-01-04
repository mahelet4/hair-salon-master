<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">
<%@ include file="../include/header.jsp" %>

<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Customer Detail</h1>
            </div>
        </div>
    </div>
</section>


<section>
    <table>
        <div class="card" style="width: 18rem;">

            <div class="card-body">
                <h5 class="card-title"><a href="/customer/detail?id=${customer.id}">${customer.firstName} ${customer.lastName}</a></h5>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>

                <a href="/customer/detail?id=${customer.id}" class="btn btn-primary">Customer Details</a>
            </div>
        </div>

    </table>
</section>
<%@ include file="../include/footer.jsp" %>