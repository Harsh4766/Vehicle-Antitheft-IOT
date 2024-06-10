package com.example.vehicleautomation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class gps extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        e1=findViewById(R.id.editText9);
        e2=findViewById(R.id.editText10);
        b1=findViewById(R.id.button8);
        b3=findViewById(R.id.button10);
        e3=findViewById(R.id.editText11);
        ActivityCompat.requestPermissions(gps.this,new String[]{Manifest.permission.READ_SMS},PackageManager.PERMISSION_GRANTED);
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
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e3.getText().toString();
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(s1));
                startActivity(intent);
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

    public void Read_SMS(View view)
    {
        Cursor cursor=getContentResolver().query(Uri.parse("content://sms"),null,null,null,null);
        cursor.moveToFirst();


        e3.setText(cursor.getString(12));
    }
}