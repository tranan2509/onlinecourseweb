<%-- 
    Document   : Lesson
    Created on : Dec 25, 2020, 3:24:26 PM
    Author     : ad
--%>


<%@page import="java.util.List"%>
<%@page import="Model.Chap"%>
<%@page import="Model.Part"%>
<%@page import="Model.FAQ"%>
<%@page  import = "Model.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Giới thiệu khóa học</title>
    <link rel="stylesheet" href="Views/Css/Course_Introduction_Student_css.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link rel="stylesheet" href="Views/Css/Course/LessonTeacher.css">
    <link rel="stylesheet" href="Views/Css/Course/Course_Introduction_Student_css.css">
    <link rel="stylesheet" href="Views/Css/common.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="./path/to/dropzone.js"></script>
</head>
<body>
    <form action="Display_Course_Introduction_Teacher"
          method="post">
            
             <script>
                 
                    var NumberFile = countfile;
                   
                    function addDownload()
                    {
                        if (NumberFile === 0)
                        {
                            alert("Hiện chưa có file hướng dẫn!!!");
                            return;
                        }

                        var i;
                        for (i = 1; i <= NumberFile ; i++)
                        {
                            var div_all_instructor = document.getElementById('div_all_instructor');

                            var div_instructor = document.createElement('a');
                            div_instructor.setAttribute('href', '');
                            div_instructor.setAttribute('id', 'div_instructor' + instructorOrder);
                            div_all_instructor.appendChild(div_instructor);
                            
                        }                       
                    }
             </script>
             

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
            <%
            Part part =(Part) session.getAttribute("part");           
            Course course= part.getCourse();
            String linkExcercise = (String)session.getAttribute("linkExcercise");
             %>
                    
            <%
            Chap chap = part.getChap();
            request.setAttribute("part", part);
            %>
        <div class="main_title">
            <div class="title_lesson">
                <p id='h1_chapName'><%=chap.getName()%></p>
                <p id = 'h3_partName'><%=part.getName()%><p>
            </div>
        </div>


        <div class="div_container">
            <!--Mục tiêu-->
            
                <h2 id='sizeh2'>1. Tài liệu hướng dẫn:</h2>
                    <p id = "text_p">Các file hướng dẫn bài học:</p>
                    
                    <%List<PartFiles> partfiles = (List<PartFiles>) request.getAttribute("partfiles");
                        if (partfiles != null)
                            for (int i = 0; i < partfiles.size(); i++) {%>
                    <script>
                        addfiles("<%= partfiles.get(i).getFileDocument()%>");
                    </script>
                    <%}
                    %>

            <%
                 List<PartFiles> pfiles = (List<PartFiles>) request.getAttribute("partfiles");
            %>
           
            <c:forEach items="${partfiles}" var="item">
             
                <label id = "filedown">
                        <c:set var = "fileNameDoc" scope = "session" value = '${item.getFileDocument()}'/>  
                        <a href = "<c:url value = "DownloadFile?fileNameDoc=${fileNameDoc}&kindfile=doc"/>"> <c:out value="${item.getFileDocument()}"/></a>&nbsp;&nbsp;&nbsp; <br>
                </label> 
            </c:forEach>       
                        

                   
            

                Nội dung các chương
            <div class ="div_noiDung">
                    <h2 id='sizeh2'>2. Video bài học:</h2>
                    <p id = "text_p">Các video hướng dẫn bài học:</p>
                        <video id ="video_id" controls>
                            <source src="./TestUpload/<%=part.getVideo()%>" type="video/mp4">
                            <source src="<%=part.getVideo()%>" type="video/ogg">
                            Your browser does not support HTML5 video.
                        </video><br>
                        <label id = "filedown">
                            <c:set var = "fileNameVideo" scope = "session" value = "<%=part.getVideo()%>"/>  
                            <a href = "<c:url value = "DownloadFile?fileName=${fileNameVideo}&kindfile=video"/>"><%=part.getVideo()%></a>&nbsp;&nbsp;&nbsp;   
                         </label>                   

            </div>
            <div class ="div_noiDung">
                    <h2 id='sizeh2'>3. Bài tập trắc nghiệm:</h2>
                    
                    <a id = "text_p" href='Display_Excercise_Student?courseid=${part.getCourse().getCourseId()}&chapid=${part.getChap().getChapOrder()}&partid=${part.getPartOrder()}'>Bài tập</a>
            </div>  
        </div>
            <div class = 'div_save'>

            </div>

            <hr> 
            <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
        </form>
            <% String message = (String) request.getAttribute("message");
            if (message != null) {%>
    <%="<script> alert('" + message + "');</script>"%>
    <% request.removeAttribute("message");%>
    <%}%>
    </body>
</html>
