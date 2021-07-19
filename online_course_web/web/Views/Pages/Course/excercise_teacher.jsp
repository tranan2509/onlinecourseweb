<%-- 
    Document   : excercise_teacher
    Created on : Nov 27, 2020, 4:10:12 AM
    Author     : A556U
--%>

<%@page import="Model.Excercise"%>
<%@page import="Model.Part"%>
<%@page import="Model.Course"%>
<%@page import="DAO.CourseDB"%>
<%@page import="Model.Chap"%>
<%@page import="DAO.ChapDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excercise</title>
        <link rel="stylesheet" href="Views/Css/Course/excercise_teacher_css.css">        
        <link rel="stylesheet" href="Views/Css/common.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        
    </head>
    <body>
        <%--<c:out value="${part}"/>--%>
       
        <div class = 'div_menu'>
            <div class="small-container horizontal">
                 <div class='div_logo'>
                    <a href="home"><img id='image_logo' src ="logo.png" ></a>
                  
                </div>
                <div id="div_CourseName">
                    <label id="label_courseName"><c:out value="${part.getCourse().getName()}"/> </label>
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
           
        <form action="Process_Excercise_Teacher" medthod ="post">
            <%Part part =(Part) session.getAttribute("part");                  
                  Course course= part.getCourse();%>
            <%
            Chap chap = (Chap)ChapDB.getChapOfCourseByOrder(part.getCourse(), part.getChap().getChapOrder());
            request.setAttribute("part", part);
            %>
            <div id="container">
                
                <h1 id='label_chapName'><c:out value="<%=chap.getName()%>"/></h1>
                <h1 id='label_partName'><c:out value="<%=part.getName()%>"/></h1>
                <div id='div_all_excercises'>
                    <div class='div_excercise' id='div_excercise1'>
                        <textarea class ='textarea_question' id='question1' name ='question1' placeholder="Nhập câu hỏi"></textarea>
                        <input type='text' class='input_answer' id='answer1_A' name='answer1_A' placeholder='Đáp án A'>
                        <input type='text' class='input_answer' id='answer1_B' name='answer1_B' placeholder='Đáp án B'>
                        <input type='text' class='input_answer' id='answer1_C' name='answer1_C' placeholder='Đáp án C'>
                        <input type='text' class='input_answer' id='answer1_D' name='answer1_D' placeholder='Đáp án D'>
                        <label class='label_correctAnswer'>Đáp án đúng:</label>
                        <select class = 'radio_correctAnswer' name ='correctAnswer1' id="correctAnswer1" >
                            <option value='A'>A</option>
                            <option value='B'> B</option>
                            <option value='C'> C </option>
                            <option value ='D' >D </option>
                        </select><br/>
                        <textarea class ='textarea_explaination' id='explaination1' name ='explaination1' placeholder="Giải thích"></textarea>
                    </div>           
                </div>
                <input type="button" id ='button_add_excercise' value='Thêm' onclick='AddExcercise()'>
            </div>
            <div class ="div_save">
                <input id="button_save" type="submit" value="Lưu">                                   
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
            <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
        </form>
        <% String message = (String) request.getAttribute("message");
                        if (message != null) {%>
            <%="<script> alert('" + message + "');</script>"%>
            <% request.setAttribute("message", null);%>
            <%}%>
    </body>
    <script>
        var totalExcercises = 30;
        var excerciseList = [];
        var totalExcercise=1;
        excerciseList[1] = 1;
        for (var i = 0; i <= totalExcercises; i++)
        {
            excerciseList.push(0);
        }

        function AddExcercise()
        {
            var emptyPosition = 0;
            for (var i = 1; i <= totalExcercises; i++)
            {
                if (excerciseList[i] === 0)
                {
                    emptyPosition = i;
                    break;
                }
            }

            if (emptyPosition === 0)
            {
                alert("Bạn chỉ được tối đa 30 câu hỏi!");
                return;
            }

            totalExcercise++;
            //Create textarea to enter a question
            var newExcerciseDiv = document.createElement('div');
            newExcerciseDiv.setAttribute('class', 'div_excercise');
            newExcerciseDiv.setAttribute('id', 'div_excercise' + emptyPosition);

            var allExcerciseDiv = document.getElementById('div_all_excercises');
            allExcerciseDiv.appendChild(newExcerciseDiv);

            var newQuestionTextarea = document.createElement('textarea');
            newQuestionTextarea.setAttribute('class', 'textarea_question');
            newQuestionTextarea.setAttribute('id', 'question' + emptyPosition);
            newQuestionTextarea.setAttribute('name', 'question' + emptyPosition);
            newQuestionTextarea.setAttribute('placeholder', 'Nhập câu hỏi');
            newExcerciseDiv.appendChild(newQuestionTextarea);


            var name = ['A', 'B', 'C', 'D'];
            for (var i = 0; i < 4; i++)
            {
                var newAnswer = document.createElement('input');
                newAnswer.setAttribute('class', 'input_answer');
                newAnswer.setAttribute('id', 'answer' + emptyPosition + '_' + name[i]);
                newAnswer.setAttribute('name', 'answer' + emptyPosition + '_' + name[i]);
                newAnswer.setAttribute('placeholder', 'Đáp án ' + name[i]);

                newExcerciseDiv.appendChild(newAnswer);
            }

            var label = document.createElement('label');
            label.setAttribute('class', 'label_correctAnswer');
            label.innerHTML = 'Đáp án đúng: ';
            newExcerciseDiv.appendChild(label);

            //Create a selection for the answer
            var newSelect = document.createElement('select');
            newSelect.setAttribute('class', 'radio_correctAnswer');
            newSelect.setAttribute('name', 'correctAnswer' + emptyPosition);
            newSelect.setAttribute('id', 'correctAnswer' + emptyPosition);
            newExcerciseDiv.appendChild(newSelect);

            for (var i = 0; i < 4; i++)
            {
                var newoption = document.createElement("option");
                newoption.setAttribute('value', name[i]);
                newoption.innerHTML = name[i];
                newSelect.appendChild(newoption);
            }

            var newExplanationTextarea = document.createElement('textarea');
            newExplanationTextarea.setAttribute('class', 'textarea_explaination');
            newExplanationTextarea.setAttribute('id', 'explaination' + emptyPosition);
            newExplanationTextarea.setAttribute('name', 'explaination' + emptyPosition);
            newExplanationTextarea.setAttribute('placeholder', 'Giải thích');
            newExcerciseDiv.appendChild(newExplanationTextarea);
    
            excerciseList[emptyPosition] = 1;
        }
       
        ///Load data
        
