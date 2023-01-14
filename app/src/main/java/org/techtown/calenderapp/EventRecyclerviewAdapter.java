package org.techtown.calenderapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventRecyclerviewAdapter extends RecyclerView.Adapter<EventRecyclerviewAdapter.ViewHolder> {

    ArrayList<EventRecyclerviewData> mArrayList;
    OnItemListener onItemListener;

    public EventRecyclerviewAdapter(Context applicationContext, ArrayList<EventRecyclerviewData> mArrayList) {
        this.mArrayList = mArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("TAG","EventRecyclerviewAdapter: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.event_recyclerview_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        return new ViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventRecyclerviewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.v("TAG","EventRecyclerviewAdapter: "+ new Object(){}.getClass().getEnclosingMethod().getName());
        /* onBindViewHolder:
        ViewHolder를 데이터와 연결할 때 호출하는 메서드. 이 메서드를 통해 뷰홀더의 레이아웃을 채우게 됨.
        */
        EventRecyclerviewData eventRecyclerviewData = mArrayList.get(position);

        holder.event_recyclerview_cell.setText(eventRecyclerviewData.getEvent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("TAG","onBindViewHolder: 아이템 선택");
                onItemListener.onItemClick(position,eventRecyclerviewData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView event_recyclerview_cell;

        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            Log.v("TAG","EventRecyclerviewAdapter: "+ new Object(){}.getClass().getEnclosingMethod().getName());

            this.event_recyclerview_cell = itemView.findViewById(R.id.event_recyclerview_cell);
        }

//        @Override
//        public void onClick(View view) {
//            onItemListener.onItemClick(getAdapterPosition(),get(getAdapterPosition()));
//        }
    }

    public void setOnItemClickListener(OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }

    public interface OnItemListener{
        void onItemClick(int position, EventRecyclerviewData eventRecyclerviewData);
    }



}
