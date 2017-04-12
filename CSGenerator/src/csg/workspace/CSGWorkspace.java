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
import csg.data.SitePage;
import csg.data.TeachingAssistant;
import csg.style.CSGStyle;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    BorderPane courseDetailsPane;
    Tab taDataTab;
    BorderPane taDataPane;
    Tab recitationDataTab;
    BorderPane recitationDataPane;
    Tab scheduleDataTab;
    Tab projectDataTab;
    
    // FOR COURSE DETAILS TAB
    VBox courseInfoBox;
    
    HBox courseInfoHeaderBox;
    Label ciHeaderLabel;
    
    HBox subjectBox;
    Label subjectLabel;
    ComboBox subjectComboBox;
    
    HBox numberBox;
    Label numberLabel;
    ComboBox numberComboBox;
    
    HBox semesterBox;
    Label semesterLabel;
    ComboBox semesterComboBox;
    
    HBox yearBox;
    Label yearLabel;
    ComboBox yearComboBox;
    
    HBox titleBox;
    Label titleLabel;
    TextField titleTextField;
    
    HBox instructorNameBox;
    Label instructorNameLabel;
    TextField instructorNameTextField;
    
    HBox instructorHomeBox;
    Label instructorHomeLabel;
    TextField instructorHomeTextField;
    
    HBox exportDirBox;
    Label exportDirLabel;
    Button changeExportDirButton;
    
    VBox siteTemplateBox;
    
    HBox siteTemplateHeaderBox;
    Label siteTemplateHeaderLabel;
    
    HBox siteTemplateInfoBox;
    Label siteTemplateInfoLabel;
    
    Button selectTemplateDirButton;
    
    HBox sitePagesBox;
    Label sitePagesHeaderLabel;
    
    TableView<SitePage> siteTable;
    TableColumn<SitePage, String> useColumn;
    TableColumn<SitePage, String> navbarColumn;
    TableColumn<SitePage, String> fileColumn;
    TableColumn<SitePage, String> scriptColumn;
    
    VBox pageStyleBox;
    
    HBox pageStyleHeaderBox;
    Label pageStyleHeaderLabel;
    
    HBox bannerSchoolImgBox;
    Label bannerSchoolImgLabel;
    Button changeBannerButton;
    
    HBox leftFooterImgBox;
    Label leftFooterImgLabel;
    Button changeLeftFooterButton;
    
    HBox rightFooterImgBox;
    Label rightFooterImgLabel;
    Button changeRightFooterButton;
    
    HBox stylesheetBox;
    Label stylesheetLabel;
    ComboBox stylesheetComboBox;
    Label ssNoteLabel;
    
    // FOR TA DATA TAB
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
    Button deleteTAButton;
    
    TableView<TeachingAssistant> taTable;
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
    
    
    public CSGWorkspace(CSGeneratorApp initApp) {
        app = initApp;
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        tabs = new TabPane();
        
        courseDetailsTab = new Tab();
        courseDetailsTab.setText(props.getProperty(CSGProperty.COURSEDETAILS_TAB_TEXT.toString()));
        courseDetailsTab.setContent(generateCourseDetailsTab());
        
        taDataTab = new Tab();
        taDataTab.setText(props.getProperty(CSGProperty.TADATA_TAB_TEXT.toString()));
        taDataTab.setContent(generateCSGDataTab());
        
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
        ((BorderPane) workspace).setCenter(tabs);
    }
    
    private Pane generateCourseDetailsTab() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        courseInfoHeaderBox = new HBox();
        String courseInfoHeaderText = props.getProperty(CSGProperty.COURSEINFO_HEADER_TEXT.toString());
        ciHeaderLabel = new Label(courseInfoHeaderText);
        courseInfoHeaderBox.getChildren().add(ciHeaderLabel);
        
        subjectBox = new HBox();
        String subjectLabelText = props.getProperty(CSGProperty.SUBJECT_LABEL_TEXT.toString());
        subjectLabel = new Label(subjectLabelText);
        subjectComboBox = new ComboBox();
        subjectBox.getChildren().addAll(subjectLabel, subjectComboBox);
        
        numberBox = new HBox();
        String numberLabelText = props.getProperty(CSGProperty.NUMBER_LABEL_TEXT.toString());
        numberLabel = new Label(numberLabelText);
        numberComboBox = new ComboBox();
        numberBox.getChildren().addAll(numberLabel, numberComboBox);
        
        HBox subNumBox = new HBox();
        subNumBox.getChildren().addAll(subjectBox, numberBox);
        
        semesterBox = new HBox();
        String semesterLabelText = props.getProperty(CSGProperty.SEMESTER_LABEL_TEXT.toString());
        semesterLabel = new Label(semesterLabelText);
        semesterComboBox = new ComboBox();
        semesterBox.getChildren().addAll(semesterLabel, semesterComboBox);
        
        yearBox = new HBox();
        String yearLabelText = props.getProperty(CSGProperty.YEAR_LABEL_TEXT.toString());
        yearLabel = new Label(yearLabelText);
        yearComboBox = new ComboBox();
        yearBox.getChildren().addAll(yearLabel, yearComboBox);
        
        HBox semeYearBox = new HBox();
        semeYearBox.getChildren().addAll(semesterBox, yearBox);
        
        titleBox = new HBox();
        String titleLabelText = props.getProperty(CSGProperty.TITLE_LABEL_TEXT.toString());
        titleLabel = new Label(titleLabelText);
        titleTextField = new TextField();
        String titlePromptText = props.getProperty(CSGProperty.TITLE_PROMPT_TEXT.toString());
        titleTextField.setPromptText(titlePromptText);
        titleBox.getChildren().addAll(titleLabel, titleTextField);
        
        instructorNameBox = new HBox();
        String instructorNameLabelText = props.getProperty(CSGProperty.INSTRUCTORNAME_LABEL_TEXT.toString());
        instructorNameLabel = new Label(instructorNameLabelText);
        instructorNameTextField = new TextField();
        String instructorNamePromptText = props.getProperty(CSGProperty.INSTRUCTORNAME_PROMPT_TEXT.toString());
        instructorNameTextField.setPromptText(instructorNamePromptText);
        instructorNameBox.getChildren().addAll(instructorNameLabel, instructorNameTextField);
        
        instructorHomeBox = new HBox();
        String instructorHomeLabelText = props.getProperty(CSGProperty.INSTRUCTORHOME_LABEL_TEXT.toString());
        instructorHomeLabel = new Label(instructorHomeLabelText);
        instructorHomeTextField = new TextField();
        String instructorHomePromptText = props.getProperty(CSGProperty.INSTRUCTORHOME_PROMPT_TEXT.toString());
        instructorHomeTextField.setPromptText(instructorHomePromptText);
        instructorHomeBox.getChildren().addAll(instructorHomeLabel, instructorHomeTextField);
        
        courseInfoBox = new VBox();
        courseInfoBox.getChildren().addAll(courseInfoHeaderBox, subNumBox, semeYearBox, titleBox, instructorNameBox, instructorHomeBox);
        
        siteTemplateHeaderBox = new HBox();
        String siteTemplateHeaderText = props.getProperty(CSGProperty.SITETEMPLATE_HEADER_TEXT.toString());
        siteTemplateHeaderLabel = new Label(siteTemplateHeaderText);
        siteTemplateHeaderBox.getChildren().add(siteTemplateHeaderLabel);
        
        siteTemplateInfoBox = new HBox();
        String siteTemplateInfoText = props.getProperty(CSGProperty.SITETEMPLATE_LABEL_TEXT.toString());
        siteTemplateInfoLabel = new Label(siteTemplateInfoText);
        siteTemplateInfoBox.getChildren().add(siteTemplateInfoLabel);
        
        selectTemplateDirButton = new Button(props.getProperty(CSGProperty.SELECT_TEMPLATE_BUTTON_TEXT.toString()));
        
        sitePagesBox = new HBox();
        String sitePagesHeaderText = props.getProperty(CSGProperty.SITEPAGES_LABEL_TEXT.toString());
        sitePagesHeaderLabel = new Label(sitePagesHeaderText);
        sitePagesBox.getChildren().add(sitePagesHeaderLabel);
        
        siteTable = new TableView();CSGData data = (CSGData) app.getDataComponent();
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
        
        siteTemplateBox = new VBox();
        siteTemplateBox.getChildren().addAll(siteTemplateHeaderBox, siteTemplateInfoBox, selectTemplateDirButton, sitePagesBox, siteTable);
        
        pageStyleHeaderBox = new HBox();
        String pageStyleHeaderText = props.getProperty(CSGProperty.PAGESTYLE_HEADER_TEXT.toString());
        pageStyleHeaderLabel = new Label(pageStyleHeaderText);
        pageStyleHeaderBox.getChildren().add(pageStyleHeaderLabel);
        
        bannerSchoolImgBox = new HBox();
        String bannerSchoolImgText = props.getProperty(CSGProperty.BANNERSCHOOLIMG_LABEL_TEXT.toString());
        bannerSchoolImgLabel = new Label(bannerSchoolImgText);
        changeBannerButton = new Button(props.getProperty(CSGProperty.CHANGE_BUTTON_TEXT.toString()));
        bannerSchoolImgBox.getChildren().addAll(bannerSchoolImgLabel, changeBannerButton);

        leftFooterImgBox = new HBox();
        String leftFooterImgText = props.getProperty(CSGProperty.LF_LABEL_TEXT.toString());
        leftFooterImgLabel = new Label(leftFooterImgText);
        changeLeftFooterButton = new Button(props.getProperty(CSGProperty.CHANGE_BUTTON_TEXT.toString()));
        leftFooterImgBox.getChildren().addAll(leftFooterImgLabel, changeLeftFooterButton);

        rightFooterImgBox = new HBox();
        String rightFooterImgText = props.getProperty(CSGProperty.RF_LABEL_TEXT.toString());
        rightFooterImgLabel = new Label(rightFooterImgText);
        changeRightFooterButton = new Button(props.getProperty(CSGProperty.CHANGE_BUTTON_TEXT.toString()));
        rightFooterImgBox.getChildren().addAll(rightFooterImgLabel, changeRightFooterButton);

        stylesheetBox = new HBox();
        String stylesheetText = props.getProperty(CSGProperty.SS_LABEL_TEXT.toString()); 
        stylesheetLabel = new Label(stylesheetText);
        stylesheetComboBox = new ComboBox();
        String ssNoteText = props.getProperty(CSGProperty.SS_NOTE_TEXT.toString());
        stylesheetBox.getChildren().addAll(stylesheetLabel, stylesheetComboBox);
        
        ssNoteLabel = new Label(ssNoteText);
        
        pageStyleBox = new VBox();
        pageStyleBox.getChildren().addAll(pageStyleHeaderBox, bannerSchoolImgBox, leftFooterImgBox, rightFooterImgBox, stylesheetBox, ssNoteLabel);
        
        courseDetailsPane = new BorderPane();
        courseDetailsPane.setTop(courseInfoBox);
        courseDetailsPane.setCenter(siteTemplateBox);
        courseDetailsPane.setBottom(pageStyleBox);
        
        return courseDetailsPane;
    }
    
    private Pane generateCSGDataTab() {
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

        // NOW LET'S SETUP THE EVENT HANDLING
        controller = new CSGController(app);

        deleteTAButton.setOnAction(e -> {
            controller.handleDelete(KeyCode.DELETE);
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
            controller.handleDelete(e.getCode());
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
        deleteRecitationButton.prefHeightProperty().bind(recitationHeaderBox.heightProperty().multiply(1.5));
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
                new PropertyValueFactory<>("ta")
        );
        recitationTable.getColumns().add(supervisingTAColumnOne);
        supervisingTAColumnTwo = new TableColumn(taColumnText);
        supervisingTAColumnTwo.setCellValueFactory(
                new PropertyValueFactory<>("ta")
        );
        recitationTable.getColumns().add(supervisingTAColumnTwo);

        addEditBox = new VBox();

        addEditHeaderBox = new HBox();
        String addEditLabelText = props.getProperty(CSGProperty.ADDEDIT_LABEL_TEXT.toString());
        addEditHeaderLabel = new Label(addEditLabelText);
        addEditHeaderBox.getChildren().add(addEditHeaderLabel);

        sectionBox = new HBox();
        String sectionLabelText = props.getProperty(CSGProperty.SECTION_LABEL_TEXT.toString());
        sectionLabel = new Label(sectionLabelText);
        sectionTextField = new TextField();
        sectionBox.getChildren().addAll(sectionLabel, sectionTextField);

        instructorBox = new HBox();
        String instructorLabelText = props.getProperty(CSGProperty.INSTRUC_LABEL_TEXT.toString());
        instructorLabel = new Label(instructorLabelText);
        instructorTextField = new TextField();
        instructorBox.getChildren().addAll(instructorLabel, instructorTextField);

        dayBox = new HBox();
        String dayLabelText = props.getProperty(CSGProperty.DAY_LABEL_TEXT.toString());
        dayLabel = new Label(dayLabelText);
        dayTextField = new TextField();
        dayBox.getChildren().addAll(dayLabel, dayTextField);

        locationBox = new HBox();
        String locationLabelText = props.getProperty(CSGProperty.LOCATION_LABEL_TEXT.toString());
        locationLabel = new Label(locationLabelText);
        locationTextField = new TextField();
        locationBox.getChildren().addAll(locationLabel, locationTextField);

        supervisingTABoxOne = new HBox();
        supervisingTABoxTwo = new HBox();
        String supervisingLabelText = props.getProperty(CSGProperty.SUPTA_LABEL_TEXT.toString());
        supervisingLabelOne = new Label(supervisingLabelText);
        supervisingLabelTwo = new Label(supervisingLabelText);
        supervisingTAComboBoxOne = new ComboBox();
        supervisingTAComboBoxTwo = new ComboBox();
        supervisingTABoxOne.getChildren().addAll(supervisingLabelOne, supervisingTAComboBoxOne);
        supervisingTABoxTwo.getChildren().addAll(supervisingLabelTwo, supervisingTAComboBoxTwo);
        
        addEditBox.getChildren().addAll(addEditHeaderBox, sectionBox, instructorBox, dayBox, locationBox, supervisingTABoxOne, supervisingTABoxTwo);
        
        recitationDataPane = new BorderPane();
        recitationDataPane.setTop(recitationHeaderBox);
        recitationDataPane.setCenter(recitationTable);
        recitationDataPane.setBottom(addEditBox);
        
        return recitationDataPane;
    }
    
    private Pane generateScheduleDataTab() {
        return null;
    }
    
    private Pane generateProjectDataTab() {
        return null;
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
        CSGData taData = (CSGData)dataComponent;
        reloadOfficeHoursGrid(taData);
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
}
