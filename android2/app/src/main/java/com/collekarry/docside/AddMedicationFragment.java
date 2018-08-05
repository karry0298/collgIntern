package com.collekarry.docside;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddMedicationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddMedicationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMedicationFragment extends DialogFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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

    public AddMedicationFragment() {
        // Required empty public constructor
    }


    public void dateSelectClicked(View v){
        Calendar calender = Calendar.getInstance();
        Dialog mDialog = new DatePickerDialog(getContext(),
                mDatesetListener, calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH), calender
                .get(Calendar.DAY_OF_MONTH));

        mDialog.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        medName = getView().findViewById(R.id.medName);
        medManufacturer = getView().findViewById(R.id.medManufacturer);
        dateTextView = getView().findViewById(R.id.dateText);
        datePickView = getView().findViewById(R.id.date_pick_view);
        addTimingButton = getView().findViewById(R.id.action_add_timing);
        consumptionTimingLayout = getView().findViewById(R.id.consumption_timings);
        timeTextViewMain = getView().findViewById(R.id.timeTextViewMain);
        medPrescriptionBy = getView().findViewById(R.id.medprescriptionBy);
        medSubmitButton = getView().findViewById(R.id.medSubmitButton);


        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        datePickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateSelectClicked(v);
            }
        });

        timeTextViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener mTimesetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String AM_PM ;
                        if(hourOfDay < 12) {
                            AM_PM = "am";
                        } else {
                            if(hourOfDay>12){
                                hourOfDay -= 12;
                            }
                            AM_PM = "pm";
                        }

                        timeTextViewMain.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute) + " " + AM_PM);
                    }

                };

                Calendar calender = Calendar.getInstance();
                Dialog mDialog = new TimePickerDialog(getContext(),
                        mTimesetListener, calender.get(Calendar.HOUR_OF_DAY),
                        calender.get(Calendar.MINUTE),false);

                mDialog.show();
            }
        });

        addTimingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addTimingButton.getTag().equals("add")) {

                    LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View row = vi.inflate(R.layout.layout_time_row, null);

                    final ImageButton newAddButton = row.findViewById(R.id.add_timing);
                    final TextView timeTextView = row.findViewById(R.id.timeTextView);


                    timeTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            TimePickerDialog.OnTimeSetListener mTimesetListener = new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    String AM_PM ;
                                    if(hourOfDay < 12) {
                                        AM_PM = "am";
                                    } else {
                                        if(hourOfDay>12){
                                            hourOfDay -= 12;
                                        }
                                        AM_PM = "pm";
                                    }

                                    timeTextView.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute) + " " + AM_PM);
                                }

                            };

                            Calendar calender = Calendar.getInstance();
                            Dialog mDialog = new TimePickerDialog(getContext(),
                                    mTimesetListener, calender.get(Calendar.HOUR_OF_DAY),
                                    calender.get(Calendar.MINUTE),
                                    false);

                            mDialog.show();
                        }
                    });



                    newAddButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            consumptionTimingLayout.removeView((View) v.getParent());
                        }
                    });


//                    LinearLayout vg = getView().findViewById(R.id.action_add_timing);
                    consumptionTimingLayout.addView(row, consumptionTimingLayout.getChildCount() , new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

//                    addTimingButton.setImageResource(R.drawable.ic_remove);
//                    addTimingButton.setTag("remove");
                }
                else{
                    consumptionTimingLayout.removeView((View) v.getParent());
                }
            }
        });

        medSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = medName.getText().toString();
                String manufacturer = medManufacturer.getText().toString();
                String dateText = dateTextView.getText().toString().trim();
                String prescriptionBy = medPrescriptionBy.getText().toString();

                if(name.length() <= 3){
                    medName.setError("Too short");
                }
                else if(manufacturer.length() <= 3){
                    medManufacturer.setError("Too short");
                }
                else if(prescriptionBy.length() <= 3){
                    medPrescriptionBy.setError("Too Short");
                }
                else{
                    Date date = new Date();
                    try {
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(dateText);
                    }catch (ParseException e){
                        Toast.makeText(getContext(), "Invalid Date format.", Toast.LENGTH_SHORT);
                    }

                    List<String> timings = new ArrayList<>();
                    int noOfTimings = consumptionTimingLayout.getChildCount();
                    for(int i = 0; i < noOfTimings; i++){
                        View t = consumptionTimingLayout.getChildAt(i);
                        TextView tv = t.findViewWithTag("time");
                        timings.add(tv.getText().toString());
                    }

                    Medicine newMed = new Medicine(name,manufacturer,date,timings,prescriptionBy);
                    ward.addMedicine(newMed);

                    mDatabaseReference.child("wards").child(ward.getUid()).child(ward.getKey()).setValue(ward)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    WardDetailsActivity w = (WardDetailsActivity) getActivity();
//                                    w.setWard(ward);
                                    Toast.makeText(getContext(), "Med added", Toast.LENGTH_SHORT).show();

                                    dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "Med not added", Toast.LENGTH_SHORT).show();
                                }
                            });
                }


            }

        });
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment AddMedicationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMedicationFragment newInstance(wardClass param1) {
        AddMedicationFragment fragment = new AddMedicationFragment();
        Bundle args = new Bundle();
        fragment.ward = param1;
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ward = (wardClass) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_medication, container, false);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
