package com.collekarry.docside;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
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

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class listOfPeople extends AppCompatActivity
{
    DatabaseReference nameList;
    ListView list;
    List<wardClass> uploadList;

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

        if(FirebaseAuth.getInstance().getUid() == null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        nameList = FirebaseDatabase.getInstance().getReference("wards").child(FirebaseAuth.getInstance().getUid());
        nameList.keepSynced(true);

        uploadList = new ArrayList<>();

        Fimages = new ArrayList<>();
        dsList = new ArrayList<>();

        mStorageReference = FirebaseStorage.getInstance().getReference();



        nameList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    HashMap value = (HashMap) postSnapshot.getValue();
//                    final String uid = (String) value.get("uid");
                    wardClass ward = postSnapshot.getValue(wardClass.class);
//                    wardClass ward = new wardClass(
//                            value.get("key").toString(),
//                            value.get("name").toString(),
//                            Integer.valueOf(Long.toString((Long) value.get("age"))),
//                            value.get("gender").toString(),
//                            value.get("uid").toString()
//                    );



                    if (ward.getUid() != null && FirebaseAuth.getInstance().getUid() != null) {
                        if (FirebaseAuth.getInstance().getUid().equals(ward.getUid())) {

                            if (!dsList.contains(value.get("key").toString() )) {

                                dsList.add(value.get("key").toString());
//                        System.out.println(dsList);


                                uploadList.add(ward);
//                                Fnames.add((String) value.get("name"));
//                                Fages.add(Integer.valueOf(Long.toString((Long) value.get("age"))));


                                final String key = (String) postSnapshot.getKey();
//                                System.out.println("here : " + uid + "/displayPictures/" + key + ".jpg");
                                StorageReference imageRef = mStorageReference
                                        .child(ward.getUid() + "/displayPictures/" + ward.getKey() + ".jpg");
                                Fimages.add(imageRef);

//                                System.out.println("key :" + key);
//                                System.out.println("uid :" + uid);
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


//                        Toast.makeText(listOfPeople.this, value.get("name") + " added", Toast.LENGTH_SHORT).show();


                            MyAdapter adapter = new MyAdapter(listOfPeople.this,
                                    uploadList,
                                    Fimages);

                            list = (ListView) findViewById(R.id.peopleListView);
                            list.setAdapter(adapter);
                        }
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

        MyAdapter adapter = new MyAdapter(this,
                uploadList,
                Fimages);

        list = (ListView) findViewById(R.id.peopleListView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), WardDetailsActivity.class);
                intent.putExtra("key", uploadList.get(position).getKey());

                startActivity(intent);
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String>{
        private final Context context;

        private final List<wardClass> listElemets;
        private final List<StorageReference> imageReferences;


        public MyAdapter(@NonNull Context context, List<wardClass> listElements, List<StorageReference> imageReferences) {
            super(context, R.layout.people_listview_layout);

            this.listElemets = listElements;
            this.imageReferences = imageReferences;
            this.context = context;
        }

        @Override
        public int getCount() {
            return listElemets.size();
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
            ImageView iv2 = (ImageView) rowView.findViewById(R.id.ivv);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            TextView age = (TextView) rowView.findViewById(R.id.age);

//            System.out.println(images);
            Glide.with(listOfPeople.this)
                    .using(new FirebaseImageLoader())
                    .load(imageReferences.get(i))
                    .centerCrop()
                    .into(iv1);

            wardClass ward = listElemets.get(i);
//            iv1.setImageResource(images[i]);
            name.setText(ward.getName().length()<=25 ? ward.getName() : ward.getName().substring(0, 25)+ "..." );
            age.setText( String.valueOf(ward.getAge())  );

            if(ward.getImp().equals("false"))
            {
                iv2.setVisibility(INVISIBLE);
            }
            else
            {
                iv2.setVisibility(VISIBLE);
            }

            return rowView;
        }
    }
}
