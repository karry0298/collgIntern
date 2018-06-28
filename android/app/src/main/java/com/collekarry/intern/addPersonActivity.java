package com.collekarry.intern;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addPersonActivity extends AppCompatActivity {

    private Button submitButton ;
    private EditText nameView;
    private EditText ageView;
    private Switch genderView;

    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;
    private com.mikhaellopez.circularimageview.CircularImageView imageView;

    private String userChosenTask;
    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        nameView = findViewById(R.id.nameInput);
        ageView = findViewById(R.id.ageInput);
        genderView = findViewById(R.id.genderSwitch);
        submitButton = (Button) findViewById(R.id.submitButton);
        imageView = findViewById(R.id.imageButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }


    public void submitButtonClicked(View v){
        String name = nameView.getText().toString().trim();
        int age = ageView.getText().toString().equals("") ? 0 : Integer.valueOf(ageView.getText().toString());
        String gender;

        if(!name.matches("^[a-zA-Z\\s]{3,20}$")){
            nameView.setError("Enter a valid name");
        }
        else if( age<30 || age>200 ){
            ageView.setError("Enter a valid age");
        }
        else{
            if(genderView.isChecked()){
                gender = "Female";
            }
            else{
                gender = "Male";
            }

            wardClass ward = new wardClass(name,age,gender,223222);

            final String key = mDatabase.child("wards").push().getKey();

            mDatabase.child("wards").child(key).setValue(ward)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(addPersonActivity.this, "key: "+key+" added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(addPersonActivity.this, listOfPeople.class));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(addPersonActivity.this, "Failed successfully", Toast.LENGTH_SHORT).show();
                        }
                    });



//            Map<String, Object> wardValues = ward.toMap();
//
//            Map<String, Object> childUpdates = new HashMap<>();
//            childUpdates.put("/wards/" + key, wardValues);
//
//            mDatabase.updateChildren(childUpdates);



        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void imageClicked(View v){

        final CharSequence[] items = { "Camera", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(addPersonActivity.this);
        if(imageView.getDrawable().equals(getDrawable(R.drawable.dpic))){
            builder.setTitle("Select Display picture :");
        }
        else{
            builder.setTitle("Change Display picture :");
        }

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Camera")) {
                    userChosenTask = "Camera";
                    boolean result = checkPermissions(addPersonActivity.this);
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChosenTask = "Choose from Library";
                    boolean result = checkPermissions(addPersonActivity.this);
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void cameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
    public void galleryIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select photo"),1);
    }

    public boolean checkPermissions(final Context context) {
        if(userChosenTask.equals("Choose from Library")) {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        }
        else{
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity) context, new String[] {android.Manifest.permission.CAMERA}, 124);
                cameraIntent();
                return false;
            }
            return true;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChosenTask.equals("Camera"))
                        cameraIntent();
                    else if(userChosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    Toast.makeText(this, "Need permissions", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1)               //1 for gallery
                onSelectFromGalleryResult(data);
            else if (requestCode == 0)          //0 for camera
                onCaptureImageResult(data);
        }
    }

    void onSelectFromGalleryResult(Intent data){
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView.setImageBitmap(bm);
        }
    }

    void onCaptureImageResult(Intent data){

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Objects.requireNonNull(thumbnail).compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(thumbnail);
    }

}
