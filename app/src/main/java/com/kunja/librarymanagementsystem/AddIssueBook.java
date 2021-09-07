package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.model.Book;
import com.kunja.librarymanagementsystem.model.IssueBook;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.kunja.librarymanagementsystem.data.DBHandler;

public class AddIssueBook extends AppCompatActivity {
    EditText book_id, student_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_book);
        book_id=findViewById(R.id.editTextIssueBookId);
        student_id=findViewById(R.id.editTextStudentId);
    }

    public void onIssueBook(View view) {
        DBHandler db = new DBHandler(AddIssueBook.this);

        IssueBook i= new IssueBook();
        int bookId = Integer.parseInt(book_id.getText().toString());
        int studentId = Integer.parseInt(student_id.getText().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String issueDate = sdf.format(new Date());
        String bookAvailability = "Yes";
        String a = db.getBookAvailability(bookId);
        String b = db.getCheckStudent(studentId);
        System.out.println("name is "+a);
        System.out.println("name type "+a.getClass().getSimpleName());
        System.out.println("name is b "+b);
        System.out.println("name type b "+b.getClass().getSimpleName());
        if (a.equals("Yes") && b.equals("Yes")){
                i.setBookId(bookId);
                i.setStudentId(studentId);
                i.setIssueDate(issueDate);
                i.setBookAvailability(bookAvailability);
                Log.d("msg", "Book Id:" + i.getBookId());
                Log.d("msg", "Student Id:" + i.getStudentId());
                Log.d("msg", "Date:" + i.getIssueDate());

                db.addIssueBook(i);
                db.updateBookAvailabilityToNo(bookId);
                Toast.makeText(AddIssueBook.this, "Book is Issued", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);

                builder.setMessage("Do you want to Issue Book again?");
                builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Issue",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        book_id.setText(String.valueOf(0));
                        student_id.setText(String.valueOf(0));
                        dialog.cancel();
                    }
                });
                AlertDialog alert=builder.create();
                alert.show();
        }
        else if(a.equals("Yes")){
            Toast.makeText(AddIssueBook.this, "Student Id are not Found", Toast.LENGTH_SHORT).show();
        }
        else if (bookId == 0 && studentId == 0){
            Toast.makeText(AddIssueBook.this, "Book Id and Student Id are empty", Toast.LENGTH_SHORT).show();
        }
        else if (studentId == 0){
            Toast.makeText(AddIssueBook.this, "Student Id are empty", Toast.LENGTH_SHORT).show();
        }
        else if (bookId == 0){
            Toast.makeText(AddIssueBook.this, "Book Id is empty", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(AddIssueBook.this, "Sorry Book is not Available", Toast.LENGTH_SHORT).show();
            book_id.setText(String.valueOf(0));
            student_id.setText(String.valueOf(0));
        }




    }

}