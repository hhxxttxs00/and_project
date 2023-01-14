package org.techtown.calenderapp;

import static org.techtown.calenderapp.CalendarUnits.daysInMontArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    TextView monthYearText;
    RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("TAG","MainActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        setContentView(R.layout.activity_main);
        initWidgets();
        CalendarUnits.selectedDate = LocalDate.now();
        setMonthView();
    }


    private void initWidgets() {
        Log.v("TAG","MainActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        calendarRecyclerView = findViewById(R.id.calendarRecyclerview);
        monthYearText = findViewById(R.id.monthYearTV);

    }

    private void setMonthView() {
        Log.v("TAG","MainActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        monthYearText.setText(monthYearFromDate(CalendarUnits.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMontArray(CalendarUnits.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public String monthYearFromDate(LocalDate date){
        Log.v("TAG","MainActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        //DateTimeFormatter: 날짜 보이는 양식을 아예 정해주는 것
        return date.format(formatter);
    }

    public void previousMonthAction(View view) {
        Log.v("TAG","MainActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        CalendarUnits.selectedDate = CalendarUnits.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        Log.v("TAG","MainActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        CalendarUnits.selectedDate = CalendarUnits.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        Log.v("TAG","MainActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        if(date != null){
            CalendarUnits.selectedDate = date;
            setMonthView();
        }
    }

    public void weeklyAction(View view) {
        Log.v("TAG","MainActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        startActivity(new Intent(this, WeekViewActivity.class));
    }
}