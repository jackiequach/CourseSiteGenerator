/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.SitePage;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.workspace.CSGWorkspace;
import djf.AppTemplate;
import djf.components.AppStyleComponent;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Jackie
 */
public class CSGStyle extends AppStyleComponent {
     // FIRST WE SHOULD DECLARE ALL OF THE STYLE TYPES WE PLAN TO USE
    
    // WE'LL USE THIS FOR ORGANIZING LEFT AND RIGHT CONTROLS
    public static String CLASS_PLAIN_PANE = "plain_pane";
    public static String CLASS_GRID_PANE = "grid_pane";
    
    // THESE ARE THE HEADERS FOR EACH SIDE
    public static String CLASS_HEADER_PANE = "header_pane";
    public static String CLASS_HEADER_LABEL = "header_label";

    public static String CLASS_LABEL = "label_label";
    
    public static String CLASS_ADD_HEADER = "add_header";
    
    // ON THE LEFT WE HAVE THE TA ENTRY
    public static String CLASS_TA_TABLE = "ta_table";
    public static String CLASS_TA_TABLE_COLUMN_HEADER = "ta_table_column_header";
    public static String CLASS_ADD_TA_PANE = "add_ta_pane";
    public static String CLASS_ADD_TA_TEXT_FIELD = "add_ta_text_field";
    public static String CLASS_ADD_TA_BUTTON = "add_ta_button";

    // ON THE RIGHT WE HAVE THE OFFICE HOURS GRID
    public static String CLASS_OFFICE_HOURS_GRID = "office_hours_grid";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE = "office_hours_grid_time_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL = "office_hours_grid_time_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE = "office_hours_grid_day_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL = "office_hours_grid_day_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE = "office_hours_grid_time_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL = "office_hours_grid_time_cell_label";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE = "office_hours_grid_ta_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL = "office_hours_grid_ta_cell_label";

    public static String START_TIME_COMBOBOX = "start_time_combobox";
    public static String START_TIME_LABEL = "start_time_label";
    public static String END_TIME_COMBOBOX = "end_time_combobox";
    public static String END_TIME_LABEL = "end_time_label";
    
    private AppTemplate app;
    
    public CSGStyle(AppTemplate initApp) {
        app = initApp;

        super.initStylesheet(app);

        app.getGUI().initFileToolbarStyle();
        
        initCSGWorkspaceStyle();
    }
    
    /**
     * This function specifies all the style classes for
     * all user interface controls in the workspace.
     */
    private void initCSGWorkspaceStyle() {
        // HEADERS
        CSGWorkspace workspaceComponent = (CSGWorkspace)app.getWorkspaceComponent();
        workspaceComponent.getTAsHeaderBox().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getTAsHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getRecitationsHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getRecitationsHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getScheduleHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getScheduleHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getProjectHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getProjectHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getTeamsHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getTeamsHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getStudentsHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getStudentsHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getCourseInfoHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getCourseInfoHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getSiteTemplateHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getSiteTemplateHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getPageStyleHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getPageStyleHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getScheduleItemsHeaderPane().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getScheduleItemsHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getCalendarBoundariesLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        
        // ADD HEADERS
        workspaceComponent.getAddEditRecitationHeaderLabelLabel().getStyleClass().add(CLASS_ADD_HEADER);
        workspaceComponent.getAddEditScheduleHeaderLabelLabel().getStyleClass().add(CLASS_ADD_HEADER);
        workspaceComponent.getAddTeamHeaderLabel().getStyleClass().add(CLASS_ADD_HEADER);
        workspaceComponent.getAddStudentHeaderLabel().getStyleClass().add(CLASS_ADD_HEADER);
        
        // LABELS
        workspaceComponent.getSubjectLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getNumberLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getSemesterLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getYearLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getTitleLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getInstructorNameLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getInstructorHomeLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getExportDirLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getSitePagesLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getBannerSchoolImgLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getLeftFooterImgLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getRightFooterImgLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getStylesheetLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getSectionLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getInstructorLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getDayLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getLocationLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getSupervisingTALabelOne().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getSupervisingTALabelTwo().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getStartingMondayLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getEndingFridayLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getTypeLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getDateLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getTimeLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getTitleScheduleLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getTopicLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getLinkLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getCriteriaLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getNameTeamLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getColorLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getTextColorLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getLinkTeamLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getFirstNameLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getLastNameLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getTeamLabel().getStyleClass().add(CLASS_LABEL);
        workspaceComponent.getRoleLabel().getStyleClass().add(CLASS_LABEL);
        
        // GRID PANES
        workspaceComponent.getCourseInfoGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getSiteTemplateGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getPageStyleGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getAddEditrecitationGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getCalendarBoundariesGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getScheduleItemsGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getAddScheduleGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getTeamsGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getAddTeamsGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getStudentsGridPane().getStyleClass().add(CLASS_GRID_PANE);
        workspaceComponent.getAddStudentsGridPane().getStyleClass().add(CLASS_GRID_PANE);

        // TABLES
        TableView<SitePage> siteTable = workspaceComponent.getSiteTable();
        siteTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : siteTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }
        
