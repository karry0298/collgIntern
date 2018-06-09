package com.collekarry.intern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.collekarry.intern.MainActivity.uId;

public class listOfPeople extends AppCompatActivity
{
    DatabaseReference nameList;
    ListView list;
    List<listOfPeopleClass> uploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_people);
        String base = uId;
        Log.i("name:",uId);
        nameList = FirebaseDatabase.getInstance().getReference(uId);
        uploadList = new ArrayList<>();
        list = (ListView) findViewById(R.id.nameList);




        nameList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    String keyName = postSnapshot.getKey();
                    listOfPeopleClass upload = new listOfPeopleClass(keyName);
                    uploadList.add(upload);
                }

                if(uploadList != null){

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }

                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    list.setAdapter(adapter);

                }
                else{
                    String[] up =new String[1] ;
                    up[0] = "no folder created ";
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, up);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
