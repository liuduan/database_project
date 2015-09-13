<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />

<style>
    .header {
        color: #36A0FF;
        font-size: 27px;
        padding: 10px;
    }

    .bigicon {
        font-size: 35px;
        color: #36A0FF;
    }
</style>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="well well-sm">
				<spring:url value="/contact" var="userActionUrl" />
                <form action="${userActionUrl}" class="form-horizontal" method="POST">
                    <fieldset>
                        <legend class="text-center header">Contact us</legend>

                        <div class="form-group">
                            <span class="col-md-1 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-10">
                                <input id="fname" name="name" type="text" placeholder="First Name" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-10">
                                <input id="lname" name="name" type="text" placeholder="Last Name" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 text-center"><i class="fa fa-envelope-o bigicon"></i></span>
                            <div class="col-md-10">
                                <input id="email" name="email" type="text" placeholder="Email Address" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-10">
                                <input id="phone" name="phone" type="text" placeholder="Phone" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 text-center"><i class="fa fa-pencil-square-o bigicon"></i></span>
                            <div class="col-md-10">
                                <textarea class="form-control" id="message" name="message" placeholder="Enter your massage for us here. We will get back to you within 2 business days." rows="7"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Send</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>