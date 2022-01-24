package com.example.madfinal2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    int hour,minute;
    EditText time;
    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText subject = findViewById(R.id.et_subject);
        date = findViewById(R.id.et_date);
        time = findViewById(R.id.et_time);
        Button btn = findViewById(R.id.btn_add);
        Button btn_open = findViewById(R.id.btn_open);

        Button gpaCalButton = findViewById(R.id.btn_calculator);

        gpaCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), calculator.class);
                startActivity(intent);
            }
        });

        btn_open.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, RVActivity.class);
            startActivity(intent);
        });
        DAOtimetable dao = new DAOtimetable();
        timetable time_edit = (timetable)getIntent().getSerializableExtra("EDIT");
        if(time_edit!=null){
            btn.setText("Update");
            subject.setText(time_edit.getSubject());
            date.setText(time_edit.getDate());
            time.setText(time_edit.getTime());
            btn_open.setVisibility(View.GONE);
        }
        else {
            btn.setText("Add");
            btn_open.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(v->{
            timetable timetab = new timetable(subject.getText().toString(),date.getText().toString(),time.getText().toString());
            if(time_edit==null) {
                dao.add(timetab).addOnSuccessListener(suc->{
                    Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("subject",subject.getText().toString());
                hashMap.put("date",date.getText().toString());
                hashMap.put("time",time.getText().toString());
                dao.update(time_edit.getKey(), hashMap).addOnSuccessListener(suc->{
                    Toast.makeText(this,"Record is updated",Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er->{
                    Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
                });
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert, mDateSetListener,year,month,day);

                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                Log.d(TAG, "onDateSet: mm/dd/yyy" + month + "/" + day + "/" + year);
                String datee = month + "/" + day + "/" + year;
                date.setText(datee);
            }
        };



    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                time.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("Select time");
        timePickerDialog.show();
    }
}