        TableView<TeachingAssistant> taTable = workspaceComponent.getTATable();
        taTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : taTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }
        
        TableView<Recitation> recitationTable = workspaceComponent.getRecitationTable();
        recitationTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : recitationTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }
        
        TableView<ScheduleItem> scheduleItemTable = workspaceComponent.getScheduleItemTable();
        scheduleItemTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : scheduleItemTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }
        
        TableView<Team> teamTable = workspaceComponent.getTeamTable();
        teamTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : teamTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }
        
        TableView<Student> studentTable = workspaceComponent.getStudentTable();
        studentTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : studentTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }

        // LEFT SIDE - THE TA DATA ENTRY
        workspaceComponent.getAddBox().getStyleClass().add(CLASS_ADD_TA_PANE);
        
        // TEXTFIELDS
        workspaceComponent.getNameTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getEmailTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getTitleTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getInstructorNameTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getInstructorHomeTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getSectionTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getInstructorTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getDayTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getLocationTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getTimeTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getTitleScheduleTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getTopicTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getLinkTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getCriteriaTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getNameTeamTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getLinkTeamTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getFirstNameTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getLastNameTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getRoleTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        
        // BUTTONS
        workspaceComponent.getAddButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getChangeExportDirButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getSelectTemplateDirectoryButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getChangeBannerButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getChangeRightFooterButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getChangeLeftFooterButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getAddRecitationButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getClearRecitationButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getAddScheduleItemButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getClearScheduleItemButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getAddTeamButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getClearTeamButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getAddStudentButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getClearStudentButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);

        // RIGHT SIDE - THE HEADER
        workspaceComponent.getOfficeHoursSubheaderBox().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getOfficeHoursSubheaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        workspaceComponent.getStartTimeComboBox().getStyleClass().add(START_TIME_COMBOBOX);
        workspaceComponent.getStartTimeLabel().getStyleClass().add(START_TIME_LABEL);
        workspaceComponent.getEndTimeComboBox().getStyleClass().add(END_TIME_COMBOBOX);
        workspaceComponent.getEndTimeLabel().getStyleClass().add(END_TIME_LABEL);
    }
    
    /**
     * This method initializes the style for all UI components in
     * the office hours grid. Note that this should be called every
     * time a new TA Office Hours Grid is created or loaded.
     */
    public void initOfficeHoursGridStyle() {
        // RIGHT SIDE - THE OFFICE HOURS GRID TIME HEADERS
        CSGWorkspace workspaceComponent = (CSGWorkspace)app.getWorkspaceComponent();
        workspaceComponent.getOfficeHoursGridPane().getStyleClass().add(CLASS_OFFICE_HOURS_GRID);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderPanes(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderLabels(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderPanes(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderLabels(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellPanes(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellLabels(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellPanes(), CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellLabels(), CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL);
    }
    
    /**
     * This helper method initializes the style of all the nodes in the nodes
     * map to a common style, styleClass.
     */
    private void setStyleClassOnAll(HashMap nodes, String styleClass) {
        for (Object nodeObject : nodes.values()) {
            Node n = (Node)nodeObject;
            n.getStyleClass().add(styleClass);
        }
    }
}
