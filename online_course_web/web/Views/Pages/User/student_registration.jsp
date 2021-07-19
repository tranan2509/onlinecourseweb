<%-- 
    Document   : student
    Created on : Jan 13, 2021, 6:16:41 AM
    Author     : ad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw==" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="Views/Css/Admin/admin.css" rel="stylesheet" type="text/css"/>
    <link href="Views/Css/Admin/adminn.css" rel="stylesheet" type="text/css"/>
    <script src="Views/Js/Admin/admin.js"></script>
    <link href="Views/Css/common.css" rel="stylesheet" type="text/css"/>
        <link href="Views/Css/Course/student.css" rel="stylesheet" type="text/css"/>

    <title>Học Viên</title>
</head>
<body>
    <div class="container-student">
        <div class="student_menu">
            <div class="logo">
                <img src="Views/Images/logo.png">
            </div>
            <div class="profile">
                <img src="Views/Images/iconLogo.png">
                <p><c:out value="${User.getName()}"/></p>
                <p><c:out value="${User.getRole().getRoleName()}"/></p>
            </div>
            <div class="menu_student">
                <button><a href="student"><i class="fas fa-home"></i></a>Trang chủ</button>
                <button><a href="student_registration" ><i class="fas fa-graduation-cap"></i>Khóa học của tôi</a></button>
                <button><a href="student_newcourse" ><i class="fas fa-book-open"></i> Khóa học mới</a></button>
                <button><a href="student_scores"><i class="fas fa-users"></i> Điểm số</a></button>
                <button><a href="account-profile_student"><i class="fas fa-user-graduate"></i>Thông tin cá nhân</a></button>
            </div>
        </div>
        <div class="wrapped">
            <div class="nav-bar">
                <h1>Học Viên</h1>
                <div class="drop-down" id="drop-down-user">
                    <a href="account-profile"><button>Thông tin cá nhân</button></a>
                    <a href="sign-in"><button>Đăng xuất</button></a>
                </div>
                <i class="fas fa-user-circle" onclick="ToggleDropDown('drop-down-user')"></i>
            </div>
            <div class="content">

            </div>
        </div>
    </div>
</body>
</html>


