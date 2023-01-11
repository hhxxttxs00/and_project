package org.techtown.calenderapp;

import static org.techtown.calenderapp.CalendarUnits.daysInWeekArray;
import static org.techtown.calenderapp.CalendarUnits.monthYearFromDate;
import static org.techtown.calenderapp.CalendarUnits.selectedDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeeklyViewActivity extends AppCompatActivity {

    TextView monthYearText;
    RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);

        initWidgets();
        setWeekView();

    }


    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerview);
        monthYearText = findViewById(R.id.monthYearTV);

    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUnits.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUnits.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
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

    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }
}