package com.collekarry.docside;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.collekarry.docside.listOfPeople.personName;

public class sendNewMedication extends Fragment implements View.OnClickListener {
    TextView morContent,noonContent,eveContent;
    String morning,afternoon,evening;
    Button sub;
    DatabaseReference mDatabaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.send_new_medication, container, false);

        morContent = (TextView) rootView.findViewById(R.id.morngContent);
        noonContent = (TextView) rootView.findViewById(R.id.afternoonContent);
        eveContent = (TextView) rootView.findViewById(R.id.evenContent);
        sub = (Button) rootView.findViewById(R.id.submitBut);
        String typeName = "sendMed";

        Log.i("name:", ""+personName);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("abc");
        mDatabaseReference  = mDatabaseReference.child(""+personName).child("doc").child(typeName);

        sub.setOnClickListener((View.OnClickListener) this);

        mDatabaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                morning = dataSnapshot.child("morning").getValue(String.class);
                afternoon = dataSnapshot.child("afternoon").getValue(String.class);
                evening = dataSnapshot.child("evening").getValue(String.class);

                morContent.setText(morning);
                noonContent.setText(afternoon);
                eveContent.setText(evening);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }

    @Override
    public void onClick(View view)
    {
        Toast.makeText(this.getContext(),"sdvsdvsdv", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this.getContext(),updateTheMedication.class));
    }

}