<%-- 
    Document   : student_newcourse
    Created on : Jan 21, 2021, 9:00:44 AM
    Author     : ad
--%>


<%@page import="Model.Course"%>
<%@page import="java.awt.List"%>
<%@page import="java.util.List"%>
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
                <button><a href="student_registration" ><i class="fas fa-graduation-cap"></i></a>Khóa học của tôi</button>
                <button><a href="#" ><i class="fas fa-book-open"></i></a> Khóa học mới</button>
                <button><a href="#"><i class="fas fa-users"></i></a> Điểm số</button>
                <button><a href="account-profile_student"><i class="fas fa-user-graduate"></i></a>Thông tin cá nhân</a></button>
                
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
            <div class="introduce-profile lagre"> 
                <div class="title-profile">
                    <p>Thông tin khóa học mới:</p>
                </div>
                <script>
                    function LoadCourses(courseId, name, obj, modifiedDate)
                    {
                        var tbody_table = document.getElementById("tbody-table");
                        //tbody_table.empty();
                        var NewTr = document.createElement("tr");

                        var CourseId = document.createElement('td');
                        CourseId.setAttribute('class', 'center');
                        CourseId.innerHTML = courseId;
                        NewTr.appendChild(CourseId);

                        var Name = document.createElement('td');
                        Name.innerHTML = name;
                        NewTr.appendChild(Name);

                        var Objective = document.createElement('td');
                        Objective.innerHTML = obj;
                        NewTr.appendChild(Objective);

                        
                        
                        var ModifiedDate = document.createElement('td');
                        ModifiedDate.setAttribute('class', 'center');
                        
                        var Input_date = document.createElement('a');
                        Input_date.setAttribute('name', 'dateModifed');
                        ModifiedDate.appendChild(Input_date);
                        Input_date.innerHTML = modifiedDate;
                         
                        ////+"/"+ modifiedDate.getMonth()+"/" +modifiedDate.getDate();
//                        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
//                        //java.sql.Date dateReportDate =  Date.valueOf(dateReport); //formatDate.parse(dateReport);
//                        java.util.Date modifiedDate = (java.util.Date) formatDate.parse(dateReport);
//                        java.util.Date modifiedDate = new java.util.Date();
//                        java.sql.Date ModifiedDate2 = new java.sql.Date(modifiedDate.getTime());
                        NewTr.appendChild(ModifiedDate);
                        
                        
                        var Infor = document.createElement('td');
                        Infor.setAttribute('class', 'center');
                        
                        var alink = document.createElement('a');
                        alink.setAttribute('href', 'Display_Course_Introduction_Student?courseid='+courseId);
                        Infor.appendChild(alink);
                        alink.innerHTML = '<i class="fas fa-info-circle"></i>';
                        
                        
                        NewTr.appendChild(Infor);
                        
                        tbody_table.appendChild(NewTr); 
                    }
                </script>
                <div class="table-div" id ="update_table">
                     <div class="grid" id="gird-transport">
                        <table border="1" cellpadding=0 id="table-transport" class="table">
                            <thead class="thead-light">
                                <tr>
                                    <th scope="col">Mã khóa học</th>
                                    <th scope="col">Tên khóa học</th>
                                    <th scope="col">Mục tiêu khóa học</th>
                                    <th scope="col">Thời gian</th>
                                    <th scope="col">Chi tiết khóa học</th>
                                    
                                </tr>
                            </thead>
                            <tbody id="tbody-table">
                                
                                   <%
                                       List<Course> courses = (List<Course>)request.getAttribute("Courses");%>
                            
                                       <%if (courses != null )
                                       {
                                            %>
                                            <script>tbody_table.empty();</script> 
                                            <%
                                           for (int i = 0; i < courses.size(); i++)
                                           {
                                                %> <script>LoadCourses("<%=courses.get(i).getCourseId()%>", "<%=courses.get(i).getName()%>", "<%=courses.get(i).getObjective()%>", "<%=courses.get(i).getModifiedDate()%>");
                                                </script> <%
//                                               
                                           }
                                       }
                                   %>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>