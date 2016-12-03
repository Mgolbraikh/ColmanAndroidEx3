package com.example.owner.studentsapp;

import android.app.Activity;
import android.content.Intent;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        // Recieve the student
        Intent intent = getIntent();
        String StudentId = intent.getExtras().get("id").toString();

        // TODO  - Delete this debug row
        Log.d("TAG","got id = " + StudentId);

        Student st = Model.instance().getStudent(Integer.parseInt(StudentId));

        TextView txId = (TextView) findViewById(R.id.StudentIdView);
        TextView Name = (TextView) findViewById(R.id.StudentNameView);
        TextView Address = (TextView) findViewById(R.id.StudentAddressView);
        TextView phone = (TextView) findViewById(R.id.StudentPhoneView);
        CheckBox checked = (CheckBox) findViewById(R.id.StudentcheckBoxView);
        // TODO : display image to screen
        //TextView phone = (ImageView) view.findViewById(R.id.StudentImageView);

        txId.setText(Integer.toString(st.getId()));
        Name.setText(st.getName());
        Address.setText(st.getAddress());
        checked.setChecked(st.getChecked());
        phone.setText(st.getPhone());

        // TODO _ display image
        //txId.setText(st.getId());

    }
}
