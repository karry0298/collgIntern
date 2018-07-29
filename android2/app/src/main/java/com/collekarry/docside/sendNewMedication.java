package com.collekarry.docside;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

public class sendNewMedication extends Fragment implements View.OnClickListener {


    private static final String ARG_PARAM1 = "ward";

    private EditText medName;
    private EditText medManufacturer;
    private TextView dateTextView;
    private LinearLayout datePickView;
    private ImageButton addTimingButton;
    private LinearLayout consumptionTimingLayout;
    private TextView timeTextViewMain;
    private EditText medPrescriptionBy;

    private DatabaseReference mDatabaseReference;

    private Button medSubmitButton;

    private DatePickerDialog.OnDateSetListener mDatesetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String d = String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year);
            dateTextView.setText(d);
        }
    };

    // TODO: Rename and change types of parameters
    private wardClass ward;

    private OnFragmentInteractionListener mListener;


        public void dateSelectClicked(View v){
        Calendar calender = Calendar.getInstance();
        Dialog mDialog = new DatePickerDialog(getContext(),
                mDatesetListener, calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH), calender
                .get(Calendar.DAY_OF_MONTH));

        mDialog.show();
    }








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.send_new_medication, container, false);

//        medName = getView().findViewById(R.id.medName);
//        medManufacturer = getView().findViewById(R.id.medManufacturer);
//        dateTextView = getView().findViewById(R.id.dateText);
//        datePickView = getView().findViewById(R.id.date_pick_view);
//        addTimingButton = getView().findViewById(R.id.action_add_timing);
//        consumptionTimingLayout = getView().findViewById(R.id.consumption_timings);
//        timeTextViewMain = getView().findViewById(R.id.timeTextViewMain);
//        medPrescriptionBy = getView().findViewById(R.id.medprescriptionBy);
//        medSubmitButton = getView().findViewById(R.id.medSubmitButton);
//
//
//        String typeName = "sendMed";
//
////        Log.i("name:", ""+personName);
////        mDatabaseReference = FirebaseDatabase.getInstance().getReference("abc");
////        mDatabaseReference  = mDatabaseReference.child(""+personName).child("doc").child(typeName);
//
//
//        mDatabaseReference.addValueEventListener(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//        medSubmitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = medName.getText().toString();
//                String manufacturer = medManufacturer.getText().toString();
//                String dateText = dateTextView.getText().toString().trim();
//                String prescriptionBy = medPrescriptionBy.getText().toString();
//
//                if(name.length() <= 3){
//                    medName.setError("Too short");
//                }
//                else if(manufacturer.length() <= 3){
//                    medManufacturer.setError("Too short");
//                }
//                else if(prescriptionBy.length() <= 3){
//                    medPrescriptionBy.setError("Too Short");
//                }
//                else{
//                    Date date = new Date();
//                    try {
//                        date = new SimpleDateFormat("dd/MM/yyyy").parse(dateText);
//                    }catch (ParseException e){
//                        Toast.makeText(getContext(), "Invalid Date format.", Toast.LENGTH_SHORT);
//                    }
//
//                    List<String> timings = new ArrayList<>();
//                    int noOfTimings = consumptionTimingLayout.getChildCount();
//                    for(int i = 0; i < noOfTimings; i++){
//                        View t = consumptionTimingLayout.getChildAt(i);
//                        TextView tv = t.findViewWithTag("time");
//                        timings.add(tv.getText().toString());
//                    }
//
//                    Medicine newMed = new Medicine(name,manufacturer,date,timings,prescriptionBy);
//                    ward.addMedicine(newMed);
//
//                    mDatabaseReference.child("wards").child(ward.getUid()).child(ward.getKey()).setValue(ward)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//
//                                    Toast.makeText(getContext(), "Med added", Toast.LENGTH_SHORT).show();
//
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(getContext(), "Med not added", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }
//
//
//            }
//
//        });


        return rootView;
    }





    @Override
    public void onClick(View view)
    {
        Toast.makeText(this.getContext(),"sdvsdvsdv", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this.getContext(),updateTheMedication.class));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}