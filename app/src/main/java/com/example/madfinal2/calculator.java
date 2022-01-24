package com.example.madfinal2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class calculator extends AppCompatActivity {

    EditText et_gr1,et_gr2,et_gr3,et_gr4,et_gr5;
    EditText et_cred1,et_cred2,et_cred3,et_cred4,et_cred5;
    Button btn1,btnTimetable;
    TextView tv_ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        et_gr1 = findViewById(R.id.et_gr1);
        et_gr2 = findViewById(R.id.et_gr2);
        et_gr3 = findViewById(R.id.et_gr3);
        et_gr4 = findViewById(R.id.et_gr4);
        et_gr5 = findViewById(R.id.et_gr5);
        et_cred1 = findViewById(R.id.et_cred1);
        et_cred2 = findViewById(R.id.et_cred2);
        et_cred3 = findViewById(R.id.et_cred3);
        et_cred4 = findViewById(R.id.et_cred4);
        et_cred5 = findViewById(R.id.et_cred5);
        btn1 = findViewById(R.id.btn_1);
        btnTimetable = findViewById(R.id.btn_time2);
        tv_ans = findViewById(R.id.tv_ans);


        btnTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    protected void onResume() {
        super.onResume();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                calculateAnswer();
            }
        });
    }

    private void calculateAnswer(){
        calculation cal = new calculation();
        String gr1 = et_gr1.getText().toString();
        String gr2 = et_gr2.getText().toString();
        String gr3 = et_gr3.getText().toString();
        String gr4 = et_gr4.getText().toString();
        String gr5 = et_gr5.getText().toString();
        String cred1 = et_cred1.getText().toString();
        String cred2 = et_cred2.getText().toString();
        String cred3 = et_cred3.getText().toString();
        String cred4 = et_cred4.getText().toString();
        String cred5 = et_cred5.getText().toString();

        Float grade1 = Float.parseFloat(gr1);
        Float grade2 = Float.parseFloat(gr2);
        Float grade3 = Float.parseFloat(gr3);
        Float grade4 = Float.parseFloat(gr4);
        Float grade5 = Float.parseFloat(gr5);
        Float credit1 = Float.parseFloat(cred1);
        Float credit2 = Float.parseFloat(cred2);
        Float credit3 = Float.parseFloat(cred3);
        Float credit4 = Float.parseFloat(cred4);
        Float credit5 = Float.parseFloat(cred5);

        Float gpa = cal.calculategpa(grade1,grade2,grade3,grade4,grade5,credit1,credit2,credit3,credit4,credit5);

        tv_ans.setText(new Float(gpa).toString());
    }
}