package org.techtown.calenderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    EditText eventNameET;
    TextView eventDateTV, eventTimeTV;
    LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("TAG","EventEditActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUnits.formattedDate(CalendarUnits.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUnits.formattedTime(time));
    }

    private void initWidgets() {
        Log.v("TAG","EventEditActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view) {
        Log.v("TAG","EventEditActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUnits.selectedDate, time);
        Event.eventsList.add(newEvent);
        finish();
    }
}