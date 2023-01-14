package org.techtown.calenderapp;

import android.util.Log;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarUnits {

    public static LocalDate selectedDate;

    //작성일자 보여주기
    public static String formattedDate(LocalDate date) {
        Log.v("TAG","CalendarUnits: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    //작성일자 보여주기
    public static String formattedTime(LocalTime time) {
        Log.v("TAG","CalendarUnits: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return time.format(formatter);
    }

    public static String monthYearFromDate(LocalDate date){
        Log.v("TAG","CalendarUnits: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        //DateTimeFormatter: 날짜 보이는 양식을 아예 정해주는 것
        return date.format(formatter);
    }


    public static ArrayList<LocalDate> daysInMontArray(LocalDate date) {
        Log.v("TAG","CalendarUnits: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        //달력에서 날짜들 보여주기
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = CalendarUnits.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue(); //getDayOfWeek: 요일 가져오기

        for(int i = 1; i<=42; i++){ //42: 달력 날짜칸 개수 7일*6줄: 42
            if(i <= dayOfWeek || i> daysInMonth + dayOfWeek)
                daysInMonthArray.add(null);
            else
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(),i - dayOfWeek));
        }
        return daysInMonthArray;
    }

     public static ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate) {
         Log.v("TAG","CalendarUnits: "+ new Object(){}.getClass().getEnclosingMethod().getName());

         //주 별 날짜 보여주기
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate current = sundayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);

        while (current.isBefore(endDate)){
            days.add(current);
            current = current.plusDays(1);
        }
        return days;
    }

    private static LocalDate sundayForDate(LocalDate current) {
        Log.v("TAG","CalendarUnits: "+ new Object(){}.getClass().getEnclosingMethod().getName());

        LocalDate oneWeekAgo = current.minusWeeks(1);
        while (current.isAfter(oneWeekAgo)){
            if(current.getDayOfWeek() == DayOfWeek.SUNDAY)
                return current;
            current = current.minusDays(1);
        }
        return null;
    }
}
