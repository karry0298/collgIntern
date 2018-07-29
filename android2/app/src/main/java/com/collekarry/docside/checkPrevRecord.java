package com.collekarry.docside;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class checkPrevRecord extends Fragment
{

    DatabaseReference  mDatabaseReference;
    List<MyBlaBlaVnew> uploadList;
    List<String> Fnames;
    ListView recdView;
    Button  mbt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.check_prev_record, container, false);
        String typeName = "pastRecd";
        uploadList = new ArrayList<>();
        recdView = (ListView) rootView.findViewById(R.id.recordListView);

        Toast.makeText(getContext(), "Invalid DateDVCBVCXBFXCBVCX format.", Toast.LENGTH_SHORT);

//        Log.i("nameOf", ""+personName);
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference("abc");
//        mDatabaseReference  = mDatabaseReference.child(""+personName).child("doc").child(typeName);

        Log.i("abc","fuck u 1st");

        recdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                MyBlaBlaVnew upName = uploadList.get(i);
                String discrp = upName.getDiscription();

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Description of previous appointment:");

                TextView mytext = new TextView(getActivity());
                mytext.setText(discrp);
                mytext.setPadding(60,10,0,0);
                mytext.setTextSize(20);
                builder.setView(mytext);

                // Set up the buttons
                builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    MyBlaBlaVnew upload = postSnapshot.getValue(MyBlaBlaVnew.class);
//                    uploadList.add(upload);
//                }
//
//                Log.i("abc","sdlgbjfbsgjbfjkdhgbvdf");
//                String[] uploads = new String[uploadList.size()];
//
//                for (int i = 0; i < uploads.length; i++) {
//
//                    Log.i("abc","pirvrvdgjvbrgv");
//                    uploads[i] = uploadList.get(i).getDaProb();
//                }
//
//                Log.i("abc","fuck u 1st");
//
//                //displaying it to list
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, uploads);
//                recdView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

    }
}
