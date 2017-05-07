/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.file;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.SitePage;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.workspace.CSGController;
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import static djf.settings.AppStartupConstants.PATH_WORK;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import jtps.jTPS;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jackie Quach
 */
public class CSGFiles implements AppFileComponent {
    CSGeneratorApp app;
    
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_UNDERGRAD = "undergrad";
    static final String JSON_NAME = "name";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_EMAIL = "email";
    
    static final String JSON_COURSE_INFO = "course_info";
    static final String JSON_SUBJECT = "subject";
    static final String JSON_NUMBER = "number";
    static final String JSON_SEMESTER = "semester";
    static final String JSON_YEAR = "year";
    static final String JSON_TITLE_CI = "ciTitle";
    static final String JSON_INSTRUCTOR_NAME = "instructorName";
    static final String JSON_INSTRUCTOR_HOME = "instructorHome";
    static final String JSON_EXPORT_DIR = "exportDir";
    static final String JSON_TEMPLATE_DIR = "templateDir";
    static final String JSON_SITE_PAGES = "site_pages";
    static final String JSON_USE = "use";
    static final String JSON_NAVBAR = "navbar";
    static final String JSON_FILE = "file";
    static final String JSON_SCRIPT = "script";
    static final String JSON_PAGE_STYLE = "page_style";
    static final String JSON_BANNER_IMG = "bannerImg";
    static final String JSON_LEFT_IMG = "leftImg";
    static final String JSON_RIGHT_IMG = "rightImg";
    static final String JSON_STYLESHEET = "stylesheet";
    
    static final String JSON_RECITATIONS = "recitations";
    static final String JSON_SECTION = "section";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_DAY_REC = "day_time";
    static final String JSON_LOCATION = "location";
    static final String JSON_TA_ONE = "ta_1";
    static final String JSON_TA_TWO = "ta_2";
    
    static final String JSON_CALENDAR = "calendar_boundaries";
    static final String JSON_START_MONDAY_MONTH = "startingMondayMonth";
    static final String JSON_START_MONDAY_DAY = "startingMondayDay";
    static final String JSON_END_FRIDAY_MONTH = "endingFridayMonth";
    static final String JSON_END_FRIDAY_DAY = "endingFridayDay";
    static final String JSON_HOLIDAYS = "holidays";
    static final String JSON_LECTURES = "lectures";
    static final String JSON_REFERENCES = "references";
    static final String JSON_SRECITATIONS = "recitations";
    static final String JSON_HWS = "hws";
    static final String JSON_START_MONDAY = "startMonday";
    static final String JSON_END_FRIDAY = "endFriday";
    static final String JSON_SCHEDULE = "schedule_items";
    static final String JSON_TYPE = "type";
    static final String JSON_DATE = "date";
    static final String JSON_MONTH = "month";
    static final String JSON_SDAY = "day";
    static final String JSON_TIME_SI = "time";
    static final String JSON_TITLE = "title";
    static final String JSON_TOPIC = "topic";
    static final String JSON_LINK_SI = "link";
    static final String JSON_CRITERIA = "criteria";
    
    
    static final String JSON_WORK = "work";
    static final String JSON_PROJECTS = "projects";
    static final String JSON_TEAMS = "teams";
    static final String JSON_NAME_TEAM = "name";
    static final String JSON_COLOR = "color";
    static final String JSON_TEXT_COLOR = "textColor";
    static final String JSON_LINK = "link";
    
    static final String JSON_STUDENTS = "students";
    static final String JSON_FIRST_NAME = "firstName";
    static final String JSON_LAST_NAME = "lastName";
    static final String JSON_TEAM = "team";
    static final String JSON_ROLE = "role";
    
    static final String PATH_PUBLIC_HTML = "../CSGeneratorTester/public_html";
    
