package org.techtown.calenderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    ArrayList<String> daysOfMonth;
    OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell,parent,false);
        /*
        true: child뷰를 parent뷰에 지금 당장 붙이겠다.
        false: child뷰를 parent뷰에 지금 붙이지 않겠다.
         */

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight()*0.166666666);
        return new ViewHolder(view, onItemListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));

    }

    @Override
    public int getItemCount() {

        return daysOfMonth.size();
    }

    //뷰를 보관하는 홀더 객체.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dayOfMonth;
        OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            dayOfMonth = itemView.findViewById(R.id.cellDayText);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition(),(String) dayOfMonth.getText());
        }
    }


    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }
}