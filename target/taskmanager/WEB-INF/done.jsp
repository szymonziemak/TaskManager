<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        .actionButton {
            float: left;
            padding-right: 5px;
        }

        .taskProperty {
            text-align: left;
        }
    </style>
    <title>TaskManager</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">TaskManager</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Wyloguj się</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="login">Zaloguj się</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<div class="row justify-content-center">
    <div class="col-md-8">
        <br><br>
        <h3>Lista wykonanych zadań</h3>
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th scope="col">Nazwa</th>
                <th scope="col">Opis</th>
                <th scope="col">Akcje</th>
            </tr>
            </thead>
            <tbody>

            <c:if test="${not empty requestScope.doneTasks}">
                <c:forEach var="task" items="${requestScope.doneTasks}">
                    <tr>
                        <td>
                            <div class="taskProperty">
                                <c:out value="${task.name}"></c:out>
                            </div>
                        </td>
                        <td>
                            <div class="taskProperty">
                                <c:out value="${task.description}"></c:out>
                            </div>
                        </td>
                        <td>
                            <div class="actionButton">
                                <form action="restore" method="post">
                                    <button type="submit" class="btn btn-warning text-white" name="restoreButton"
                                            value="${task.task_id}">Przywróć
                                    </button>
                                </form>
                            </div>
                            <div class="actionButton">
                                <form action="edit" method="post">
                                    <button type="submit" class="btn btn-primary" name="editButton"
                                            value="${task.task_id}">Edytuj
                                    </button>
                                </form>
                            </div>
                            <div class="actionButton">
                                <form action="delete" method="post">
                                    <button type="submit" class="btn btn-danger" name="deleteButton"
                                            value="${task.task_id}">Usuń
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/" class="btn btn-light border">Pokaż listę zadań</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>