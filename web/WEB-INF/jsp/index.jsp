<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.dev.lab8.logic.Product" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>ХРАНИЛИЩЕ</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="http://www.w3schools.com/w3css/4/w3.css">

</head>

<button id="viewBtn" type="button">RUN</button>
<div id="tableView">
<div class="w3-container w3-table-all" align="center">

<table class="hide-me" align="left" border="2" cellpadding="1" cellspacing="1" style="width: 500px;">
    <caption>ТОВАРЫ</caption>
    <thead>
    <tr  class="w3-green">
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">PCS</th>
        <th scope="col">Tags</th>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
    </div>
</div>
<body>

</body>
</html>
