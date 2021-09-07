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
import com.kunja.librarymanagementsystem.model.IssueBook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReturnBook extends AppCompatActivity {
    EditText return_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);
        return_book=findViewById(R.id.editTextReturnBookId);
    }

    public void onReturnBook(View view) {
        DBHandler db = new DBHandler(ReturnBook.this);
        IssueBook r = new IssueBook();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String returnDate = sdf.format(new Date());
        Integer returnBookId = Integer.parseInt(return_book.getText().toString());
        System.out.println("returnBookId type "+((Object)returnBookId).getClass().getName());

//        String a = db.getCheckIssueBook(returnBookId);
//        System.out.println("name is "+a);
//        System.out.println("name type "+((Object)a).getClass().getName());
//        System.out.println(a +"=="+ returnBookId);
        String a = db.getCheckIssueBook(returnBookId);
        System.out.println("name is "+a);
        System.out.println("name type "+a.getClass().getSimpleName());

        if(a.equals("Yes")){
            r.setReturnDate(returnDate);
            Log.d("msg", "Return Book Id:" + returnBookId);
            Log.d("msg", "Return Date:" + returnDate);

            db.returnIssueBook(returnBookId,returnDate);
            db.updateBookAvailabilityToYes(returnBookId);
            db.updateBookShowToNo(returnBookId);
            Toast.makeText(ReturnBook.this, "Book is Return", Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);

            builder.setMessage("Do you want to Return Book again?");
            builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Return",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return_book.setText(String.valueOf(0));
                    dialog.cancel();
                }
            });
            AlertDialog alert=builder.create();
            alert.show();
        }
        else if (returnBookId==0){
            Toast.makeText(ReturnBook.this, "Book Id is empty", Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(ReturnBook.this, "Sorry Not Found", Toast.LENGTH_SHORT).show();
            return_book.setText(String.valueOf(0));
        }
    }
}