package org.techtown.calenderapp;

import static org.techtown.calenderapp.CalendarUnits.daysInWeekArray;
import static org.techtown.calenderapp.CalendarUnits.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    TextView monthYearText;
    RecyclerView calendarRecyclerView, weekRecyclerview;
    ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("TAG","WeekViewActivity: onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);

        initWidgets();
        setWeekView();
    }


    private void initWidgets() {
        Log.v("TAG","WeekViewActivity: initWidgets");

        calendarRecyclerView = findViewById(R.id.calendarRecyclerview);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
        weekRecyclerview = findViewById(R.id.weekRecyclerview);
    }

    private void setWeekView() {
        Log.v("TAG","WeekViewActivity: setWeekView");

        monthYearText.setText(monthYearFromDate(CalendarUnits.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUnits.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }


    public void previousWeekAction(View view) {
        CalendarUnits.selectedDate = CalendarUnits.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUnits.selectedDate = CalendarUnits.selectedDate.plusWeeks(1);
        setWeekView();
    }


    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUnits.selectedDate = date;
        setWeekView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUnits.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    private void setEventRecyclerviewAdapter(){

    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }
}