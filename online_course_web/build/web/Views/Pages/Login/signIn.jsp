<%-- 
    Document   : signIn
    Created on : Nov 24, 2020, 11:40:00 AM
    Author     : TRAN VAN AN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <meta content="text/html; charset=UTF-8; X-Content-Type-Options=nosniff" http-equiv="Content-Type" />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
    <link href="Views/Css/common.css" type="text/css" rel="stylesheet">
     
    <link href="Views/Css/Login/login.css" type="text/css" rel="stylesheet"/>
    <title>ASQ - Đăng nhập</title>
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="home"><div class="logo"></div></a>
        </div>
        <div class="content">   
            <form action="sign-in" method="POST">
                <script>
                    if ("<c:out value="${ErrorSignIn}"/>" != "")
                        alert("<c:out value="${ErrorSignIn}"/>");
                </script>
                <div class="small-container">
                    <div class="title">
                        <h2>ĐĂNG NHẬP</h2>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Email:</span>
                        </div>
                        <div class="text-input">
                            <c:set var="varEmail" value="${Email}"/>
                            <input type="email" id="txtEmail" name="email" placeholder="Email của bạn" 
                                   value="<c:out value="${varEmail}"/>" maxlength="100" required/>
                        </div>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Mật khẩu: </span>
                        </div>
                        <div class="text-input">
                            <input type="password" id="txtPassword" name="password" <c:out value="${Password}" />  
                                   maxlength="100" placeholder="Mật khẩu của bạn" required/>
                        </div>
                    </div>
                    <div class="footer-form">
                        <div class="get-password div-footer-form">
                            <span><a href="get-password">Quên mật khẩu</a></span>
                        </div>
                        <div class="link-pages div-footer-form">
                            <span>Đăng ký <a href="sign-up">ở đây</a></span>
                        </div>
                    </div>      
                    <input type="submit" value="Đăng nhập" id="btnSubmit" class="btnSubmit">
                </div>
                        <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
            </form>

            <hr>
            <div class="contact-info">
                <div class="title-contact-info">
                    <span><i>Thông tin liên lạc</i></span>
                </div>
                <div class="icon-contact-info">
                    <div class="icon-contact" id="icon-facebook"></div>
                    <div class="icon-contact" id="icon-gmail"></div>
                </div> 
            </div>
        </div>   
        
    </div>
</body>
</html>