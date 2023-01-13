package org.techtown.calenderapp;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventRecyclerview {

    String event;
    LocalDate date;
    LocalTime time;

    public static ArrayList<EventRecyclerview> eventRecyclerviewList = new ArrayList<>();
    public static ArrayList<EventRecyclerview> eventsForDate(LocalDate date){
        ArrayList<EventRecyclerview> events = new ArrayList<>();
        for(EventRecyclerview eventRecyclerview: eventRecyclerviewList){
            if(eventRecyclerview.getDate().equals(date));
        }
        return events;
    }

    public EventRecyclerview(String event, LocalDate date, LocalTime time) {
        this.event = event;
        this.date = date;
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
