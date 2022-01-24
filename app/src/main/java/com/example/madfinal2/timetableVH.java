package com.example.madfinal2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class timetableVH extends RecyclerView.ViewHolder {
    public TextView txt_subject,txt_date,txt_time,txt_option;
    public timetableVH(@NonNull View itemView) {
        super(itemView);
        txt_subject = itemView.findViewById(R.id.txt_Subject);
        txt_date = itemView.findViewById(R.id.txt_Date);
        txt_time = itemView.findViewById(R.id.txt_Time);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
