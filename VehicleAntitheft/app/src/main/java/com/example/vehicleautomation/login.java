package com.example.vehicleautomation;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class login extends AppCompatActivity {
    EditText e5, e6;
    Button b5,b6;
    DBHelper DB;
    boolean isAllFieldsChecked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e5 = (EditText) findViewById(R.id.editText5);
        e6 = (EditText) findViewById(R.id.editText6);
        b5=(Button)findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        DB = new DBHelper(this);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,MainActivity.class));
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    String number = e5.getText().toString();
                    String password = e6.getText().toString();


                    Boolean checknumpass = DB.checknumberpassword(number, password);
                    if (checknumpass == true) {
                        Toast.makeText(login.this, "Login successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this,home.class));
                    } else {
                        Toast.makeText(login.this, "Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public boolean CheckAllFields()
    {
        if(e5.length()==0)
        {
            e5.setError("Mobile number is required");
            return false;
        }
        if(e6.length()==0)
        {
            e6.setError("Password is required");
            return false;
        }
        return true;
    }
}