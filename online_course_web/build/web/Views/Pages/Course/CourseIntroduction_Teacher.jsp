
<%@page import="java.util.List"%>
<%@page import="Model.Chap"%>
<%@page import="Model.Part"%>
<%@page import="Model.FAQ"%>
<%@page import="Model.User"%>
<%@page import="Model.Instructor"%>
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
        <link rel="stylesheet" href="Views/Css/Course/CourseIntroduction_Teacher_css.css">
        <link href="Views/Css/Course/course.css" type="text/css" rel="stylesheet">
        <link rel="stylesheet" href="Views/Css/common.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </head>

    <body>     
        <form action="Process_CourseIntroduction_Teacher"
              method="post" enctype="multipart/form-data">
            <input type ="hidden" name='courseId' value="${course.getCourseId()}">
            <%--<c:out value="${course.getCourseId()}"/>--%>
             <div class="small-container horizontal">
                 <div class='div_logo'>
                    <a href="home"><img id='image_logo' src ="logo.png" ></a>
                </div>
                 
                <div id='div_account' class="div_account">
                    <label id='label_account'>${User.getName()} </label>
                    <div class="drop-down account" id="drop-down-person">
<!--                        <a href="admin"><button>Thông tin cá nhân</button></a>-->
                        <!--<a href="sign-in"><button>Đăng xuất</button></a>-->
                     </div>    
                    <i class='fas fa-caret-down' onclick="ToggleDropDown('drop-down-person')"></i>    
                </div>
             </div>
            <div class="div_container">
                <!--Mục tiêu-->
                <div class='div_mucTieu'>
                    <h2>1. Mục tiêu khóa học:</h2>
                    <textarea id="textarea_mucTieu" type="text" placeholder="Nhập mục tiêu khóa học" 
                              name='objective' ><c:if test="${ not empty course.getObjective()}"><c:out value="${course.getObjective()}"/></c:if></textarea>
                    </div>

                    <!--Nội dung các chương-->
                    <div class ="div_noiDung">
                        <h2>2. Nội dung chương trình học:</h2>        
                        <input type="text" id ="courseName" name ="courseName" placeholder="Nhập tên khóa học"
                        <c:if test="${not empty course.getName()}"> value ='<c:out value="${course.getName()}"/>'</c:if>>
                        <div id='textbox_group'>
                            <div id="div_chap1" class="div_chap">                          
                                <input type='textbox' class="textbox_chap" id='textbox_chap1' name='chap1' 
                                <c:if test="${ not empty chap1}">
                                    value =  '<c:out value= "${chap1.getName()}" /> '                                        
                                </c:if> placeholder = "Nhập tên chương" >
                            <input class="btn_add_part" id='btn_add_part_chap1' type='button' value='+' onclick="addPart(1)"  >
                        </div>
                    </div>
                    <input class="btn_add_chap" id='btn_add_chap' type='button' value='Add Chap' onclick ='addChap()' >
                    <input class="btn_standardize_chap" id='btn_standardize_chap' type='button' value='Chuẩn hóa' onclick='Standardize()' >

