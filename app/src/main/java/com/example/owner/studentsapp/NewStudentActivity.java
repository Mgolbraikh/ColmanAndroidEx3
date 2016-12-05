package com.example.owner.studentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import static com.example.owner.studentsapp.R.id.StudentImage;
import static com.example.owner.studentsapp.R.id.StudentName;

public class NewStudentActivity extends Activity {

    private EditText StudentID;
    private EditText StudentName;
    private EditText StudentAddress;
    private EditText StudentPhone;
    private ImageView StudentImage;
    private CheckBox StudentChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        // TODO : Show the student picture in the start the default

        // On create of new student
        StudentID = (EditText) findViewById(R.id.StudentIDAdd);
        StudentName = (EditText) findViewById(R.id.StudentNameAdd);
        StudentAddress = (EditText) findViewById(R.id.StudentAddressAdd);
        StudentPhone = (EditText) findViewById(R.id.StudentPhoneAdd);
        StudentImage = (ImageView) findViewById(R.id.StudentImageAdd);
        StudentChecked = (CheckBox) findViewById(R.id.StudentcheckBoxAdd);
        // StudentChecked = ()

        Button save = (Button) findViewById(R.id.StudentAddAddButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student st = new Student(StudentName.getText().toString(),
                        Integer.parseInt(StudentID.getText().toString()),
                        StudentAddress.getText().toString(),
                        StudentPhone.getText().toString());

                // Adding  checked and picture
                st.setChecked(StudentChecked.isChecked());

                Model.instance().addStudent(st);
                Log.d("TAG", "saving student to the db");
            }
        });
    }
}
