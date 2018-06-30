package com.collekarry.docside;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.collekarry.docside.listOfPeople.personName;
import static com.collekarry.docside.tabbedChoice.choicForPerson;
import static com.collekarry.docside.tabbedChoice.flag;

public class checkPrevRecord extends Fragment {

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

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("abc");
        mDatabaseReference  = mDatabaseReference.child(personName).child("doc").child(typeName);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MyBlaBlaVnew upload = postSnapshot.getValue(MyBlaBlaVnew.class);
                    uploadList.add(upload);
                }

                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getDaProb();
                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, uploads);
                recdView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

    }
}
