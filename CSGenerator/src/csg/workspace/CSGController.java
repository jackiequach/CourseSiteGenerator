/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGProperty;
import static csg.CSGProperty.INVALID_TA_EMAIL_MESSAGE;
import static csg.CSGProperty.INVALID_TA_EMAIL_TITLE;
import static csg.CSGProperty.MISSING_TA_EMAIL_MESSAGE;
import static csg.CSGProperty.MISSING_TA_EMAIL_TITLE;
import static csg.CSGProperty.MISSING_TA_NAME_MESSAGE;
import static csg.CSGProperty.MISSING_TA_NAME_TITLE;
import static csg.CSGProperty.TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE;
import static csg.CSGProperty.TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE;
import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.TeachingAssistant;
import csg.file.TimeSlot;
import csg.jtps.AddTrans;
import csg.jtps.DeleteTrans;
import csg.jtps.EndTimeTrans;
import csg.jtps.StartTimeTrans;
import csg.jtps.ToggleTrans;
import csg.jtps.UpdateTrans;
import static djf.settings.AppPropertyType.INVALID_END_TIME_MESSAGE;
import static djf.settings.AppPropertyType.INVALID_END_TIME_TITLE;
import static djf.settings.AppPropertyType.INVALID_START_TIME_MESSAGE;
import static djf.settings.AppPropertyType.INVALID_START_TIME_TITLE;
import djf.ui.AppMessageDialogSingleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import jtps.jTPS;
import properties_manager.PropertiesManager;

/**
 *
 * @author Jackie
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
            AddTrans add = new AddTrans(app,name,email);
            transactions.addTransaction(add);
            
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            emailTextField.setText("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            
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
        app.getGUI().getAppFileController().markAsEdited(app.getGUI());
    }
    
    public void handleDelete(KeyCode key)
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
        TableView taTable = workspace.getTATable();
        Button addButton = workspace.getAddButton();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        CSGData data = (CSGData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
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
             transactions.undoTransaction();
            if(transactions.getMostRecentTransaction() < 0)
                app.getGUI().getAppFileController().markAsNotEdited(app.getGUI());
        }
         if(key.getCode() == KeyCode.Y && key.isControlDown()) {
             transactions.doTransaction();
             app.getGUI().getAppFileController().markAsEdited(app.getGUI());
         }
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
