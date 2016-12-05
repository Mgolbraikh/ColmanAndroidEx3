package com.example.owner.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
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

        studentsList = Model.instance().getAllStudents();
        ListView list = (ListView) findViewById(R.id.studentlistViewview);

         _adapter = new StudentsAdapter();
        list.setAdapter(_adapter);

        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Object o = ((ListView) (view)).getItemAtPosition(position);
                // TODO Delete this debug row
                Log.d("TAG", "row selected " + position);
                Intent intent = new Intent(getApplicationContext(), StudentDetailsActivity.class);
                intent.putExtra("id", ((Integer)studentsList.get(position).getId()).toString());
                startActivity(intent);
            }
        });

        Button btAddStudent = (Button) findViewById(R.id.StudentAddListviewButton);

        btAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 2:
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
                final int index = i;
                view = getLayoutInflater().inflate(R.layout.studentlistrow, null);
                //view.setClickable(true);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openDetails = new Intent(StudentListActivity.this, StudentDetailsActivity.class);
                        openDetails.putExtra("id",((Student)getItem(index)).getId());
                        startActivityForResult(openDetails,2);

                    }
                });
                // TODO add checkbox change
//                    final CheckBox cb = (CheckBox) view.findViewById(R.id.studentRowCheckBox);
//                    cb.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Integer index = (Integer)cb.getTag();
//                            Student st = studentsList.get(index);
//                            st.setChecked(!st.getChecked());
//                            Log.d("TAG","onCheckedChanged " + index + "name " + st.getName());
//                        }.
//                    });
//
            }

            // TODO show image
            Student st = studentsList.get(i);
            TextView nameTv = (TextView) view.findViewById(R.id.StudentName);
            TextView idTv = (TextView) view.findViewById(R.id.StudentID);
            nameTv.setText(st.getName());
            CheckBox cb = (CheckBox) view.findViewById(R.id.checkBoxRow);
            cb.setChecked(st.getChecked());
            // cb.setTag(new Integer(i));
            idTv.setText(((Integer)st.getId()).toString());
            return view;
        }
    }
}