    public CSGFiles(CSGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        // GET THE DATA
	CSGData dataManager = (CSGData)data;
        
        JsonArrayBuilder courseInfoArrayBuilder = Json.createArrayBuilder();
        JsonObject courseInfo = Json.createObjectBuilder()
            .add(JSON_SUBJECT, dataManager.getSubject())
            .add(JSON_NUMBER, dataManager.getNumber())
            .add(JSON_SEMESTER, dataManager.getSemester())
            .add(JSON_YEAR, dataManager.getYear())
            .add(JSON_TITLE_CI, dataManager.getTitle())
            .add(JSON_INSTRUCTOR_NAME, dataManager.getInstructorName())
            .add(JSON_INSTRUCTOR_HOME, dataManager.getInstructorHome())
            .add(JSON_EXPORT_DIR, dataManager.getExportDirPath())
            .add(JSON_TEMPLATE_DIR, dataManager.getTemplateDirPath()).build();
        courseInfoArrayBuilder.add(courseInfo);
        JsonArray courseInfoArray = courseInfoArrayBuilder.build();
        
        JsonArrayBuilder sitePagesArrayBuilder = Json.createArrayBuilder();
        ObservableList<SitePage> sitePages = dataManager.getSitePages();
	for (SitePage sitePage : sitePages) {	    
	    JsonObject siteJson = Json.createObjectBuilder()
                .add(JSON_USE, sitePage.getUse())
                .add(JSON_NAVBAR, sitePage.getNavbar())
                .add(JSON_FILE, sitePage.getFile())
                .add(JSON_SCRIPT, sitePage.getScript()).build();
	    sitePagesArrayBuilder.add(siteJson);
	}
        JsonArray sitePagesArray = sitePagesArrayBuilder.build();
        
        JsonArrayBuilder pageStyleArrayBuilder = Json.createArrayBuilder();
        JsonObject pageStyle = Json.createObjectBuilder()
            .add(JSON_BANNER_IMG, dataManager.getBannerImgPath())
            .add(JSON_LEFT_IMG, dataManager.getLeftFooterImgPath())
            .add(JSON_RIGHT_IMG, dataManager.getRightFooterImgPath())
            .add(JSON_STYLESHEET, dataManager.getStylesheet()).build();
        pageStyleArrayBuilder.add(pageStyle);
        JsonArray pageStyleArray = pageStyleArrayBuilder.build();

	// NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	
            if(ta.isUndergrad()) {
                JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName())
                    .add(JSON_EMAIL, ta.getEmail()).build();
                taArrayBuilder.add(taJson);
            }
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
                .add(JSON_DAY, ts.getDay())
                .add(JSON_TIME, ts.getTime())
                .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
	JsonArrayBuilder recitationsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitation> recitations = dataManager.getRecitations();
	for (Recitation recitation : recitations) {	    
	    JsonObject recitationJson = Json.createObjectBuilder()
                .add(JSON_SECTION, recitation.getSection())
                .add(JSON_INSTRUCTOR, recitation.getInstructor())
                .add(JSON_DAY, recitation.getDay())
                .add(JSON_LOCATION, recitation.getLocation())
                .add(JSON_TA_ONE, recitation.getTAOne())
                .add(JSON_TA_TWO, recitation.getTATwo()).build();
	    recitationsArrayBuilder.add(recitationJson);
	}
	JsonArray recitationsArray = recitationsArrayBuilder.build();
        
        JsonArrayBuilder calendarArrayBuilder = Json.createArrayBuilder();
        JsonObject calendarBoundariesJson = Json.createObjectBuilder()
            .add(JSON_START_MONDAY, dataManager.getStartMonday())
            .add(JSON_END_FRIDAY, dataManager.getEndFriday()).build();
        calendarArrayBuilder.add(calendarBoundariesJson);
        JsonArray calendarArray = calendarArrayBuilder.build();
        
        JsonArrayBuilder scheduleArrayBuilder = Json.createArrayBuilder();
        ObservableList<ScheduleItem> scheduleItems = dataManager.getScheduleItems();
        for (ScheduleItem scheduleItem : scheduleItems) {	    
	    JsonObject scheduleItemJson = Json.createObjectBuilder()
                .add(JSON_TYPE, scheduleItem.getType())
                .add(JSON_DATE, scheduleItem.getDate())
                .add(JSON_TIME, scheduleItem.getTime())
                .add(JSON_TITLE, scheduleItem.getTitle())
                .add(JSON_TOPIC, scheduleItem.getTopic())
                .add(JSON_LINK_SI, scheduleItem.getLink())
                .add(JSON_CRITERIA, scheduleItem.getCriteria()).build();
	    scheduleArrayBuilder.add(scheduleItemJson);
	}
        JsonArray scheduleArray = scheduleArrayBuilder.build();
        
