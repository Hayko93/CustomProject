
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>AutoMarket register</title>
    <link rel="stylesheet" href="./css/register.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-center h-100">
            <div class="card">
                <div class="card-header">
                    <h3>To register</h3>
                    <div class="d-flex justify-content-end social_icon">
                        <a href="https://facebook.com">
                        <span><i class="fab fa-facebook-square"></i><p6 hidden="hidden">https://facebook.com/</p6></span>
                        </a>
                        <a href="https://google.com">
                        <span><i class="fab fa-google-plus-square"><p6 hidden="hidden">https://google.com/</p6></i></span>
                        </a>
                        <a href="https://twitter.com">
                        <span><i class="fab fa-twitter-square" ><p6 hidden="hidden">https://twitter.com/</p6></i></span>
                        </a>
                    </div>
                </div>
                <div class="card-body">
                    <form id="form-inputs" action="register" method="post" enctype="multipart/form-data">
                            <input id="name-input" type="text"
                                   class="<c:out value="${requestScope.errorName != null ? 'danger-placeholder' : ''}"/>"
                                   placeholder="<c:out value="${requestScope.errorName != null ? requestScope.errorName : 'Name'}"/>"
                                   name="name"
                                   required/>
                        <br><br><br>
                            <input id="surname-input" type="text"
                                   placeholder="<c:out value="${requestScope.errorSurname != null ? requestScope.errorSurname : 'Surname'}"/>"
                                   class="<c:out value="${requestScope.errorSurname != null ? 'danger-placeholder' : ''}"/>"
                                   name="surname"
                                   required/>
                        <br><br><br>
                            <input id="email-input" type="text"
                                   placeholder="<c:out value="${requestScope.errorEmail != null ? requestScope.errorEmail : 'Email'}"/>"
                                   class="<c:out value="${requestScope.errorEmail != null ? 'danger-placeholder' : ''}"/>"
                                   name="email"
                                   required/>
                       <br><br><br>
                           <input id="password-input" type="password"
                                  placeholder="<c:out value="${requestScope.errorPassword != null ? requestScope.errorPassword : 'Password'}"/>"
                                  class="<c:out value="${requestScope.errorPassword != null ? 'danger-placeholder' : ''}"/>"
                                  name="password"
                                  required/>
                        <br><br><br>
                            <input id="confirm-password-input" type="password"
                                   placeholder="<c:out value="${requestScope.errorConfirmPassword != null ? requestScope.errorConfirmPassword : 'Confirm Password'}"/>"
                                   class="<c:out value="${requestScope.errorConfirmPassword != null ? 'danger-placeholder' : ''}"/>"
                                   name="confirmPassword"
                                   required/>
                        <br><br><br>
                            <input id="country-input" type="text"
                                   placeholder="<c:out value="${requestScope.errorCountry != null ? requestScope.errorCountry : 'Country'}"/>"
                                   class="<c:out value="${requestScope.errorCountry != null ? 'danger-placeholder' : ''}"/>"
                                   name="country"
                                   required/>
                        <br><br><br>
                        <div >
                            <p style="color: #ffd800">Select Profile Image</p>
                            <input id="file-input" type="file" name="file" accept="image/*">
                        </div><br>
                        <div class="form-group">
                            <button  class="button-icon" onclick="doRegister()">Check In</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer">
                    <div class="d-flex justify-content-center links">
                        <a id="home-page-id" href="autoMarket">Market Page</a>
                    </div>
                    <div class="d-flex justify-content-center links">
                        <a id="login-page-id" href="login">To Login</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
