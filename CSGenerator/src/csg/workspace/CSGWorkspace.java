/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGProperty;
import csg.CSGeneratorApp;
import csg.data.CSGData;
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
    Tab taDataTab;
    BorderPane taDataPane;
    Tab recitationDataTab;
    Tab scheduleDataTab;
    Tab projectDataTab;
    
    // FOR COURSE DETAILS TAB
    
    
    // FOR TA DATA TAB
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
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
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));
        
        return border;
    }
    
    private Pane generateCSGDataTab() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        tasHeaderBox = new HBox();
        String tasHeaderText = props.getProperty(CSGProperty.TAS_HEADER_TEXT.toString());
        tasHeaderLabel = new Label(tasHeaderText);
        tasHeaderBox.getChildren().add(tasHeaderLabel);

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
        return null;
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
