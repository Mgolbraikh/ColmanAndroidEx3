package com.example.owner.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class StudentListActivity extends Activity {
    List<Student> studentsList;
    StudentsAdapter _adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        studentsList = Model.instance().getAllStudents();
        ListView list = (ListView) findViewById(R.id.studentlistViewview);

         _adapter = new StudentsAdapter();
        list.setAdapter(_adapter);
        Button btAddStudent = (Button) findViewById(R.id.StudentAddListviewButton);

        // Opening new student activity
        btAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                startActivityForResult(intent,2);
            }
        });
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 2:
                // Checking if update is needed
                if (resultCode == RESULT_OK && data.getBooleanExtra("changed", false)) {
                    _adapter.notifyDataSetChanged();
                }
                break;
        }
    }
    class StudentsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return studentsList.size();
        }

        @Override
        public Object getItem(int i) {
            return studentsList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.studentlistrow, null);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openDetails = new Intent(StudentListActivity.this, StudentDetailsActivity.class);
                        openDetails.putExtra("id",((Student)getItem((int)view.getTag())).getId());
                        startActivityForResult(openDetails,2);

                    }
                });
                // TODO add checkbox change
                    final CheckBox cb = (CheckBox) view.findViewById(R.id.checkBoxRow);
                    cb.setOnClickListener(new View.OnClickListener() {
//                        @Override
                        public void onClick(View view) {
                            Student currStudent = (Student)getItem((int)((View)view.getParent()).getTag());
                            currStudent.setChecked(!currStudent.getChecked());
                        }
                    });
            }

            // Setting tag to identify current index
            view.setTag(i);
            Student currStudent = (Student)getItem(i);
            TextView nameTv = (TextView) view.findViewById(R.id.StudentName);
            TextView idTv = (TextView) view.findViewById(R.id.StudentID);
            nameTv.setText(currStudent.getName());
            CheckBox cb = (CheckBox) view.findViewById(R.id.checkBoxRow);
            cb.setChecked(currStudent.getChecked());
            idTv.setText(((Integer)currStudent.getId()).toString());
            return view;
        }
    }
}


