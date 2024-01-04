<jsp:include page="../include/header.jsp"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

 <h1>Welcome to the Admin Dashboard</h1>

    <h2>Users</h2>
    <table>
        <thead>
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Email</th>
                <!-- Add more user attributes here -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <!-- Add more user attributes here -->
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Services</h2>
    <table>
        <thead>
            <tr>
                <th>Service ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <!-- Add more service attributes here -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="service" items="${services}">
                <tr>
                    <td>${service.id}</td>
                    <td>${service.name}</td>
                    <td>${service.description}</td>
                    <td>${service.price}</td>
                    <!-- Add more service attributes here -->
                </tr>
            </c:forEach>
        </tbody>
    </table>


<jsp:include page="../include/footer.jsp"/>