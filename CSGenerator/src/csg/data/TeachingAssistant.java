package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a Teaching Assistant for the table of TAs.
 * 
 * @author Richard McKenna
 * @author Jackie Quach
 */
public class TeachingAssistant<E extends Comparable<E>> implements Comparable<E>  {
    // THE TABLE WILL STORE TA NAMES AND EMAILS
    private final BooleanProperty undergrad;
    private final StringProperty name;
    private final StringProperty email;

    /**
     * Constructor initializes the TA name
     */
    public TeachingAssistant(Boolean initUndergrad, String initName, String initEmail) {
        undergrad = new SimpleBooleanProperty(initUndergrad);
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES

    public Boolean getUndergrad() {
        return undergrad.get();
    }
    
    public void setUndergrad(Boolean undergrad) {
        this.undergrad.setValue(undergrad);
    }
    
    public String getName() {
        return name.get();
    }
    
    public String getEmail() {
        return email.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }
    
    public void setEmail(String initEmail) {
        email.set(initEmail);
    }

    @Override
    public int compareTo(E otherTA) {
        return getName().compareTo(((TeachingAssistant)otherTA).getName());
    }
    
    @Override
    public String toString() {
        return name.getValue();
    }
}