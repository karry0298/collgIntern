package com.collekarry.intern;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    static String uId;
    EditText email,password;
    Button sign;


    public void loginClick(View view)
    {
        RelativeLayout layImg = findViewById(R.id.bgLayout);
        layImg.animate().alpha(0.6f);

        RelativeLayout layLog = findViewById(R.id.login);
//        layLog.animate().translationY(300f);
        layLog.setVisibility(layLog.VISIBLE);
//        layLog.animate().translationY(-300f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.Einput);
        password = (EditText) findViewById(R.id.passInupt);
        sign = (Button) findViewById(R.id.signIn);

        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        uId = email.getText().toString();

        if(!uId.equals("") && !password.equals(""))
        {
            Intent intent = new Intent(getApplicationContext(),listOfPeople.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Enter the containts...", Toast.LENGTH_SHORT).show();
    }

}
