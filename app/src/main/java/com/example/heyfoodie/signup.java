package com.example.heyfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    EditText firstname, lastname, phoneno, address, email, password, confirmpassword;
    RadioGroup gender;
    Button signup, cancel;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstname=findViewById(R.id.FirstName);
        lastname=findViewById(R.id.LastName);
        phoneno=findViewById(R.id.PhoneNo);
        address=findViewById(R.id.Address);
        email=findViewById(R.id.Email);
        gender=findViewById(R.id.ChooseGender);
        signup=findViewById(R.id.Signup);
        cancel=findViewById(R.id.Cancel);
        password=findViewById(R.id.Password);
        confirmpassword=findViewById(R.id.confirmPassword);
        dataBaseHelper =new DataBaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstnamevalue=firstname.getText().toString();
                String lastnamevalue=lastname.getText().toString();
                String phonenovalue=phoneno.getText().toString();
                String addressvalue=address.getText().toString();
                String emailvalue=email.getText().toString();
                String passwordvalue=password.getText().toString();
                String confirmpasswordvalue=confirmpassword.getText().toString();
                RadioButton checkedbtn=findViewById(gender.getCheckedRadioButtonId());
                String gendervalue=checkedbtn.getText().toString();

                if(passwordvalue.equals(confirmpasswordvalue ) && emailvalue.length()>1 ) {

                    Boolean result = dataBaseHelper.insertuser(firstnamevalue,lastnamevalue,phonenovalue,addressvalue,emailvalue,passwordvalue,gendervalue);

                    if (!result) {
                        Toast.makeText(signup.this, " Something Went wrong ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(signup.this,"User Registered! ",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(signup.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(signup.this,"Password field and confirm Password field must be same OR Email is empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signup.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}