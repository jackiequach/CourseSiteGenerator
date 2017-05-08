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
var bannerImgExt;

function SitePage(title,fileName) {
    this.title = title;
    this.fileName = fileName;
}
function initCourseInfoHome() {
    var dataFile = "./js/HomeBuilder.json";
    loadDataHome(dataFile);
}

function initCourseInfoSchedule() {
    var dataFile = "./js/HomeBuilder.json";
    loadDataSchedule(dataFile);
}

function initCourseInfoHW() {
    var dataFile = "./js/HomeBuilder.json";
    loadDataHW(dataFile);
}

function initCourseInfoSyllabus() {
    var dataFile = "./js/HomeBuilder.json";
    loadDataSyllabus(dataFile);
}

function initCourseInfoProject() {
    var dataFile = "./js/HomeBuilder.json";
    loadDataProject(dataFile);
}

function loadDataHome(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        addCouseData(json);
        addSitePagesHome(json);
        addCourseInfoToPage();
    });
}


function loadDataSchedule(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        addCouseData(json);
        addSitePagesSchedule(json);
        addCourseInfoToPage();
    });
}

function loadDataHW(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        addCouseData(json);
        addSitePagesHW(json);
        addCourseInfoToPage();
    });
}

function loadDataSyllabus(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        addCouseData(json);
        addSitePagesSyllabus(json);
        addCourseInfoToPage();
    });
}

function loadDataProject(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        addCouseData(json);
        addSitePages(json);
        addCourseInfoToPage();
    });
}


function addSitePagesHome(data) {
    var navbar = $("#navbar");
    var links = "";
    for(var i=0; i < data.site_pages.length; i++) {
        if(data.site_pages[i].use) {
            if(data.site_pages[i].navbar === "Home") {
                links += "<a class=\"open_nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
            else {
                links += "<a class=\"nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
        }
    }
    navbar.append(links);
}

function addSitePagesSchedule(data) {
    var navbar = $("#navbar");
    var links = "";
    for(var i=0; i < data.site_pages.length; i++) {
        if(data.site_pages[i].use) {
            if(data.site_pages[i].navbar === "Schedule") {
                links += "<a class=\"open_nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
            else {
                links += "<a class=\"nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
        }
    }
    navbar.append(links);
}

function addSitePagesHW(data) {
    var navbar = $("#navbar");
    var links = "";
    for(var i=0; i < data.site_pages.length; i++) {
        if(data.site_pages[i].use) {
            if(data.site_pages[i].navbar === "HWs") {
                links += "<a class=\"open_nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
            else {
                links += "<a class=\"nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
        }
    }
    navbar.append(links);
}

function addSitePagesSyllabus(data) {
    var navbar = $("#navbar");
    var links = "";
    for(var i=0; i < data.site_pages.length; i++) {
        if(data.site_pages[i].use) {
            if(data.site_pages[i].navbar === "Syllabus") {
                links += "<a class=\"open_nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
            else {
                links += "<a class=\"nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
        }
    }
    navbar.append(links);
}

function addSitePagesProject(data) {
    var navbar = $("#navbar");
    var links = "";
    for(var i=0; i < data.site_pages.length; i++) {
        if(data.site_pages[i].use) {
            if(data.site_pages[i].navbar === "Projects") {
                links += "<a class=\"open_nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
            else {
                links += "<a class=\"nav\" href=\"" + data.site_pages[i].file + "\">" + data.site_pages[i].navbar + "</a>";
            }
        }
    }
    navbar.append(links);
}

function addCouseData(data) {
    subject = data.course_info[0].subject;
    semester = data.course_info[0].semester;
    number = data.course_info[0].number;
    year = data.course_info[0].year;
    title = data.course_info[0].ciTitle;
    instructorName = data.course_info[0].instructorName;
    instructorHome = data.course_info[0].instructorHome;
    bannerImgExt =  data.page_style[0].bannerImg.split('.')[2];
}

function addCourseInfoToPage() {
    var div = $("#banner");
    var text = "";
    text += subject + "-" + semester + " " + number + " " + year + "<br />" + title;
    div.append(text);
    var span = $("#instructor_link");
    var text = "";
    text += "<a href=\"" + instructorHome + "\">" + instructorName + "</a>";
    span.append(text);
}