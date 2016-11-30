package com.example.owner.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class StudentListActivity extends Activity {
    List<Student> studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        studentsList = Model.instance().getAllStudents();
        ListView list = (ListView) findViewById(R.id.studentlistViewview);

        StudentsAdapter adapter = new StudentsAdapter();
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "row selected " + i);
                Intent intent = new Intent(getApplicationContext(), StudentDetails.class);
                intent.putExtra("id", studentsList.get(i).getId());
                startActivity(intent);
            }
        });
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
                        Intent openEdit = new Intent(StudentListActivity.this, NewStudent.class);
                        openEdit.putExtra("id",index);
                        startActivity(openEdit);

                    }
                });
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

            Student st = studentsList.get(i);
            TextView nameTv = (TextView) view.findViewById(R.id.StudentName);
            TextView idTv = (TextView) view.findViewById(R.id.StudentID);
            nameTv.setText(st.getName());
            //CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox);
           // cb.setChecked(st.getChecked());
           // cb.setTag(new Integer(i));
            idTv.setText(((Integer)st.getId()).toString());
            return view;
        }
    }
}


