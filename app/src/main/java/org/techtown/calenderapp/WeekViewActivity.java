package org.techtown.calenderapp;

import static org.techtown.calenderapp.CalendarUnits.daysInWeekArray;
import static org.techtown.calenderapp.CalendarUnits.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    TextView monthYearText;
    RecyclerView calendarRecyclerView;
    ListView eventListView;

    //리사이클러뷰
    ArrayList<EventRecyclerviewData> mArrayList;
    EventRecyclerviewAdapter eventRecyclerviewAdapter;
    RecyclerView weekRecyclerview;
    EditText edit_content, edit_update_content;
    Button btn_save;
    String event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_view);

        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        initWidgets();
        setWeekView();
        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event = edit_content.getText().toString();

                EventRecyclerviewData eventRecyclerviewData = new EventRecyclerviewData(event);
                EventRecyclerviewData.eventRecyclerviewDataList.add(eventRecyclerviewData);

                Log.v("TAG","버튼 클릭");
            }
        });
    }


    private void initWidgets() {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        calendarRecyclerView = findViewById(R.id.calendarRecyclerview);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
        weekRecyclerview = findViewById(R.id.weekRecyclerview);
    }

    private void setWeekView() {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        monthYearText.setText(monthYearFromDate(CalendarUnits.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUnits.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();

        //이벤트 리사이클러뷰
        EventRecyclerviewAdapter eventRecyclerviewAdapter = new EventRecyclerviewAdapter(getApplicationContext(),mArrayList);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());
        weekRecyclerview.setLayoutManager(layoutManager1);
        weekRecyclerview.setAdapter(eventRecyclerviewAdapter);
        setEventRecyclerviewAdapter();
    }


    public void previousWeekAction(View view) {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        CalendarUnits.selectedDate = CalendarUnits.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        CalendarUnits.selectedDate = CalendarUnits.selectedDate.plusWeeks(1);
        setWeekView();
    }


    @Override
    public void onItemClick(int position, LocalDate date) {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        CalendarUnits.selectedDate = date;
        setWeekView();
    }


    @Override
    protected void onResume() {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        super.onResume();
        setEventAdapter();
        setEventRecyclerviewAdapter();
    }

    private void setEventAdapter() {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUnits.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    private void setEventRecyclerviewAdapter(){
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        ArrayList<EventRecyclerviewData> dailyEvents = EventRecyclerviewData.eventsForDate(CalendarUnits.selectedDate);
        EventRecyclerviewAdapter eventRecyclerviewAdapter = new EventRecyclerviewAdapter(getApplicationContext(),dailyEvents);
        weekRecyclerview.setAdapter(eventRecyclerviewAdapter);
    }

    public void newEventAction(View view) {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void addRecyclerview(View view) {
        Log.v("TAG","WeekViewActivity: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        event = edit_content.getText().toString();

        EventRecyclerviewData eventRecyclerviewData = new EventRecyclerviewData(event);
        eventRecyclerviewData.setEvent(event);

        mArrayList.add(0,eventRecyclerviewData);
        eventRecyclerviewAdapter.notifyDataSetChanged();

        edit_content.setText("");
    }
}