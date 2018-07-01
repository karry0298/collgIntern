package com.collekarry.docside;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class create_appoinment extends AppCompatActivity
{

    Button date,time,ok;
    TextView set_date,set_time;
    EditText mytext;

    public DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appoinment);

        date = (Button) findViewById(R.id.datePicker);
        time = (Button) findViewById(R.id.timePicker);
        ok = (Button) findViewById(R.id.okBtn);
        set_date = (TextView) findViewById(R.id.dateViewer);
        set_time = (TextView) findViewById(R.id.timeViewer);
        mytext = (EditText) findViewById(R.id.discpEdittext);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        create_appoinment.this,
                        android.R.style.TextAppearance_Theme,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String mydate = month + "/" + day + "/" + year;
                Log.i("abc",mydate);
                set_date.setText(mydate);
            }
        };

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
