<%-- 
    Document   : excercise_student
    Created on : Jan 2, 2021, 3:45:04 PM
    Author     : A556U
--%>
<%@page import="Model.Excercise"%>
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
        <link rel="stylesheet" href="Views/Css/Course/excercise_student_css.css">
        
        <link rel="stylesheet" href="Views/Css/common.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        
    </head>
    <body>
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
        <form action="Process_Excercise_Student" medthod ="post">  
            <%
                    Part part =(Part) session.getAttribute("part");           
                    Course course= part.getCourse();
                     %>
                    
            <%
            Chap chap = part.getChap();
            request.setAttribute("part", part);
            %>
            <div id="container">
                <h1 id='label_chapName'><%=chap.getName()%></h1>
                <h1 id='label_partName'><%=part.getName()%></h1>
                <div id='div_all_excercises'>
                    <div class='div_excercise' id='div_excercise1'>
                        <input type='button' class ='button_question' id='button_question1' >
                        <input type='radio' class='radio_answer' id='radio_answer1_A' name='answer1' value='A' ><label for='radio_answer1_A'id='label_answer1_A'></label><br>
                        <input type='radio' class='radio_answer' id='radio_answer1_B' name='answer1' value='B'><label for='radio_answer1_B' id='label_answer1_B'></label><br>
                        <input type='radio' class='radio_answer' id='radio_answer1_C' name='answer1' value='C' ><label for='radio_answer1_C' id='label_answer1_C'></label><br>
                        <input type='radio' class='radio_answer' id='radio_answer1_D' name='answer1' value='D'><label for='radio_answer1_D' id='label_answer1_D'></label><br>
                        <p class='p_correctAnswer' id = 'p_correctAnswer1'></p>
                      
                        <p class ='p_explaination' id='p_explaination1' ></p>
                    </div>           
                </div>
            </div>
            <div id ="div_button">
                <input id="button_completed" type="submit" value="Hoàn thành"> 
                 <a  href="student"><input  id='button_profile' type="button" value="Trang chính"></a>
               
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
        
        console.log("${message}");
        
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

            totalExcercise++;
            //Create textarea to enter a question
            var newExcerciseDiv = document.createElement('div');
            newExcerciseDiv.setAttribute('class', 'div_excercise');
            newExcerciseDiv.setAttribute('id', 'div_excercise' + emptyPosition);

            var allExcerciseDiv = document.getElementById('div_all_excercises');
            allExcerciseDiv.appendChild(newExcerciseDiv);

            var newQuestion = document.createElement('input');
            newQuestion.setAttribute("type", "button");
            newQuestion.setAttribute('class', 'button_question');
            newQuestion.setAttribute('id', 'button_question' + emptyPosition);
            newExcerciseDiv.appendChild(newQuestion);

            var name = ['A', 'B', 'C', 'D'];
            for (var i = 0; i < 4; i++)
            {
                var newAnswer = document.createElement('input');
                newAnswer.setAttribute("type","radio");
                newAnswer.setAttribute('class', 'radio_answer');
                newAnswer.setAttribute('id', 'radio_answer' + emptyPosition + '_' + name[i]);
                newAnswer.setAttribute("name", 'answer' + emptyPosition);
                newAnswer.setAttribute("value", name[i]);
                newExcerciseDiv.appendChild(newAnswer);
                
                //Answer 
                var newLabel = document.createElement("label");
                newLabel.setAttribute("id", "label_answer"+emptyPosition+"_"+name[i]);
                newLabel.setAttribute("for",'radio_answer' + emptyPosition + '_' + name[i] );
                newExcerciseDiv.appendChild(newLabel);
                
                var spaceLine = document.createElement("br");
                newExcerciseDiv.appendChild(spaceLine);
            }

            var pCorrectAnswer = document.createElement('p');
            pCorrectAnswer.setAttribute('class', 'p_correctAnswer');
            pCorrectAnswer.setAttribute('id', 'p_correctAnswer'+emptyPosition);
