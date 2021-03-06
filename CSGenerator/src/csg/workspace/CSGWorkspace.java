/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGProperty;
import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.SitePage;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.style.CSGStyle;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import static djf.settings.AppStartupConstants.PATH_WORK;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import jtps.jTPS;
import properties_manager.PropertiesManager;

/**
 *
 * @author Jackie
 */
public class CSGWorkspace extends AppWorkspaceComponent {
    CSGeneratorApp app;
    
    CSGController controller;
    
    jTPS transactions = new jTPS();
    
    TabPane tabs;
    
    Tab courseDetailsTab;
    GridPane courseGridPane;
    GridPane courseInfoGridPane;
    GridPane siteTemplateGridPane;
    GridPane pageStyleGridPane;
    BorderPane courseDetailsPane;
    ScrollPane courseDetailsScroll;
    
    Tab taDataTab;
    BorderPane taDataPane;
    
    GridPane recitationGridPane;
    GridPane addEditrecitationGridPane;
    Tab recitationDataTab;
    BorderPane recitationDataPane;
    ScrollPane recitationDataScroll;
    
    GridPane scheduleGridPane;
    GridPane calendarBoundariesGridPane;
    GridPane scheduleItemsGridPane;
    GridPane addScheduleGridPane;
    Tab scheduleDataTab;
    BorderPane scheduleDataPane;
    ScrollPane scheduleDataScroll;
    
    GridPane projectsGridPane;
    GridPane teamsGridPane;
    GridPane addTeamsGridPane;
    GridPane studentsGridPane;
    GridPane addStudentsGridPane;
    Tab projectDataTab;
    BorderPane projectDataPane;
    ScrollPane projectDataScroll;
    
    // FOR COURSE DETAILS TAB
    HBox courseInfoHeaderBox;
    Label ciHeaderLabel;
    
    Label subjectLabel;
    ComboBox subjectComboBox;
    
    Label numberLabel;
    ComboBox numberComboBox;
    
    Label semesterLabel;
    ComboBox semesterComboBox;
    
    Label yearLabel;
    ComboBox yearComboBox;
    
    Label titleLabel;
    TextField titleTextField;
    
    Label instructorNameLabel;
    TextField instructorNameTextField;
    
    Label instructorHomeLabel;
    TextField instructorHomeTextField;
    
    Label exportDirLabel;
    Label exportDirPathLabel;
    Button changeExportDirButton;
    
    HBox siteTemplateHeaderBox;
    Label siteTemplateHeaderLabel;
    
    Label siteTemplateInfoLabel;
    
    Label selectTemplateDirPathLabel;
    Button selectTemplateDirButton;
    
    Label sitePagesHeaderLabel;
    
    TableView<SitePage> siteTable;
    TableColumn<SitePage, Boolean> useColumn;
    TableColumn<SitePage, String> navbarColumn;
    TableColumn<SitePage, String> fileColumn;
    TableColumn<SitePage, String> scriptColumn;
    
    HBox pageStyleHeaderBox;
    Label pageStyleHeaderLabel;
    
    Label bannerSchoolImgLabel;
    Label bannerImgPath;
    Image bannerSchoolImg;
    ImageView bannerSchoolImgView;
    Button changeBannerButton;
    
    Label leftFooterImgLabel;
    Label leftFooterImgPath;
    Image leftFooterImg;
    ImageView leftFooterImgView;
    Button changeLeftFooterButton;
    
    Label rightFooterImgLabel;
    Label rightFooterImgPath;
    Image rightFooterImg;
    ImageView rightFooterImgView;
    Button changeRightFooterButton;
    
    Label stylesheetLabel;
    ComboBox stylesheetComboBox;
    Label ssNoteLabel;
    
    // FOR TA DATA TAB
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
    Button deleteTAButton;
    
    TableView<TeachingAssistant> taTable;
    TableColumn<TeachingAssistant, Boolean> undergradColumn;
    TableColumn<TeachingAssistant, String> nameColumn;
    TableColumn<TeachingAssistant, String> emailColumn;
    
    HBox addBox;
    TextField nameTextField;
    TextField emailTextField;
    Button addButton;
    Button clearButton;
    
    BorderPane officeHoursHeaderBox;
    Label officeHoursHeaderLabel;
    
    ComboBox startTime;
    ComboBox endTime;
    ObservableList<String> times;
    Label startT;
    Label endT;
    
    GridPane officeHoursGridPane;
    HashMap<String, Pane> officeHoursGridTimeHeaderPanes;
    HashMap<String, Label> officeHoursGridTimeHeaderLabels;
    HashMap<String, Pane> officeHoursGridDayHeaderPanes;
    HashMap<String, Label> officeHoursGridDayHeaderLabels;
    HashMap<String, Pane> officeHoursGridTimeCellPanes;
    HashMap<String, Label> officeHoursGridTimeCellLabels;
    HashMap<String, Pane> officeHoursGridTACellPanes;
    HashMap<String, Label> officeHoursGridTACellLabels;
    
    // FOR RECITATION DATA TAB
    HBox recitationHeaderBox;
    Label recitationHeaderLabel;
    
    Button deleteRecitationButton;
    
    TableView<Recitation> recitationTable;
    TableColumn<Recitation, String> sectionColumn;
    TableColumn<Recitation, String> instructorColumn;
    TableColumn<Recitation, String> dayColumn;
    TableColumn<Recitation, String> locationColumn;
    TableColumn<Recitation, String> supervisingTAColumnOne;
    TableColumn<Recitation, String> supervisingTAColumnTwo;
    
    VBox addEditBox;
    
    HBox addEditHeaderBox;
    Label addEditHeaderLabel;
    
    HBox sectionBox;
    Label sectionLabel;
    TextField sectionTextField;
    
    HBox instructorBox;
    Label instructorLabel;
    TextField instructorTextField;
    
    HBox dayBox;
    Label dayLabel;
    TextField dayTextField;
    
    HBox locationBox;
    Label locationLabel;
    TextField locationTextField;
    
    HBox supervisingTABoxOne;
    HBox supervisingTABoxTwo;
    Label supervisingLabelOne;
    Label supervisingLabelTwo;
    ComboBox supervisingTAComboBoxOne;
    ComboBox supervisingTAComboBoxTwo;
    
    Button addRecitationButton;
    Button clearRecitationButton;
    
    // FOR SCHEDULE DATA TAB
    HBox scheduleHeaderBox;
    Label scheduleHeaderLabel;
    
    Label calendarBoundariesHeaderLabel;
    
    Label startingMondayLabel;
    DatePicker startingMondayPicker;
    
    Label endingFridayLabel;
    DatePicker endingFridayDatePicker;
    
    HBox scheduleItemsHeaderBox;
    Label scheduleItemsHeaderLabel;
    Button deleteScheduleItemButton;
    
    TableView<ScheduleItem> scheduleItemTable;
    TableColumn<ScheduleItem, String> typeColumn;
    TableColumn<ScheduleItem, String> dateColumn;
    TableColumn<ScheduleItem, String> titleColumn;
    TableColumn<ScheduleItem, String> topicColumn;
    
    HBox addEditScheduleHeaderBox;
    Label addEditScheduleHeaderLabel;
    
    Label typeLabel;
    ComboBox typeComboBox;
    
    Label dateLabel;
    DatePicker dateSchedulePicker;
    
    Label timeLabel;
    TextField timeTextField;
    
    Label titleScheduleLabel;
    TextField titleScheduleTextField;
    
    Label topicLabel;
    TextField topicTextField;
    
    Label linkLabel;
    TextField linkTextField;
    
    Label criteriaLabel;
    TextField criteriaTextField;
    
    Button addScheduleItemButton;
    Button clearScheduleItemButton;
    
    // FOR PROJECT DATA TAB
    HBox projectHeaderBox;
    Label projectHeaderLabel;
    
    HBox teamsHeaderBox;
    Label teamsHeaderLabel;
    Button teamsDeleteButton;
    
    TableView<Team> teamTable;
    TableColumn<Team, String> nameTeamColumn;
    TableColumn<Team, String> colorColumn;
    TableColumn<Team, String> textColorColumn;
    TableColumn<Team, String> linkColumn;
    
    Label addTeamHeader;
    
    Label nameLabel;
    TextField nameTeamTextField;
    
    Label colorLabel;
    ColorPicker colorPicker;
    
    Label textColorLabel;
    ColorPicker textColorPicker;
    
    Label linkTeamLabel;
    TextField linkTeamTextField;
    
    Button addTeamButton;
    Button clearTeamButton;
    
    HBox studentsHeaderBox;
    Label studentsHeaderLabel;
    Button studentsDeleteButton;
    
    TableView<Student> studentTable;
    TableColumn<Student, String> firstNameColumn;
    TableColumn<Student, String> lastNameColumn;
    TableColumn<Student, String> teamColumn;
    TableColumn<Student, String> roleColumn;
    
    Label addStudentHeader;
    
    Label firstNameLabel;
    TextField firstNameTextField;
    
    Label lastNameLabel;
    TextField lastNameTextField;
    
    Label teamLabel;
    ComboBox teamComboBox;
    
    Label roleLabel;
    TextField roleTextField;
    
    Button addStudentButton;
    Button clearStudentButton;
    
