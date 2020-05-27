package com.example.model2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ProfileManagement extends AppCompatActivity {

    EditText username;
    EditText dateofbirth;
    EditText Password;
    RadioButton male;
    RadioButton female;
    Button updateprofile;
    DBHelper db4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abc_tooltip);



        username=findViewById(R.id.editText3);
        dateofbirth=findViewById(R.id.editText5);
        Password=findViewById(R.id.editText6);
        male=findViewById(R.id.radioButton);
        female=findViewById(R.id.radioButton2);
        updateprofile=findViewById(R.id.button3);

        addinfor();
    }
    public void addinfor() {

        updateprofile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String user = username.getText().toString().trim();
                        String date = dateofbirth.getText().toString().trim();
                        String pass = Password.getText().toString().trim();
                      String gen = male.getText().toString().trim();
                        // String amount = amount.getText().toString().trim();

                        if(user.isEmpty()){
                            username.setError("Enter the username");
                            username.requestFocus();
                            return;
                        }

                        if(date.isEmpty()){
                            dateofbirth.setError("Enter the dateof birth");
                            dateofbirth.requestFocus();
                            return;
                        }
                        if(pass.isEmpty()){
                            Password.setError("Enter the password");
                            Password.requestFocus();
                            return;
                        }
                        if(gen.isEmpty()){
                            male.setError("Enter the gender");
                            male.requestFocus();
                            return;
                        }


                        boolean isInserted = db4.addInfo(user,date,gen);

                        if (isInserted == true)
                            Toast.makeText(ProfileManagement.this, "Success", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProfileManagement.this, "failure", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

}
