package com.example.owner.studentsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentDetailsActivity extends Activity {

    Student st;
    TextView txId;
    TextView Name;
    TextView Address;
            TextView phone;
    CheckBox checked;
            ImageView stdImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Recieve the student
        st = Model.instance().getStudent(getIntent().getExtras().getInt("id"));

         txId = (TextView)findViewById(R.id.StudentIdView);
         Name = (TextView) findViewById(R.id.StudentNameView);
         Address = (TextView) findViewById(R.id.StudentAddressView);
         phone = (TextView) findViewById(R.id.StudentPhoneView);
         checked = (CheckBox) findViewById(R.id.StudentcheckBoxView);
         stdImage = (ImageView) findViewById(R.id.StudentImageView);

        setTextViews();

        //TODO - change the set image to work with the student png
        stdImage.setImageResource(R.drawable.images);

        findViewById(R.id.StudentEditButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editStudent = new Intent(StudentDetailsActivity.this, StudentActivity.class);
                editStudent.putExtra("id",st.getId());
                startActivityForResult(editStudent,1);
            }
        });
        // TODO _ display image
        //txId.setText(st.getId());

    }

    @Override
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

    /*
         * Sets the view text
         *
         */
    private void setTextViews() {
        txId.setText(Integer.toString(st.getId()));
        Name.setText(st.getName());
        Address.setText(st.getAddress());
        checked.setChecked(st.getChecked());
        phone.setText(st.getPhone());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1:

                // Adding changed if needed
                if (resultCode == RESULT_OK) {
                    if (data.getBooleanExtra("del",false)) {
                        getIntent().putExtra("changed", true);
                        setResult(RESULT_OK,getIntent());
                        finish();
                    } else {
                        getIntent().putExtra("changed", true);
                        setResult(RESULT_OK,getIntent());
                        setTextViews();
                    }
                }
                break;
        }
    }
}
