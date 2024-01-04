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

 <section class="pt-5 pb-5">
   <div class="container">
     <form method="get" action="/employee/createSubmit">

            <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="firstNameHelp" value="${form.firstName}">
                <div id="firstNameHelp" class="form-text">Please let us know your first name</div>
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${form.lastName}">
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${form.phone}">
            </div>
            <div class="mb-3">
                <label for="speciality" class="form-label">Speciality</label>
                <input type="text" class="form-control" id="speciality" name="speciality" value="${form.speciality}">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
     </form>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>
```