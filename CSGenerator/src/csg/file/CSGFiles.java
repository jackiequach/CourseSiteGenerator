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
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

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
    static final String JSON_SITE_TEMPLATE = "site_template";
    static final String JSON_TEMPLATE_DIR = "templateDir";
    static final String JSON_USE = "use";
    static final String JSON_NAVBAR = "navbar";
    static final String JSON_FILE = "file";
    static final String JSON_SCRIPT = "script";
    static final String JSON_PAGE_STYLE = "page_style";
    static final String JSON_BANNER_IMG = "bannerImg";
    static final String JSON_LEFT_IMG = "leftImg";
    static final String JSON_RIGHT_IMG = "rightImg";
    static final String JSON_STYLESHEET = "stylesheet";
    
    static final String JSON_RECITATION = "recitations";
    static final String JSON_SECTION = "section";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_DAY_REC = "dayRec";
    static final String JSON_LOCATION = "location";
    static final String JSON_TA_ONE = "taOne";
    static final String JSON_TA_TWO = "taTwo";
    
    static final String JSON_CALENDAR = "calendar_boundaries";
    static final String JSON_START_MONDAY = "startMonday";
    static final String JSON_END_FRIDAY = "endFriday";
    static final String JSON_SCHEDULE = "schedule_items";
    static final String JSON_TYPE = "type";
    static final String JSON_DATE = "date";
    static final String JSON_TIME_SI = "time";
    static final String JSON_TITLE = "title";
    static final String JSON_TOPIC = "topic";
    static final String JSON_LINK_SI = "link";
    static final String JSON_CRITERIA = "criteria";
    
    static final String JSON_TEAMS = "teams";
    static final String JSON_NAME_TEAM = "nameTeam";
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
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        
        JsonArrayBuilder courseInfoArrayBuilder = Json.createArrayBuilder();
        JsonObject courseInfo = Json.createObjectBuilder()
            .add(JSON_SUBJECT, dataManager.getSubject())
            .add(JSON_NUMBER, dataManager.getNumber())
            .add(JSON_SEMESTER, dataManager.getSemester())
            .add(JSON_YEAR, dataManager.getYear())
            .add(JSON_TITLE_CI, dataManager.getTitle())
            .add(JSON_INSTRUCTOR_NAME, dataManager.getInstructorName())
            .add(JSON_INSTRUCTOR_HOME, dataManager.getInstructorHome())
            .add(JSON_EXPORT_DIR, dataManager.getExportDirPath()).build();
        courseInfoArrayBuilder.add(courseInfo);
        JsonArray courseInfoArray = courseInfoArrayBuilder.build();
        
        JsonArrayBuilder siteTemplateArrayBuilder = Json.createArrayBuilder();
        JsonObject siteTemplate = Json.createObjectBuilder()
            .add(JSON_TEMPLATE_DIR, dataManager.getTemplateDirPath()).build();
        siteTemplateArrayBuilder.add(siteTemplate);
        
        JsonArrayBuilder sitePagesArrayBuilder = Json.createArrayBuilder();
        ObservableList<SitePage> sitePages = dataManager.getSitePages();
	for (SitePage sitePage : sitePages) {	    
	    JsonObject siteJson = Json.createObjectBuilder()
                .add(JSON_USE, sitePage.getUse())
                .add(JSON_NAVBAR, sitePage.getTitle())
                .add(JSON_FILE, sitePage.getFileName())
                .add(JSON_SCRIPT, sitePage.getScript()).build();
	    sitePagesArrayBuilder.add(siteJson);
	}
        siteTemplateArrayBuilder.add(sitePagesArrayBuilder);
        JsonArray siteTemplateArray = siteTemplateArrayBuilder.build();
        
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
	    JsonObject taJson = Json.createObjectBuilder()
                .add(JSON_NAME, ta.getName())
                .add(JSON_EMAIL, ta.getEmail()).build();
	    taArrayBuilder.add(taJson);
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
        JsonObject scheduleItemExtraJson = Json.createObjectBuilder()
            .add(JSON_TIME, dataManager.getTime())
            .add(JSON_LINK_SI, dataManager.getLink())
            .add(JSON_CRITERIA, dataManager.getCriteria()).build();
        scheduleArrayBuilder.add(scheduleItemExtraJson);
        ObservableList<ScheduleItem> scheduleItems = dataManager.getScheduleItems();
        for (ScheduleItem scheduleItem : scheduleItems) {	    
	    JsonObject scheduleItemJson = Json.createObjectBuilder()
                .add(JSON_TYPE, scheduleItem.getType())
                .add(JSON_DATE, scheduleItem.getDate())
                .add(JSON_TITLE, scheduleItem.getTitle())
                .add(JSON_TOPIC, scheduleItem.getTopic()).build();
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
            .add(JSON_SITE_TEMPLATE, siteTemplateArray)
            .add(JSON_PAGE_STYLE, pageStyleArray)
            .add(JSON_START_HOUR, "" + dataManager.getStartHour())
            .add(JSON_END_HOUR, "" + dataManager.getEndHour())
            .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
            .add(JSON_OFFICE_HOURS, timeSlotsArray)
            .add(JSON_RECITATION, recitationsArray)
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

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;

	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);

	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
        dataManager.initHours(startHour, endHour);

        // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
        app.getWorkspaceComponent().reloadWorkspace(app.getDataComponent());

        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            dataManager.addTA(name, email);
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
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
