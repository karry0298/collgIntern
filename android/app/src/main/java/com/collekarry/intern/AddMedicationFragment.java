package com.collekarry.intern;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView dateTextView;
    private LinearLayout datePickView;
    private ImageButton addTimingButton;
    private LinearLayout consumptionTimingLayout;
    private TextView timeTextViewMain;

    private DatePickerDialog.OnDateSetListener mDatesetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String d = String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year);
            dateTextView.setText(d);
        }
    };

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        dateTextView = getView().findViewById(R.id.dateText);
        datePickView = getView().findViewById(R.id.date_pick_view);
        addTimingButton = getView().findViewById(R.id.action_add_timing);
        consumptionTimingLayout = getView().findViewById(R.id.consumption_timings);
        timeTextViewMain = getView().findViewById(R.id.timeTextViewMain);

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
                        calender.get(Calendar.MINUTE),
                        false);

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
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMedicationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMedicationFragment newInstance(String param1, String param2) {
        AddMedicationFragment fragment = new AddMedicationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_medication, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
