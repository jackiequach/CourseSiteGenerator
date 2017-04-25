/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.file.CSGFiles;
import static djf.settings.AppPropertyType.WORK_FILE_EXT;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import static djf.settings.AppStartupConstants.PATH_WORK;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Jackie Quach
 */
public class TestSave extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            CSGeneratorApp app = new CSGeneratorApp();
            app.loadProperties(APP_PROPERTIES_FILE_NAME);
            CSGData data = new CSGData(app);
            data.addTA(true, "John Doe", "john.doe@gmail.com");
            data.addTA(false, "Jane Doe", "jane.doe@gmail.com");
            data.addSitePage(true, "HWs", "index.html", "HomeBuilder.js");
            data.addRecitation("R01", "Mckenna", "Wed 3:30pm-4:23pm", "Old CS 2114", "Jane Doe", "Joe Shmo");
            data.addScheduleItem("Holiday", "02/03/2017", "9:00am", "SNOW DAY", "topic", "this is a link", "this is criteria");
            data.addTeam("C4 Comics", "235399", "ffffff", "thisalink");
            data.addStudent("Jane", "Doe", "C4 Comics", "Lead Programmer");
            data.setSubject("CSE");
            data.setSemester("Fall");
            data.setNumber("219");
            data.setYear("2017");
            data.setTitle("Computer Science III");
            data.setInstructorName("Richard Mckenna");
            data.setInstructorHome("http://http://www3.cs.stonybrook.edu/~richard/");
            data.setExportDirPath("..\\Courses\\CSE219\\Fall2017\\public");
            data.setTemplateDirPath(".\\templates\\base");
            data.setStylesheet("sea_wolf.css");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
            data.setStartMonday(LocalDate.parse("04/22/2012", formatter));
            data.setEndFriday(LocalDate.parse("05/22/2012", formatter));

            ArrayList<String> gridHeaders = data.getGridHeaders();
            HashMap<String, Pane> officeHoursGridTimeHeaderPanes = new HashMap();
            HashMap<String, Label> officeHoursGridTimeHeaderLabels = new HashMap();
            HashMap<String, Pane> officeHoursGridDayHeaderPanes = new HashMap();
            HashMap<String, Label> officeHoursGridDayHeaderLabels = new HashMap();
            HashMap<String, Pane> officeHoursGridTimeCellPanes = new HashMap();
            HashMap<String, Label> officeHoursGridTimeCellLabels = new HashMap();
            HashMap<String, Pane> officeHoursGridTACellPanes = new HashMap();
            HashMap<String, Label> officeHoursGridTACellLabels = new HashMap();
            // ADD THE TIME HEADERS
            for (int i = 0; i < 2; i++) {
                addCellToGrid(data, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
                data.getCellTextProperty(i, 0).set(gridHeaders.get(i));
            }

            // THEN THE DAY OF WEEK HEADERS
            for (int i = 2; i < 7; i++) {
                addCellToGrid(data, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
                data.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
            }

            // THEN THE TIME AND TA CELLS
            int row = 1;
            for (int i = data.getStartHour(); i < data.getEndHour(); i++) {
                // START TIME COLUMN
                int col = 0;
                addCellToGrid(data, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
                data.getCellTextProperty(col, row).set(buildCellText(i, "00"));
                addCellToGrid(data, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
                data.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

                // END TIME COLUMN
                col++;
                int endHour = i;
                addCellToGrid(data, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
                data.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
                addCellToGrid(data, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
                data.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
                col++;

                // AND NOW ALL THE TA TOGGLE CELLS
                while (col < 7) {
                    addCellToGrid(data, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                    addCellToGrid(data, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);
                    col++;
                }
                row += 2;
            }
            data.toggleTAOfficeHours("4_4", "John Doe");
            CSGFiles file = new CSGFiles(app);
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String path = PATH_WORK + "SiteSaveTest" + props.getProperty(WORK_FILE_EXT);
            file.saveData(data, path);
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    public void addCellToGrid(CSGData dataComponent, HashMap<String, Pane> panes, HashMap<String, Label> labels, int col, int row) {       
        // MAKE THE LABEL IN A PANE
        Label cellLabel = new Label("");
        HBox cellPane = new HBox();
        cellPane.setAlignment(Pos.CENTER);
        cellPane.getChildren().add(cellLabel);

        // BUILD A KEY TO EASILY UNIQUELY IDENTIFY THE CELL
        String cellKey = dataComponent.getCellKey(col, row);
        cellPane.setId(cellKey);
        cellLabel.setId(cellKey);
        
        // AND ALSO KEEP IN IN CASE WE NEED TO STYLIZE IT
        panes.put(cellKey, cellPane);
        labels.put(cellKey, cellLabel);
        
        // AND FINALLY, GIVE THE TEXT PROPERTY TO THE DATA MANAGER
        // SO IT CAN MANAGE ALL CHANGES
        dataComponent.setCellProperty(col, row, cellLabel.textProperty());        
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
}
