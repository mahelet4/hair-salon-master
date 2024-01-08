<jsp:include page="include/header.jsp"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">
    <div class="container1">
        <h1 class="custom-h1" style="text-align: center; font-size: 36px; margin-bottom: 20px;">Welcome to Our Services</h1>

        <!-- Image container with equal-sized columns -->
        <div class="image-container row">
            <div class="col-md-4 mb-4">
                <img src="https://cdn-cjhgk.nitrocdn.com/CXxGixRVyChwAxySbAyltuCiQXRKaWDN/assets/images/optimized/rev-c59ff5c/www.newbeauty.com/wp-content/uploads/2020/02/18663-hairsalonjpg.jpg" alt="Image 2" class="img-fluid" style="height: 200px;">
            </div>
            <div class="col-md-4 mb-4">
                <img src="https://s3.amazonaws.com/static-webstudio-accorhotels-usa-1.wp-ha.fastbooking.com/wp-content/uploads/sites/8/2021/09/30233832/LeSalon_LifestyleActionShots_0281-1170x780.jpg" alt="Image 3" class="img-fluid" style="height: 200px;">
            </div>
            <div class="col-md-4 mb-4">
                <img src="https://ik.imagekit.io/parlon/Branch%20Photos/2302/FAF15C32-88E4-4E52-BBAF-F789A54CAE09.jpeg" alt="Image 4" class="img-fluid" style="height: 200px;">
            </div>
        </div>

        <!-- Services in a grid layout -->
        <h1 class="custom-h1" style="text-align: center; font-size: 36px; margin-bottom: 20px;">Here are Our Services</h1>
        <div class="row">
            <c:forEach var="service" items="${services}" varStatus="status">
                <div class="col-md-2 col-sm-4 mb-4">
                    <img src="${pageContext.request.contextPath}/pub/images/${service.imageUrl}" alt="${service.name}" width="200" height="150">
                    <p class="text-center">${service.name}</p>
                </div>
                <!-- Break to the new line after every 5 images -->
                <c:if test="${status.count % 5 == 0}">
                    <div class="w-100"></div>
                </c:if>
            </c:forEach>
        </div>
    </div>
<jsp:include page="include/footer.jsp"/>



