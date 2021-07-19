
<%@page import="Model.Chap"%>
<%@page import="Model.Part"%>
<%@page import="Model.FAQ"%>
<%@page import="Model.User"%>
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
        <link rel="stylesheet" href="Views/Css/Course/Course_Introduction_Student_css.css">
         <!--<link href="Views/Css/Course/course.css" type="text/css" rel="stylesheet">-->
        <link rel="stylesheet" href="Views/Css/common.css">
        <script src="Views/Js/Admin/admin.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <link href="Views/Css/Admin/admin.css" type="text/css" rel="stylesheet"/>
    </head>

    <body>     
        <form action="add-course"
              method="post">
            <input type ="hidden" name='courseId' value="${course.getCourseId()}">
           
            <%--<c:out value="${course.getCourseId()}"/>--%>
            <div class = 'div_menu'>
            <div class="small-container horizontal">
                 <div class='div_logo'>
                    <a href="home"><img id='image_logo' src ="logo.png" ></a>
                 </div>              
                 <div id='div_account' class="div_account">
                    <label id='label_account'>${User.getName()} </label>
                    <div class="drop-down account" id="drop-down-person">
                        <!--<a href="sign-in"><button>Đăng xuất</button></a>-->
                     </div>    
                    <i class='fas fa-caret-down' onclick="ToggleDropDown('drop-down-person')"></i>    
                </div>
             </div>
        </div>
            <div class="div_container">
                <!--Mục tiêu-->
                 <p type="text" id ="courseName">
                        <c:if test="${not empty course.getName()}"><c:out value="${course.getName()}"/></c:if></p>
                <div class='div_mucTieu'>
                    
                    <h2>1. Mục tiêu khóa học:</h2>
                    <p id="p_mucTieu"  ><c:if test="${ not empty course.getObjective()}"><c:out value="${course.getObjective()}"/></c:if></p>
                    </div>

                    <!--Nội dung các chương-->
                    <div class ="div_noiDung">
                        <h2>2. Nội dung chương trình học:</h2>        
          
                        <div id='textbox_group'>
                            <div id="div_chap1" class="div_chap">                          
                                <input type='button' class="button_chap" id='button_chap1'  onclick="clickChap(1)"
                                <c:if test="${ not empty chap1}">
                                    value =  '<c:out value= "${chap1.getName()}" /> '                                        
                                </c:if> >
                          
                        </div>
                    </div>
                   
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
                            newTextBox.setAttribute("type", "button");
                            newTextBox.setAttribute("class", "button_chap");
                            newTextBox.setAttribute("onclick", "clickChap("+counter_chap+")");


                        <% for (int i = 2; i <= 10; i++) {%>
                            if (counter_chap === <%=i%>)
                            {
                        <% Chap c = (Chap) request.getAttribute("chap" + i);
                            if (c != null) {%>
                                newTextBox.setAttribute("value", "<c:out value='<%=c.getName()%>'/>");
                        <%}%>
                            }
                        <%}%>
                            newTextBox.setAttribute("id", "button_chap" + counter_chap);                      
                            newDiv.appendChild(newTextBox);

                         
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
                                lastPart = document.getElementById("button_chap" + chap);
                             
                            } else
                            {
                                lastPart = document.getElementById("link_chap" + chap + "_part"+numberOfPart);
                            }
                            lastPart.insertAdjacentHTML("afterend", "<a  class ='link_part' id ='link_chap" + chap + "_part" + (numberOfPart + 1) +"' ></a>");

                            var link_part = document.getElementById("link_chap"+chap+"_part"+(numberOfPart+1));
                            //ẩn nút
                            link_part.style.display="none";

                            var button_part = document.createElement("input");
                            button_part.setAttribute("type", "button");
                            button_part.setAttribute("class", "button_part");
                            button_part.setAttribute("id", "button_chap"+chap+"_part"+(numberOfPart+1));
                            
                            link_part.appendChild(button_part);                        
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
                                    var button_part = document.getElementById('button_chap' + chap + '_part' + part);
                                    //the value of the part is null
                                    if (button_part.value.trim() === "")
                                    {
                                        //the flag variable indicates that whether there is a unempty part after current part
                                        var flag = false;
                                        for (var i = part + 1; i <= numberOfParts[chap]; i++)
                                        {
                                            var textbox_i = document.getElementById('button_chap' + chap + '_part' + i);
                                            if (textbox_i.value.trim() !== "")
                                            {
                                                button_part.value = textbox_i.value;
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
                                                var item = document.getElementById('button_chap' + chap + '_part' + i);
                                                item.parentNode.removeChild(item);
                                                numberOfParts[chap]--;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        function clickChap(chap)
                        {
                            for(var partid =1;partid<=numberOfParts[chap]; partid++ )
                             {
                                var button_part =document.getElementById("link_chap"+chap+"_part"+partid);
                                if(button_part.style.display==="none")
                                     button_part.style.display="block";
                                else
                                    button_part.style.display="none";                            }
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
                                        addPart("<%=chapid%>");
                                            var input = document.getElementById('<%="button_chap"+chapid+"_part"+partid%>');
                                            input.setAttribute("value","<c:out value='<%=part.getName()%>'/>");
                                            
                                            
                                            //link of link_goto
                                            var link = document.getElementById('<%="link_chap"+chapid+"_part"+partid%>');
                                            link.setAttribute("href", '<%="Display_Part_Student?courseid="+part.getCourse().getCourseId()
                                                                        +"&chapid="+part.getChap().getChapOrder()+"&partid="+part.getPartOrder()%>');
                                            
                                             var linkExcercise = document.getElementById('<%="link_chap"+chapid+"_part"+partid%>');
                                            linkExcercise.setAttribute("href", '<%="Display_Part_Student?courseid="+part.getCourse().getCourseId()
                                                                        +"&chapid="+part.getChap().getChapOrder()+"&partid="+part.getPartOrder()%>');
                                            
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
                                <img class ="instructor_image" id='instructor_image_1' src="logo.png" name='imageIntructor1'>

                            </div>
                            <div class ="div_instructor_information">
                                <input class='input_instructor_name'  id ="input_instructorName1" type="button" name="instructorName1" placeholder="Nhập tên người giảng dạy">
                                <input class='input_instructor_disription'  id ="input_instructorDescription1" type="button" name="instructorDescription1"placeholder="Mô tả chức vụ ">                               
                               
                            </div>
                        </div>
                    </div>
                    

                    <script>
                        var numberOfInstructors = 1;

                        var checkEmpty = [0, 1, 0, 0, 0, 0, 0, 0];

                        function addIntructor()
                        {
                            if (numberOfInstructors >= 7)
                            {
                                alert("Chỉ cho phép tối đa 7 người hướng dẫn");
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
                            newImage.setAttribute('src', 'logo.png');
                            newImage.setAttribute('id', "instructor_image_" + instructorOrder);
                            newImage.setAttribute('name', 'imageIntructor' + instructorOrder);
                            newImageDiv.appendChild(newImage);

                            //instructtor's information div
                            var new_div_instructor_information = document.createElement('div');
                            new_div_instructor_information.setAttribute("class", "div_instructor_information");
                            div_instructor.appendChild(new_div_instructor_information);

                            var newNameInput = document.createElement("input");
                            newNameInput.setAttribute('class', 'input_instructor_name');
                             newNameInput.setAttribute('id','input_instructorName'+instructorOrder);
                            newNameInput.setAttribute('type', 'button');
                            newNameInput.setAttribute('name', 'instructorName' + instructorOrder);
                            newNameInput.setAttribute('placeholder', 'Nhập tên người giảng dạy');
                            new_div_instructor_information.appendChild(newNameInput);


                            var newInstructorDescriptionInput = document.createElement("input");
                            newInstructorDescriptionInput.setAttribute('class', 'input_instructor_disription');
                            newInstructorDescriptionInput.setAttribute('id','input_instructorDescription'+instructorOrder);
                            newInstructorDescriptionInput.setAttribute('type', 'button');
                            newInstructorDescriptionInput.setAttribute('name', 'instructorDescription' + instructorOrder);
                            newInstructorDescriptionInput.setAttribute('placeholder', 'Mô tả chức vụ');
                            new_div_instructor_information.appendChild(newInstructorDescriptionInput);
                          

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
                        <input type="button" class='button_question'  id ='button_question1' >
                        <input type="button" class ='button_answer' id= 'button_answer1'>                      
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

                        var div_FAQ = document.getElementById("div_FAQ");
                        
                        var button_question = document.createElement("input");
                        button_question.setAttribute("type", "button");
                        button_question.setAttribute("class", "button_question");
                        button_question.setAttribute("id", "button_question" + counter_questions);
                        div_FAQ.append(button_question);
//                        button_add_FAQ.insertAdjacentHTML('beforebegin', "<input type='button' class='button_question'  id ='button_question" + counter_questions + "' >");

                        button_question.insertAdjacentHTML('afterend', "<input type='button' class ='button_answer' id= 'button_answer" + counter_questions+ "' >");
                    }
                    
//                    var input1 = document.getElementById("textarea_question1");
//                                            console.log(input1);
//                                            input1 = document.getElementById('aaa111');
                   <% 
                       
//                       try
//                         {
                             for (int faqid = 1; faqid <= 10; faqid++) {
                             
                                        FAQ faq = (FAQ) request.getAttribute("FAQ" + faqid );
                                        if (faq != null) {%>
                                        
                                            var input1 = document.getElementById("button_question" + <%=faqid%>);
                                            while(!input1)
                                            {
                                                addFAQ();
                                                input1 = document.getElementById("button_question"+ <%=faqid%>);
                                            }
                                                                                     
                                                 
                                            input1.value= "<c:out value='<%=faq.getQuestion()%>'/>";
                                            
                                            input1 = document.getElementById("button_answer"+<%=faqid%>);
                                            input1.value= "<c:out value='<%=faq.getAnswer()%>'/>";                       
                                        <% }
                               }
//                            }                              
//                         catch(Exception ex){}%>
                </script>
           
            </div>
            <div class = 'div_save'>
<!--                <a href="Process_CourseIntroduction_Teacher"><input type='button' id ='button_save' value='Save'></a>-->
                <!--<a  href="Process_CourseIntroduction_Teacher"><input  id='button_save' type="button" value="Lưu"></a>-->
                
                <input id ='button_save'  type="submit" value="Đăng ký khóa học">                
<!--                <a  href="Display_Course_Introduction_Teacher?requirement='new'"><input  id='button_newCourse' type="button" value="Tạo khóa học mới"></a>
                  <a  href="teacher-profile"><input  id='button_profile' type="button" value="Trang chính"></a>-->

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
                if (message !=null ) {%>
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