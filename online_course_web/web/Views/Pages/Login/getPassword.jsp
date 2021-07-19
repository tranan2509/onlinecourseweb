<%-- 
    Document   : getPassword
    Created on : Nov 24, 2020, 11:41:03 AM
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
            <!--<form action="get-password" method="POST">-->
                <div class="small-container">
                    <div class="title">
                        <h2>Quên mật khẩu</h2>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Email:</span>
                        </div>
                        <form action="send-mail" method="POST"> 
                            <script>
                                if ("<c:out value="${ErrorSendMail}"/>" != "")
                                    alert("<c:out value="${ErrorSendMail}"/>");
                            </script>
                            <div class="text-input" id="input-email">
                                <input type="email" id="txtEmail" name="email" value="<c:out value="${Email}"/>" required/>
                                <input type="submit" value="Gửi" id="btnSendCode"/>
                            </div>
                            <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
                        </form>
                    </div>
                    <form action="get-password" method="POST">
                         <script>
                                if ("<c:out value="${ErrorGetPassword}"/>" != "")
                                    alert("<c:out value="${ErrorGetPassword}"/>");
                            </script>
                        <div class="text-input-login">
                            <div class="label-input">
                                <span>Mã: </span>
                            </div>
                            <div class="text-input">
                                <input type="text" maxlength="6" id="txtCode" name="code" <c:out value="${Code}"/> placeholder="Một mã gồm 6 số được gửi tới email đăng ký"/>
                             </div>
                        </div>
                        <div class="footer-form">
                            <div class="link-pages div-footer-form">
                                <span><a href="sign-in">Đăng nhập</a></span>
                            </div>          
                        </div>
                        <input type="submit" value="Tiếp tục" id="btnSubmit" class="btnSubmit">
                        <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
                    </form>
                </div>
            <!--</form>-->
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