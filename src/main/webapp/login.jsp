<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import=" java.util.* " %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background: #f5f5f5
        }

        .blue {
            background-color: #00b5ec !important;
        }
    </style>
</head>
<body>
<main role="main" class="container">
    <div class="my-3 p-3 bg-white rounded box-shadow">
        <h4 class="text-center mb-4 mt-1">Sign up</h4>
        <hr>

        <c:if test="${errors != null}">
            <c:forEach var="i" begin="0" end="${errors.size()-1}">
                <div class="alert alert-warning">
                    <strong>${errors.get(i).header}</strong>
                    <strong>${errors.get(i).message}</strong>
                </div>
            </c:forEach>
        </c:if>

        <form action="login" method="POST">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="login" class="form-control" placeholder="Login" type="text" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="*****" type="password" aria-required>
                </div>
            </div>
            <div class="form-group">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block blue"> Sign up</button>
                </div>
                <p class="text-center"><a href="register" class="btn">Registration</a></p>
            </div>
        </form>
    </div>
</main>

</body>
</html>
