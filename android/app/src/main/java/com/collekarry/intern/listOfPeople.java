package com.collekarry.intern;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.collekarry.intern.MyNotificationManager.CHANNEL_DESCRIPTION;
import static com.collekarry.intern.MyNotificationManager.CHANNEL_ID;
import static com.collekarry.intern.MyNotificationManager.CHANNEL_NAME;


public class listOfPeople extends AppCompatActivity
{
    DatabaseReference nameList;
    RecyclerView list;
    List<wardClass> uploadList;
    SwipeRefreshLayout srl;

    List<StorageReference> Fimages;
    List<String> dsList;
    NotificationCompat.Builder notification;
    private static final int notId = 12321;

    private StorageReference mStorageReference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menubar, menu);
        return true;
    }


    public void addWard(View v){
        startActivity(new Intent(listOfPeople.this, addPersonActivity.class));
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
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setElevation(0);

        if(FirebaseAuth.getInstance().getUid() == null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        nameList = FirebaseDatabase.getInstance().getReference("wards").child(FirebaseAuth.getInstance().getUid());
        nameList.keepSynced(true);

        uploadList = new ArrayList<>();

        Fimages = new ArrayList<>();
        dsList = new ArrayList<>();

        mStorageReference = FirebaseStorage.getInstance().getReference();

        list = (RecyclerView) findViewById(R.id.peopleListView);

        srl = findViewById(R.id.swipe_container);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                startActivity(new Intent( getApplicationContext() ,listOfPeople.class));

            }
        });


        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
        String currentDateTimeString = sdf.format(d);
        Log.i("data",currentDateTimeString);


//            displayNotification();
//        aList = new ArrayList<>();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }

        /*
         * Displaying a notification locally
         */
        MyNotificationManager.getInstance(this).displayNotification("Greetings", "Hello how are you?");



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


                            list.setAdapter(adapter);

                            RecyclerView.LayoutManager lm = new LinearLayoutManager(listOfPeople.this);
                            list.setLayoutManager(lm);
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

        list = (RecyclerView) findViewById(R.id.peopleListView);
        list.setAdapter(adapter);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(listOfPeople.this);
        list.setLayoutManager(lm);

        FloatingActionButton fab = findViewById(R.id.ambulance_call_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + 102));
                startActivity(i);
            }
        });

    }

    public void displayNotification()
    {
        String cId = "abcabc";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationManager notificationManager = (NotificationManager)
                                                    getSystemService(getApplicationContext().NOTIFICATION_SERVICE);


            CharSequence name = "Chnnel Name";
            String description = "Channel Descp";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(cId, name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.setLightColor(Color.WHITE);
            channel.enableVibration(false);

            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, cId)
                .setSmallIcon(R.drawable.appointments_ic)
                .setContentTitle("title")
                .setContentText("kdfhjkgasfbsdjfbdgbjhbsg")
                .setLights(Color.WHITE,500,500)
                .setColor(Color.RED)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(12312, mBuilder.build());

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private final Context context;
        private final List<wardClass> listElemets;
        private final List<StorageReference> imageReferences;


        public MyAdapter(@NonNull Context context, List<wardClass> listElements, List<StorageReference> imageReferences) {
            this.listElemets = listElements;
            this.imageReferences = imageReferences;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_listview_layout, parent, false);
            ViewHolder vh = new ViewHolder(row);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            Glide.with(listOfPeople.this)
                    .using(new FirebaseImageLoader())
                    .load(imageReferences.get(position))
                    .centerCrop()
                    .into(holder.iv1);

            holder.name.setText(listElemets.get(position).getName());
            holder.age.setText(String.valueOf(listElemets.get(position).getAge()));
            holder.gender.setText(listElemets.get(position).getGender());

            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), WardDetailsActivity.class);
                    intent.putExtra("key", uploadList.get(position).getKey());

                    startActivity(intent);
                }
            });
        }


        @Override
        public int getItemCount() {
            if (listElemets == null){
                return 0;
            }
            else{
                return listElemets.size();
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView iv1 ;
            View row;
            TextView name ;
            TextView age ;
            TextView gender;

            public ViewHolder(View rowView) {
                super(rowView);
                row = rowView;
                iv1 = (ImageView) rowView.findViewById(R.id.displayPicture);
                name = (TextView) rowView.findViewById(R.id.name);
                age = (TextView) rowView.findViewById(R.id.age);
                gender = (TextView) rowView.findViewById(R.id.gender);
            }
        }
    }
}
