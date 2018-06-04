<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:url value="/style.css" var="styleUrl"/>
        <c:url value="/index.js" var="indexScriptUrl"/>
        <c:url value="/login.js" var="loginScriptUrl"/>
        <c:url value="/profile.js" var="profileScriptUrl"/>
        <c:url value="/product.js" var="productScriptUrl"/>
        <c:url value="/order.js" var="orderScriptUrl"/>
        <c:url value="/back-to-profile.js" var="backToProfileScriptUrl"/>
        <c:url value="/logout.js" var="logoutScriptUrl"/>
        <link rel="stylesheet" type="text/css" href="${styleUrl}">
        <script src="${indexScriptUrl}"></script>
        <script src="${loginScriptUrl}"></script>
        <script src="${profileScriptUrl}"></script>
        <script src="${productScriptUrl}"></script>
        <script src="${orderScriptUrl}"></script>
        <script src="${backToProfileScriptUrl}"></script>
        <script src="${logoutScriptUrl}"></script>
        <title>App</title>
    </head>
<body>
<div id="logout-content" class="hidden content">
    <button id="logout-button" class="button">Logout</button>
    <button id="products-button" class="button">List Products</button>
    <button id="orders-button" class="button">See Your Orders</button>
    <button id="orders2-button" class="button">List Your Detailed Orders</button>
</div>

<div id="login-content" class="content">
    <h1>Login</h1>
    <form id="login-form" onsubmit="return false;">
        <input type="text" name="id" placeholder="Type your id">
        <button id="login-button" class="button">Login</button>
    </form>
</div>

<div id="profile-content" class="hidden content">
    <h3>Welcome, <span id="user-contactname"></span>!</h3>
    <p>Id: <span id="user-id"></span></p>
    <p><span id="user-companyname"></span></p>

</div>

<div id="back-to-profile-content" class="hidden content">
    <button onclick="onBackToProfileClicked();">Back to profile</button>
</div>


<div id="products" class="hidden content">
    <h2>Available Products</h2>
</div>

<div id="orders" class="hidden content">
    <h2>Orders</h2>
</div>

<div id="orders2" class="hidden content">
    <h2>Detailed Orders</h2>
</div>

</body>
</html>
