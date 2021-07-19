<%-- 
    Document   : adminn
    Created on : Jan 8, 2021, 12:38:32 PM
    Author     : TRAN VAN AN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <meta content="text/html; charset=UTF-8; X-Content-Type-Options=nosniff" http-equiv="Content-Type" />
     <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw==" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="Views/Css/Admin/admin.css" rel="stylesheet" type="text/css"/>
    <link href="Views/Css/common.css" rel="stylesheet" type="text/css"/>
    <script src="Views/Js/Admin/admin.js"></script>
    <title>Cập nhật tài khoản |ASQ</title>
</head>
<body>
    <div class="container-full">
        <div class="side-bar">
            <div class="logo">
                <img src="Views/Images/logo.png">
            </div>
            <div class="profile">
                <img src="Views/Images/iconLogo.png">
                <p><c:out value="${User.getName()}"/></p>
                <p><c:out value="${User.getRole().getRoleName()}"/></p>
            </div>
            <div class="menu">
                <button><a href="admin"><i class="fas fa-home"></i>Trang chủ</a></button>
                <button><a href="account-managerment"><i class="fas fa-address-book"></i> Quản lý tài khoản</a></button>
                <button><a href="course-managerment"><i class="fas fa-book-open"></i> Quản lý khóa học</a></button>
                <button><a href="user-managerment"><i class="fas fa-users"></i> Quản lý người dùng</a></button>
                <button><a href="account-profile"><i class="fas fa-user"></i> Thông tin cá nhân</a></button>
            </div>
        </div>
        <div class="wrapped">
            <div class="nav-bar">
                <h1>Thông tin cá nhân - Admin</h1>
                <div class="drop-down" id="drop-down-user">
                    <a href="account-profile"><button>Thông tin cá nhân</button></a>
                    <a href="sign-in"><button>Đăng xuất</button></a>
                </div>
                <i class="fas fa-user-circle" onclick="ToggleDropDown('drop-down-user')"></i>
            </div>
            <div class="content">
                <div class="introduce-profile lagre">
                    <div class="title-profile">
                        <p>Giới thiệu</p>
                        <div class="drop-down setting" id="drop-down-setting">
                            <button onclick="ShowFrontDivEditInfo()">Đổi thông tin cá nhân</button>
                            <button onclick="ShowFrontDivEditPass()">Đổi mật khẩu</button>
                        </div>
                        <i class="fas fa-cog" id="setting" onclick="ToggleDropDown('drop-down-setting')"></i> 
                    </div>
                    <div class="info-profile">
                        <div class="info-detail-profile">
                            <p class="title-info-detail-profile">Chức vụ: </p>
                            <p class="describe">${Role.getRoleName()}</p>
                        </div>
                        <div class="info-detail-profile">
                            <p class="title-info-detail-profile">Ngày sinh: </p>
                            <p class="describe">${User.getDateOfBirth()}</p>
                        </div>
                        <div class="info-detail-profile">
                            <p class="title-info-detail-profile">Giới tính: </p>
                            <p class="describe">${User.isGender() == true ? "Nam" : "Nữ"}</p>
                        </div>
                        <div class="info-detail-profile">
                            <p class="title-info-detail-profile">Email: </p>
                            <p class="describe">${User.getEmail()}</p>
                        </div>
                        <div class="info-detail-profile">
                            <p class="title-info-detail-profile">Số điện thoại: </p>
                            <p class="describe">${User.getPhone()}</p>
                        </div>
                    </div>
                </div>
            <div class="front-div admin" id="front-div">
                <script>
                    if ("<c:out value="${ErrorEditInformation}"/>" != "")
                        alert("<c:out value="${ErrorEditInformation}"/>");
                    if ("<c:out value="${IsShowEditInfo}"/>" == "true")
                        document.getElementById('front-div').style.display = 'flex';
                    else
                        document.getElementById('front-div').style.display = 'none';
                </script>
                <div class="content-front">
                    <div class="btn-close" id="btn-close">
                        <i class="far fa-times-circle" onclick="CloseFrontDivEditInfo()"></i>
                    </div>
                    <p class="header">Cập nhật tài khoản</p>
                    <form action="edit-information" method="post">
                        <div class="small-container">
                            <input type="hidden" name="userIdEdit" required value="<c:out value="${User.getUserId()}"/>">
                            <div class="field-input">
                                <p>Họ tên:</p>
                                <input type="text" name="nameEdit" required value="<c:out value="${User.getName()}"/>">
                            </div>
                            <div class="field-input">
                                <p>Ngày sinh:</p>
                                <input type="date" name="dateOfBirthEdit" required value="<c:out value="${User.getDateOfBirth()}"/>">
                            </div>
                             <div class="field-input">
                                <p>Giới tính:</p>
                                <!--<input type="text" name="genderEdit" value="<c:out value="${User.isGender()}"/>">-->
                                <select id="genderEdit" name="genderEdit">
                                    <option value="true"
                                     <c:if test="${User.isGender() == 'true'}"><c:out value="selected"/></c:if>
                                    >Nam</option>
                                    <option value="false"
                                     <c:if test="${User.isGender() == 'false'}"><c:out value="selected"/></c:if>
                                    >Nữ</option>
                                </select>
                            </div>
                             <div class="field-input">
                                <p>Email:</p>
                                <input type="text" name="emailEdit" required value="<c:out value="${User.getEmail()}"/>">
                            </div>
                             <div class="field-input">
                                <p>Điện thoại:</p>
                                <input type="text" name="phoneEdit" required value="<c:out value="${User.getPhone()}"/>">
                            </div>
                            <input type="submit" value="Cập nhật" required class="btn-submit center btn-submit-front" id="btnUpdate"/>
                        </div>
                       <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
                    </form>    
                </div>
            </div>
            <div class="front-div admin" id="front-div-change-password">
                <script>
                    if ("<c:out value="${ErrorChangePassword}"/>" != "")
                        alert("<c:out value="${ErrorChangePassword}"/>");
                    if ("<c:out value="${IsShowEditPass}"/>" == "true")
                        document.getElementById('front-div-change-password').style.display = 'flex';
                    else
                        document.getElementById('front-div-change-password').style.display = 'none';
                </script>
                <div class="content-front">
                    <div class="btn-close" id="btn-close">
                        <i class="far fa-times-circle" onclick="CloseFrontDivEditPass()"></i>
                    </div>
                    <p class="header">Cập nhật mật khẩu</p>
                    <form action="change-password" method="post">
                        <div class="small-container">
                            <input type="hidden" name="userId" value="<c:out value="${User.getUserId()}"/>">
                            <div class="field-input">
                                <p>Mật khẩu cũ:</p>
                                <input type="password" name="oldPassword" required value="<c:out value="${OldPassword}"/>">
                            </div>
                            <div class="field-input">
                                <p>Mật khẩu mới:</p>
                                <input type="password" name="password" required value="<c:out value="${Password}"/>">
                            </div>
                            <div class="field-input">
                                <p>Xác nhận mật khẩu:</p>
                                <input type="password" name="rePassword" required value="<c:out value="${RePassword}"/>">
                            </div>
                            <input type="submit" value="Cập nhật" class="btn-submit center btn-submit-front" id="btnUpdatePassword"/>
                        </div>
                     <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">               
                    </form>    
                </div>
            </div>            
        </div>
    </div>
</body>
</html>