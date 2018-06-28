package com.collekarry.intern;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class listOfPeople extends AppCompatActivity
{
    DatabaseReference nameList;
    ListView list;
    List<wardClass> uploadList;
    List<HashMap<String,String>> aList;

    String[] names = { "ABC", "DEF", "JHI", "JKL", "MNO", "PQR", "STU" ,"Obama", "Osama", "robzrjg", "miguel Rodrigues chacking max length", "Pable"};
    Integer[] ages = { 98, 97, 99, 104, 84, 89, 78, 99, 99, 99, 99, 99};
    Integer[] images = {R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round};

    List<String> Fnames ;
    List<Integer> Fages;
    List<StorageReference> Fimages;
    List<String> dsList;

    private StorageReference mStorageReference;


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
            startActivity(new Intent(listOfPeople.this, addPersonActivity.class));
        }
        else if (id == R.id.action_logout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(listOfPeople.this, MainActivity.class));
        }

        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_people);
//        String base = "";
//        Log.i("name:","");
        nameList = FirebaseDatabase.getInstance().getReference("wards");
//        uploadList = new ArrayList<>();

        Fnames = new ArrayList<>();
        Fages = new ArrayList<>();
        Fimages = new ArrayList<>();
        dsList = new ArrayList<>();

        mStorageReference = FirebaseStorage.getInstance().getReference();

//        aList = new ArrayList<>();



        nameList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    HashMap value = (HashMap) postSnapshot.getValue();
                    if(!dsList.contains(postSnapshot.getKey())) {

                        dsList.add(postSnapshot.getKey());
//                        System.out.println(dsList);

                        Fnames.add((String) value.get("name"));
                        Fages.add(Integer.valueOf(Long.toString((Long) value.get("age"))));


                        final String uid = (String) value.get("uid");
                        final String key = (String) postSnapshot.getKey();
                        if(uid != null){
                            System.out.println("here : " + uid + "/displayPictures/" + key +".jpg");
                            StorageReference imageRef = mStorageReference.child(uid + "/displayPictures/" + key +".jpg");
                            Fimages.add(imageRef);

//
//                            mStorageReference.child(uid + "/displayPictures/" + key +".jpg").getDownloadUrl()
//                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            System.out.println("here : " + uid + "/displayPictures/" + key +".jpg");
//                                            StorageReference imageRef = mStorageReference.child(uid + "/displayPictures/" + key +".jpg");
//
//                                            Fimages.add(imageRef);
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Fimages.add(mStorageReference.child("dpic.jpg"));
//                                        }
//                                    });
                        }
                        else{
                            Fimages.add(mStorageReference.child("dpic.jpg"));
                        }


//                        Toast.makeText(listOfPeople.this, value.get("name") + " added", Toast.LENGTH_SHORT).show();
                        System.out.println("key :" + key);
                        System.out.println("uid :" + uid);

                        MyAdapter adapter = new MyAdapter(listOfPeople.this, Fnames.toArray(new String[Fnames.size()]), Fages.toArray(new Integer[Fages.size()]), Fimages.toArray(new StorageReference[Fimages.size()]));

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

        MyAdapter adapter = new MyAdapter(this, Fnames.toArray(new String[Fnames.size()]),
                Fages.toArray(new Integer[Fages.size()]),
                Fimages.toArray(new StorageReference[Fimages.size()]));

        list = (ListView) findViewById(R.id.peopleListView);
        list.setAdapter(adapter);
    }

    class MyAdapter extends ArrayAdapter<String>{
        private final Context context;
        private final String[] names;
        private final Integer[] ages;
        private final StorageReference[] images;

        public MyAdapter(@NonNull Context context, String[] names, Integer[] ages, StorageReference[] images) {
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

            System.out.println(images);
            Glide.with(listOfPeople.this)
                    .using(new FirebaseImageLoader())
                    .load(images[i])
                    .into(iv1);

//            iv1.setImageResource(images[i]);
            name.setText(names[i].length()<=25 ? names[i] : names[i].substring(0, 25)+ "..." );
            age.setText(String.valueOf( ages[i] ));


            return rowView;
        }
    }
}
