package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Book;
import com.kunja.librarymanagementsystem.model.Student;

import java.util.List;

public class ViewStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        DBHandler db = new DBHandler(ViewStudent.this);
        final List<Student> students = db.getAllStudent();

        ArrayAdapter<Student> listAdapter = new ArrayAdapter<>(ViewStudent.this, android.R.layout.simple_list_item_1, students);
        ListView listStudents = findViewById(R.id.list_students);
        listStudents.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listStudents,
                                            View itemView,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(ViewStudent.this,
                                DetailStudent.class);
                        intent.putExtra(DetailStudent.EXTRA_STUDENTID, students.get((int)id).getStudentId());
                        startActivity(intent);
                    }
                };
        listStudents.setOnItemClickListener(itemClickListener);
    }
}