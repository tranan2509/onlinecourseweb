<%-- 
    Document   : courses_managerment
    Created on : Jan 12, 2021, 3:25:20 PM
    Author     : TRAN VAN AN
--%>

<%@page import="com.google.gson.JsonArray"%>
<%@page import="java.util.List"%>
<%@page import="Model.User"%>
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
    <link href="Views/Css/common.css" rel="stylesheet" type="text/css"/>
    <link href="Views/Css/Admin/admin.css" rel="stylesheet" type="text/css"/>
    <script src="Views/Js/Admin/admin.js"></script>
    <script src="Views/Js/Admin/user_managerment.js"></script>
    <title>Admin</title>
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
                    <h1>Quản lý người dùng - Admin</h1>
                    <div class="drop-down" id="drop-down-user">
                        <a href="account-profile"><button>Thông tin cá nhân</button></a>
                        <a href="sign-in"><button>Đăng xuất</button></a>
                    </div>
                    <i class="fas fa-user-circle" onclick="ToggleDropDown('drop-down-user')"></i>
                </div>
                <div class="content">
                   <form action="user-managerment" method="post">
                        <div class="">
                            <div class="filter">
                                <select id="approved" class="select" name="userType">
                                    <option value="all" 
                                            <c:if test="${UserType == 'all'}"><c:out value="selected"/></c:if>
                                            >-- Bộ lọc người dùng --</option>
                                    <option value="teacher"
                                             <c:if test="${UserType == 'teacher'}"><c:out value="selected"/></c:if>
                                            >Giáo viên</option>
                                    <option value="student"
                                             <c:if test="${UserType == 'student'}"><c:out value="selected"/></c:if>
                                            >Sinh viên</option>
                                </select>
                                <input id="txtSearch" type="text" placeholder="Tìm kiếm" name="search" value="<c:out value="${Search}"/>"/>
                                <input type="submit" value="Tìm kiếm" class="btn-submit">
                            </div>
                            <div class="table-div">
                                 <div class="grid" id="gird-transport">
                                    <table border="1" cellpadding=0 id="table-transport" class="table">
                                        <thead class="thead-light">
                                            <tr>
                                                <th scope="col">Mã người dùng</th>
                                                <th scope="col">Tên người dùng</th>
                                                <th scope="col">Ngày sinh</th>
                                                <th scope="col">Giới tính</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Số điện thoại</th>
                                                <th scope="col">Chức vụ</th>
                                                <th scope="col">Chi tiết</th>
                                            </tr>
                                        </thead>
                                         <tbody id="tbody-table">
                                               <%
                                                   JsonArray usersJson = (JsonArray)request.getAttribute("UsersJson");
                                                   request.setAttribute("usersJson", usersJson);
                                                   if (usersJson != null )
                                                   {
                                                        %><script>
                                                                LoadUsersByNumberPages(<%= usersJson%>);
                                                        </script><%
                                                    }
                                               %>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="pagination">
                                    <i class="fas fa-angle-double-left" onclick="numberOfPages('start')"></i>
                                    <i class="fas fa-angle-left" onclick="numberOfPages('previous')"></i>
                                    <input type="number" value="1" min="1" name="numberOfPages" id="numberOfPages"
                                           onchange="reloadDataAccount()" readonly>
                                    <i class="fas fa-angle-right" onclick="numberOfPages('next')"></i>
                                    <i class="fas fa-angle-double-right" onclick="numberOfPages('end')"></i>
                                    <script>SetMaxNumberPages(<%= usersJson%>);</script>
                                </div>
                            </div>
                        </div>
                        <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
                    </form>
                </div>
            </div>
            <div class="front-div admin" id="front-div-info">
                 <script>
                        if ("<c:out value="${IsShowInfo}"/>" === "true")
                            document.getElementById('front-div-info').style.display = 'flex';
                        else
                            document.getElementById('front-div-info').style.display = 'none';
                </script>
                <div class="content-front large">
                    <div class="btn-close" id="btn-close">
                        <i class="far fa-times-circle" onclick="CloseFrontDivInfo()"></i>
                    </div>
                    <p class="header">Thông tin chi tiết</p>
                    <h2 style="text-align: center; width: 100%; margin-bottom: 40px; margin-top: 20px;"><c:out value="${NameInfo}"/></h2>
                    <form action="course-managerment?isSendEmail=true&isShowEmail=true" method="post">
                        <div class="small-container">
                            <div class="table-div">
                                 <div class="grid" id="gird-transport">
                                    <table border="1" cellpadding=0 id="table-transport" class="table">
                                        <thead class="thead-light">
                                            <tr>
                                                <th scope="col">Mã khóa học</th>
                                                <th scope="col">Tên khóa học</th>
                                                <th scope="col">Trạng thái</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tbody-table-info">
                                               <%
                                                   JsonArray coursesJson = (JsonArray)request.getAttribute("CoursesJson");
                                                   if (coursesJson != null )
                                                   {
                                                        %><script>
                                                                LoadCoursesInfoOfUser(<%= coursesJson%>);
                                                        </script><%
                                                    }
                                               %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
                    </form>    
                </div>
            </div>
        </div>
    </body>
</html>
