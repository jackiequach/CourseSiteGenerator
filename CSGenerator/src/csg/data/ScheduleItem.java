/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jackie
 */
public class ScheduleItem<E extends Comparable<E>> implements Comparable<E> {
    private final StringProperty type;
    private final StringProperty date;
    private final StringProperty time;
    private final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    private final StringProperty criteria;
    
    public ScheduleItem(String initType, String initDate, String initTitle, String initTopic) {
        this(initType,initDate,"",initTitle,initTopic,"","");
    }
    
    public ScheduleItem(String initType, String initDate, String initTime, String initTitle, String initTopic, String initLink, String initCriteria) {
        type = new SimpleStringProperty(initType);
        date = new SimpleStringProperty(initDate);
        time = new SimpleStringProperty(initTime);
        title = new SimpleStringProperty(initTitle);
        topic = new SimpleStringProperty(initTopic);
        link = new SimpleStringProperty(initLink);
        criteria = new SimpleStringProperty(initCriteria);
    }
    
    public String getType() {
        return type.get();
    }
    
    public String getDate() {
        return date.get();
    }
    
    public String getTime() {
        return time.get();
    }
    
    public String getTitle() {
        return title.get();
    }
    
    public String getTopic() {
        return topic.get();
    }
    
    public String getLink() {
        return link.get();
    }
    
    public String getCriteria() {
        return criteria.get();
    }
    
    public void setType(String type) {
        this.type.set(type);
    }
    
    public void setDate(String date) {
        this.date.set(date);
    }
    
    public void setTime(String time) {
        this.time.set(time);
    }
    
    public void setTitle(String title) {
        this.title.set(title);
    }
    
    public void setTopic(String topic) {
        this.topic.set(topic);
    }
    
    public void setLink(String link) {
        this.link.set(link);
    }
    
    public void setCriteria(String criteria) {
        this.criteria.set(criteria);
    }

    @Override
    public int compareTo(E otherScheduleItem) {
        if(LocalDate.parse(getDate()).isBefore(LocalDate.parse(((ScheduleItem)otherScheduleItem).getDate()))) {
            return -1;
        }
        else if(LocalDate.parse(getDate()).isEqual(LocalDate.parse(((ScheduleItem)otherScheduleItem).getDate()))) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
