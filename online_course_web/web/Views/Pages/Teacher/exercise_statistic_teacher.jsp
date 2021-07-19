

<%@page import="DAO.CourseDB"%>
<%@page import="Model.Course"%>
<%@page import="java.util.List"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
        <link rel="stylesheet" href="Views/Css/common.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <link rel="stylesheet" href="Views/Css/Profile/exercise_statistic_teacher.css">
        <title>ASQ | Teacher</title>

        <script>

            //Thay đổi các lựa chọn lọc
            function changeCourse()
            {
                //get the selected courseid
                var selectCourse = document.getElementById("select-course");
                var courseid = selectCourse.value;

                var selectChap = document.getElementById("select-chap");
                var options = selectChap.options;

                for (var i = 0; i < options.length; i++)
                {
                    if (options[i].dataset.tag !== courseid)
                        options[i].style.display = "none";
                    else
                    {
                        options[i].style.display = "block";
                        options[i].selected = "selected";

                    }
                }
            }

            function changeChap()
            {
                //get the selected chapid
                var selectedChap = document.getElementById("select-chap");
                var chapid = selectedChap.value;
                var selectPart = document.getElementById("select-part");
                var options = selectPart.options;
                for (var i = 0; i < options.length; i++)
                {
                    if (options[i].dataset.tag !== chapid)
                        options[i].style.display = "none";
                    else
                    {
                        options[i].style.display = "block";
                        options[i].selected = "selected";
                    }
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
                    <label id='label_account'>${User.getName()} </label>
                    <div class="drop-down account" id="drop-down-person">
                        <!--                        <a href="admin"><button>Thông tin cá nhân</button></a>
                                                <a href="sign-in"><button>Đăng xuất</button></a>-->
                    </div>    
                    <i class='fas fa-caret-down' onclick="ToggleDropDown('drop-down-person')"></i>    
                </div>
            </div>
        </div>
        <div class="div-container">
            <div class="div-menu">
                <a href='teacher' ><input class='button-menu-option' type='button' value='Quản lí khóa học'></a>
                <a href="Get_data_All_courses_Teacher" ><input  class='button-menu-option' type='button' value ='Thống kê bài tập'></a>
                <a href="sign-in" ><input  class='button-menu-option' type='button' value ='Đăng xuất'></a>
            </div>
            <form action="Get_data_All_courses_Teacher" method='post'>
                <div class ='div-content'>
                    <div class='div-contain-course-list'>
                        <div class ='div-function-name'>
                            <h1 id ="h1-function-name">Thống kê phần bài tập</h1>
                        </div>
                        <div class="div-main-content">
                            <div class="div-condition">
                                <select class="select-condition" id="select-course" name="courseid" onchange="changeCourse()">
                                    <c:if test='${not empty User.getCourses()}'>
                                        <c:forEach items = "${User.getCourses()}"  var = "course">
                                            <option value="<c:out value='${course.getCourseId()}'/>"> <c:out value = "${course.getName()}"/></option>                                                                             
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <select class="select-condition" id="select-chap" name="chapid" onchange="changeChap()">
                                    <c:if test='${not empty User.getCourses()}'>                                    
                                        <c:forEach items = "${User.getCourses()}"  var = "course">
                                            <c:if test='${not empty course.getChaps()}'>
                                                <c:forEach items="${course.getChaps()}" var='chap'>
                                                    <option value="<c:out value='${chap.getChapId()}'/>" data-tag="<c:out value='${course.getCourseId()}'/>"> <c:out value = "${chap.getName()}"/></option>                                                                     
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <!--gọi hàm để ẩn các chap-->
                                <script>changeCourse()</script>

                                <!--xử lí part-->
                                <select class="select-condition" id="select-part" name="partid" on>
                                    <c:if test='${not empty User.getCourses()}'>                                    
                                        <c:forEach items = "${User.getCourses()}"  var = "course">
                                            <c:if test='${not empty course.getChaps()}'>
                                                <c:forEach items="${course.getChaps()}" var='chap'>
                                                    <c:if test='${not empty chap.getParts()}'>
                                                        <c:forEach items = "${chap.getParts()}"  var = "part">
                                                            <option value="<c:out value='${part.getPartId()}'/>" data-tag="<c:out value='${chap.getChapId()}'/>"> <c:out value = "${part.getName()}"/></option>                             
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </select> 
                                <!--gọi hàm để ẩn các chap-->
                                <script>changeChap();</script>


                            </div>
                            <div class="div-submit"><input id='submit-check' type="submit"  value ="Kiểm tra"></div>

                            <div class="div-statistic-table">
                                <label id="label-sum-excercises">Tổng số câu hỏi: <c:out value='${sumExcercises}'/></label>
                                <table id="statistic-table">
                                    <tr>
                                        <th>Lần thử thứ</th>
                                        <th>Số người tham gia</th>                              
                                        <th>Đúng từ 0-25%</th>
                                        <th>Đúng >25%-50%</th>
                                        <th>Đúng >50%-75%</th>
                                        <th>Đúng >75%</th>
                                    </tr>
                                    <c:forEach var="i" begin='1' end='3'>
                                        <tr>
                                            <td><c:out value='${i}'/></td>
                                            <td><c:set var="t" value="${sumPeople[i]}" />
                                                <c:out value="${t}"/></td>
                                            <td><c:set var="t" value="${NumberOf25percent[i]}" />
                                                <c:out value="${t}"/></td>
                                            <td><c:set var="t" value="${NumberOf25To50percent[i]}" />
                                                <c:out value="${t}"/></td>
                                            <td><c:set var="t" value="${NumberOf50To75percent[i]}" />
                                                <c:out value="${t}"/></td>
                                            <td><c:set var="t" value="${NumberOf75To100percent[i]}" />
                                                <c:out value="${t}"/></td>

                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>                       
                    </div>
                    <% String message = (String) request.getAttribute("message");
                        if (message != null) {%>
                    <%="<script> alert('" + message + "');</script>"%>
                    <% request.removeAttribute("message");%>
                    <%}%>

                </div>
                <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
            </form>
    </body>

</html>
