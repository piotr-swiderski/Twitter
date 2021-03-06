<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <ul class="navbar-nav mr-auto lh-100">
                <li class="nav-item">
                    <a class="nav-link" methods="GET" href="posts">Posts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" methods="GET" href="users">Users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)">Disabled</a>
                </li>
            </ul>
            <div class="d-flex align-items-center p-1 my-1 text-white-50 bg-blue rounded box-shadow">
                <img class="mr-3" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" width="48"
                     height="48">
                <div class="lh-100">
                    <h6 class="mb-0 mr-3 text-white lh-100">${fn:toUpperCase(login)}</h6>
                </div>
            </div>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="text" placeholder="Search">
                <button class="btn btn-success my-2 my-sm-0 mr-2" type="button">Search</button>
            </form>
            <form class="form-inline my-2 my-lg-0 mr-3" method="post" action="LogoutServlet">
                <button value="logout" class="btn btn-success my-2 my-sm-0 mr-3" name="logout" type="submit">Logout</button>
            </form>
        </div>
    </nav>
</div>


<div class="container">
    <c:if test="${followedUsers.size() > 0}">
        <h1 class="text-center">Following</h1>
        <c:forEach items="${followedUsers}" var="user">
            <div class="card text-black bg-white my-4 text-center">
                <div class="card-body">
                    <h5 class="card-title">${user.login}</h5>
                    <p class="card-text">${user.name} ${user.lastName}</p>
                    <h6 class="card-subtitle mb-2"><fmt:formatDate value="${user.dateOfRegistration}"
                                                                   pattern="yyy-MM-dd HH-mm-ss"></fmt:formatDate></h6>
                </div>
            </div>
            <form class="form-inline my-2 my-lg-0" method="post" action="followServlet">
                <button value="${user.login}" class="btn btn-success my-2 my-sm-0" name="userToNotFollow" type="submit">
                    Not Follow
                </button>
            </form>
        </c:forEach>
    </c:if>
</div>

<div class="container">
    <c:if test="${notFollowedUsers.size() > 0}">
        <h1 class="text-center">Others users</h1>
        <c:forEach items="${notFollowedUsers}" var="user">
            <div class="card text-black bg-white my-4 text-center">
                <div class="card-body">
                    <h5 class="card-title">${user.login}</h5>
                    <p class="card-text">${user.name} ${user.lastName}</p>
                    <h6 class="card-subtitle mb-2"><fmt:formatDate value="${user.dateOfRegistration}"
                                                                   pattern="yyy-MM-dd HH-mm-ss"></fmt:formatDate></h6>
                </div>
            </div>
            <form class="form-inline my-2 my-lg-0" method="post" action="followServlet">
                <button value="${user.login}" class="btn btn-success my-2 my-sm-0" name="userToFollow"
                        type="submit">Follow
                </button>
            </form>
        </c:forEach>
    </c:if>
</div>

<br><br>


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
