package com.example.owner.studentsapp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by owner on 29-Nov-16.
 */

public class Model {
    final private static Model instance = new Model();
    private Model(){
        for (int i =0;i<100;i++){
            Student st = new Student("name " + i,i,"Address  " + i,((Integer)(i*127)).toString());
            addStudent(st);
        }
    }

    public static Model instance(){
        return instance;
    }

    private List<Student> studentsData = new LinkedList<>();


    public List<Student> getAllStudents(){
        return studentsData;
    }

    public void addStudent(Student item){
        studentsData.add(item);
    }

}
