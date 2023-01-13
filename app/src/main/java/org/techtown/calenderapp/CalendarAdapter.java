package org.techtown.calenderapp;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    ArrayList<LocalDate> days;
    OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
        Log.v("TAG","CalendarAdapter days: " + days + onItemListener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* onCreateViewHolder:
        ViewHolder를 새로 만들어야 할 때 호출되는 메서드.
        이 메서드를 통해 각 아이템을 위한 XML 레이아웃을 이용한 뷰 객체를 생성하고 뷰 홀더에 담아 리턴.
        이때 뷰의 콘텐츠를 채우지 않는다. 아직 ViewHolder가 특정 데이터에 바인딩된 상태가 아니기 때문.
         */

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        /*
        true: child뷰를 parent뷰에 지금 당장 붙이겠다.
        false: child뷰를 parent뷰에 지금 붙이지 않겠다.
         */
        if(days.size()>15) {//monthView
            layoutParams.height = (int) (parent.getHeight()*0.1666666); //달력 날짜 높이설정
            Log.v("TAG","days.size: " +days.size());
        }
        else {// week view
            layoutParams.height = (int) parent.getHeight();

        }
        return new ViewHolder(view, onItemListener,days);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /* onBindViewHolder:
        ViewHolder를 데이터와 연결할 때 호출하는 메서드. 이 메서드를 통해 뷰홀더의 레이아웃을 채우게 됨.
         */
        LocalDate date = days.get(position);
        Log.v("TAG","day: " + date);
        if(date == null){
            holder.dayOfMonth.setText("");
        }
        else{
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CalendarUnits.selectedDate))
                holder.parentView.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    //뷰를 보관하는 홀더 객체.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /*
        ViewHolder: 뷰를 보관하고 있는 객체.
        각 뷰 객체를 뷰 홀더에 보관함으로써 findViewById()와 같은 반복적 호출 메서드를 줄여 효과적으로 속도 개선을 할 수 있음.
         */


        ArrayList<LocalDate> days;
        View parentView;
        TextView dayOfMonth;
        OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener, ArrayList<LocalDate> days) {
            super(itemView);
            parentView = itemView.findViewById(R.id.parentView);
            dayOfMonth = itemView.findViewById(R.id.cellDayText);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
            this.days = days;
        }


        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition(),days.get(getAdapterPosition()));
        }
    }


    public interface OnItemListener{
        void onItemClick(int position, LocalDate date);
    }
}
