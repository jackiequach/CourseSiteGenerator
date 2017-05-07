/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGProperty;
import static csg.CSGProperty.ADD_RECITATION_BUTTON_TEXT;
import static csg.CSGProperty.ADD_SCHEDULE_BUTTON_TEXT;
import static csg.CSGProperty.ADD_TEAM_BUTTON_TEXT;
import static csg.CSGProperty.INVALID_TA_EMAIL_MESSAGE;
import static csg.CSGProperty.INVALID_TA_EMAIL_TITLE;
import static csg.CSGProperty.MISSING_RECITATION_DAY_MESSAGE;
import static csg.CSGProperty.MISSING_RECITATION_DAY_TITLE;
import static csg.CSGProperty.MISSING_RECITATION_INSTRUCTOR_MESSAGE;
import static csg.CSGProperty.MISSING_RECITATION_INSTRUCTOR_TITLE;
import static csg.CSGProperty.MISSING_RECITATION_LOCATION_MESSAGE;
import static csg.CSGProperty.MISSING_RECITATION_LOCATION_TITLE;
import static csg.CSGProperty.MISSING_RECITATION_SECTION_MESSAGE;
import static csg.CSGProperty.MISSING_RECITATION_SECTION_TITLE;
import static csg.CSGProperty.MISSING_SCHEDULE_CRITERIA_MESSAGE;
import static csg.CSGProperty.MISSING_SCHEDULE_CRITERIA_TITLE;
import static csg.CSGProperty.MISSING_SCHEDULE_DATE_MESSAGE;
import static csg.CSGProperty.MISSING_SCHEDULE_DATE_TITLE;
import static csg.CSGProperty.MISSING_SCHEDULE_LINK_MESSAGE;
import static csg.CSGProperty.MISSING_SCHEDULE_LINK_TITLE;
import static csg.CSGProperty.MISSING_SCHEDULE_TIME_MESSAGE;
import static csg.CSGProperty.MISSING_SCHEDULE_TIME_TITLE;
import static csg.CSGProperty.MISSING_SCHEDULE_TITLE_MESSAGE;
import static csg.CSGProperty.MISSING_SCHEDULE_TITLE_TITLE;
import static csg.CSGProperty.MISSING_SCHEDULE_TOPIC_MESSAGE;
import static csg.CSGProperty.MISSING_SCHEDULE_TOPIC_TITLE;
import static csg.CSGProperty.MISSING_SCHEDULE_TYPE_MESSAGE;
import static csg.CSGProperty.MISSING_SCHEDULE_TYPE_TITLE;
import static csg.CSGProperty.MISSING_TA_EMAIL_MESSAGE;
import static csg.CSGProperty.MISSING_TA_EMAIL_TITLE;
import static csg.CSGProperty.MISSING_TA_NAME_MESSAGE;
import static csg.CSGProperty.MISSING_TA_NAME_TITLE;
import static csg.CSGProperty.MISSING_TEAM_COLOR_MESSAGE;
import static csg.CSGProperty.MISSING_TEAM_COLOR_TITLE;
import static csg.CSGProperty.MISSING_TEAM_LINK_MESSAGE;
import static csg.CSGProperty.MISSING_TEAM_LINK_TITLE;
import static csg.CSGProperty.MISSING_TEAM_NAME_MESSAGE;
import static csg.CSGProperty.MISSING_TEAM_NAME_TITLE;
import static csg.CSGProperty.MISSING_TEAM_TEXT_COLOR_MESSAGE;
import static csg.CSGProperty.MISSING_TEAM_TEXT_COLOR_TITLE;
import static csg.CSGProperty.RECITATION_SECTION_NOT_UNIQUE_MESSAGE;
import static csg.CSGProperty.RECITATION_SECTION_NOT_UNIQUE_TITLE;
import static csg.CSGProperty.TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE;
import static csg.CSGProperty.TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE;
import static csg.CSGProperty.TEAM_NAME_AND_COLOR_NOT_UNIQUE_MESSAGE;
import static csg.CSGProperty.TEAM_NAME_AND_COLOR_NOT_UNIQUE_TITLE;
import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.SitePage;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.file.TimeSlot;
import csg.jtps.AddRecTrans;
import csg.jtps.AddSITrans;
import csg.jtps.AddTeamTrans;
import csg.jtps.AddTrans;
import csg.jtps.DeleteRecTrans;
import csg.jtps.DeleteSITrans;
import csg.jtps.DeleteTeamTrans;
import csg.jtps.DeleteTrans;
import csg.jtps.EndFridayTrans;
import csg.jtps.EndTimeTrans;
import csg.jtps.StartMondayTrans;
import csg.jtps.StartTimeTrans;
import csg.jtps.ToggleTrans;
import csg.jtps.UpdateRecTrans;
import csg.jtps.UpdateSITrans;
import csg.jtps.UpdateTeamTrans;
import csg.jtps.UpdateTrans;
import static djf.settings.AppPropertyType.EXPORT_DIR_TITLE;
import static djf.settings.AppPropertyType.INVALID_END_FRIDAY_FRIDAY_MESSAGE;
import static djf.settings.AppPropertyType.INVALID_END_FRIDAY_FRIDAY_TITLE;
import static djf.settings.AppPropertyType.INVALID_END_FRIDAY_MESSAGE;
import static djf.settings.AppPropertyType.INVALID_END_FRIDAY_TITLE;
import static djf.settings.AppPropertyType.INVALID_END_TIME_MESSAGE;
import static djf.settings.AppPropertyType.INVALID_END_TIME_TITLE;
import static djf.settings.AppPropertyType.INVALID_START_MONDAY_MESSAGE;
import static djf.settings.AppPropertyType.INVALID_START_MONDAY_MONDAY_MESSAGE;
import static djf.settings.AppPropertyType.INVALID_START_MONDAY_MONDAY_TITLE;
import static djf.settings.AppPropertyType.INVALID_START_MONDAY_TITLE;
import static djf.settings.AppPropertyType.INVALID_START_TIME_MESSAGE;
import static djf.settings.AppPropertyType.INVALID_START_TIME_TITLE;
import static djf.settings.AppStartupConstants.PATH_EMPTY;
import static djf.settings.AppStartupConstants.PATH_TEMPLATES;
import djf.ui.AppMessageDialogSingleton;
import java.io.File;
import java.io.FilenameFilter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import jtps.jTPS;
import properties_manager.PropertiesManager;

