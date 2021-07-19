
<%-- 
    Document   : student_registration
    Created on : Jan 21, 2021, 5:53:10 AM
    Author     : ad
--%>

<%@page import="Model.Course"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Views/Css/common.css" type="text/css" rel="stylesheet">
        <link href="Views/Css/Admin/admin.css" type="text/css" rel="stylesheet"/>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="Views/Js/Admin/admin.js"></script>
        <link rel="stylesheet" href="Views/Css/Profile/teachProfile.css">
        <link href="Views/Css/Course/student.css" type="text/css" rel="stylesheet"/>
        <title>ASQ | Học viên</title>
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
                        <a href="student"><button>Thông tin cá nhân</button></a>
                        <a href="sign-in"><button>Đăng xuất</button></a>
                     </div>    
                    <i class='fas fa-caret-down' onclick="ToggleDropDown('drop-down-person')"></i>    
                </div>
             </div>
        </div>
            
        <div class="small-container">
            <div class="image-profile">
                 <div class="background-profile">
                     <img src="Views/Images/background.png"/>
                </div>
                <div class="avatar-profile">
                    <img src="Views/Images/logoUTE.png"/>
                </div>
                <div class="name-profile">
                    <p><c:out value='${User.getName()}'/></p>
                </div>
            </div>
            <div class="introduce-profile lagre">
                <div class="title-profile">
                    <p>Giới thiệu</p>
                    <div class="drop-down setting" id="drop-down-setting">
                        <button onclick="ShowFrontDivEditInfoStudent()">Đổi thông tin cá nhân</button>
                        <button onclick="ShowFrontDivEditPassStudent()">Đổi mật khẩu</button>
                    </div>
                    <i class="fas fa-cog" id="setting" onclick="ToggleDropDown('drop-down-setting')"></i> 
                </div>
                <div class="info-profile">
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Chức vụ: </p>
                        <p class="describe">
                            <c:if test="${Role.getRoleName() == 'student'}"><c:out value="Học viên"/></c:if>
                        </p>
                    </div>
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Ngày sinh: </p>
                        <p class="describe"><c:out value='${User.getDateOfBirth()}'/></p>
                    </div>
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Giới tính: </p>
                        <p class="describe"><c:out value='${User.isGender() == true ? "Nam" : "Nữ"}'/></p>
                    </div>
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Email: </p>
                        <p class="describe"><c:out value='${User.getEmail()}'></c:out></p>
                    </div>
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Số điện thoại: </p>
                        <p class="describe"><c:out value='${User.getPhone()}'></c:out></p>
                    </div>
                </div>
            </div>
            <script>
                function addCourse(text, idCourse)
            {
                var ListCourse = document.getElementById('list-course-student');

                var newDiv = document.createElement('div');
                newDiv.setAttribute('class', 'div-course-teacher');
                ListCourse.appendChild(newDiv);

                var newText = document.createElement('p');
                newText.setAttribute('class', 'text-course-teacher');
                var newLinkEdit = document.createElement('a');
                newLinkEdit.setAttribute('href',"Display_Course_Introduction_Student?courseid=" + idCourse);
                newText.appendChild(newLinkEdit);
                newLinkEdit.innerHTML = text;
                newDiv.appendChild(newText);


            }
            </script>        
            <div class="introduce-profile lagre">
                <div class="title-profile">
                    <p>Khóa học đã đăng ký :</p>
                </div>
                <div id = "list-course-student">
                    <%
                        List<Course> Courses = (List<Course>) request.getAttribute("studying");
                        if (Courses != null)
                            for (int i = 0; i < Courses.size(); i++) {%>
                    <script>
                        addCourse("<%= Courses.get(i).getName()%>", "<%= Courses.get(i).getCourseId()%>");
                    </script>
                    <%}
                    %>
                </div>
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

                        
                        
//                        var ModifiedDate = document.createElement('td');
//                        ModifiedDate.setAttribute('class', 'center');
//                        
//                        var Input_date = document.createElement('a');
//                        Input_date.setAttribute('name', 'dateModifed');
//                        ModifiedDate.appendChild(Input_date);
//                        Input_date.innerHTML = modifiedDate;
//                        NewTr.appendChild(ModifiedDate);
                        
                        
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
                     <div class="grid" id = "table-student">
                        <table border="1" cellpadding=0 id="table-transport" class="table">
                            <thead class="thead-light">
                                <tr>
                                    <th scope="col">Mã khóa học</th>
                                    <th scope="col">Tên khóa học</th>
                                    <th scope="col">Mục tiêu khóa học</th>
                                    
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
                                                %> <script>LoadCourses("<%=courses.get(i).getCourseId()%>", "<%=courses.get(i).getName()%>", "<%=courses.get(i).getObjective()%>");
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
            <div class="introduce-profile lagre"> 
                <div class="title-profile">
                    <p>Điểm phần bài tập:</p>
                </div>
            </div>
        </div>
     
        <div class="front-div" id="front-div">
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
                    <i class="far fa-times-circle" onclick="CloseFrontDivEditInfoStudent()"></i>
                </div>
                <p class="header">Cập nhật tài khoản</p>
                <form action="edit-information" method="post">
                    <div class="small-container">
                        <input type="hidden" name="userIdEdit" required value="<c:out value="${User.getUserId()}"/>">
                        <div class="field-input">
                            <p>Name:</p>
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
      
        <div class="front-div" id="front-div-change-password">
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
                    <i class="far fa-times-circle" onclick="CloseFrontDivEditPassStudent()"></i>
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
       
    </body>
</html>

