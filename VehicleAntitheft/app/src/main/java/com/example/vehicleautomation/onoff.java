package com.example.vehicleautomation;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class onoff extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onoff);
        e1=findViewById(R.id.editText9);
        e2=findViewById(R.id.editText10);
        b1=findViewById(R.id.button8);
        i1=findViewById(R.id.imageView1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
                    {

                        sendsms();
                    }
                    else
                    {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
        });
    }
    public void sendsms()
    {
        String s1=e1.getText().toString().trim();
        String s2=e2.getText().toString().trim();

        SmsManager smsManager= SmsManager.getDefault();
        smsManager.sendTextMessage(s1,null,s2,null,null);
        Toast.makeText(this,"Message is sent",Toast.LENGTH_SHORT).show();


    }
}