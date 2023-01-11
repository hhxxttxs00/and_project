package org.techtown.calenderapp;

import android.graphics.Color;
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
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        /*
        true: child뷰를 parent뷰에 지금 당장 붙이겠다.
        false: child뷰를 parent뷰에 지금 붙이지 않겠다.
         */
        if(days.size()>15) //monthView
            layoutParams.height = (int) (parent.getHeight()*0.166666666);
        else // week view
            layoutParams.height = (int) parent.getHeight();
        return new ViewHolder(view, onItemListener,days);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocalDate date = days.get(position);
        if(date == null)
            holder.dayOfMonth.setText("");
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
