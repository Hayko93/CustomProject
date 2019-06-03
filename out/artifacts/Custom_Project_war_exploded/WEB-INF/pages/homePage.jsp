<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" href="./css/homePage.css" type="text/css">
</head>
<body>
    <form class="form" action="homePage" method="get">
        <div class="dropdown">
            <h2 class="h2">Brand</h2>
            <input type="checkbox" id="checkbox-input">
            <label for="checkbox-input">Choose</label>
            <ul>
                <br>
                <li>Mercedes-benz</li>
                <br>
                <li>BMW</li>
                <br>
                <li>Nissan</li>
                <br>
                <li>Suzuki</li>
                <br>
                <li>Toyota</li>
            </ul>
        </div>
        <h2 class="h22">Release</h2>
        <div class="dropdown1">
            <input type="checkbox" id="checkbox-input2">
            <label id="label">Select</label>
            <ul id="ul-id">
                <li>2010-2011</li>
                <li>2011-2012</li>
                <li><2012-2013</li>
                <li>2013-2014</li>
                <li>2014-2015</li>
                <li>2015-2016</li>
                <li>2016-2017</li>
                <li>2017-2018</li>
                <li>2018-2019</li>
            </ul>
        </div>
        <div>
            <a id="a-href" href="login">Logout</a>
        </div>
        <div class="box">
            <div class="container-4">
                <input type="search" id="search" placeholder="Search..." />
            </div>
        </div>
        <div>
            <img src='<c:out value="${sessionScope.client.imageUrl}"/>'>
        </div>
    </form>
</body>
</html>
