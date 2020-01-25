<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import=" java.util.* " %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
            integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="main.css">
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

<div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="margin:24px 0;">
        <a class="navbar-brand" href="users">Twitter</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navb">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" methods="GET" href="posts">Posts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)">Disabled</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="text" placeholder="Search">
                <button class="btn btn-success my-2 my-sm-0" type="button">Search</button>
            </form>
            <form class="form-inline my-2 my-lg-0" method="post" action="LogoutServlet">
                <button value="logout" class="btn btn-success my-2 my-sm-0" name="logout" type="submit">Logout</button>
            </form>
        </div>
    </nav>
</div>

<%--<main role="posts" class="container">--%>
<%--    <div class="card my-4">--%>
<%--        <h5 class="card-header">Add tweet:</h5>--%>
<%--        <div class="card-body">--%>
<%--            <form name="users" method="POST">--%>
<%--                <div class="form-group">--%>
<%--                    <textarea name="message" class="form-control" rows="3"></textarea>--%>
<%--                </div>--%>
<%--                <button type="submit" class="btn btn-primary">Add tweet</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</main>--%>

<h1 class="text-center">followed user</h1>
<div class="container">
    <c:if test="${followedUsers.size() > 0}">
        <c:forEach items="${followedUsers}" var="user">
            <div class="card text-white bg-info my-4">
                <div class="card-body">
                    <h5 class="card-title">login: ${user.login} password: ${user.password}</h5>
                    <p class="card-text"> name: ${user.name} surname: ${user.lastName}</p>
                    <h6 class="card-subtitle mb-2"><fmt:formatDate value="${user.dateOfRegistration}"
                                                                   pattern="yyy-MM-dd HH-mm-ss"></fmt:formatDate></h6>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>

<h1 class="text-center">not followed user</h1>
<div class="container">
    <c:if test="${notFollowedUsers.size() > 0}">
        <c:forEach items="${notFollowedUsers}" var="user">
            <div class="card text-white bg-info my-4">
                <div class="card-body">
                    <h5 class="card-title"> login: ${user.login} password: ${user.password}</h5>
                    <p class="card-text"> name: ${user.name} surname: ${user.lastName}</p>
                    <h6 class="card-subtitle mb-2"><fmt:formatDate value="${user.dateOfRegistration}"
                                                                   pattern="yyy-MM-dd HH-mm-ss"></fmt:formatDate></h6>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>
</body>
</html>
