package com.example.nschat.nsCalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nschat.R;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.viewHolder> {

    Context context;
    String[] data;

    public CalendarAdapter(Context context, String[] data) {
        super();
        this.context = context;
        this.data = data;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView;

        public viewHolder(View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.day_recycler_calendar);
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_calendar_item, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.dayTextView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}

