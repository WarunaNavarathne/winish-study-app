package com.example.madfinal2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<timetable> list = new ArrayList<>();
    public RVAdapter(Context ctx){
        this.context = ctx;
    }
    public void setItems(ArrayList<timetable> tbl){
        list.addAll(tbl);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new timetableVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        timetableVH vh = (timetableVH) holder;
        timetable tme = list.get(position);
        vh.txt_subject.setText(tme.getSubject());
        vh.txt_date.setText(tme.getDate());
        vh.txt_time.setText(tme.getTime());

        vh.txt_option.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        Intent intent = new Intent(context,MainActivity.class);
                        intent.putExtra("EDIT", tme);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOtimetable dao = new DAOtimetable();
                        dao.remove(tme.getKey()).addOnSuccessListener(suc->{
                            Toast.makeText(context,"Record is removed",Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(er->{
                            Toast.makeText(context,""+er.getMessage(),Toast.LENGTH_SHORT).show();
                        });
                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
