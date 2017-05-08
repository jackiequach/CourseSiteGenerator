package test_bed;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.SitePage;
import csg.data.Student;
import csg.data.Team;
import csg.file.CSGFiles;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Jackie Quach
 */
public class JsonJUnitTest {
    CSGeneratorApp app = new CSGeneratorApp();
    
    public JsonJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        try {
            app.loadProperties(APP_PROPERTIES_FILE_NAME);
            app.buildAppComponentsHookTest();
            CSGFiles file = (CSGFiles)app.getFileComponent();
            CSGData data = (CSGData)app.getDataComponent();
            ArrayList<String> gridHeaders = data.getGridHeaders();
            // ADD THE TIME HEADERS
            for (int i = 0; i < 2; i++) {
                data.setCellProperty(i, 0, new SimpleStringProperty(""));
                data.getCellTextProperty(i, 0).set(gridHeaders.get(i));
            }

            // THEN THE DAY OF WEEK HEADERS
            for (int i = 2; i < 7; i++) {
                data.setCellProperty(i, 0, new SimpleStringProperty(""));
                data.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
            }

            // THEN THE TIME AND TA CELLS
            int row = 1;
            for (int i = data.getStartHour(); i < data.getEndHour(); i++) {
                // START TIME COLUMN
                int col = 0;
                data.setCellProperty(col, row, new SimpleStringProperty(""));
                data.getCellTextProperty(col, row).set(buildCellText(i, "00"));
                data.setCellProperty(col, row+1, new SimpleStringProperty(""));
                data.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

                // END TIME COLUMN
                col++;
                int endHour = i;
                data.setCellProperty(col, row, new SimpleStringProperty(""));
                data.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
                data.setCellProperty(col, row+1, new SimpleStringProperty(""));
                data.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
                col++;

                // AND NOW ALL THE TA TOGGLE CELLS
                while (col < 7) {
                    data.setCellProperty(col, row, new SimpleStringProperty(""));
                    data.setCellProperty(col, row+1, new SimpleStringProperty(""));
                    col++;
                }
                row += 2;
            }
            file.testLoadData(data, "./work/SiteSaveTest.json");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public String buildCellText(int militaryHour, String minutes) {
        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutes;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
    @Test
    public void subjectCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("CSE", data.getSubject());
    }
    
    @Test
    public void numberCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("219", data.getNumber());
    }
    
    @Test
    public void semesterCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("Fall", data.getSemester());
    }
    
    @Test
    public void yearCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("2017", data.getYear());
    }
    
    @Test
    public void titleCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("Computer Science III", data.getTitle());
    }
    
    @Test
    public void exportDirPathCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("..\\Courses\\CSE219\\Fall2017\\public", data.getExportDirPath());
    }
    
    @Test
    public void templateDirPathCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals(".\\templates\\CSE219", data.getTemplateDirPath());
    }
    
    @Test
    public void bannerImgPathCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("file:./images/SBUDarkRedShieldLogo.png", data.getBannerImgPath());
    }
    
    @Test
    public void leftFooterImgPathCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("file:./images/SBUWhiteShieldLogo.jpg", data.getLeftFooterImgPath());
    }
    
    @Test
    public void rightFooterImgPathCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("file:./images/CSLogo.png", data.getRightFooterImgPath());
    }
    
    @Test
    public void sitePagesCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        ObservableList<SitePage> sitePages = data.getSitePages();
        for(SitePage sitePage : sitePages) {
            assertEquals(new Boolean(true), sitePage.getUse());
            assertEquals("HWs",sitePage.getNavbar());
            assertEquals("index.html", sitePage.getFile());
            assertEquals("HomeBuilder.js", sitePage.getScript());
        }
    }
    
    @Test
    public void recitationCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        ObservableList<Recitation> recitations = data.getRecitations();
        for(Recitation recitation : recitations) {
            assertEquals("R01", recitation.getSection());
            assertEquals("Mckenna",recitation.getInstructor());
            assertEquals("Wed 3:30pm-4:23pm", recitation.getDay());
            assertEquals("Old CS 2114", recitation.getLocation());
            assertEquals("Jane Doe", recitation.getTAOne());
            assertEquals("Joe Shmo", recitation.getTATwo());
        }
    }
    
    @Test
    public void startMondayCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("2012-04-22", data.getStartMonday());
    }
    
    @Test
    public void endFridayCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        assertEquals("2012-05-22", data.getEndFriday());
    }
    
    @Test
    public void scheduleItemCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        ObservableList<ScheduleItem> scheduleItems = data.getScheduleItems();
        for(ScheduleItem scheduleItem : scheduleItems) {
            assertEquals("Holiday", scheduleItem.getType());
            assertEquals("02/03/2017",scheduleItem.getDate());
            assertEquals("9:00am", scheduleItem.getTime());
            assertEquals("SNOW DAY", scheduleItem.getTitle());
            assertEquals("topic", scheduleItem.getTopic());
            assertEquals("this is a link", scheduleItem.getLink());
            assertEquals("this is criteria", scheduleItem.getCriteria());
        }
    }
    
    @Test
    public void teamCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        ObservableList<Team> teams = data.getTeams();
        for(Team team : teams) {
            assertEquals("C4 Comics", team.getName());
            assertEquals("235399",team.getColor());
            assertEquals("ffffff", team.getTextColor());
            assertEquals("thisalink", team.getLink());
        }
    }
    
    @Test
    public void studentCheck() {
        CSGData data = (CSGData)app.getDataComponent();
        ObservableList<Student> students = data.getStudents();
        for(Student student : students) {
            assertEquals("Jane", student.getFirstName());
            assertEquals("Doe",student.getLastName());
            assertEquals("C4 Comics", student.getTeam());
            assertEquals("Lead Programmer", student.getRole());
        }
    }
    
    
    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
