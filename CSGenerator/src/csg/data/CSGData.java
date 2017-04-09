/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CSGProperty;
import static csg.CSGProperty.ADD_BUTTON_TEXT;
import static csg.CSGProperty.INVALID_TA_EMAIL_MESSAGE;
import static csg.CSGProperty.INVALID_TA_EMAIL_TITLE;
import static csg.CSGProperty.TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE;
import static csg.CSGProperty.TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE;
import static csg.CSGProperty.UPDATE_BUTTON_TEXT;
import csg.CSGeneratorApp;
import csg.workspace.CSGController;
import csg.workspace.CSGWorkspace;
import djf.components.AppDataComponent;
import static djf.settings.AppPropertyType.DELETE_TIME_MESSAGE;
import static djf.settings.AppPropertyType.DELETE_TIME_TITLE;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import properties_manager.PropertiesManager;
import csg.file.TimeSlot;

/**
 *
 * @author Jackie
 */
public class CSGData implements AppDataComponent {
    CSGeneratorApp app;

    ObservableList<TeachingAssistant> teachingAssistants;

    HashMap<String, StringProperty> officeHours;
    
    ArrayList<String> gridHeaders;
    
    int startHour;
    int endHour;
    
    public static final int MIN_START_HOUR = 0;
    public static final int MAX_END_HOUR = 23;
    
    public CSGData(CSGeneratorApp initApp) {
        app = initApp;
        
        teachingAssistants = FXCollections.observableArrayList();

        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        
        officeHours = new HashMap();
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> timeHeaders = props.getPropertyOptionsList(CSGProperty.OFFICE_HOURS_TABLE_HEADERS);
        ArrayList<String> dowHeaders = props.getPropertyOptionsList(CSGProperty.DAYS_OF_WEEK);
        gridHeaders = new ArrayList();
        gridHeaders.addAll(timeHeaders);
        gridHeaders.addAll(dowHeaders);
    }
    
    @Override
    public void resetData() {
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        officeHours.clear();
    }
    
    // ACCESSOR METHODS

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
    
    public ArrayList<String> getGridHeaders() {
        return gridHeaders;
    }

    public ObservableList getTeachingAssistants() {
        return teachingAssistants;
    }
    
    public String getCellKey(int col, int row) {
        return col + "_" + row;
    }

    public StringProperty getCellTextProperty(int col, int row) {
        String cellKey = getCellKey(col, row);
        return officeHours.get(cellKey);
    }

    public HashMap<String, StringProperty> getOfficeHours() {
        return officeHours;
    }
    
    public int getNumRows() {
        return ((endHour - startHour) * 2) + 1;
    }

    public String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
    public String getCellKey(String day, String time) {
        int col = gridHeaders.indexOf(day);
        int row = 1;
        int hour = Integer.parseInt(time.substring(0, time.indexOf("_")));
        int milHour = hour;
        if ((hour < startHour%12) || (hour >= (startHour%12) && time.contains("pm") && !time.contains("12")))
            milHour += 12;
        row += (milHour - startHour) * 2;
        if (time.contains("_30"))
            row += 1;
        return getCellKey(col, row);
    }
    
