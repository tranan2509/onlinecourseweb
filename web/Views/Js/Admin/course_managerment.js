/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var SizePages = 10;
var CourseJson;
function LoadUsersByNumberPages(courseJson, numberPages = 1, sizePages = SizePages)
{
    if (courseJson === "")
        return;
    CourseJson = courseJson;
    var tbody_table = document.getElementById("tbody-table");
    tbody_table.innerHTML = "";
    var start = (numberPages - 1) * sizePages;
    var end = (start + sizePages) < courseJson.length ? start + sizePages : courseJson.length;
    for (start; start < end; start++)
    {
        FillRowTable(courseJson[start], tbody_table);
    }
}
function FillRowTable(course, tbody_table)
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

    var Teacher = document.createElement('td');
    Teacher.innerHTML = course.Teacher;
    NewTr.appendChild(Teacher);
    
    var Approved = document.createElement('td');
    Approved.innerHTML = course.Approved ? "Đã duyệt" : "Chưa duyệt";
    NewTr.appendChild(Approved);

    var Function = document.createElement('td');
    Function.setAttribute('class', 'center');
        var Approve = document.createElement('input');
        Approve.setAttribute('type', 'button');
        Approve.setAttribute('class', 'btnApprove');
        Approve.setAttribute('onclick', 'approvedbtn("' + course.CourseId + '")');
        Approve.setAttribute('value', 'Duyệt');
        Function.appendChild(Approve);
        var Cancel = document.createElement('input');
        Cancel.setAttribute('type', 'button');
        Cancel.setAttribute('class', 'btnCancel');
        Cancel.setAttribute('onclick', 'canceled("' + course.CourseId + '")');
        Cancel.setAttribute('value', 'Hủy');
        Function.appendChild(Cancel);
    NewTr.appendChild(Function);

    var Detail = document.createElement('td');
    Detail.setAttribute('class', 'center');
    Detail.setAttribute('onclick', 'goCourse("'+ course.CourseId + '")');
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
    LoadUsersByNumberPages(CourseJson, number.value);
}

function reloadDataAccount(){
    var number = document.getElementById("numberOfPages");
    LoadUsersByNumberPages(CourseJson, number.value);
}

function approvedbtn(courseId)
{
    window.location = "/new_online_course_web/course-managerment?courseId=" + courseId + "&stateApproved=approved";
}

function canceled(courseId)
{
     ShowFrontDivSendEmail(courseId, 'unapproved');
}

function CloseFrontDivSendEmail()
{
    window.location = "/new_online_course_web/course-managerment?isShowEmail=false";
}

function ShowFrontDivSendEmail(courseId, state)
{
    window.location = "/new_online_course_web/course-managerment?isShowEmail=true&courseId=" + courseId + "&stateApproved=" + state;
}

function goCourse(courseId)
{
     window.location = "/new_online_course_web/Display_Course_Introduction_Student?courseid=" + courseId;
}