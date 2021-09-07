package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Student;

public class AddStudent extends AppCompatActivity {
    EditText name, email,number,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name=findViewById(R.id.editTextName);
        email=findViewById(R.id.editTextEmail);
        number=findViewById(R.id.editTextPhoneNumber);
        address=findViewById(R.id.editTextAddress);
    }

    public void onAddStudent(View view) {
        DBHandler db = new DBHandler(AddStudent.this);
        Student s= new Student();
        String studentName = name.getText().toString();
        String studentEmail = email.getText().toString();
        String studentNumber = number.getText().toString();
        String studentAddress = address.getText().toString();
        String bookAvailability = "Yes";

        if (studentName.isEmpty() && studentEmail.isEmpty() && studentNumber.isEmpty() && studentAddress.isEmpty()){
            Toast.makeText(AddStudent.this, "All Fields are empty", Toast.LENGTH_SHORT).show();

        }
        else if (studentName.isEmpty() || studentEmail.isEmpty() ||studentNumber.isEmpty() ||studentAddress.isEmpty()){
            Toast.makeText(AddStudent.this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }

        else{
            s.setStudentName(studentName);
            s.setStudentEmail(studentEmail);
            s.setStudentPhoneNumber(studentNumber);
            s.setStudentAddress(studentAddress);
            s.setStudentAvailability(bookAvailability);
            db.addStudent(s);
            Toast.makeText(AddStudent.this, "New Student Added", Toast.LENGTH_SHORT).show();
            Log.d("msg", "Student Name:"+s.getStudentName());
            Log.d("msg", "Student Email:"+s.getStudentEmail());
            Log.d("msg", "Student Number:"+s.getStudentPhoneNumber());
            Log.d("msg", "Student Address:"+s.getStudentAddress());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);

            builder.setMessage("Do you want to add Student again?");
            builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Add",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name.setText("");
                    email.setText("");
                    number.setText("");
                    address.setText("");
                    dialog.cancel();

                }
            });
            AlertDialog alert=builder.create();
            alert.show();
        }
    }
}