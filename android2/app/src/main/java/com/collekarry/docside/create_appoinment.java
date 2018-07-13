package com.collekarry.docside;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.collekarry.docside.listOfPeople.personName;

public class create_appoinment extends AppCompatActivity
{
    Button date,time,ok;
    TextView set_date,set_time;
    EditText mytext;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    TimePickerDialog.OnTimeSetListener time_listener;
    String mytime,mydate,dbdate,editTextinput;
    DatabaseReference mDatabaseReference;

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
        String typeName = "pastRecd";
        mytime="";
        mydate="";
        dbdate="";
        editTextinput="";

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("abc");
        mDatabaseReference  = mDatabaseReference.child(""+personName).child("doc").child(typeName);

        Log.i("nameOf", personName);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(create_appoinment.this, "sfsfasfasfsafsaffasffasff", Toast.LENGTH_SHORT).show();
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//
                DatePickerDialog dialog = new DatePickerDialog(create_appoinment.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog
                        ,mDateSetListener
                        , year, month, day);
                dialog.show();
            }
        });



        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                mydate = month + "/" + day + "/" + year;
                dbdate = year+"year"+month+"data"+day+"month";

                String showDate = "date: "+mydate;
                Log.i("abc",mydate);
                set_date.setText(showDate);
            }
        };


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(create_appoinment.this, "sfsfasfasfsafsaffasffasff2", Toast.LENGTH_SHORT).show();


            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);

            TimePickerDialog dialog = new TimePickerDialog(create_appoinment.this,
                                    android.R.style.Theme_DeviceDefault_Light_Dialog,
                                    time_listener, hour,
                                    minute, false);

            dialog.show();
            }
        });

        time_listener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                // store the data in one string and set it to text
                mytime = String.valueOf(hour) + ":" + String.valueOf(minute);
                String showTime = "Time: "+mytime;
                set_time.setText(showTime);
            }
        };

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextinput = mytext.getText().toString();

                if(editTextinput.equals("") || mydate.equals("")|| mytime.equals("")){
                    Toast.makeText(create_appoinment.this, "Fucking doc enter the text", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MyBlaBlaVnew newBla = new MyBlaBlaVnew(mydate+" :"+editTextinput,"NO REPORT",mydate,mytime);
                    mDatabaseReference.child(dbdate).setValue(newBla);

                    startActivity(new Intent(create_appoinment.this,tabbedChoice.class));
                    Toast.makeText(create_appoinment.this, "APPOINTMENT CREATED", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
