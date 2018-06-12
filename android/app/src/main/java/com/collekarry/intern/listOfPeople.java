package com.collekarry.intern;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.collekarry.intern.MainActivity.uId;

public class listOfPeople extends AppCompatActivity
{
    DatabaseReference nameList;
    ListView list;
    List<listOfPeopleClass> uploadList;
    List<HashMap<String,String>> aList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menubar, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_newname)
        {

        }
        else if (id == R.id.action_update)
        {

        }

        return false;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_people);
        String base = uId;
        Log.i("name:",uId);
        nameList = FirebaseDatabase.getInstance().getReference(uId);
        uploadList = new ArrayList<>();
        list = (ListView) findViewById(R.id.peopleListView);
        aList = new ArrayList<>();

//        nameList.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
//                {
//                    String keyName = postSnapshot.getKey();
//                    int age = 98;
//                    int imageId = R.mipmap.ic_launcher_round;                   //change this when proper database created
//                    listOfPeopleClass upload = new listOfPeopleClass(keyName,age);
////                    uploadList.add(upload);
//
//                    //addition :
//                    System.out.println(upload.getName());
//                    HashMap<String, String> hm = new HashMap<>();
//                    hm.put("name", upload.getName());
//                    hm.put("age", ""+upload.getAge());
//                    hm.put("imageId", ""+upload.getImageId());
//                    aList.add(hm);
//                }
//
//                /*if(uploadList != null){
//
//                    String[] uploads = new String[uploadList.size()];
//
//                    for (int i = 0; i < uploads.length; i++) {
//                        uploads[i] = uploadList.get(i).getName();
//                    }
//
//                    //displaying it to list
//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
//                    list.setAdapter(adapter);
//                }
//                else{
//                    String[] up =new String[1] ;
//                    up[0] = "no folder created ";
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, up);
//                }
//                */
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });

        HashMap<String,String> h1 = new HashMap<>();
        h1.put("name","Nicola Tesla");
        h1.put("age", "98");
        h1.put("imageId", ""+R.mipmap.ic_launcher_round);
        aList.add(h1);
        String[] from = { "name", "age", "imageId"};
        int[] to = { R.id.name, R.id.age, R.id.displayPicture};

        SimpleAdapter sAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.people_listview_layout, from, to);

        list.setAdapter(sAdapter);
    }
}