    public CSGWorkspace(CSGeneratorApp initApp) {
        app = initApp;
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        tabs = new TabPane();

        controller = new CSGController(app);
        
        courseDetailsTab = new Tab();
        courseDetailsTab.setText(props.getProperty(CSGProperty.COURSEDETAILS_TAB_TEXT.toString()));
        courseDetailsTab.setContent(generateCourseDetailsTab());
        
        taDataTab = new Tab();
        taDataTab.setText(props.getProperty(CSGProperty.TADATA_TAB_TEXT.toString()));
        taDataTab.setContent(generateTADataTab());
        
        recitationDataTab = new Tab();
        recitationDataTab.setText(props.getProperty(CSGProperty.RECITATIONDATA_TAB_TEXT.toString()));
        recitationDataTab.setContent(generateRecitationDataTab());
        
        scheduleDataTab = new Tab();
        scheduleDataTab.setText(props.getProperty(CSGProperty.SCHEDULEDATA_TAB_TEXT.toString()));
        scheduleDataTab.setContent(generateScheduleDataTab());
        
        projectDataTab = new Tab();
        projectDataTab.setText(props.getProperty(CSGProperty.PROJECTDATA_TAB_TEXT.toString()));
        projectDataTab.setContent(generateProjectDataTab());
        
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabs.getTabs().addAll(courseDetailsTab, taDataTab, recitationDataTab, scheduleDataTab, projectDataTab);
        
        workspace = new BorderPane();
        workspace.setOnKeyPressed(e -> {
            controller.handleKeyPress(e);
        });
        ((BorderPane) workspace).setCenter(tabs);
    }
    
    private Pane generateCourseDetailsTab() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData data = (CSGData) app.getDataComponent();
        courseInfoHeaderBox = new HBox();
        String courseInfoHeaderText = props.getProperty(CSGProperty.COURSEINFO_HEADER_TEXT.toString());
        ciHeaderLabel = new Label(courseInfoHeaderText);
        courseInfoHeaderBox.getChildren().add(ciHeaderLabel);
        
        String subjectLabelText = props.getProperty(CSGProperty.SUBJECT_LABEL_TEXT.toString());
        subjectLabel = new Label(subjectLabelText);
        subjectComboBox = new ComboBox();
        subjectComboBox.setItems(data.getSubjects());
        subjectComboBox.setValue(data.getSubject());
        
        String numberLabelText = props.getProperty(CSGProperty.NUMBER_LABEL_TEXT.toString());
        numberLabel = new Label(numberLabelText);
        numberComboBox = new ComboBox();
        numberComboBox.setItems(data.getNumbers());
        numberComboBox.setValue(data.getNumber());
        
        String semesterLabelText = props.getProperty(CSGProperty.SEMESTER_LABEL_TEXT.toString());
        semesterLabel = new Label(semesterLabelText);
        semesterComboBox = new ComboBox();
        semesterComboBox.setItems(data.getSemesters());
        semesterComboBox.setValue(data.getSemester());
        
        String yearLabelText = props.getProperty(CSGProperty.YEAR_LABEL_TEXT.toString());
        yearLabel = new Label(yearLabelText);
        yearComboBox = new ComboBox();
        yearComboBox.setItems(data.getYears());
        yearComboBox.setValue(data.getYear());
        
        String titleLabelText = props.getProperty(CSGProperty.TITLE_LABEL_TEXT.toString());
        titleLabel = new Label(titleLabelText);
        titleTextField = new TextField();
        String titlePromptText = props.getProperty(CSGProperty.TITLE_PROMPT_TEXT.toString());
        titleTextField.setPromptText(titlePromptText);
        titleTextField.setText(data.getTitle());
        
        String instructorNameLabelText = props.getProperty(CSGProperty.INSTRUCTORNAME_LABEL_TEXT.toString());
        instructorNameLabel = new Label(instructorNameLabelText);
        instructorNameTextField = new TextField();
        String instructorNamePromptText = props.getProperty(CSGProperty.INSTRUCTORNAME_PROMPT_TEXT.toString());
        instructorNameTextField.setPromptText(instructorNamePromptText);
        instructorNameTextField.setText(data.getInstructorName());
        
        
        String instructorHomeLabelText = props.getProperty(CSGProperty.INSTRUCTORHOME_LABEL_TEXT.toString());
        instructorHomeLabel = new Label(instructorHomeLabelText);
        instructorHomeTextField = new TextField();
        String instructorHomePromptText = props.getProperty(CSGProperty.INSTRUCTORHOME_PROMPT_TEXT.toString());
        instructorHomeTextField.setPromptText(instructorHomePromptText);
        instructorHomeTextField.setText(data.getInstructorHome());
        
        String exportLabelText = props.getProperty(CSGProperty.EXPORTDIR_LABEL_TEXT.toString());
        exportDirLabel = new Label(exportLabelText);
        exportDirPathLabel = new Label();
        exportDirPathLabel.setText(data.getExportDirPath());
        changeExportDirButton = new Button(props.getProperty(CSGProperty.CHANGE_BUTTON_TEXT.toString()));
        
        courseInfoGridPane = new GridPane();
        courseInfoGridPane.setVgap(5);
        courseInfoGridPane.setHgap(5);
        courseInfoGridPane.setPadding(new Insets(10, 10, 10, 10));
        courseInfoGridPane.add(courseInfoHeaderBox, 0, 0);
        courseInfoGridPane.add(subjectLabel, 0, 1);
        courseInfoGridPane.add(subjectComboBox, 1, 1);
        courseInfoGridPane.add(numberLabel, 2, 1);
        courseInfoGridPane.add(numberComboBox, 3, 1);
        courseInfoGridPane.add(semesterLabel, 0, 2);
        courseInfoGridPane.add(semesterComboBox, 1, 2);
        courseInfoGridPane.add(yearLabel, 2, 2);
        courseInfoGridPane.add(yearComboBox, 3, 2);
        courseInfoGridPane.add(titleLabel, 0, 3);
        courseInfoGridPane.add(titleTextField, 1, 3, 3, 1);
        courseInfoGridPane.add(instructorNameLabel, 0, 4);
        courseInfoGridPane.add(instructorNameTextField, 1, 4, 3, 1);
        courseInfoGridPane.add(instructorHomeLabel, 0, 5);
        courseInfoGridPane.add(instructorHomeTextField, 1, 5, 3, 1);
        courseInfoGridPane.add(exportDirLabel, 0, 6);
        courseInfoGridPane.add(exportDirPathLabel, 1, 6, 2, 1);
        courseInfoGridPane.add(changeExportDirButton, 3, 6);
        
        siteTemplateHeaderBox = new HBox();
        String siteTemplateHeaderText = props.getProperty(CSGProperty.SITETEMPLATE_HEADER_TEXT.toString());
        siteTemplateHeaderLabel = new Label(siteTemplateHeaderText);
        siteTemplateHeaderBox.getChildren().add(siteTemplateHeaderLabel);
        
        String siteTemplateInfoText = props.getProperty(CSGProperty.SITETEMPLATE_LABEL_TEXT.toString());
        siteTemplateInfoLabel = new Label(siteTemplateInfoText);
        
        selectTemplateDirPathLabel = new Label();
        selectTemplateDirPathLabel.setText(data.getTemplateDirPath());
        selectTemplateDirButton = new Button(props.getProperty(CSGProperty.SELECT_TEMPLATE_BUTTON_TEXT.toString()));
        
        String sitePagesHeaderText = props.getProperty(CSGProperty.SITEPAGES_LABEL_TEXT.toString());
        sitePagesHeaderLabel = new Label(sitePagesHeaderText);
        
        siteTable = new TableView();
        ObservableList<SitePage> siteData = data.getSitePages();
        siteTable.setItems(siteData);
        String useColumnText = props.getProperty(CSGProperty.USE_COLUMN_TEXT.toString());
        String navbarColumnText = props.getProperty(CSGProperty.NAVBAR_COLUMN_TEXT.toString());
        String fileColumnText = props.getProperty(CSGProperty.FILENAME_COLUMN_TEXT.toString());
        String scriptColumnText = props.getProperty(CSGProperty.SCRIPT_COLUMN_TEXT.toString());
        useColumn = new TableColumn(useColumnText);
        useColumn.setCellValueFactory(
                new PropertyValueFactory<>("use")
        );
        useColumn.setCellFactory(
                new CheckBoxCellFactory()
        );
        useColumn.setEditable(true);
        siteTable.getColumns().add(useColumn);
        navbarColumn = new TableColumn(navbarColumnText);
        navbarColumn.setCellValueFactory(
                new PropertyValueFactory<>("navbar")
        );
        siteTable.getColumns().add(navbarColumn);
        fileColumn = new TableColumn(fileColumnText);
        fileColumn.setCellValueFactory(
                new PropertyValueFactory<>("file")
        );
        siteTable.getColumns().add(fileColumn);
        scriptColumn = new TableColumn(scriptColumnText);
        scriptColumn.setCellValueFactory(
                new PropertyValueFactory<>("script")
        );
        siteTable.getColumns().add(scriptColumn);
        siteTable.setEditable(true);
        
        siteTemplateGridPane = new GridPane();
        siteTemplateGridPane.setVgap(5);
        siteTemplateGridPane.setPadding(new Insets(10, 10, 10, 10));
        siteTemplateGridPane.add(siteTemplateHeaderBox, 0, 0);
        siteTemplateGridPane.add(siteTemplateInfoLabel, 0, 1);
        siteTemplateGridPane.add(selectTemplateDirPathLabel, 0, 2);
        siteTemplateGridPane.add(selectTemplateDirButton, 0, 3);
        siteTemplateGridPane.add(sitePagesHeaderLabel, 0, 4);
        siteTemplateGridPane.add(siteTable, 0, 5);
        siteTemplateGridPane.setHgrow(siteTable, Priority.ALWAYS);
        
