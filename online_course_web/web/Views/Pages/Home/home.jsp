<%-- 
    Document   : Home
    Created on : Nov 10, 2020, 12:37:31 PM
    Author     : TRAN VAN AN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta content="text/html; charset=UTF-8; X-Content-Type-Options=nosniff" http-equiv="Content-Type" />
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
        
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        
        <link href="Views/Css/Home/home.css" type="text/css" rel="stylesheet">
        <link href="Views/Css/common.css" type="text/css" rel="stylesheet">
        
        <title>Trang Chủ</title>
    </head>
    <body>
    <div class="container">
        <div class="header">
            <div class="small-container">
                <div class="logo"><a href="home"></a></div>
                <div class="menu-function">
                    <ul>
                        <li><a href="#">Courses</a></li>
                        <li><a href="#">Program & Degrees</a></li>
                        <li><a href="#">School & Partners</a></li>
                    </ul>
                </div>
                <div class="menu-login">
                    <div class="icon-search"><i class="fas fa-search"></i></div>
<!--                    <div class="sign-in">
                        <a href="sign-in"><input type="button" value="Sign In" id="btnSignIn"></a>
                    </div>-->
                        <form action="sign-in" method="post">
                            <input type="submit" value="Sign In" id="btnSignIn">
                           <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
                        </form>
                    <div class="register">
                        <a href="sign-up"><input type="button" value="Register" id="btnRegister"></a>
                    </div>
                </div>
            </div>  
        </div>
            <div class="web-introduction">
                <div class="small-container">
                    <div class="describe-web-introduction">
                        <h2>Access 2500+ Online Courses from 140 Institutions. Start Today!</h2>
                    </div>
                    <div class="find-course">
                        <input type="button" value="Fine courses">
                    </div>
                </div>
            </div>
            <div class="search-course">
                <div class="small-container">
                    <div class="question-search-course">
                        <h3>What do you want to learn?</h3>
                    </div>
                    <div class="input-search-course">
                        <input type="text">
                        <i class="fas fa-search"></i>
                    </div>
                </div>      
            </div>
            <div class="university-introduction">
                <div class="small-container">
                    <a href="#"></a><div class="logo-school" id="logo-ute"></div></a>
                    <a href="#"></a><div class="logo-school" id="logo-uit"></div></a>
                    <a href="#"></a><div class="logo-school" id="logo-khtn"></div></a>
                    <a href="#"></a><div class="logo-school" id="logo-bk"></div></a>
                    <a href="#"></a><div class="logo-school" id="logo-hutech"></div></a>
                </div>   
            </div>
            <div class="outstanding-course">
                <div class="small-container">
                    <div class="outstanding-course-introduction">
                        <div class="title-outstanding-course">
                            <h1>Executive Education Course on Business Analytics Fundamentals for Leaders</h1>
                        </div>
                        <div class="detail-outstanding-course">
                            <span>A 4-week foundational course for those looking to be able to analyze, present findings,
                             and make meaningful conclusions about data in a business setting.</span>
                        </div>
                        <div class="request-information">
                            <a href="#"><input type="button" value="Request Information"></a>
                        </div>
                    </div>
                    <div class="logo-university-outstanding-course">
                    </div>
                </div>       
            </div>
            <div class="small-container">
                <div class="popular-subject">
                    <div class="title-popular-subject">
                        <h2>Popular Subject</h2>
                    </div>
                    <div class="subject-list">
                        <div class="subject-item">
                            <div class="header-subject-item">
                                <div class="icon-header-subject-item">
                                    <i class="far fa-keyboard icon-item"></i>
                                </div>
                                <div class="title-header-subject-item">
                                    <h2>Computer science</h2>
                                </div>
                            </div>
                            <div class="content-subject-item">
                                <ul>
                                    <li><a href="#">Azure</a></li>
                                    <li><a href="#">Blockchain</a></li>
                                    <li><a href="#">C Programming</a></li>
                                    <li><a href="#">Devops</a></li>
                                    <li><a href="#">Django</a></li>
                                    <li><a href="#">Full Stack Development</a></li>
                                    <li><a href="#">Html</a></li>
                                    <li><a href="#">Java</a></li>
                                    <li><a href="#">Javascript</a></li>
                                    <li><a href="#">Python</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="subject-item">
                            <div class="header-subject-item">
                                <div class="icon-header-subject-item">
                                    <i class="fas fa-language icon-item"></i>
                                </div>
                                <div class="title-header-subject-item">
                                    <h2>Language</h2>
                                </div>
                            </div>
                            <div class="content-subject-item">
                                <ul>
                                    <li><a href="#">Chinese</a></li>
                                    <li><a href="#">English</a></li>
                                    <li><a href="#">ESL</a></li>
                                    <li><a href="#">Grammar</a></li>
                                    <li><a href="#">Italian</a></li>
                                    <li><a href="#">Japanese</a></li>
                                    <li><a href="#">Mandarin</a></li>
                                    <li><a href="#">Sign Language</a></li>
                                    <li><a href="#">Spanish</a></li>
                                    <li><a href="#">Writing</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="subject-item">
                            <div class="header-subject-item">
                                <div class="icon-header-subject-item">
                                    <i class="fas fa-database icon-item"></i>
                                </div>
                                <div class="title-header-subject-item">
                                    <h2>Data Science</h2>
                                </div>
                            </div>
                            <div class="content-subject-item">
                                <ul>
                                    <li><a href="#">Artificial Intelligence</a></li>
                                    <li><a href="#">Big Data</a></li>
                                    <li><a href="#">Cloud Computing</a></li>
                                    <li><a href="#">Computer Programming</a></li>
                                    <li><a href="#">Data Analysis</a></li>
                                    <li><a href="#">Data Mining</a></li>
                                    <li><a href="#">Machine Learning</a></li>
                                    <li><a href="#">Power BI</a></li>
                                    <li><a href="#">Python</a></li>
                                    <li><a href="#">Quantum Computing</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="subject-item">
                            <div class="header-subject-item">
                                <div class="icon-header-subject-item">
                                    <i class="fas fa-briefcase icon-item"></i>
                                </div>
                                <div class="title-header-subject-item">
                                    <h2>Business & Management</h2>
                                </div>
                            </div>
                            <div class="content-subject-item">
                                <ul>
                                    <li><a href="#">Business Administration</a></li>
                                    <li><a href="#">Business Analysis</a></li>
                                    <li><a href="#">Corporate</a></li>
                                    <li><a href="#">Economics</a></li>
                                    <li><a href="#">Entrepreneurship</a></li>
                                    <li><a href="#">Finance</a></li>
                                    <li><a href="#">Financial Literacy</a></li>
                                    <li><a href="#">Leadership</a></li>
                                    <li><a href="#">Project Management</a></li>
                                    <li><a href="#">Statistics</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="subject-item">
                            <div class="header-subject-item">
                                <div class="icon-header-subject-item">
                                    <i class="fas fa-tools icon-item"></i>
                                </div>
                                <div class="title-header-subject-item">
                                    <h2>Engineering</h2>
                                </div>
                            </div>
                            <div class="content-subject-item">
                                <ul>
                                    <li><a href="#">Aerospace Engineering</a></li>
                                    <li><a href="#">Biomedical Engineering</a></li>
                                    <li><a href="#">Chemical Engineering</a></li>
                                    <li><a href="#">Civil Engineering</a></li>
                                    <li><a href="#">Computer Engineering</a></li>
                                    <li><a href="#">Electrical Engineering</a></li>
                                    <li><a href="#">Industrial Engineering</a></li>
                                    <li><a href="#">Mechanical Engineering</a></li>
                                    <li><a href="#">Software Engineering</a></li>
                                    <li><a href="#">Structural Engineering</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="subject-item">
                            <div class="header-subject-item">
                                <div class="icon-header-subject-item">
                                    <i class="fas fa-users icon-item"></i>
                                </div>
                                <div class="title-header-subject-item">
                                    <h2>Humanities</h2>
                                </div>
                            </div>
                            <div class="content-subject-item">
                                <ul>
                                    <li><a href="#">Art</a></li>
                                    <li><a href="#">Child Development</a></li>
                                    <li><a href="#">Epidemics</a></li>
                                    <li><a href="#">Fashion</a></li>
                                    <li><a href="#">History</a></li>
                                    <li><a href="#">Human Anatomy</a></li>
                                    <li><a href="#">Literature</a></li>
                                    <li><a href="#">Psychology</a></li>
                                    <li><a href="#">Public Speaking</a></li>
                                    <li><a href="#">Shakespeare</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            <div class="online-course">
                <a href="#"><input type="button" value="Browse online courses"></a>
            </div>
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
        <div class="copyright">
            <span>© 2020 ASQ Inc. All rights reserved.</span>
        </div>
    </div>
</body>
</html>
