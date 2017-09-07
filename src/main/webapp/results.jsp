<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp"%>

<html><body>

<div class="container-fluid">
    <h2>Search Results: </h2>
    <table
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>User ID</th>
            <th>Date of Birth</th>
            <th>Age</th>
        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.userid}</td>
                <td>${user.dateOfBirth}</td>
                <td>${user.calculateAge()}</td>
            </tr>
        </c:forEach>

    </table>

</div>

</body>
</html>