//            pCorrectAnswer.innerHTML = 'Đáp án đúng: ';
            newExcerciseDiv.appendChild(pCorrectAnswer);

           

            var newExplanationTextarea = document.createElement('p');
            newExplanationTextarea.setAttribute('class', 'p_explaination');
            newExplanationTextarea.setAttribute('id', 'p_explaination' + emptyPosition);;
            newExcerciseDiv.appendChild(newExplanationTextarea);
    
            excerciseList[emptyPosition] = 1;
        }
       
        ///Load data
       
        <% try
        {
            int maxExcercise = Integer.parseInt(request.getAttribute("maxExcercise").toString());
             request.setAttribute("maxExcercise", 0);
            for(int excerciseid =1; excerciseid<=maxExcercise; excerciseid++)
            {
                
                Excercise excercise = (Excercise) request.getAttribute("Excercise"+excerciseid);
                if(excercise!=null)
                {
                    String correctAnswer = excercise.getCorrectAnswer();%>
                        console.log("chạy");
                    var button_question = document.getElementById("button_question"+<%=excerciseid%>);
                    while( button_question==null)
                    {
                        AddExcercise();
                        button_question = document.getElementById("button_question"+<%=excerciseid%>);
                    }

                    button_question.value="<c:out value='<%=excercise.getQuestion()%>'/>";
                  
                  

                    var ansLabel = document.getElementById("label_answer"+"<%=excercise.getExcerciseOrder()%>"+"_A");
                    if("<%=excercise.getAnswerA()%>"!=="")
                    {
                        ansLabel.innerHTML="A. "+ "<%=excercise.getAnswerA()%>";
                    }
                    else//không có thì xóa
                    {
                        ansLabel.remove();
                        document.getElementById("radio_answer"+"<%=excercise.getExcerciseOrder()%>"+"_A").remove();
                    }

                     ansLabel = document.getElementById("label_answer"+"<%=excercise.getExcerciseOrder()%>"+"_B");
                     if("<%=excercise.getAnswerB()%>"!=="")
                    {
                        ansLabel.innerHTML="B. "+"<%=excercise.getAnswerB()%>";
                    }
                    else//không có thì xóa
                    {
                        ansLabel.remove();
                        document.getElementById("radio_answer"+"<%=excercise.getExcerciseOrder()%>"+"_B").style.display="none";
                    }


                    ansLabel = document.getElementById("label_answer"+"<%=excercise.getExcerciseOrder()%>"+"_C");
                    if("<%=excercise.getAnswerC()%>"!=="")
                    {
                        ansLabel.innerHTML= "C. "+"<%=excercise.getAnswerC()%>";
                    }
                    else
                    {
                        ansLabel.style.display="none";
                       document.getElementById("radio_answer"+"<%=excercise.getExcerciseOrder()%>"+"_C").style.display="none";
                    }

                    ansLabel = document.getElementById("label_answer"+"<%=excercise.getExcerciseOrder()%>"+"_D");
                    if("<%=excercise.getAnswerD()%>"!=="")
                    {
                        ansLabel.innerHTML= "D. "+"<%=excercise.getAnswerD()%>";
                    }
                    else
                    {
                        ansLabel.style.display="none";
                        document.getElementById("radio_answer"+"<%=excercise.getExcerciseOrder()%>"+"_D").style.display="none";
                    }
                    
                    //Đáp án
                        <%String result = (String)request.getAttribute("resultOfAnswer"+excercise.getExcerciseOrder());
                        if(result!=null)
                        {%>
                        var correctAnswerLabel = document.getElementById("p_correctAnswer"+"<%=excercise.getExcerciseOrder()%>");
                        correctAnswerLabel.innerHTML= "<%=result%>";
                         //Giải thích
                        var p_enplaination = document.getElementById("p_explaination"+"<%=excercise.getExcerciseOrder()%>");
                         p_enplaination.innerHTML="<%=excercise.getExplaination()%>";
                         
                         //Thêm nút làm lại bài
                         if(document.getElementById("button_do_again")==null)
                         {
                         var div_button = document.getElementById("div_button");
                         var button_completed = document.getElementById("button_completed");
//                   
                         button_completed.insertAdjacentHTML("afterend", "<a id='link_do_again' href= "+ "Display_Excercise_Student?courseid="+"${part.getCourse().getCourseId()}"
                                +"&chapid="+"${part.getChap().getChapOrder()}" +"&partid="+"${part.getPartOrder()}"+">");
                          
                        var link_do_again = document.getElementById("link_do_again");
                         
                         var button_do_again = document.createElement("input");
                         button_do_again.setAttribute("type", "button");
                         button_do_again.setAttribute("id", "button_do_again");
                         button_do_again.setAttribute("value", "Làm lại");
                         link_do_again.appendChild(button_do_again);
                        
                        }
                        
                        //Ẩn nứt hoàn thành
                        var button_completed = document.getElementById("button_completed");
                        button_completed.style.display="none";
                         
                         //in kết quả làm
                        if("<%=result%>"==="Trả lời đúng")
                            correctAnswerLabel.setAttribute("style", "color:#01DF01");
                           else
                                correctAnswerLabel.setAttribute("style", "color:red");
                          <%}%>
                              
                    
                     
                        //Chọn lại lựa chọn mà student đã chọn
                          <%String selectedAns = (String)request.getAttribute("answer"+excercise.getExcerciseOrder());
                          if(selectedAns!=null) {%>
                            var radio= document.getElementById("radio_answer"+"<%=excercise.getExcerciseOrder()%>"+"_"+"<%=selectedAns%>");
                             radio.checked=true;
                        <%}%>
                <%}
            }
           
        }
        catch(Exception ex)
            {}%>
        
    </script>
</html>