/**
 *
 * @author Jackie Quach
 */
public class CSGController {
    CSGeneratorApp app;

    /**
     * Constructor, note that the app must already be constructed.
     */
    public CSGController(CSGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
    }
    
    public void handleAddTA() {
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TextField nameTextField = workspace.getNameTextField();
        String name = nameTextField.getText();
        TextField emailTextField = workspace.getEmailTextField();
        String email = emailTextField.getText();
        EmailValidator emailValidator = new EmailValidator();
        jTPS transactions = workspace.getjTPS();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSGData data = (CSGData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // DID THE USER NEGLECT TO PROVIDE A TA NAME?
        if (name.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));            
        }
        // IS THE EMAIL VALID?
        else if (!emailValidator.validate(email)) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(INVALID_TA_EMAIL_TITLE), props.getProperty(INVALID_TA_EMAIL_MESSAGE));
        }
        // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
        else if (email.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));
        }
        // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
        else if (data.containsTA(name) || data.containsEmail(email)) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
        }
        // EVERYTHING IS FINE, ADD A NEW TA
        else {
            // ADD THE NEW TA TO THE DATA
            AddTrans add = new AddTrans(app,false,name,email);
            transactions.addTransaction(add);
            
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            emailTextField.setText("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            
            app.getGUI().getAppFileController().markAsDone(app.getGUI());
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        }
    }

    /**
     * This function provides a response for when the user clicks
     * on the office hours grid to add or remove a TA to a time slot.
     * 
     * @param pane The pane that was toggled.
     */
    public void handleCellToggle(Pane pane) {
        // GET THE TABLE
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();
        jTPS transactions = workspace.getjTPS();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
        // GET THE TA
        TeachingAssistant ta = (TeachingAssistant)selectedItem;
        String taName = ta.getName();
        CSGData data = (CSGData)app.getDataComponent();
        String cellKey = pane.getId();
        HashMap<String, StringProperty> officeHours = data.getOfficeHours();
        StringProperty cellProp = officeHours.get(cellKey);
        String cellText = cellProp.getValue();
        ToggleTrans toggle = new ToggleTrans(app,cellKey,cellProp,cellText,taName);
        
        // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
        transactions.addTransaction(toggle);
        app.getGUI().getAppFileController().markAsDone(app.getGUI());
        app.getGUI().getAppFileController().markAsEdited(app.getGUI());
    }
    
    public void handleDeleteTA(KeyCode key)
    {
        // GET THE TABLE
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
        // GET THE TA
        TeachingAssistant ta = (TeachingAssistant)selectedItem;
        jTPS transaction = workspace.getjTPS();
        ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList((CSGData)app.getDataComponent());
        HashMap<String, StringProperty> officeHoursH = ((CSGData)app.getDataComponent()).getOfficeHours();
        DeleteTrans delete = new DeleteTrans(app,ta,officeHours,officeHoursH);
        
        if(key == KeyCode.DELETE) {
            transaction.addTransaction(delete);
            workspace.getNameTextField().setText("");
            workspace.getEmailTextField().setText("");
            workspace.getAddButton().setText("Add TA");
            workspace.getAddButton().setOnAction(ee -> {
                handleAddTA();
            });
        }
        app.getGUI().getAppFileController().markAsDone(app.getGUI());
        app.getGUI().getAppFileController().markAsEdited(app.getGUI());
    }
    
    public void handleHover(Pane p, String selColor, String colRowColor, String bordWidth)
    {
        p.setStyle("-fx-border-color:" + selColor + ";-fx-border-width:" + bordWidth);
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        HashMap<String, Pane> panes = workspace.getOfficeHoursGridTACellPanes();
        String selected = workspace.getCellKey(p);
        int selectC = Integer.parseInt(selected.split("_")[0]);
        int selectR = Integer.parseInt(selected.split("_")[1]);
        int cCount = selectC;
        int rCount = selectR;
        for(String k : panes.keySet())
        {
            int c = Integer.parseInt(k.split("_")[0]);
            int r = Integer.parseInt(k.split("_")[1]);
            if(selectR > 0 && r < rCount && cCount == c) {
                panes.get(k).setStyle("-fx-border-color:" + colRowColor + ";-fx-border-width:" + bordWidth);
                selectR--;
            }
            if(selectC > 0 && c < cCount && rCount == r) {
               panes.get(k).setStyle("-fx-border-color:" + colRowColor + ";-fx-border-width:" + bordWidth);
               selectC--;
            }
        }
    }
    
    public void handleSelectTA() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Button addButton = workspace.getAddButton();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null) {
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            nameTextField.setText(ta.getName());
            emailTextField.setText(ta.getEmail());
            addButton.setText(props.getProperty(CSGProperty.UPDATE_BUTTON_TEXT.toString()));
            nameTextField.setOnAction(e -> {
                handleUpdateTA(selectedItem);
            });
            emailTextField.setOnAction(e -> {
                handleUpdateTA(selectedItem);
            });
            addButton.setOnAction(e -> {
                handleUpdateTA(selectedItem);
            });
        }
    }
    
    public void handleUpdateTA(Object selectedItem) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        EmailValidator emailValidator = new EmailValidator();
        TeachingAssistant ta = (TeachingAssistant)selectedItem;
            
        jTPS transactions = workspace.getjTPS();
        UpdateTrans update = new UpdateTrans(app, emailValidator, ta.getName(), ta.getEmail(), ta);
        transactions.addTransaction(update);
    }
    
    public void handleClear() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();
        Button addButton = workspace.getAddButton();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        
        taTable.getSelectionModel().clearSelection();
        
        nameTextField.setText("");
        emailTextField.setText("");

        nameTextField.requestFocus();

        addButton.setText("Add TA");
        addButton.setOnAction(ee -> {
            handleAddTA();
        });
    }
    
    public void handleStart() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        ComboBox startTime = workspace.getStartTimeComboBox();
        CSGData data = (CSGData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String selectedTime = (String) startTime.getSelectionModel().getSelectedItem();
        ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(data);
        HashMap<String, StringProperty> officeHoursP = data.getOfficeHours();
        
        int startHour = data.getStartHour();
        int endHour = data.getEndHour();
        int chosenHour = Integer.parseInt(selectedTime.split(":")[0]);
        if(selectedTime.contains("pm") && !selectedTime.contains("12"))
            chosenHour = Integer.parseInt(selectedTime.split(":")[0]) + 12;
        else if(selectedTime.equals("12:00am"))
            chosenHour = 0;
        if(chosenHour >= endHour) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_START_TIME_TITLE), props.getProperty(INVALID_START_TIME_MESSAGE));
        }
        else {
            jTPS transactions = workspace.getjTPS();
            StartTimeTrans start = new StartTimeTrans(app, chosenHour, startHour, officeHours, selectedTime, officeHoursP);
            transactions.addTransaction(start);
        }
    }
    
    public void handleEnd() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        ComboBox endTime = workspace.getEndTimeComboBox();
        CSGData data = (CSGData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String selectedTime = (String) endTime.getSelectionModel().getSelectedItem();
        ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(data);
        HashMap<String, StringProperty> officeHoursP = data.getOfficeHours();
        
        int startHour = data.getStartHour();
        int endHour = data.getEndHour();
        int chosenHour = Integer.parseInt(selectedTime.split(":")[0]);
        if(selectedTime.contains("pm") && !selectedTime.contains("12"))
            chosenHour = Integer.parseInt(selectedTime.split(":")[0]) + 12;
        else if(selectedTime.equals("12:00am"))
            chosenHour = 0;
        if(chosenHour <= startHour) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_END_TIME_TITLE), props.getProperty(INVALID_END_TIME_MESSAGE));
        }
        else {
            jTPS transactions = workspace.getjTPS();
            EndTimeTrans end = new EndTimeTrans(app, chosenHour, endHour, officeHours, selectedTime, officeHoursP);
            transactions.addTransaction(end);
        }
    }
    
    public void handleKeyPress(KeyEvent key) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        jTPS transactions = workspace.getjTPS();
        if(key.getCode() == KeyCode.Z && key.isControlDown()) {
            handleUndo(transactions);
        }
        if(key.getCode() == KeyCode.Y && key.isControlDown()) {
            handleRedo(transactions);
        }
    }
    
    public void handleUndo(jTPS transactions) {
        transactions.undoTransaction();
        if(transactions.getMostRecentTransaction() < 0) {
            app.getGUI().getAppFileController().markAsNotEdited(app.getGUI());
            app.getGUI().getAppFileController().markAsFullyUndid(app.getGUI());
        }
        else
            app.getGUI().getAppFileController().markAsUndid(app.getGUI());
    }
    
    public void handleRedo(jTPS transactions) {
        transactions.doTransaction();
        app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        if(transactions.transSize()-1 > transactions.getMostRecentTransaction())
            app.getGUI().getAppFileController().markAsDone(app.getGUI());
        else {
            app.getGUI().getAppFileController().markAsFullyDone(app.getGUI());
        }
    }
    
    public void handleCourseInfo() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        ComboBox subjectComboBox = workspace.getSubjectComboBox();
        ComboBox numberComboBox = workspace.getNumberComboBox();
        ComboBox semesterComboBox = workspace.getSemesterComboBox();
        ComboBox yearComboBox = workspace.getYearComboBox();
        TextField titleTextField = workspace.getTitleTextField();
        TextField instructorNameTextField = workspace.getInstructorNameTextField();
        TextField instructorHomeTextField = workspace.getInstructorHomeTextField();
        
        CSGData data = (CSGData)app.getDataComponent();
        
        data.setSubject((String)subjectComboBox.getValue());
        data.setNumber((String)numberComboBox.getValue());
        data.setSemester((String)semesterComboBox.getValue());
        data.setYear((String)yearComboBox.getValue());
        data.setTitle(titleTextField.getText());
        data.setInstructorName(instructorNameTextField.getText());
        data.setInstructorHome(instructorHomeTextField.getText());
    }
    
    public void handleChangeExportDir() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData data = (CSGData)app.getDataComponent();
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(PATH_EMPTY));
        dc.setTitle(props.getProperty(EXPORT_DIR_TITLE));

        File directory = dc.showDialog(app.getGUI().getWindow());
        if (directory != null) {
            data.setExportDirPath(directory.getPath());
        }
    }
    
    public void handleSelectTemplate() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData data = (CSGData)app.getDataComponent();
        ObservableList<SitePage> sitePages = data.getSitePages();
        
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(PATH_TEMPLATES));
        dc.setTitle(props.getProperty(EXPORT_DIR_TITLE));

        File directory = dc.showDialog(app.getGUI().getWindow());
        if (directory != null) {
            data.setTemplateDirPath(directory.getPath());
            File[] directoryListing = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".html");
                }
            });
            if(directoryListing != null) {
                for(File htmlFile : directoryListing) {
                    if(htmlFile.getName().equals("index.html")) {
                        sitePages.add(new SitePage(true, "Home", htmlFile.getName(), "HomeBuilder.js"));
                    }
                    if(htmlFile.getName().equals("syllabus.html")) {
                        sitePages.add(new SitePage(true, "Syllabus", htmlFile.getName(), "SyllabusBuilder.js"));
                    }
                    if(htmlFile.getName().equals("schedule.html")) {
                        sitePages.add(new SitePage(true, "Schedule", htmlFile.getName(), "ScheduleBuilder.js"));
                    }
                    if(htmlFile.getName().equals("hws.html")) {
                        sitePages.add(new SitePage(true, "HWs", htmlFile.getName(), "HWsBuilder.js"));
                    }
                    if(htmlFile.getName().equals("projects.html")) {
                        sitePages.add(new SitePage(true, "Projects", htmlFile.getName(), "ProjectsBuilder.js"));
                    }
                }
            }
        }
    }
    
    public void handleAddRecitation() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TextField sectionTextField = workspace.getSectionTextField();
        String section = sectionTextField.getText();
        TextField instructorTextField = workspace.getInstructorTextField();
        String instructor = instructorTextField.getText();
        TextField dayTextField = workspace.getDayTextField();
        String day = dayTextField.getText();
        TextField locationTextField = workspace.getLocationTextField();
        String location = locationTextField.getText();
        ComboBox taOneComboBox = workspace.getSupervisingTAComboBoxOne();
        String taOne = "";
        String taTwo = "";
        if(taOneComboBox.getValue() != null) {
            taOne = ((TeachingAssistant)taOneComboBox.getValue()).getName();
        }
        ComboBox taTwoComboBox = workspace.getSupervisingTAComboBoxTwo();
        if(taTwoComboBox.getValue() != null) {
            taTwo = ((TeachingAssistant)taTwoComboBox.getValue()).getName();
        }
        jTPS transactions = workspace.getjTPS();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSGData data = (CSGData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if (section.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_SECTION_TITLE), props.getProperty(MISSING_RECITATION_SECTION_MESSAGE));            
        }
        else if (instructor.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_INSTRUCTOR_TITLE), props.getProperty(MISSING_RECITATION_INSTRUCTOR_MESSAGE));
        }
        else if (day.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_DAY_TITLE), props.getProperty(MISSING_RECITATION_DAY_MESSAGE));
        }
        else if(location.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_LOCATION_TITLE), props.getProperty(MISSING_RECITATION_LOCATION_MESSAGE));
        }
        else if (data.containsRecitation(section)) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(RECITATION_SECTION_NOT_UNIQUE_TITLE), props.getProperty(RECITATION_SECTION_NOT_UNIQUE_MESSAGE));                                    
        }
        else {
            AddRecTrans add = new AddRecTrans(app,section,instructor,day,location,taOne,taTwo);
            transactions.addTransaction(add);
            
            // CLEAR THE TEXT FIELDS
            sectionTextField.setText("");
            instructorTextField.setText("");
            dayTextField.setText("");
            locationTextField.setText("");
            taOneComboBox.setValue(null);
            taOneComboBox.setValue(null);
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            sectionTextField.requestFocus();
            
            app.getGUI().getAppFileController().markAsDone(app.getGUI());
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        }
    }
    
    public void handleDeleteRecitation(KeyCode key) {
        // GET THE TABLE
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        TableView recitationTable = workspace.getRecitationTable();
        
        // IS A RECITATION SELECTED IN THE TABLE?
        Object selectedItem = recitationTable.getSelectionModel().getSelectedItem();
        
        // GET THE RECITATION
        Recitation recitation = (Recitation)selectedItem;
        jTPS transaction = workspace.getjTPS();
        DeleteRecTrans delete = new DeleteRecTrans(app,recitation);
        
        if(key == KeyCode.DELETE) {
            transaction.addTransaction(delete);
            workspace.getSectionTextField().setText("");
            workspace.getInstructorTextField().setText("");
            workspace.getDayTextField().setText("");
            workspace.getLocationTextField().setText("");
            workspace.getSupervisingTAComboBoxOne().setValue(null);
            workspace.getSupervisingTAComboBoxTwo().setValue(null);
            workspace.getAddRecitationButton().setText(props.getProperty(ADD_RECITATION_BUTTON_TEXT));
            workspace.getAddRecitationButton().setOnAction(ee -> {
                handleAddRecitation();
            });
        }
        app.getGUI().getAppFileController().markAsDone(app.getGUI());
        app.getGUI().getAppFileController().markAsEdited(app.getGUI());
    }
    
    public void handleClearRecitation() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        TableView recitationTable = workspace.getRecitationTable();
        Button addButton = workspace.getAddRecitationButton();
        TextField sectionTextField = workspace.getSectionTextField();
        TextField instructorTextField = workspace.getInstructorTextField();
        TextField dayTextField = workspace.getDayTextField();
        TextField locationTextField = workspace.getLocationTextField();
        ComboBox taOneComboBox = workspace.getSupervisingTAComboBoxOne();
        ComboBox taTwoComboBox = workspace.getSupervisingTAComboBoxTwo();
        
        recitationTable.getSelectionModel().clearSelection();
        
        sectionTextField.setText("");
        instructorTextField.setText("");
        dayTextField.setText("");
        locationTextField.setText("");
        taOneComboBox.setValue(null);
        taTwoComboBox.setValue(null);

        sectionTextField.requestFocus();

        addButton.setText(props.getProperty(ADD_RECITATION_BUTTON_TEXT));
        addButton.setOnAction(ee -> {
            handleAddRecitation();
        });
    }
    
    public void handleSelectRecitation() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TableView recitationTable = workspace.getRecitationTable();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Button addButton = workspace.getAddRecitationButton();
        TextField sectionTextField = workspace.getSectionTextField();
        TextField instructorTextField = workspace.getInstructorTextField();
        TextField dayTextField = workspace.getDayTextField();
        TextField locationTextField = workspace.getLocationTextField();
        ComboBox taOneComboBox = workspace.getSupervisingTAComboBoxOne();
        ComboBox taTwoComboBox = workspace.getSupervisingTAComboBoxTwo();
        Object selectedItem = recitationTable.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null) {
            Recitation recitation = (Recitation)selectedItem;
            sectionTextField.setText(recitation.getSection());
            instructorTextField.setText(recitation.getInstructor());
            dayTextField.setText(recitation.getDay());
            locationTextField.setText(recitation.getLocation());
            taOneComboBox.setValue(recitation.getTAOne());
            taTwoComboBox.setValue(recitation.getTATwo());
            addButton.setText(props.getProperty(CSGProperty.UPDATE_RECITATION_BUTTON_TEXT.toString()));
            addButton.setOnAction(e -> {
                handleUpdateRecitation(selectedItem);
            });
        }
    }

    public void handleUpdateRecitation(Object selectedItem) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        Recitation recitation = (Recitation)selectedItem;
            
        jTPS transactions = workspace.getjTPS();
        UpdateRecTrans update = new UpdateRecTrans(app, recitation.getSection(), recitation.getInstructor(), recitation.getDay(), recitation.getLocation(), recitation.getTAOne(), recitation.getTATwo(), recitation);
        transactions.addTransaction(update);
    }
    
    public void handleStartingMonday() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        DatePicker startingMondayPicker = workspace.getStartingMondayPicker();
        CSGData data = (CSGData)app.getDataComponent();
        LocalDate selectedDate = startingMondayPicker.getValue();
        LocalDate startMonday = null;
        if(data.getStartMonday() != null)
            startMonday = LocalDate.parse(data.getStartMonday());
        LocalDate endFriday = null;
        if(data.getEndFriday() != null)
            endFriday = LocalDate.parse(data.getEndFriday());
        if(selectedDate != null && !selectedDate.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_START_MONDAY_MONDAY_TITLE), props.getProperty(INVALID_START_MONDAY_MONDAY_MESSAGE));
            startingMondayPicker.setValue(startMonday);
        }
        else if(endFriday != null && selectedDate != null && selectedDate.isAfter(endFriday)) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_START_MONDAY_TITLE), props.getProperty(INVALID_START_MONDAY_MESSAGE));
            startingMondayPicker.setValue(startMonday);
        }
        else if(endFriday == null || (selectedDate != null && selectedDate.isBefore(endFriday))) {
            jTPS transactions = workspace.getjTPS();
            StartMondayTrans start = new StartMondayTrans(app, selectedDate, startMonday, endFriday);
            transactions.addTransaction(start);
        }
    }
    
    public void handleEndingFriday() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        DatePicker endingFridayPicker = workspace.getEndingFridayPicker();
        CSGData data = (CSGData)app.getDataComponent();
        LocalDate selectedDate = endingFridayPicker.getValue();
        LocalDate startMonday = null;
        if(data.getStartMonday() != null)
            startMonday = LocalDate.parse(data.getStartMonday());
        LocalDate endFriday = null;
        if(data.getEndFriday() != null)
            endFriday = LocalDate.parse(data.getEndFriday());
        
        if(selectedDate != null && !selectedDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_END_FRIDAY_FRIDAY_TITLE), props.getProperty(INVALID_END_FRIDAY_FRIDAY_MESSAGE));
            endingFridayPicker.setValue(endFriday);
        }
        else if(startMonday != null && selectedDate != null && selectedDate.isBefore(startMonday)) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_END_FRIDAY_TITLE), props.getProperty(INVALID_END_FRIDAY_MESSAGE));
            endingFridayPicker.setValue(endFriday);
        }
        else if(startMonday == null || (selectedDate != null && selectedDate.isAfter(startMonday))) {
            jTPS transactions = workspace.getjTPS();
            EndFridayTrans start = new EndFridayTrans(app, selectedDate, startMonday, endFriday);
            transactions.addTransaction(start);
        }
    }
    
    public void handleAddScheduleItem() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        ComboBox typeComboBox = workspace.getTypeComboBox();
        String type = (String)(typeComboBox.getValue());
        DatePicker dateSchedulePicker = workspace.getDateSchedulePicker();
        String date = "";
        if(dateSchedulePicker.getValue() != null)
            date = dateSchedulePicker.getValue().toString();
        TextField timeTextField = workspace.getTimeTextField();
        String time = timeTextField.getText();
        TextField titleScheduleTextField = workspace.getTitleScheduleTextField();
        String title = titleScheduleTextField.getText();
        TextField topicTextField = workspace.getTopicTextField();
        String topic = topicTextField.getText();
        TextField linkTextField = workspace.getLinkTextField();
        String link = linkTextField.getText();
        TextField criteriaTextField = workspace.getCriteriaTextField();
        String criteria = criteriaTextField.getText();
        jTPS transactions = workspace.getjTPS();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSGData data = (CSGData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if (type == null) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_TYPE_TITLE), props.getProperty(MISSING_SCHEDULE_TYPE_MESSAGE));            
        }
        else if (date.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_DATE_TITLE), props.getProperty(MISSING_SCHEDULE_DATE_MESSAGE));
        }
        else if (time.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_TIME_TITLE), props.getProperty(MISSING_SCHEDULE_TIME_MESSAGE));
        }
        else if(title.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_TITLE_TITLE), props.getProperty(MISSING_SCHEDULE_TITLE_MESSAGE));
        }
        else if(topic.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_TOPIC_TITLE), props.getProperty(MISSING_SCHEDULE_TOPIC_MESSAGE));
        }
        else if(link.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_LINK_TITLE), props.getProperty(MISSING_SCHEDULE_LINK_MESSAGE));
        }
        else if(criteria.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_CRITERIA_TITLE), props.getProperty(MISSING_SCHEDULE_CRITERIA_MESSAGE));
        }
        else {
            AddSITrans add = new AddSITrans(app,type,date,time,title,topic,link,criteria);
            transactions.addTransaction(add);
            
            // CLEAR THE TEXT FIELDS
            typeComboBox.setValue(null);
            dateSchedulePicker.setValue(null);
            timeTextField.setText("");
            titleScheduleTextField.setText("");
            topicTextField.setText("");
            linkTextField.setText("");
            criteriaTextField.setText("");
            
            app.getGUI().getAppFileController().markAsDone(app.getGUI());
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        }
    }
    
    public void handleDeleteScheduleItem(KeyCode key) {
        // GET THE TABLE
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        TableView scheduleItemTable = workspace.getScheduleItemTable();
        
        // IS A RECITATION SELECTED IN THE TABLE?
        Object selectedItem = scheduleItemTable.getSelectionModel().getSelectedItem();
        
        // GET THE RECITATION
        ScheduleItem scheduleItem = (ScheduleItem)selectedItem;
        jTPS transaction = workspace.getjTPS();
        DeleteSITrans delete = new DeleteSITrans(app,scheduleItem);
        
        if(key == KeyCode.DELETE) {
            transaction.addTransaction(delete);
            workspace.getTypeComboBox().setValue(null);
            workspace.getDateSchedulePicker().setValue(null);
            workspace.getTimeTextField().setText("");
            workspace.getTopicTextField().setText("");
            workspace.getLinkTextField().setText("");
            workspace.getCriteriaTextField().setText("");
            workspace.getAddScheduleItemButton().setText(props.getProperty(ADD_SCHEDULE_BUTTON_TEXT));
            workspace.getAddScheduleItemButton().setOnAction(ee -> {
                handleAddScheduleItem();
            });
        }
        app.getGUI().getAppFileController().markAsDone(app.getGUI());
        app.getGUI().getAppFileController().markAsEdited(app.getGUI());
    }
    
    public void handleSelectScheduleItem() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TableView scheduleItemTable = workspace.getScheduleItemTable();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Button addButton = workspace.getAddScheduleItemButton();
        ComboBox typeComboBox = workspace.getTypeComboBox();
        DatePicker dateSchedulePicker = workspace.getDateSchedulePicker();
        TextField timeTextField = workspace.getTimeTextField();
        TextField titleScheduleTextField = workspace.getTitleScheduleTextField();
        TextField topicTextField = workspace.getTopicTextField();
        TextField linkTextField = workspace.getLinkTextField();
        TextField criteriaTextField = workspace.getCriteriaTextField();
        Object selectedItem = scheduleItemTable.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null) {
            ScheduleItem scheduleItem = (ScheduleItem)selectedItem;
            typeComboBox.setValue(scheduleItem.getType());
            dateSchedulePicker.setValue(LocalDate.parse(scheduleItem.getDate()));
            timeTextField.setText(scheduleItem.getTime());
            titleScheduleTextField.setText(scheduleItem.getTitle());
            topicTextField.setText(scheduleItem.getTopic());
            linkTextField.setText(scheduleItem.getLink());
            criteriaTextField.setText(scheduleItem.getCriteria());
            addButton.setText(props.getProperty(CSGProperty.UPDATE_SCHEDULE_BUTTON_TEXT.toString()));
            addButton.setOnAction(e -> {
                handleUpdateScheduleItem(selectedItem);
            });
        }
    }
    
    public void handleUpdateScheduleItem(Object selectedItem) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        ScheduleItem scheduleItem = (ScheduleItem)selectedItem;
            
        jTPS transactions = workspace.getjTPS();
        UpdateSITrans update = new UpdateSITrans(app, scheduleItem.getType(), scheduleItem.getDate(), scheduleItem.getTime(), scheduleItem.getTitle(), scheduleItem.getTopic(), scheduleItem.getLink(), scheduleItem.getCriteria(), scheduleItem);
        transactions.addTransaction(update);
    }
    
    public void handleClearScheduleItem() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        TableView scheduleItemTable = workspace.getScheduleItemTable();
        Button addButton = workspace.getAddScheduleItemButton();
        ComboBox typeComboBox = workspace.getTypeComboBox();
        DatePicker dateSchedulePicker = workspace.getDateSchedulePicker();
        TextField timeTextField = workspace.getTimeTextField();
        TextField titleScheduleTextField = workspace.getTitleScheduleTextField();
        TextField topicTextField = workspace.getTopicTextField();
        TextField linkTextField = workspace.getLinkTextField();
        TextField criteriaTextField = workspace.getCriteriaTextField();
        
        scheduleItemTable.getSelectionModel().clearSelection();
        
        typeComboBox.setValue(null);
        dateSchedulePicker.setValue(null);
        timeTextField.setText("");
        titleScheduleTextField.setText("");
        topicTextField.setText("");
        linkTextField.setText("");
        criteriaTextField.setText("");

        addButton.setText(props.getProperty(ADD_SCHEDULE_BUTTON_TEXT));
        addButton.setOnAction(ee -> {
            handleAddScheduleItem();
        });
    }
    
    public void handleAddTeam() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TextField nameTextField = workspace.getNameTeamTextField();
        String name = nameTextField.getText();
        ColorPicker colorPicker = workspace.getColorPicker();
        String color = Integer.toHexString(colorPicker.getValue().hashCode());
        ColorPicker textColorPicker = workspace.getTextColorPicker();
        String textColor = Integer.toHexString(textColorPicker.getValue().hashCode());
        TextField linkTextField = workspace.getLinkTeamTextField();
        String link = linkTextField.getText();
        
        jTPS transactions = workspace.getjTPS();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSGData data = (CSGData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if (name.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_NAME_TITLE), props.getProperty(MISSING_TEAM_NAME_MESSAGE));            
        }
        else if (color.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_COLOR_TITLE), props.getProperty(MISSING_TEAM_COLOR_MESSAGE));
        }
        else if (textColor.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_TEXT_COLOR_TITLE), props.getProperty(MISSING_TEAM_TEXT_COLOR_MESSAGE));
        }
        else if(link.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_LINK_TITLE), props.getProperty(MISSING_TEAM_LINK_MESSAGE));
        }
        else if (data.containsTeam(name,color)) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(TEAM_NAME_AND_COLOR_NOT_UNIQUE_TITLE), props.getProperty(TEAM_NAME_AND_COLOR_NOT_UNIQUE_MESSAGE));                                    
        }
        else {
            AddTeamTrans add = new AddTeamTrans(app,name,color,textColor,link);
            transactions.addTransaction(add);
            
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            colorPicker.setValue(Color.WHITE);
            textColorPicker.setValue(Color.WHITE);
            linkTextField.setText("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            
            app.getGUI().getAppFileController().markAsDone(app.getGUI());
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        }
    }
    
    public void handleDeleteTeam(KeyCode key) {
        // GET THE TABLE
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        TableView teamTable = workspace.getTeamTable();
        
        // IS A RECITATION SELECTED IN THE TABLE?
        Object selectedItem = teamTable.getSelectionModel().getSelectedItem();
        
        // GET THE TEAM
        Team team = (Team)selectedItem;
        jTPS transaction = workspace.getjTPS();
        DeleteTeamTrans delete = new DeleteTeamTrans(app,team);
        
        if(key == KeyCode.DELETE) {
            transaction.addTransaction(delete);
            workspace.getNameTeamTextField().setText("");
            workspace.getColorPicker().setValue(Color.WHITE);
            workspace.getTextColorPicker().setValue(Color.WHITE);
            workspace.getLinkTeamTextField().setText("");
            workspace.getAddTeamButton().setText(props.getProperty(ADD_TEAM_BUTTON_TEXT));
            workspace.getAddTeamButton().setOnAction(ee -> {
                handleAddTeam();
            });
        }
        app.getGUI().getAppFileController().markAsDone(app.getGUI());
        app.getGUI().getAppFileController().markAsEdited(app.getGUI());
    }
    
    public void handleSelectTeam() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        TableView teamTable = workspace.getTeamTable();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Button addButton = workspace.getAddTeamButton();
        TextField nameTextField = workspace.getNameTeamTextField();
        ColorPicker colorPicker = workspace.getColorPicker();
        ColorPicker textColorPicker = workspace.getTextColorPicker();
        TextField linkTextField = workspace.getLinkTeamTextField();
        Object selectedItem = teamTable.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null) {
            Team team = (Team)selectedItem;
            nameTextField.setText(team.getName());
            colorPicker.setValue(Color.web(team.getColor()));
            textColorPicker.setValue(Color.web(team.getTextColor()));
            linkTextField.setText(team.getLink());
            addButton.setText(props.getProperty(CSGProperty.UPDATE_TEAM_BUTTON_TEXT.toString()));
            addButton.setOnAction(e -> {
                handleUpdateTeam(selectedItem);
            });
            
        }
    }
    
    public void handleUpdateTeam(Object selectedItem) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        Team team = (Team)selectedItem;
            
        jTPS transactions = workspace.getjTPS();
        UpdateTeamTrans update = new UpdateTeamTrans(app, team.getName(), team.getColor(), team.getTextColor(), team.getLink(), team);
        transactions.addTransaction(update);
    }
    
    public void handleClearTeam() {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        TableView teamTable = workspace.getTeamTable();
        Button addButton = workspace.getAddTeamButton();
        TextField nameTextField = workspace.getNameTeamTextField();
        ColorPicker colorPicker = workspace.getColorPicker();
        ColorPicker textColorPicker = workspace.getTextColorPicker();
        TextField linkTextField = workspace.getLinkTeamTextField();
        
        teamTable.getSelectionModel().clearSelection();
        
        nameTextField.setText("");
        colorPicker.setValue(Color.WHITE);
        textColorPicker.setValue(Color.WHITE);
        linkTextField.setText("");

        nameTextField.requestFocus();

        addButton.setText(props.getProperty(ADD_TEAM_BUTTON_TEXT));
        addButton.setOnAction(ee -> {
            handleAddTeam();
        });
    }
    
    public class EmailValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
    }
}