    public TeachingAssistant getTA(String testName) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return ta;
            }
        }
        return null;
    }
    
    /**
     * This method is for giving this data manager the string property
     * for a given cell.
     */
    public void setCellProperty(int col, int row, StringProperty prop) {
        String cellKey = getCellKey(col, row);
        officeHours.put(cellKey, prop);
    }    
    
    /**
     * This method is for setting the string property for a given cell.
     */
    public void setGridProperty(ArrayList<ArrayList<StringProperty>> grid,
                                int column, int row, StringProperty prop) {
        grid.get(row).set(column, prop);
    }
    
    private void initOfficeHours(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;
        
        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();
            
        // WE'LL BUILD THE USER INTERFACE COMPONENT FOR THE
        // OFFICE HOURS GRID AND FEED THEM TO OUR DATA
        // STRUCTURE AS WE GO
        CSGWorkspace workspaceComponent = (CSGWorkspace)app.getWorkspaceComponent();
        workspaceComponent.reloadOfficeHoursGrid(this);
    }
    
    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if ((initStartHour <= initEndHour)) {
            // THESE ARE VALID HOURS SO KEEP THEM
            initOfficeHours(initStartHour, initEndHour);
        }
    }

    public boolean containsTA(String testName) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsEmail(String testEmail) {
        for(TeachingAssistant ta : teachingAssistants) {
            if(ta.getEmail().equals(testEmail)) {
                return true;
            }
        }
        return false;
    }

    public void addTA(String initName, String initEmail) {
        // MAKE THE TA
        TeachingAssistant ta = new TeachingAssistant(initName, initEmail);

        // ADD THE TA
        if (!containsTA(initName)) {
            teachingAssistants.add(ta);
        }

        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }
    
    public void removeTA(TeachingAssistant ta)
    {
        String taName = ta.getName();
        for(StringProperty cellProp : officeHours.values())
        {
            removeTAFromCell(cellProp, taName);
        }
        for (Iterator<TeachingAssistant> it = teachingAssistants.iterator(); it.hasNext();) {
            TeachingAssistant t = it.next();
            if(t.compareTo(ta) == 0)
                it.remove();
        }
    }
    
    public void updateTA(CSGController.EmailValidator emailValidator, String taName) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        CSGController controller = new CSGController(app);
        Button addButton = workspace.getAddButton();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        CSGData data = (CSGData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        ObservableList<TeachingAssistant> listOfTAs = data.getTeachingAssistants();
        HashMap<String, StringProperty> officeHours = data.getOfficeHours();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        for(int i = 0; i < listOfTAs.size(); i++)
        {
            TeachingAssistant t = listOfTAs.get(i);
            if(!t.getName().equals(taName) && ((t.getName().equals(name)) || (t.getEmail().equals(email)))) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));
            }
            else if (!emailValidator.validate(email)) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(INVALID_TA_EMAIL_TITLE), props.getProperty(INVALID_TA_EMAIL_MESSAGE));
            }
            else if(t.getName().equals(taName)) {
                t.setName(name);
                t.setEmail(email);
                listOfTAs.set(i, t);

                if(!taName.equals(t.getName())) {
                    for(StringProperty cellProp : officeHours.values()) {
                        String cellText = cellProp.getValue();

                        if (cellText.equals(taName)) {
                            cellProp.setValue(nameTextField.getText());
                        }

                        else if (cellText.indexOf(taName) == 0) {
                            int startIndex = taName.length() + 1;
                            cellText = t.getName() + "\n" + cellText.substring(startIndex);
                            cellProp.setValue(cellText);
                        }

                        else if (cellText.contains(taName)) {
                            int startIndex = cellText.indexOf("\n" + taName);
                            if(startIndex > 0) {
                                cellText = cellText.substring(0, startIndex) + "\n" + t.getName() + cellText.substring(startIndex + taName.length()+1);
                                cellProp.setValue(cellText);
                            }
                        }
                    }
                    app.getGUI().getAppFileController().markAsEdited(app.getGUI());
                }
                nameTextField.setText("");
                emailTextField.setText("");

                addButton.setText(props.getProperty(ADD_BUTTON_TEXT));
                addButton.setOnAction(ee -> {
                    controller.handleAddTA();
                });
                break;
            }
        }
    }
    
    public void undoUpdateTA(String name, String email, TeachingAssistant ta, CSGController.EmailValidator emailValidator) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        CSGController controller = new CSGController(app);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Button addButton = workspace.getAddButton();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        CSGData data = (CSGData)app.getDataComponent();
        
        ObservableList<TeachingAssistant> listOfTAs = data.getTeachingAssistants();
        HashMap<String, StringProperty> officeHours = data.getOfficeHours();
        String taName = ta.getName();
        String taEmail = ta.getEmail();
        for(int i = 0; i < listOfTAs.size(); i++)
        {
            TeachingAssistant t = listOfTAs.get(i);
            if(t.getName().equals(taName)) {
                t.setName(name);
                t.setEmail(email);
                listOfTAs.set(i, t);

                if(!taName.equals(t.getName())) {
                    for(StringProperty cellProp : officeHours.values()) {
                        String cellText = cellProp.getValue();

                        if (cellText.equals(taName)) {
                            cellProp.setValue(name);
                        }

                        else if (cellText.indexOf(taName) == 0) {
                            int startIndex = taName.length() + 1;
                            cellText = t.getName() + "\n" + cellText.substring(startIndex);
                            cellProp.setValue(cellText);
                        }

                        else if (cellText.contains(taName)) {
                            int startIndex = cellText.indexOf("\n" + taName);
                            if(startIndex > 0) {
                                cellText = cellText.substring(0, startIndex) + "\n" + t.getName() + cellText.substring(startIndex + taName.length()+1);
                                cellProp.setValue(cellText);
                            }
                        }
                    }
                }
                nameTextField.setText(taName);
                emailTextField.setText(taEmail);

                addButton.setText(props.getProperty(UPDATE_BUTTON_TEXT));
                addButton.setOnAction(ee -> {
                    controller.handleUpdateTA(t);
                });
                break;
            }
        }
    }
    
    public void changeStartHour(int chosenHour, int startHour, ArrayList<TimeSlot> officeHours, String selectedTime, HashMap<String, StringProperty> officeHoursP) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        CSGData data = (CSGData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(chosenHour > startHour) {
            AppYesNoCancelDialogSingleton yesNoDialog = AppYesNoCancelDialogSingleton.getSingleton();
            yesNoDialog.show(props.getProperty(DELETE_TIME_TITLE),props.getProperty(DELETE_TIME_MESSAGE));
            String selection = yesNoDialog.getSelection();
            if (selection.equals(AppYesNoCancelDialogSingleton.YES)) {
                workspace.resetWorkspace();
                data.initHours(String.valueOf(chosenHour), String.valueOf(data.getEndHour()));
                for (TimeSlot ts : officeHours) {
                    int hour = Integer.parseInt(ts.getTime().substring(0, ts.getTime().indexOf("_")));
                    if(ts.getTime().contains("pm") && !ts.getTime().contains("12"))
                        hour += 12;
                    if((ts.getTime().contains("pm") && !selectedTime.contains("pm")) ||
                        (ts.getTime().contains("am") && selectedTime.contains("am") && hour >= chosenHour) ||
                        (ts.getTime().contains("pm") && selectedTime.contains("pm") && hour >= chosenHour)) {
                        data.addOfficeHoursReservation(ts.getDay(), ts.getTime(), ts.getName());
                        for (StringProperty p : officeHoursP.values()) {
                            String selected = data.getCellKey(ts.getDay(),ts.getTime());
                            int col = Integer.parseInt(selected.split("_")[0]);
                            int row = Integer.parseInt(selected.split("_")[1]);
                            data.setCellProperty(col, row, data.getCellTextProperty(col, row));
                        }
                    }
                }
                app.getGUI().getAppFileController().markAsEdited(app.getGUI());
            }
        }
        else {
            data.initHours(String.valueOf(chosenHour), String.valueOf(data.getEndHour()));
            for (TimeSlot ts : officeHours) {
                data.addOfficeHoursReservation(ts.getDay(), ts.getTime(), ts.getName());
                String selected = data.getCellKey(ts.getDay(),ts.getTime());
                int col = Integer.parseInt(selected.split("_")[0]);
                int row = Integer.parseInt(selected.split("_")[1]);
                data.setCellProperty(col, row, data.getCellTextProperty(col, row));
            }
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        }
    }
    
    public void undoChangeStartHour(int startHour, ArrayList<TimeSlot> officeHours) {
        CSGData data = (CSGData)app.getDataComponent();
        data.initHours(String.valueOf(startHour), String.valueOf(data.getEndHour()));
            for (TimeSlot ts : officeHours) {
                data.addOfficeHoursReservation(ts.getDay(), ts.getTime(), ts.getName());
                String selected = data.getCellKey(ts.getDay(),ts.getTime());
                int col = Integer.parseInt(selected.split("_")[0]);
                int row = Integer.parseInt(selected.split("_")[1]);
                data.setCellProperty(col, row, data.getCellTextProperty(col, row));
            }
    }
    
    public void changeEndHour(int chosenHour, int endHour, ArrayList<TimeSlot> officeHours, String selectedTime, HashMap<String, StringProperty> officeHoursP) {
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        CSGData data = (CSGData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(chosenHour < endHour) {
            AppYesNoCancelDialogSingleton yesNoDialog = AppYesNoCancelDialogSingleton.getSingleton();
            yesNoDialog.show(props.getProperty(DELETE_TIME_TITLE),props.getProperty(DELETE_TIME_MESSAGE));
            String selection = yesNoDialog.getSelection();
            if (selection.equals(AppYesNoCancelDialogSingleton.YES)) {
                workspace.resetWorkspace();
                data.initHours(String.valueOf(data.getStartHour()), String.valueOf(chosenHour));
                for (TimeSlot ts : officeHours) {
                    int hour = Integer.parseInt(ts.getTime().substring(0, ts.getTime().indexOf("_")));
                    if(ts.getTime().contains("pm") && !ts.getTime().contains("12"))
                        hour += 12;
                    if((ts.getTime().contains("am") && selectedTime.contains("pm")) ||
                        (ts.getTime().contains("am") && selectedTime.contains("am") && hour < chosenHour) ||
                        (ts.getTime().contains("pm") && selectedTime.contains("pm") && hour < chosenHour)) {
                        data.addOfficeHoursReservation(ts.getDay(), ts.getTime(), ts.getName());
                        for (StringProperty p : officeHoursP.values()) {
                            String selected = data.getCellKey(ts.getDay(),ts.getTime());
                            int col = Integer.parseInt(selected.split("_")[0]);
                            int row = Integer.parseInt(selected.split("_")[1]);
                            data.setCellProperty(col, row, data.getCellTextProperty(col, row));
                        }
                    }
                }
                app.getGUI().getAppFileController().markAsEdited(app.getGUI());
            }
        }
        else {
            data.initHours(String.valueOf(data.getStartHour()), String.valueOf(chosenHour));
            for (TimeSlot ts : officeHours) {
                data.addOfficeHoursReservation(ts.getDay(), ts.getTime(), ts.getName());
                String selected = data.getCellKey(ts.getDay(),ts.getTime());
                int col = Integer.parseInt(selected.split("_")[0]);
                int row = Integer.parseInt(selected.split("_")[1]);
                data.setCellProperty(col, row, data.getCellTextProperty(col, row));
            }
            app.getGUI().getAppFileController().markAsEdited(app.getGUI());
        }
    }
    
    public void undoChangeEndHour(int endHour, ArrayList<TimeSlot> officeHours) {
        CSGData data = (CSGData)app.getDataComponent();
        data.initHours(String.valueOf(data.getStartHour()), String.valueOf(endHour));
        for (TimeSlot ts : officeHours) {
            data.addOfficeHoursReservation(ts.getDay(), ts.getTime(), ts.getName());
            String selected = data.getCellKey(ts.getDay(),ts.getTime());
            int col = Integer.parseInt(selected.split("_")[0]);
            int row = Integer.parseInt(selected.split("_")[1]);
            data.setCellProperty(col, row, data.getCellTextProperty(col, row));
        }
    }

    public void addOfficeHoursReservation(String day, String time, String taName) {
        String cellKey = getCellKey(day, time);
        toggleTAOfficeHours(cellKey, taName);
    }
    
    /**
     * This function toggles the taName in the cell represented
     * by cellKey. Toggle means if it's there it removes it, if
     * it's not there it adds it.
     */
    public void toggleTAOfficeHours(String cellKey, String taName) {
        StringProperty cellProp = officeHours.get(cellKey);
        String cellText = cellProp.getValue();
        if(cellText.equals(taName) || cellText.indexOf(taName) == 0 || cellText.indexOf("\n" + taName) > 0)
            removeTAFromCell(cellProp, taName);
        else
            if(cellText.length() > 1)
                cellProp.setValue(cellText + "\n" + taName);
            else
                cellProp.setValue(cellText + taName);
    }
    
    public void undoToggleTA(StringProperty cellProp, String cellText) {
        cellProp.setValue(cellText);
    }
    
    /**
     * This method removes taName from the office grid cell
     * represented by cellProp.
     */
    public void removeTAFromCell(StringProperty cellProp, String taName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            cellProp.setValue("");
        }
        // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = taName.length() + 1;
            cellText = cellText.substring(startIndex);
            cellProp.setValue(cellText);
        }
        // IT MUST BE ANOTHER TA IN THE CELL
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            if(startIndex > 0) {
                cellText = cellText.substring(0, startIndex) + cellText.substring(startIndex + taName.length()+1);
                cellProp.setValue(cellText);
            }
        }
    }
}
