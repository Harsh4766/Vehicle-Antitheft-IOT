package com.example.vehicleautomation;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button b3,b4;
    DBHelper DB;
    boolean isAllFieldsChecked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        DB = new DBHelper(this);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this,MainActivity.class));
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    String name = e1.getText().toString();
                    String email = e2.getText().toString();
                    String number = e3.getText().toString();
                    String password = e4.getText().toString();

                    Boolean insert = DB.insertData(name, email, number, password);
                    if (insert == true) {
                        Toast.makeText(signup.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this,home.class));
                    }
                }
            }
        });

    }
    public boolean CheckAllFields()
    {
        if(e1.length()==0)
        {
            e1.setError("Name is required");
            return false;
        }
        if(e2.length()==0)
        {
            e2.setError("Email is required");
            return false;
        }
        if(e3.length()==0)
        {
            e3.setError("Mobile number is required");
            return false;
        }
        if(e4.length()==0)
        {
            e4.setError("Password is required");
            return false;
        }
        else if(e4.length()<8)
        {
            e4.setError("Password must be minimum 8 characters");
            return false;
        }
        return true;
    }
}