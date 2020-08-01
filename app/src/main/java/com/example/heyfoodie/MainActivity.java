package com.example.heyfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   EditText email, password;
   Button signin, signup;
   DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.Email);
        password=findViewById(R.id.Password);
        signin=findViewById(R.id.Signin);
        signup=findViewById(R.id.Signup);
        dataBaseHelper=new DataBaseHelper(this);

       signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String emailvalue=email.getText().toString();
               String passwordvalue=password.getText().toString();

               if(dataBaseHelper.isloginvalid(emailvalue,passwordvalue)){

                   Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(MainActivity.this, HomeActivity.class );
                   intent.putExtra("Key",emailvalue);
                   startActivity(intent);
                   finish();
               }
               else{
                   Toast.makeText(MainActivity.this,"Invalid Username or Password!",Toast.LENGTH_SHORT).show();
               }
           }
       });
       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,signup.class);
               startActivity(intent);
               finish();
           }
       });
    }
}