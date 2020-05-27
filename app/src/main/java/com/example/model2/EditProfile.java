package com.example.model2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {
    EditText username;
    EditText dob;
    EditText password;
    RadioButton male;
    RadioButton female;
    Button search;
    Button edit;
    Button delete;
    DBHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abc_search_view);

       // myDb = new DBHelper(this);

        username=findViewById(R.id.editText9);
        dob=findViewById(R.id.editText10);
        password=findViewById(R.id.editText11);
        male=findViewById(R.id.radioButton5);
        female=findViewById(R.id.radioButton6);
    }
    public void deleteData () {
        delete.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Integer deleterows = myDb.deleteInfo(username.getText().toString());
                        if (deleterows > 0)
                            Toast.makeText(EditProfile.this, "Details Are Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditProfile.this, "Details Are Not Deleted", Toast.LENGTH_LONG).show();
                    }
                }

        );
    }

    public void updateData() {

        edit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateInfor(username.getText().toString(),
                                dob.getText().toString(),
                                male.getText().toString());

                        if (isUpdate == true)

                            Toast.makeText(EditProfile.this, "Details Are Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditProfile.this, "Details Are Not Updated", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll() {

        search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.readAllInfor();
                        if (res.getCount() == 0) {
                            showMessage("Error", "Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("User name :" + res.getString(1) + "\n");
                            buffer.append("Date of birth :" + res.getString(2) + "\n");
                            buffer.append("gender :" + res.getString(3) + "\n");



                        }

                        showMessage("user Details", buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title, String Messsage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messsage);
        builder.show();


    }
}
