package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Book;
import com.kunja.librarymanagementsystem.model.Student;

public class DetailStudent extends AppCompatActivity {
    public static final String EXTRA_STUDENTID = "studentId";
    int studentId;
    TextView student_name,student_email,student_phoneno,student_address,student_availability,student_book_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        studentId = (Integer)getIntent().getExtras().get(EXTRA_STUDENTID);
        DBHandler db = new DBHandler(DetailStudent.this);
        Student student = db.getStudentDetail(studentId);
        String s = db.getStudentBookList(studentId);

        student_name = (TextView)findViewById(R.id.student_name);
        student_name.setText(student.getStudentName());

        student_email = (TextView)findViewById(R.id.student_email);
        student_email.setText(student.getStudentEmail());

        student_phoneno = (TextView)findViewById(R.id.student_phoneno);
        student_phoneno.setText(student.getStudentPhoneNumber());

        student_address = (TextView)findViewById(R.id.student_address);
        student_address.setText(student.getStudentAddress());

        student_availability = (TextView)findViewById(R.id.student_availability);
        student_availability.setText(student.getStudentAvailability());

        student_book_list = (TextView)findViewById(R.id.student_book_list);
        if(s.isEmpty()) {
            student_book_list.setText("None");
        }
        else{
            student_book_list.setText(s);
        }
    }


    public void onCall(View view) {
        studentId = (Integer)getIntent().getExtras().get(EXTRA_STUDENTID);
        DBHandler db = new DBHandler(DetailStudent.this);
        Student student = db.getStudentDetail(studentId);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+student.getStudentPhoneNumber()));
        startActivity(intent);
    }
}