        pageStyleHeaderBox = new HBox();
        String pageStyleHeaderText = props.getProperty(CSGProperty.PAGESTYLE_HEADER_TEXT.toString());
        pageStyleHeaderLabel = new Label(pageStyleHeaderText);
        pageStyleHeaderBox.getChildren().add(pageStyleHeaderLabel);
        
        String bannerSchoolImgText = props.getProperty(CSGProperty.BANNERSCHOOLIMG_LABEL_TEXT.toString());
        bannerSchoolImgLabel = new Label(bannerSchoolImgText);
        bannerImgPath = new Label(data.getBannerImgPath());
        bannerSchoolImg = new Image(bannerImgPath.getText());
        bannerSchoolImgView = new ImageView();
        bannerSchoolImgView.setImage(bannerSchoolImg);
        changeBannerButton = new Button(props.getProperty(CSGProperty.CHANGE_BUTTON_TEXT.toString()));
        
        String leftFooterImgText = props.getProperty(CSGProperty.LF_LABEL_TEXT.toString());
        leftFooterImgLabel = new Label(leftFooterImgText);
        leftFooterImgPath = new Label(data.getLeftFooterImgPath());
        leftFooterImg = new Image(leftFooterImgPath.getText());
        leftFooterImgView = new ImageView();
        leftFooterImgView.setImage(leftFooterImg);
        changeLeftFooterButton = new Button(props.getProperty(CSGProperty.CHANGE_BUTTON_TEXT.toString()));

        String rightFooterImgText = props.getProperty(CSGProperty.RF_LABEL_TEXT.toString());
        rightFooterImgLabel = new Label(rightFooterImgText);
        rightFooterImgPath = new Label(data.getRightFooterImgPath());
        rightFooterImg = new Image(rightFooterImgPath.getText());
        rightFooterImgView = new ImageView();
        rightFooterImgView.setImage(rightFooterImg);
        changeRightFooterButton = new Button(props.getProperty(CSGProperty.CHANGE_BUTTON_TEXT.toString()));

        String stylesheetText = props.getProperty(CSGProperty.SS_LABEL_TEXT.toString()); 
        stylesheetLabel = new Label(stylesheetText);
        stylesheetComboBox = new ComboBox();
        File workCssFolder = new File(PATH_WORK + "/css");
        File[] listOfCss = workCssFolder.listFiles();
        for(File file : listOfCss) {
            stylesheetComboBox.getItems().addAll(file.getName());
        }
        
        String ssNoteText = props.getProperty(CSGProperty.SS_NOTE_TEXT.toString());
        ssNoteLabel = new Label(ssNoteText);
        
        pageStyleGridPane = new GridPane();
        pageStyleGridPane.setVgap(5);
        pageStyleGridPane.setPadding(new Insets(10, 10, 10, 10));
        pageStyleGridPane.add(pageStyleHeaderBox, 0, 0);
        pageStyleGridPane.add(bannerSchoolImgLabel, 0, 1);
        pageStyleGridPane.add(bannerSchoolImgView, 1, 1);
        pageStyleGridPane.add(changeBannerButton, 2, 1);
        pageStyleGridPane.add(leftFooterImgLabel, 0, 2);
        pageStyleGridPane.add(leftFooterImgView, 1, 2);
        pageStyleGridPane.add(changeLeftFooterButton, 2, 2);
        pageStyleGridPane.add(rightFooterImgLabel, 0, 3);
        pageStyleGridPane.add(rightFooterImgView, 1, 3);
        pageStyleGridPane.add(changeRightFooterButton, 2, 3);
        pageStyleGridPane.add(stylesheetLabel, 0, 4);
        pageStyleGridPane.add(stylesheetComboBox, 1, 4);
        pageStyleGridPane.add(ssNoteLabel, 0, 5);
        
        courseGridPane = new GridPane();
        courseGridPane.setVgap(5);
        courseGridPane.add(courseInfoGridPane, 0, 0);
        courseGridPane.setHgrow(courseInfoGridPane, Priority.ALWAYS);
        courseGridPane.add(siteTemplateGridPane, 0, 1);
        courseGridPane.add(pageStyleGridPane, 0, 2);
                
        BorderPane temp = new BorderPane();
        temp.setTop(courseGridPane);
        
        courseDetailsScroll = new ScrollPane();
        courseDetailsScroll.setFitToWidth(true);
        courseDetailsScroll.setContent(temp);
        
        courseDetailsPane = new BorderPane();
        courseDetailsPane.setCenter(courseDetailsScroll);
        
        // CONTROLS FOR ADDING TAs
        subjectComboBox.setOnAction(e -> {
            controller.handleCourseInfo();
        });
        numberComboBox.setOnAction(e -> {
            controller.handleCourseInfo();
        });
        semesterComboBox.setOnAction(e -> {
            controller.handleCourseInfo();
        });
        yearComboBox.setOnAction(e -> {
            controller.handleCourseInfo();
        });
        titleTextField.textProperty().addListener((observable,oldvalue,newValue) -> {
            data.setTitle(newValue);
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        });
        instructorNameTextField.textProperty().addListener((observable,oldvalue,newValue)-> {
            data.setInstructorName(newValue);
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        });
        instructorHomeTextField.textProperty().addListener((observable,oldvalue,newValue)-> {
            data.setInstructorHome(newValue);
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        });
        
