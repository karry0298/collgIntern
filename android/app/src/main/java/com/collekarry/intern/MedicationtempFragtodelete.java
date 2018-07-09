//package com.collekarry.intern;
//
//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.sql.Time;
//import org.joda.time.LocalTime;
//import java.util.Date;
//import java.util.List;
//
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
//
// * to handle interaction events.
// * Use the {@link MedicationFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class MedicationtempFragtodelete extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "ward";
////    private static final String ARG_PARAM2 = "param2";
//    private ListView listView;
//
//    // TODO: Rename and change types of parameters
//    private wardClass mParam1;
//    private String mParam2;
//
////    private OnFragmentInteractionListener mListener;
//
//    public MedicationtempFragtodelete() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
////     * @param param1 Parameter 1.
////     * @param param2 Parameter 2.
//     * @return A new instance of fragment MedicationFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MedicationFragment newInstance(wardClass ward, String param2) {
//        MedicationFragment fragment = new MedicationFragment();
//        Bundle args = new Bundle();
//        fragment.mParam1 = ward;
//        args.putSerializable(ARG_PARAM1, ward);
////        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
////        }
////    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        // Inflate the layout for this fragment
//        View v = inflater.inflate(android.R.layout.simple_list_item_2, container, false);
//        listView = v.findViewById(R.id.med_listview);
//        if(mParam1!= null) {
//
//            List<Medicine> meds = mParam1.getMedicines();
//            if (meds != null && meds.size() > 0) {
//
//                ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_2,
//                        android.R.id.text1,
//                        mParam1.getMedicines()) {
//                    @NonNull
//                    @Override
//                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                        View view = super.getView(position, convertView, parent);
//                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//                        text1.setText(mParam1.getMedicines().get(position).getName());
//                        text2.setText(mParam1.getMedicines().get(position).getNextConsumptionTime(LocalTime.now()).toString());
//                        return view;
//                    }
//                };
//
//                listView.setAdapter(adapter);
//            }
//        }
//
//        return v;
//
//    }
////
////    // TODO: Rename method, update argument and hook method into UI event
////    public void onButtonPressed(Uri uri) {
////        if (mListener != null) {
////            mListener.onFragmentInteraction(uri);
////        }
////    }
////
////    @Override
////    public void onAttach(Context context) {
////        super.onAttach(context);
////        if (context instanceof OnFragmentInteractionListener) {
////            mListener = (OnFragmentInteractionListener) context;
////        } else {
////            throw new RuntimeException(context.toString()
////                    + " must implement OnFragmentInteractionListener");
////        }
////    }
////
////    @Override
////    public void onDetach() {
////        super.onDetach();
////        mListener = null;
////    }
////
////    /**
////     * This interface must be implemented by activities that contain this
////     * fragment to allow an interaction in this fragment to be communicated
////     * to the activity and potentially other fragments contained in that
////     * activity.
////     * <p>
////     * See the Android Training lesson <a href=
////     * "http://developer.android.com/training/basics/fragments/communicating.html"
////     * >Communicating with Other Fragments</a> for more information.
////     */
////    public interface OnFragmentInteractionListener {
////        // TODO: Update argument type and name
////        void onFragmentInteraction(Uri uri);
////    }
//}