        JsonArrayBuilder teamsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
	for (Team team : teams) {	    
	    JsonObject teamJson = Json.createObjectBuilder()
                .add(JSON_NAME_TEAM, team.getName())
                .add(JSON_COLOR, team.getColor())
                .add(JSON_TEXT_COLOR, team.getTextColor())
                .add(JSON_LINK, team.getLink()).build();
	    teamsArrayBuilder.add(teamJson);
	}
	JsonArray teamsArray = teamsArrayBuilder.build();
        
        JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Student> students = dataManager.getStudents();
	for (Student student : students) {	    
	    JsonObject teamJson = Json.createObjectBuilder()
                .add(JSON_FIRST_NAME, student.getFirstName())
                .add(JSON_LAST_NAME, student.getLastName())
                .add(JSON_TEAM, student.getTeam())
                .add(JSON_ROLE, student.getRole()).build();
	    studentsArrayBuilder.add(teamJson);
	}
	JsonArray studentsArray = studentsArrayBuilder.build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
            .add(JSON_COURSE_INFO, courseInfoArray)
            .add(JSON_SITE_PAGES, sitePagesArray)
            .add(JSON_PAGE_STYLE, pageStyleArray)
            .add(JSON_START_HOUR, "" + dataManager.getStartHour())
            .add(JSON_END_HOUR, "" + dataManager.getEndHour())
            .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
            .add(JSON_OFFICE_HOURS, timeSlotsArray)
            .add(JSON_RECITATIONS, recitationsArray)
            .add(JSON_CALENDAR, calendarArray)
            .add(JSON_SCHEDULE, scheduleArray)
            .add(JSON_TEAMS, teamsArray)
            .add(JSON_STUDENTS, studentsArray)
            .build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void saveTAJson(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;
        // NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	
            if(ta.isUndergrad()) {
                JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName())
                    .add(JSON_EMAIL, ta.getEmail()).build();
                taArrayBuilder.add(taJson);
            }
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();
        
	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
                .add(JSON_DAY, ts.getDay())
                .add(JSON_TIME, ts.getTime())
                .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        // THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
            .add(JSON_START_HOUR, "" + dataManager.getStartHour())
            .add(JSON_END_HOUR, "" + dataManager.getEndHour())
            .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
            .add(JSON_OFFICE_HOURS, timeSlotsArray)
            .build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void saveRecitationsJson(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;
        
        JsonArrayBuilder recitationsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitation> recitations = dataManager.getRecitations();
	for (Recitation recitation : recitations) {	    
	    JsonObject recitationJson = Json.createObjectBuilder()
                .add(JSON_SECTION, recitation.getSection())
                .add(JSON_INSTRUCTOR, recitation.getInstructor())
                .add(JSON_DAY, recitation.getDay())
                .add(JSON_LOCATION, recitation.getLocation())
                .add(JSON_TA_ONE, recitation.getTAOne())
                .add(JSON_TA_TWO, recitation.getTATwo()).build();
	    recitationsArrayBuilder.add(recitationJson);
	}
	JsonArray recitationsArray = recitationsArrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
            .add(JSON_RECITATIONS, recitationsArray).build();
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void saveScheduleJson(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;
        
        JsonArrayBuilder holidayArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder lectureArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder referenceArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder hwArrayBuilder = Json.createArrayBuilder();
        ObservableList<ScheduleItem> scheduleItems = dataManager.getScheduleItems();
        for (ScheduleItem scheduleItem : scheduleItems) {
            if(scheduleItem.getType().equals("Holiday")) {
                JsonObject holidayItemJson = Json.createObjectBuilder()
                    .add(JSON_MONTH, scheduleItem.getDate().split("-")[1])
                    .add(JSON_SDAY, scheduleItem.getDate().split("-")[2])
                    .add(JSON_TITLE, scheduleItem.getTitle())
                    .add(JSON_LINK_SI, scheduleItem.getLink()).build();
                holidayArrayBuilder.add(holidayItemJson);
            }
            if(scheduleItem.getType().equals("Lecture")) {
                JsonObject lectureItemJson = Json.createObjectBuilder()
                    .add(JSON_MONTH, scheduleItem.getDate().split("-")[1])
                    .add(JSON_SDAY, scheduleItem.getDate().split("-")[2])
                    .add(JSON_TITLE, scheduleItem.getTitle())
                    .add(JSON_TOPIC, scheduleItem.getTopic())
                    .add(JSON_LINK_SI, scheduleItem.getLink()).build();
                lectureArrayBuilder.add(lectureItemJson);
            }
            if(scheduleItem.getType().equals("Recitation")) {
                JsonObject recitationItemJson = Json.createObjectBuilder()
                    .add(JSON_MONTH, scheduleItem.getDate().split("-")[1])
                    .add(JSON_SDAY, scheduleItem.getDate().split("-")[2])
                    .add(JSON_TITLE, scheduleItem.getTitle())
                    .add(JSON_TOPIC, scheduleItem.getTopic()).build();
                recitationArrayBuilder.add(recitationItemJson);
            }
            if(scheduleItem.getType().equals("Reference")) {
                JsonObject referenceItemJson = Json.createObjectBuilder()
                    .add(JSON_MONTH, scheduleItem.getDate().split("-")[1])
                    .add(JSON_SDAY, scheduleItem.getDate().split("-")[2])
                    .add(JSON_TITLE, scheduleItem.getTitle())
                    .add(JSON_TOPIC, scheduleItem.getTopic())
                    .add(JSON_LINK_SI, scheduleItem.getLink()).build();
                referenceArrayBuilder.add(referenceItemJson);
            }
            if(scheduleItem.getType().equals("HW")) {
                JsonObject hwItemJson = Json.createObjectBuilder()
                    .add(JSON_MONTH, scheduleItem.getDate().split("-")[1])
                    .add(JSON_SDAY, scheduleItem.getDate().split("-")[2])
                    .add(JSON_TITLE, scheduleItem.getTitle())
                    .add(JSON_TOPIC, scheduleItem.getTopic())
                    .add(JSON_TIME, scheduleItem.getTime())
                    .add(JSON_LINK_SI, scheduleItem.getLink())
                    .add(JSON_CRITERIA, scheduleItem.getCriteria()).build();
                hwArrayBuilder.add(hwItemJson);
            }
	}
        JsonArray holidayArray = holidayArrayBuilder.build();
        JsonArray lectureArray = lectureArrayBuilder.build();
        JsonArray recitationArray = recitationArrayBuilder.build();
        JsonArray referenceArray = referenceArrayBuilder.build();
        JsonArray hwArray = hwArrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
            .add(JSON_START_MONDAY_MONTH, dataManager.getStartMonday().split("-")[1])
            .add(JSON_START_MONDAY_DAY, dataManager.getStartMonday().split("-")[2])
            .add(JSON_END_FRIDAY_MONTH, dataManager.getEndFriday().split("-")[1])
            .add(JSON_END_FRIDAY_DAY, dataManager.getEndFriday().split("-")[2])
            .add(JSON_HOLIDAYS, holidayArray)
            .add(JSON_LECTURES, lectureArray)
            .add(JSON_REFERENCES, referenceArray)
            .add(JSON_SRECITATIONS, recitationArray)
            .add(JSON_HWS, hwArray)
            .build();
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void saveProjectsJson(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;
        
        JsonArrayBuilder workArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
	for (Team team : teams) {
            JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();
            ObservableList<Student> students = dataManager.getStudents();
            for (Student student : students) {
                if(student.getTeam().equals(team.getName())) {
                    JsonObject studentJson = Json.createObjectBuilder()
                        .add(JSON_FIRST_NAME, student.getFirstName())
                        .add(JSON_LAST_NAME, student.getLastName()).build();
                    studentsArrayBuilder.add(studentJson);
                }
            }
            JsonArray studentsArray = studentsArrayBuilder.build();
	    JsonObject teamJson = Json.createObjectBuilder()
                .add(JSON_NAME_TEAM, team.getName())
                .add(JSON_STUDENTS, studentsArray)
                .add(JSON_LINK, team.getLink()).build();
	    workArrayBuilder.add(teamJson);
	}
	JsonArray workArray = workArrayBuilder.build();
        
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
            .add(JSON_WORK, workArray)
            .build();
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }

    public void saveTeamsStudentsJson(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;
        
        JsonArrayBuilder teamsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
	for (Team team : teams) {	    
	    JsonObject teamJson = Json.createObjectBuilder()
                .add(JSON_NAME_TEAM, team.getName())
                .add(JSON_COLOR, team.getColor())
                .add(JSON_TEXT_COLOR, team.getTextColor())
                .add(JSON_LINK, team.getLink()).build();
	    teamsArrayBuilder.add(teamJson);
	}
	JsonArray teamsArray = teamsArrayBuilder.build();
        
        JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Student> students = dataManager.getStudents();
	for (Student student : students) {	    
	    JsonObject studentJson = Json.createObjectBuilder()
                .add(JSON_FIRST_NAME, student.getFirstName())
                .add(JSON_LAST_NAME, student.getLastName())
                .add(JSON_TEAM, student.getTeam())
                .add(JSON_ROLE, student.getRole()).build();
	    studentsArrayBuilder.add(studentJson);
	}
	JsonArray studentsArray = studentsArrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
            .add(JSON_TEAMS, teamsArray)
            .add(JSON_STUDENTS, studentsArray)
            .build();
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;

	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
        
        JsonArray jsonCourseInfoArray = json.getJsonArray(JSON_COURSE_INFO);
        for(int i = 0; i < jsonCourseInfoArray.size(); i++) {
            JsonObject jsonCourseInfo = jsonCourseInfoArray.getJsonObject(i);
            String subject = jsonCourseInfo.getString(JSON_SUBJECT);
            dataManager.setSubject(subject);
            String number = jsonCourseInfo.getString(JSON_NUMBER);
            dataManager.setNumber(number);
            String semester = jsonCourseInfo.getString(JSON_SEMESTER);
            dataManager.setSemester(semester);
            String year = jsonCourseInfo.getString(JSON_YEAR);
            dataManager.setYear(year);
            String title = jsonCourseInfo.getString(JSON_TITLE_CI);
            dataManager.setTitle(title);
            String instructorName = jsonCourseInfo.getString(JSON_INSTRUCTOR_NAME);
            dataManager.setInstructorName(instructorName);
            String instructorHome = jsonCourseInfo.getString(JSON_INSTRUCTOR_HOME);
            dataManager.setInstructorHome(instructorHome);
            String exportDirPath = jsonCourseInfo.getString(JSON_EXPORT_DIR);
            dataManager.setExportDirPath(exportDirPath);
            String templateDirPath = jsonCourseInfo.getString(JSON_TEMPLATE_DIR);
            dataManager.setTemplateDirPath(templateDirPath);
        }
        
        JsonArray jsonSitePagesArray = json.getJsonArray(JSON_SITE_PAGES);
        for (int i = 0; i < jsonSitePagesArray.size(); i++) {
            JsonObject jsonSitePage = jsonSitePagesArray.getJsonObject(i);
            Boolean use = jsonSitePage.getBoolean(JSON_USE);
            String navbarTitle = jsonSitePage.getString(JSON_NAVBAR);
            String fileName = jsonSitePage.getString(JSON_FILE);
            String script = jsonSitePage.getString(JSON_SCRIPT);
            dataManager.addSitePage(use, navbarTitle, fileName, script);
        }
        
        JsonArray jsonPageStyleArray = json.getJsonArray(JSON_PAGE_STYLE);
        for(int i = 0; i < jsonPageStyleArray.size(); i++) {
            JsonObject jsonPageStyle = jsonPageStyleArray.getJsonObject(i);
            String bannerImgPath = jsonPageStyle.getString(JSON_BANNER_IMG);
            dataManager.setBannerImgPath(bannerImgPath);
            String leftFooterImgPath = jsonPageStyle.getString(JSON_LEFT_IMG);
            dataManager.setLeftFooterImgPath(leftFooterImgPath);
            String rightFooterImgPath = jsonPageStyle.getString(JSON_RIGHT_IMG);
            dataManager.setRightFooterImgPath(rightFooterImgPath);
            String stylesheet = jsonPageStyle.getString(JSON_STYLESHEET);
            dataManager.setStylesheet(stylesheet);
        }
        
	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
        dataManager.initHours(startHour, endHour);

        // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
        app.getWorkspaceComponent().reloadWorkspace(dataManager);

        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            Boolean undergrad = jsonTA.getBoolean(JSON_UNDERGRAD);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            dataManager.addTA(undergrad, name, email);
        }

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            dataManager.addOfficeHoursReservation(day, time, name);
        }
        
        JsonArray jsonRecitationArray = json.getJsonArray(JSON_RECITATIONS);
        for (int i = 0; i < jsonRecitationArray.size(); i++) {
            JsonObject jsonRecitation = jsonRecitationArray.getJsonObject(i);
            String section = jsonRecitation.getString(JSON_SECTION);
            String instructor = jsonRecitation.getString(JSON_INSTRUCTOR);
            String day = jsonRecitation.getString(JSON_DAY);
            String location = jsonRecitation.getString(JSON_LOCATION);
            String taOne = jsonRecitation.getString(JSON_TA_ONE);
            String taTwo = jsonRecitation.getString(JSON_TA_TWO);
            dataManager.addRecitation(section, instructor, day, location, taOne, taTwo);
        }
        
        JsonArray jsonCalendarArray = json.getJsonArray(JSON_CALENDAR);
        for (int i = 0; i < jsonCalendarArray.size(); i++) {
            JsonObject jsonCalendar = jsonCalendarArray.getJsonObject(i);
            String startMonday = jsonCalendar.getString(JSON_START_MONDAY);
            String endFriday = jsonCalendar.getString(JSON_END_FRIDAY);
            dataManager.setStartMonday(LocalDate.parse(startMonday));
            dataManager.setEndFriday(LocalDate.parse(endFriday));
        }
        
        JsonArray jsonScheduleItemArray = json.getJsonArray(JSON_SCHEDULE);
        for (int i = 0; i < jsonScheduleItemArray.size(); i++) {
            JsonObject jsonScheduleItem = jsonScheduleItemArray.getJsonObject(i);
            String type = jsonScheduleItem.getString(JSON_TYPE);
            String date = jsonScheduleItem.getString(JSON_DATE);
            String time = jsonScheduleItem.getString(JSON_TIME_SI);
            String title = jsonScheduleItem.getString(JSON_TITLE);
            String topic = jsonScheduleItem.getString(JSON_TOPIC);
            String link = jsonScheduleItem.getString(JSON_LINK_SI);
            String criteria = jsonScheduleItem.getString(JSON_CRITERIA);
            dataManager.addScheduleItem(type, date, time, title, topic, link, criteria);
        }
        
        JsonArray jsonTeamsArray = json.getJsonArray(JSON_TEAMS);
        for (int i = 0; i < jsonTeamsArray.size(); i++) {
            JsonObject jsonTeam = jsonTeamsArray.getJsonObject(i);
            String name = jsonTeam.getString(JSON_NAME_TEAM);
            String color = jsonTeam.getString(JSON_COLOR);
            String textColor = jsonTeam.getString(JSON_TEXT_COLOR);
            String link = jsonTeam.getString(JSON_LINK);
            dataManager.addTeam(name, color, textColor, link);
        }
        
        JsonArray jsonStudentsArray = json.getJsonArray(JSON_STUDENTS);
        for (int i = 0; i < jsonStudentsArray.size(); i++) {
            JsonObject jsonStudent = jsonStudentsArray.getJsonObject(i);
            String firstName = jsonStudent.getString(JSON_FIRST_NAME);
            String lastName = jsonStudent.getString(JSON_LAST_NAME);
            String team = jsonStudent.getString(JSON_TEAM);
            String role = jsonStudent.getString(JSON_ROLE);
            dataManager.addStudent(firstName, lastName, team, role);
        }
    }
    
