package com.example.owner.studentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static com.example.owner.studentsapp.R.id.StudentImage;
import static com.example.owner.studentsapp.R.id.StudentName;

public class NewStudent extends Activity {

    private EditText StudentID;
    private EditText StudentName;
    private EditText StudentAddress;
    private EditText StudentPhone;
    private ImageView StudentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        // On create of new student
        StudentID = (EditText) findViewById(R.id.StudentIDAdd);
        StudentName = (EditText) findViewById(R.id.StudentNameAdd);
        StudentAddress = (EditText) findViewById(R.id.StudentAddressAdd);
        StudentPhone = (EditText) findViewById(R.id.StudentPhoneAdd);
        StudentImage = (ImageView) findViewById(R.id.StudentImageAdd);
        // Todo : the checked box item
        // StudentChecked = ()

        Button save = (Button) findViewById(R.id.StudentAddAddButton);
    }
}
