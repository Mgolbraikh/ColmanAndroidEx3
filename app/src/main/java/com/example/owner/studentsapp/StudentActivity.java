package com.example.owner.studentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class StudentActivity extends Activity {

    private EditText StudentID;
    private EditText StudentName;
    private EditText StudentAddress;
    private EditText StudentPhone;
    private CheckBox StudentChecked;
    private Student _currStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO : Show the student picture in the start the default

        // On create of new student
        StudentID = (EditText) findViewById(R.id.StudentIDAdd);
        StudentName = (EditText) findViewById(R.id.StudentNameAdd);
        StudentAddress = (EditText) findViewById(R.id.StudentAddressAdd);
        StudentPhone = (EditText) findViewById(R.id.StudentPhoneAdd);
        StudentChecked = (CheckBox) findViewById(R.id.StudentcheckBoxAdd);
        // StudentChecked = ()

        Button save = (Button) findViewById(R.id.StudentAddAddButton);
        if (getIntent().getIntExtra("id",-20) != -20){
            _currStudent = Model.instance().getStudent(getIntent().getExtras().getInt("id"));
            StudentID.setText(Integer.toString(_currStudent.getId()));
            StudentName.setText(_currStudent.getName());
            StudentAddress.setText(_currStudent.getAddress());
            StudentPhone.setText(_currStudent.getPhone());
            StudentChecked.setChecked(_currStudent.getChecked());

            View delButton = findViewById(R.id.StudentDeleteButton);
                    delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Model.instance().deleteStudent(_currStudent);
                    getIntent().putExtra("del",true);
                    setResult(RESULT_OK,getIntent());
                    finish();
                }
            });
            delButton.setVisibility(View.VISIBLE);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _currStudent.setAddress(StudentAddress.getText().toString());
                    _currStudent.setId(Integer.parseInt(StudentID.getText().toString()));
                    _currStudent.setName(StudentName.getText().toString());
                    _currStudent.setPhone(StudentPhone.getText().toString());
                    _currStudent.setChecked(StudentChecked.isChecked());
                    setResult(RESULT_OK,getIntent());
                    finish();

                }
            });


        }else {
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
                    getIntent().putExtra("changed",true);
                    setResult(RESULT_OK,getIntent());
                    finish();
                }
            });
        }
    }


    /*
* This method override the button pressed back for the app
*
* */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:{
                onBackPressed();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }


}
