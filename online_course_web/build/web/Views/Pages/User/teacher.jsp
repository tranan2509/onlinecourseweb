<%-- 
    Document   : teacherProfile
    Created on : Dec 25, 2020, 10:15:45 AM
    Author     : TRAN VAN AN
--%>

<%@page import="DAO.CourseDB"%>
<%@page import="Model.Course"%>
<%@page import="java.util.List"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
        <link rel="stylesheet" href="Views/Css/common.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <link rel="stylesheet" href="Views/Css/Profile/teachProfile.css">
        <title>ASQ | Profile</title>

        <script>
            function addCourse(text, idCourse)
            {
                var ListCourse = document.getElementById('list-approved-course-teacher');

                var newDiv = document.createElement('div');
                newDiv.setAttribute('class', 'div-course-teacher');
//                newDiv.setAttribute('id', 'course-teacher');
                ListCourse.appendChild(newDiv);

                var newText = document.createElement('p');
                newText.setAttribute('class', 'text-course-teacher');
//                newText.setAttribute('id', 'text-course-teacher' + '-idCourse');
                newText.innerHTML = text;
                newDiv.appendChild(newText);


                //link to edit the course
                var newLinkEdit = document.createElement('a');
                newLinkEdit.href = "Display_Course_Introduction_Teacher?courseid=" + idCourse;
                newDiv.appendChild(newLinkEdit);

                var newButtonEdit = document.createElement('button');
                newButtonEdit.setAttribute('class', 'btn-course');
                newButtonEdit.classList.add('btn-course-edit');
                newButtonEdit.innerHTML = '!';
                newLinkEdit.appendChild(newButtonEdit);

                var newDeleteLink = document.createElement('a');
                newDeleteLink.href = "Process_Delete_Course_Teacher?courseid=" + idCourse;
                newDiv.appendChild(newDeleteLink);

                var newButtonDelete = document.createElement('button');
                newButtonDelete.setAttribute('class', 'btn-course');
                newButtonDelete.classList.add('btn-course-delete');
                newButtonDelete.innerHTML = '-';
                newDeleteLink.appendChild(newButtonDelete);

            }

            function addNotApprovedCourse(text, idCourse)
            {
                var ListCourse = document.getElementById('list-not-approved-course-teacher');

                var newDiv = document.createElement('div');
                newDiv.setAttribute('class', 'div-course-teacher');
//                newDiv.setAttribute('id', 'course-teacher');
                ListCourse.appendChild(newDiv);

                var newText = document.createElement('p');
                newText.setAttribute('class', 'text-course-teacher');
//                newText.setAttribute('id', 'text-course-teacher' + '-idCourse');
                newText.innerHTML = text;
                newDiv.appendChild(newText);


                //link to edit the course
                var newLinkEdit = document.createElement('a');
                newLinkEdit.href = "Display_Course_Introduction_Teacher?courseid=" + idCourse;
                newDiv.appendChild(newLinkEdit);

                var newButtonEdit = document.createElement('button');
                newButtonEdit.setAttribute('class', 'btn-course');
                newButtonEdit.classList.add('btn-course-edit');
                newButtonEdit.innerHTML = '!';
                newLinkEdit.appendChild(newButtonEdit);

//                var newDeleteLink = document.createElement('a');
//                newDeleteLink.href = "Process_Delete_Course_Teacher?courseid=" + idCourse;
//                newDiv.appendChild(newDeleteLink);

                var newButtonDelete = document.createElement('button');
                newButtonDelete.setAttribute('class', 'btn-course');
                newButtonDelete.classList.add('btn-course-delete');
                newButtonDelete.innerHTML = '-';
                newButtonDelete.setAttribute("onclick", "deleteCourse(" + idCourse + ")");
                newDiv.appendChild(newButtonDelete);

            }

            function deleteCourse (courseid)
            {
                if (confirm("Bạn muốn xóa khóa học đã chọn?")) {
                    window.location.href="Process_Delete_Course_Teacher?courseid=" + courseid;;
                } else {
                  
                }
            }


        </script>
    </head>
    <body>
        <div class = 'div_menu'>
            <div class="small-container horizontal">
                <div class='div_logo'>
                    <a href="home"><img id='image_logo' src ="logo.png" ></a>
                </div>

                <div id='div_account' class="div_account">
                    <label id='label_account'><c:out value='${User.getName()}'/> </label>
                    <div class="drop-down account" id="drop-down-person">
                        <!--                        <a href="admin"><button>Thông tin cá nhân</button></a>
                                                <a href="sign-in"><button>Đăng xuất</button></a>-->
                    </div>    
                    <i class='fas fa-caret-down' onclick="ToggleDropDown('drop-down-person')"></i>    
                </div>
            </div>
        </div>
        <div class="small-container">
            <div class="profile">

                <p class="name-user"><c:out value='${User.getName()}'></c:out></p>
                <!--<p class="role-user">Student</p>-->    
            </div>
            <div class="course">
                <div class="title-course">
                    <input class="input_courseOption" id="input_ApprovedCourses" type="button" value="Các khóa học đã được duyệt">
                </div>
                <div class="div-list-course" id="list-approved-course-teacher">
                    <%
                        List<Course> Courses = (List<Course>) request.getAttribute("ApprovedCoursesTeacher");
                        if (Courses != null)
                            for (int i = 0; i < Courses.size(); i++) {%>
                    <script>
                        addCourse("<%= Courses.get(i).getName()%>", "<%= Courses.get(i).getCourseId()%>");
                    </script>
                    <%}
                    %>
                </div>

                <div class="title-course">
                    <input class="input_courseOption" id="input_ApprovedCourses" type="button" value="Các khóa học chưa được duyệt">
                </div>
                <div class="list-course" id="list-not-approved-course-teacher">
                    <%
                        List<Course> notApprovedCourse = (List<Course>) request.getAttribute("NotApprovedCoursesTeacher");
                        if (notApprovedCourse != null)
                            for (int i = 0; i < notApprovedCourse.size(); i++) {%>
                    <script>
                        addNotApprovedCourse("<%= notApprovedCourse.get(i).getName()%>", "<%= notApprovedCourse.get(i).getCourseId()%>");
                    </script>
                    <%}
                    %>
                </div>
                <form action="Display_Course_Introduction_Teacher?requirement=new" method="post" class="add-course-div">
                    <input type="submit" id="btnAddCourse" value="Thêm khóa học">
                    <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
                </form>

                <% String message = (String) request.getAttribute("message");
                    if (message != null) {%>
                <%="<script> alert('" + message + "');</script>"%>
                <% request.removeAttribute("message");%>
                <%}%>
            </div>
        </div>
    </body>

</html>