//        while ()
        <% try
        {
            int maxExcercise = Integer.parseInt(request.getAttribute("maxExcercise").toString());
            for(int excerciseid =1; excerciseid<=maxExcercise; excerciseid++)
            {
                Excercise excercise = (Excercise) request.getAttribute("Excercise"+excerciseid);
                if(excercise!=null)
                {%>
                  var textarea_question = document.getElementById("question"+<%=excerciseid%>);
                  while( textarea_question==null)
                  {
                      AddExcercise();
                      textarea_question = document.getElementById("question"+<%=excerciseid%>);
                  }
                  
                  textarea_question.value="<c:out value='<%=excercise.getQuestion()%>'/>";
                  var textarea_answer = document.getElementById("answer"+<%=excerciseid%>+"_A");
                  textarea_answer.setAttribute("value","<c:out value="<%=excercise.getAnswerA()%>"/>");
                  
                  //ans B
                  textarea_answer = document.getElementById("answer"+<%=excerciseid%>+"_B");
                  textarea_answer.setAttribute("value", "<c:out value='<%=excercise.getAnswerB()%>'/>");
                 
                 //ans C
                  textarea_answer = document.getElementById("answer"+<%=excerciseid%>+"_C");
                  textarea_answer.setAttribute("value", "<c:out value='<%=excercise.getAnswerC()%>'/>");
                  
                  //Ans D
                   textarea_answer = document.getElementById("answer"+<%=excerciseid%>+"_D");
                   textarea_answer.setAttribute("value", "<c:out value='<%=excercise.getAnswerD()%>'/>");

                   //Correct Ans
                   var select_correct_answer= document.getElementById("correctAnswer"+ <%=excerciseid%>);
                   select_correct_answer.value="<c:out value='<%=excercise.getCorrectAnswer()%>'/>";
                   
                  //explain
                   textarea_answer = document.getElementById("explaination"+<%=excerciseid%>);
                   textarea_answer.value="<c:out value='<%=excercise.getExplaination()%>'/>"
                <%}
            }
        }
        catch(Exception ex)
            {}%>
        
    </script>
</html>
