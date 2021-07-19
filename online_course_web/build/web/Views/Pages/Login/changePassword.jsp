<%-- 
    Document   : changePassword
    Created on : Nov 24, 2020, 11:41:20 AM
    Author     : TRAN VAN AN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
    <link href="Views/Css/common.css" type="text/css" rel="stylesheet">
    
    <link href="Views/Css/Login/login.css" type="text/css" rel="stylesheet"/>
    <title>ASQ - Quên mật khẩu</title>
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="home"><div class="logo"></div></a>
        </div>
        <div class="content">
            <script>
                if ("<c:out value="${ErrorChangePassword}"/>" != "")
                    alert("<c:out value="${ErrorChangePassword}"/>");
            </script>
            <form action="change-password" method="POST">
                <div class="small-container">
                    <div class="title">
                        <h2>Quên mật khẩu</h2>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Mật khẩu: </span>
                        </div>
                        <div class="text-input" id="input-email">
                            <input type="password" id="txtPassword" name="password" <c:out value="${Password}" /> placeholder="Mật khẩu mới" required/>
                        </div>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Xác nhận mật khẩu:</span>
                        </div>
                        <div class="text-input">
                            <input type="password" maxlength="6" name="rePassword"  <c:out value="${RePassword}" /> id="txtConfirmPassword" placeholder="Xác nhận mật khẩu mới" required/>
                        </div>
                    </div>
                    <input type="submit" value="Hoàn thành" id="btnSubmit" class="btnSubmit">
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