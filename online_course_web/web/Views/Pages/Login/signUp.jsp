<%-- 
    Document   : signUp
    Created on : Nov 24, 2020, 11:40:43 AM
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
    <title>ASQ - Đăng ký</title>
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="home"><div class="logo"></div></a>
        </div>
        <div class="content">
            <form action="sign-up" method="POST">
                <script>
                    if ("<c:out value="${ErrorSignUp}"/>" != "")
                        alert("<c:out value="${ErrorSignUp}"/>");
                </script>
                <div class="small-container">
                    <div class="title">
                        <h2>ĐĂNG KÝ</h2>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Tên đầy đủ:</span>
                        </div>
                        <div class="text-input">
                            <input type="text" id="txtName" name="name" value="<c:out value="${Name}"/>" placeholder="Tên đầy đủ" required/>
                        </div>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Ngày sinh: </span>
                        </div>
                        <div class="text-input">
                            <input type="date" id="dtBirthDate" name="birthDate" value="<c:out value="${BirthDate}"/>" required/>
                        </div>
                    </div>
                    <div class="text-input-login horizontal gender">
                        <div class="label-input">
                            <span>Giới tính: </span>
                        </div>
                        <div class="horizontal">
                            <input type="radio" id="rdoMale" name="gender" value="male" checked class="horizontal gender-sign-in"
                                    <c:if test="${Gender == 'male'}">
                                               <c:out value="checked"/>
                                           </c:if>
                                   />Nam
                            <input type="radio" id="rdoFemale" name="gender" value="female" class="gender-sign-in"
                                   <c:if test="${Gender == 'female'}">
                                               <c:out value="checked"/>
                                           </c:if>
                                   />Nữ
                        </div>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Email:</span>
                        </div>
                        <div class="text-input">
                            <input type="email" id="txtEmail" name="email" value="<c:out value="${Email}"/>" placeholder="Email đăng ký" required/>
                        </div>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Điện thọai: </span>
                        </div>
                        <div class="text-input">
                            <input type="tel" id="txtPhone" name="phone" value="<c:out value="${Phone}"/>" placeholder="Số điện thoại đăng ký" required/>
                        </div>
                    </div>
                    <div class="text-input-login horizontal gender">
                        <div class="label-input">
                            <span>Vai trò: </span>
                        </div>
                        <div class="horizontal">
                            <input type="radio" id="rdoMale" name="role" value="student" checked class="horizontal gender-sign-in"
                                   <c:if test="${Role == 'student'}">
                                               <c:out value="checked"/>
                                           </c:if>
                                   />Học viên
                            <input type="radio" id="rdoFemale" name="role" value="teacher" class="gender-sign-in"
                                   <c:if test="${Role == 'teacher'}">
                                               <c:out value="checked"/>
                                           </c:if>
                                   />Giáo viên
                        </div>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Mật khẩu:</span>
                        </div>
                        <div class="text-input">
                            <input type="password" id="txtPassword" name="password" placeholder="Mật khẩu đăng ký" required/>
                        </div>
                    </div>
                    <div class="text-input-login">
                        <div class="label-input">
                            <span>Xác nhận mật khẩu:</span>
                        </div>
                        <div class="text-input">
                            <input type="password" id="txtConfirmPassword" name="rePassword" placeholder="Xác nhận lại mật khẩu" required/>
                        </div>
                    </div>
                    <div class="footer-form">
                        <div class="agree-policy div-footer-form">
                            <input type="checkbox" required> Tôi đã đọc <a href="#">chính sách</a>
                        </div>
                        <div class="link-pages div-footer-form">
                            <span>Đã có <a href="sign-in">tài khoản</a></span>
                        </div>          
                    </div>
                    <input type="submit" value="Đăng ký" id="btnSubmit" class="btnSubmit">
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
