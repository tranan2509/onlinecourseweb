/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var SizePages = 10;
var UsersJson;
function LoadUsersByNumberPages(usersJson, numberPages = 1, sizePages = SizePages)
{
    console.log(usersJson);
    if (usersJson === "")
        return;
    UsersJson = usersJson;
    
    var tbody_table = document.getElementById("tbody-table");
    tbody_table.innerHTML = "";
    var start = (numberPages - 1) * sizePages;
    var end = (start + sizePages) < usersJson.length ? start + sizePages : usersJson.length;
    for (start; start < end; start++)
    {
        FillRowTable(usersJson[start], tbody_table);
    }
}

function FillRowTable(user, tbody_table)
{
    var NewTr = document.createElement("tr");

    var UserId = document.createElement('td');
    UserId.setAttribute('class', 'center');
    UserId.innerHTML = user.UserId;
    NewTr.appendChild(UserId);

    var Name = document.createElement('td');
    Name.innerHTML = user.Name.replace("&#47", "/");
    NewTr.appendChild(Name);

    var DateOfBirth = document.createElement('td');
    DateOfBirth.innerHTML = user.DateOfBirth;
    NewTr.appendChild(DateOfBirth);
    
    var Gender = document.createElement('td');
    Gender.innerHTML = user.Gender ? "Nam" : "Nữ";
    NewTr.appendChild(Gender);

    var Email = document.createElement('td');
    Email.innerHTML = user.Email;
    NewTr.appendChild(Email);
    
    var Phone = document.createElement('td');
    Phone.innerHTML = user.Phone;
    NewTr.appendChild(Phone);
    
    var RoleName = document.createElement('td');
    RoleName.innerHTML = user.RoleName;
    NewTr.appendChild(RoleName);

    var Detail = document.createElement('td');
    Detail.setAttribute('class', 'center');
     Detail.setAttribute('onclick', 'loadDetail("' + user.UserId + '")');
    Detail.innerHTML = '<i class="fas fa-info-circle"></i>';
    NewTr.appendChild(Detail);

    tbody_table.appendChild(NewTr);
}

function numberOfPages(type)
{
    var number = document.getElementById("numberOfPages");
    if (type === 'start'){
        number.value = 1;
    }else if (type === 'end'){
        number.value = number.max;
    }else if (type === 'previous'){
        if (parseInt(number.value) > 1)
            number.value = parseInt(number.value) - 1;
    }else if (type === 'next')
    {
        if (parseInt(number.value) < number.max)
            number.value = parseInt(number.value) + 1;
    }
    LoadUsersByNumberPages(UsersJson, number.value);
}

//Load courser information of user
function LoadCoursesInfoOfUser(coursesJson)
{
    console.log(coursesJson);
    if (coursesJson === "")
        return;
    var tbody_table_info = document.getElementById("tbody-table-info");
    tbody_table_info.innerHTML = "";
    var end = coursesJson.length;
    for (var start = 0; start < end; start++)
    {
        FillRowTableCoursesInfoOfUser(coursesJson[start], tbody_table_info);
    }
}

function FillRowTableCoursesInfoOfUser(course, tbody_table_info)
{
    var self = this;
    var NewTr = document.createElement("tr");
    var self = NewTr;

    var CourseId = document.createElement('td');
    CourseId.setAttribute('class', 'center');
    CourseId.innerHTML = course.CourseId;
    NewTr.appendChild(CourseId);

    var Name = document.createElement('td');
    Name.innerHTML = course.Name;
    NewTr.appendChild(Name);

    var Approved = document.createElement('td');
    Approved.innerHTML = course.Approved ? "Đã duyệt" : "Chưa duyệt";
    NewTr.appendChild(Approved);
    

    tbody_table_info.appendChild(NewTr);
}

function reloadDataAccount(){
    var number = document.getElementById("numberOfPages");
    LoadUsersByNumberPages(UsersJson, number.value);
}

function loadDetail(userId)
{
    window.location = "/new_online_course_web/user-managerment?isShowInfo=true&userId="+userId;
}

function CloseFrontDivInfo()
{
    window.location = "/new_online_course_web/user-managerment?isShowInfo=false";
}