    public void testLoadData(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;

	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
        
        JsonArray jsonCourseInfoArray = json.getJsonArray(JSON_COURSE_INFO);
        for(int i = 0; i < jsonCourseInfoArray.size(); i++) {
            JsonObject jsonCourseInfo = jsonCourseInfoArray.getJsonObject(i);
            String subject = jsonCourseInfo.getString(JSON_SUBJECT);
            dataManager.setSubject(subject);
            String number = jsonCourseInfo.getString(JSON_NUMBER);
            dataManager.setNumber(number);
            String semester = jsonCourseInfo.getString(JSON_SEMESTER);
            dataManager.setSemester(semester);
            String year = jsonCourseInfo.getString(JSON_YEAR);
            dataManager.setYear(year);
            String title = jsonCourseInfo.getString(JSON_TITLE_CI);
            dataManager.setTitle(title);
            String instructorName = jsonCourseInfo.getString(JSON_INSTRUCTOR_NAME);
            dataManager.setInstructorName(instructorName);
            String instructorHome = jsonCourseInfo.getString(JSON_INSTRUCTOR_HOME);
            dataManager.setInstructorHome(instructorHome);
            String exportDirPath = jsonCourseInfo.getString(JSON_EXPORT_DIR);
            dataManager.setExportDirPath(exportDirPath);
            String templateDirPath = jsonCourseInfo.getString(JSON_TEMPLATE_DIR);
            dataManager.setTemplateDirPath(templateDirPath);
        }
        
        JsonArray jsonSitePagesArray = json.getJsonArray(JSON_SITE_PAGES);
        for (int i = 0; i < jsonSitePagesArray.size(); i++) {
            JsonObject jsonSitePage = jsonSitePagesArray.getJsonObject(i);
            Boolean use = jsonSitePage.getBoolean(JSON_USE);
            String navbarTitle = jsonSitePage.getString(JSON_NAVBAR);
            String fileName = jsonSitePage.getString(JSON_FILE);
            String script = jsonSitePage.getString(JSON_SCRIPT);
            dataManager.addSitePage(use, navbarTitle, fileName, script);
        }
        
        JsonArray jsonPageStyleArray = json.getJsonArray(JSON_PAGE_STYLE);
        for(int i = 0; i < jsonPageStyleArray.size(); i++) {
            JsonObject jsonPageStyle = jsonPageStyleArray.getJsonObject(i);
            String bannerImgPath = jsonPageStyle.getString(JSON_BANNER_IMG);
            dataManager.setBannerImgPath(bannerImgPath);
            String leftFooterImgPath = jsonPageStyle.getString(JSON_LEFT_IMG);
            dataManager.setLeftFooterImgPath(leftFooterImgPath);
            String rightFooterImgPath = jsonPageStyle.getString(JSON_RIGHT_IMG);
            dataManager.setRightFooterImgPath(rightFooterImgPath);
            String stylesheet = jsonPageStyle.getString(JSON_STYLESHEET);
            dataManager.setStylesheet(stylesheet);
        }
        
	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);

        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            Boolean undergrad = jsonTA.getBoolean(JSON_UNDERGRAD);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            dataManager.addTA(undergrad, name, email);
        }

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            dataManager.addOfficeHoursReservation(day, time, name);
        }
        
        JsonArray jsonRecitationArray = json.getJsonArray(JSON_RECITATIONS);
        for (int i = 0; i < jsonRecitationArray.size(); i++) {
            JsonObject jsonRecitation = jsonRecitationArray.getJsonObject(i);
            String section = jsonRecitation.getString(JSON_SECTION);
            String instructor = jsonRecitation.getString(JSON_INSTRUCTOR);
            String day = jsonRecitation.getString(JSON_DAY);
            String location = jsonRecitation.getString(JSON_LOCATION);
            String taOne = jsonRecitation.getString(JSON_TA_ONE);
            String taTwo = jsonRecitation.getString(JSON_TA_TWO);
            dataManager.addRecitation(section, instructor, day, location, taOne, taTwo);
        }
        
        JsonArray jsonCalendarArray = json.getJsonArray(JSON_CALENDAR);
        for (int i = 0; i < jsonCalendarArray.size(); i++) {
            JsonObject jsonCalendar = jsonCalendarArray.getJsonObject(i);
            String startMonday = jsonCalendar.getString(JSON_START_MONDAY);
            String endFriday = jsonCalendar.getString(JSON_END_FRIDAY);
            dataManager.setStartMonday(LocalDate.parse(startMonday));
            dataManager.setEndFriday(LocalDate.parse(endFriday));
        }
        
        JsonArray jsonScheduleItemArray = json.getJsonArray(JSON_SCHEDULE);
        for (int i = 0; i < jsonScheduleItemArray.size(); i++) {
            JsonObject jsonScheduleItem = jsonScheduleItemArray.getJsonObject(i);
            String type = jsonScheduleItem.getString(JSON_TYPE);
            String date = jsonScheduleItem.getString(JSON_DATE);
            String time = jsonScheduleItem.getString(JSON_TIME_SI);
            String title = jsonScheduleItem.getString(JSON_TITLE);
            String topic = jsonScheduleItem.getString(JSON_TOPIC);
            String link = jsonScheduleItem.getString(JSON_LINK_SI);
            String criteria = jsonScheduleItem.getString(JSON_CRITERIA);
            dataManager.addScheduleItem(type, date, time, title, topic, link, criteria);
        }
        
        JsonArray jsonTeamsArray = json.getJsonArray(JSON_TEAMS);
        for (int i = 0; i < jsonTeamsArray.size(); i++) {
            JsonObject jsonTeam = jsonTeamsArray.getJsonObject(i);
            String name = jsonTeam.getString(JSON_NAME_TEAM);
            String color = jsonTeam.getString(JSON_COLOR);
            String textColor = jsonTeam.getString(JSON_TEXT_COLOR);
            String link = jsonTeam.getString(JSON_LINK);
            dataManager.addTeam(name, color, textColor, link);
        }
        
        JsonArray jsonStudentsArray = json.getJsonArray(JSON_STUDENTS);
        for (int i = 0; i < jsonStudentsArray.size(); i++) {
            JsonObject jsonStudent = jsonStudentsArray.getJsonObject(i);
            String firstName = jsonStudent.getString(JSON_FIRST_NAME);
            String lastName = jsonStudent.getString(JSON_LAST_NAME);
            String team = jsonStudent.getString(JSON_TEAM);
            String role = jsonStudent.getString(JSON_ROLE);
            dataManager.addStudent(firstName, lastName, team, role);
        }
    }

    @Override
    public void exportData(AppDataComponent data) throws IOException {
        CSGData dataC = (CSGData) data;
        String exportDirPath = dataC.getExportDirPath();
        String templateDirPath = dataC.getTemplateDirPath();
        File srcDir = new File(templateDirPath);
        File destDir = new File(exportDirPath);
        FileUtils.copyDirectory(srcDir,destDir);
        
        String cssFile = dataC.getStylesheet();
        File srcCss = new File(PATH_WORK + cssFile);
        File destCss = new File(destDir + "/css");
        FileUtils.copyFileToDirectory(srcCss, destCss);
        
        File srcBannerImg = new File(dataC.getBannerImgPath());
        File srcLeftFooterImg = new File(dataC.getLeftFooterImgPath());
        File srcRightFooterImg = new File(dataC.getRightFooterImgPath());
        File destImg = new File(destDir + "/images");
        FileUtils.copyFileToDirectory(srcBannerImg, destImg);
        FileUtils.copyFileToDirectory(srcLeftFooterImg, destImg);
        FileUtils.copyFileToDirectory(srcRightFooterImg, destImg);
        saveTAJson(data,destDir+"\\js\\OfficeHoursGridData.json");
        saveRecitationsJson(data,destDir+"\\js\\RecitationsData.json");
        saveScheduleJson(data,destDir+"\\js\\ScheduleData.json");
        saveTeamsStudentsJson(data,destDir+"\\js\\TeamsAndStudents.json");
        saveProjectsJson(data,destDir+"\\js\\ProjectsData.json");
    }
    
    @Override
    public void undoTrans(AppDataComponent data) {
        CSGController controller = new CSGController(app);
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        jTPS transactions = workspace.getjTPS();
        controller.handleUndo(transactions);
    }
    
    @Override
    public void redoTrans(AppDataComponent data) {
        CSGController controller = new CSGController(app);
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        jTPS transactions = workspace.getjTPS();
        controller.handleRedo(transactions);
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
}
