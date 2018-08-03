package com.collekarry.docside;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

//import static com.collekarry.docside.listOfPeople.personName;

public class updateTheMedication extends AppCompatActivity implements View.OnClickListener {
    EditText morn,aft,even;
    DatabaseReference mDatabaseReference;
    Button change;
    String mrngtext,aftertext,eventext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_the_medication);

//        morn = (EditText) findViewById(R.id.morngUpdate);
//        aft = (EditText) findViewById(R.id.afternoonUpdate);
//        even = (EditText) findViewById(R.id.evenUpdate);
//        change = (Button) findViewById(R.id.sub);
        String typeName = "sendMed";

//        mDatabaseReference = FirebaseDatabase.getInstance().getReference("abc");
//        mDatabaseReference  = mDatabaseReference.child(""+personName).child("doc").child(typeName);

        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        mrngtext = morn.getText().toString();
        aftertext = aft.getText().toString();
        eventext = even.getText().toString();

        if(!mrngtext.equals("") && !aftertext.equals("") && !eventext.equals(""))
        {
            MediactionBla md = new MediactionBla(aftertext,eventext,mrngtext,"");
            mDatabaseReference.setValue(md);

            startActivity(new Intent(this,tabbedChoice.class));
        }
    }

    public static class MediactionBla
    {
        String afternoon,evening,morning,roughdata;

        public MediactionBla(){}

        public MediactionBla(String afternoon, String evening, String morning, String roughdata) {
            this.afternoon = afternoon;
            this.evening = evening;
            this.morning = morning;
            this.roughdata = roughdata;
        }

        public String getAfternoon() {
            return afternoon;
        }

        public String getEvening() {
            return evening;
        }

        public String getMorning() {
            return morning;
        }

        public String getRoughdata() {
            return roughdata;
        }
    }

}