        changeExportDirButton.setOnAction(e -> {
            controller.handleChangeExportDir();
        });
        selectTemplateDirButton.setOnAction(e -> {
            controller.handleSelectTemplate();
        });
        changeBannerButton.setOnAction(e -> {
            controller.handleSelectBannerImg();
        });
        changeLeftFooterButton.setOnAction(e -> {
            controller.handleSelectLeftFooterImg();
        });
        changeRightFooterButton.setOnAction(e -> {
            controller.handleSelectRightFooterImg();
        });
        stylesheetComboBox.setOnAction(e -> {
            controller.handleStylesheet();
        });
        return courseDetailsPane;
    }
    
    private Pane generateTADataTab() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        tasHeaderBox = new HBox();
        String tasHeaderText = props.getProperty(CSGProperty.TAS_HEADER_TEXT.toString());
        tasHeaderLabel = new Label(tasHeaderText);
        String deleteButtonText = props.getProperty(CSGProperty.DELETE_BUTTON_TEXT.toString());
        deleteTAButton = new Button(deleteButtonText);
        deleteTAButton.prefHeightProperty().bind(tasHeaderBox.heightProperty().multiply(1));
        tasHeaderBox.getChildren().addAll(tasHeaderLabel, deleteTAButton);

        // MAKE THE TABLE AND SETUP THE DATA MODEL
        taTable = new TableView();
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSGData data = (CSGData) app.getDataComponent();
        ObservableList<TeachingAssistant> tableData = data.getTeachingAssistants();
        taTable.setItems(tableData);
        String nameColumnText = props.getProperty(CSGProperty.NAME_COLUMN_TEXT.toString());
        String emailColumnText = props.getProperty(CSGProperty.EMAIL_COLUMN_TEXT.toString());
        String undergradColumnText = props.getProperty(CSGProperty.UNDERGRAD_COLUMN_TEXT.toString());
        undergradColumn = new TableColumn(undergradColumnText);
        undergradColumn.setCellValueFactory(
                new PropertyValueFactory<>("undergrad")
        );
        undergradColumn.setCellFactory(
                new CheckBoxCellFactory()
        );
        undergradColumn.setEditable(true);
        taTable.getColumns().add(undergradColumn);
        nameColumn = new TableColumn(nameColumnText);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        taTable.getColumns().add(nameColumn);
        emailColumn = new TableColumn(emailColumnText);
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );
        taTable.getColumns().add(emailColumn);
        taTable.setEditable(true);
        // ADD BOX FOR ADDING A TA
        String namePromptText = props.getProperty(CSGProperty.NAME_PROMPT_TEXT.toString());
        String addButtonText = props.getProperty(CSGProperty.ADD_BUTTON_TEXT.toString());
        String emailPromptText = props.getProperty(CSGProperty.EMAIL_PROMPT_TEXT.toString());
        nameTextField = new TextField();
        nameTextField.setPromptText(namePromptText);
        addButton = new Button(addButtonText);
        emailTextField = new TextField();
        emailTextField.setPromptText(emailPromptText);
        clearButton = new Button(props.getProperty(CSGProperty.CLEAR_BUTTON_TEXT.toString()));
        addBox = new HBox();
        nameTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.3));
        emailTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.35));
        addButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        clearButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.15));
        addBox.getChildren().add(nameTextField);
        addBox.getChildren().add(emailTextField);
        addBox.getChildren().add(addButton);
        addBox.getChildren().add(clearButton);

        startTime = new ComboBox();
        endTime = new ComboBox();
        String time;
        for (int hour = 0; hour < 24; hour++) {
            if(hour == 0)
                time = "12:00am";
            else
                time = data.getTimeString(hour, true);
            startTime.getItems().add(time);
            endTime.getItems().add(time);
        }
        startT = new Label(props.getProperty(CSGProperty.START_TIME_TEXT.toString()));
        endT = new Label(props.getProperty(CSGProperty.END_TIME_TEXT.toString()));
        HBox rightAlign = new HBox();
        rightAlign.getChildren().add(startT);
        rightAlign.getChildren().add(startTime);
        rightAlign.getChildren().add(endT);
        rightAlign.getChildren().add(endTime);
        
        // INIT THE HEADER ON THE RIGHT
        officeHoursHeaderBox = new BorderPane();
        String officeHoursGridText = props.getProperty(CSGProperty.OFFICE_HOURS_SUBHEADER.toString());
        officeHoursHeaderLabel = new Label(officeHoursGridText);
        officeHoursHeaderBox.setLeft(officeHoursHeaderLabel);
        officeHoursHeaderBox.setRight(rightAlign);
        
        // THESE WILL STORE PANES AND LABELS FOR OUR OFFICE HOURS GRID
        officeHoursGridPane = new GridPane();
        officeHoursGridTimeHeaderPanes = new HashMap();
        officeHoursGridTimeHeaderLabels = new HashMap();
        officeHoursGridDayHeaderPanes = new HashMap();
        officeHoursGridDayHeaderLabels = new HashMap();
        officeHoursGridTimeCellPanes = new HashMap();
        officeHoursGridTimeCellLabels = new HashMap();
        officeHoursGridTACellPanes = new HashMap();
        officeHoursGridTACellLabels = new HashMap();
        
        // ORGANIZE THE LEFT AND RIGHT PANES
        VBox leftPane = new VBox();
        leftPane.getChildren().add(tasHeaderBox);        
        leftPane.getChildren().add(taTable);        
        leftPane.getChildren().add(addBox);
        VBox rightPane = new VBox();
        rightPane.getChildren().add(officeHoursHeaderBox);
        rightPane.getChildren().add(officeHoursGridPane);
        
        // BOTH PANES WILL NOW GO IN A SPLIT PANE
        SplitPane sPane = new SplitPane(leftPane, new ScrollPane(rightPane));
        taDataPane = new BorderPane();
        
        // AND PUT EVERYTHING IN THE TA DATA TAB
        taDataPane.setCenter(sPane);

        // MAKE SURE THE TABLE EXTENDS DOWN FAR ENOUGH
        taTable.prefHeightProperty().bind(taDataPane.heightProperty().multiply(1.9));

        deleteTAButton.setOnAction(e -> {
            controller.handleDeleteTA(KeyCode.DELETE);
        });
        
        // CONTROLS FOR ADDING TAs
        nameTextField.setOnAction(e -> {
            controller.handleAddTA();
        });
        emailTextField.setOnAction(e -> {
            controller.handleAddTA();
        });
        addButton.setOnAction(e -> {
            controller.handleAddTA();
        });
        taTable.setOnKeyPressed(e -> {
            controller.handleDeleteTA(e.getCode());
        });
        taTable.setRowFactory(tableview -> {
            TableRow<TeachingAssistant> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                controller.handleSelectTA();
            });
            return row;
        });
        clearButton.setOnAction(e -> {
           controller.handleClear(); 
        });
        startTime.setOnAction(e -> {
           controller.handleStart(); 
        });
        endTime.setOnAction(e -> {
            controller.handleEnd();
        });
        return taDataPane;
    }
    
    private Pane generateRecitationDataTab() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        recitationHeaderBox = new HBox();
        String recitationHeaderText = props.getProperty(CSGProperty.RECIT_HEADER_TEXT.toString());
        recitationHeaderLabel = new Label(recitationHeaderText);

        String deleteButtonText = props.getProperty(CSGProperty.DELETE_BUTTON_TEXT.toString());
        deleteRecitationButton = new Button(deleteButtonText);
        deleteRecitationButton.prefHeightProperty().bind(recitationHeaderLabel.heightProperty().multiply(1.5));
        recitationHeaderBox.getChildren().addAll(recitationHeaderLabel, deleteRecitationButton);

        recitationTable = new TableView();
        recitationTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSGData data = (CSGData) app.getDataComponent();
        ObservableList<Recitation> tableData = data.getRecitations();
        recitationTable.setItems(tableData);
        String sectionColumnText = props.getProperty(CSGProperty.SECTION_COLUMN_TEXT.toString());
        String instructorColumnText = props.getProperty(CSGProperty.INSTRUCT_COLUMN_TEXT.toString());
        String dayColumnText = props.getProperty(CSGProperty.DAY_COLUMN_TEXT.toString());
        String locationColumnText = props.getProperty(CSGProperty.LOCATION_COLUMN_TEXT.toString());
        String taColumnText = props.getProperty(CSGProperty.TA_COLUMN_TEXT.toString());
        sectionColumn = new TableColumn(sectionColumnText);
        sectionColumn.setCellValueFactory(
                new PropertyValueFactory<>("section")
        );
        recitationTable.getColumns().add(sectionColumn);
        instructorColumn = new TableColumn(instructorColumnText);
        instructorColumn.setCellValueFactory(
                new PropertyValueFactory<>("instructor")
        );
        recitationTable.getColumns().add(instructorColumn);
        dayColumn = new TableColumn(dayColumnText);
        dayColumn.setCellValueFactory(
                new PropertyValueFactory<>("day")
        );
        recitationTable.getColumns().add(dayColumn);
        locationColumn = new TableColumn(locationColumnText);
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location")
        );
        recitationTable.getColumns().add(locationColumn);
        supervisingTAColumnOne = new TableColumn(taColumnText);
        supervisingTAColumnOne.setCellValueFactory(
                new PropertyValueFactory<>("TAOne")
        );
        recitationTable.getColumns().add(supervisingTAColumnOne);
        supervisingTAColumnTwo = new TableColumn(taColumnText);
        supervisingTAColumnTwo.setCellValueFactory(
                new PropertyValueFactory<>("TATwo")
        );
        recitationTable.getColumns().add(supervisingTAColumnTwo);

        addEditHeaderBox = new HBox();
        String addEditLabelText = props.getProperty(CSGProperty.ADDEDIT_LABEL_TEXT.toString());
        addEditHeaderLabel = new Label(addEditLabelText);
        addEditHeaderBox.getChildren().add(addEditHeaderLabel);

        String sectionLabelText = props.getProperty(CSGProperty.SECTION_LABEL_TEXT.toString());
        sectionLabel = new Label(sectionLabelText);
        sectionTextField = new TextField();

        String instructorLabelText = props.getProperty(CSGProperty.INSTRUC_LABEL_TEXT.toString());
        instructorLabel = new Label(instructorLabelText);
        instructorTextField = new TextField();

        String dayLabelText = props.getProperty(CSGProperty.DAY_LABEL_TEXT.toString());
        dayLabel = new Label(dayLabelText);
        dayTextField = new TextField();

        String locationLabelText = props.getProperty(CSGProperty.LOCATION_LABEL_TEXT.toString());
        locationLabel = new Label(locationLabelText);
        locationTextField = new TextField();

        String supervisingLabelText = props.getProperty(CSGProperty.SUPTA_LABEL_TEXT.toString());
        supervisingLabelOne = new Label(supervisingLabelText);
        supervisingLabelTwo = new Label(supervisingLabelText);
        supervisingTAComboBoxOne = new ComboBox();
        supervisingTAComboBoxTwo = new ComboBox();
        supervisingTAComboBoxOne.setItems(data.getTeachingAssistantNames());
        supervisingTAComboBoxTwo.setItems(data.getTeachingAssistantNames());
        
        addRecitationButton = new Button(props.getProperty(CSGProperty.ADD_RECITATION_BUTTON_TEXT.toString()));
        clearRecitationButton = new Button(props.getProperty(CSGProperty.CLEAR_BUTTON_TEXT.toString()));
       
        recitationGridPane = new GridPane();
        recitationGridPane.setPadding(new Insets(10, 10, 10, 10));
        recitationGridPane.setVgap(5);
        recitationGridPane.setHgap(5);
        recitationGridPane.add(recitationHeaderBox, 0, 0);
        recitationGridPane.add(recitationTable, 0, 1);
        recitationGridPane.setHgrow(recitationTable, Priority.ALWAYS);
        
        addEditrecitationGridPane = new GridPane();
        addEditrecitationGridPane.setPadding(new Insets(10, 10, 10, 10));
        addEditrecitationGridPane.setVgap(5);
        addEditrecitationGridPane.setHgap(5);
        addEditrecitationGridPane.add(addEditHeaderBox, 0, 0);
        addEditrecitationGridPane.add(sectionLabel, 0, 1);
        addEditrecitationGridPane.add(sectionTextField, 1, 1);
        addEditrecitationGridPane.add(instructorLabel, 0, 2);
        addEditrecitationGridPane.add(instructorTextField, 1, 2);
        addEditrecitationGridPane.add(dayLabel, 0, 3);
        addEditrecitationGridPane.add(dayTextField, 1, 3);
        addEditrecitationGridPane.add(locationLabel, 0, 4);
        addEditrecitationGridPane.add(locationTextField, 1, 4);
        addEditrecitationGridPane.add(supervisingLabelOne, 0, 5);
        addEditrecitationGridPane.add(supervisingTAComboBoxOne, 1, 5);
        addEditrecitationGridPane.add(supervisingLabelTwo, 0, 6);
        addEditrecitationGridPane.add(supervisingTAComboBoxTwo, 1, 6);
        addEditrecitationGridPane.add(addRecitationButton, 0, 7);
        addEditrecitationGridPane.add(clearRecitationButton, 1, 7);
        
        recitationGridPane.add(addEditrecitationGridPane, 0, 2);
        
        BorderPane temp = new BorderPane();
        temp.setCenter(recitationGridPane);
        
        recitationDataScroll = new ScrollPane();
        recitationDataScroll.setFitToWidth(true);
        recitationDataScroll.setContent(temp);
        
        recitationDataPane = new BorderPane();
        recitationDataPane.setCenter(recitationDataScroll);
        
        // CONTROLS FOR ADDING RECITATIONS
        deleteRecitationButton.setOnAction(e -> {
            controller.handleDeleteRecitation(KeyCode.DELETE);
        });
        sectionTextField.setOnAction(e -> {
            controller.handleAddRecitation();
        });
        instructorTextField.setOnAction(e -> {
            controller.handleAddRecitation();
        });
        dayTextField.setOnAction(e -> {
            controller.handleAddRecitation();
        });
        locationTextField.setOnAction(e -> {
            controller.handleAddRecitation();
        });
        supervisingTAComboBoxOne.setOnAction(e -> {
            controller.handleSuperTA();
        });
        supervisingTAComboBoxTwo.setOnAction(e -> {
            controller.handleSuperTA();
        });
        addRecitationButton.setOnAction(e -> {
            controller.handleAddRecitation();
        });
        recitationTable.setOnKeyPressed(e -> {
            controller.handleDeleteRecitation(e.getCode());
        });
        recitationTable.setRowFactory(tableview -> {
            TableRow<Recitation> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                controller.handleSelectRecitation();
            });
            return row;
        });
        clearRecitationButton.setOnAction(e -> {
           controller.handleClearRecitation(); 
        });
        
        return recitationDataPane;
    }
    
    private Pane generateScheduleDataTab() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData data = (CSGData) app.getDataComponent();
        scheduleHeaderBox = new HBox();
        String scheduleHeaderLabelText = props.getProperty(CSGProperty.SCHEDULE_HEADER_TEXT.toString());
        scheduleHeaderLabel = new Label(scheduleHeaderLabelText);
        scheduleHeaderBox.getChildren().add(scheduleHeaderLabel);

        String calendarBoundariesHeaderLabelText = props.getProperty(CSGProperty.CB_HEADER_TEXT.toString());
        calendarBoundariesHeaderLabel = new Label(calendarBoundariesHeaderLabelText);

        String startingMondayLabelText = props.getProperty(CSGProperty.STMON_LABEL_TEXT.toString());
        startingMondayLabel = new Label(startingMondayLabelText);
        startingMondayPicker = new DatePicker();

        String endingFridayLabelText = props.getProperty(CSGProperty.ENFRI_LABEL_TEXT.toString());
        endingFridayLabel = new Label(endingFridayLabelText);
        endingFridayDatePicker = new DatePicker();
        
        scheduleItemsHeaderBox = new HBox();
        String scheduleItemsHeaderLabelText = props.getProperty(CSGProperty.SCHIT_HEADER_TEXT.toString());
        scheduleItemsHeaderLabel = new Label(scheduleItemsHeaderLabelText);
        deleteScheduleItemButton = new Button(props.getProperty(CSGProperty.DELETE_BUTTON_TEXT.toString()));
        deleteScheduleItemButton.prefHeightProperty().bind(scheduleItemsHeaderLabel.heightProperty().multiply(1.5));
        scheduleItemsHeaderBox.getChildren().addAll(scheduleItemsHeaderLabel, deleteScheduleItemButton);

        scheduleItemTable = new TableView();
        scheduleItemTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<ScheduleItem> tableData = data.getScheduleItems();
        scheduleItemTable.setItems(tableData);
        String typeColumnText = props.getProperty(CSGProperty.TYPE_COLUMN_TEXT.toString());
        String dateColumnText = props.getProperty(CSGProperty.DATE_COLUMN_TEXT.toString());
        String titleColumnText = props.getProperty(CSGProperty.TITLE_COLUMN_TEXT.toString());
        String topicColumnText = props.getProperty(CSGProperty.TOPIC_COLUMN_TEXT.toString());
        typeColumn = new TableColumn(typeColumnText);
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        scheduleItemTable.getColumns().add(typeColumn);
        dateColumn = new TableColumn(dateColumnText);
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        scheduleItemTable.getColumns().add(dateColumn);
        titleColumn = new TableColumn(titleColumnText);
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        scheduleItemTable.getColumns().add(titleColumn);
        topicColumn = new TableColumn(topicColumnText);
        topicColumn.setCellValueFactory(
                new PropertyValueFactory<>("topic")
        );
        scheduleItemTable.getColumns().add(topicColumn);

        String addEditScheduleHeaderLabelText = props.getProperty(CSGProperty.ADDEDIT_LABEL_TEXT.toString());
        addEditScheduleHeaderLabel = new Label(addEditScheduleHeaderLabelText);

        String typeLabelText = props.getProperty(CSGProperty.TYPE_LABEL_TEXT.toString());
        typeLabel = new Label(typeLabelText);
        typeComboBox = new ComboBox();
        typeComboBox.setItems(data.getTypes());

        String dateLabelText = props.getProperty(CSGProperty.DATE_LABEL_TEXT.toString());
        dateLabel = new Label(dateLabelText);
        dateSchedulePicker = new DatePicker();

        String timeLabelText = props.getProperty(CSGProperty.TIME_LABEL_TEXT.toString());
        timeLabel = new Label(timeLabelText);
        timeTextField = new TextField();

        String titleScheduleLabelText = props.getProperty(CSGProperty.TITLE_LABEL_TEXT.toString());
        titleScheduleLabel = new Label(titleScheduleLabelText);
        titleScheduleTextField = new TextField();

        String topicLabelText = props.getProperty(CSGProperty.TOPIC_LABEL_TEXT.toString());
        topicLabel = new Label(topicLabelText);
        topicTextField = new TextField();

        String linkLabelText = props.getProperty(CSGProperty.LINK_LABEL_TEXT.toString());
        linkLabel = new Label(linkLabelText);
        linkTextField = new TextField();

        String criteriaLabelText = props.getProperty(CSGProperty.CRIT_LABEL_TEXT.toString());
        criteriaLabel = new Label(criteriaLabelText);
        criteriaTextField = new TextField();
        
        addScheduleItemButton = new Button(props.getProperty(CSGProperty.ADD_SCHEDULE_BUTTON_TEXT.toString()));
        clearScheduleItemButton = new Button(props.getProperty(CSGProperty.CLEAR_BUTTON_TEXT.toString()));
        
        scheduleGridPane = new GridPane();
        scheduleGridPane.setVgap(5);
        scheduleGridPane.setPadding(new Insets(10, 10, 10, 10));
        scheduleGridPane.add(scheduleHeaderBox, 0, 0);
        scheduleGridPane.setHgrow(scheduleHeaderBox, Priority.ALWAYS);
        
        calendarBoundariesGridPane = new GridPane();
        calendarBoundariesGridPane.setPadding(new Insets(10, 10, 10, 10));
        calendarBoundariesGridPane.setVgap(5);
        calendarBoundariesGridPane.setHgap(5);
        calendarBoundariesGridPane.add(calendarBoundariesHeaderLabel, 0, 0);
        calendarBoundariesGridPane.add(startingMondayLabel, 0, 1);
        calendarBoundariesGridPane.add(startingMondayPicker, 1, 1);
        calendarBoundariesGridPane.add(endingFridayLabel, 3, 1);
        calendarBoundariesGridPane.add(endingFridayDatePicker, 4, 1);
        
        scheduleGridPane.add(calendarBoundariesGridPane, 0, 1);
        
        scheduleItemsGridPane = new GridPane();
        scheduleItemsGridPane.setPadding(new Insets(10, 10, 10, 10));
        scheduleItemsGridPane.setVgap(5);
        scheduleItemsGridPane.add(scheduleItemsHeaderBox, 0, 0);
        scheduleItemsGridPane.add(scheduleItemTable, 0, 1);
        scheduleItemsGridPane.setHgrow(scheduleItemTable, Priority.ALWAYS);
        
        scheduleGridPane.add(scheduleItemsGridPane, 0, 2);
        
        addScheduleGridPane = new GridPane();
        addScheduleGridPane.setPadding(new Insets(10, 10, 10, 10));
        addScheduleGridPane.setVgap(5);
        addScheduleGridPane.setHgap(5);
        addScheduleGridPane.add(addEditScheduleHeaderLabel, 0, 1);
        addScheduleGridPane.add(typeLabel, 0, 2);
        addScheduleGridPane.add(typeComboBox, 1, 2);
        addScheduleGridPane.add(dateLabel, 0, 3);
        addScheduleGridPane.add(dateSchedulePicker, 1, 3);
        addScheduleGridPane.add(timeLabel, 0, 4);
        addScheduleGridPane.add(timeTextField, 1, 4);
        addScheduleGridPane.add(titleScheduleLabel, 0, 5);
        addScheduleGridPane.add(titleScheduleTextField, 1, 5);
        addScheduleGridPane.add(topicLabel, 0, 6);
        addScheduleGridPane.add(topicTextField, 1, 6);
        addScheduleGridPane.add(linkLabel, 0, 7);
        addScheduleGridPane.add(linkTextField, 1, 7);
        addScheduleGridPane.add(criteriaLabel, 0, 8);
        addScheduleGridPane.add(criteriaTextField, 1, 8);
        addScheduleGridPane.add(addScheduleItemButton, 0, 9);
        addScheduleGridPane.add(clearScheduleItemButton, 1, 9);
        
        scheduleGridPane.add(addScheduleGridPane, 0, 3);
        
        BorderPane temp = new BorderPane();
        temp.setTop(scheduleGridPane);
        
        scheduleDataScroll = new ScrollPane();
        scheduleDataScroll.setFitToWidth(true);
        scheduleDataScroll.setContent(temp);
        
        scheduleDataPane = new BorderPane();
        scheduleDataPane.setCenter(scheduleDataScroll);
        
        deleteScheduleItemButton.setOnAction(e -> {
            controller.handleDeleteScheduleItem(KeyCode.DELETE);
        });
        startingMondayPicker.setOnAction(e -> {
            controller.handleStartingMonday();
        });
        endingFridayDatePicker.setOnAction(e -> {
            controller.handleEndingFriday();
        });
        addScheduleItemButton.setOnAction(e -> {
            controller.handleAddScheduleItem();
        });
        scheduleItemTable.setOnKeyPressed(e -> {
            controller.handleDeleteScheduleItem(e.getCode());
        });
        scheduleItemTable.setRowFactory(tableview -> {
            TableRow<ScheduleItem> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                controller.handleSelectScheduleItem();
            });
            return row;
        });
        clearScheduleItemButton.setOnAction(e -> {
           controller.handleClearScheduleItem(); 
        });
        
        return scheduleDataPane;
    }
    
    private Pane generateProjectDataTab() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        projectHeaderBox = new HBox();
        String projectHeaderLabelText = props.getProperty(CSGProperty.PROJECT_HEADER_TEXT.toString());
        projectHeaderLabel = new Label(projectHeaderLabelText);
        projectHeaderBox.getChildren().add(projectHeaderLabel);

        teamsHeaderBox = new HBox();
        String teamsHeaderLabelText = props.getProperty(CSGProperty.TEAM_HEADER_TEXT.toString());
        teamsHeaderLabel = new Label(teamsHeaderLabelText);
        teamsDeleteButton = new Button(props.getProperty(CSGProperty.DELETE_BUTTON_TEXT.toString()));
        teamsDeleteButton.prefHeightProperty().bind(teamsHeaderLabel.heightProperty().multiply(1.5));
        teamsHeaderBox.getChildren().addAll(teamsHeaderLabel, teamsDeleteButton);
        
        teamTable = new TableView();
        teamTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSGData data = (CSGData) app.getDataComponent();
        ObservableList<Team> tableData = data.getTeams();
        teamTable.setItems(tableData);
        String nameColumnText = props.getProperty(CSGProperty.NAME_COLUMN_TEXT.toString());
        String colorColumnText = props.getProperty(CSGProperty.COLOR_COLUMN_TEXT.toString());
        String textColorColumnText = props.getProperty(CSGProperty.TEXTCOL_COLUMN_TEXT.toString());
        String linkColumnText = props.getProperty(CSGProperty.LINK_COLUMN_TEXT.toString());
        nameTeamColumn = new TableColumn(nameColumnText);
        nameTeamColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        teamTable.getColumns().add(nameTeamColumn);
        colorColumn = new TableColumn(colorColumnText);
        colorColumn.setCellValueFactory(
                new PropertyValueFactory<>("color")
        );
        teamTable.getColumns().add(colorColumn);
        textColorColumn = new TableColumn(textColorColumnText);
        textColorColumn.setCellValueFactory(
                new PropertyValueFactory<>("textColor")
        );
        teamTable.getColumns().add(textColorColumn);
        linkColumn = new TableColumn(linkColumnText);
        linkColumn.setCellValueFactory(
                new PropertyValueFactory<>("link")
        );
        teamTable.getColumns().add(linkColumn);

        String addTeamHeaderText = props.getProperty(CSGProperty.ADDEDIT_LABEL_TEXT.toString());
        addTeamHeader = new Label(addTeamHeaderText);

        String nameLabelText = props.getProperty(CSGProperty.NAME_LABEL_TEXT.toString());
        nameLabel = new Label(nameLabelText);
        nameTeamTextField = new TextField();

        String colorLabelText = props.getProperty(CSGProperty.COLOR_LABEL_TEXT.toString());
        colorLabel = new Label(colorLabelText);
        colorPicker = new ColorPicker();

        String textColorLabelText = props.getProperty(CSGProperty.TEXTCOL_LABEL_TEXT.toString());
        textColorLabel = new Label(textColorLabelText);
        textColorPicker = new ColorPicker();

        String linkTeamLabelText = props.getProperty(CSGProperty.LINK_LABEL_TEXT.toString());
        linkTeamLabel = new Label(linkTeamLabelText);
        linkTeamTextField = new TextField();

        addTeamButton = new Button(props.getProperty(CSGProperty.ADD_TEAM_BUTTON_TEXT.toString()));
        clearTeamButton = new Button(props.getProperty(CSGProperty.CLEAR_BUTTON_TEXT.toString()));

        studentsHeaderBox = new HBox();
        String studentsHeaderLabelText = props.getProperty(CSGProperty.STUDENT_HEADER_TEXT.toString());
        studentsHeaderLabel = new Label(studentsHeaderLabelText);
        studentsDeleteButton = new Button(props.getProperty(CSGProperty.DELETE_BUTTON_TEXT.toString()));
        studentsDeleteButton.prefHeightProperty().bind(studentsHeaderLabel.heightProperty().multiply(1.5));
        studentsHeaderBox.getChildren().addAll(studentsHeaderLabel, studentsDeleteButton);

        studentTable = new TableView();
        studentTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Student> tableDataS = data.getStudents();
        studentTable.setItems(tableDataS);
        String firstNameColumnText = props.getProperty(CSGProperty.FIRSTN_COLUMN_TEXT.toString());
        String lastNameColumnText = props.getProperty(CSGProperty.LASTN_COLUMN_TEXT.toString());
        String teamColumnText = props.getProperty(CSGProperty.TEAM_COLUMN_TEXT.toString());
        String roleColumnText = props.getProperty(CSGProperty.LINK_COLUMN_TEXT.toString());
        firstNameColumn = new TableColumn(firstNameColumnText);
        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        studentTable.getColumns().add(firstNameColumn);
        lastNameColumn = new TableColumn(lastNameColumnText);
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        studentTable.getColumns().add(lastNameColumn);
        teamColumn = new TableColumn(teamColumnText);
        teamColumn.setCellValueFactory(
                new PropertyValueFactory<>("team")
        );
        studentTable.getColumns().add(teamColumn);
        roleColumn = new TableColumn(roleColumnText);
        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role")
        );
        studentTable.getColumns().add(roleColumn);

        String addStudentHeaderText = props.getProperty(CSGProperty.ADDEDIT_LABEL_TEXT.toString());
        addStudentHeader = new Label(addStudentHeaderText);

        String firstNameLabelText = props.getProperty(CSGProperty.FIRSTN_LABEL_TEXT.toString());
        firstNameLabel = new Label(firstNameLabelText);
        firstNameTextField = new TextField();

        String lastNameLabelText = props.getProperty(CSGProperty.LASTN_LABEL_TEXT.toString());
        lastNameLabel = new Label(lastNameLabelText);
        lastNameTextField = new TextField();

        String teamLabelText = props.getProperty(CSGProperty.TEAM_LABEL_TEXT.toString());
        teamLabel = new Label(teamLabelText);
        teamComboBox = new ComboBox();
        teamComboBox.setItems(data.getTeamNames());
                
        String roleLabelText = props.getProperty(CSGProperty.ROLE_LABEL_TEXT.toString());
        roleLabel = new Label(roleLabelText);
        roleTextField = new TextField();
        
        addStudentButton = new Button(props.getProperty(CSGProperty.ADD_STUDENT_BUTTON_TEXT.toString()));
        clearStudentButton = new Button(props.getProperty(CSGProperty.CLEAR_BUTTON_TEXT.toString()));
        
        projectsGridPane = new GridPane();
        projectsGridPane.setPadding(new Insets(10, 10, 10, 10));
        projectsGridPane.setVgap(5);
        projectsGridPane.add(projectHeaderBox, 0, 0);
        projectsGridPane.setHgrow(projectHeaderBox, Priority.ALWAYS);
        
        teamsGridPane = new GridPane();
        teamsGridPane.setPadding(new Insets(10, 10, 10, 10));
        teamsGridPane.setVgap(5);
        teamsGridPane.add(teamsHeaderBox, 0, 0);
        teamsGridPane.add(teamTable, 0, 1);
        teamsGridPane.setHgrow(teamTable, Priority.ALWAYS);
        
        projectsGridPane.add(teamsGridPane, 0, 1);
        
        addTeamsGridPane = new GridPane();
        addTeamsGridPane.setPadding(new Insets(10, 10, 10, 10));
        addTeamsGridPane.setVgap(5);
        addTeamsGridPane.setHgap(5);
        addTeamsGridPane.add(addTeamHeader, 0, 0);
        addTeamsGridPane.add(nameLabel, 0, 1);
        addTeamsGridPane.add(nameTeamTextField, 1, 1);
        addTeamsGridPane.add(colorLabel, 0, 2);
        addTeamsGridPane.add(colorPicker, 1, 2);
        addTeamsGridPane.add(textColorLabel, 2, 2);
        addTeamsGridPane.add(textColorPicker, 3, 2);
        addTeamsGridPane.add(linkTeamLabel, 0, 3);
        addTeamsGridPane.add(linkTeamTextField, 1, 3);
        addTeamsGridPane.add(addTeamButton, 0, 4);
        addTeamsGridPane.add(clearTeamButton, 1, 4);
        
        projectsGridPane.add(addTeamsGridPane, 0, 2);
        
        studentsGridPane = new GridPane();
        studentsGridPane.setPadding(new Insets(10, 10, 10, 10));
        studentsGridPane.setVgap(5);
        studentsGridPane.add(studentsHeaderBox, 0, 0);
        studentsGridPane.add(studentTable, 0, 1);
        studentsGridPane.setHgrow(studentTable, Priority.ALWAYS);
        
        projectsGridPane.add(studentsGridPane, 0, 3);
        
        addStudentsGridPane = new GridPane();
        addStudentsGridPane.setPadding(new Insets(10, 10, 10, 10));
        addStudentsGridPane.setVgap(5);
        addStudentsGridPane.setHgap(5);
        addStudentsGridPane.add(addStudentHeader, 0, 0);
        addStudentsGridPane.add(firstNameLabel, 0, 1);
        addStudentsGridPane.add(firstNameTextField, 1, 1);
        addStudentsGridPane.add(lastNameLabel, 0, 2);
        addStudentsGridPane.add(lastNameTextField, 1, 2);
        addStudentsGridPane.add(teamLabel, 0, 3);
        addStudentsGridPane.add(teamComboBox, 1, 3);
        addStudentsGridPane.add(roleLabel, 0, 4);
        addStudentsGridPane.add(roleTextField, 1, 4);
        addStudentsGridPane.add(addStudentButton, 0, 5);
        addStudentsGridPane.add(clearStudentButton, 1, 5);
        
        projectsGridPane.add(addStudentsGridPane, 0, 4);
        
        BorderPane tempA = new BorderPane();
        tempA.setTop(projectsGridPane);
        
        projectDataScroll = new ScrollPane();
        projectDataScroll.setContent(tempA);
        projectDataScroll.setFitToWidth(true);
        
        projectDataPane = new BorderPane();
        projectDataPane.setCenter(projectDataScroll);
        
        teamsDeleteButton.setOnAction(e -> {
            controller.handleDeleteTeam(KeyCode.DELETE);
        });
        addTeamButton.setOnAction(e -> {
            controller.handleAddTeam();
        });
        teamTable.setOnKeyPressed(e -> {
            controller.handleDeleteTeam(e.getCode());
        });
        teamTable.setRowFactory(tableview -> {
            TableRow<Team> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                controller.handleSelectTeam();
            });
            return row;
        });
        clearTeamButton.setOnAction(e -> {
           controller.handleClearTeam(); 
        });
        
        studentsDeleteButton.setOnAction(e -> {
            controller.handleDeleteStudent(KeyCode.DELETE);
        });
        addStudentButton.setOnAction(e -> {
            controller.handleAddStudent();
        });
        studentTable.setOnKeyPressed(e -> {
            controller.handleDeleteStudent(e.getCode());
        });
        studentTable.setRowFactory(tableview -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                controller.handleSelectStudent();
            });
            return row;
        });
        clearStudentButton.setOnAction(e -> {
           controller.handleClearStudent(); 
        });
        
        return projectDataPane;
    }
    
    public HBox getTAsHeaderBox() {
        return tasHeaderBox;
    }

    public Label getTAsHeaderLabel() {
        return tasHeaderLabel;
    }

    public TableView getTATable() {
        return taTable;
    }

    public HBox getAddBox() {
        return addBox;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }
    
    public TextField getEmailTextField() {
        return emailTextField;
    }

    public Button getAddButton() {
        return addButton;
    }
    
    public Button getClearButton() {
        return clearButton;
    }
    
    public jTPS getjTPS() {
        return transactions;
    }

    public BorderPane getOfficeHoursSubheaderBox() {
        return officeHoursHeaderBox;
    }

    public Label getOfficeHoursSubheaderLabel() {
        return officeHoursHeaderLabel;
    }
    
    public Label getStartTimeLabel() {
        return startT;
    }
    
    public ComboBox getStartTimeComboBox() {
        return startTime;
    }
    
    public Label getEndTimeLabel() {
        return endT;
    }
    
    public ComboBox getEndTimeComboBox() {
        return endTime;
    }

    public GridPane getOfficeHoursGridPane() {
        return officeHoursGridPane;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeHeaderPanes() {
        return officeHoursGridTimeHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeHeaderLabels() {
        return officeHoursGridTimeHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridDayHeaderPanes() {
        return officeHoursGridDayHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridDayHeaderLabels() {
        return officeHoursGridDayHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeCellPanes() {
        return officeHoursGridTimeCellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeCellLabels() {
        return officeHoursGridTimeCellLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTACellPanes() {
        return officeHoursGridTACellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTACellLabels() {
        return officeHoursGridTACellLabels;
    }
    
    public String getCellKey(Pane testPane) {
        for (String key : officeHoursGridTACellLabels.keySet()) {
            if (officeHoursGridTACellPanes.get(key) == testPane) {
                return key;
            }
        }
        return null;
    }

    public Label getTACellLabel(String cellKey) {
        return officeHoursGridTACellLabels.get(cellKey);
    }

    public Pane getTACellPane(String cellPane) {
        return officeHoursGridTACellPanes.get(cellPane);
    }

    public String buildCellKey(int col, int row) {
        return "" + col + "_" + row;
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

    @Override
    public void resetWorkspace() {
        // CLEAR OUT THE GRID PANE
        officeHoursGridPane.getChildren().clear();
        
        // AND THEN ALL THE GRID PANES AND LABELS
        officeHoursGridTimeHeaderPanes.clear();
        officeHoursGridTimeHeaderLabels.clear();
        officeHoursGridDayHeaderPanes.clear();
        officeHoursGridDayHeaderLabels.clear();
        officeHoursGridTimeCellPanes.clear();
        officeHoursGridTimeCellLabels.clear();
        officeHoursGridTACellPanes.clear();
        officeHoursGridTACellLabels.clear();
    }
    
    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        CSGData data = (CSGData)dataComponent;
        reloadOfficeHoursGrid(data);
    }

    public void reloadOfficeHoursGrid(CSGData dataComponent) {        
        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();

        // ADD THE TIME HEADERS
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
        
        // THEN THE TIME AND TA CELLS
        int row = 1;
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) {
            // START TIME COLUMN
            int col = 0;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

            // END TIME COLUMN
            col++;
            int endHour = i;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
            col++;

            // AND NOW ALL THE TA TOGGLE CELLS
            while (col < 7) {
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);
                col++;
            }
            row += 2;
        }

        // CONTROLS FOR TOGGLING TA OFFICE HOURS
        for (Pane p : officeHoursGridTACellPanes.values()) {
            p.setOnMouseClicked(e -> {
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
               controller.handleHover((Pane) e.getSource(), "yellow", "#fff77f", "1.3"); 
            });
            p.setOnMouseExited(e -> {
                controller.handleHover((Pane) e.getSource(), "black", "black", "1");
            });
        }
        
        // AND MAKE SURE ALL THE COMPONENTS HAVE THE PROPER STYLE
        CSGStyle csgStyle = (CSGStyle)app.getStyleComponent();
        csgStyle.initOfficeHoursGridStyle();
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
        
        // NOW PUT THE CELL IN THE WORKSPACE GRID
        officeHoursGridPane.add(cellPane, col, row);
        
        // AND ALSO KEEP IN IN CASE WE NEED TO STYLIZE IT
        panes.put(cellKey, cellPane);
        labels.put(cellKey, cellLabel);
        
        // AND FINALLY, GIVE THE TEXT PROPERTY TO THE DATA MANAGER
        // SO IT CAN MANAGE ALL CHANGES
        dataComponent.setCellProperty(col, row, cellLabel.textProperty());        
    }
    
    public GridPane getCourseInfoGridPane() {
        return courseInfoGridPane;
    }
    
    public GridPane getSiteTemplateGridPane() {
        return siteTemplateGridPane;
    }
    
    public GridPane getPageStyleGridPane() {
        return pageStyleGridPane;
    }
    
    public GridPane getAddEditrecitationGridPane() {
        return addEditrecitationGridPane;
    }
    
    public GridPane getCalendarBoundariesGridPane() {
        return calendarBoundariesGridPane;
    }
    
    public GridPane getScheduleItemsGridPane() {
        return scheduleItemsGridPane;
    }
    
    public GridPane getAddScheduleGridPane() {
        return addScheduleGridPane;
    }
    
    public GridPane getTeamsGridPane() {
        return teamsGridPane;
    }
    
    public GridPane getAddTeamsGridPane() {
        return addTeamsGridPane;
    }
    
    public GridPane getStudentsGridPane() {
        return studentsGridPane;
    }
    
    public GridPane getAddStudentsGridPane() {
        return addStudentsGridPane;
    }
    
    public Pane getRecitationsHeaderPane() {
        return recitationHeaderBox;
    }
    
    public Pane getScheduleHeaderPane() {
        return scheduleHeaderBox;
    }
    
    public Pane getProjectHeaderPane() {
        return projectHeaderBox;
    }
    
    public Label getRecitationsHeaderLabel() {
        return recitationHeaderLabel;
    }
    
    public Label getScheduleHeaderLabel() {
        return scheduleHeaderLabel;
    }
    
    public Label getProjectHeaderLabel() {
        return projectHeaderLabel;
    }
    
    public Pane getTeamsHeaderPane() {
        return teamsHeaderBox;
    }
    
    public Label getTeamsHeaderLabel() {
        return teamsHeaderLabel;
    }
    
    public Pane getStudentsHeaderPane() {
        return studentsHeaderBox;
    }
    
    public Label getStudentsHeaderLabel() {
        return studentsHeaderLabel;
    }
    
    public Pane getCourseInfoHeaderPane() {
        return courseInfoHeaderBox;
    }
    
    public Label getCourseInfoHeaderLabel() {
        return ciHeaderLabel;
    }
    
    public Pane getSiteTemplateHeaderPane() {
        return siteTemplateHeaderBox;
    }
    
    public Label getSiteTemplateHeaderLabel() {
        return siteTemplateHeaderLabel;
    }
    
    public Pane getPageStyleHeaderPane() {
        return pageStyleHeaderBox;
    }
    
    public Label getPageStyleHeaderLabel() {
        return pageStyleHeaderLabel;
    }
    
    public Label getCalendarBoundariesLabel() {
        return calendarBoundariesHeaderLabel;
    }
    
    public Pane getScheduleItemsHeaderPane() {
        return scheduleItemsHeaderBox;
    }
    
    public Label getScheduleItemsHeaderLabel() {
        return scheduleItemsHeaderLabel;
    }
    
    public TableView<SitePage> getSiteTable() {
        return siteTable;
    }
    
    public TableView<Recitation> getRecitationTable() {
        return recitationTable;
    }
    
    public TableView<ScheduleItem> getScheduleItemTable() {
        return scheduleItemTable;
    }
    
    public TableView<Team> getTeamTable() {
        return teamTable;
    }
    
    public TableView<Student> getStudentTable() {
        return studentTable;
    }
    
    public Label getSubjectLabel() {
        return subjectLabel;
    }
    
    public Label getNumberLabel() {
        return numberLabel;
    }
    
    public Label getSemesterLabel() {
        return semesterLabel;
    }
    
    public Label getYearLabel() {
        return yearLabel;
    }
    
    public Label getTitleLabel() {
        return titleLabel;
    }
    
    public Label getInstructorNameLabel() {
        return instructorNameLabel;
    }
    
    public Label getInstructorHomeLabel() {
        return instructorHomeLabel;
    }
    
    public Label getExportDirLabel() {
        return exportDirLabel;
    }
    
    public Label getExportDirPathLabel() {
        return exportDirPathLabel;
    }
    
    public Label getSiteTemplateInfoLabel() {
        return siteTemplateInfoLabel;
    }
    
    public Label getSelectTemplateDirPathLabel() {
        return selectTemplateDirPathLabel;
    }
    
    public Label getSitePagesLabel() {
        return sitePagesHeaderLabel;
    }
    
    public Label getBannerSchoolImgLabel() {
        return bannerSchoolImgLabel;
    }
    
    public Label getLeftFooterImgLabel() {
        return leftFooterImgLabel;
    }
    
    public Label getRightFooterImgLabel() {
        return rightFooterImgLabel;
    }
    
    public Label getStylesheetLabel() {
        return stylesheetLabel;
    }
    
    public Label getSsNoteLabel() {
        return ssNoteLabel;
    }
    
    public Label getSectionLabel() {
        return sectionLabel;
    }
    
    public Label getInstructorLabel() {
        return instructorLabel;
    }
    
    public Label getDayLabel() {
        return dayLabel;
    }
    
    public Label getLocationLabel() {
        return locationLabel;
    }
    
    public Label getSupervisingTALabelOne() {
        return supervisingLabelOne;
    }
    
    public Label getSupervisingTALabelTwo() {
        return supervisingLabelTwo;
    }
    
    public Label getStartingMondayLabel() {
        return startingMondayLabel;
    }
    
    public Label getEndingFridayLabel() {
        return endingFridayLabel;
    }
    
    public Label getTypeLabel() {
        return typeLabel;
    }
    
    public Label getDateLabel() {
        return dateLabel;
    }
    
    public Label getTimeLabel() {
        return timeLabel;
    }
    
    public Label getTitleScheduleLabel() {
        return titleScheduleLabel;
    }
    
    public Label getTopicLabel() {
        return topicLabel;
    }
    
    public Label getLinkLabel() {
        return linkLabel;
    }
    
    public Label getCriteriaLabel() {
        return criteriaLabel;
    }
    
    public Label getNameTeamLabel() {
        return nameLabel;
    }
    
    public Label getColorLabel() {
        return colorLabel;
    }
    
    public Label getTextColorLabel() {
        return textColorLabel;
    }
    
    public Label getLinkTeamLabel() {
        return linkTeamLabel;
    }
    
    public Label getFirstNameLabel() {
        return firstNameLabel;
    }
    
    public Label getLastNameLabel() {
        return lastNameLabel;
    }
    
    public Label getTeamLabel() {
        return teamLabel;
    }
    
    public Label getRoleLabel() {
        return roleLabel;
    }
    
    public Label getAddEditRecitationHeaderLabelLabel() {
        return addEditHeaderLabel;
    }
    
    public Label getAddEditScheduleHeaderLabelLabel() {
        return addEditScheduleHeaderLabel;
    }
    
    public Label getAddStudentHeaderLabel() {
        return addStudentHeader;
    }
    
    public Label getAddTeamHeaderLabel() {
        return addTeamHeader;
    }
    
    public Button getChangeExportDirButton() {
        return changeExportDirButton;
    }
    
    public Button getSelectTemplateDirectoryButton() {
        return selectTemplateDirButton;
    }
    
    public Button getChangeBannerButton() {
        return changeBannerButton;
    }
    
    public Button getChangeRightFooterButton() {
        return changeRightFooterButton;
    }
    
    public Button getChangeLeftFooterButton() {
        return changeLeftFooterButton;
    }
    
    public Button getAddRecitationButton() {
        return addRecitationButton;
    }
    
    public Button getClearRecitationButton() {
        return clearRecitationButton;
    }
    
    public Button getAddScheduleItemButton() {
        return addScheduleItemButton;
    }
    
    public Button getClearScheduleItemButton() {
        return clearScheduleItemButton;
    }
    
    public Button getAddTeamButton() {
        return addTeamButton;
    }
    
    public Button getClearTeamButton() {
        return clearTeamButton;
    }
    
    public Button getAddStudentButton() {
        return addStudentButton;
    }
    
    public Button getClearStudentButton() {
        return clearStudentButton;
    }
    
    public TextField getTitleTextField() {
        return titleTextField;
    }
    
    public TextField getInstructorNameTextField() {
        return instructorNameTextField;
    }
    
    public TextField getInstructorHomeTextField() {
        return instructorHomeTextField;
    }
    
    public TextField getSectionTextField() {
        return sectionTextField;
    }
    
    public TextField getInstructorTextField() {
        return instructorTextField;
    }
    
    public TextField getDayTextField() {
        return dayTextField;
    }
    
    public TextField getLocationTextField() {
        return locationTextField;
    }
    
    public TextField getTimeTextField() {
        return timeTextField;
    }
    
    public TextField getTitleScheduleTextField() {
        return titleScheduleTextField;
    }
    
    public TextField getTopicTextField() {
        return topicTextField;
    }
    
    public TextField getLinkTextField() {
        return linkTextField;
    }
    
    public TextField getCriteriaTextField() {
        return criteriaTextField;
    }
    
    public TextField getNameTeamTextField() {
        return nameTeamTextField;
    }
    
    public TextField getLinkTeamTextField() {
        return linkTeamTextField;
    }
    
    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }
    
    public TextField getLastNameTextField() {
        return lastNameTextField;
    }
    
    public TextField getRoleTextField() {
        return roleTextField;
    }
    
    public ComboBox getSubjectComboBox() {
        return subjectComboBox;
    }
    
    public ComboBox getNumberComboBox() {
        return numberComboBox;
    }
    
    public ComboBox getSemesterComboBox() {
        return semesterComboBox;
    }
    
    public ComboBox getYearComboBox() {
        return yearComboBox;
    }
    
    public ComboBox getStylesheetComboBox() {
        return stylesheetComboBox;
    }
    
    public ComboBox getSupervisingTAComboBoxOne() {
        return supervisingTAComboBoxOne;
    }
    
    public ComboBox getSupervisingTAComboBoxTwo() {
        return supervisingTAComboBoxTwo;
    }
    
    public Label getBannerImgPath() {
        return bannerImgPath;
    }
    
    public Label getLeftFooterImgPath() {
        return leftFooterImgPath;
    }
    
    public Label getRightFooterImgPath() {
        return rightFooterImgPath;
    }
    
    public DatePicker getStartingMondayPicker() {
        return startingMondayPicker;
    }
    
    public DatePicker getEndingFridayPicker() {
        return endingFridayDatePicker;
    }
    
    public ComboBox getTypeComboBox() {
        return typeComboBox;
    }
    
    public DatePicker getDateSchedulePicker() {
        return dateSchedulePicker;
    }
    
    public ColorPicker getColorPicker() {
        return colorPicker;
    }
    
    public ColorPicker getTextColorPicker() {
        return textColorPicker;
    }
    
    public ComboBox getTeamComboBox() {
        return teamComboBox;
    }
    
    public ImageView getBannerImgView() {
        return bannerSchoolImgView;
    }
    
    public ImageView getLeftFooterImgView() {
        return leftFooterImgView;
    }
    
    public ImageView getRightFooterImgView() {
        return rightFooterImgView;
    }
    
    public class CheckBoxCellFactory<T> implements Callback {
        @Override
        public TableCell call(Object param) {
            CheckBoxTableCell<T,Boolean> checkBoxCell = new CheckBoxTableCell();
            return checkBoxCell;
        }
    }
}
