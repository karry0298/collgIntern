package com.collekarry.intern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class listOfPeople extends AppCompatActivity
{
    DatabaseReference nameList;
    ListView list;
    List<listOfPeopleClass> uploadList;
    List<HashMap<String,String>> aList;

    String[] names = { "ABC", "DEF", "JHI", "JKL", "MNO", "PQR", "STU" ,"Obama", "Osama", "robzrjg", "miguel Rodrigues chacking max length", "Pable"};
    Integer[] ages = { 98, 97, 99, 104, 84, 89, 78, 99, 99, 99, 99, 99};
    Integer[] images = {R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round};

    List<String> Fnames ;
    List<Integer> Fages;
    List<Integer> Fimages;
    List<HashMap> dsList;


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
        else if (id == R.id.action_logout)
        {
            signOut();
        }

        return false;
    }

    private void signOut() {
       
    }




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_people);
//        String base = "";
//        Log.i("name:","");
        nameList = FirebaseDatabase.getInstance().getReference("abc");
//        uploadList = new ArrayList<>();

        Fnames = new ArrayList<>();
        Fages = new ArrayList<>();
        Fimages = new ArrayList<>();
        dsList = new ArrayList<>();

//        aList = new ArrayList<>();



        nameList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    HashMap value = (HashMap) postSnapshot.getValue();
                    if(!dsList.contains(value)) {

                        dsList.add(value);
//                        System.out.println(dsList);

                        Fnames.add((String) value.get("name"));
                        Fages.add(Integer.valueOf(Long.toString((Long) value.get("age"))));
                        Fimages.add(R.mipmap.ic_launcher_round);    //add actual images

                        Toast.makeText(listOfPeople.this, value.get("name") + " added", Toast.LENGTH_SHORT).show();
                        System.out.println(Fnames);

                        MyAdapter adapter = new MyAdapter(listOfPeople.this, Fnames.toArray(new String[Fnames.size()]), Fages.toArray(new Integer[Fages.size()]), Fimages.toArray(new Integer[Fimages.size()]));

                        list = (ListView) findViewById(R.id.peopleListView);
                        list.setAdapter(adapter);
                    }

                    //addition :
//                    System.out.println(upload.getName());
//                    HashMap<String, String> hm = new HashMap<>();
//                    hm.put("name", upload.getName());
//                    hm.put("age", ""+upload.getAge());
//                    hm.put("imageId", ""+upload.getImageId());
//                    aList.add(hm);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






//        HashMap<String,String> h1 = new HashMap<>();
//        h1.put("name","Nicola Tesla");
//        h1.put("age", "98");
//        h1.put("imageId", Integer.toString(R.mipmap.ic_launcher_round));
//        aList.add(h1);
//        String[] from = { "name", "age", "imageId"};
//        int[] to = { R.id.name, R.id.age, R.id.displayPicture};
//
//        SimpleAdapter sAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.people_listview_layout, from, to);



//        MyAdapter adapter = new MyAdapter(this, names,ages, images);

        MyAdapter adapter = new MyAdapter(this, Fnames.toArray(new String[Fnames.size()]), Fages.toArray(new Integer[Fages.size()]), Fimages.toArray(new Integer[Fimages.size()]));

        list = (ListView) findViewById(R.id.peopleListView);
        list.setAdapter(adapter);
    }

    class MyAdapter extends ArrayAdapter<String>{
        private final Context context;
        private final String[] names;
        private final Integer[] ages;
        private final Integer[] images;

        public MyAdapter(@NonNull Context context, String[] names, Integer[] ages, Integer[] images) {
            super(context, R.layout.people_listview_layout, names);

            this.context = context;
            this.names = names;
            this.ages = ages;
            this.images  = images;
        }

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater lf = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View rowView = lf.inflate(R.layout.people_listview_layout, null, true);


            ImageView iv1 = (ImageView) rowView.findViewById(R.id.displayPicture);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            TextView age = (TextView) rowView.findViewById(R.id.age);

            iv1.setImageResource(images[i]);
            name.setText(names[i].length()<=25 ? names[i] : names[i].substring(0, 25)+ "..." );
            age.setText(String.valueOf( ages[i] ));


            return rowView;
        }
    }
}
