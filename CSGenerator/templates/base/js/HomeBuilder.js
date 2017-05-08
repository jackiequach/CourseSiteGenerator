/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var subject;
var semester;
var number;
var year;
var title;
var instructorName;
var instructorHome;

function Course(hSubject, hSemester, hNumber, hYear, hTitle, hInstructorName, hInstructorHome) {
    this.subject = hSubject;
    this.semester = hSemester;
    this.number = hNumber;
    this.year = hYear;
    this.title = hTitle;
    this.instructorName = hInstructorName;
    this.instructorHome = hInstructorHome;
}
function initCourseInfo() {
    var dataFile = "./js/overall.json";
    loadData(dataFile);
}

function loadData(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        addCourseInfo(json);
    });
}

function addCourseInfo(data) {
    var div = $("#banner");
    var text = "";
    text += data.course_info[0] + "-" + data.course_info[1] + " " + data.course_info[2] + " " + data.course_info[3] + "<br />" + data.course_info[4];
    div.append(text);
    var span = $("#instructor_link");
    var text = "";
    text += "<a href=\"" + data.course_info[5] + "\">" + data.course_info[5] + "</a>";
    span.append(text);
}