<!--                    <form action ="excercise_teacher.jsp"
                        method="post">
                        <input type ="submit" value="thu">
                    </form>-->
                    <script>

                        var counter_chap = 1;
                        var numberOfParts = [0, 0];

                        function addChap()
                        {
                            counter_chap++;

                            if (counter_chap > 10) {
                                alert("Only 10 textboxes allow");
                                return false;
                            }

                            //get textboxGroup
                            var textboxGroup = document.getElementById("textbox_group");
                            //Create new chapter div
                            var newDiv = document.createElement('div');
                            newDiv.setAttribute("class", 'div_chap');
                            newDiv.setAttribute("id", 'div_chap' + counter_chap);
                            textboxGroup.appendChild(newDiv);


                            var newTextBox = document.createElement('input');
                            newTextBox.setAttribute("type", "text");
                            newTextBox.setAttribute("name", "chap" + counter_chap);
                            newTextBox.setAttribute("class", "textbox_chap");


                        <%for (int i = 2; i <= 10; i++) {%>
                            if (counter_chap === <%=i%>)
                            {
                        <% Chap c = (Chap) request.getAttribute("chap" + i);
                            if (c != null) {%>
                                newTextBox.setAttribute("value", "<c:out value='<%=c.getName()%>'/>");
                        <%}%>
                            }
                        <%}%>
                            newTextBox.setAttribute("id", "textbox_chap" + counter_chap);
                            newTextBox.setAttribute("placeholder", "Nhập tên chương");
                            newDiv.appendChild(newTextBox);

                            //create a button which is to add a part
                            var newButton = document.createElement("input");
                            newButton.setAttribute("class", "btn_add_part");
                            newButton.setAttribute("id", "bt_add_part_chap" + counter_chap);
                            newButton.setAttribute("type", "button");
                            newButton.setAttribute("value", "+");
                            newButton.setAttribute("onclick", "addPart(" + counter_chap + ")");
                            newDiv.appendChild(newButton);

                            numberOfParts.push(0);

                        }

                        function addPart(chap)
                        {
                            //number of part (substract 2 (including chap input and button)
                            var numberOfPart = numberOfParts[chap];

                            if (numberOfPart > 10) {
                                alert("Only 10 textboxes allow");
                                return false;
                            }
                            var lastPart;
                            if (numberOfPart === 0)
                            {
                                lastPart = document.getElementById("textbox_chap" + chap);
                            } else
                            {
                                lastPart = document.getElementById("link_goto_excercise_chap" + chap + "_part"+numberOfPart);
                            }

                            lastPart.insertAdjacentHTML("afterend", "<input type = 'text' class ='textbox_part' id ='textbox_chap" + chap + "_part" + (numberOfPart + 1) + "'\n\
                                                                                name = 'chap" + chap + "_part" + (numberOfPart + 1) + "' placeholder ='Nhập tên'>");
                            lastPart =document.getElementById("textbox_chap" + chap + "_part" + (numberOfPart+1));
                            
                            //Tới trang bài học
                            lastPart.insertAdjacentHTML("afterend", "<a  class='link_part' id = 'link_goto_part_chap"+chap+"_part"+(numberOfPart+1)
                                    +"' href='Display_Part_Teacher?previousPage=Display_Course_Introduction_Teacher'><input class='link_goto_Part'  type ='button' value='Bài học' button' ></a>");
                            
//                            Display_Part_Teacher?previousPage=Display_Course_Introduction_Teacher
                            lastPart =document.getElementById("link_goto_part_chap" + chap + "_part" + (numberOfPart+1));
                            
                            //Thêm nút bài tập
                            lastPart.insertAdjacentHTML("afterend", "<a class='link_excercise'  id = 'link_goto_excercise_chap"+chap+"_part"+(numberOfPart+1)
                                    +"' href='Display_Excercise_Teacher?previousPage=Display_Course_Introduction_Teacher'><input  class='link_goto_Excercise'  type ='button' value='Bài tập' ></a>");
                            numberOfParts[chap] += 1;
                            
                        }

                        
                        //function to standardize the content display
                        function Standardize()
                        {
                            var textbox_group = document.getElementById('textbox_group');

                            var numberOfChaps = counter_chap;

                            var chap;
                            for (chap = 1; chap <= counter_chap; chap++)
                            {
                                var part;
                                for (part = 1; part <= numberOfParts[chap]; part++)
                                {
                                    var textbox_part = document.getElementById('textbox_chap' + chap + '_part' + part);
                                    //the value of the part is null
                                    if (textbox_part.value.trim() === "")
                                    {
                                        //the flag variable indicates that whether there is a unempty part after current part
                                        var flag = false;
                                        for (var i = part + 1; i <= numberOfParts[chap]; i++)
                                        {
                                            var textbox_i = document.getElementById('textbox_chap' + chap + '_part' + i);
                                            if (textbox_i.value.trim() !== "")
                                            {
                                                textbox_part.value = textbox_i.value;
                                                textbox_i.value = "";
                                                flag = true;
                                                break;
                                            }
                                        }
                                        //there is not any part after current part.
                                        if (flag === false)
                                        {
                                            for (var i = numberOfParts[chap]; i >= part; i--)
                                            {
                                                var item = document.getElementById('textbox_chap' + chap + '_part' + i);
                                                item.parentNode.removeChild(item);
                                                numberOfParts[chap]--;
                                                
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        <c:set var="maxchap" value="0"/>
                        <c:if test="${not empty maxChap}">
                            <c:set var="maxchap" value="${maxChap}"/>
                             
                        </c:if>
                        //Thêm các chap vào                         
                        while (counter_chap < ${maxchap})
                        {
                            
                            addChap();
                        }
                        
                         <% try
                         {
                             int maxChap =  (int)request.getAttribute("maxChap");
                             for (int chapid = 1; chapid <= maxChap; chapid++) {
                                    for (int partid = 1; partid <= 10; partid++) {
                                        String s = "chap" + chapid + "_part" + partid;
                                        Part part = (Part) request.getAttribute("chap" + chapid + "_part" + partid);
                                        if (part != null) {%>
                                            addPart("<c:out  value="<%= part.getChap().getChapOrder()%>"></c:out>");
                                            var input = document.getElementById('<%="textbox_chap"+chapid+"_part"+partid%>');
                                            input.setAttribute("value","<c:out value='<%=part.getName()%>'/>");
                                            
                                             //chang link of link_goto
                                            var link = document.getElementById("link_goto_part_chap"+ "<c:out  value='<%= part.getChap().getChapOrder()%>'/>" +"_part"+ "<c:out  value='<%= part.getPartOrder()%>'/>");
                                            link.setAttribute("href", "Display_Part_Teacher?courseid="+"<c:out  value='<%= part.getCourse().getCourseId()%>'/>"+
                                                    "&chapid="+"<c:out  value='<%= part.getChap().getChapOrder()%>'/>"
                                                    +"&partid="+"<c:out  value='<%= part.getPartOrder()%>'/>");
                                            
                                            //chang link of link_goto
                                            var link = document.getElementById("link_goto_excercise_chap"+ "<c:out  value='<%= part.getChap().getChapOrder()%>'/>" +"_part"+ "<c:out  value='<%= part.getPartOrder()%>'/>");
                                            link.setAttribute("href", "Display_Excercise_Teacher?courseid="+"<c:out  value='<%= part.getCourse().getCourseId()%>'/>"+
                                                    "&chapid="+"<c:out  value='<%= part.getChap().getChapOrder()%>'/>"
                                                    +"&partid="+"<c:out  value='<%= part.getPartOrder()%>'/>");
                                            
                                            
                                        <% }
                                    else break;
                                }
                            }
                        }       
                         catch(Exception ex){}%>

                    </script>
                   

                </div>
                <div class ="div_part3_instructor">
                    <h2>3. Thông tin người giảng dạy:</h2>
                    <div id="div_all_instructor">  
                        <div class='div_instructor' id ='div_instructor1'>
                            <div class="div_instructor_image">
                                <img class ="instructor_image" id='instructor_image_1' src="" name='imageIntructor1'>

                            </div>
                            <div class ="div_instructor_information">
                                <input class='input_instructor_name' id ="input_instructorName1" type="text" name="instructorName1"  placeholder="Nhập tên người giảng dạy">
                                <input class='input_instructor_disription'  id ="input_instructorDescription1" type="text" name="instructorDescription1" placeholder="Mô tả chức vụ ">
                                <input type="file"  accept="image/*" name="image1" id="image1"  onchange="loadFile('1')" style="display: none;"> 
                                <label for ='image1' class='label_upload_image' >Upload</label>
                                <input type='button' class ='button_remove_instructor' value='-' onclick='removeInstructor(1)'>
                            </div>
                        </div>
                    </div>
                    <input type='button' id ='button_add_instructor' value='Thêm' onclick='addIntructor()'>

                    <script>
                        var numberOfInstructors = 1;

                        var checkEmpty = [0, 1, 0, 0, 0, 0, 0, 0];

                        function addIntructor()
                        {
                            if (numberOfInstructors >= 7)
                            {
                                alert("Chỉ cho phép tối đa 6 người hướng dẫn");
                                return;
                            }

                            var instructorOrder;
                            for (instructorOrder = 1; instructorOrder <= 7; instructorOrder++)
                            {
                                if (checkEmpty[instructorOrder] === 0)
                                    break;
                            }


                            var div_all_instructor = document.getElementById('div_all_instructor');

                            var div_instructor = document.createElement('div');
                            div_instructor.setAttribute('class', 'div_instructor');
                            div_instructor.setAttribute('id', 'div_instructor' + instructorOrder);
                            div_all_instructor.appendChild(div_instructor);


                            var newImageDiv = document.createElement('div');
                            newImageDiv.setAttribute('class', 'div_instructor_image');
                            div_instructor.appendChild(newImageDiv);

                            var newImage = document.createElement("img");
                            newImage.setAttribute('class', "instructor_image");
                            newImage.setAttribute('src', '');
                            newImage.setAttribute('id', "instructor_image_" + instructorOrder);
                            newImage.setAttribute('name', 'imageInstructor' + instructorOrder);
                            newImageDiv.appendChild(newImage);

                            //instructtor's information div
                            var new_div_instructor_information = document.createElement('div');
                            new_div_instructor_information.setAttribute("class", "div_instructor_information");
                            div_instructor.appendChild(new_div_instructor_information);

                            var newNameInput = document.createElement("input");
                            newNameInput.setAttribute('class', 'input_instructor_name');
                            newNameInput.setAttribute('id','input_instructorName'+instructorOrder);
                            newNameInput.setAttribute('type', 'text');
                            newNameInput.setAttribute('name', 'instructorName' + instructorOrder);
                            newNameInput.setAttribute('placeholder', 'Nhập tên người giảng dạy');
                            new_div_instructor_information.appendChild(newNameInput);


                            var newInstructorDescriptionInput = document.createElement("input");
                            newInstructorDescriptionInput.setAttribute('class', 'input_instructor_disription');
                            newInstructorDescriptionInput.setAttribute('id', 'input_instructorDescription'+instructorOrder);
                            newInstructorDescriptionInput.setAttribute('type', 'text');
                            newInstructorDescriptionInput.setAttribute('name', 'instructorDescription' + instructorOrder);
                            newInstructorDescriptionInput.setAttribute('placeholder', 'Mô tả chức vụ');
                            new_div_instructor_information.appendChild(newInstructorDescriptionInput);

                            var button_remove = document.createElement('input');
                            button_remove.setAttribute("value", '-');
                            button_remove.setAttribute("type", 'button');
                            button_remove.setAttribute("class", 'button_remove_instructor');
                            button_remove.setAttribute('onclick', 'removeInstructor(' + instructorOrder + ')');
                            new_div_instructor_information.appendChild(button_remove);


                            var createFile = '<input type="file"  accept="image/*" name="image' + instructorOrder + '" id="image' + instructorOrder + '"onchange="loadFile(' + instructorOrder + ')" style="display: none;">';
                            newInstructorDescriptionInput.insertAdjacentHTML('afterend', createFile);

                            var label_upload_image = document.createElement('label');
                            label_upload_image.setAttribute('for', 'image' + instructorOrder);
                            label_upload_image.setAttribute('class', 'label_upload_image');
                            label_upload_image.innerHTML = 'Upload';
                            new_div_instructor_information.appendChild(label_upload_image);

                            checkEmpty[instructorOrder] = 1;
                            numberOfInstructors++;

                        }

                        var loadFile = function (order) {
                            var image = document.getElementById('instructor_image_' + order);
                          
                            image.src = URL.createObjectURL(event.target.files[0]);
                        };

                        function removeInstructor(order)
                        {
                            var div_instructor = document.getElementById('div_instructor' + order);
                            div_instructor.remove();
                            checkEmpty[order] = 0;
                            numberOfInstructors--;
                        }
                        
                        //hiển thị thông tin instructor
                        <%
                        for(int instructorCounter=1; instructorCounter<=6; instructorCounter++)
                        {   
                            Instructor ins = (Instructor)request.getAttribute("instructor"+instructorCounter);
                            if(ins!=null){%>
                             console.log("<%=instructorCounter%>");
                             var image = document.getElementById("instructor_image_"+"<%=instructorCounter%>");
                             while(image ==null)
                                {
                                    addIntructor();
                                image = document.getElementById("instructor_image_"+"<%=instructorCounter%>");
                                }
                            
                                image.setAttribute("src", "<c:out value='<%=ins.getPathOfImage()%>'/>");
                            
                                var inputInstructorName = document.getElementById("input_instructorName"+"<%=instructorCounter%>");
                                inputInstructorName.setAttribute("value", "<c:out value='<%=ins.getName()%>'/>");
                            
                                var inputInstructorDes = document.getElementById("input_instructorDescription"+"<%=instructorCounter%>");
                                inputInstructorDes.setAttribute("value", "<c:out value='<%=ins.getPosition()%>'/>");
                            
                            <%}
                        }%>
                    </script>
                </div>

                <div id="div_part4_FAQ">
                    <h2>4. Các câu hỏi thường gặp:</h2>
                    <div id ='div_FAQ'>
                        <textarea class='textarea_question'  id ='textarea_question1' placeholder='Nhập câu hỏi' name="question1"></textarea>
                        <textarea class ='textarea_answer' id= 'textarea_answer1' placeholder='Nhập câu trả lời'name ='answer1'></textarea>
                        <input type='button' id ='button_add_FAQ' value='Thêm' onclick='addFAQ()'>
                    </div>
                </div>    
                <script>

                    var counter_questions = 1;

                    function addFAQ()
                    {
                        counter_questions++;
                        if (counter_questions > 10)
                        {
                            alert("Bạn được thể hiện tối đa 10 câu hỏi cho phần này!");
                            return;
                        }

                        var button_add_FAQ = document.getElementById("button_add_FAQ");
                        button_add_FAQ.insertAdjacentHTML('beforebegin', "<textarea class='textarea_question'  id ='textarea_question" + counter_questions + "' placeholder='Nhập câu hỏi' name='question" + counter_questions + "'></textarea>");

                        button_add_FAQ.insertAdjacentHTML('beforebegin', "<textarea class ='textarea_answer' id= 'textarea_answer" + counter_questions + "'placeholder='Nhập câu trả lời' name ='answer" + counter_questions + "'></textarea>");
                    }
                    
                    var input1 = document.getElementById("textarea_question1");
                                            
                                            input1 = document.getElementById('aaa111');
                   <% try
                         {
                             for (int faqid = 1; faqid <= 10; faqid++) {
                             
                                        FAQ faq = (FAQ) request.getAttribute("FAQ" + faqid );
                                        if (faq != null) {%>
                                        
                                            var input1 = document.getElementById("textarea_question" + <%=faqid%>);
                                            
                                            while(!input1)
                                            {
                                                addFAQ();
                                                input1 = document.getElementById("textarea_question"+ <%=faqid%>);
                                            }
                                                                                     
                                                 
                                            input1.value= "<c:out value='<%=faq.getQuestion()%>'/>";
                                            
                                            input1 = document.getElementById("textarea_answer"+<%=faqid%>);
                                            input1.value= "<c:out value='<%=faq.getAnswer()%>'/>";                       
                                        <% }
                               }
                            }                              
                         catch(Exception ex){}%>
                </script>
           
            </div>
            <div class = 'div_save'>
                <!--<a href="Process_CourseIntroduction_Teacher"><input type='button' id ='button_save' value='Save'></a>-->
                <!--<a  href="Process_CourseIntroduction_Teacher"><input  id='button_save' type="button" value="Lưu"></a>-->
                <input id ='button_save'  type="submit" value="Lưu">
                <a  href="Display_Course_Introduction_Teacher?requirement='new'"><input  id='button_newCourse' type="button" value="Tạo khóa học mới"></a>
                  <a  href="teacher"><input  id='button_profile' type="button" value="Trang chính"></a>

            </div>
            <div class="footer">
                <div class="small-container">
                    <div class="info-member">
                        <div class="member-detail">
                            <h3>Name of Member</h3>
                            <ul>
                                <li>Trần Văn Ân</li>
                                <li>Nguyễn Phan Sự</li>
                                <li>Nguyễn Anh Quốc</li>
                            </ul>
                        </div>
                        <div class="member-detail">
                            <h3>ID Student</h3>
                            <ul>
                                <li>18110249</li>
                                <li>18110355</li>
                                <li>18110345</li>
                            </ul>
                        </div>
                    </div>
                    <div class="info-contact member-detail">
                        <h3>Contact</h3>
                        <ul>
                            <li><a href="#"><i class="fab fa-facebook"></i> Facebook</a></li>
                            <li><a href="#"><i class="fas fa-at"></i> Email</a></li>
                            <li><a href="#"><i class="fab fa-telegram"></i> Telegram</a></li>
                        </ul>
                    </div>
                    <div class="icon-logo">

                    </div>
                </div>
            </div>
            <hr>
            <% String message = (String) request.getAttribute("message");
                        if (message != null) {%>
            <%="<script> alert('" + message + "');</script>"%>
            <% request.removeAttribute("message");%>
            <%}%>
            <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
        </form>
        
<!--        <form action="UploadServlet" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Upload File" />
</form>
    --></body>

</html>