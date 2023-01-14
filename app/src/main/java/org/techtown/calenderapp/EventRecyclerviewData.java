package org.techtown.calenderapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventRecyclerviewData {

    String event;
    LocalDate date;
    LocalTime time;

    public EventRecyclerviewData(String event) {
        this.event = event;
//        this.date = date;
//        this.time = time;
    }

    public static ArrayList<EventRecyclerviewData> eventRecyclerviewDataList = new ArrayList<>();
    public static ArrayList<EventRecyclerviewData> eventsForDate(LocalDate date){
        ArrayList<EventRecyclerviewData> events = new ArrayList<>();
        for(EventRecyclerviewData eventRecyclerviewData : eventRecyclerviewDataList){
            if(eventRecyclerviewData.getDate().equals(date));
        }
        return events;
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
